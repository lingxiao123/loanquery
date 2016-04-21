package com.mcjs.service;

import java.util.List;

import com.mcjs.entity.Purview;
import com.mcjs.entity.RoleAuthorize;
import com.mcjs.tool.PageTool;

public interface PurviewService {
	public List<Purview> findPurviews(PageTool tool);
	public int getPurview();
	public void addPurview(Purview purview);
	public List<Purview> findPurviews(String module,String type);
	public void addRoleAuthorize(RoleAuthorize roleAuthorize);
	public void deleteRoleAuthorize(String roleid);
	public List<RoleAuthorize> findrRoleAuthorizes(int roleid);
	public void deletePurview(int id);
}
