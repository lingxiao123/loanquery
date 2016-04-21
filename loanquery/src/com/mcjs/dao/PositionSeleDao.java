package com.mcjs.dao;

import java.util.List;

import com.mcjs.entity.PositionSele;
import com.mcjs.tool.PageTool;

public interface PositionSeleDao {
	public void addPositionSele(PositionSele positionSele);
	public int getPositionSele();
	public List<PositionSele> findpPositionSeles(PageTool tool);
	public int getPositionSele(String where);
	public List<PositionSele> findpPositionSeles(PageTool tool,String where);
	public List<PositionSele> findPositionSele(String where);
}
