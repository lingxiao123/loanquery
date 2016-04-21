<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
	String error=null;
	try{
		if(session==null){
			response.sendRedirect(request.getContextPath()
					+ "/login.jsp");
		}else if(session.getAttribute("error").toString()==""){
			response.sendRedirect(request.getContextPath()
					+ "/login.jsp");
		}else{
			error=session.getAttribute("error").toString();
			System.out.println(error);
		}
	}catch(Exception e){
		response.sendRedirect(request.getContextPath()
					+ "/login.jsp");
	}
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>欢迎登录查询系统</title>
<link rel="icon" href="images/logo.ico">
<link rel="stylesheet" type="text/css" href="<%=path %>/css/style.css">
<script type="text/javascript" src="<%=path %>/js/jquery.min.1.8.1.js" ></script>
<script type="text/javascript" src="<%=path %>/js/cloud.js" ></script>
<script type="text/javascript">
	$(function(){
		var userAgent = navigator.userAgent;
		if (userAgent.indexOf("compatible") > -1 && userAgent.indexOf("MSIE") > -1) {
		  $('.loginbox').css({'position':'absolute','left':(document.body.clientWidth-692)/2});
		}else{
			$('.loginbox').css({'position':'absolute','left':($(window).width()-692)/2});
			$(window).resize(function(){  
		    $('.loginbox').css({'position':'absolute','left':($(window).width()-692)/2});
		    });  
		}  
	});
	/*
	function login() {
		var f = document.getElementsByTagName("form")[0];
		f.action="getUser.action";
	}*/
	function yzm(){ 
		//getRememberInfo();
		var data=["0","1","2","3","4","5","6","7","8","9"]; 
		var result=""; 
		for(var i=0;i<4;i++){ 
			r=Math.floor(Math.random()*10);		 
			result+=data[r]; 
		}
		document.getElementById("yzm").innerHTML=result;
	}
	function login() {
		var f = document.getElementsByTagName("form")[0];
    	var name=document.getElementById("username").value;
    	var yzm=document.getElementById("yzm").innerHTML;
    	var sryzm=document.getElementById("sryzm").value;
    	var pwd=document.getElementById("userpwd").value;
    	if(name==""||pwd==""){
    		f.action="";
    		alert("请输入用户名密码");
    	}else if(sryzm==""||yzm!=sryzm){
    		f.action="";
    		alert("验证码错误！");
    		
    	}else{
    		setCookie("userName", name, 24, "/");
    		f.action="Userslogin.action";
    	}
	}
	//新建cookie。
    //hours为空字符串时,cookie的生存期至浏览器会话结束。hours为数字0时,建立的是一个失效的cookie,这个cookie会覆盖已经建立过的同名、同path的cookie（如果这个cookie存在）。
    function setCookie(name, value, hours, path) {
        var name = escape(name);
        var value = escape(value);
        var expires = new Date();
        expires.setTime(expires.getTime() + hours * 3600000);
        path = path == "" ? "" : ";path=" + path;
        _expires = (typeof hours) == "string" ? "" : ";expires=" + expires.toUTCString();
        document.cookie = name + "=" + value + _expires + path;
    }
    //获取cookie值
    function getCookieValue(name) {
        var name = escape(name);
        //读cookie属性，这将返回文档的所有cookie
        var allcookies = document.cookie;
        //查找名为name的cookie的开始位置
        name += "=";
        var pos = allcookies.indexOf(name);
        //如果找到了具有该名字的cookie，那么提取并使用它的值
        if (pos != -1) { //如果pos值为-1则说明搜索"version="失败
            var start = pos + name.length; //cookie值开始的位置
            var end = allcookies.indexOf(";", start); //从cookie值开始的位置起搜索第一个";"的位置,即cookie值结尾的位置
            if (end == -1) end = allcookies.length; //如果end值为-1说明cookie列表里只有一个cookie
            var value = allcookies.substring(start, end); //提取cookie的值
            return unescape(value); //对它解码
        }
        else return ""; //搜索失败，返回空字符串
    }
    //获取cookie信息
    function getRememberInfo() {
        // alert("---获取cookie信息---");
        try {
            var userName = "";
            userName = getCookieValue("userName");
            document.getElementById("name").value = userName;
        } catch (err) {
            alert("NO RMB PASSWORD!");
        }
    }
</script>
</head>
<script type="text/javascript">
	alert(error);
  	function show(){
  		<%if(error=="2"){%>
  		 	alert("用户名密码错误！");
  		 	window.location.href="login.jsp";
  		<%}%>
  		<%if(error=="0"){%>
  			alert("用户已被禁用，请联系系统管理员");
		 	window.location.href="login.jsp";
  		<%}%>
  		<%if(error=="3"){%>
			alert("登录用户不存在");
	 		window.location.href="login.jsp";
		<%}%>
  	}
  </script>
<body onload="yzm();show();" style="background-color:#1c77ac; background-image:url(<%=path %>/images/light.png); background-repeat:no-repeat; background-position:center top; overflow:hidden;">
    <form action="">
    <div id="mainBody">
      <div id="cloud1" class="cloud"></div>
      <div id="cloud2" class="cloud"></div>
    </div> 
	<div class="logintop">    
    <span>欢迎进入查询系统</span>        
    </div> 
    <div class="loginbody">
    <span class="systemlogo"></span> 
    <div class="loginbox loginbox2">    
    <ul>
    <li><input name="username" id="username" type="text" class="loginuser" value="" onclick="JavaScript:this.value=''"/></li>
    <li><input name="userpwd" id="userpwd"  type="password" class="loginpwd" value="密码" onclick="JavaScript:this.value=''"/></li>
    <li class="yzm">
    <span><input name="" id="sryzm" type="text" value="验证码" onclick="JavaScript:this.value=''"/></span><cite id="yzm"></cite> 
    </li>
    <li><input name="" type="submit" class="loginbtn" value="登录"  onclick="login();"  /><label><input name="" type="checkbox" value="" checked="checked" />记住登录名</label><label><a href="#"></a></label></li>
    </ul>
    </div>   
    </div>
    <div class="loginbm">版权所有 2016 江西金澍投资管理有限公司</div>
    </form>
</body>
</html>
