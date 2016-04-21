package com.mcjs.service.impl;

import java.util.List;

import com.mcjs.dao.UserDao;
import com.mcjs.entity.User;
import com.mcjs.service.UserService;
import com.mcjs.tool.PageTool;

public class UserServiceImpl implements UserService {
	private UserDao userDao=null;
	@Override
	public List<User> findUser(PageTool tool) {
		// TODO Auto-generated method stub
		return this.userDao.findUser(tool);
	}
	public UserDao getUserDao() {
		return userDao;
	}
	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}
	public int getUser() {
		return this.userDao.getUser();
	}
	public void addUser(User user) {
		this.userDao.addUser(user);
	}
	public int getLoginUser(User user) {
		return this.userDao.getLoginUser(user);
	}
	public List<User> findUser(int id) {
		return this.userDao.findUser(id);
	}
	public void updateUser(User user) {
		 this.userDao.updateUser(user);
	}
	public void deleteUser(int id) {
		this.userDao.deleteUser(id);
	}
	public void updateUsers(User user) {
		this.userDao.updateUsers(user);
	}
	public boolean getCheckName(String where) {
		return this.userDao.getCheckName(where);
	}
	public List<User> findUser(PageTool tool, String where) {
		return this.userDao.findUser(tool, where);
	}
	public int getUser(String where) {
		return this.userDao.getUser(where);
	}
	public boolean getCheckName(String where, int id) {
		return this.userDao.getCheckName(where, id);
	}
	
}
