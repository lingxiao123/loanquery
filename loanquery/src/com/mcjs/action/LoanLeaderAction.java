package com.mcjs.action;

import java.net.URLDecoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.mcjs.entity.LoanLeader;
import com.mcjs.entity.OrgInfo;
import com.mcjs.entity.PostData;
import com.mcjs.entity.Role;
import com.mcjs.entity.RoleAuthorize;
import com.mcjs.entity.SearchLoan;
import com.mcjs.service.LoanLeaderService;
import com.mcjs.service.OrgInfoService;
import com.mcjs.service.PurviewService;
import com.mcjs.service.RoleService;
import com.mcjs.tool.PageTool;
import com.mcjs.util.MD5Util;
import com.opensymphony.xwork2.ActionContext;

public class LoanLeaderAction {
	private LoanLeaderService loanLeaderService=null;
	private LoanLeader loanLeader=null;
	private RoleService roleService=null;
	private PurviewService purviewService=null;
	private OrgInfoService orgInfoService=null;
	private SearchLoan searchLoaninfo=null;
	private Map<String,Object> dataMap;
	PageTool tool=new PageTool();
	private int Page=1;
	private int id=0;
	private int status=0;
	private String loan_name;
	private String loan_orgname;
	public String findLoanLeader(){
		try {
			ActionContext ac = ActionContext.getContext();
			String role=ac.getSession().get("role").toString();
			if (role=="系统用户") {
				tool.setTotalCount(this.loanLeaderService.getLoanLeader());
				tool.setTotalPage((tool.getTotalCount()%tool.getPageSize()==0)?(tool.getTotalCount() / tool.getPageSize()):(tool.getTotalCount() / tool.getPageSize() + 1));
				/**
				 * 当前页码
				 */
				tool.setCurrPage(Page);
				ac.getSession().put("Page", Page);
				ac.getSession().put("TotalCount", tool.getTotalCount());
				ac.getSession().put("PageCount",tool.getTotalPage());
				List<LoanLeader> loanList = this.loanLeaderService.findLoanLeaders(tool);
				if (loanList.size()>0) {
					for (int i = 0; i < loanList.size(); i++) {
						List<OrgInfo> org_list=this.orgInfoService.findOrginfo(loanList.get(i).getLoan_orgId());
						if (org_list.size()>0) {
							loanList.get(i).setLoan_orgname(org_list.get(0).getOrg_Abbreviation());
						}
					}
				}
				ac.getSession().put("loanList", loanList);
				if (!"".equals(ac.getSession().get("user_role"))&&ac.getSession().get("user_role")!=null) {
					String roleid=ac.getSession().get("user_role").toString();
					List<RoleAuthorize> ralist=this.purviewService.findrRoleAuthorizes(Integer.parseInt(roleid));
					ac.getSession().put("ralist", ralist);
				}
				List<OrgInfo> org_list=this.orgInfoService.findOrgInfo();
				if (org_list.size()>0) {
					ac.getSession().put("org_list", org_list);
				}
				ac.getSession().put("orgId", 0);
				ac.getSession().put("orgname", "");
				ac.getSession().put("loanname", "");
			}
			if (role=="居间商") {
				int orgid=0;
				if (ac.getSession().get("orgId").toString()!="") {
					orgid=Integer.parseInt(ac.getSession().get("orgId").toString());
				}else {
					//返回登陆页
				}
				String hql=" and loan_orgId="+orgid+"";
				String loanid="";
				String where="";
				List<LoanLeader> list_leader=this.loanLeaderService.findlLoanLeaders(hql);
				if (list_leader.size()>0) {
					for (int i = 0; i < list_leader.size(); i++) {
						loanid+=list_leader.get(i).getLoan_id()+",";
					}
					loanid=loanid.substring(0,loanid.lastIndexOf(","));
					where=" and loan_id in("+loanid+")";
				}else {
					where=" and loan_orgId="+orgid+"";
				}
				tool.setTotalCount(this.loanLeaderService.getLoanLeader(where));
				tool.setTotalPage((tool.getTotalCount()%tool.getPageSize()==0)?(tool.getTotalCount() / tool.getPageSize()):(tool.getTotalCount() / tool.getPageSize() + 1));
				/**
				 * 当前页码
				 */
				tool.setCurrPage(Page);
				ac.getSession().put("Page", Page);
				ac.getSession().put("TotalCount", tool.getTotalCount());
				ac.getSession().put("PageCount",tool.getTotalPage());
				List<LoanLeader> loanList = this.loanLeaderService.findLoanLeaders(tool,where);
				if (loanList.size()>0) {
					for (int i = 0; i < loanList.size(); i++) {
						List<OrgInfo> org_list=this.orgInfoService.findOrginfo(loanList.get(i).getLoan_orgId());
						if (org_list.size()>0) {
							loanList.get(i).setLoan_orgname(org_list.get(0).getOrg_Abbreviation());
						}
					}
				}
				ac.getSession().put("loanList", loanList);
				if (!"".equals(ac.getSession().get("org_RoleId"))&&ac.getSession().get("org_RoleId")!=null) {
					String roleid=ac.getSession().get("org_RoleId").toString();
					List<RoleAuthorize> ralist=this.purviewService.findrRoleAuthorizes(Integer.parseInt(roleid));
					ac.getSession().put("ralist", ralist);
				}
				ac.getSession().put("orgname", "");
				ac.getSession().put("loanname", "");
			}
			if(role=="个代"){
				String loanid=ac.getSession().get("loanid").toString();
				String where=" and loan_id="+Integer.parseInt(loanid)+"";
				tool.setTotalCount(this.loanLeaderService.getLoanLeader(where));
				tool.setTotalPage((tool.getTotalCount()%tool.getPageSize()==0)?(tool.getTotalCount() / tool.getPageSize()):(tool.getTotalCount() / tool.getPageSize() + 1));
				/**
				 * 当前页码
				 */
				tool.setCurrPage(Page);
				ac.getSession().put("Page", Page);
				ac.getSession().put("TotalCount", tool.getTotalCount());
				ac.getSession().put("PageCount",tool.getTotalPage());
				List<LoanLeader> loanList = this.loanLeaderService.findLoanLeaders(tool,where);
				if (loanList.size()>0) {
					for (int i = 0; i < loanList.size(); i++) {
						List<OrgInfo> org_list=this.orgInfoService.findOrginfo(loanList.get(i).getLoan_orgId());
						if (org_list.size()>0) {
							loanList.get(i).setLoan_orgname(org_list.get(0).getOrg_Abbreviation());
						}
					}
				}
				System.out.println(ac.getSession().get("loan_roleId"));
				ac.getSession().put("loanList", loanList);
				if (!"".equals(ac.getSession().get("loan_roleId"))&&ac.getSession().get("org_RoleId")!=null) {
					String roleid=ac.getSession().get("loan_roleId").toString();
					List<RoleAuthorize> ralist=this.purviewService.findrRoleAuthorizes(Integer.parseInt(roleid));
					ac.getSession().put("ralist", ralist);
				}
				ac.getSession().put("orgname", "");
				ac.getSession().put("loanname", "");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "findAll_success";
	}
	
	public String addLoanLeader(){
		try {
			String hql=" and role_roleName like '个代%'";
			List<Role> list=this.roleService.findRoles(hql);
			if (list.size()>0) {
				loanLeader.setLoan_roleId(list.get(0).getRole_id());
			}
			this.getLoanLeaderService().addLoanLeader(loanLeader);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "success";
	}
	public String updateLoan(){
		try {
			loanLeader.setLoan_passWord(MD5Util.convertMD5(loanLeader.getLoan_passWord()));
			this.loanLeaderService.updateLoanLeader(loanLeader);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "success";
	}
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public String getLoanLeaders(){
		try {
			int count=this.loanLeaderService.getLoanLeader(" and loan_orgId="+id+"");
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
	public String updateLoanStatus(){
		try {
			List<LoanLeader> list=this.loanLeaderService.findLoanLeaders(id);
			LoanLeader ll=new LoanLeader();
			if (list.size()>0) {
				ll=list.get(0);
			}
			ll.setLoan_status(status);
			this.loanLeaderService.updateLoanLeader(ll);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "success";
	}
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public String deleteLoan(){
		try {
			this.loanLeaderService.deleteLoanLeader(id);
			dataMap=new HashMap();
			dataMap.put("success",true);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "success";
	}
	public String searchLoan(){
		ActionContext ac = ActionContext.getContext();
		String where="";
		String role=ac.getSession().get("role").toString();
		try {
			if (role.equals("系统用户")) {
				String loanname=URLDecoder.decode(searchLoaninfo.getLoan_name().trim(),"utf-8");
				String orgname=URLDecoder.decode(searchLoaninfo.getLoan_orgname().trim(),"utf-8");
				if (!"".equals(loanname)&&loanname!=null) {
					where+=" and loan_name like '%"+loanname+"%'";
				}
				if (!"".equals(orgname)&&orgname!=null) {
					String hql=" and org_Abbreviation like '%"+orgname+"%'";
					List<OrgInfo> list=orgInfoService.findOrginfo(hql);
					String ids="";
					if (list.size()>0) {
						for (int i = 0; i < list.size(); i++) {
							ids+=list.get(i).getOrg_id()+",";
						}
						ids=ids.substring(0,ids.lastIndexOf(','));
						where +=" and loan_orgId in("+ids+")";
					}
				}
				tool.setTotalCount(this.loanLeaderService.getLoanLeader(where));
				tool.setTotalPage((tool.getTotalCount()%tool.getPageSize()==0)?(tool.getTotalCount() / tool.getPageSize()):(tool.getTotalCount() / tool.getPageSize() + 1));
				/**
				 * 当前页码
				 */
				tool.setCurrPage(Page);
				ac.getSession().put("Page", Page);
				ac.getSession().put("TotalCount", tool.getTotalCount());
				ac.getSession().put("PageCount",tool.getTotalPage());
				List<LoanLeader> loanList = this.loanLeaderService.findLoanLeaders(tool,where);
				if (loanList.size()>0) {
					for (int i = 0; i < loanList.size(); i++) {
						List<OrgInfo> org_list=this.orgInfoService.findOrginfo(loanList.get(i).getLoan_orgId());
						if (org_list.size()>0) {
							loanList.get(i).setLoan_orgname(org_list.get(0).getOrg_Abbreviation());
						}
					}
				}
				ac.getSession().put("loanList", loanList);
				ac.getSession().put("orgname", orgname);
				ac.getSession().put("loanname", loanname);
			}
			if (role.equals("居间商")) {
				String orgid=ac.getSession().get("orgId").toString();
				String loanname=URLDecoder.decode(searchLoaninfo.getLoan_name().trim(),"utf-8");
				if (!"".equals(loanname)&&loanname!=null) {
					where+=" and loan_name like '%"+loanname+"%'";
				}
				where+=" and loan_orgId="+orgid+"";
				tool.setTotalCount(this.loanLeaderService.getLoanLeader(where));
				tool.setTotalPage((tool.getTotalCount()%tool.getPageSize()==0)?(tool.getTotalCount() / tool.getPageSize()):(tool.getTotalCount() / tool.getPageSize() + 1));
				/**
				 * 当前页码
				 */
				tool.setCurrPage(Page);
				ac.getSession().put("Page", Page);
				ac.getSession().put("TotalCount", tool.getTotalCount());
				ac.getSession().put("PageCount",tool.getTotalPage());
				List<LoanLeader> loanList = this.loanLeaderService.findLoanLeaders(tool,where);
				if (loanList.size()>0) {
					for (int i = 0; i < loanList.size(); i++) {
						List<OrgInfo> org_list=this.orgInfoService.findOrginfo(loanList.get(i).getLoan_orgId());
						if (org_list.size()>0) {
							loanList.get(i).setLoan_orgname(org_list.get(0).getOrg_Abbreviation());
						}
					}
				}
				ac.getSession().put("loanList", loanList);
				ac.getSession().put("loanname", loanname);
				ac.getSession().put("orgname", "");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "findAll_success";
	}
	public String goPage(){
		try {
			ActionContext ac = ActionContext.getContext();
			String where="";
			String role=ac.getSession().get("role").toString();
			if (role.equals("系统用户")) {
				String loanname=new String(loan_name.getBytes("iso8859-1"),"utf-8");
				String orgname=new String(loan_orgname.getBytes("iso8859-1"),"utf-8");
				if (!"".equals(loanname)&&loanname!=null) {
					where+=" and loan_name like '%"+loanname+"%'";
				}
				if (!"".equals(orgname)&&orgname!=null) {
					String hql=" and org_Abbreviation like '%"+orgname+"%'";
					List<OrgInfo> list=orgInfoService.findOrginfo(hql);
					String ids="";
					if (list.size()>0) {
						for (int i = 0; i < list.size(); i++) {
							ids+=list.get(i).getOrg_id()+",";
						}
						ids=ids.substring(0,ids.lastIndexOf(','));
						where +=" and loan_orgId in("+ids+")";
					}
				}
				tool.setTotalCount(this.loanLeaderService.getLoanLeader(where));
				tool.setTotalPage((tool.getTotalCount()%tool.getPageSize()==0)?(tool.getTotalCount() / tool.getPageSize()):(tool.getTotalCount() / tool.getPageSize() + 1));
				/**
				 * 当前页码
				 */
				tool.setCurrPage(Page);
				ac.getSession().put("Page", Page);
				ac.getSession().put("TotalCount", tool.getTotalCount());
				ac.getSession().put("PageCount",tool.getTotalPage());
				List<LoanLeader> loanList = this.loanLeaderService.findLoanLeaders(tool,where);
				if (loanList.size()>0) {
					for (int i = 0; i < loanList.size(); i++) {
						List<OrgInfo> org_list=this.orgInfoService.findOrginfo(loanList.get(i).getLoan_orgId());
						if (org_list.size()>0) {
							loanList.get(i).setLoan_orgname(org_list.get(0).getOrg_Abbreviation());
						}
					}
				}
				ac.getSession().put("loanList", loanList);
				ac.getSession().put("orgname", orgname);
				ac.getSession().put("loanname", loanname);
			}
			if (role.equals("居间商")) {
				String orgid=ac.getSession().get("orgId").toString();
				String loanname=new String(loan_name.getBytes("iso8859-1"),"utf-8");
				if (!"".equals(loanname)&&loanname!=null) {
					where+=" and loan_name like '%"+loanname+"%'";
				}
				where+=" and loan_orgId="+orgid+"";
				tool.setTotalCount(this.loanLeaderService.getLoanLeader(where));
				tool.setTotalPage((tool.getTotalCount()%tool.getPageSize()==0)?(tool.getTotalCount() / tool.getPageSize()):(tool.getTotalCount() / tool.getPageSize() + 1));
				/**
				 * 当前页码
				 */
				tool.setCurrPage(Page);
				ac.getSession().put("Page", Page);
				ac.getSession().put("TotalCount", tool.getTotalCount());
				ac.getSession().put("PageCount",tool.getTotalPage());
				List<LoanLeader> loanList = this.loanLeaderService.findLoanLeaders(tool,where);
				if (loanList.size()>0) {
					for (int i = 0; i < loanList.size(); i++) {
						List<OrgInfo> org_list=this.orgInfoService.findOrginfo(loanList.get(i).getLoan_orgId());
						if (org_list.size()>0) {
							loanList.get(i).setLoan_orgname(org_list.get(0).getOrg_Abbreviation());
						}
					}
				}
				ac.getSession().put("loanList", loanList);
				ac.getSession().put("loanname", loanname);
				ac.getSession().put("orgname", "");
			}
			if (role.equals("个代")) {
				String loanid=ac.getSession().get("loanid").toString();
				where+=" and loan_id="+loanid+"";
				tool.setTotalCount(this.loanLeaderService.getLoanLeader(where));
				tool.setTotalPage((tool.getTotalCount()%tool.getPageSize()==0)?(tool.getTotalCount() / tool.getPageSize()):(tool.getTotalCount() / tool.getPageSize() + 1));
				/**
				 * 当前页码
				 */
				tool.setCurrPage(Page);
				ac.getSession().put("Page", Page);
				ac.getSession().put("TotalCount", tool.getTotalCount());
				ac.getSession().put("PageCount",tool.getTotalPage());
				List<LoanLeader> loanList = this.loanLeaderService.findLoanLeaders(tool,where);
				if (loanList.size()>0) {
					for (int i = 0; i < loanList.size(); i++) {
						List<OrgInfo> org_list=this.orgInfoService.findOrginfo(loanList.get(i).getLoan_orgId());
						if (org_list.size()>0) {
							loanList.get(i).setLoan_orgname(org_list.get(0).getOrg_Abbreviation());
						}
					}
				}
				ac.getSession().put("loanList", loanList);
				ac.getSession().put("loanname", "");
				ac.getSession().put("orgname", "");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "findAll_success";
	}
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public String AutocompleteLoan(){
		HttpServletRequest request=ServletActionContext.getRequest();
		ActionContext ac = ActionContext.getContext();
		String where="";
		String role=ac.getSession().get("role").toString();
		try {
			if (role.equals("居间商")) {
				String orgid=ac.getSession().get("orgId").toString();
				where+=" and loan_orgId="+orgid+"";
			}
			String keyword=request.getParameter("keyword").trim();
			List<PostData> list=this.loanLeaderService.finLoanName(keyword, where);
			dataMap=new HashMap();
			dataMap.put("list",list);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "success";
	}
	public LoanLeaderService getLoanLeaderService() {
		return loanLeaderService;
	}
	public void setLoanLeaderService(LoanLeaderService loanLeaderService) {
		this.loanLeaderService = loanLeaderService;
	}
	public LoanLeader getLoanLeader() {
		return loanLeader;
	}
	public void setLoanLeader(LoanLeader loanLeader) {
		this.loanLeader = loanLeader;
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

	public RoleService getRoleService() {
		return roleService;
	}

	public void setRoleService(RoleService roleService) {
		this.roleService = roleService;
	}

	public PurviewService getPurviewService() {
		return purviewService;
	}

	public void setPurviewService(PurviewService purviewService) {
		this.purviewService = purviewService;
	}

	public OrgInfoService getOrgInfoService() {
		return orgInfoService;
	}

	public void setOrgInfoService(OrgInfoService orgInfoService) {
		this.orgInfoService = orgInfoService;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public Map<String, Object> getDataMap() {
		return dataMap;
	}

	public void setDataMap(Map<String, Object> dataMap) {
		this.dataMap = dataMap;
	}

	public SearchLoan getSearchLoaninfo() {
		return searchLoaninfo;
	}

	public void setSearchLoaninfo(SearchLoan searchLoaninfo) {
		this.searchLoaninfo = searchLoaninfo;
	}

	public String getLoan_name() {
		return loan_name;
	}

	public void setLoan_name(String loan_name) {
		this.loan_name = loan_name;
	}

	public String getLoan_orgname() {
		return loan_orgname;
	}

	public void setLoan_orgname(String loan_orgname) {
		this.loan_orgname = loan_orgname;
	}
}
