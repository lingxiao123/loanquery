package com.mcjs.util;

import java.io.OutputStream;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;

import com.mcjs.entity.BusinessGather;
import com.mcjs.entity.BusinessSele;
import com.mcjs.entity.CustomFund;
import com.mcjs.entity.EntrustSele;
import com.mcjs.entity.FundStreamSele;
import com.mcjs.entity.PositionGather;
import com.mcjs.entity.PositionSele;
import com.mcjs.entity.StateFund;

public class ExcleTable {
	@SuppressWarnings("deprecation")
	public void ExportBusGather(String title,List<BusinessGather> list,OutputStream os){
		 // 声明一个工作薄
		  HSSFWorkbook workbook = new HSSFWorkbook();
	      // 生成一个表格
	      HSSFSheet sheet = workbook.createSheet(title);
	      // 设置表格默认列宽度为20个字节
	      sheet.setDefaultColumnWidth((short)20);
	      // 生成一个字体
	      HSSFFont font2 = workbook.createFont();
	      font2.setColor(HSSFColor.BLACK.index);
	      font2.setFontHeightInPoints((short) 10);	      
	   // 生成一个样式style
	      HSSFCellStyle style2 = workbook.createCellStyle();
	     // 把字体应用到当前的样式
	     style2.setFont(font2);	   
	      int index =0; //上端
	      int left = 0; //距左边
	      String[] header ={ "序号","结算时间", "交易账号", "客户名称","商品","成交量","成交金额","平仓盈亏","持仓盈亏","盈亏合计"
	        		,"交易所存留手续费","综合会员存留手续费","收客户手续费"};
	      //表头
	      HSSFRow row0 = sheet.createRow((index));      
	      for(int h =0+index ; h< header.length+index; h++){
	    	  HSSFCell cell00 = row0.createCell(left+h);
		      cell00.setCellValue(header[h]);
	      }
	      for (int i = 0; i <list.size(); i++) {
	    	  	BusinessGather bg=new BusinessGather();
	    	  	bg = list.get(i);
		    	HSSFRow row = sheet.createRow((index+i+1));//
		    	
		    	HSSFCell celli0 = row.createCell(left+0);
		    	celli0.setCellValue(i+1);//序号
		    	HSSFCell celli1 = row.createCell(left+1);
		    	celli1.setCellValue(bg.getAccountsTime());//结算日期    	
		    	HSSFCell celli2 = row.createCell(left+2);
		    	celli2.setCellValue(bg.getTradeAccount());//交易账号		 	    	    	
		    	HSSFCell celli3 = row.createCell(left+3);
		    	celli3.setCellValue(bg.getCustomerName());//客户名称
		    	HSSFCell celli4 = row.createCell(left+4);
		    	celli4.setCellValue(bg.getCommodity());//商品		    	
		    	HSSFCell celli5 = row.createCell(left+5);
		    	celli5.setCellValue(bg.getTurnoverNum());//成交量		    	
		    	HSSFCell celli6 = row.createCell(left+6);
		    	celli6.setCellValue(bg.getTurnoverMoney());//成交金额
		    	HSSFCell celli7 = row.createCell(left+7);
		    	celli7.setCellValue(bg.getFlatProfit());//平仓盈亏
		    	HSSFCell celli8 = row.createCell(left+8);
		    	celli8.setCellValue(bg.getPositionProfit());//持仓盈亏	    	
		    	HSSFCell celli9 = row.createCell(left+9);
		    	celli9.setCellValue(bg.getProfitTotal());//盈亏合计
		    	HSSFCell celli10 = row.createCell(left+10);
		    	celli10.setCellValue(bg.getExchangePoundage());//交易所存留手续费
		    	HSSFCell celli11 = row.createCell(left+11);
		    	celli11.setCellValue(bg.getSynthesizePoundage());//综合会员存留手续费
		    	HSSFCell celli12 = row.createCell(left+12);
		    	celli12.setCellValue(bg.getTakeinPoundage());//收客户手续费		    	
		  }
	      try {
		    	workbook.write(os);	
	      }catch (Exception e) {
				e.printStackTrace();
		  }
	}
	
