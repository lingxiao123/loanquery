package com.mcjs.service.impl;

import java.util.List;

import com.mcjs.dao.StateFundDao;
import com.mcjs.entity.StateFund;
import com.mcjs.service.StateFundService;
import com.mcjs.tool.PageTool;

public class StateFundServiceImpl implements StateFundService {
	private StateFundDao stateFundDao=null;
	public void addStateFund(StateFund stateFund) {
		this.stateFundDao.addStateFund(stateFund);
	}
	public List<StateFund> findStateFunds(PageTool tool) {
		return this.stateFundDao.findStateFunds(tool);
	}
	public int getStateFund() {		
		return this.stateFundDao.getStateFund();
	}
	public StateFundDao getStateFundDao() {
		return stateFundDao;
	}
	public void setStateFundDao(StateFundDao stateFundDao) {
		this.stateFundDao = stateFundDao;
	}
	public int getStateFund(String where) {
		return this.stateFundDao.getStateFund(where);
	}
	public List<StateFund> findStateFunds(PageTool tool, String where) {
		return this.stateFundDao.findStateFunds(tool, where);
	}
	public List<StateFund> findsStateFund(String where) {
		return this.stateFundDao.findsStateFund(where);
	}
	
}
