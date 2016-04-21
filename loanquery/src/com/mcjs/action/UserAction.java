package com.mcjs.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.mcjs.service.PurviewService;
import com.mcjs.service.RoleService;
import com.mcjs.service.UserService;
import com.mcjs.tool.PageTool;
import com.mcjs.util.MD5Util;
import com.mcjs.entity.Role;
import com.mcjs.entity.RoleAuthorize;
import com.mcjs.entity.User;
import com.opensymphony.xwork2.ActionContext;
public class UserAction {
	private User user=null;
	private UserService userService=null;
	private RoleService roleService=null;
	private PurviewService purviewService=null;
	PageTool tool=new PageTool();
	private int Page=1;
	private int id=0;
	private int status=0;
	private Map<String,Object> dataMap;
	private String name=null;
	public String findUsers(){
		try {
			ActionContext ac = ActionContext.getContext();
			if (ac.getSession().get("username").toString()!=null&&ac.getSession().get("username").toString()!="") {
				if (ac.getSession().get("username").toString().trim().contains("admin")) {
					tool.setTotalCount(this.userService.getUser());
					tool.setTotalPage((tool.getTotalCount()%tool.getPageSize()==0)?(tool.getTotalCount() / tool.getPageSize()):(tool.getTotalCount() / tool.getPageSize() + 1));
					/**
					 * 当前页码
					 */
					tool.setCurrPage(Page);
					ac.getSession().put("Page", Page);
					ac.getSession().put("TotalCount", tool.getTotalCount());
					ac.getSession().put("PageCount",tool.getTotalPage());
					List<User> userList = this.userService.findUser(tool);
					if (userList.size()>0) {
						for (int i = 0; i < userList.size(); i++) {
							List<Role> list=this.roleService.findRoles(userList.get(i).getUser_role());
							if (list.size()>0) {
								userList.get(i).setUser_roleName(list.get(0).getRole_roleName());
							}
						}
					}
					ac.getSession().put("userList", userList);
				}else {
					String where=" and user_name='"+ac.getSession().get("username").toString().trim()+"'";
					tool.setTotalCount(this.userService.getUser(where));
					tool.setTotalPage((tool.getTotalCount()%tool.getPageSize()==0)?(tool.getTotalCount() / tool.getPageSize()):(tool.getTotalCount() / tool.getPageSize() + 1));
					/**
					 * 当前页码
					 */
					tool.setCurrPage(Page);
					ac.getSession().put("Page", Page);
					ac.getSession().put("TotalCount", tool.getTotalCount());
					ac.getSession().put("PageCount",tool.getTotalPage());
					List<User> userList = this.userService.findUser(tool,where);
					if (userList.size()>0) {
						for (int i = 0; i < userList.size(); i++) {
							List<Role> list=this.roleService.findRoles(userList.get(i).getUser_role());
							if (list.size()>0) {
								userList.get(i).setUser_roleName(list.get(0).getRole_roleName());
							}
						}
					}
					ac.getSession().put("userList", userList);
				}
			}
			
			if (!"".equals(ac.getSession().get("user_role"))&&ac.getSession().get("user_role")!=null) {
				String roleid=ac.getSession().get("user_role").toString();
				List<RoleAuthorize> ralist=this.purviewService.findrRoleAuthorizes(Integer.parseInt(roleid));
				ac.getSession().put("ralist", ralist);
			}
			List<Role> list=this.roleService.findRole();
			ac.getSession().put("list", list);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "findAll_success";
	}
	public String addUser(){
		try {
			this.userService.addUser(user);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "success";
	}
	public String updateUser(){
		try {
			List<User> list=this.userService.findUser(id);
			User user=new User();
			if (list.size()>0) {
				user=list.get(0);
			}
			if (id!=0) {
				user.setUser_id(id);
				user.setUser_status(status);
			}
			this.userService.updateUser(user);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "success";
	}
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public String deleteUser(){
		try {
			this.userService.deleteUser(id);
			dataMap=new HashMap();
			dataMap.put("success",true);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "success";
	}
	public String updateUsers(){
		try {
			System.out.println(user.getUser_passWord());
			System.out.println(MD5Util.convertMD5(MD5Util.convertMD5(user.getUser_passWord())));
			user.setUser_passWord(MD5Util.convertMD5(user.getUser_passWord()));
			this.userService.updateUsers(user);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "success";
	}
	public String loginUser() throws Exception{
		int isok=this.userService.getLoginUser(user);
		ActionContext ac = ActionContext.getContext();
		if(isok==1){
			return "success";
		}else if (isok==0) {
			ac.getSession().put("error", "0");
			return "error";
		}else if(isok==2){
			ac.getSession().put("error", "2");
			return "error";
		}else {
			ac.getSession().put("error", "3");
			return "error";
		}
	}
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public String checkName(){
		try {
			boolean flag=this.userService.getCheckName(name);
			dataMap=new HashMap();
			if (flag) {
				dataMap.put("success",true);
			}else {
				dataMap.put("success",false);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "success";
	}
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public String checkNames(){
		try {
			boolean flag=this.userService.getCheckName(name, id);
			dataMap=new HashMap();
			if (flag) {
				dataMap.put("success",true);
			}else {
				dataMap.put("success",false);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "success";
	}
	
	public String getUsers() throws Exception{
		return "success";
	}
	
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public UserService getUserService() {
		return userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
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
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
}
