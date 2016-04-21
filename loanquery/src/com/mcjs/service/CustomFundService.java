package com.mcjs.service;

import java.util.List;

import com.mcjs.entity.CustomFund;
import com.mcjs.tool.PageTool;

public interface CustomFundService {
	public void addCustomFund(CustomFund customFund);
	public void updateCustomFund(CustomFund customFund);
	public List<CustomFund> getCustomFundById(String id);
	public int getCustomFund();
	public List<CustomFund> findCustomFunds(PageTool tool);
	public int getCustomFund(String where);
	public List<CustomFund> findCustomFunds(PageTool tool,String where);
	public void deleteCustomFund();
	public List<CustomFund> findCustomFund(String where);
}
