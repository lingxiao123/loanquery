package com.mcjs.service.impl;

import java.util.List;

import com.mcjs.dao.PositionGatherDao;
import com.mcjs.entity.PositionGather;
import com.mcjs.service.PositionGatherService;
import com.mcjs.tool.PageTool;

public class PositionGatherServiceImpl implements PositionGatherService {
	private PositionGatherDao positionGatherDao=null;

	public void addPositionGather(PositionGather positionGather) {
		this.positionGatherDao.addPositionGather(positionGather);
	}
	public List<PositionGather> findPositionGather(PageTool tool) {
		return this.positionGatherDao.findPositionGather(tool);
	}
	public int getPositionGather() {
		return this.positionGatherDao.getPositionGather();
	}
	
	public PositionGatherDao getPositionGatherDao() {
		return positionGatherDao;
	}

	public void setPositionGatherDao(PositionGatherDao positionGatherDao) {
		this.positionGatherDao = positionGatherDao;
	}
	public int getPositionGather(String where) {
		return this.positionGatherDao.getPositionGather(where);
	}
	public List<PositionGather> findPositionGather(PageTool tool, String where) {
		return this.positionGatherDao.findPositionGather(tool, where);
	}
	public List<PositionGather> findPositionGather(String where) {
		return this.positionGatherDao.findPositionGather(where);
	}
}
