package com.mcjs.dao;

import java.util.List;

import com.mcjs.entity.PositionGather;
import com.mcjs.tool.PageTool;

public interface PositionGatherDao {
	public void addPositionGather(PositionGather positionGather);
	public List<PositionGather> findPositionGather(PageTool tool);
	public int getPositionGather();
	public int getPositionGather(String where);
	public List<PositionGather> findPositionGather(PageTool tool,String where);
	public List<PositionGather> findPositionGather(String where);
}
