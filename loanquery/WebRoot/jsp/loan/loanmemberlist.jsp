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
String merber="";
String loanname="";
String tradaccount="";
try{
	if(session.getAttribute("role").toString()==""){
		response.sendRedirect(request.getContextPath()
				+ "/login.jsp");
	}else{
		role=session.getAttribute("role").toString();
	}
	merber=session.getAttribute("merber").toString();
	loanname=session.getAttribute("loanname").toString();
	tradaccount=session.getAttribute("tradaccount").toString();
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
		$(function(){
			$("#loan_orgAbbreviation").autocomplete({
                source: function (request, response) {
                    $.ajax({
                        url: "AutocompleteOrg.action",  //要查询的Action
	                    type: "POST",  //Post提交
	                    dataType: "json", //json格式数据，默认是text
	                    data: { keyword: request.term }, //参数，不知道为什么？请指点
	                    success: function (data) {
	                        response($.map(data, function (item) {
	                            return { label: item[0].text, value: item[0].value }; //将返回的jsonresult的属性赋值给autocomplete item
	                        }));
	                    }
	                });
	            },
            	focus: function (event, ui) {
            	    $("#loan_orgAbbreviation").val(ui.item.label); //选中item的文本
            	    return false;
            	},
            	select: function (event, ui) {
            	    $("#loan_orgAbbreviation").val(ui.item.label);  //选中item的文本
            	    $("#loan_orgId").val(ui.item.value); //选中item的值
            	    return false;
            	}
            });
		});
		function deletemember(obj) {
			if(window.confirm("您确认要删除该记录吗？")){
				$.ajax({
					url:"deleteLoanMember.action",
					type:"post",
					data:{"id":obj},
					async:false,
					dataType:"json",
					success:function(data){
						if(data.success==true){
							alert("删除成功！");
							window.location.href="findLoanmember.action";
						}
					}
				});
			}
		}
		function search() {
			var f=document.getElementsByTagName("form")[0];
			f.action="searchLoanmerber.action";		
		}
	</script>
  </head> 
  <body>
  	<div class="place">
    <span>位置：</span>
    <ul class="placeul">
    <li><a href="#">首页</a></li>
    <li><a href="#">基础数据管理</a></li>
    <li><a href="#">终端客户信息</a></li>
    </ul>
    </div>    
    <div class="rightinfo">  
    <div class="tools">  
    	<ul class="toolbar">
        <li class="click"><a href="linkLoanmember.action" target="rightFrame"><span><img src="images/t01.png" /></span>添加</a></li>
        </ul>
        <div style="text-align:right;line-height:35px;height:35px;">
        <% if(role=="系统用户"||role=="居间商") {%>
	       	<form action="" method="post">
	        	终端名称:<input type="text" class="defaultinput" name="searchLoanMerber.lm_name" value="<%=merber%>">
	        	交易账号:<input type="text" class="defaultinput" name="searchLoanMerber.lm_tradAccount" value="<%=tradaccount%>">
	        	所属个代:<input type="text" class="defaultinput" name="searchLoanMerber.lm_loanname" value="<%=loanname%>">
	        	<input type="submit" id="sub" class="search" onclick="search();" name="sub" value="搜索">
	        </form>
	    <%}%> 
	    <% if(role=="个人主管") {%>
	       	<form action="" method="post">
	        	终端名称:<input type="text" class="defaultinput" name="searchLoanMerber.lm_name" value="<%=merber%>">
	        	交易账号:<input type="text" class="defaultinput" name="searchLoanMerber.lm_tradAccount" value="<%=tradaccount%>">
	        	<input type="submit" id="sub" class="search" onclick="search();" name="sub" value="搜索">
	        </form>
	    <%}%> 
        </div>         
    </div>   
    <table class="tablelist">
    	<thead>
    	<tr>
        <th><input name="" type="checkbox" value="" checked="checked"/></th>
        <th style="text-align:center;">终端客户名称</th>
        <th style="text-align:center;">交易账号</th>
        <th style="text-align:center;">手机号码</th>
        <th style="text-align:center;">所属个代</th>
        <th style="text-align:center;">添加时间</th>
        <th style="text-align:center;">操作</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="u" items="${memberlist}">
        	<tr>
		        <td style="text-align:center;">${u.lm_id}</td>
		        <td style="text-align:center;">${u.lm_name}</td>
		         <td style="text-align:center;">${u.lm_tradAccount}</td>
		        <td style="text-align:center;">${u.lm_phoneNumber}</td>
		        <td style="text-align:center;">${u.lm_loanname}</td>
		        <td style="text-align:center;">${u.lm_addTime}</td>
		        <td style="text-align:center;"><a href="linkUpdateLoanMember.action?id=${u.lm_id}" style="text-decoration: none;color: blue;margin-right: 10px;">修改</a><a onclick="deletemember(${u.lm_id})" href="#" style="text-decoration: none;color: blue;cursor:pointer;margin-right: 10px;">删除</a></td>      
	        </tr>
        </c:forEach>                             
        </tbody>
    </table>     
    <div class="pagin">
    	<div class="message" style="width:50%;">共<i class="blue"><%=TotalCount %></i>条记录，当前显示第&nbsp;<i class="blue"><%=Page %>&nbsp;</i>页,共&nbsp;<i class="blue"><%=PageCount %>&nbsp;</i>页</div>
    	 <ul class="paginList">
        	<li class="paginItem"><a href = "findLoanmemberPage.action?Page=1&tradAccount=<%=tradaccount %>&merber=<%=merber %>&loanname=<%=loanname %>" style="width:40px;" >首页</a>
               <%if(Page!=1){%> 
               	<a href = "findLoanmemberPage.action?Page=<%=Page-1%>&tradAccount=<%=tradaccount %>&merber=<%=merber %>&loanname=<%=loanname %>" style="" >上一页</a>	
               	
               <% }else{%>
               	<a href = "findLoanmemberPage.action?Page=<%=Page-1%>&tradAccount=<%=tradaccount %>&merber=<%=merber %>&loanname=<%=loanname %>" onclick="return false" style="background-color:#ccc;" >上一页</a>
               <% }%>
               <%
               if(Page!=PageCount){%>
               	<a href = "findLoanmemberPage.action?Page=<%=Page+1 %>&tradAccount=<%=tradaccount %>&merber=<%=merber %>&loanname=<%=loanname %>" style=" ">下一页</a>
               <%}else{%>
               	<a href = "findLoanmemberPage.action?Page=<%=Page+1 %>&tradAccount=<%=tradaccount %>&merber=<%=merber %>&loanname=<%=loanname %>" onclick="return false" style="background-color:#ccc;" style="">下一页</a>
               <%}%>
               <a href = "findLoanmemberPage.action?Page=<%=PageCount %>&tradAccount=<%=tradaccount %>&merber=<%=merber %>&loanname=<%=loanname %>" style=" ">尾页</a></li>
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
