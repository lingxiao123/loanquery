<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
String roleid=null;
try{
	if(session==null){
		response.sendRedirect(request.getContextPath()
				+ "/login.jsp");
	}else if(session.getAttribute("id").toString()==""){
		response.sendRedirect(request.getContextPath()
				+ "/login.jsp");
	}else{
		roleid=session.getAttribute("id").toString();
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
	<link rel="stylesheet" type="text/css" href="<%=path %>/css/style.css">
	<link rel="stylesheet" type="text/css" href="<%=path %>/css/select.css">
	<script type="text/javascript" src="<%=path %>/js/jquery.min.1.8.1.js" ></script>
	<script type="text/javascript" src="<%=path %>/js/select-ui.min.js" ></script>
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<script type="text/javascript">
		$(document).ready(function(e) {
		    $(".select1").uedSelect({
				width : 345			  
			});
			$(".select2").uedSelect({
				width : 167  
			});
			$(".select3").uedSelect({
				width : 100
			});
		});
	</script>
	<script type="text/javascript">
		function Getqxs() {
         	var cks = [];
         	$("input[name='checkqx'][flag='qx']:checked").each(function (idx,item) {
             	cks.push(item.value);
         	});
         	$("#qxs").val(cks.join(','));
	    }
		function addpurview() {
			var f=document.getElementsByTagName("form")[0];
			Getqxs();
			f.action="addRoleAuthorize.action";
		}
		function CheckAll(flag) {
			if(flag===false){
				$("input[type='checkbox']").each(function(idx,item){
					item.checked=!item.checked;
				});
			}else{
				$("input[type='checkbox']").each(function(idx,item){
					item.checked=true;
				});
			}
		}
	</script>
	<style type="text/css">
		.item{margin-left:15px;}
		.table{width:100%;}
		.table td{border-top:solid 1px #cbcbcb;border-bottom:solid 1px #cbcbcb;border-left:solid 1px #cbcbcb;border-right:solid 1px #cbcbcb;height:85px;}
		.btn_a{width:60px;height:25px;line-height:25px;border-radius:3px;display:inline-block;background-color:green;color:white;text-decoration:none;margin-right: 20px;}
		.btn_b{width:60px;height:25px;line-height:25px;border-radius:3px;display:inline-block;background-color:green;color:white;text-decoration:none;}
	</style>
  </head> 
  <body>
  	<form action="" method="post">
  	<div class="place">
    <span>位置：</span>
    <ul class="placeul">
    <li><a href="#">首页</a></li>
    <li><a href="#">系统设置</a></li>
    <li><a href="#">角色管理</a></li>
    <li><a href="#">角色授权</a></li>
    </ul>
    </div>   
    <div class="formbody">  
    <div class="formtitle"><span>角色授权</span></div>
    <table class="table">
        <tbody>
        	<tr><td style="width:20%;text-align:center;">系统设置</td>
        	<td>
        		<c:forEach var="i" items="${syslist_menu}">
        			<label class="item"><input type="checkbox" flag="qx"  name="checkqx" value="${i.p_menuname }">${i.p_menuname}</label>
        		</c:forEach>
        		<c:forEach var="i" items="${syslist_btn}">
        			<label class="item"><input type="checkbox" flag="qx"  name="checkqx" value="${i.p_btntype }">${i.p_btntype}</label>
        		</c:forEach>
        	</td></tr>
        	<tr><td style="width:20%;text-align:center;">数据导入</td>
        	<td>
        		<c:forEach var="i" items="${importlist_menu}">
        			<label class="item"><input type="checkbox" flag="qx" name="checkqx" value="${i.p_menuname}">${i.p_menuname}</label>
        		</c:forEach>
        		<c:forEach var="i" items="${importlist_btn}">
        			<label class="item"><input type="checkbox" flag="qx"  name="checkqx" value="${i.p_btntype }">${i.p_btntype}</label>
        		</c:forEach>
        	</td></tr>   
        	<tr><td style="width:20%;text-align:center;">基础数据管理</td>
        	<td>
        		<c:forEach var="i" items="${base_menu}">
        			<label class="item"><input type="checkbox" flag="qx" name="checkqx" value="${i.p_menuname}">${i.p_menuname}</label>
        		</c:forEach>
        		<c:forEach var="i" items="${base_btn}">
        			<label class="item"><input type="checkbox" flag="qx"  name="checkqx" value="${i.p_btntype }">${i.p_btntype}</label>
        		</c:forEach>
        	</td></tr>   
        	<tr><td style="width:20%;text-align:center;">个代数据查询</td>
        	<td>
        		<c:forEach var="i" items="${data_menu}">
        			<label class="item"><input type="checkbox" flag="qx" name="checkqx" value="${i.p_menuname }">${i.p_menuname}</label>
        		</c:forEach>
        		<c:forEach var="i" items="${data_btn}">
        			<label class="item"><input type="checkbox" flag="qx"  name="checkqx" value="${i.p_btntype }">${i.p_btntype}</label>
        		</c:forEach>
        	</td></tr>  
        	<tr><td colspan="2" style="height:40px;text-align:center;">
        		<a href="#" class="btn_a" onclick="CheckAll();">全选</a><a href="#" class="btn_b" onclick="CheckAll(false);">反选</a>
        	</td></tr>    
        </tbody>
    </table>
    <div style="width:100%;text-align: center;">
		<input type="hidden" id="roleid" name="roleAuthorize.ra_roleid" value="<%=roleid%>"/>
		<input type="hidden" id="qxs" name="roleAuthorize.ra_purviewname"/> 
    	<input name="" type="submit" class="btn" onclick="addpurview();" value="保存"/>&nbsp;&nbsp;<input name="" type="button" style="background-color:green;" onclick="javascript:history.go(-1);" class="btn" value="返回"/>
    </div>
    <!--     
    <ul class="forminfo">
    <li><label>模块名称</label><input id="modulename" name="purview.p_modulename" type="text" class="dfinput" /><i></i></li>
    <li><label>菜单名称</label><input id="menuname" name="purview.p_menuname"  class="dfinput" /><i></i></li>
    <li><label>菜单路径</label><input id="menuurl" name="purview.p_menuurl" class="dfinput" /><i id="str"></i></li>
    <li><label>按钮类型</label><input id="p_btntype" name="purview.p_btntype" class="dfinput" /></li>
    <li><label>权限类型</label><div class="cityright"><select name="purview.p_type" class="select2"><option value="菜单">菜单</option><option value="功能">功能</option></select></div></li>
    <li><label>&nbsp;</label><input name="" type="submit" class="btn" onclick="addpurview();" value="确认保存"/>&nbsp;&nbsp;<input name="" type="button" style="background-color:green;" onclick="javascript:history.go(-1);" class="btn" value="返回"/></li>
    </ul>--> 
    </div>
    </form>
    <script type="text/javascript">
    $.ajax({
        url: "getRoleAuthorize.action",
        type: "post",
        data:{"id":$("#roleid").val()},
        async: false,
        dataType: "json",
        success: function (data) {
        	var arr = new Array();
        	arr=data.purview.split(",");
        	var purview = document.getElementsByName("checkqx");
        	for (var i = 0; i < purview.length; i++) {
                for (var j = 0; j < arr.length; j++) {
                    if (arr[j] == purview[i].value) {
                        purview[i].checked = true;
                    }
                }
            }
        }
    });
	</script>
  </body>
</html>
