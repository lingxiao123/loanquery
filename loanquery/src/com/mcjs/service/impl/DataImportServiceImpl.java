package com.mcjs.service.impl;

import java.util.List;

import com.mcjs.dao.DataImportDao;
import com.mcjs.entity.ImportData;
import com.mcjs.service.DataImportService;
import com.mcjs.tool.PageTool;

public class DataImportServiceImpl implements DataImportService {
	private DataImportDao dataImportDao=null;
	public List<ImportData> findDatas(PageTool tool) {
		return this.dataImportDao.findDatas(tool);
	}
	public int getDataImport() {
		return this.dataImportDao.getDataImport();
	}
	public void addData(ImportData importData) {
		this.dataImportDao.addData(importData);
	}
	public void deleteData(int id) {
		this.dataImportDao.deleteData(id);
	}	
	public DataImportDao getDataImportDao() {
		return dataImportDao;
	}
	public void setDataImportDao(DataImportDao dataImportDao) {
		this.dataImportDao = dataImportDao;
	}
}
