package com.mcjs.service.impl;

import java.util.List;

import com.mcjs.dao.FundStreamSeleDao;
import com.mcjs.entity.FundStreamSele;
import com.mcjs.service.FundStreamSeleService;
import com.mcjs.tool.PageTool;

public class FundStreamSeleServiceImpl implements FundStreamSeleService {
	private FundStreamSeleDao fundStreamSeleDao=null;
	public void addFundStreamSele(FundStreamSele fundStreamSele) {
		this.fundStreamSeleDao.addFundStreamSele(fundStreamSele);	
	}
	public FundStreamSeleDao getFundStreamSeleDao() {
		return fundStreamSeleDao;
	}
	public void setFundStreamSeleDao(FundStreamSeleDao fundStreamSeleDao) {
		this.fundStreamSeleDao = fundStreamSeleDao;
	}
	public int getFundStreamSele() {
		return this.fundStreamSeleDao.getFundStreamSele();
	}
	public List<FundStreamSele> findFundStreamSeles(PageTool tool) {
		return this.fundStreamSeleDao.findFundStreamSeles(tool);
	}
	public int getFundStreamSele(String where) {
		return this.fundStreamSeleDao.getFundStreamSele(where);
	}
	public List<FundStreamSele> findFundStreamSeles(PageTool tool, String where) {
		return this.fundStreamSeleDao.findFundStreamSeles(tool, where);
	}
	public List<FundStreamSele> findFundStreamSele(String where) {
		return this.fundStreamSeleDao.findFundStreamSele(where);
	}	
}
