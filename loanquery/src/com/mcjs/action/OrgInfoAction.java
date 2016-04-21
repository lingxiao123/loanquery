package com.mcjs.action;

import java.net.URLDecoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import sun.org.mozilla.javascript.internal.ContextAction;

import com.mcjs.entity.OrgInfo;
import com.mcjs.entity.PostData;
import com.mcjs.entity.Role;
import com.mcjs.entity.RoleAuthorize;
import com.mcjs.entity.SearchOrg;
import com.mcjs.service.OrgInfoService;
import com.mcjs.service.PurviewService;
import com.mcjs.service.RoleService;
import com.mcjs.tool.PageTool;
import com.mcjs.util.MD5Util;
import com.opensymphony.xwork2.ActionContext;

public class OrgInfoAction {
	private OrgInfoService orgInfoService=null;
	private RoleService roleService=null;
	private PurviewService purviewService=null;
	private SearchOrg searchOrginfo=null;
	private OrgInfo orgInfo=null;
	private Map<String,Object> dataMap;
	PageTool tool=new PageTool();
	private int Page=1;
	private int id=0;
	private int status;
	private String org_Abbreviation;
	private String orgname;
	public String findOrgInfo(){
		try {
			ActionContext ac = ActionContext.getContext();
			String role=ac.getSession().get("role").toString();
			if (role=="系统用户") {
				tool.setTotalCount(this.orgInfoService.getOrgInfo());
				tool.setTotalPage((tool.getTotalCount()%tool.getPageSize()==0)?(tool.getTotalCount() / tool.getPageSize()):(tool.getTotalCount() / tool.getPageSize() + 1));
				/**
				 * 当前页码
				 */
				tool.setCurrPage(Page);
				ac.getSession().put("Page", Page);
				ac.getSession().put("TotalCount", tool.getTotalCount());
				ac.getSession().put("PageCount",tool.getTotalPage());
				List<OrgInfo> orgList = this.orgInfoService.findOrgInfo(tool);
				ac.getSession().put("orgList", orgList);
				if (!"".equals(ac.getSession().get("user_role"))&&ac.getSession().get("org_RoleId")!=null) {
					String roleid=ac.getSession().get("user_role").toString();
					List<RoleAuthorize> ralist=this.purviewService.findrRoleAuthorizes(Integer.parseInt(roleid));
					ac.getSession().put("ralist", ralist);
				}
				ac.getSession().put("orgname", "");
			}
			if (role=="居间商") {
				int orgid=0;
				if (ac.getSession().get("orgId").toString()!="") {
					orgid=Integer.parseInt(ac.getSession().get("orgId").toString());
				}else {
					//返回登陆页
				}
				String where=" and org_id="+orgid+"";
				tool.setTotalCount(this.orgInfoService.getOrgInfo(where));
				tool.setTotalPage((tool.getTotalCount()%tool.getPageSize()==0)?(tool.getTotalCount() / tool.getPageSize()):(tool.getTotalCount() / tool.getPageSize() + 1));
				/**
				 * 当前页码
				 */
				tool.setCurrPage(Page);
				ac.getSession().put("Page", Page);
				ac.getSession().put("TotalCount", tool.getTotalCount());
				ac.getSession().put("PageCount",tool.getTotalPage());
				List<OrgInfo> orgList = this.orgInfoService.findOrgInfo(tool,where);
				ac.getSession().put("orgList", orgList);
				if (!"".equals(ac.getSession().get("org_RoleId"))&&ac.getSession().get("org_RoleId")!=null) {
					String roleid=ac.getSession().get("org_RoleId").toString();
					List<RoleAuthorize> ralist=this.purviewService.findrRoleAuthorizes(Integer.parseInt(roleid));
					ac.getSession().put("ralist", ralist);
				}
				ac.getSession().put("orgname", "");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "findAll_success";
	}
	public String addOrgInfo(){
		try {
			String hql=" and role_roleName like '居间%'";
			List<Role> list=this.roleService.findRoles(hql);
			if (list.size()>0) {
				orgInfo.setOrg_RoleId(list.get(0).getRole_id());
			}
			this.orgInfoService.addOrgInfo(orgInfo);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "success";
	}
	public String UpdateOrgInfo(){
		try {
			orgInfo.setOrg_PassWord(MD5Util.convertMD5(orgInfo.getOrg_PassWord().trim()));
			this.orgInfoService.updateOrginfo(orgInfo);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "success";
	}
	public String updateStatusOrg(){
		try {
			List<OrgInfo> list=this.orgInfoService.findOrginfo(id);
			OrgInfo org=new OrgInfo();
			if (list.size()>0) {
				org=list.get(0);
			}
			if (id!=0) {
				org.setOrg_id(id);
				org.setOrg_Status(status);
			}
			this.orgInfoService.updateOrginfo(org);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "success";
	}
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public String deleteOrgInfo(){
		try {
			this.orgInfoService.deleteOrgInfo(id);
			dataMap=new HashMap();
			dataMap.put("success",true);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "success";
	}
	
	public String searchOrg(){
		ActionContext ac = ActionContext.getContext();
		try {
			String where="";
			String org=URLDecoder.decode(searchOrginfo.getOrg_Abbreviation().trim(),"utf-8");
			if (!"".equals(org)&&org!=null) {
				where+=" and org_Abbreviation like '%"+org+"%'";
			}
			tool.setTotalCount(this.orgInfoService.getOrgInfo(where));
			tool.setTotalPage((tool.getTotalCount()%tool.getPageSize()==0)?(tool.getTotalCount() / tool.getPageSize()):(tool.getTotalCount() / tool.getPageSize() + 1));
			/**
			 * 当前页码
			 */
			tool.setCurrPage(Page);
			ac.getSession().put("Page", Page);
			ac.getSession().put("TotalCount", tool.getTotalCount());
			ac.getSession().put("PageCount",tool.getTotalPage());
			List<OrgInfo> orgList = this.orgInfoService.findOrgInfo(tool,where);
			ac.getSession().put("orgList", orgList);
			ac.getSession().put("orgname", org);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "findAll_success";
	}
	public String goPage(){
		ActionContext ac = ActionContext.getContext();
		String where="";
		try {
			String org=new String(orgname.getBytes("iso8859-1"),"utf-8");
			if (!"".equals(org)&&org!=null) {
				where+=" and org_Abbreviation like '%"+org+"%'";
			}
			tool.setTotalCount(this.orgInfoService.getOrgInfo(where));
			tool.setTotalPage((tool.getTotalCount()%tool.getPageSize()==0)?(tool.getTotalCount() / tool.getPageSize()):(tool.getTotalCount() / tool.getPageSize() + 1));
			/**
			 * 当前页码
			 */
			tool.setCurrPage(Page);
			ac.getSession().put("Page", Page);
			ac.getSession().put("TotalCount", tool.getTotalCount());
			ac.getSession().put("PageCount",tool.getTotalPage());
			List<OrgInfo> orgList = this.orgInfoService.findOrgInfo(tool,where);
			ac.getSession().put("orgList", orgList);
			ac.getSession().put("orgname", org);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "findAll_success";
	}
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public String AutocompleteOrg(){
		HttpServletRequest request=ServletActionContext.getRequest();
		try {
			String keyword=request.getParameter("keyword").trim();
			List<PostData> list=this.orgInfoService.finOrginfos(keyword);
			dataMap=new HashMap();
			dataMap.put("list",list);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "success";
	}
	public OrgInfoService getOrgInfoService() {
		return orgInfoService;
	}

	public void setOrgInfoService(OrgInfoService orgInfoService) {
		this.orgInfoService = orgInfoService;
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
	public OrgInfo getOrgInfo() {
		return orgInfo;
	}
	public void setOrgInfo(OrgInfo orgInfo) {
		this.orgInfo = orgInfo;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public RoleService getRoleService() {
		return roleService;
	}
	public void setRoleService(RoleService roleService) {
		this.roleService = roleService;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public PurviewService getPurviewService() {
		return purviewService;
	}
	public void setPurviewService(PurviewService purviewService) {
		this.purviewService = purviewService;
	}
	public Map<String, Object> getDataMap() {
		return dataMap;
	}
	public void setDataMap(Map<String, Object> dataMap) {
		this.dataMap = dataMap;
	}
	public String getOrg_Abbreviation() {
		return org_Abbreviation;
	}
	public void setOrg_Abbreviation(String org_Abbreviation) {
		this.org_Abbreviation = org_Abbreviation;
	}
	public SearchOrg getSearchOrginfo() {
		return searchOrginfo;
	}
	public void setSearchOrginfo(SearchOrg searchOrginfo) {
		this.searchOrginfo = searchOrginfo;
	}
	public String getOrgname() {
		return orgname;
	}
	public void setOrgname(String orgname) {
		this.orgname = orgname;
	}
}
