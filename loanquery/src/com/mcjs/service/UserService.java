package com.mcjs.service;

import java.util.List;

import com.mcjs.entity.User;
import com.mcjs.tool.PageTool;

public interface UserService {
	public List<User> findUser(PageTool tool);
	public int getUser();
	public void addUser(User user);
	public int getLoginUser(User user);
	public List<User> findUser(int id);
	public void updateUser(User user);
	public void deleteUser(int id);
	public void updateUsers(User user);
	public boolean getCheckName(String where);
	public boolean getCheckName(String where,int id);
	public List<User> findUser(PageTool tool,String where);
	public int getUser(String where);
}
