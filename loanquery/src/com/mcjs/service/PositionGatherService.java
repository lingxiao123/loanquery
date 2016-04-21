package com.mcjs.service;

import java.util.List;

import com.mcjs.entity.PositionGather;
import com.mcjs.tool.PageTool;

public interface PositionGatherService  {
	public void addPositionGather(PositionGather positionGather);
	public List<PositionGather> findPositionGather(PageTool tool);
	public int getPositionGather();
	public int getPositionGather(String where);
	public List<PositionGather> findPositionGather(PageTool tool,String where);
	public List<PositionGather> findPositionGather(String where);
}
