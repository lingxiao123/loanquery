package com.mcjs.service.impl;

import java.util.List;

import com.mcjs.dao.RoleDao;
import com.mcjs.entity.Role;
import com.mcjs.service.RoleService;
import com.mcjs.tool.PageTool;

public class RoleServiceImpl implements RoleService {
	private RoleDao roleDao=null;
	public List<Role> findRole(PageTool tool) {
		return this.roleDao.findRole(tool);
	}
	public void addRole(Role role) {
		this.roleDao.addRole(role);
	}
	public RoleDao getRoleDao() {
		return roleDao;
	}
	public void setRoleDao(RoleDao roleDao) {
		this.roleDao = roleDao;
	}
	public int getRole() {
		return this.roleDao.getRole();
	}
	public List<Role> findRole() {
		return this.roleDao.findRole();
	}
	public List<Role> findRoles(int where) {
		return this.roleDao.findRoles(where);
	}
	public List<Role> findRoles(String where) {
		return this.roleDao.findRoles(where);
	}
	
	public void updateRole(Role role) {
		this.roleDao.updateRole(role);
	}
	public void deleteRole(int id) {
		this.roleDao.deleteRole(id);
	}
}
