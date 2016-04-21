<%@ page language="java" import="java.util.*,java.text.SimpleDateFormat" pageEncoding="utf-8"%>
<%
	SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");//设置日期格式
	String time=df.format(new Date());// new Date()为获取当前系统时间
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
		function addorginfo() {
			var f=document.getElementsByTagName("form")[0];
			var pwd=document.getElementById("pwd").value;
			var org_FullName=document.getElementById("org_FullName").value;
			var org_Abbreviation=document.getElementById("org_Abbreviation").value;
			if(pwd==""||pwd==null){
				alert("请输入密码！");
				return;
			}else if(org_FullName==""||org_FullName==null){
				alert("请输入居间全称！");
				return;
			}else if(org_Abbreviation==""||org_Abbreviation==null){
				alert("请输入居间简称！");
				return;
			}else if(!checkname()){
				alert("保存失败,请输入正确的信息！");
				return;
			}else{
				f.action="addOrgInfo.action";
			}
		}
		function checkname() {
			var flag=false;
			var obj=document.getElementById("org_Abbreviation").value;
			$.ajax({
				url:"getCheckName.action",
				type:"post",
				data:{"name":obj},
				async:false,
				dataType:"json",
				success:function(data){
					if(data.success==true){
						document.getElementById("str").innerHTML="";
						flag=true;
					}else{
						document.getElementById("str").innerHTML="该名已存在,请重新输入居间简称！";
						flag=false;
					}
				}
			});	
			return flag;
		}
		function checkPhone() {
			var flag=false;
			var phone=document.getElementById("org_PhoneNumber").value;
			if(phone==""){
				document.getElementById("strs").innerHTML="";
				flag= true; 	
			}else{
				var myreg = /^(((13[0-9]{1})|(15[0-9]{1})|(18[0-9]{1}))+\d{8})$/; 
				if(!myreg.test(phone)) 
				{ 
				    document.getElementById("strs").innerHTML="请输入有效的手机号码！";
				    flag= false; 
				}else{
					document.getElementById("strs").innerHTML="";
					flag= true; 
				} 
			}
			return flag;
		}
	</script>
  </head> 
  <body>
  	<form action="" method="post">
  	<div class="place">
    <span>位置：</span>
    <ul class="placeul">
    <li><a href="#">首页</a></li>
    <li><a href="#">居间管理</a></li>
    <li><a href="#">添加居间</a></li>
    </ul>
    </div>   
    <div class="formbody">  
    <div class="formtitle"><span>基本信息</span></div>  
    <ul class="forminfo">
    <li><label><i style="color: red;">*</i>居间全称</label><input id="org_FullName" name="orgInfo.org_FullName" type="text" class="dfinput" /><i></i></li>
    <li><label><i style="color: red;">*</i>居间简称</label><input id="org_Abbreviation" name="orgInfo.org_Abbreviation" onblur="checkname()" type="text" class="dfinput" /><i id="str" style="color: red;"></i></li>
    <li><label><i style="color: red;">*</i>密码</label><input id="pwd" name="orgInfo.org_PassWord" type="password" class="dfinput" /><i></i></li>
    <li><label><i style="color: red;"></i>手机号码</label><input id="org_PhoneNumber" name="orgInfo.org_PhoneNumber" onkeyup="checkPhone()" type="text" class="dfinput" /><i id="strs" style="color: red;"></i></li>
    <li><label>状态</label><div class="cityright"><select name="orgInfo.org_Status" class="select2"><option value="1">启用</option><option value="0">禁用</option></select></div></li>
    <li><label>添加时间</label><input name="orgInfo.org_AddTime" value="<%=time %>" type="text" class="dfinput" /><i></i></li>
    <li><label>&nbsp;</label><input name="" type="submit" class="btn" onclick="addorginfo();" value="确认保存"/>&nbsp;&nbsp;<input name="" type="button" style="background-color:green;" onclick="javascript:history.go(-1);" class="btn" value="返回"/></li>
    </ul>
    </div>
    </form>
  </body>
</html>
