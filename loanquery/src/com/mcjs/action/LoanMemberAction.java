package com.mcjs.action;

import java.net.URLDecoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.mcjs.entity.LoanLeader;
import com.mcjs.entity.LoanMember;
import com.mcjs.entity.SearchLoanMerber;
import com.mcjs.service.LoanLeaderService;
import com.mcjs.service.LoanMemberService;
import com.mcjs.tool.PageTool;
import com.opensymphony.xwork2.ActionContext;

public class LoanMemberAction {
	private LoanMember loanMember=null;
	private LoanMemberService loanMemberService=null;
	private LoanLeaderService loanLeaderService=null;
	private SearchLoanMerber searchLoanMerber=null;
	PageTool tool=new PageTool();
	private int Page=1;
	private int id=0;
	private String tradAccount=null;
	private String merber=null;
	private String loanname=null;
	private Map<String,Object> dataMap;
	public String findLoanMember(){
		try {
			ActionContext ac = ActionContext.getContext();
			String role=ac.getSession().get("role").toString();
			if (role=="系统用户") {
				tool.setTotalCount(this.loanMemberService.getLoanMember());
				tool.setTotalPage((tool.getTotalCount()%tool.getPageSize()==0)?(tool.getTotalCount() / tool.getPageSize()):(tool.getTotalCount() / tool.getPageSize() + 1));
				/**
				 * 当前页码
				 */
				tool.setCurrPage(Page);
				ac.getSession().put("Page", Page);
				ac.getSession().put("TotalCount", tool.getTotalCount());
				ac.getSession().put("PageCount",tool.getTotalPage());
				List<LoanMember> memberlist = this.loanMemberService.findLoanMembers(tool);
				if (memberlist.size()>0) {
					for (int i = 0; i < memberlist.size(); i++) {
						List<LoanLeader> loan_list=this.loanLeaderService.findlLoanLeaders(" and loan_id="+memberlist.get(i).getLm_loanid()+"");
						if (loan_list.size()>0) {
							memberlist.get(i).setLm_loanname(loan_list.get(0).getLoan_name());
						}
					}
				}
				ac.getSession().put("memberlist", memberlist);
				ac.getSession().put("merber", "");
				ac.getSession().put("tradaccount", "");
				ac.getSession().put("loanname","");
				List<LoanLeader> loan_list=this.loanLeaderService.findLoanLeaders("");
				if (loan_list.size()>0) {
					ac.getSession().put("loan_list", loan_list);
				}
				ac.getSession().put("loanid", 0);
			}
			if (role=="居间商") {
				int orgid=0;
				if (ac.getSession().get("orgId").toString()!="") {
					orgid=Integer.parseInt(ac.getSession().get("orgId").toString());
				}
				String hql=" and loan_orgId="+orgid+" and loan_status=1";
				String loanid="";
				String where="";
				List<LoanLeader> list_leader=this.loanLeaderService.findlLoanLeaders(hql);
				if (list_leader.size()>0) {
					for (int i = 0; i < list_leader.size(); i++) {
						loanid+=list_leader.get(i).getLoan_id()+",";
					}
					loanid=loanid.substring(0,loanid.lastIndexOf(","));
					where=" and lm_loanid in("+loanid+")";
				}else {
					where=" and lm_loanid=0";
				}
				tool.setTotalCount(this.loanMemberService.getLoanMember(where));
				tool.setTotalPage((tool.getTotalCount()%tool.getPageSize()==0)?(tool.getTotalCount() / tool.getPageSize()):(tool.getTotalCount() / tool.getPageSize() + 1));
				/**
				 * 当前页码
				 */
				tool.setCurrPage(Page);
				ac.getSession().put("Page", Page);
				ac.getSession().put("TotalCount", tool.getTotalCount());
				ac.getSession().put("PageCount",tool.getTotalPage());
				List<LoanMember> memberlist = this.loanMemberService.findLoanMembers(tool,where);	
				if (memberlist.size()>0) {
					for (int i = 0; i < memberlist.size(); i++) {
						List<LoanLeader> loan_list=this.loanLeaderService.findlLoanLeaders(" and loan_id="+memberlist.get(i).getLm_loanid()+"");
						if (loan_list.size()>0) {
							memberlist.get(i).setLm_loanname(loan_list.get(0).getLoan_name());
						}
					}
				}
				ac.getSession().put("memberlist", memberlist);
				ac.getSession().put("merber", "");
				ac.getSession().put("tradaccount", "");
				ac.getSession().put("loanname","");
				List<LoanLeader> loan_list=this.loanLeaderService.findLoanLeaders(" and loan_orgId="+orgid+"");
				if (loan_list.size()>0) {
					ac.getSession().put("loan_list",loan_list);
				}
				ac.getSession().put("loanid", 0);
			}
			if(role=="个代"){
				String loanid=ac.getSession().get("loanid").toString();
				String where=" and lm_loanid="+Integer.parseInt(loanid)+"";
				tool.setTotalCount(this.loanMemberService.getLoanMember(where));
				tool.setTotalPage((tool.getTotalCount()%tool.getPageSize()==0)?(tool.getTotalCount() / tool.getPageSize()):(tool.getTotalCount() / tool.getPageSize() + 1));
				/**
				 * 当前页码
				 */
				tool.setCurrPage(Page);
				ac.getSession().put("Page", Page);
				ac.getSession().put("TotalCount", tool.getTotalCount());
				ac.getSession().put("PageCount",tool.getTotalPage());
				List<LoanMember> memberlist = this.loanMemberService.findLoanMembers(tool,where);
				if (memberlist.size()>0) {
					for (int i = 0; i < memberlist.size(); i++) {
						List<LoanLeader> loan_list=this.loanLeaderService.findlLoanLeaders(" and loan_id="+memberlist.get(i).getLm_loanid()+"");
						if (loan_list.size()>0) {
							memberlist.get(i).setLm_loanname(loan_list.get(0).getLoan_name());
						}
					}
				}
				ac.getSession().put("memberlist", memberlist);
				ac.getSession().put("merber", "");
				ac.getSession().put("tradaccount", "");
				ac.getSession().put("loanname","");
			}			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "findAll_success";
	}
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public String getCheckAccount(){
		try {
			int count=this.loanMemberService.getLoanMember(" and lm_tradAccount='"+tradAccount+"'");
			if (count>0) {
				dataMap=new HashMap();
				dataMap.put("success",false);
			}else {
				dataMap=new HashMap();
				dataMap.put("success",true);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "success";
	}
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public String getCheckAccounts(){
		try {
			int count=this.loanMemberService.getLoanMember(" and lm_tradAccount='"+tradAccount+"' and lm_id !="+id+"");
			if (count>0) {
				dataMap=new HashMap();
				dataMap.put("success",false);
			}else {
				dataMap=new HashMap();
				dataMap.put("success",true);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "success";
	}
	public String addLoanMember(){
		try {
			this.loanMemberService.addLoanMember(loanMember);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "success";
	}
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public String deleteLoanMember(){
		try {
			this.loanMemberService.deleteLoanMember(id);
			dataMap=new HashMap();
			dataMap.put("success",true);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "success";
	}
	public String updateLoanmember(){
		try {
			this.loanMemberService.updateLoanMember(loanMember);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "success";
	}
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public String getLoanMembers(){
		try {
			int counts=this.loanMemberService.getLoanMember(" and lm_loanid="+id+"");
			if (counts>0) {
				dataMap=new HashMap();
				dataMap.put("success",false);
			}else {
				dataMap=new HashMap();
				dataMap.put("success",true);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "success";
	}
	
	public String searchLoanmerber(){
		ActionContext ac = ActionContext.getContext();
		String role=ac.getSession().get("role").toString();
		String where="";
		try {
			if (role.equals("系统用户")) {
				String lmname=URLDecoder.decode(searchLoanMerber.getLm_name().trim(),"utf-8");
				String tradaccount=URLDecoder.decode(searchLoanMerber.getLm_tradAccount().trim(),"utf-8");
				String loanname=URLDecoder.decode(searchLoanMerber.getLm_loanname().trim(),"utf-8");
				if (!"".equals(lmname)&&lmname!=null) {
					where+=" and lm_name like '%"+lmname+"%'";
				}
				if (!"".equals(tradaccount)&&tradaccount!=null) {
					where+=" and lm_tradAccount like '%"+tradaccount+"%'";
				}
				if (!"".equals(loanname)&&loanname!=null) {
					String hql=" and loan_name like '%"+loanname+"%'";
					List<LoanLeader> list=this.loanLeaderService.findlLoanLeaders(hql);
					String ids="";
					if (list.size()>0) {
						for (int i = 0; i < list.size(); i++) {
							ids+=list.get(i).getLoan_id()+",";
						}
						ids=ids.substring(0,ids.lastIndexOf(','));
						where +=" and lm_loanid in("+ids+")";
					}
				}
				tool.setTotalCount(this.loanMemberService.getLoanMember(where));
				tool.setTotalPage((tool.getTotalCount()%tool.getPageSize()==0)?(tool.getTotalCount() / tool.getPageSize()):(tool.getTotalCount() / tool.getPageSize() + 1));
				/**
				 * 当前页码
				 */
				tool.setCurrPage(Page);
				ac.getSession().put("Page", Page);
				ac.getSession().put("TotalCount", tool.getTotalCount());
				ac.getSession().put("PageCount",tool.getTotalPage());
				List<LoanMember> memberlist = this.loanMemberService.findLoanMembers(tool,where);
				if (memberlist.size()>0) {
					for (int i = 0; i < memberlist.size(); i++) {
						List<LoanLeader> loan_list=this.loanLeaderService.findlLoanLeaders(" and loan_id="+memberlist.get(i).getLm_loanid()+"");
						if (loan_list.size()>0) {
							memberlist.get(i).setLm_loanname(loan_list.get(0).getLoan_name());
						}
					}
				}
				ac.getSession().put("memberlist", memberlist);
				ac.getSession().put("merber", lmname);
				ac.getSession().put("tradaccount", tradaccount);
				ac.getSession().put("loanname", loanname);
			}
			if (role.equals("居间商")) {
				String orgid=ac.getSession().get("orgId").toString();
				String lmname=URLDecoder.decode(searchLoanMerber.getLm_name().trim(),"utf-8");
				String tradaccount=URLDecoder.decode(searchLoanMerber.getLm_tradAccount().trim(),"utf-8");
				String loanname=URLDecoder.decode(searchLoanMerber.getLm_loanname().trim(),"utf-8");
				if (!"".equals(lmname)&&lmname!=null) {
					where+=" and lm_name like '%"+lmname+"%'";
				}
				if (!"".equals(tradaccount)&&tradaccount!=null) {
					where+=" and lm_tradAccount like '%"+tradaccount+"%'";
				}
				if (!"".equals(loanname)&&loanname!=null) {
					String hql=" and loan_name like '%"+loanname+"%'";
					List<LoanLeader> list=this.loanLeaderService.findlLoanLeaders(hql);
					String ids="";
					if (list.size()>0) {
						for (int i = 0; i < list.size(); i++) {
							ids+=list.get(i).getLoan_id()+",";
						}
						ids=ids.substring(0,ids.lastIndexOf(','));
						where +=" and lm_loanid in("+ids+")";
					}
				}
				String hql=" and loan_orgId="+orgid+"";
				List<LoanLeader> list=this.loanLeaderService.findlLoanLeaders(hql);
				String ids="";
				if (list.size()>0) {
					for (int i = 0; i < list.size(); i++) {
						ids+=list.get(i).getLoan_id()+",";
					}
					ids=ids.substring(0,ids.lastIndexOf(','));
					where +=" and lm_loanid in("+ids+")";
				}
				tool.setTotalCount(this.loanMemberService.getLoanMember(where));
				tool.setTotalPage((tool.getTotalCount()%tool.getPageSize()==0)?(tool.getTotalCount() / tool.getPageSize()):(tool.getTotalCount() / tool.getPageSize() + 1));
				/**
				 * 当前页码
				 */
				tool.setCurrPage(Page);
				ac.getSession().put("Page", Page);
				ac.getSession().put("TotalCount", tool.getTotalCount());
				ac.getSession().put("PageCount",tool.getTotalPage());
				List<LoanMember> memberlist = this.loanMemberService.findLoanMembers(tool,where);
				if (memberlist.size()>0) {
					for (int i = 0; i < memberlist.size(); i++) {
						List<LoanLeader> loan_list=this.loanLeaderService.findlLoanLeaders(" and loan_id="+memberlist.get(i).getLm_loanid()+"");
						if (loan_list.size()>0) {
							memberlist.get(i).setLm_loanname(loan_list.get(0).getLoan_name());
						}
					}
				}
				ac.getSession().put("memberlist", memberlist);
				ac.getSession().put("merber", lmname);
				ac.getSession().put("tradaccount", tradaccount);
				ac.getSession().put("loanname", loanname);
			}
			if (role.equals("个代主管")) {
				String loanid=ac.getSession().get("loanid").toString();
				String lmname=URLDecoder.decode(searchLoanMerber.getLm_name().trim(),"utf-8");
				String tradaccount=URLDecoder.decode(searchLoanMerber.getLm_tradAccount().trim(),"utf-8");
				if (!"".equals(lmname)&&lmname!=null) {
					where+=" and lm_name like '%"+lmname+"%'";
				}
				if (!"".equals(tradaccount)&&tradaccount!=null) {
					where+=" and lm_tradAccount like '%"+tradaccount+"%'";
				}
				where+=" and lm_loanid="+loanid+"";
				tool.setTotalCount(this.loanMemberService.getLoanMember(where));
				tool.setTotalPage((tool.getTotalCount()%tool.getPageSize()==0)?(tool.getTotalCount() / tool.getPageSize()):(tool.getTotalCount() / tool.getPageSize() + 1));
				/**
				 * 当前页码
				 */
				tool.setCurrPage(Page);
				ac.getSession().put("Page", Page);
				ac.getSession().put("TotalCount", tool.getTotalCount());
				ac.getSession().put("PageCount",tool.getTotalPage());
				List<LoanMember> memberlist = this.loanMemberService.findLoanMembers(tool,where);
				if (memberlist.size()>0) {
					for (int i = 0; i < memberlist.size(); i++) {
						List<LoanLeader> loan_list=this.loanLeaderService.findlLoanLeaders(" and loan_id="+memberlist.get(i).getLm_loanid()+"");
						if (loan_list.size()>0) {
							memberlist.get(i).setLm_loanname(loan_list.get(0).getLoan_name());
						}
					}
				}
				ac.getSession().put("memberlist", memberlist);
				ac.getSession().put("merber", lmname);
				ac.getSession().put("tradaccount", tradaccount);
				ac.getSession().put("loanname", "");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "findAll_success";
	}
	public String goPage(){
		ActionContext ac = ActionContext.getContext();
		String role=ac.getSession().get("role").toString();
		String where="";
		try {
			if(role.equals("居间商")){
				int orgid=0;
				if (ac.getSession().get("orgId").toString()!="") {
					orgid=Integer.parseInt(ac.getSession().get("orgId").toString());
				}
				String hql=" and loan_orgId="+orgid+" and loan_status=1";
				String loanid="";
				List<LoanLeader> list_leader=this.loanLeaderService.findlLoanLeaders(hql);
				if (list_leader.size()>0) {
					for (int i = 0; i < list_leader.size(); i++) {
						loanid+=list_leader.get(i).getLoan_id()+",";
					}
					loanid=loanid.substring(0,loanid.lastIndexOf(","));
					where=" and lm_loanid in("+loanid+")";
				}else {
					where=" and lm_loanid=0";
				}
			}
			if(role.equals("个代")){
				String loanid=ac.getSession().get("loanid").toString();
				where+=" and lm_loanid="+loanid+"";
			}
			String lmname=URLDecoder.decode(merber.trim(),"utf-8");
			String tradaccount=URLDecoder.decode(tradAccount.trim(),"utf-8");
			loanname=new String(loanname.getBytes("iso8859-1"),"utf-8");
			String loannames=URLDecoder.decode(loanname,"utf-8");
			if (!"".equals(lmname)&&lmname!=null) {
				where+=" and lm_name like '%"+lmname+"%'";
			}
			if (!"".equals(tradaccount)&&tradaccount!=null) {
				where+=" and lm_tradAccount like '%"+tradaccount+"%'";
			}
			if (!"".equals(loannames)&&loannames!=null) {
				String hql=" and loan_name like '%"+loanname.trim()+"%'";
				List<LoanLeader> list=this.loanLeaderService.findlLoanLeaders(hql);
				String ids="";
				if (list.size()>0) {
					for (int i = 0; i < list.size(); i++) {
						ids+=list.get(i).getLoan_id()+",";
					}
					ids=ids.substring(0,ids.lastIndexOf(','));
					where +=" and lm_loanid in("+ids+")";
				}
			}
			tool.setTotalCount(this.loanMemberService.getLoanMember(where));
			tool.setTotalPage((tool.getTotalCount()%tool.getPageSize()==0)?(tool.getTotalCount() / tool.getPageSize()):(tool.getTotalCount() / tool.getPageSize() + 1));
			/**
			 * 当前页码
			 */
			tool.setCurrPage(Page);
			ac.getSession().put("Page", Page);
			ac.getSession().put("TotalCount", tool.getTotalCount());
			ac.getSession().put("PageCount",tool.getTotalPage());
			List<LoanMember> memberlist = this.loanMemberService.findLoanMembers(tool,where);
			if (memberlist.size()>0) {
				for (int i = 0; i < memberlist.size(); i++) {
					List<LoanLeader> loan_list=this.loanLeaderService.findlLoanLeaders(" and loan_id="+memberlist.get(i).getLm_loanid()+"");
					if (loan_list.size()>0) {
						memberlist.get(i).setLm_loanname(loan_list.get(0).getLoan_name());
					}
				}
			}
			ac.getSession().put("memberlist", memberlist);
			ac.getSession().put("merber", merber);
			ac.getSession().put("tradaccount", tradAccount);
			ac.getSession().put("loanname",loanname);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "findAll_success";
	}
	public LoanMember getLoanMember() {
		return loanMember;
	}
	public void setLoanMember(LoanMember loanMember) {
		this.loanMember = loanMember;
	}
	public LoanMemberService getLoanMemberService() {
		return loanMemberService;
	}
	public void setLoanMemberService(LoanMemberService loanMemberService) {
		this.loanMemberService = loanMemberService;
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

	public LoanLeaderService getLoanLeaderService() {
		return loanLeaderService;
	}

	public void setLoanLeaderService(LoanLeaderService loanLeaderService) {
		this.loanLeaderService = loanLeaderService;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Map<String, Object> getDataMap() {
		return dataMap;
	}

	public void setDataMap(Map<String, Object> dataMap) {
		this.dataMap = dataMap;
	}
	public String getTradAccount() {
		return tradAccount;
	}
	public void setTradAccount(String tradAccount) {
		this.tradAccount = tradAccount;
	}
	public SearchLoanMerber getSearchLoanMerber() {
		return searchLoanMerber;
	}
	public void setSearchLoanMerber(SearchLoanMerber searchLoanMerber) {
		this.searchLoanMerber = searchLoanMerber;
	}
	public String getMerber() {
		return merber;
	}
	public void setMerber(String merber) {
		this.merber = merber;
	}
	public String getLoanname() {
		return loanname;
	}
	public void setLoanname(String loanname) {
		this.loanname = loanname;
	}
	
	
}
