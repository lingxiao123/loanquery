<%@ page language="java" import="java.util.*"  contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
String tradcount="";
String clientname="";
String starttime="";
String endtime="";
try{
	if(session==null){
		response.sendRedirect(request.getContextPath()
				+ "/login.jsp");
	}
	tradcount=session.getAttribute("tradaccount").toString();
	clientname=session.getAttribute("clientname").toString();
	starttime=session.getAttribute("starttime").toString();
	endtime=session.getAttribute("endtime").toString();
}catch(Exception e){
	response.sendRedirect(request.getContextPath()
			+ "/login.jsp");
}
int TotalCount=Integer.parseInt(session.getAttribute("TotalCount").toString());
int Page=Integer.parseInt(session.getAttribute("Page").toString());
int PageCount=Integer.parseInt(session.getAttribute("PageCount").toString());
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>个代列表</title> 
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link rel="stylesheet" type="text/css" href="<%=path %>/css/style.css">
	<script type="text/javascript" src="<%=path %>/js/jquery.min.1.8.1.js" ></script>
	<script type="text/javascript" src="<%=path %>/js/time.js" ></script>
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<script type="text/javascript">
	function GetDateStr() {
		var dd = new Date();
		dd.setDate(dd.getDate()-1);//获取AddDayCount天后的日期
		var y = dd.getFullYear();
		var m = dd.getMonth()+1;//获取当前月份的日期
		var d = dd.getDate();
		//return y+"-"+m+"-"+d;
		if(m<10){
			m="0"+m;
		}
		if(d<10){
			d="0"+d;
		}
		var str=y+"-"+m+"-"+d;
		document.getElementById("stateTime").value=str;
		document.getElementById("endTime").value=str;
	}
	function search() {
		var tradaccount=document.getElementById("tradAccount").value;
		var clientname=document.getElementById("clientName").value;
		var starttime=document.getElementById("stateTime").value;
		var endtime=document.getElementById("endTime").value;
		var f=document.getElementsByTagName("form")[0];
		f.action="searchStausFund.action?tradaccount="+tradaccount+"&clientname="+clientname+"&starttime="+starttime+"&endtime="+endtime;
		
	}
	function Reset() {
		document.getElementById("tradAccount").value="";
		document.getElementById("clientName").value="";
		document.getElementById("stateTime").value="";
		document.getElementById("endTime").value="";
	}
	function importData() {
		location.href="importStatusFund.action?tradAccount="+document.getElementById("tradAccount").value+"&clientName="+document.getElementById("clientName").value+"&stateTime="+document.getElementById("stateTime").value+"&endTime="+document.getElementById("endTime").value;
	}
	</script>
  <style type="text/css">
  	.form th,.form td {
	padding: 0.1em 0.3em;
	border: 1px solid #D1D7DC;
	}
	
	.table th {
		background: #EDF4FF;
		font-weight: normal;
	}
	
	.table tbody th {
		text-align: right;
	}
	.table{
		width:80%;
		height:60px;
		font-size:12px;
		border: 1;
	}
	.table tr td{
		align:left;
	}
  </style>
  </head> 
  <body>
  	<div class="place">
    <span>位置：</span>
    <ul class="placeul">
    <li><a href="#">首页</a></li>
    <li><a href="#">个代数据查询</a></li>
    <li><a href="#">客户资金状况表</a></li>
    </ul>
    </div>    
    <div class="rightinfo">  
    <div>
    	<form action="" method="post">
    		交易帐号:<input type="text" id="tradAccount" value="<%=tradcount %>" class="defaultinput" name="searchStatusFund.tradAccount">
    		客户名称:<input type="text" id="clientName" value="<%=clientname %>" class="defaultinput" name="searchStatusFund.clientName">
    		开始时间:<input type="text" id="stateTime" value="<%=starttime %>" onclick="SetDate(this,'yyyy-MM-dd')" class="defaultinput" name="searchStatusFund.stateTime">
    		结束时间:<input type="text" id="endTime"  value="<%=endtime %>"onclick="SetDate(this,'yyyy-MM-dd')" class="defaultinput" name="searchStatusFund.endTime">
    		<input type="submit" id="sub" class="search" onclick="search();" name="sub" value="搜索">
    		<a href="#" class="a_reset" onclick="Reset();">重置</a>
    		<a href="#" class="a_reset" onclick="importData();">导出数据</a>
    	</form>          
    </div>   
    <div id="div_tab" style="width:100%;overflow-x: scroll; overflow-y: hidden;">
    <table class="tablelist" style="table-layout: fixed;">
    	<thead>
    	<tr style="display: -webkit-flex;">
        <th style="text-align:center;width:100px;">结算日期</th>
        <th style="text-align:center;width:100px;">交易账号</th>
        <th style="text-align:center;width:100px;">客户名称</th>
        <th style="text-align:center;width:100px;">期初权益</th>
        <th style="text-align:center;width:100px;">出入金</th>
        <th style="text-align:center;width:100px;">平仓盈亏</th>
        <th style="text-align:center;width:100px;">持仓盈亏</th>
        <th style="text-align:center;width:100px;">盈亏合计</th>
        <th style="text-align:center;width:200px;">交易所存留手续费</th>
        <th style="text-align:center;width:200px;">综合会员存留手续费</th>
        <th style="text-align:center;width:100px;">收客户手续费</th>
        <th style="text-align:center;width:100px;">收客户延期费</th>
        <th style="text-align:center;width:100px;">占用保证金</th>
        <th style="text-align:center;width:100px;">交收保证金</th>
        <th style="text-align:center;width:100px;">交收手续费</th>
        <th style="text-align:center;width:100px;">交收货款</th>
        <th style="text-align:center;width:100px;">交收违约金</th>
        <th style="text-align:center;width:100px;">交收税款</th>
        <th style="text-align:center;width:100px;">期末权益</th>
        <th style="text-align:center;width:100px;">风险率</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="u" items="${statuslist}">
        	<tr style="display: -webkit-flex;">
		        <td style="text-align:center;width:100px;">${u.accountsTime}</td>
		        <td style="text-align:center;width:100px;">${u.tradeAccount}</td>
		        <td style="text-align:center;width:100px;">${u.customerName}</td>
		        <td style="text-align:center;width:100px;">${u.primeBenefit}</td>
		        <td style="text-align:center;width:100px;">${u.inandoutMoney}</td>
		        <td style="text-align:center;width:100px;">${u.flatProfit}</td>
		        <td style="text-align:center;width:100px;">${u.positionProfit}</td>
		        <td style="text-align:center;width:100px;">${u.profitTotal}</td>
		        <td style="text-align:center;width:200px;">${u.exchangeFee}</td>
		        <td style="text-align:center;width:200px;">${u.memberFee}</td>
		        <td style="text-align:center;width:100px;">${u.susPoundage}</td>
		        <td style="text-align:center;width:100px;">${u.demurrage}</td>
		        <td style="text-align:center;width:100px;">${u.occupyBail}</td>
		        <td style="text-align:center;width:100px;">${u.dealBail}</td>
		        <td style="text-align:center;width:100px;">${u.dealPoundage}</td>
		        <td style="text-align:center;width:100px;">${u.dealLoan}</td>
		        <td style="text-align:center;width:100px;">${u.taxSettlement}</td>
		        <td style="text-align:center;width:100px;">${u.dealPenalty}</td>
		        <td style="text-align:center;width:100px;">${u.lastBenefit}</td> 
		        <td style="text-align:center;width:100px;">${u.riskRate}</td>    
	        </tr>
        </c:forEach>                             
        </tbody>
    </table>  
    </div>   
    <div class="pagin">
    	<div class="message" style="width:50%;">共<i class="blue"><%=TotalCount %></i>条记录，当前显示第&nbsp;<i class="blue"><%=Page %>&nbsp;</i>页,共&nbsp;<i class="blue"><%=PageCount %>&nbsp;</i>页</div>
    	 <ul class="paginList">
        	<li class="paginItem"><a href = "findStatusFundPage.action?Page=1&tradAccount=<%=tradcount %>&clientName=<%=clientname %>&stateTime=<%=starttime %>&endTime=<%=endtime %>" style="width:40px;" >首页</a>
               <%if(Page!=1){%> 
               	<a href = "findStatusFundPage.action?Page=<%=Page-1%>&tradAccount=<%=tradcount %>&clientName=<%=clientname %>&stateTime=<%=starttime %>&endTime=<%=endtime %>" style="" >上一页</a>	
               	
               <% }else{%>
               	<a href = "findStatusFundPage.action?Page=<%=Page-1%>&tradAccount=<%=tradcount %>&clientName=<%=clientname %>&stateTime=<%=starttime %>&endTime=<%=endtime %>" onclick="return false" style="background-color:#ccc;" >上一页</a>
               <% }%>
               <%
               if(Page!=PageCount){%>
               	<a href = "findStatusFundPage.action?Page=<%=Page+1 %>&tradAccount=<%=tradcount %>&clientName=<%=clientname %>&stateTime=<%=starttime %>&endTime=<%=endtime %>" style=" ">下一页</a>
               <%}else{%>
               	<a href = "findStatusFundPage.action?Page=<%=Page+1 %>&tradAccount=<%=tradcount %>&clientName=<%=clientname %>&stateTime=<%=starttime %>&endTime=<%=endtime %>" onclick="return false" style="background-color:#ccc;" style="">下一页</a>
               <%}%>
               <a href = "findStatusFundPage.action?Page=<%=PageCount %>&tradAccount=<%=tradcount %>&clientName=<%=clientname %>&stateTime=<%=starttime %>&endTime=<%=endtime %>" style=" ">尾页</a></li>
        </ul>
    </div>    
    <div class="tip">
    	<div class="tiptop"><span>提示信息</span><a></a></div>       
      <div class="tipinfo">
        <span><img src="images/ticon.png" /></span>
        <div class="tipright">
        <p>是否确认对信息的修改 ？</p>
        <cite>如果是请点击确定按钮 ，否则请点取消。</cite>
        </div>
        </div>
        
        <div class="tipbtn">
        <input name="" type="button"  class="sure" value="确定" />&nbsp;
        <input name="" type="button"  class="cancel" value="取消" />
        </div>
    </div>  
    </div>  
    <script type="text/javascript">
		$('.tablelist tbody tr:odd').addClass('odd');
	</script>
  </body>
</html>
