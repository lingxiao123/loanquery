<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
int TotalCount=Integer.parseInt(session.getAttribute("TotalCount").toString());
int Page=Integer.parseInt(session.getAttribute("Page").toString());
int PageCount=Integer.parseInt(session.getAttribute("PageCount").toString());
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>用户列表</title> 
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link rel="stylesheet" type="text/css" href="<%=path %>/css/style.css">
	<script type="text/javascript" src="<%=path %>/js/jquery.min.1.8.1.js" ></script>
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
  </head> 
  <body>
  	<div class="place">
    <span>位置：</span>
    <ul class="placeul">
    <li><a href="#">首页</a></li>
    <li><a href="#">系统设置</a></li>
    <li><a href="#">用户管理</a></li>
    </ul>
    </div>    
    <div class="rightinfo">  
    <div class="tools">  
    	<ul class="toolbar">
        <li class="click"><a href="linkPurview.action" target="rightFrame"><span><img src="images/t01.png" /></span>添加</a></li>
        </ul>         
    </div>   
    <table class="tablelist">
    	<thead>
    	<tr>
        <th><input name="" type="checkbox" value="" checked="checked"/></th>
        <th style="text-align:center;">模块名称</th>
        <th style="text-align:center;">菜单名称</th>
        <th style="text-align:center;">菜单路径</th>
        <th style="text-align:center;">按钮类型</th>
        <th style="text-align:center;">权限类型</th>
        <th style="text-align:center;">操作</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="u" items="${purviewlist}">
        	<tr>
		        <td style="width:50px;"><input name="" type="checkbox" value="" /></td>
		        <td style="text-align:center;">${u.p_modulename}</td>
		        <td style="text-align:center;">${u.p_menuname}</td>
		        <td style="text-align:center;">${u.p_menuurl}</td>
		        <td style="text-align:center;">${u.p_btntype}</td>
		        <td style="text-align:center;">${u.p_type}</td>
		        <td style="text-align:center;"><a href="deletePurview.action?id=${u.p_id}" style="text-decoration:none;color:blue;margin-right:5px;">删除</a></td>
	        </tr>
        </c:forEach>                             
        </tbody>
    </table>     
    <div class="pagin">
    	<div class="message" style="width:50%;">共<i class="blue"><%=TotalCount %></i>条记录，当前显示第&nbsp;<i class="blue"><%=Page %>&nbsp;</i>页,共&nbsp;<i class="blue"><%=PageCount %>&nbsp;</i>页</div>
    	 <ul class="paginList">
        	<li class="paginItem"><a href = "findPurview.action?Page=1" style="width:40px;" >首页</a>
               <%if(Page!=1){%> 
               	<a href = "findPurview.action?Page=<%=Page-1%>" style="" >上一页</a>	
               	
               <% }else{%>
               	<a href = "findPurview.action?Page=<%=Page-1%>" onclick="return false" style="background-color:#ccc;" >上一页</a>
               <% }%>
               <%
               if(Page!=PageCount){%>
               	<a href = "findPurview.action?Page=<%=Page+1 %>" style=" ">下一页</a>
               <%}else{%>
               	<a href = "findPurview.action?Page=<%=Page+1 %>" onclick="return false" style="background-color:#ccc;" style="">下一页</a>
               <%}%>
               <a href = "findPurview.action?Page=<%=PageCount %>" style=" ">尾页</a></li>
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