package com.mcjs.service.impl;

import java.util.List;

import com.mcjs.dao.CustomFundDao;
import com.mcjs.entity.CustomFund;
import com.mcjs.service.CustomFundService;
import com.mcjs.tool.PageTool;

public class CustomFundServiceImpl implements CustomFundService {
	private CustomFundDao customFundDao=null;
	public void addCustomFund(CustomFund customFund) {
		this.customFundDao.addCustomFund(customFund);
	}

	public void updateCustomFund(CustomFund customFund) {
		this.customFundDao.updateCustomFund(customFund);
	}
	
	public List<CustomFund> getCustomFundById(String id) {
		return this.customFundDao.getCustomFundById(id);
	}	
	
	public CustomFundDao getCustomFundDao() {
		return customFundDao;
	}
	public void setCustomFundDao(CustomFundDao customFundDao) {
		this.customFundDao = customFundDao;
	}

	public int getCustomFund() {
		return this.customFundDao.getCustomFund();
	}
	public List<CustomFund> findCustomFunds(PageTool tool) {
		return this.customFundDao.findCustomFunds(tool);
	}
	public int getCustomFund(String where) {
		return this.customFundDao.getCustomFund(where);
	}
	public List<CustomFund> findCustomFunds(PageTool tool, String where) {
		return this.customFundDao.findCustomFunds(tool, where);
	}

	public void deleteCustomFund() {
		this.customFundDao.deleteCustomFund();
	}

	public List<CustomFund> findCustomFund(String where) {
		return this.customFundDao.findCustomFund(where);
	}
}
