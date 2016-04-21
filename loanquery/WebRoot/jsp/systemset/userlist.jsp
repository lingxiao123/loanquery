<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
int TotalCount=Integer.parseInt(session.getAttribute("TotalCount").toString());
int Page=Integer.parseInt(session.getAttribute("Page").toString());
int PageCount=Integer.parseInt(session.getAttribute("PageCount").toString());
String username="";
try{
	 if(session.getAttribute("username").toString()==""){
		response.sendRedirect(request.getContextPath()
				+ "/login.jsp");
	}else{
		username=session.getAttribute("username").toString();
	}
}catch(Exception e){
	response.sendRedirect(request.getContextPath()
				+ "/login.jsp");
}
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
	<script type="text/javascript">
		function deleteuser(obj) {
			if(window.confirm("您确认要删除该记录吗？")){
				$.ajax({
					url:"deleteUser.action",
					data:{"id":obj},
					type:"post",
			        async: false,
			        dataType: "json",
					success:function(data){
						if(data.success==true){
							alert("删除成功！");
							window.location.href="findUser.action";
						}
					}			
				});
			}
		}
	</script>
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
    	<c:forEach var="i" items="${ralist}">
    		<c:if test="${i.ra_purviewname=='添加用户'}">
    			 <li class="click"><a href="linkUser.action" target="rightFrame"><span><img src="images/t01.png" /></span>添加</a></li>
    		</c:if>
    	</c:forEach>
        </ul>          
    </div>   
    <table class="tablelist">
    	<thead>
    	<tr>
        <th><input name="" type="checkbox" value="" checked="checked"/></th>
        <th style="width:50px;">编号<i class="sort"><!--<img src="images/px.gif" />  --></i></th>
        <th style="text-align:center;">用户名</th>
        <th style="text-align:center;">类型</th>
        <th style="text-align:center;">状态</th>
        <th style="text-align:center;">操作</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="u" items="${userList}">
        	<tr>
		        <td style="width:50px;"><input name="" type="checkbox" value="" /></td>
		        <td style="text-align:center;">${u.user_id}</td>
		        <td style="text-align:center;">${u.user_name}</td>
		        <td style="text-align:center;">${u.user_roleName}</td>
		        <!--<c:if test="${u.user_role==1}">
		        	<td style="text-align:center;">管理员</td>
		        </c:if>
		        <c:if test="${u.user_role==2}">
		        	<td style="text-align:center;">管理员</td>
		        </c:if>-->
		        <c:if test="${u.user_status==0}">
		        	<td style="text-align:center;"><a href="updateUser.action?id=${u.user_id}&status=1" style="text-decoration:none;color:blue;">禁用</a></td>
		        </c:if>
		        <c:if test="${u.user_status==1}">
		        	<td style="text-align:center;"><a href="updateUser.action?id=${u.user_id}&status=0" style="text-decoration:none;color:blue;">启用</a></td>
		        </c:if>
		        <td style="text-align:center;">
		        	<a href="linkUpdateUser.action?id=${u.user_id}" style="text-decoration:none;color:blue;margin-right: 10px;">修改</a>
		        	<%if(username.contains("admin")){ %>
		        		<a onclick="deleteuser(${u.user_id})" href="#" style="text-decoration:none;color:blue;margin-right: 10px;">删除</a>
		        	<%} %>
		        </td>		        
	        </tr>
        </c:forEach>                             
        </tbody>
    </table>     
    <div class="pagin">
    	<div class="message" style="width:50%;">共<i class="blue"><%=TotalCount %></i>条记录，当前显示第&nbsp;<i class="blue"><%=Page %>&nbsp;</i>页,共&nbsp;<i class="blue"><%=PageCount %>&nbsp;</i>页</div>
    	 <ul class="paginList">
        	<li class="paginItem"><a href = "findUser.action?Page=1" style="width:40px;" >首页</a>
               <%if(Page!=1){%> 
               	<a href = "findUser.action?Page=<%=Page-1%>" style="" >上一页</a>	
               	
               <% }else{%>
               	<a href = "findUser.action?Page=<%=Page-1%>" onclick="return false" style="background-color:#ccc;" >上一页</a>
               <% }%>
               <%
               if(Page!=PageCount){%>
               	<a href = "findUser.action?Page=<%=Page+1 %>" style=" ">下一页</a>
               <%}else{%>
               	<a href = "findUser.action?Page=<%=Page+1 %>" onclick="return false" style="background-color:#ccc;" style="">下一页</a>
               <%}%>
               <a href = "findUser.action?Page=<%=PageCount %>" style=" ">尾页</a></li>
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
