<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
	String tradcount="";
	String clientname="";
	String starttime="";
	String endtime="";
	String bussnumber="";
	try{
		if(session==null){
			response.sendRedirect(request.getContextPath()+ "/login.jsp");
		}
		tradcount=session.getAttribute("tradaccpunt").toString();
		clientname=session.getAttribute("clientname").toString();
		starttime=session.getAttribute("starttime").toString();
		endtime=session.getAttribute("endtime").toString();
		bussnumber=session.getAttribute("businessnumber").toString();
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
			var f=document.getElementsByTagName("form")[0];
			f.action="searchBusinessSele.action";		
		}
		function Reset() {
			document.getElementById("tradAccount").value="";
			document.getElementById("clientName").value="";
			document.getElementById("stateTime").value="";
			document.getElementById("endTime").value="";
			document.getElementById("businessNumber").value="";
		}
		function importData() {
			location.href="importBusSele.action?tradAccount="+document.getElementById("tradAccount").value+"&clientName="+document.getElementById("clientName").value+"&stateTime="+document.getElementById("stateTime").value+"&endTime="+document.getElementById("endTime").value;
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
    <li><a href="#">客户成交查询</a></li>
    </ul>
    </div>    
    <div class="rightinfo">  
    <div >  
    	<form action="" method="post">
    		交易帐号:<input type="text" id="tradAccount" class="defaultinput" value="<%=tradcount %>" name="searchBusinessSele.tradAccount">
    		客户名称:<input type="text" id="clientName" class="defaultinput" value="<%=clientname %>" name="searchBusinessSele.clientName">
    		成交单号:<input type="text" id="businessNumber" class="defaultinput" value="<%=bussnumber %>" name="searchBusinessSele.businessNumber">
    		开始时间:<input type="text" id="stateTime" value="<%=starttime %>" onclick="SetDate(this,'yyyy-MM-dd')" class="defaultinput" name="searchBusinessSele.stateTime">
    		结束时间:<input type="text" id="endTime" value="<%=endtime %>" onclick="SetDate(this,'yyyy-MM-dd')" class="defaultinput" name="searchBusinessSele.endTime">
    		<input type="submit" id="sub" class="search" onclick="search();" name="sub" value="搜索">
    		<a href="#" class="a_reset" onclick="Reset();">重置</a> 
    		<a href="#" class="a_reset" onclick="importData();">导出数据</a>
    	</form>          
    </div>
    <div style="width:100%;overflow-x: scroll; overflow-y: hidden;">
    <table class="tablelist" style="table-layout: fixed;" >
    	<thead>
    	<tr style="display: -webkit-flex;">
        <th style="text-align:center;width:100px;">结算日期</th>
        <th style="text-align:center;width:100px;">交易账号</th>
        <th style="text-align:center;width:100px;">客户名称</th>
        <th style="text-align:center;width:100px;">建/平仓</th>
        <th style="text-align:center;width:100px;">成交单号</th>
        <th style="text-align:center;width:100px;">委托单号</th>
        <th style="text-align:center;width:100px;">持仓单号</th>
        <th style="text-align:center;width:200px;">成交时间</th>
        <th style="text-align:center;width:100px;">商品</th>
        <th style="text-align:center;width:100px;">成交量</th>
        <th style="text-align:center;width:100px;">成交金额</th>
        <th style="text-align:center;width:100px;">买卖方向</th>
        <th style="text-align:center;width:100px;">建仓价</th>
        <th style="text-align:center;width:100px;">持仓价</th>
        <th style="text-align:center;width:100px;">平仓价</th>
        <th style="text-align:center;width:100px;">平仓盈亏</th>
        <th style="text-align:center;width:100px;">实际盈亏</th>
        <th style="text-align:center;width:100px;">手续费</th>
        <th style="text-align:center;width:100px;">成交类型</th>
        <th style="text-align:center;width:100px;">操作类型</th>
        <th style="text-align:center;width:150px;">操作人</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="p" items="${businessselelist}">
        	<tr style="display: -webkit-flex;">
		        <td style="text-align:center;width:100px;">${p.balanceDate}</td>
		        <td style="text-align:center;width:100px;">${p.tradeAccount}</td>
		        <td style="text-align:center;width:100px;">${p.customerName}</td>
		        <td style="text-align:center;width:100px;">${p.buildFlatPrice}</td>
		        <td style="text-align:center;width:100px;">${p.turnoverNumbers}</td>
		        <td style="text-align:center;width:100px;">${p.entrustNumbers}</td>
		        <td style="text-align:center;width:100px;">${p.positionNumbers}</td>
		        <td style="text-align:center;width:200px;">${p.turnoverTime}</td>
		        <td style="text-align:center;width:100px;">${p.commodity}</td>
		        <td style="text-align:center;width:100px;">${p.turnoverAmount}</td>
		        <td style="text-align:center;width:100px;">${p.turnoverMoney}</td>
		        <td style="text-align:center;width:100px;">${p.shopDirection}</td>
		        <td style="text-align:center;width:100px;">${p.buildPrice}</td>
		        <td style="text-align:center;width:100px;">${p.positionPrice}</td>
		        <td style="text-align:center;width:100px;">${p.flatPrice}</td>
		        <td style="text-align:center;width:100px;">${p.flatProfit}</td>
		        <td style="text-align:center;width:100px;">${p.realityProfit}</td>
		        <td style="text-align:center;width:100px;">${p.poundage}</td>
		        <td style="text-align:center;width:100px;">${p.turnoverType}</td>
		        <td style="text-align:center;width:100px;">${p.operatorType}</td>
		        <td style="text-align:center;width:150px;">${p.operator}</td>
	        </tr>
        </c:forEach>                             
        </tbody>
    </table> 
     </div>    
    <div class="pagin">
    	<div class="message" style="width:50%;">共<i class="blue"><%=TotalCount %></i>条记录，当前显示第&nbsp;<i class="blue"><%=Page %>&nbsp;</i>页,共&nbsp;<i class="blue"><%=PageCount %>&nbsp;</i>页</div>
    	 <ul class="paginList">
        	<li class="paginItem"><a href = "findBusinessSelePage.action?Page=1&tradAccount=<%=tradcount %>&clientName=<%=clientname %>&stateTime=<%=starttime %>&endTime=<%=endtime %>&businessNumber=<%=bussnumber %>" style="width:40px;" >首页</a>
               <%if(Page!=1){%> 
               	<a href = "findBusinessSelePage.action?Page=<%=Page-1%>&tradAccount=<%=tradcount %>&clientName=<%=clientname %>&stateTime=<%=starttime %>&endTime=<%=endtime %>&businessNumber=<%=bussnumber %>" style="" >上一页</a>	
               	
               <% }else{%>
               	<a href = "findBusinessSelePage.action?Page=<%=Page-1%>&tradAccount=<%=tradcount %>&clientName=<%=clientname %>&stateTime=<%=starttime %>&endTime=<%=endtime %>&businessNumber=<%=bussnumber %>" onclick="return false" style="background-color:#ccc;" >上一页</a>
               <% }%>
               <%
               if(Page!=PageCount){%>
               	<a href = "findBusinessSelePage.action?Page=<%=Page+1 %>&tradAccount=<%=tradcount %>&clientName=<%=clientname %>&stateTime=<%=starttime %>&endTime=<%=endtime %>&businessNumber=<%=bussnumber %>" style=" ">下一页</a>
               <%}else{%>
               	<a href = "findBusinessSelePage.action?Page=<%=Page+1 %>&tradAccount=<%=tradcount %>&clientName=<%=clientname %>&stateTime=<%=starttime %>&endTime=<%=endtime %>&businessNumber=<%=bussnumber %>" onclick="return false" style="background-color:#ccc;" style="">下一页</a>
               <%}%>
               <a href = "findBusinessSelePage.action?Page=<%=PageCount %>&tradAccount=<%=tradcount %>&clientName=<%=clientname %>&stateTime=<%=starttime %>&endTime=<%=endtime %>&businessNumber=<%=bussnumber %>" style=" ">尾页</a></li>
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
