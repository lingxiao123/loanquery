package com.mcjs.action;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.mcjs.entity.FundStreamSele;
import com.mcjs.entity.LoanLeader;
import com.mcjs.entity.LoanMember;
import com.mcjs.entity.SearchFundStreamSele;
import com.mcjs.service.FundStreamSeleService;
import com.mcjs.service.LoanLeaderService;
import com.mcjs.service.LoanMemberService;
import com.mcjs.tool.PageTool;
import com.mcjs.util.Convert;
import com.mcjs.util.ExcleTable;
import com.opensymphony.xwork2.ActionContext;

public class FundStreamSeleAction {
	private FundStreamSeleService fundStreamSeleService=null;
	private FundStreamSele fundStreamSele=null;
	private SearchFundStreamSele searchFundStreamSele=null;
	private LoanLeaderService loanLeaderService=null;
	private LoanMemberService loanMemberService=null;
	PageTool tool=new PageTool();
	private int Page=1;
	private String tradAccount=null;
	private String clientName=null;
	private String funderStreamNumber=null;
	private String stateTime=null;
	private String endTime=null;
	private int id=0;
	public String findFundStreamSele(){
		try {
			ActionContext ac = ActionContext.getContext();
			String role=ac.getSession().get("role").toString();
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");//设置日期格式
			Calendar date = Calendar.getInstance();
			date.setTime(new Date());
			date.set(Calendar.DATE, date.get(Calendar.DATE) - 1);
			String time=df.format(date.getTime());
			String where="";
			where+=" and BalanceDate >='"+time+"' and BalanceDate <='"+time+"'";
			if (role=="系统用户") {
				tool.setTotalCount(this.fundStreamSeleService.getFundStreamSele(where));
				tool.setTotalPage((tool.getTotalCount()%tool.getPageSize()==0)?(tool.getTotalCount() / tool.getPageSize()):(tool.getTotalCount() / tool.getPageSize() + 1));
				/**
				 * 当前页码
				 */
				tool.setCurrPage(Page);
				ac.getSession().put("Page", Page);
				ac.getSession().put("TotalCount", tool.getTotalCount());
				ac.getSession().put("PageCount",tool.getTotalPage());
				List<FundStreamSele> list = this.fundStreamSeleService.findFundStreamSeles(tool,where);
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
				tool.setTotalCount(this.fundStreamSeleService.getFundStreamSele(where));
				tool.setTotalPage((tool.getTotalCount()%tool.getPageSize()==0)?(tool.getTotalCount() / tool.getPageSize()):(tool.getTotalCount() / tool.getPageSize() + 1));
				/**
				 * 当前页码
				 */
				tool.setCurrPage(Page);
				ac.getSession().put("Page", Page);
				ac.getSession().put("TotalCount", tool.getTotalCount());
				ac.getSession().put("PageCount",tool.getTotalPage());
				List<FundStreamSele> list = this.fundStreamSeleService.findFundStreamSeles(tool,where);
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
				tool.setTotalCount(this.fundStreamSeleService.getFundStreamSele(where));
				tool.setTotalPage((tool.getTotalCount()%tool.getPageSize()==0)?(tool.getTotalCount() / tool.getPageSize()):(tool.getTotalCount() / tool.getPageSize() + 1));
				/**
				 * 当前页码
				 */
				tool.setCurrPage(Page);
				ac.getSession().put("Page", Page);
				ac.getSession().put("TotalCount", tool.getTotalCount());
				ac.getSession().put("PageCount",tool.getTotalPage());
				List<FundStreamSele> list = this.fundStreamSeleService.findFundStreamSeles(tool,where);
				ac.getSession().put("list", list);
			}
			ac.getSession().put("starttime",time);
			ac.getSession().put("endtime",time);
			ac.getSession().put("tradaccount","");
			ac.getSession().put("funderstreamnumber","");
			ac.getSession().put("clientname","");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "findAll_success";
	}
	public String searchFundStream(){
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
			String tradaccount=searchFundStreamSele.getTradAccount().trim();
			String clientname=URLDecoder.decode(searchFundStreamSele.getClientName().trim(), "utf-8");
			String starttime=searchFundStreamSele.getStateTime();
			String endtime=searchFundStreamSele.getEndTime();
			String positionnumber=searchFundStreamSele.getFunderStreamNumber();
			System.out.println(searchFundStreamSele.getTradAccount());
			if (!"".equals(tradaccount)&&tradaccount!=null) {
				where=" and TradeAccount like '%"+tradaccount+"%'";
			}
			if (!"".equals(clientname)&&clientname!=null) {
				where+=" and CustomerName like '%"+clientname+"%'";
			}
			if (!"".equals(positionnumber)) {
				where+=" and SerialNumber like '%"+positionnumber+"%'";
			}
			if (!"".equals(starttime)&&starttime!=null&&endtime=="") {
				where+=" and BalanceDate>='"+starttime+"'";
			}
			if (starttime==""&&!"".equals(endtime)) {
				where+=" and BalanceDate<='"+endtime+"'";
			}
			if (!"".equals(starttime)&&!"".equals(endtime)) {
				where+=" and BalanceDate >='"+starttime+"' and BalanceDate <='"+endtime+"'";
			}

			tool.setTotalCount(this.fundStreamSeleService.getFundStreamSele(where));
			tool.setTotalPage((tool.getTotalCount()%tool.getPageSize()==0)?(tool.getTotalCount() / tool.getPageSize()):(tool.getTotalCount() / tool.getPageSize() + 1));
			/**
			 * 当前页码
			 */
			tool.setCurrPage(Page);
			ac.getSession().put("Page", Page);
			ac.getSession().put("TotalCount", tool.getTotalCount());
			ac.getSession().put("PageCount",tool.getTotalPage());
			List<FundStreamSele> list = this.fundStreamSeleService.findFundStreamSeles(tool,where);
			ac.getSession().put("list", list);
			ac.getSession().put("starttime",starttime);
			ac.getSession().put("endtime",endtime);
			ac.getSession().put("tradaccount",tradaccount);
			ac.getSession().put("funderstreamnumber",positionnumber);
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
			clientName=new String(clientName.trim().getBytes("iso8859-1"), "utf-8");
			if (!"".equals(tradAccount.trim())&&tradAccount.trim()!=null) {
				where=" and TradeAccount like '%"+tradAccount.trim()+"%'";
			}
			if (!"".equals(clientName.trim())&&clientName.trim()!=null) {
				where+=" and CustomerName like '%"+clientName.trim()+"%'";
			}
			if (!"".equals(funderStreamNumber.trim())) {
				where+=" and SerialNumber like '%"+funderStreamNumber.trim()+"%'";
			}
			if (!"".equals(stateTime.trim())&&stateTime.trim()!=null&&endTime.trim()=="") {
				where+=" and BalanceDate>='"+stateTime.trim()+"'";
			}
			if (stateTime.trim()==""&&!"".equals(endTime.trim())) {
				where+=" and BalanceDate<='"+endTime.trim()+"'";
			}
			if (!"".equals(stateTime.trim())&&!"".equals(endTime.trim())) {
				where+=" and BalanceDate >='"+stateTime.trim()+"' and BalanceDate <='"+endTime.trim()+"'";
			}

			tool.setTotalCount(this.fundStreamSeleService.getFundStreamSele(where));
			tool.setTotalPage((tool.getTotalCount()%tool.getPageSize()==0)?(tool.getTotalCount() / tool.getPageSize()):(tool.getTotalCount() / tool.getPageSize() + 1));
			/**
			 * 当前页码
			 */
			tool.setCurrPage(Page);
			ac.getSession().put("Page", Page);
			ac.getSession().put("TotalCount", tool.getTotalCount());
			ac.getSession().put("PageCount",tool.getTotalPage());
			List<FundStreamSele> list = this.fundStreamSeleService.findFundStreamSeles(tool,where);
			ac.getSession().put("list", list);
			ac.getSession().put("starttime",stateTime);
			ac.getSession().put("endtime",endTime);
			ac.getSession().put("tradaccount",tradAccount);
			ac.getSession().put("funderstreamnumber",funderStreamNumber);
			ac.getSession().put("clientname",clientName);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "findAll_success";
	}
	public String importFundStreamSele(){
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
			clientname =new String(request.getParameter("clientName").trim().getBytes("iso8859-1"), "utf-8");
		} catch (UnsupportedEncodingException e1) {
			e1.printStackTrace();
		}
		String starttime=request.getParameter("stateTime").trim();
		String endtime=request.getParameter("endTime").trim();
		String positionnumber=request.getParameter("funderStreamNumber").trim();
		if (!"".equals(tradaccount)&&tradaccount!=null) {
			where=" and TradeAccount like '%"+tradaccount+"%'";
		}
		if (!"".equals(clientname)&&clientname!=null) {
			where+=" and CustomerName like '%"+clientname+"%'";
		}
		if (!"".equals(positionnumber)) {
			where+=" and SerialNumber like '%"+positionnumber+"%'";
		}
		if (!"".equals(starttime)&&starttime!=null&&endtime=="") {
			where+=" and BalanceDate>='"+starttime+"'";
		}
		if (starttime==""&&!"".equals(endtime)) {
			where+=" and BalanceDate<='"+endtime+"'";
		}
		if (!"".equals(starttime)&&!"".equals(endtime)) {
			where+=" and BalanceDate >='"+starttime+"' and BalanceDate <='"+endtime+"'";
		}
		ExcleTable ex=new ExcleTable();
	 	SimpleDateFormat df=new SimpleDateFormat("yyyyMMddHHmm");
	 	java.util.Date date=new java.util.Date();
	 	String datestr=df.format(date);
	 	String fileName = "";
		String title = "客户资金流水查询";
		fileName = title+datestr+".xls";
	 	OutputStream os = null;
	 	List<FundStreamSele> list=this.fundStreamSeleService.findFundStreamSele(where);
		try {
			HttpServletResponse response =ServletActionContext.getResponse();
			os = response.getOutputStream();
			response.setContentType("textml;charset=utf-8");
			response.setContentType("application/msexcel");//
			String filename = new String(fileName.getBytes("GBK"), "iso-8859-1");
			response.addHeader("Content-Disposition", "attachment;filename="+ filename);
			ex.ExportFundStream(title, list, os);
		} catch (FileNotFoundException e) {
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
	public FundStreamSeleService getFundStreamSeleService() {
		return fundStreamSeleService;
	}
	public void setFundStreamSeleService(FundStreamSeleService fundStreamSeleService) {
		this.fundStreamSeleService = fundStreamSeleService;
	}
	public FundStreamSele getFundStreamSele() {
		return fundStreamSele;
	}
	public void setFundStreamSele(FundStreamSele fundStreamSele) {
		this.fundStreamSele = fundStreamSele;
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
	public SearchFundStreamSele getSearchFundStreamSele() {
		return searchFundStreamSele;
	}
	public void setSearchFundStreamSele(SearchFundStreamSele searchFundStreamSele) {
		this.searchFundStreamSele = searchFundStreamSele;
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
	public String getFunderStreamNumber() {
		return funderStreamNumber;
	}
	public void setFunderStreamNumber(String funderStreamNumber) {
		this.funderStreamNumber = funderStreamNumber;
	}
	public String getStateTime() {
		return stateTime;
	}
	public void setStateTime(String stateTime) {
		this.stateTime = stateTime;
	}
	public String getEndTime() {
		return endTime;
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	
}
