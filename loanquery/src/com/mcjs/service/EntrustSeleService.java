package com.mcjs.service;

import java.util.List;

import com.mcjs.entity.EntrustSele;
import com.mcjs.tool.PageTool;

public interface EntrustSeleService {
	public void addEntrustSele(EntrustSele entrustSele);
	public List<EntrustSele> findeEntrustSeles(PageTool tool);
	public int getEntrustSele();
	public List<EntrustSele> findeEntrustSeles(PageTool tool,String where);
	public int getEntrustSele(String where);
	public List<EntrustSele> findEntrustSele(String where);
}
