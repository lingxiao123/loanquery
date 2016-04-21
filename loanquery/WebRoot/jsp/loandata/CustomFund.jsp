<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
	String tradacount="";
	String clientname="";
	try{
		if(session==null){
			response.sendRedirect(request.getContextPath()
					+ "/login.jsp");
		}
		tradacount=session.getAttribute("tradaccount").toString();
		clientname=session.getAttribute("clientname").toString();
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
	<script type="text/javascript">
		function search() {
			var f=document.getElementsByTagName("form")[0];
			f.action="searchCustomFund.action";		
		}
		function Reset() {
			document.getElementById("tradAccount").value="";
			document.getElementById("clientName").value="";
		}
		function importData() {
			location.href="importCustomFund.action?tradAccount="+document.getElementById("tradAccount").value+"&clientName="+document.getElementById("clientName").value;
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
    <li><a href="#">客户资金查询</a></li>
    </ul>
    </div>    
    <div class="rightinfo">  
    <div>  
    	<form action="" method="post">
    		交易帐号:<input type="text" id="tradAccount" value="<%=tradacount %>" class="defaultinput" name="searchCustomFund.tradAccount">
    		客户名称:<input type="text" id="clientName" value="<%=clientname %>" class="defaultinput" name="searchCustomFund.clientName">
    		<input type="submit" id="sub" class="search" onclick="search();" name="sub" value="搜索">
    		<a href="#" class="a_reset" onclick="Reset();">重置</a>
    		<a href="#" class="a_reset" onclick="importData();">导出数据</a> 
    	</form>          
    </div>           
    </div>
    <div style="width:100%;overflow-x: scroll; overflow-y: hidden;">
    <table class="tablelist" style="table-layout: fixed;">
    	<thead>
    	<tr style="display: -webkit-flex;">
        <th style="text-align:center;width:100px;">交易账号</th>
        <th style="text-align:center;width:100px;">客户名称</th>
        <th style="text-align:center;width:100px;">期初权益</th>
        <th style="text-align:center;width:100px;">出入金</th>
        <th style="text-align:center;width:100px;">平仓盈亏</th>
        <th style="text-align:center;width:100px;">持仓盈亏</th>
        <th style="text-align:center;width:100px;">手续费</th>
        <th style="text-align:center;width:100px;">占用保证金</th>
        <th style="text-align:center;width:100px;">可用保证金</th>
        <th style="text-align:center;width:100px;">冻结保证金</th>
        <th style="text-align:center;width:100px;">冻结手续费</th>
        <th style="text-align:center;width:100px;">当前权益</th>
        <th style="text-align:center;width:100px;">交收保证金</th>
        <th style="text-align:center;width:100px;">交收手续费</th>
        <th style="text-align:center;width:100px;">交收货款</th>
        <th style="text-align:center;width:100px;">交收税款</th>
        <th style="text-align:center;width:100px;">违约金</th>
        <th style="text-align:center;width:100px;">风险率</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="p" items="${list}">
        	<tr style="display: -webkit-flex;">
		        <td style="text-align:center;width:100px;">${p.tradeAccount}</td>
		        <td style="text-align:center;width:100px;">${p.customerName}</td>
		        <td style="text-align:center;width:100px;">${p.primeBenefit}</td>
		        <td style="text-align:center;width:100px;">${p.inandoutMoney}</td>
		        <td style="text-align:center;width:100px;">${p.flatProfit}</td>
		        <td style="text-align:center;width:100px;">${p.positionProfit}</td>
		        <td style="text-align:center;width:100px;">${p.poundage}</td>
		        <td style="text-align:center;width:100px;">${p.occupyBail}</td>
		        <td style="text-align:center;width:100px;">${p.usableBail}</td>
		        <td style="text-align:center;width:100px;">${p.freezeBail}</td>
		        <td style="text-align:center;width:100px;">${p.freezePoundage}</td>
		        <td style="text-align:center;width:100px;">${p.presentIncome}</td>
		        <td style="text-align:center;width:100px;">${p.dealBail}</td>
		        <td style="text-align:center;width:100px;">${p.dealPoundage}</td>
		        <td style="text-align:center;width:100px;">${p.dealLoan}</td>
		        <td style="text-align:center;width:100px;">${p.dealPenalty}</td>
		        <td style="text-align:center;width:100px;">${p.penaltyMoney}</td>
		        <td style="text-align:center;width:100px;">${p.riskRate}</td>
	        </tr>
        </c:forEach>                             
        </tbody>
    </table>
    </div>        
    <div class="pagin">
    	<div class="message" style="width:50%;">共<i class="blue"><%=TotalCount %></i>条记录，当前显示第&nbsp;<i class="blue"><%=Page %>&nbsp;</i>页,共&nbsp;<i class="blue"><%=PageCount %>&nbsp;</i>页</div>
	   	 <ul class="paginList">
	       	<li class="paginItem"><a href = "findCustomFundPage.action?Page=1&tradAccount=<%=tradacount %>&clientName=<%=clientname %>" style="width:40px;" >首页</a>
	              <%if(Page!=1){%> 
	              	<a href = "findCustomFundPage.action?Page=<%=Page-1%>&tradAccount=<%=tradacount %>&clientName=<%=clientname %>" style="" >上一页</a>	
	              	
	              <% }else{%>
	              	<a href = "findCustomFundPage.action?Page=<%=Page-1%>&tradAccount=<%=tradacount %>&clientName=<%=clientname %>" onclick="return false" style="background-color:#ccc;" >上一页</a>
	              <% }%>
	              <%
	              if(Page!=PageCount){%>
	              	<a href = "findCustomFundPage.action?Page=<%=Page+1 %>&tradAccount=<%=tradacount %>&clientName=<%=clientname %>" style=" ">下一页</a>
	              <%}else{%>
	              	<a href = "findCustomFundPage.action?Page=<%=Page+1 %>&tradAccount=<%=tradacount %>&clientName=<%=clientname %>" onclick="return false" style="background-color:#ccc;" style="">下一页</a>
	              <%}%>
	              <a href = "findCustomFundPage.action?Page=<%=PageCount %>&tradAccount=<%=tradacount %>&clientName=<%=clientname %>" style=" ">尾页</a></li>
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
