package com.mcjs.dao;

import java.util.List;

import com.mcjs.entity.StateFund;
import com.mcjs.tool.PageTool;

public interface StateFundDao {
	public  void addStateFund(StateFund stateFund);
	public List<StateFund> findStateFunds(PageTool tool);
	public int getStateFund();
	public int getStateFund(String where);
	public List<StateFund> findStateFunds(PageTool tool,String where);
	public List<StateFund> findsStateFund(String where);
}