	@SuppressWarnings("deprecation")
	public void ExportBusinessSele(String title,List<BusinessSele> list,OutputStream os){
		// 声明一个工作薄
		  HSSFWorkbook workbook = new HSSFWorkbook();
	      // 生成一个表格
	      HSSFSheet sheet = workbook.createSheet(title);
	      // 设置表格默认列宽度为20个字节
	      sheet.setDefaultColumnWidth((short)20);
	      // 生成一个字体
	      HSSFFont font2 = workbook.createFont();
	      font2.setColor(HSSFColor.BLACK.index);
	      font2.setFontHeightInPoints((short) 10);	      
	      // 生成一个样式style
	      HSSFCellStyle style2 = workbook.createCellStyle();
	      // 把字体应用到当前的样式
	      style2.setFont(font2);	   
	      int index =0; //上端
	      int left = 0; //距左边
	      String[] header ={ "序号","结算时间", "交易账号", "客户名称","建/平仓","成交单号","委托单号","持仓单号","成交时间","商品","成交量"
	        		,"成交金额","买卖方向","建仓价","持仓价","平仓价","平仓盈亏","实际盈亏","手续费","成交类型","操作类型","操作员" };
	      //表头
	      HSSFRow row0 = sheet.createRow((index));      
	      for(int h =0+index ; h< header.length+index; h++){
	    	  HSSFCell cell00 = row0.createCell(left+h);
		      cell00.setCellValue(header[h]);
	      }
	      for (int i = 0; i <list.size(); i++) {
	    	  	BusinessSele bs=new BusinessSele();
	    	  	bs = list.get(i);
		    	HSSFRow row = sheet.createRow((index+i+1));//	    	
		    	HSSFCell celli0 = row.createCell(left+0);
		    	celli0.setCellValue(i+1);//序号
		    	HSSFCell celli1 = row.createCell(left+1);
		    	celli1.setCellValue(bs.getBalanceDate());//结算时间   	
		    	HSSFCell celli2 = row.createCell(left+2);
		    	celli2.setCellValue(bs.getTradeAccount());//交易账号		 	    	    	
		    	HSSFCell celli3 = row.createCell(left+3);
		    	celli3.setCellValue(bs.getCustomerName());//客户名称
		    	HSSFCell celli4 = row.createCell(left+4);
		    	celli4.setCellValue(bs.getBuildFlatPrice());//建/平仓		    	
		    	HSSFCell celli5 = row.createCell(left+5);
		    	celli5.setCellValue(bs.getTurnoverNumbers());//成交单号		    	
		    	HSSFCell celli6 = row.createCell(left+6);
		    	celli6.setCellValue(bs.getEntrustNumbers());//委托单号
		    	HSSFCell celli7 = row.createCell(left+7);
		    	celli7.setCellValue(bs.getPositionNumbers());//持仓单号
		    	HSSFCell celli8 = row.createCell(left+8);
		    	celli8.setCellValue(bs.getTurnoverTime());//成交时间 	
		    	HSSFCell celli9 = row.createCell(left+9);
		    	celli9.setCellValue(bs.getCommodity());//商品
		    	HSSFCell celli10 = row.createCell(left+10);
		    	celli10.setCellValue(bs.getTurnoverAmount());//成交量
		    	HSSFCell celli11 = row.createCell(left+11);
		    	celli11.setCellValue(bs.getTurnoverMoney());//成交金额
		    	HSSFCell celli12 = row.createCell(left+12);
		    	celli12.setCellValue(bs.getShopDirection());//买卖方向
		    	HSSFCell celli13 = row.createCell(left+13);
		    	celli13.setCellValue(bs.getBuildPrice());//建仓价
		    	HSSFCell celli14 = row.createCell(left+14);
		    	celli14.setCellValue(bs.getPositionPrice());//持仓价
		    	HSSFCell celli15= row.createCell(left+15);
		    	celli15.setCellValue(bs.getFlatPrice());//平仓价
		    	HSSFCell celli16= row.createCell(left+16);
		    	celli16.setCellValue(bs.getFlatProfit());//平仓盈亏
		    	HSSFCell celli17= row.createCell(left+17);
		    	celli17.setCellValue(bs.getRealityProfit());//实际盈亏
		    	HSSFCell celli18= row.createCell(left+18);
		    	celli18.setCellValue(bs.getPoundage());//手续费
		    	HSSFCell celli19= row.createCell(left+19);
		    	celli19.setCellValue(bs.getTurnoverType());//成交类型
		    	HSSFCell celli20= row.createCell(left+20);
		    	celli20.setCellValue(bs.getOperatorType());//操作类型
		    	HSSFCell celli21= row.createCell(left+21);
		    	celli21.setCellValue(bs.getOperator());//操作员   
		  }
	      try {
		    	workbook.write(os);	
	      }catch (Exception e) {
				e.printStackTrace();
		  }
	}
	@SuppressWarnings("deprecation")
	public void ExportCustomFund(String title,List<CustomFund> list,OutputStream os){
		 // 声明一个工作薄
		  HSSFWorkbook workbook = new HSSFWorkbook();
	      // 生成一个表格
	      HSSFSheet sheet = workbook.createSheet(title);
	      // 设置表格默认列宽度为20个字节
	      sheet.setDefaultColumnWidth((short)20);
	      // 生成一个字体
	      HSSFFont font2 = workbook.createFont();
	      font2.setColor(HSSFColor.BLACK.index);
	      font2.setFontHeightInPoints((short) 10);	      
	      // 生成一个样式style
	      HSSFCellStyle style2 = workbook.createCellStyle();
	      // 把字体应用到当前的样式
	      style2.setFont(font2);	   
	      int index =0; //上端
	      int left = 0; //距左边
	      String[] header ={ "序号", "交易账号", "客户名称","期初权益","出入金","平仓盈亏","持仓盈亏","手续费","占用保证金","可用保证金"
	        		,"冻结保证金","冻结手续费","当前权益","交收保证金","交收手续费","交收贷款","交收违约金","违约金","风险率" };
	      //表头
	      HSSFRow row0 = sheet.createRow((index));      
	      for(int h =0+index ; h< header.length+index; h++){
	    	  HSSFCell cell00 = row0.createCell(left+h);
		      cell00.setCellValue(header[h]);
	      }
	      for (int i = 0; i <list.size(); i++) {
	    	  	CustomFund cf=new CustomFund();
	    	  	cf = list.get(i);
		    	HSSFRow row = sheet.createRow((index+i+1));//	    	
		    	HSSFCell celli0 = row.createCell(left+0);
		    	celli0.setCellValue(i+1);//序号
		    	HSSFCell celli1 = row.createCell(left+1);
		    	celli1.setCellValue(cf.getTradeAccount());//交易账号	
		    	HSSFCell celli2 = row.createCell(left+2);
		    	celli2.setCellValue(cf.getCustomerName());//客户名称		 	    	    	
		    	HSSFCell celli3 = row.createCell(left+3);
		    	celli3.setCellValue(cf.getPrimeBenefit());//期初权益
		    	HSSFCell celli4 = row.createCell(left+4);
		    	celli4.setCellValue(cf.getInandoutMoney());//出入金		    	
		    	HSSFCell celli5 = row.createCell(left+5);
		    	celli5.setCellValue(cf.getFlatProfit());//平仓盈亏		    	
		    	HSSFCell celli6 = row.createCell(left+6);
		    	celli6.setCellValue(cf.getPositionProfit());//持仓盈亏
		    	HSSFCell celli7 = row.createCell(left+7);
		    	celli7.setCellValue(cf.getPoundage());//手续费
		    	HSSFCell celli8 = row.createCell(left+8);
		    	celli8.setCellValue(cf.getOccupyBail());//占用保证金	
		    	HSSFCell celli9 = row.createCell(left+9);
		    	celli9.setCellValue(cf.getUsableBail());//可用保证金
		    	HSSFCell celli10 = row.createCell(left+10);
		    	celli10.setCellValue(cf.getFreezeBail());//冻结保证金
		    	HSSFCell celli11 = row.createCell(left+11);
		    	celli11.setCellValue(cf.getFreezePoundage());//冻结手续费
		    	HSSFCell celli12 = row.createCell(left+12);
		    	celli12.setCellValue(cf.getPresentIncome());//当前权益
		    	HSSFCell celli13 = row.createCell(left+13);
		    	celli13.setCellValue(cf.getDealBail());//交收保证金
		    	HSSFCell celli14 = row.createCell(left+14);
		    	celli14.setCellValue(cf.getDealPoundage());//交收手续费
		    	HSSFCell celli15= row.createCell(left+15);
		    	celli15.setCellValue(cf.getDealLoan());//交收贷款
		    	HSSFCell celli16= row.createCell(left+16);
		    	celli16.setCellValue(cf.getDealPenalty());//交收违约金
		    	HSSFCell celli17= row.createCell(left+17);
		    	celli17.setCellValue(cf.getPenaltyMoney());//违约金
		    	HSSFCell celli18= row.createCell(left+18);
		    	celli18.setCellValue(cf.getRiskRate());//风险率
		  }
	      try {
		    	workbook.write(os);	
	      }catch (Exception e) {
				e.printStackTrace();
		  }
	}
	@SuppressWarnings("deprecation")
	public void ExportEntrustSele(String title,List<EntrustSele> list,OutputStream os){
		// 声明一个工作薄
		  HSSFWorkbook workbook = new HSSFWorkbook();
	      // 生成一个表格
	      HSSFSheet sheet = workbook.createSheet(title);
	      // 设置表格默认列宽度为20个字节
	      sheet.setDefaultColumnWidth((short)20);
	      // 生成一个字体
	      HSSFFont font2 = workbook.createFont();
	      font2.setColor(HSSFColor.BLACK.index);
	      font2.setFontHeightInPoints((short) 10);	      
	   // 生成一个样式style
	      HSSFCellStyle style2 = workbook.createCellStyle();
	     // 把字体应用到当前的样式
	     style2.setFont(font2);	   
	      int index =0; //上端
	      int left = 0; //距左边
	      String[] header ={ "序号","结算时间","委托时间", "交易账号", "客户名称","委托单号","持仓单号","委托类型","商品名称","买卖方向","建/平仓","止损价"
	        		,"止盈价","数量","委托价","成交价","委托状态","撤单时间"};
	      //表头
	      HSSFRow row0 = sheet.createRow((index));      
	      for(int h =0+index ; h< header.length+index; h++){
	    	  HSSFCell cell00 = row0.createCell(left+h);
		      cell00.setCellValue(header[h]);
	      }
	      for (int i = 0; i <list.size(); i++) {
	    	  	EntrustSele es=new EntrustSele();
	    	  	es = list.get(i);
		    	HSSFRow row = sheet.createRow((index+i+1));//	    	
		    	HSSFCell celli0 = row.createCell(left+0);
		    	celli0.setCellValue(i+1);//序号
		    	HSSFCell celli1 = row.createCell(left+1);
		    	celli1.setCellValue(es.getBalanceDate());//结算日期    	
		    	HSSFCell celli2 = row.createCell(left+2);
		    	celli2.setCellValue(es.getEntrustTime());//委托时间	 	    	    	
		    	HSSFCell celli3 = row.createCell(left+3);
		    	celli3.setCellValue(es.getTradeAccount());//交易账号
		    	HSSFCell celli4 = row.createCell(left+4);
		    	celli4.setCellValue(es.getCustomerName());//客户名称		    	
		    	HSSFCell celli5 = row.createCell(left+5);
		    	celli5.setCellValue(es.getEntrustNumber());//委托单号		    	
		    	HSSFCell celli6 = row.createCell(left+6);
		    	celli6.setCellValue(es.getPositionNumber());//持仓单号
		    	HSSFCell celli7 = row.createCell(left+7);
		    	celli7.setCellValue(es.getEntrustType());//委托类型
		    	HSSFCell celli8 = row.createCell(left+8);
		    	celli8.setCellValue(es.getCommodityName());//商品名称	
		    	HSSFCell celli9 = row.createCell(left+9);
		    	celli9.setCellValue(es.getShopDirection());//买卖方向
		    	HSSFCell celli10 = row.createCell(left+10);
		    	celli10.setCellValue(es.getBuildFlatPrice());//建/平仓
		    	HSSFCell celli11 = row.createCell(left+11);
		    	celli11.setCellValue(es.getStopLoss());//止损价
		    	HSSFCell celli12 = row.createCell(left+12);
		    	celli12.setCellValue(es.getStopProfit());//止盈价
		    	HSSFCell celli13 = row.createCell(left+13);
		    	celli13.setCellValue(es.getNumber());//数量
		    	HSSFCell celli14 = row.createCell(left+14);
		    	celli14.setCellValue(es.getEntrustPrice());//委托价
		    	HSSFCell celli15 = row.createCell(left+15);
		    	celli15.setCellValue(es.getTransactionPrice());//成交价
		    	HSSFCell celli16 = row.createCell(left+16);
		    	celli16.setCellValue(es.getEntrustStatus());//委托状态
		    	HSSFCell celli17 = row.createCell(left+17);
		    	celli17.setCellValue(es.getCancelTime());//测单时间
		  }
	      try {
		    	workbook.write(os);	
	      }catch (Exception e) {
				e.printStackTrace();
		  }
	}
	@SuppressWarnings("deprecation")
	public void ExportFundStream(String title,List<FundStreamSele> list,OutputStream os){
		// 声明一个工作薄
		  HSSFWorkbook workbook = new HSSFWorkbook();
	      // 生成一个表格
	      HSSFSheet sheet = workbook.createSheet(title);
	      // 设置表格默认列宽度为20个字节
	      sheet.setDefaultColumnWidth((short)20);
	      // 生成一个字体
	      HSSFFont font2 = workbook.createFont();
	      font2.setColor(HSSFColor.BLACK.index);
	      font2.setFontHeightInPoints((short) 10);	      
	   // 生成一个样式style
	      HSSFCellStyle style2 = workbook.createCellStyle();
	     // 把字体应用到当前的样式
	     style2.setFont(font2);	   
	      int index =0; //上端
	      int left = 0; //距左边
	      String[] header ={ "序号","结算日期", "交易账号", "客户名称","流水号","业务名称","发生时间","变动资金","变后资金","关联单号"};
	      //表头
	      HSSFRow row0 = sheet.createRow((index));      
	      for(int h =0+index ; h< header.length+index; h++){
	    	  HSSFCell cell00 = row0.createCell(left+h);
		      cell00.setCellValue(header[h]);
	      }
	      for (int i = 0; i <list.size(); i++) {
	    	  	FundStreamSele fs=new FundStreamSele();
	    	  	fs = list.get(i);
		    	HSSFRow row = sheet.createRow((index+i+1));//	    	
		    	HSSFCell celli0 = row.createCell(left+0);
		    	celli0.setCellValue(i+1);//序号
		    	HSSFCell celli1 = row.createCell(left+1);
		    	celli1.setCellValue(fs.getBalanceDate());//结算日期    	
		    	HSSFCell celli2 = row.createCell(left+2);
		    	celli2.setCellValue(fs.getTradeAccount());//交易账号	 	    	    	
		    	HSSFCell celli3 = row.createCell(left+3);
		    	celli3.setCellValue(fs.getCustomerName());//客户名称
		    	HSSFCell celli4 = row.createCell(left+4);
		    	celli4.setCellValue(fs.getSerialNumber());//流水号		    	
		    	HSSFCell celli5 = row.createCell(left+5);
		    	celli5.setCellValue(fs.getServiceName());//业务名称	    	
		    	HSSFCell celli6 = row.createCell(left+6);
		    	celli6.setCellValue(fs.getHappenTime());//发生时间
		    	HSSFCell celli7 = row.createCell(left+7);
		    	celli7.setCellValue(fs.getChangeMoney());//变动资金
		    	HSSFCell celli8 = row.createCell(left+8);
		    	celli8.setCellValue(fs.getModificaMoney());//变后资金	
		    	HSSFCell celli9 = row.createCell(left+9);
		    	celli9.setCellValue(fs.getRelevanceNumber());//关联单号
		  }
	      try {
		    	workbook.write(os);	
	      }catch (Exception e) {
				e.printStackTrace();
		  }
	}
	@SuppressWarnings("deprecation")
	public void ExportPositionGather(String title,List<PositionGather> list,OutputStream os){
		// 声明一个工作薄
		  HSSFWorkbook workbook = new HSSFWorkbook();
	      // 生成一个表格
	      HSSFSheet sheet = workbook.createSheet(title);
	      // 设置表格默认列宽度为20个字节
	      sheet.setDefaultColumnWidth((short)20);
	      // 生成一个字体
	      HSSFFont font2 = workbook.createFont();
	      font2.setColor(HSSFColor.BLACK.index);
	      font2.setFontHeightInPoints((short) 10);	      
	   // 生成一个样式style
	      HSSFCellStyle style2 = workbook.createCellStyle();
	     // 把字体应用到当前的样式
	     style2.setFont(font2);	   
	      int index =0; //上端
	      int left = 0; //距左边
	      String[] header ={ "序号","结算时间", "交易账号", "客户名称","商品","买单持仓量","卖单持仓量","持仓合计","延期费","市场留存手续费","占用保证金"};
	      //表头
	      HSSFRow row0 = sheet.createRow((index));      
	      for(int h =0+index ; h< header.length+index; h++){
	    	  HSSFCell cell00 = row0.createCell(left+h);
		      cell00.setCellValue(header[h]);
	      }
	      for (int i = 0; i <list.size(); i++) {
	    	    PositionGather ps=new PositionGather();
	    	  	ps = list.get(i);
		    	HSSFRow row = sheet.createRow((index+i+1));//	    	
		    	HSSFCell celli0 = row.createCell(left+0);
		    	celli0.setCellValue(i+1);//序号
		    	HSSFCell celli1 = row.createCell(left+1);
		    	celli1.setCellValue(ps.getAccountsTime());//结算日期    	
		    	HSSFCell celli2 = row.createCell(left+2);
		    	celli2.setCellValue(ps.getTradeAccount());//交易账号		 	    	    	
		    	HSSFCell celli3 = row.createCell(left+3);
		    	celli3.setCellValue(ps.getCustomerName());//客户名称
		    	HSSFCell celli4 = row.createCell(left+4);
		    	celli4.setCellValue(ps.getCommodity());//商品		    	
		    	HSSFCell celli5 = row.createCell(left+5);
		    	celli5.setCellValue(ps.getBuyPositionNum());//买单持仓量		    	
		    	HSSFCell celli6 = row.createCell(left+6);
		    	celli6.setCellValue(ps.getSellPositionNum());//卖单持仓量
		    	HSSFCell celli7 = row.createCell(left+7);
		    	celli7.setCellValue(ps.getPositionTotal());//持仓合计
		    	HSSFCell celli8 = row.createCell(left+8);
		    	celli8.setCellValue(ps.getDemurrage());//延期费
		    	HSSFCell celli9 = row.createCell(left+9);
		    	celli9.setCellValue(ps.getMarketPoundage());//市场留存手续费
		    	HSSFCell celli10 = row.createCell(left+10);
		    	celli10.setCellValue(ps.getOccupyBail());//占用保证金		    	
		  }
	      try {
		    	workbook.write(os);	
	      }catch (Exception e) {
				e.printStackTrace();
		  }
	}
	@SuppressWarnings("deprecation")
	public void ExportPositionsele(String title,List<PositionSele> list,OutputStream os){
		// 声明一个工作薄
		  HSSFWorkbook workbook = new HSSFWorkbook();
	      // 生成一个表格
	      HSSFSheet sheet = workbook.createSheet(title);
	      // 设置表格默认列宽度为20个字节
	      sheet.setDefaultColumnWidth((short)20);
	      // 生成一个字体
	      HSSFFont font2 = workbook.createFont();
	      font2.setColor(HSSFColor.BLACK.index);
	      font2.setFontHeightInPoints((short) 10);	      
	   // 生成一个样式style
	      HSSFCellStyle style2 = workbook.createCellStyle();
	     // 把字体应用到当前的样式
	     style2.setFont(font2);	   
	      int index =0; //上端
	      int left = 0; //距左边
	      String[] header ={ "序号","结算时间", "交易账号", "客户名称","持仓单号","建仓时间","商品","买卖标志","持仓数量","建仓价","持仓价","平仓价","浮动盈亏","占用保证金","手续费","延期费","操作员" };
	      //表头
	      HSSFRow row0 = sheet.createRow((index));      
	      for(int h =0+index ; h< header.length+index; h++){
	    	  HSSFCell cell00 = row0.createCell(left+h);
		      cell00.setCellValue(header[h]);
	      }
	      for (int i = 0; i <list.size(); i++) {
	    	  	PositionSele ps=new PositionSele();
	    	  	ps= list.get(i);
		    	HSSFRow row = sheet.createRow((index+i+1));//	    	
		    	HSSFCell celli0 = row.createCell(left+0);
		    	celli0.setCellValue(i+1);//序号
		    	HSSFCell celli1 = row.createCell(left+1);
		    	celli1.setCellValue(ps.getBalanceDate());//结算时间   	
		    	HSSFCell celli2 = row.createCell(left+2);
		    	celli2.setCellValue(ps.getTradeAccount());//交易账号		 	    	    	
		    	HSSFCell celli3 = row.createCell(left+3);
		    	celli3.setCellValue(ps.getCustomerName());//客户名称
		    	HSSFCell celli4 = row.createCell(left+4);
		    	celli4.setCellValue(ps.getPositionNumbers());//持仓单号		    	
		    	HSSFCell celli5 = row.createCell(left+5);
		    	celli5.setCellValue(ps.getBuildTime());//建仓时间		    	
		    	HSSFCell celli6 = row.createCell(left+6);
		    	celli6.setCellValue(ps.getCommodity());//商品
		    	HSSFCell celli7 = row.createCell(left+7);
		    	celli7.setCellValue(ps.getShopSign());//买卖标志
		    	HSSFCell celli8 = row.createCell(left+8);
		    	celli8.setCellValue(ps.getPositionAmount());//持仓数量		    	
		    	HSSFCell celli9 = row.createCell(left+9);
		    	celli9.setCellValue(ps.getRunningPrice());//建仓价
		    	HSSFCell celli10 = row.createCell(left+10);
		    	celli10.setCellValue(ps.getPositionPrice());//持仓价
		    	HSSFCell celli11 = row.createCell(left+11);
		    	celli11.setCellValue(ps.getFlatPrice());//平仓价
		    	HSSFCell celli12 = row.createCell(left+12);
		    	celli12.setCellValue(ps.getFloatProfit());//浮动盈亏
		    	HSSFCell celli13 = row.createCell(left+13);
		    	celli13.setCellValue(ps.getOccupyBail());//占用保证金
		    	HSSFCell celli14 = row.createCell(left+14);
		    	celli14.setCellValue(ps.getPoundage());//手续费
		    	HSSFCell celli15= row.createCell(left+15);
		    	celli15.setCellValue(ps.getDemurrage());//延期费
		    	HSSFCell celli16= row.createCell(left+16);
		    	celli16.setCellValue(ps.getOperator());//操作员
		  }
	      try {
		    	workbook.write(os);	
	      }catch (Exception e) {
				e.printStackTrace();
		  }
	}
	@SuppressWarnings("deprecation")
	public void ExportStateFund(String title,List<StateFund> list,OutputStream os){
		// 声明一个工作薄
		HSSFWorkbook workbook = new HSSFWorkbook();
	      // 生成一个表格
	      HSSFSheet sheet = workbook.createSheet(title);
	      // 设置表格默认列宽度为20个字节
	      sheet.setDefaultColumnWidth((short)20);
	      // 生成一个字体
	      HSSFFont font2 = workbook.createFont();
	      font2.setColor(HSSFColor.BLACK.index);
	      font2.setFontHeightInPoints((short) 10);
	      
	   // 生成一个样式style
	      HSSFCellStyle style2 = workbook.createCellStyle();
	     // 把字体应用到当前的样式
	     style2.setFont(font2);
	     

	      int index =0; //上端
	      int left = 0; //距左边
	      String[] header ={ "序号","结算时间", "交易账号", "客户名称","初期权益","出入金","平仓盈亏","持仓盈亏","盈亏合计","收客户手续费","延期费"
	        		,"占用保证金","交收保证金","交收手续费","交收贷款","交收违约金","期末权益","风险率" };
	      //表头
	      HSSFRow row0 = sheet.createRow((index));
	      
	      for(int h =0+index ; h< header.length+index; h++){
	    	  HSSFCell cell00 = row0.createCell(left+h);
		      cell00.setCellValue(header[h]);
	      }
	      for (int i = 0; i <list.size(); i++) {
	    	  	StateFund sf=new StateFund();
	    	  	sf = list.get(i);
		    	HSSFRow row = sheet.createRow((index+i+1));//
		    	
		    	HSSFCell celli0 = row.createCell(left+0);
		    	celli0.setCellValue(i+1);//序号
		    	HSSFCell celli1 = row.createCell(left+1);
		    	celli1.setCellValue(sf.getAccountsTime());//结算日期    	
		    	HSSFCell celli2 = row.createCell(left+2);
		    	celli2.setCellValue(sf.getTradeAccount());//交易账号		 	    	    	
		    	HSSFCell celli3 = row.createCell(left+3);
		    	celli3.setCellValue(sf.getCustomerName());//客户名称
		    	HSSFCell celli4 = row.createCell(left+4);
		    	celli4.setCellValue(sf.getPrimeBenefit());//初期权益		    	
		    	HSSFCell celli5 = row.createCell(left+5);
		    	celli5.setCellValue(sf.getInandoutMoney());//出入金		    	
		    	HSSFCell celli6 = row.createCell(left+6);
		    	celli6.setCellValue(sf.getFlatProfit());//平仓盈亏
		    	HSSFCell celli7 = row.createCell(left+7);
		    	celli7.setCellValue(sf.getPositionProfit());//持仓盈亏
		    	HSSFCell celli8 = row.createCell(left+8);
		    	celli8.setCellValue(sf.getProfitTotal());//盈亏合计
		    	
		    	HSSFCell celli9 = row.createCell(left+9);
		    	celli9.setCellValue(sf.getSusPoundage());//收客户手续费
		    	HSSFCell celli10 = row.createCell(left+10);
		    	celli10.setCellValue(sf.getDemurrage());//延期费
		    	HSSFCell celli11 = row.createCell(left+11);
		    	celli11.setCellValue(sf.getOccupyBail());//占用保证金
		    	HSSFCell celli12 = row.createCell(left+12);
		    	celli12.setCellValue(sf.getDealBail());//交收保证金
		    	HSSFCell celli13 = row.createCell(left+13);
		    	celli13.setCellValue(sf.getDealPoundage());//交收手续费
		    	HSSFCell celli14 = row.createCell(left+14);
		    	celli14.setCellValue(sf.getDealLoan());//交收贷款
		    	HSSFCell celli15 = row.createCell(left+15);
		    	celli15.setCellValue(sf.getDealPenalty());//交收违约金
		    	HSSFCell celli16 = row.createCell(left+16);
		    	celli16.setCellValue(sf.getLastBenefit());//期末权益
		    	HSSFCell celli17 = row.createCell(left+17);
		    	celli17.setCellValue(sf.getRiskRate());//风险率		    	
		  }
	      try {
		    	workbook.write(os);	
	      }catch (Exception e) {
				e.printStackTrace();
		  }
	}
}
