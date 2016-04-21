package com.mcjs.service.impl;

import java.util.List;

import com.mcjs.dao.PurviewDao;
import com.mcjs.entity.Purview;
import com.mcjs.entity.RoleAuthorize;
import com.mcjs.service.PurviewService;
import com.mcjs.tool.PageTool;

public class PurviewServiceImpl implements PurviewService {
	private PurviewDao purviewDao=null;
	public List<Purview> findPurviews(PageTool tool) {
		return this.purviewDao.findPurviews(tool);
	}
	public int getPurview() {
		return this.purviewDao.getPurview();
	}
	public List<Purview> findPurviews(String module, String type) {
		return this.purviewDao.findPurviews(module, type);
	}
	public PurviewDao getPurviewDao() {
		return purviewDao;
	}

	public void setPurviewDao(PurviewDao purviewDao) {
		this.purviewDao = purviewDao;
	}
	public void addPurview(Purview purview) {
		this.purviewDao.addPurview(purview);
	}
	public void addRoleAuthorize(RoleAuthorize roleAuthorize) {
		this.purviewDao.addRoleAuthorize(roleAuthorize);
	}
	public void deleteRoleAuthorize(String roleid) {
		this.purviewDao.deleteRoleAuthorize(roleid);
	}
	public List<RoleAuthorize> findrRoleAuthorizes(int roleid) {
		return this.purviewDao.findrRoleAuthorizes(roleid);
	}
	public void deletePurview(int id) {
		this.purviewDao.deletePurview(id);
	}


}
