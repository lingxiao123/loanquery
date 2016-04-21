package com.mcjs.action;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.mcjs.entity.CustomFund;
import com.mcjs.entity.LoanLeader;
import com.mcjs.entity.LoanMember;
import com.mcjs.entity.SearchCustomFund;
import com.mcjs.service.CustomFundService;
import com.mcjs.service.LoanLeaderService;
import com.mcjs.service.LoanMemberService;
import com.mcjs.tool.PageTool;
import com.mcjs.util.Convert;
import com.mcjs.util.ExcleTable;
import com.opensymphony.xwork2.ActionContext;

public class CustomFundAction {
	private CustomFund customFund=null;
	private SearchCustomFund searchCustomFund=null;
	private CustomFundService customFundService=null;
	private LoanLeaderService loanLeaderService=null;
	private LoanMemberService loanMemberService=null;
	private String tradAccount=null;
	private String clientName=null;
	PageTool tool=new PageTool();
	private int Page=1;
	private int id=0;
	
	public String findCustomFund() throws Exception{
		try {
			ActionContext ac = ActionContext.getContext();
			String role=ac.getSession().get("role").toString();
			String where="";
			if (role=="系统用户") {
				tool.setTotalCount(this.customFundService.getCustomFund());
				tool.setTotalPage((tool.getTotalCount()%tool.getPageSize()==0)?(tool.getTotalCount() / tool.getPageSize()):(tool.getTotalCount() / tool.getPageSize() + 1));
				/**
				 * 当前页码
				 */
				tool.setCurrPage(Page);
				ac.getSession().put("Page", Page);
				ac.getSession().put("TotalCount", tool.getTotalCount());
				ac.getSession().put("PageCount",tool.getTotalPage());
				List<CustomFund> list = this.customFundService.findCustomFunds(tool);
				ac.getSession().put("list", list);
			}
			if (role=="居间商") {
				int orgid=0;
				if (ac.getSession().get("orgId").toString()!="") {
					orgid=Integer.parseInt(ac.getSession().get("orgId").toString());
				}else {
					//返回登陆页
				}
				String hql=" and loan_orgId="+orgid+" and loan_status=1";
				String loanid="";
				String account="";
				List<LoanLeader> list_leader=this.loanLeaderService.findlLoanLeaders(hql);
				if (list_leader.size()>0) {
					for (int i = 0; i < list_leader.size(); i++) {
						loanid+=list_leader.get(i).getLoan_id()+",";
						if (!"".equals(list_leader.get(i).getLoan_tradAccount())&&list_leader.get(i).getLoan_tradAccount()!=null) {
							account+=list_leader.get(i).getLoan_tradAccount()+",";
						}
					}
					loanid=loanid.substring(0,loanid.lastIndexOf(","));
					loanid=Convert.SpiltStr(loanid);
					String hql_account=" and lm_loanid in ("+loanid+")";
					List<LoanMember> list_member=this.loanMemberService.findLoanMembers(hql_account);
					if (list_member.size()>0) {
						for (int i = 0; i < list_member.size(); i++) {
							account+=list_member.get(i).getLm_tradAccount()+",";
						}
					}
					account=account.substring(0,account.lastIndexOf(","));
					account=Convert.SpiltStr(account);
					where+=" and TradeAccount in ("+account+")";
				}else {
					where+=" and TradeAccount=''";
				}
				tool.setTotalCount(this.customFundService.getCustomFund(where));
				tool.setTotalPage((tool.getTotalCount()%tool.getPageSize()==0)?(tool.getTotalCount() / tool.getPageSize()):(tool.getTotalCount() / tool.getPageSize() + 1));
				/**
				 * 当前页码
				 */
				tool.setCurrPage(Page);
				ac.getSession().put("Page", Page);
				ac.getSession().put("TotalCount", tool.getTotalCount());
				ac.getSession().put("PageCount",tool.getTotalPage());
				List<CustomFund> list = this.customFundService.findCustomFunds(tool,where);
				ac.getSession().put("list", list);
			}
			if (role=="个代") {
				String account="";
				String loanid=ac.getSession().get("loanid").toString();
				String hql_leader=" and loan_id="+loanid+"";
				List<LoanLeader> list_leader=this.loanLeaderService.findlLoanLeaders(hql_leader);
				if (list_leader.size()>0) {
					if (!"".equals(list_leader.get(0).getLoan_tradAccount())&&list_leader.get(0).getLoan_tradAccount()!=null) {
						account+=list_leader.get(0).getLoan_tradAccount()+",";
					}
				}
				loanid=Convert.SpiltStr(loanid);
				String hql_account=" and lm_loanid in ("+loanid+")";
				List<LoanMember> list_member=this.loanMemberService.findLoanMembers(hql_account);
				if (list_member.size()>0) {
					for (int i = 0; i < list_member.size(); i++) {
						account+=list_member.get(i).getLm_tradAccount()+",";
					}
				}
				if (!"".equals(account)) {
					account=account.substring(0,account.lastIndexOf(","));
					account=Convert.SpiltStr(account);
					where+=" and TradeAccount in ("+account+")";
				}else {
					where+=" and TradeAccount =''";
				}
				tool.setTotalCount(this.customFundService.getCustomFund(where));
				tool.setTotalPage((tool.getTotalCount()%tool.getPageSize()==0)?(tool.getTotalCount() / tool.getPageSize()):(tool.getTotalCount() / tool.getPageSize() + 1));
				/**
				 * 当前页码
				 */
				tool.setCurrPage(Page);
				ac.getSession().put("Page", Page);
				ac.getSession().put("TotalCount", tool.getTotalCount());
				ac.getSession().put("PageCount",tool.getTotalPage());
				List<CustomFund> list = this.customFundService.findCustomFunds(tool,where);
				ac.getSession().put("list", list);
			}
			ac.getSession().put("tradaccount", "");
			ac.getSession().put("clientname","");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "findAll_success";
	}
	public String searchCustomFund(){
		try {
			ActionContext ac = ActionContext.getContext();
			String role=ac.getSession().get("role").toString();
			String where="";
			if (role=="居间商") {
				int orgid=0;
				if (ac.getSession().get("orgId").toString()!="") {
					orgid=Integer.parseInt(ac.getSession().get("orgId").toString());
				}else {
					//返回登陆页
				}
				String hql=" and loan_orgId="+orgid+" and loan_status=1";
				String loanid="";
				String account="";
				List<LoanLeader> list_leader=this.loanLeaderService.findlLoanLeaders(hql);
				if (list_leader.size()>0) {
					for (int i = 0; i < list_leader.size(); i++) {
						loanid+=list_leader.get(i).getLoan_id()+",";
						if (!"".equals(list_leader.get(i).getLoan_tradAccount())&&list_leader.get(i).getLoan_tradAccount()!=null) {
							account+=list_leader.get(i).getLoan_tradAccount()+",";
						}
					}
					loanid=loanid.substring(0,loanid.lastIndexOf(","));
					loanid=Convert.SpiltStr(loanid);
					String hql_account=" and lm_loanid in ("+loanid+")";
					List<LoanMember> list_member=this.loanMemberService.findLoanMembers(hql_account);
					if (list_member.size()>0) {
						for (int i = 0; i < list_member.size(); i++) {
							account+=list_member.get(i).getLm_tradAccount()+",";
						}
					}
					account=account.substring(0,account.lastIndexOf(","));
					account=Convert.SpiltStr(account);
					where+=" and TradeAccount in ("+account+")";
				}else {
					where+=" and TradeAccount=''";
				}
			}else if (role=="个代") {
				String account="";
				String loanid=ac.getSession().get("loanid").toString();
				String hql_leader=" and loan_id="+loanid+"";
				List<LoanLeader> list_leader=this.loanLeaderService.findlLoanLeaders(hql_leader);
				if (list_leader.size()>0) {
					if (!"".equals(list_leader.get(0).getLoan_tradAccount())&&list_leader.get(0).getLoan_tradAccount()!=null) {
						account+=list_leader.get(0).getLoan_tradAccount()+",";
					}
				}
				loanid=Convert.SpiltStr(loanid);
				String hql_account=" and lm_loanid in ("+loanid+")";
				List<LoanMember> list_member=this.loanMemberService.findLoanMembers(hql_account);
				if (list_member.size()>0) {
					for (int i = 0; i < list_member.size(); i++) {
						account+=list_member.get(i).getLm_tradAccount()+",";
					}
				}
				if (!"".equals(account)) {
					account=account.substring(0,account.lastIndexOf(","));
					account=Convert.SpiltStr(account);
					where+=" and TradeAccount in ("+account+")";
				}else {
					where+=" and TradeAccount =''";
				}		
			}
			String tradaccount=searchCustomFund.getTradAccount().trim();
			String clientname=URLDecoder.decode(searchCustomFund.getClientName().trim(), "utf-8");
			if (!"".equals(tradaccount)&&tradaccount!=null) {
				where=" and TradeAccount like '%"+tradaccount+"%'";
			}
			if (!"".equals(clientname)&&clientname!=null) {
				where+=" and CustomerName like '%"+clientname+"%'";
			}
			tool.setTotalCount(this.customFundService.getCustomFund(where));
			tool.setTotalPage((tool.getTotalCount()%tool.getPageSize()==0)?(tool.getTotalCount() / tool.getPageSize()):(tool.getTotalCount() / tool.getPageSize() + 1));
			/**
			 * 当前页码
			 */
			tool.setCurrPage(Page);
			ac.getSession().put("Page", Page);
			ac.getSession().put("TotalCount", tool.getTotalCount());
			ac.getSession().put("PageCount",tool.getTotalPage());
			List<CustomFund> list = this.customFundService.findCustomFunds(tool,where);
			ac.getSession().put("list", list);
			ac.getSession().put("tradaccount", tradaccount);
			ac.getSession().put("clientname",clientname);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "findAll_success";
	}
	public String goPage(){
		try {
			ActionContext ac = ActionContext.getContext();
			String role=ac.getSession().get("role").toString();
			String where="";
			if (role=="居间商") {
				int orgid=0;
				if (ac.getSession().get("orgId").toString()!="") {
					orgid=Integer.parseInt(ac.getSession().get("orgId").toString());
				}else {
					//返回登陆页
				}
				String hql=" and loan_orgId="+orgid+" and loan_status=1";
				String loanid="";
				String account="";
				List<LoanLeader> list_leader=this.loanLeaderService.findlLoanLeaders(hql);
				if (list_leader.size()>0) {
					for (int i = 0; i < list_leader.size(); i++) {
						loanid+=list_leader.get(i).getLoan_id()+",";
						if (!"".equals(list_leader.get(i).getLoan_tradAccount())&&list_leader.get(i).getLoan_tradAccount()!=null) {
							account+=list_leader.get(i).getLoan_tradAccount()+",";
						}
					}
					loanid=loanid.substring(0,loanid.lastIndexOf(","));
					loanid=Convert.SpiltStr(loanid);
					String hql_account=" and lm_loanid in ("+loanid+")";
					List<LoanMember> list_member=this.loanMemberService.findLoanMembers(hql_account);
					if (list_member.size()>0) {
						for (int i = 0; i < list_member.size(); i++) {
							account+=list_member.get(i).getLm_tradAccount()+",";
						}
					}
					account=account.substring(0,account.lastIndexOf(","));
					account=Convert.SpiltStr(account);
					where+=" and TradeAccount in ("+account+")";
				}else {
					where+=" and TradeAccount=''";
				}
			}else if (role=="个代") {
				String account="";
				String loanid=ac.getSession().get("loanid").toString();
				String hql_leader=" and loan_id="+loanid+"";
				List<LoanLeader> list_leader=this.loanLeaderService.findlLoanLeaders(hql_leader);
				if (list_leader.size()>0) {
					if (!"".equals(list_leader.get(0).getLoan_tradAccount())&&list_leader.get(0).getLoan_tradAccount()!=null) {
						account+=list_leader.get(0).getLoan_tradAccount()+",";
					}
				}
				loanid=Convert.SpiltStr(loanid);
				String hql_account=" and lm_loanid in ("+loanid+")";
				List<LoanMember> list_member=this.loanMemberService.findLoanMembers(hql_account);
				if (list_member.size()>0) {
					for (int i = 0; i < list_member.size(); i++) {
						account+=list_member.get(i).getLm_tradAccount()+",";
					}
				}
				if (!"".equals(account)) {
					account=account.substring(0,account.lastIndexOf(","));
					account=Convert.SpiltStr(account);
					where+=" and TradeAccount in ("+account+")";
				}else {
					where+=" and TradeAccount =''";
				}		
			}
			clientName= new String(clientName.trim().getBytes("iso8859-1"), "utf-8");
			if (!"".equals(tradAccount)&&tradAccount!=null) {
				where=" and TradeAccount like '%"+tradAccount+"%'";
			}
			if (!"".equals(clientName)&&clientName!=null) {
				where+=" and CustomerName like '%"+clientName+"%'";
			}
			tool.setTotalCount(this.customFundService.getCustomFund(where));
			tool.setTotalPage((tool.getTotalCount()%tool.getPageSize()==0)?(tool.getTotalCount() / tool.getPageSize()):(tool.getTotalCount() / tool.getPageSize() + 1));
			/**
			 * 当前页码
			 */
			tool.setCurrPage(Page);
			ac.getSession().put("Page", Page);
			ac.getSession().put("TotalCount", tool.getTotalCount());
			ac.getSession().put("PageCount",tool.getTotalPage());
			List<CustomFund> list = this.customFundService.findCustomFunds(tool,where);
			ac.getSession().put("list", list);
			ac.getSession().put("tradaccount", tradAccount);
			ac.getSession().put("clientname",clientName);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "findAll_success";
	}
	public String importCustomFund(){
		ActionContext ac = ActionContext.getContext();
		String role=ac.getSession().get("role").toString();
		HttpServletRequest request = ServletActionContext.getRequest();
		String where="";
		if (role=="居间商") {
			int orgid=0;
			if (ac.getSession().get("orgId").toString()!="") {
				orgid=Integer.parseInt(ac.getSession().get("orgId").toString());
			}else {
				//返回登陆页
			}
			String hql=" and loan_orgId="+orgid+" and loan_status=1";
			String loanid="";
			String account="";
			List<LoanLeader> list_leader=this.loanLeaderService.findlLoanLeaders(hql);
			if (list_leader.size()>0) {
				for (int i = 0; i < list_leader.size(); i++) {
					loanid+=list_leader.get(i).getLoan_id()+",";
					if (!"".equals(list_leader.get(i).getLoan_tradAccount())&&list_leader.get(i).getLoan_tradAccount()!=null) {
						account+=list_leader.get(i).getLoan_tradAccount()+",";
					}
				}
				loanid=loanid.substring(0,loanid.lastIndexOf(","));
				loanid=Convert.SpiltStr(loanid);
				String hql_account=" and lm_loanid in ("+loanid+")";
				List<LoanMember> list_member=this.loanMemberService.findLoanMembers(hql_account);
				if (list_member.size()>0) {
					for (int i = 0; i < list_member.size(); i++) {
						account+=list_member.get(i).getLm_tradAccount()+",";
					}
				}
				account=account.substring(0,account.lastIndexOf(","));
				account=Convert.SpiltStr(account);
				where+=" and TradeAccount in ("+account+")";
			}else {
				where+=" and TradeAccount=''";
			}
		}else if (role=="个代") {
			String account="";
			String loanid=ac.getSession().get("loanid").toString();
			String hql_leader=" and loan_id="+loanid+"";
			List<LoanLeader> list_leader=this.loanLeaderService.findlLoanLeaders(hql_leader);
			if (list_leader.size()>0) {
				if (!"".equals(list_leader.get(0).getLoan_tradAccount())&&list_leader.get(0).getLoan_tradAccount()!=null) {
					account+=list_leader.get(0).getLoan_tradAccount()+",";
				}
			}
			loanid=Convert.SpiltStr(loanid);
			String hql_account=" and lm_loanid in ("+loanid+")";
			List<LoanMember> list_member=this.loanMemberService.findLoanMembers(hql_account);
			if (list_member.size()>0) {
				for (int i = 0; i < list_member.size(); i++) {
					account+=list_member.get(i).getLm_tradAccount()+",";
				}
			}
			if (!"".equals(account)) {
				account=account.substring(0,account.lastIndexOf(","));
				account=Convert.SpiltStr(account);
				where+=" and TradeAccount in ("+account+")";
			}else {
				where+=" and TradeAccount =''";
			}		
		}
		String tradaccount=request.getParameter("tradAccount").trim();
		String clientname="";
		try {
			clientname =URLDecoder.decode(request.getParameter("clientName").trim(), "utf-8");
		} catch (UnsupportedEncodingException e1) {
			e1.printStackTrace();
		}
		if (!"".equals(tradaccount)&&tradaccount!=null) {
			where=" and TradeAccount like '%"+tradaccount+"%'";
		}
		if (!"".equals(clientname)&&clientname!=null) {
			where+=" and CustomerName like '%"+clientname+"%'";
		}
		ExcleTable ex=new ExcleTable();
	 	SimpleDateFormat df=new SimpleDateFormat("yyyyMMddHHmm");
	 	java.util.Date date=new java.util.Date();
	 	String datestr=df.format(date);
	 	String fileName = "";
		String title = "客户资金查询";
		fileName = title+datestr+".xls";
	 	OutputStream os = null;
	 	List<CustomFund> list=this.customFundService.findCustomFund(where);
		try {
			HttpServletResponse response =ServletActionContext.getResponse();;
			os = response.getOutputStream();
			response.setContentType("textml;charset=utf-8");
			response.setContentType("application/msexcel");//
			String filename = new String(fileName.getBytes("GBK"), "iso-8859-1");
			response.addHeader("Content-Disposition", "attachment;filename="
					+ filename);
			ex.ExportCustomFund(title, list, os);
		}catch (FileNotFoundException e) {
			System.out.println("FileNotFoundException" + e);
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (os != null) {
					os.flush();
					os.close();
				}
			} catch (IOException e) {
				System.out.println("IOException");
				e.printStackTrace();
			}
		}
		return "success";
	}
	
	public CustomFund getCustomFund() {
		return customFund;
	}
	public void setCustomFund(CustomFund customFund) {
		this.customFund = customFund;
	}
	public CustomFundService getCustomFundService() {
		return customFundService;
	}
	public void setCustomFundService(CustomFundService customFundService) {
		this.customFundService = customFundService;
	}
	public PageTool getTool() {
		return tool;
	}
	public void setTool(PageTool tool) {
		this.tool = tool;
	}
	public int getPage() {
		return Page;
	}
	public void setPage(int page) {
		Page = page;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}

	public SearchCustomFund getSearchCustomFund() {
		return searchCustomFund;
	}

	public void setSearchCustomFund(SearchCustomFund searchCustomFund) {
		this.searchCustomFund = searchCustomFund;
	}
	public LoanLeaderService getLoanLeaderService() {
		return loanLeaderService;
	}
	public void setLoanLeaderService(LoanLeaderService loanLeaderService) {
		this.loanLeaderService = loanLeaderService;
	}
	public LoanMemberService getLoanMemberService() {
		return loanMemberService;
	}
	public void setLoanMemberService(LoanMemberService loanMemberService) {
		this.loanMemberService = loanMemberService;
	}
	public String getTradAccount() {
		return tradAccount;
	}
	public void setTradAccount(String tradAccount) {
		this.tradAccount = tradAccount;
	}
	public String getClientName() {
		return clientName;
	}
	public void setClientName(String clientName) {
		this.clientName = clientName;
	}
}
