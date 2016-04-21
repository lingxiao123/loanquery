<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
	int TotalCount=Integer.parseInt(session.getAttribute("TotalCount").toString());
	int Page=Integer.parseInt(session.getAttribute("Page").toString());
	int PageCount=Integer.parseInt(session.getAttribute("PageCount").toString());
	String role=null;
	String orgname="";
	String loanname="";
	try{
		if(session.getAttribute("role").toString()==""){
			response.sendRedirect(request.getContextPath()
					+ "/login.jsp");
		}else{
			role=session.getAttribute("role").toString();
		}
		orgname=session.getAttribute("orgname").toString();
		loanname=session.getAttribute("loanname").toString();
	}catch(Exception e){
		response.sendRedirect(request.getContextPath()
					+ "/login.jsp");
	}
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
		function deleteloan(obj) {
			if(window.confirm("确认要删除该记录吗？")){
				$.ajax({
					url:"getLoanMembers.action",
					type:"post",
					data:{"id":obj},
					async:false,
					dataType:"json",
					success:function(data){
						if(data.success==true){
							$.ajax({
								url:"deleteLoan.action",
								type:"post",
								data:{"id":obj},
								async:false,
								dataType:"json",
								success:function(data){
									if(data.success==true){
										alert("删除成功！");
										window.location.href="findLoan.action";
									}
								}
							});
						}else{
							alert("删除失败,当前个代含有成员请先删除成员！");
						}
					}
				});
			}
		}
		function search() {
			var f=document.getElementsByTagName("form")[0];
			f.action="searchLoan.action";		
		}
	</script>
  </head> 
  <body>
  	<div class="place">
    <span>位置：</span>
    <ul class="placeul">
    <li><a href="#">首页</a></li>
    <li><a href="#">基础数据管理</a></li>
    <li><a href="#">个代信息</a></li>
    </ul>
    </div>    
    <div class="rightinfo">  
    <div class="tools">  
    	<ul class="toolbar">
    	<c:forEach var="i" items="${ralist}">
    		<c:if test="${i.ra_purviewname=='添加个代'}">
        	 <li class="click"><a href="linkLoan.action" target="rightFrame"><span><img src="images/t01.png" /></span>添加</a></li>
    		</c:if>
    	</c:forEach>  
        </ul> 
        <div style="text-align:right;line-height:35px;height:35px;">
        	<% if(role=="系统用户"){%>
		       	<form action="" method="post">
		        	个代名称:<input type="text" class="defaultinput" name="searchLoaninfo.loan_name" value="<%=loanname%>">
		        	所属居间:<input type="text" class="defaultinput" name="searchLoaninfo.loan_orgname" value="<%=orgname%>">
		        	<input type="submit" id="sub" class="search" onclick="search();" name="sub" value="搜索">
		        </form>   
        	<%} %>
        	<% if(role=="居间商"){%>
		       	<form action="" method="post">
		        	个代名称:<input type="text" class="defaultinput" name="searchLoaninfo.loan_name" value="<%=loanname%>">
		        	<input type="submit" id="sub" class="search" onclick="search();" name="sub" value="搜索">
		        </form>   
        	<%} %>
        </div>         
    </div>   
    <table class="tablelist">
    	<thead>
    	<tr>
        <th><input name="" type="checkbox" value="" checked="checked"/></th>
        <th style="width:50px;">编号<i class="sort"><!--<img src="images/px.gif" />  --></i></th>
        <th style="text-align:center;">个代名称</th>
        <th style="text-align:center;">交易账号</th>
        <th style="text-align:center;">手机号码</th>
        <th style="text-align:center;">所属居间</th>
        <th style="text-align:center;">添加时间</th>
        <%if(role=="居间商"||role=="系统用户"){ %>
        <th style="text-align:center;">状态</th>
        <%} %>
        <th style="text-align:center;">操作</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="u" items="${loanList}">
        	<tr>
		        <td style="width:50px;"><input name="" type="checkbox" value="${u.loan_id}" /></td>
		        <td style="text-align:center;">${u.loan_id}</td>
		        <td style="text-align:center;">${u.loan_name}</td>
		        <td style="text-align:center;">${u.loan_tradAccount}</td>
		        <td style="text-align:center;">${u.loan_phoneNumber}</td>
		        <td style="text-align:center;">${u.loan_orgname}</td>
		        <td style="text-align:center;">${u.loan_addTime}</td>
		        <%if(role=="居间商"||role=="系统用户"){ %>
			        <c:if test="${u.loan_status==1}">
			        	<td style="text-align:center;"><a href="updateLoanStatus?id=${u.loan_id}&status=0" style="text-decoration: none;color: blue;">启用</a></td>
			        </c:if>
			        <c:if test="${u.loan_status==0}">
			        	<td style="text-align:center;"><a href="updateLoanStatus?id=${u.loan_id}&status=1" style="text-decoration: none;color: blue;">禁用</a></td>
			        </c:if>
		        <%} %>
		        <td style="text-align:center;">
		        	<a href="linkUpdateLoan.action?id=${u.loan_id}" style="text-decoration:none;color:blue;margin-right:10px;">修改</a>
		        	<%if(role=="居间商"||role=="系统用户"){%>
		        		<a onclick="deleteloan(${u.loan_id})"  style="text-decoration:none;color:blue;cursor:pointer; margin-right:10px;">删除</a>
		        	<%} %>
		        </td>     
	        </tr>
        </c:forEach>                             
        </tbody>
    </table>     
    <div class="pagin">
    	<div class="message" style="width:50%;">共<i class="blue"><%=TotalCount %></i>条记录，当前显示第&nbsp;<i class="blue"><%=Page %>&nbsp;</i>页,共&nbsp;<i class="blue"><%=PageCount %>&nbsp;</i>页</div>
    	 <ul class="paginList">
        	<li class="paginItem"><a href = "findLoanPage.action?Page=1&loan_name=<%=loanname %>&loan_orgname=<%=orgname %>" style="width:40px;" >首页</a>
               <%if(Page!=1){%> 
               	<a href = "findLoanPage.action?Page=<%=Page-1%>&loan_name=<%=loanname %>&loan_orgname=<%=orgname %>" style="" >上一页</a>	
               	
               <% }else{%>
               	<a href = "findLoanPage.action?Page=<%=Page-1%>&loan_name=<%=loanname %>&loan_orgname=<%=orgname %>" onclick="return false" style="background-color:#ccc;" >上一页</a>
               <% }%>
               <%
               if(Page!=PageCount){%>
               	<a href = "findLoanPage.action?Page=<%=Page+1 %>&loan_name=<%=loanname %>&loan_orgname=<%=orgname %>" style=" ">下一页</a>
               <%}else{%>
               	<a href = "findLoanPage.action?Page=<%=Page+1 %>&loan_name=<%=loanname %>&loan_orgname=<%=orgname %>" onclick="return false" style="background-color:#ccc;" style="">下一页</a>
               <%}%>
               <a href = "findLoanPage.action?Page=<%=PageCount %>&loan_name=<%=loanname %>&loan_orgname=<%=orgname %>" style=" ">尾页</a></li>
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
