package com.mcjs.dao;

import java.util.List;
import com.mcjs.entity.BusinessSele;
import com.mcjs.tool.PageTool;

public interface BusinessSeleDao {
	public void addBusinessSele(BusinessSele businessSele);
	public List<BusinessSele> findbusBusinessSeles(PageTool tool);
	public int getBusinessSele();
	public List<BusinessSele> findbusBusinessSeles(PageTool tool,String where);
	public int getBusinessSele(String where);
	public List<BusinessSele> findfBusinessSele(String where);
}
