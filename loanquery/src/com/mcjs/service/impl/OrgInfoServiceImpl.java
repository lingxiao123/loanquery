package com.mcjs.service.impl;

import java.util.List;

import com.mcjs.dao.OrgInfoDao;
import com.mcjs.entity.OrgInfo;
import com.mcjs.entity.PostData;
import com.mcjs.service.OrgInfoService;
import com.mcjs.tool.PageTool;

public class OrgInfoServiceImpl implements OrgInfoService {
	private OrgInfoDao orgInfoDao=null;
	public List<OrgInfo> findOrgInfo(PageTool tool) {
		return this.orgInfoDao.findOrginfo(tool);
	}
	public void addOrgInfo(OrgInfo orgInfo) {
		this.orgInfoDao.addOrgInfo(orgInfo);
	}
	public void deleteOrgInfo(int id) {
		this.orgInfoDao.deleteOrgInfo(id);
	}
	public int getOrgInfo() {
		return this.orgInfoDao.getOrgInfo();
	}
	
	public OrgInfoDao getOrgInfoDao() {
		return orgInfoDao;
	}
	public void setOrgInfoDao(OrgInfoDao orgInfoDao) {
		this.orgInfoDao = orgInfoDao;
	}
	public int getOrgInfo(String where) {
		return this.orgInfoDao.getOrgInfo(where);
	}
	public List<OrgInfo> findOrgInfo(PageTool tool,String where) {
		return this.orgInfoDao.findOrginfo(tool, where);
	}
	public List<OrgInfo> findOrginfo(int id) {
		return this.orgInfoDao.findOrginfo(id);
	}
	public void updateOrginfo(OrgInfo orgInfo) {
		this.orgInfoDao.updateOrginfo(orgInfo);
	}
	public List<OrgInfo> findOrgInfo() {		
		return this.orgInfoDao.findOrginfo();
	}
	public List<OrgInfo> findOrginfo(String where) {
		return this.orgInfoDao.findOrginfo(where);
	}
	public List<PostData> finOrginfos(String keyword) {
		return this.orgInfoDao.finOrginfos(keyword);
	}
}
