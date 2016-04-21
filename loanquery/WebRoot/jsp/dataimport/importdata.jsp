<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
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
		function add() {
			var imgval = $('#fileField').val();
			if ("" == imgval) {
				alert("请选择文件！");
				return;
			} else {
				if (-1 == imgval.indexOf('.xls')) {
					 if(-1 == imgval.indexOf('.csv')){
						 alert("请选择正确的文件格式(.xls 、.xlsx 或 .csv)!");
						 return;
					 }
				}
				var fileInput = $("#fileField")[0];
		           byteSize  = fileInput.size;//Byte
		        if( byteSize > (100 * 1024 * 1024)){
		        	alert("上传文件最大值为100M!");
		        	return;
		        }
			}
			/*
			$("#fm_fileinfo_info").form('submit', {
				url:'importData.action',
				timeout: 30000,
				success:function(data){
					
				}
			});
			$.ajax({
				url:"importData.action",
				type:post,
				success:function(data){
					
				}
			});*/
			var f=document.getElementsByTagName("form")[0];
			f.action="importData.action";
		}
	</script>
  </head> 
  <body>
  	<div class="place">
    <span>位置：</span>
    <ul class="placeul">
    <li><a href="#">首页</a></li>
    <li><a href="#">数据导入</a></li>
    <li><a href="#">导入数据</a></li>
    </ul>
    </div>
    <form id="fm_fileinfo_info" method="post" enctype="multipart/form-data">   
    <div class="formbody">  
    <div class="formtitle"><span>导入数据</span></div>  
    <ul class="forminfo">
    <li><label>导入文件</label><input type="file" id="fileField" name="fileField"><i></i></li>
    <li><label>&nbsp;</label><input name="" type="submit" class="btn" onclick="add();" value="上传"/>&nbsp;&nbsp;<input name="" type="button" style="background-color:green;" onclick="javascript:history.go(-1);" class="btn" value="返回"/></li>
    </ul>
    </div>
    </form>
  </body>
</html>
