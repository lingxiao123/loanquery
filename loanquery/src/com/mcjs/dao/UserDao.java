package com.mcjs.dao;

import java.util.List;

import com.mcjs.entity.User;
import com.mcjs.tool.PageTool;

public interface UserDao {
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
	public int getUser(String where);
	public List<User> findUser(PageTool tool,String where);
}
