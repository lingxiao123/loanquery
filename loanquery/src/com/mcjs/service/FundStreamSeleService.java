package com.mcjs.service;

import java.util.List;

import com.mcjs.entity.FundStreamSele;
import com.mcjs.tool.PageTool;

public interface FundStreamSeleService {
	public void addFundStreamSele(FundStreamSele fundStreamSele);
	public int getFundStreamSele();
	public List<FundStreamSele> findFundStreamSeles(PageTool tool);
	public int getFundStreamSele(String where);
	public List<FundStreamSele> findFundStreamSeles(PageTool tool,String where);
	public List<FundStreamSele> findFundStreamSele(String where);
}
