package com.mcjs.service;

import java.util.List;

import com.mcjs.entity.Role;
import com.mcjs.tool.PageTool;

public interface RoleService {
	public List<Role> findRole(PageTool tool);
	public int getRole();
	public void addRole(Role role);
	public List<Role> findRole();
	public List<Role> findRoles(int where);
	public List<Role> findRoles(String where);
	public void updateRole(Role role);
	public void deleteRole(int id);
}
