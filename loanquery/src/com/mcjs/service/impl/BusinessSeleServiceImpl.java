package com.mcjs.service.impl;

import java.util.List;

import com.mcjs.dao.BusinessSeleDao;
import com.mcjs.entity.BusinessSele;
import com.mcjs.service.BusinessSeleService;
import com.mcjs.tool.PageTool;

public class BusinessSeleServiceImpl implements BusinessSeleService {
	private BusinessSeleDao businessSeleDao=null;
	public void addBusinessSele(BusinessSele businessSele) {
		this.businessSeleDao.addBusinessSele(businessSele);
	}
	public BusinessSeleDao getBusinessSeleDao() {
		return businessSeleDao;
	}
	public void setBusinessSeleDao(BusinessSeleDao businessSeleDao) {
		this.businessSeleDao = businessSeleDao;
	}
	public List<BusinessSele> findbusBusinessSeles(PageTool tool) {
		return this.businessSeleDao.findbusBusinessSeles(tool);
	}
	public int getBusinessSele() {
		return this.businessSeleDao.getBusinessSele();
	}
	public List<BusinessSele> findbusBusinessSeles(PageTool tool, String where) {
		return this.businessSeleDao.findbusBusinessSeles(tool, where);
	}
	public int getBusinessSele(String where) {		
		return this.businessSeleDao.getBusinessSele(where);
	}
	public List<BusinessSele> findfBusinessSele(String where) {
		return this.businessSeleDao.findfBusinessSele(where);
	}	
}
