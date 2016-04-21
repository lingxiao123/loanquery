<%@page import="com.sun.java.swing.plaf.windows.resources.windows"%>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
String path = request.getContextPath();
String roleid=null;
String base=null;
String sys=null;
String imports=null;
String data=null;
	try{
		if(session==null){
			response.sendRedirect(request.getContextPath()
					+ "/login.jsp");
		}
		if(session.getAttribute("base").toString()==""){
			response.sendRedirect(request.getContextPath()
					+ "/login.jsp");
		}else{
			base=session.getAttribute("base").toString();
		}
		if(session.getAttribute("sys").toString()==""){
			response.sendRedirect(request.getContextPath()
					+ "/login.jsp");
		}else{
			sys=session.getAttribute("sys").toString();
		}
		if(session.getAttribute("import").toString()==""){
			response.sendRedirect(request.getContextPath()
					+ "/login.jsp");
		}else{
			imports=session.getAttribute("import").toString();
		}
		if(session.getAttribute("data").toString()==""){
			response.sendRedirect(request.getContextPath()
					+ "/login.jsp");
		}else{
			data=session.getAttribute("data").toString();
		}
	}catch(Exception e){
		response.sendRedirect(request.getContextPath()
					+ "/login.jsp");
	}
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title></title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<link href="<%=path %>/css/style.css" rel="stylesheet" type="text/css" />
	<script type="text/javascript" src="<%=path %>/js/jquery.js" ></script>
	<script type="text/javascript">
	$(function(){	
		//导航切换
		$(".menuson .header").click(function(){
			var $parent = $(this).parent();
			$(".menuson>li.active").not($parent).removeClass("active open").find('.sub-menus').hide();
			
			$parent.addClass("active");
			if(!!$(this).next('.sub-menus').size()){
				if($parent.hasClass("open")){
					$parent.removeClass("open").find('.sub-menus').hide();
				}else{
					$parent.addClass("open").find('.sub-menus').show();	
				}
				
				
			}
		});
		
		// 三级菜单点击
		$('.sub-menus li').click(function(e) {
	        $(".sub-menus li.active").removeClass("active");
			$(this).addClass("active");
	    });
		
		$('.title').click(function(){
			var $ul = $(this).next('ul');
			$('dd').find('.menuson').slideUp();
			if($ul.is(':visible')){
				$(this).next('.menuson').slideUp();
			}else{
				$(this).next('.menuson').slideDown();
			}
		});
	});	
</script>
  </head>
  <body style="background:#f0f9fd;">
  	<div class="lefttop"><span></span>个代查询系统</div>    
    <dl class="leftmenu">
    <%if(data=="true") {%>
    	<dd>
	    <div class="title">
	    <span><img src="<%=path %>/images/leftico02.png" /></span>个代数据查询
	    </div>
		    <ul class="menuson">
		    	<c:forEach var="i" items="${list_role}">
		    		<c:if test="${i.ra_purviewname=='客户持仓查询'}">
		    				<li><cite></cite><a href="findPositionSele.action" target="rightFrame">客户持仓查询</a><i></i></li>
		    		</c:if>
		    		<c:if test="${i.ra_purviewname=='客户成交查询'}">
		    				<li><cite></cite><a href="findBusinessSele.action" target="rightFrame">客户成交查询</a><i></i></li>
		    		</c:if>
		    		<c:if test="${i.ra_purviewname=='客户委托单查询'}">
		    				<li><cite></cite><a href="findEntrustSele.action" target="rightFrame">客户委托单查询</a><i></i></li>
		    		</c:if>
		    		<c:if test="${i.ra_purviewname=='客户资金流水查询'}">
		    				<li><cite></cite><a href="findFundStreamSele.action" target="rightFrame">客户资金流水查询</a><i></i></li>
		    		</c:if>
		    		<c:if test="${i.ra_purviewname=='客户资金查询'}">
		    				<li><cite></cite><a href="findCustomFund.action" target="rightFrame">客户资金查询</a><i></i></li>
		    		</c:if>
		    		<c:if test="${i.ra_purviewname=='客户资金状况表'}">
		    				<li><cite></cite><a href="findStatusFund.action" target="rightFrame">客户资金状况表</a><i></i></li>
		    		</c:if>
		    		<c:if test="${i.ra_purviewname=='客户持仓汇总表'}">
		    				<li><cite></cite><a href="findPositionGather.action" target="rightFrame">客户持仓汇总表</a><i></i></li>
		    		</c:if>
		    		<c:if test="${i.ra_purviewname=='客户成交汇总表'}">
		    				<li><cite></cite><a href="findBusinessGather.action" target="rightFrame">客户成交汇总表</a><i></i></li>
		    		</c:if>
		    	</c:forEach>
		    </ul>     
	    </dd> 
    <%} %>
    <%if(base=="true"){ %>
    	<dd>
		    <div class="title">
		    <span><img src="<%=path %>/images/leftico02.png" /></span>基础数据管理
		    </div>
		    <ul class="menuson">
		    	<c:forEach var="i" items="${list_role}">
		    		<c:if test="${i.ra_purviewname=='居间商'}">
		    			<li><cite></cite><a href="findOrgInfo.action" target="rightFrame">居间商</a><i></i></li>
		    		</c:if>
		    		<c:if test="${i.ra_purviewname=='个代'}">
		    			<li><cite></cite><a href="findLoan.action" target="rightFrame">个代</a><i></i></li>
		    		</c:if>
		    		<c:if test="${i.ra_purviewname=='终端客户'}">
		    			<li><cite></cite><a href="findLoanmember.action" target="rightFrame">终端客户</a><i></i></li>
		    		</c:if>
		    	</c:forEach>
		    </ul>     
    	</dd> 
    <%} %>
    <%if(imports=="true"){ %>
    	<dd>
    		<div class="title"><span><img src="<%=path %>/images/leftico03.png" /></span>数据导入</div>
		    <ul class="menuson">
		    	<c:forEach var="i" items="${list_role}">
		    		<c:if test="${i.ra_purviewname=='客户持仓数据'}">
		    			<li><cite></cite><a href="findData.action" target="rightFrame">客户持仓数据</a><i></i></li>
		    		</c:if>
		    	</c:forEach>
		    </ul>    
    	</dd>
    <%} %>
   	<%if(sys=="true"){ %>
   		<dd><div class="title"><span><img src="<%=path %>/images/leftico04.png" /></span>系统设置</div>
		    <ul class="menuson">
			    <c:forEach var="i" items="${list_role}">
			    	<c:if test="${i.ra_purviewname=='用户管理'}">
			    		<li><cite></cite><a href="findUser.action" target="rightFrame">用户管理</a><i></i></li>
			    	</c:if>
			    	<c:if test="${i.ra_purviewname=='角色管理'}">
			    		<li><cite></cite><a href="findRole.action" target="rightFrame">角色管理</a><i></i></li>
			    	</c:if>
			    	<c:if test="${i.ra_purviewname=='权限信息'}">
			    		<li><cite></cite><a href="findPurview.action" target="rightFrame">权限信息</a><i></i></li>
			    	</c:if>
			    </c:forEach>
		    </ul>
    	</dd>
   		<%}%>
    </dl>
  </body>
</html>
