package com.mcjs.service.impl;

import java.util.List;

import com.mcjs.dao.BusinessGatherDao;
import com.mcjs.entity.BusinessGather;
import com.mcjs.service.BusinessGatherService;
import com.mcjs.tool.PageTool;

public class BusinessGatherServiceImpl implements BusinessGatherService {
	private BusinessGatherDao businessGatherDao=null;
	public void addBusinessGather(BusinessGather businessGather) {
		this.businessGatherDao.addBusinessGather(businessGather);
	}
	public List<BusinessGather> findBusinessGathers(PageTool tool) {
		return this.businessGatherDao.findBusinessGathers(tool);
	}
	public int getBusinessGather() {
		return this.businessGatherDao.getBusinessGather();
		
	}
	
	public BusinessGatherDao getBusinessGatherDao() {
		return businessGatherDao;
	}
	public void setBusinessGatherDao(BusinessGatherDao businessGatherDao) {
		this.businessGatherDao = businessGatherDao;
	}
	public List<BusinessGather> findBusinessGathers(PageTool tool, String where) {
		return this.businessGatherDao.findBusinessGathers(tool, where);
	}
	public int getBusinessGather(String where) {
		return this.businessGatherDao.getBusinessGather(where);
	}
	public List<BusinessGather> findbBusinessGathers(String where) {
		return this.businessGatherDao.findbBusinessGathers(where);
	}
}
