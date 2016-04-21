package com.mcjs.service;

import java.util.List;

import com.mcjs.entity.BusinessGather;
import com.mcjs.tool.PageTool;

public interface BusinessGatherService {
	public void addBusinessGather(BusinessGather businessGather);
	public List<BusinessGather> findBusinessGathers(PageTool tool);
	public int getBusinessGather();
	public List<BusinessGather> findBusinessGathers(PageTool tool,String where);
	public int getBusinessGather(String where);
	public List<BusinessGather> findbBusinessGathers(String where);
}
