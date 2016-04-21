package com.mcjs.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.mcjs.entity.LoanLeader;
import com.mcjs.entity.LoanMember;
import com.mcjs.entity.OrgInfo;
import com.mcjs.entity.Role;
import com.mcjs.entity.User;
import com.mcjs.service.LoanLeaderService;
import com.mcjs.service.LoanMemberService;
import com.mcjs.service.OrgInfoService;
import com.mcjs.service.RoleService;
import com.mcjs.service.UserService;
import com.mcjs.util.MD5Util;
import com.opensymphony.xwork2.ActionContext;

public class LinkAction {
	private RoleService roleService=null;
	private OrgInfoService orgInfoService=null;
	private LoanLeaderService loanLeaderService=null;
	private LoanMemberService loanMemberService=null;
	private UserService userService=null;
	private int id=0;
	public String addRole(){
		return "success";
	}
	public String importData(){
		return "success";
	}
	public String addOrg(){
		return "success";
	}
	public String addLoan(){
		return "success";
	}
	
	public String addData(){
		return "success";
	}
	public String addLoanmember(){
		return "success";
	}
	public String addPurview(){
		return "success";
	}
	public String updateRole(){
		ActionContext ac=ActionContext.getContext();
		List<Role> list=this.roleService.findRoles(id);
		if (list.size()>0) {
			ac.getSession().put("rolename",list.get(0).getRole_roleName());
		}
		ac.getSession().put("role_id", id);
		
		return "success";
	}
	public String updateOrg(){
		ActionContext ac=ActionContext.getContext();
		System.out.println(id);
		List<OrgInfo> list=this.orgInfoService.findOrginfo(id);
		if (list.size()>0) {
			ac.getSession().put("org_id",id);
			ac.getSession().put("org_FullName",list.get(0).getOrg_FullName());
			ac.getSession().put("org_Abbreviation",list.get(0).getOrg_Abbreviation());
			ac.getSession().put("org_PassWord",MD5Util.convertMD5(list.get(0).getOrg_FullName()));
			ac.getSession().put("org_PhoneNumber",list.get(0).getOrg_PhoneNumber());
			ac.getSession().put("org_AddTime",list.get(0).getOrg_AddTime());
			ac.getSession().put("org_RoleId",list.get(0).getOrg_RoleId());
			ac.getSession().put("org_Status",list.get(0).getOrg_Status());
		}
		return "success";
	}
	
	public String linkUpdateLoan(){
		try {
			ActionContext ac=ActionContext.getContext();
			List<LoanLeader> list=this.loanLeaderService.findLoanLeaders(id);
			if (list.size()>0) {
				ac.getSession().put("loan_id", id);
				ac.getSession().put("loan_name", list.get(0).getLoan_name());
				ac.getSession().put("loan_passWord", MD5Util.convertMD5(list.get(0).getLoan_passWord()));
				ac.getSession().put("loan_phoneNumber", list.get(0).getLoan_phoneNumber());
				ac.getSession().put("loan_status", list.get(0).getLoan_status());
				ac.getSession().put("loan_addTime",list.get(0).getLoan_addTime());
				ac.getSession().put("loan_orgId",list.get(0).getLoan_orgId());
				ac.getSession().put("loan_tradAccount",list.get(0).getLoan_tradAccount());
				ac.getSession().put("loan_roleId",list.get(0).getLoan_roleId());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "success";
	}
	
	public String linkUpdateUser(){
		try {
			ActionContext ac=ActionContext.getContext();
			List<User> list=this.userService.findUser(id);
			System.out.println("aa="+MD5Util.convertMD5(MD5Util.convertMD5(list.get(0).getUser_passWord())));
			if (list.size()>0) {
				ac.getSession().put("user_id",list.get(0).getUser_id());
				ac.getSession().put("user_name",list.get(0).getUser_name());
				ac.getSession().put("user_passWord",MD5Util.convertMD5(list.get(0).getUser_passWord()));
				ac.getSession().put("user_role",list.get(0).getUser_role());
				ac.getSession().put("user_status",list.get(0).getUser_status());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "success";
	}
	public String linkUpdateLoanMember(){
		try {
			ActionContext ac=ActionContext.getContext();
			List<LoanMember> list=this.loanMemberService.findLoanMembers(id);
			if (list.size()>0) {
				ac.getSession().put("lm_id",list.get(0).getLm_id());
				ac.getSession().put("lm_name",list.get(0).getLm_name());
				ac.getSession().put("lm_tradAccount",list.get(0).getLm_tradAccount());
				ac.getSession().put("lm_phoneNumber",list.get(0).getLm_phoneNumber());
				ac.getSession().put("lm_status",list.get(0).getLm_status());
				ac.getSession().put("lm_addTime",list.get(0).getLm_addTime());
				ac.getSession().put("lm_loanid",list.get(0).getLm_loanid());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "success";
	}
	public String out(){
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session1 = request.getSession();
		session1.invalidate();
		return "success";
	}
	public String addUsers(){
		try {
			ActionContext ac = ActionContext.getContext();
			List<Role> list=this.roleService.findRole();
			ac.getSession().put("list", list);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "success";
	}
	public RoleService getRoleService() {
		return roleService;
	}
	public void setRoleService(RoleService roleService) {
		this.roleService = roleService;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public OrgInfoService getOrgInfoService() {
		return orgInfoService;
	}
	public void setOrgInfoService(OrgInfoService orgInfoService) {
		this.orgInfoService = orgInfoService;
	}
	public LoanLeaderService getLoanLeaderService() {
		return loanLeaderService;
	}
	public void setLoanLeaderService(LoanLeaderService loanLeaderService) {
		this.loanLeaderService = loanLeaderService;
	}
	public UserService getUserService() {
		return userService;
	}
	public void setUserService(UserService userService) {
		this.userService = userService;
	}
	public LoanMemberService getLoanMemberService() {
		return loanMemberService;
	}
	public void setLoanMemberService(LoanMemberService loanMemberService) {
		this.loanMemberService = loanMemberService;
	}
	
}
