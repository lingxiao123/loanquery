package com.mcjs.action;

import java.util.List;

import com.mcjs.entity.Role;
import com.mcjs.service.RoleService;
import com.mcjs.tool.PageTool;
import com.opensymphony.xwork2.ActionContext;

public class RoleAction {
	private Role role=null;
	private RoleService roleService=null;
	private PageTool tool=new PageTool();
	private int Page=1;
	private int id=0;
	public String findRoles(){
		try {
			ActionContext ac = ActionContext.getContext();
			tool.setTotalCount(this.roleService.getRole());
			tool.setTotalPage((tool.getTotalCount()%tool.getPageSize()==0)?(tool.getTotalCount() / tool.getPageSize()):(tool.getTotalCount() / tool.getPageSize() + 1));
			/**
			 * 当前页码
			 */
			tool.setCurrPage(Page);
			ac.getSession().put("Page", Page);
			ac.getSession().put("TotalCount", tool.getTotalCount());
			ac.getSession().put("PageCount",tool.getTotalPage());
			List<Role> roleList = this.roleService.findRole(tool);
			ac.getSession().put("roleList", roleList);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "findAll_success";
	}
	
	public String addRoles() throws Exception{
		this.roleService.addRole(role);
		return "success";
	}
	
	public String updaterole() throws Exception{
		this.roleService.updateRole(role);
		return "success";
	}
	public String deleteRole()throws Exception{
		this.roleService.deleteRole(id);
		return "success";
	}
	public Role getRole() {
		return role;
	}
	public void setRole(Role role) {
		this.role = role;
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

	public PageTool getTool() {
		return tool;
	}

	public void setTool(PageTool tool) {
		this.tool = tool;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
}
