package com.mcjs.action;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.mcjs.entity.ImportData;
import com.mcjs.service.DataImportService;
import com.mcjs.tool.PageTool;
import com.opensymphony.xwork2.ActionContext;

public class DataImportAction {
	private ImportData importData=null;
	private DataImportService dataImportService=null;
	PageTool tool=new PageTool();
	private int Page=1;
	private int id=0;
	private Map<String,Object> dataMap;
	public String findData(){
		try {
			ActionContext ac = ActionContext.getContext();
			tool.setTotalCount(this.dataImportService.getDataImport());
			tool.setTotalPage((tool.getTotalCount()%tool.getPageSize()==0)?(tool.getTotalCount() / tool.getPageSize()):(tool.getTotalCount() / tool.getPageSize() + 1));
			/**
			 * 当前页码
			 */
			tool.setCurrPage(Page);
			ac.getSession().put("Page", Page);
			ac.getSession().put("TotalCount", tool.getTotalCount());
			ac.getSession().put("PageCount",tool.getTotalPage());
			List<ImportData> importList = this.dataImportService.findDatas(tool);
			ac.getSession().put("importList", importList);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "findAll_success";
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public String deleteImport() {
		try {
			this.dataImportService.deleteData(id);
			dataMap=new HashMap();
			dataMap.put("success",true);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "success";
	}
	
	
	public ImportData getImportData() {
		return importData;
	}
	public void setImportData(ImportData importData) {
		this.importData = importData;
	}
	public DataImportService getDataImportService() {
		return dataImportService;
	}
	public void setDataImportService(DataImportService dataImportService) {
		this.dataImportService = dataImportService;
	}
	public PageTool getTool() {
		return tool;
	}
	public void setTool(PageTool tool) {
		this.tool = tool;
	}
	public int getPage() {
		return Page;
	}
	public void setPage(int page) {
		Page = page;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Map<String, Object> getDataMap() {
		return dataMap;
	}

	public void setDataMap(Map<String, Object> dataMap) {
		this.dataMap = dataMap;
	}
}
