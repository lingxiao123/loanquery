package com.mcjs.service.impl;

import java.util.List;

import com.mcjs.dao.EntrustSeleDao;
import com.mcjs.entity.EntrustSele;
import com.mcjs.service.EntrustSeleService;
import com.mcjs.tool.PageTool;

public class EntrustSeleServiceImpl implements EntrustSeleService {
	private EntrustSeleDao entrustSeleDao=null;
	public void addEntrustSele(EntrustSele entrustSele) {
		this.entrustSeleDao.addEntrustSele(entrustSele);
	}
	public EntrustSeleDao getEntrustSeleDao() {
		return entrustSeleDao;
	}
	public void setEntrustSeleDao(EntrustSeleDao entrustSeleDao) {
		this.entrustSeleDao = entrustSeleDao;
	}
	public List<EntrustSele> findeEntrustSeles(PageTool tool) {
		return this.entrustSeleDao.findeEntrustSeles(tool);
	}
	public int getEntrustSele() {
		return this.entrustSeleDao.getEntrustSele();
	}
	public List<EntrustSele> findeEntrustSeles(PageTool tool, String where) {
		return this.entrustSeleDao.findeEntrustSeles(tool, where);
	}
	public int getEntrustSele(String where) {
		return this.entrustSeleDao.getEntrustSele(where);
	}
	public List<EntrustSele> findEntrustSele(String where) {
		return this.entrustSeleDao.findEntrustSele(where);
	}
	
	
}
