package com.mcjs.dao;

import java.util.List;

import com.mcjs.entity.ImportData;
import com.mcjs.tool.PageTool;

public interface DataImportDao {
	public List<ImportData> findDatas(PageTool tool);
	public int getDataImport();
	public void addData(ImportData importData);
	public void deleteData(int id);
}
