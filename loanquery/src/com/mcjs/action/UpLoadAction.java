package com.mcjs.action;
import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;

import com.csvreader.CsvReader;
import com.mcjs.entity.BusinessGather;
import com.mcjs.entity.BusinessSele;
import com.mcjs.entity.CustomFund;
import com.mcjs.entity.EntrustSele;
import com.mcjs.entity.FundStreamSele;
import com.mcjs.entity.ImportData;
import com.mcjs.entity.PositionGather;
import com.mcjs.entity.PositionSele;
import com.mcjs.entity.StateFund;
import com.mcjs.service.BusinessGatherService;
import com.mcjs.service.BusinessSeleService;
import com.mcjs.service.CustomFundService;
import com.mcjs.service.DataImportService;
import com.mcjs.service.EntrustSeleService;
import com.mcjs.service.FundStreamSeleService;
import com.mcjs.service.PositionGatherService;
import com.mcjs.service.PositionSeleService;
import com.mcjs.service.StateFundService;
import com.mcjs.util.LogNo;
import com.opensymphony.xwork2.ActionSupport;

@SuppressWarnings("serial")
public class UpLoadAction extends ActionSupport {
	private File fileField;  
	private String fileFieldFileName;
	private StateFundService stateFundService=null;
	private DataImportService dataImportService=null;
	private com.mcjs.service.PositionGatherService positionGatherService=null;
	private BusinessGatherService businessGatherService=null;
	private PositionSeleService positionSeleService=null;
	private BusinessSeleService businessSeleService=null;
	private EntrustSeleService entrustSeleService=null;
	private FundStreamSeleService fundStreamSeleService=null;
	private CustomFundService customFundService=null;
	SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");//设置日期格式
	String time=df.format(new Date());// new Date()为获取当前系统时间
	public String importData(){
		try {
			String directory = "/upload";    
	        String targetDirectory = ServletActionContext.getServletContext().getRealPath(directory);    
	        //生成上传的文件对象    
	        File target = new File(targetDirectory,fileFieldFileName);    
	        //如果文件已经存在，则删除原有文件    
	        if(target.exists()){    
	            target.delete();    
	        }    
	        //复制file对象，实现上传    
	        try {    
	            FileUtils.copyFile(fileField, target);    
	        } catch (IOException e) {    
	            e.printStackTrace();    
	        }
	        loadInfo();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "success";
	}
	
	public void loadInfo(){ 
		try {
			String directory = "/upload"; 
			String targetDirectory = ServletActionContext.getServletContext().getRealPath(directory); 
			File target = new File(targetDirectory,fileFieldFileName);
			
			String fileType = fileFieldFileName.substring(fileFieldFileName.indexOf(".") + 1,
					fileFieldFileName.length());// fileType
			String fileName = fileFieldFileName.substring(0, fileFieldFileName.indexOf("."));// fileName
			String path=target.getAbsolutePath();
			String str="";
			if ("csv".equals(fileType)) {
				if (fileName.contains("客户资金状况表")) {
					try {
						str=readCsvStateFunds(path);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
				if (fileName.contains("客户持仓汇总表")) {
					try {
						str=readCsvPositionGathers(path);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
				if (fileName.contains("客户成交汇总表")) {
					try {
						str=readCsvBusinessGathers(path);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
				if (fileName.contains("客户持仓查询")) {
					try {
						str=readCsvPositionSeles(path);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
				if (fileName.contains("客户成交查询")) {
					try {
						str=readCsvBusinessSeles(path);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
				if (fileName.contains("客户委托单查询")) {
					try {
						str=readCsvEntrustSeles(path);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
				if (fileName.contains("客户资金流水查询")) {
					try {
						str=readCsvFundStreamSeles(path);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
				if (fileName.contains("客户资金查询")) {
					try {
						this.customFundService.deleteCustomFund();
						str=readCsvCustomFunds(path);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * 导入客户资金状况表
	 * @param path
	 * @return
	 * @throws Exception
	 */
	public String readCsvStateFunds(String path) throws Exception{
		CsvReader reader = new CsvReader(path, ',',
				Charset.forName("GBK"));//
		reader.readHeaders();
		String[] headers = reader.getHeaders();		
		List<Object[]> list = new ArrayList<Object[]>();
		while (reader.readRecord()) {
			list.add(reader.getValues());
		}
		Object[][] datas = new String[list.size()][];
		for (int i = 0; i < list.size(); i++) {
			datas[i] = list.get(i);
		}
		/*
		 * 以下输出
		 */

		for (int i = 0; i < headers.length; i++) {
			System.out.print(headers[i] + "\t");
		}
		LogNo lg=new LogNo();
		String batNo=lg.getNumber();
		for (int i = 0; i < datas.length; i++) {
			Object[] data = datas[i]; // 取出一组数据
			StateFund sf=new StateFund();
			System.out.println("版本规格=============" + headers.length);
			for (int j = 1; j < data.length; j++) {
				Object cell = data[j];
 				System.out.print(cell + "\t");
 				cell.toString().replace("\t","");
 				switch (j) {
				case 1:
					sf.setAccountsTime(cell.toString().replace("\t",""));
					break;
				case 2:
					sf.setAgencyName(cell.toString().replace("\t",""));
					break;
				case 3:
					sf.setOrgName(cell.toString().replace("\t",""));
					break;
				case 4:
					sf.setTradeAccount(cell.toString().replace("\t",""));
					break;
				case 5:
					sf.setCustomerName(cell.toString().replace("\t",""));
					break;
				case 6:
					sf.setPrimeBenefit(cell.toString().replace("\t",""));
					break;
				case 7:
					sf.setInandoutMoney(cell.toString().replace("\t",""));
					break;
				case 8:
					sf.setFlatProfit(cell.toString().replace("\t",""));
					break;
				case 9:
					sf.setPositionProfit(cell.toString().replace("\t",""));
					break;
				case 10:
					sf.setProfitTotal(cell.toString().replace("\t",""));
					break;
				case 11:
					sf.setExchangeFee(cell.toString().replace("\t",""));
					break;
				case 12:
					sf.setMemberFee(cell.toString().replace("\t",""));
					break;
				case 13:
					sf.setSusPoundage(cell.toString().replace("\t",""));
					break;
				case 14:
					sf.setDemurrage(cell.toString().replace("\t",""));
					break;
				case 15:
					sf.setOccupyBail(cell.toString().replace("\t",""));
					break;
				case 16:
					sf.setDealBail(cell.toString().replace("\t",""));
					break;
				case 17:
					sf.setDealPoundage(cell.toString().replace("\t",""));
					break;
				case 18:
					sf.setDealLoan(cell.toString().replace("\t",""));
					break;
				case 19:
					sf.setDealPenalty(cell.toString().replace("\t",""));
					break;
				case 20:
					sf.setTaxSettlement(cell.toString().replace("\t",""));
					break;
				case 21:
					sf.setLastBenefit(cell.toString().replace("\t",""));
					break;
				case 22:
					sf.setRiskRate(cell.toString().replace("\t",""));
					break;
				default:
					break;
				}
			}
			sf.setBatNumber(batNo);
			this.stateFundService.addStateFund(sf);
		}
		ImportData im=new ImportData();
		im.setIm_imortNumber(batNo);
		im.setIm_type("客户资金状况表");
		im.setIm_addTime(time);
		im.setIm_status(1);
		this.dataImportService.addData(im);
		return "success";
	}
	/**
	 * 导入客户持仓汇总表
	 * @param path
	 * @return
	 * @throws Exception
	 */
	public String readCsvPositionGathers(String path) throws Exception{
		CsvReader reader = new CsvReader(path, ',',
				Charset.forName("GBK"));//
		reader.readHeaders();
		String[] headers = reader.getHeaders();		
		List<Object[]> list = new ArrayList<Object[]>();
		while (reader.readRecord()) {
			list.add(reader.getValues());
		}
		Object[][] datas = new String[list.size()][];
		for (int i = 0; i < list.size(); i++) {
			datas[i] = list.get(i);
		}
		/*
		 * 以下输出
		 */

		for (int i = 0; i < headers.length; i++) {
			System.out.print(headers[i] + "\t");
		}
		LogNo lg=new LogNo();
		String batNo=lg.getNumber();
		for (int i = 0; i < datas.length; i++) {
			Object[] data = datas[i]; // 取出一组数据
			PositionGather pg=new PositionGather();
			System.out.println("版本规格=============" + headers.length);
			for (int j = 1; j < data.length; j++) {
				Object cell = data[j];
 				System.out.print(cell + "\t");
 				cell.toString().replace("\t","");
 				switch (j) {
				case 1:
					pg.setAccountsTime(cell.toString().replace("\t",""));
					break;
				case 2:
					pg.setAgencyName(cell.toString().replace("\t",""));
					break;
				case 3:
					pg.setOrgName(cell.toString().replace("\t",""));
					break;
				case 4:
					pg.setTradeAccount(cell.toString().replace("\t",""));
					break;
				case 5:
					pg.setCustomerName(cell.toString().replace("\t",""));
					break;
				case 6:
					pg.setCommodity(cell.toString().replace("\t",""));
					break;
				case 7:
					pg.setBuyPositionNum(cell.toString().replace("\t",""));
					break;
				case 8:
					pg.setSellPositionNum(cell.toString().replace("\t",""));
					break;
				case 9:
					pg.setPositionTotal(cell.toString().replace("\t",""));
					break;
				case 10:
					pg.setDemurrage(cell.toString().replace("\t",""));
					break;
				case 11:
					pg.setMarketPoundage(cell.toString().replace("\t",""));
					break;
				case 12:
					pg.setDemurrage(cell.toString().replace("\t",""));
					break;
				case 13:
					pg.setOccupyBail(cell.toString().replace("\t",""));
					break;
				default:
					break;
				}
			}
			pg.setBatNumber(batNo);
			this.positionGatherService.addPositionGather(pg);
		}
		ImportData im=new ImportData();
		im.setIm_imortNumber(batNo);
		im.setIm_type("客户持仓汇总表");
		im.setIm_addTime(time);
		im.setIm_status(1);
		this.dataImportService.addData(im);
		return "";
	}
	public String readCsvBusinessGathers(String path) throws Exception{
		CsvReader reader = new CsvReader(path, ',',
				Charset.forName("GBK"));//
		reader.readHeaders();
		String[] headers = reader.getHeaders();		
		List<Object[]> list = new ArrayList<Object[]>();
		while (reader.readRecord()) {
			list.add(reader.getValues());
		}
		Object[][] datas = new String[list.size()][];
		for (int i = 0; i < list.size(); i++) {
			datas[i] = list.get(i);
		}
		/*
		 * 以下输出
		 */

		for (int i = 0; i < headers.length; i++) {
			System.out.print(headers[i] + "\t");
		}
		LogNo lg=new LogNo();
		String batNo=lg.getNumber();
		for (int i = 0; i < datas.length; i++) {
			Object[] data = datas[i]; // 取出一组数据
			BusinessGather bg=new BusinessGather();
			System.out.println("版本规格=============" + headers.length);
			for (int j = 1; j < data.length; j++) {
				Object cell = data[j];
 				System.out.print(cell + "\t");
 				cell.toString().replace("\t","");
 				switch (j) {
				case 1:
					bg.setAccountsTime(cell.toString().replace("\t",""));
					break;
				case 2:
					bg.setAgencyName(cell.toString().replace("\t",""));
					break;
				case 3:
					bg.setOrgName(cell.toString().replace("\t",""));
					break;
				case 4:
					bg.setTradeAccount(cell.toString().replace("\t",""));
					break;
				case 5:
					bg.setCustomerName(cell.toString().replace("\t",""));
					break;
				case 6:
					bg.setCommodity(cell.toString().replace("\t",""));
					break;
				case 7:
					bg.setTurnoverNum(cell.toString().replace("\t",""));
					break;
				case 8:
					bg.setTurnoverMoney(cell.toString().replace("\t",""));
					break;
				case 9:
					bg.setFlatProfit(cell.toString().replace("\t",""));
					break;
				case 10:
					bg.setPositionProfit(cell.toString().replace("\t",""));
					break;
				case 11:
					bg.setProfitTotal(cell.toString().replace("\t",""));
					break;
				case 12:
					bg.setExchangePoundage(cell.toString().replace("\t",""));
					break;
				case 13:
					bg.setSynthesizePoundage(cell.toString().replace("\t",""));
					break;
				case 14:
					bg.setTakeinPoundage(cell.toString().replace("\t",""));
					break;
				default:
					break;
				}
			}
			bg.setBatNumber(batNo);
			this.businessGatherService.addBusinessGather(bg);
		}
		ImportData im=new ImportData();
		im.setIm_imortNumber(batNo);
		im.setIm_type("客户成交汇总表");
		im.setIm_addTime(time);
		im.setIm_status(1);
		this.dataImportService.addData(im);
		return "";
	}
	public String readCsvPositionSeles(String path) throws Exception{
		CsvReader reader = new CsvReader(path, ',',
				Charset.forName("GBK"));//
		reader.readHeaders();
		String[] headers = reader.getHeaders();		
		List<Object[]> list = new ArrayList<Object[]>();
		while (reader.readRecord()) {
			list.add(reader.getValues());
		}
		Object[][] datas = new String[list.size()][];
		for (int i = 0; i < list.size(); i++) {
			datas[i] = list.get(i);
		}
		/*
		 * 以下输出
		 */

		for (int i = 0; i < headers.length; i++) {
			System.out.print(headers[i] + "\t");
		}
		LogNo lg=new LogNo();
		String batNo=lg.getNumber();
		for (int i = 0; i < datas.length; i++) {
			Object[] data = datas[i]; // 取出一组数据
			PositionSele ps=new PositionSele();
			System.out.println("版本规格=============" + headers.length);
			for (int j = 1; j < data.length; j++) {
				Object cell = data[j];
 				System.out.print(cell + "\t");
 				cell.toString().replace("\t","");
 				switch (j) {
				case 1:
					ps.setBalanceDate(cell.toString().replace("\t",""));
					break;
				case 2:
					ps.setTradeAccount(cell.toString().replace("\t",""));
					break;
				case 3:
					ps.setCustomerName(cell.toString().replace("\t",""));
					break;
				case 4:
					ps.setPositionNumbers(cell.toString().replace("\t",""));
					break;
				case 5:
					ps.setBuildTime(cell.toString().replace("\t",""));
					break;
				case 6:
					ps.setCommodity(cell.toString().replace("\t",""));
					break;
				case 7:
					ps.setShopSign(cell.toString().replace("\t",""));
					break;
				case 8:
					ps.setPositionAmount(cell.toString().replace("\t",""));
					break;
				case 9:
					ps.setRunningPrice(cell.toString().replace("\t",""));
					break;
				case 10:
					ps.setPositionPrice(cell.toString().replace("\t",""));
					break;
				case 11:
					ps.setFlatPrice(cell.toString().replace("\t",""));
					break;
				case 12:
					ps.setFloatProfit(cell.toString().replace("\t",""));
					break;
				case 13:
					ps.setOccupyBail(cell.toString().replace("\t",""));
					break;
				case 14:
					ps.setPoundage(cell.toString().replace("\t",""));
					break;
				case 15:
					ps.setDemurrage(cell.toString().replace("\t",""));
					break;
				case 16:
					ps.setOperator(cell.toString().replace("\t",""));
					break;
				default:
					break;
				}
			}
			ps.setBatNumber(batNo);
			this.positionSeleService.addPositionSele(ps);
		}
		ImportData im=new ImportData();
		im.setIm_imortNumber(batNo);
		im.setIm_type("客户持仓查询");
		im.setIm_addTime(time);
		im.setIm_status(1);
		this.dataImportService.addData(im);
		return "";
	}
	
	public String readCsvBusinessSeles(String path) throws Exception{
		CsvReader reader = new CsvReader(path, ',',
				Charset.forName("GBK"));//
		reader.readHeaders();
		String[] headers = reader.getHeaders();		
		List<Object[]> list = new ArrayList<Object[]>();
		while (reader.readRecord()) {
			list.add(reader.getValues());
		}
		Object[][] datas = new String[list.size()][];
		for (int i = 0; i < list.size(); i++) {
			datas[i] = list.get(i);
		}
		/*
		 * 以下输出
		 */

		for (int i = 0; i < headers.length; i++) {
			System.out.print(headers[i] + "\t");
		}
		LogNo lg=new LogNo();
		String batNo=lg.getNumber();
		for (int i = 0; i < datas.length; i++) {
			Object[] data = datas[i]; // 取出一组数据
			BusinessSele bs=new BusinessSele();
			System.out.println("版本规格=============" + headers.length);
			for (int j = 1; j < data.length; j++) {
				Object cell = data[j];
 				System.out.print(cell + "\t");
 				cell.toString().replace("\t","");
 				switch (j) {
				case 1:
					bs.setBalanceDate(cell.toString().replace("\t",""));
					break;
				case 2:
					bs.setAgencyName(cell.toString().replace("\t",""));
					break;
				case 3:
					bs.setOrgName(cell.toString().replace("\t",""));
					break;
				case 4:
					bs.setTradeAccount(cell.toString().replace("\t",""));
					break;
				case 5:
					bs.setCustomerName(cell.toString().replace("\t",""));
					break;
				case 6:
					bs.setBuildFlatPrice(cell.toString().replace("\t",""));
					break;
				case 7:
					bs.setTurnoverNumbers(cell.toString().replace("\t",""));
					break;
				case 8:
					bs.setEntrustNumbers(cell.toString().replace("\t",""));
					break;
				case 9:
					bs.setPositionNumbers(cell.toString().replace("\t",""));
					break;
				case 10:
					bs.setTurnoverTime(cell.toString().replace("\t",""));
					break;
				case 11:
					bs.setCommodity(cell.toString().replace("\t",""));
					break;
				case 12:
					bs.setTurnoverAmount(cell.toString());
					break;
				case 13:
					bs.setTurnoverMoney(cell.toString().replace("\t",""));
					break;
				case 14:
					bs.setShopDirection(cell.toString().replace("\t",""));
					break;
				case 15:
					bs.setBuildPrice(cell.toString().replace("\t",""));
					break;
				case 16:
					bs.setPositionPrice(cell.toString().replace("\t",""));
					break;
				case 17:
					bs.setFlatPrice(cell.toString().replace("\t",""));
					break;
				case 18:
					bs.setFlatProfit(cell.toString().replace("\t",""));
					break;
				case 19:
					bs.setRealityProfit(cell.toString().replace("\t",""));
					break;
				case 20:
					bs.setPoundage(cell.toString().replace("\t",""));
					break;
				case 21:
					bs.setTurnoverType(cell.toString().replace("\t",""));
					break;
				case 22:
					bs.setOperatorType(cell.toString().replace("\t",""));
					break;
				case 23:
					bs.setOperator(cell.toString().replace("\t",""));
					break;
				default:
					break;
				}
			}
			bs.setBatNumber(batNo);
			this.businessSeleService.addBusinessSele(bs);
		}
		ImportData im=new ImportData();
		im.setIm_imortNumber(batNo);
		im.setIm_type("客户成交查询");
		im.setIm_addTime(time);
		im.setIm_status(1);
		this.dataImportService.addData(im);
		return "";
	}
	
	
	public String readCsvEntrustSeles(String path) throws Exception{
		CsvReader reader = new CsvReader(path, ',',
				Charset.forName("GBK"));//
		reader.readHeaders();
		String[] headers = reader.getHeaders();		
		List<Object[]> list = new ArrayList<Object[]>();
		while (reader.readRecord()) {
			list.add(reader.getValues());
		}
		Object[][] datas = new String[list.size()][];
		for (int i = 0; i < list.size(); i++) {
			datas[i] = list.get(i);
		}
		/*
		 * 以下输出
		 */

		for (int i = 0; i < headers.length; i++) {
			System.out.print(headers[i] + "\t");
		}
		LogNo lg=new LogNo();
		String batNo=lg.getNumber();
		for (int i = 0; i < datas.length; i++) {
			Object[] data = datas[i]; // 取出一组数据
			EntrustSele es=new EntrustSele();
			System.out.println("版本规格=============" + headers.length);
			for (int j = 1; j < data.length; j++) {
				Object cell = data[j];
 				System.out.print(cell + "\t");
 				cell.toString().replace("\t","");
 				switch (j) {
				case 1:
					es.setBalanceDate(cell.toString().replace("\t",""));
					break;
				case 2:
					es.setEntrustTime(cell.toString().replace("\t",""));
					break;
				case 3:
					es.setAgencyName(cell.toString().replace("\t",""));
					break;
				case 4:
					es.setOrgName(cell.toString().replace("\t",""));
					break;
				case 5:
					es.setTradeAccount(cell.toString().replace("\t",""));
					break;
				case 6:
					es.setCustomerName(cell.toString().replace("\t",""));
					break;
				case 7:
					es.setEntrustNumber(cell.toString().replace("\t",""));
					break;
				case 8:
					es.setPositionNumber(cell.toString().replace("\t",""));
					break;
				case 9:
					es.setEntrustType(cell.toString().replace("\t",""));
					break;
				case 10:
					es.setCommodityName(cell.toString().replace("\t",""));
					break;
				case 11:
					es.setShopDirection(cell.toString().replace("\t",""));
					break;
				case 12:
					es.setBuildFlatPrice(cell.toString().replace("\t",""));
					break;
				case 13:
					es.setStopLoss(cell.toString().replace("\t",""));
					break;
				case 14:
					es.setStopProfit(cell.toString().replace("\t",""));
					break;
				case 15:
					es.setNumber(cell.toString().replace("\t",""));
					break;
				case 16:
					es.setEntrustPrice(cell.toString().replace("\t",""));
					break;
				case 17:
					es.setTransactionPrice(cell.toString().replace("\t",""));
					break;
				case 18:
					es.setEntrustStatus(cell.toString().replace("\t",""));
					break;
				case 19:
					es.setCancelTime(cell.toString().replace("\t",""));
					break;
				default:
					break;
				}
			}
			es.setBatNumber(batNo);
			this.entrustSeleService.addEntrustSele(es);
		}
		ImportData im=new ImportData();
		im.setIm_imortNumber(batNo);
		im.setIm_type("客户委托单查询");
		im.setIm_addTime(time);
		im.setIm_status(1);
		this.dataImportService.addData(im);
		return "";
	}
	public String readCsvFundStreamSeles(String path) throws Exception{
		CsvReader reader = new CsvReader(path, ',',
				Charset.forName("GBK"));//
		reader.readHeaders();
		String[] headers = reader.getHeaders();		
		List<Object[]> list = new ArrayList<Object[]>();
		while (reader.readRecord()) {
			list.add(reader.getValues());
		}
		Object[][] datas = new String[list.size()][];
		for (int i = 0; i < list.size(); i++) {
			datas[i] = list.get(i);
		}
		/*
		 * 以下输出
		 */

		for (int i = 0; i < headers.length; i++) {
			System.out.print(headers[i] + "\t");
		}
		LogNo lg=new LogNo();
		String batNo=lg.getNumber();
		for (int i = 0; i < datas.length; i++) {
			Object[] data = datas[i]; // 取出一组数据
			FundStreamSele fs=new FundStreamSele();
			System.out.println("版本规格=============" + headers.length);
			for (int j = 1; j < data.length; j++) {
				Object cell = data[j];
 				System.out.print(cell + "\t");
 				cell.toString().replace("\t","");
 				switch (j) {
				case 1:
					fs.setBalanceDate(cell.toString().replace("\t",""));
					break;
				case 2:
					fs.setAgencyName(cell.toString().replace("\t",""));
					break;
				case 3:
					fs.setOrgName(cell.toString().replace("\t",""));
					break;
				case 4:
					fs.setTradeAccount(cell.toString().replace("\t",""));
					break;
				case 5:
					fs.setCustomerName(cell.toString().replace("\t",""));
					break;
				case 6:
					fs.setSerialNumber(cell.toString().replace("\t",""));
					break;
				case 7:
					fs.setServiceName(cell.toString().replace("\t",""));
					break;
				case 8:
					fs.setHappenTime(cell.toString().replace("\t",""));
					break;
				case 9:
					fs.setChangeMoney(cell.toString().replace("\t",""));
					break;
				case 10:
					fs.setModificaMoney(cell.toString().replace("\t",""));
					break;
				case 11:
					fs.setRelevanceNumber(cell.toString().replace("\t",""));
					break;
				default:
					break;
				}
			}
			fs.setBatNumber(batNo);
			this.fundStreamSeleService.addFundStreamSele(fs);
		}
		ImportData im=new ImportData();
		im.setIm_imortNumber(batNo);
		im.setIm_type("客户资金流水查询");
		im.setIm_addTime(time);
		im.setIm_status(1);
		this.dataImportService.addData(im);
		return "";
	}
	
	public String readCsvCustomFunds(String path) throws Exception{
		CsvReader reader = new CsvReader(path, ',',
				Charset.forName("GBK"));//
		reader.readHeaders();
		String[] headers = reader.getHeaders();		
		List<Object[]> list = new ArrayList<Object[]>();
		while (reader.readRecord()) {
			list.add(reader.getValues());
		}
		Object[][] datas = new String[list.size()][];
		for (int i = 0; i < list.size(); i++) {
			datas[i] = list.get(i);
		}
		/*
		 * 以下输出
		 */

		for (int i = 0; i < headers.length; i++) {
			System.out.print(headers[i] + "\t");
		}
		LogNo lg=new LogNo();
		String batNo=lg.getNumber();
		for (int i = 0; i < datas.length; i++) {
			Object[] data = datas[i]; // 取出一组数据
			CustomFund cf=new CustomFund();
			System.out.println("版本规格=============" + headers.length);
			for (int j = 1; j < data.length; j++) {
				Object cell = data[j];
 				System.out.print(cell + "\t");
 				cell.toString().replace("\t","");
 				switch (j) {
 				case 1:
					cf.setAgencyName(cell.toString().trim());
					break;
 				case 2:
					cf.setOrgName(cell.toString().trim());
					break;
				case 3:
					cf.setTradeAccount(cell.toString().trim());
					break;
				case 4:
					cf.setCustomerName(cell.toString().trim());
					break;
				case 5:
					cf.setPrimeBenefit(cell.toString().trim());
					break;
				case 6:
					cf.setInandoutMoney(cell.toString().trim());
					break;
				case 7:
					cf.setFlatProfit(cell.toString().trim());
					break;
				case 8:
					cf.setPositionProfit(cell.toString().trim());
					break;
				case 9:
					cf.setPoundage(cell.toString().trim());
					break;
				case 10:
					cf.setOccupyBail(cell.toString().trim());
					break;
				case 11:
					cf.setUsableBail(cell.toString().trim());
					break;
				case 12:
					cf.setFreezeBail(cell.toString().trim());
					break;
				case 13:
					cf.setFreezePoundage(cell.toString().trim());
					break;
				case 14:
					cf.setPresentIncome(cell.toString().trim());
					break;
				case 15:
					cf.setDealBail(cell.toString().trim());
					break;
				case 16:
					cf.setDealPoundage(cell.toString().trim());
					break;
				case 17:
					cf.setDealLoan(cell.toString().trim());
					break;
				case 18:
					cf.setDealPenalty(cell.toString().trim());
					break;
				case 19:
					cf.setPenaltyMoney(cell.toString().trim());
					break;	
				case 20:
					cf.setRiskRate(cell.toString().trim());
					break;
				default:
					break;
				}
			}
			cf.setBatNumber(batNo);
			this.customFundService.addCustomFund(cf);			
		}
		ImportData im=new ImportData();
		im.setIm_imortNumber(batNo);
		im.setIm_type("客户资金查询");
		im.setIm_addTime(time);
		im.setIm_status(1);
		this.dataImportService.addData(im);
		return "";
	}
	@SuppressWarnings({ "rawtypes", "deprecation"})
	public String Upload(){
		HttpServletRequest request=ServletActionContext.getRequest();
		HttpServletResponse response=ServletActionContext.getResponse();
		String fileName;
		try {
			request.setCharacterEncoding("utf-8");
			response.setCharacterEncoding("utf-8");
			String realPath = request.getRealPath("/");
			String dirPath = realPath + "upload";
			File dirFile = new File(dirPath);
			if(!dirFile.exists()){
				dirFile.mkdirs();
			}
			FileItemFactory factory = new DiskFileItemFactory();
			ServletFileUpload upload = new ServletFileUpload(factory);			
			List items = upload.parseRequest(request);
			if(null != items)
			{
				Iterator itr = items.iterator();
				while(itr.hasNext()){
					FileItem item = (FileItem)itr.next();
					if(item.isFormField()){
						continue;
					} else{
						fileName = item.getName();					
						File saveFile = new File(dirPath,fileName);
						item.write(saveFile);
						String str="";
						String path=saveFile.getAbsolutePath();
						if (fileName.contains("客户资金状况表")) {
							try {
								str=readCsvStateFunds(path);
							} catch (Exception e) {
								e.printStackTrace();
							}
						}
						if (fileName.contains("客户持仓汇总表")) {
							try {
								str=readCsvPositionGathers(path);
							} catch (Exception e) {
								e.printStackTrace();
							}
						}
						if (fileName.contains("客户成交汇总表")) {
							try {
								str=readCsvBusinessGathers(path);
							} catch (Exception e) {
								e.printStackTrace();
							}
						}
						if (fileName.contains("客户持仓查询")) {
							try {
								str=readCsvPositionSeles(path);
							} catch (Exception e) {
								e.printStackTrace();
							}
						}
						if (fileName.contains("客户成交查询")) {
							try {
								str=readCsvBusinessSeles(path);
							} catch (Exception e) {
								e.printStackTrace();
							}
						}
						if (fileName.contains("客户委托单查询")) {
							try {
								str=readCsvEntrustSeles(path);
							} catch (Exception e) {
								e.printStackTrace();
							}
						}
						if (fileName.contains("客户资金流水查询")) {
							try {
								str=readCsvFundStreamSeles(path);
							} catch (Exception e) {
								e.printStackTrace();
							}
						}
						if (fileName.contains("客户资金查询")) {
							try {
								this.customFundService.deleteCustomFund();
								str=readCsvCustomFunds(path);
							} catch (Exception e) {
								e.printStackTrace();
							}
						}
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "success";
	}
	public File getFileField() {
		return fileField;
	}

	public void setFileField(File fileField) {
		this.fileField = fileField;
	}
	public String getFileFieldFileName() {
		return fileFieldFileName;
	}
	public void setFileFieldFileName(String fileFieldFileName) {
		this.fileFieldFileName = fileFieldFileName;
	}

	public StateFundService getStateFundService() {
		return stateFundService;
	}

	public void setStateFundService(StateFundService stateFundService) {
		this.stateFundService = stateFundService;
	}

	public DataImportService getDataImportService() {
		return dataImportService;
	}

	public void setDataImportService(DataImportService dataImportService) {
		this.dataImportService = dataImportService;
	}

	public PositionGatherService getPositionGatherService() {
		return positionGatherService;
	}

	public void setPositionGatherService(PositionGatherService positionGatherService) {
		this.positionGatherService = positionGatherService;
	}

	public BusinessGatherService getBusinessGatherService() {
		return businessGatherService;
	}

	public void setBusinessGatherService(BusinessGatherService businessGatherService) {
		this.businessGatherService = businessGatherService;
	}

	public PositionSeleService getPositionSeleService() {
		return positionSeleService;
	}

	public void setPositionSeleService(PositionSeleService positionSeleService) {
		this.positionSeleService = positionSeleService;
	}

	public BusinessSeleService getBusinessSeleService() {
		return businessSeleService;
	}

	public void setBusinessSeleService(BusinessSeleService businessSeleService) {
		this.businessSeleService = businessSeleService;
	}

	public EntrustSeleService getEntrustSeleService() {
		return entrustSeleService;
	}

	public void setEntrustSeleService(EntrustSeleService entrustSeleService) {
		this.entrustSeleService = entrustSeleService;
	}

	public FundStreamSeleService getFundStreamSeleService() {
		return fundStreamSeleService;
	}

	public void setFundStreamSeleService(FundStreamSeleService fundStreamSeleService) {
		this.fundStreamSeleService = fundStreamSeleService;
	}

	public CustomFundService getCustomFundService() {
		return customFundService;
	}

	public void setCustomFundService(CustomFundService customFundService) {
		this.customFundService = customFundService;
	}
}
