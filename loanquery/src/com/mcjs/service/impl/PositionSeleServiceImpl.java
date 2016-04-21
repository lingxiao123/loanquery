package com.mcjs.service.impl;

import java.util.List;

import com.mcjs.dao.PositionSeleDao;
import com.mcjs.entity.PositionSele;
import com.mcjs.service.PositionSeleService;
import com.mcjs.tool.PageTool;

public class PositionSeleServiceImpl implements PositionSeleService {
	private PositionSeleDao positionSeleDao=null;
	public void addPositionSele(PositionSele positionSele) {
		this.positionSeleDao.addPositionSele(positionSele);
	}
	public int getPositionSele() {
		return this.positionSeleDao.getPositionSele();
	}
	public List<PositionSele> findpPositionSeles(PageTool tool) {
		return this.positionSeleDao.findpPositionSeles(tool);
	}
	public PositionSeleDao getPositionSeleDao() {
		return positionSeleDao;
	}
	public void setPositionSeleDao(PositionSeleDao positionSeleDao) {
		this.positionSeleDao = positionSeleDao;
	}
	public int getPositionSele(String where) {
		return this.positionSeleDao.getPositionSele(where);
	}
	public List<PositionSele> findpPositionSeles(PageTool tool, String where) {
		return this.positionSeleDao.findpPositionSeles(tool, where);
	}
	public List<PositionSele> findPositionSele(String where) {
		return this.positionSeleDao.findPositionSele(where);
	}
}
