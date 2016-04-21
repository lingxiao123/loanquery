package com.mcjs.service;

import java.util.List;

import com.mcjs.entity.OrgInfo;
import com.mcjs.entity.PostData;
import com.mcjs.tool.PageTool;

public interface OrgInfoService {
	public List<OrgInfo> findOrgInfo(PageTool tool);
	public int getOrgInfo();
	public void addOrgInfo(OrgInfo orgInfo);
	public void deleteOrgInfo(int id);
	public List<OrgInfo> findOrgInfo(PageTool tool,String where);
	public int getOrgInfo(String where);
	public List<OrgInfo> findOrginfo(int id);
	public void updateOrginfo(OrgInfo orgInfo);
	public List<OrgInfo> findOrgInfo();
	public List<OrgInfo> findOrginfo(String where);
	public List<PostData> finOrginfos(String keyword);
}
