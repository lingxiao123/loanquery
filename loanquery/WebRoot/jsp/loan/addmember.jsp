<%@ page language="java" import="java.util.*,java.text.SimpleDateFormat" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
	SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");//设置日期格式
	String time=df.format(new Date());// new Date()为获取当前系统时间
	String loanid=null;
	String role=null;
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
	try{
		if(session==null){
			response.sendRedirect(request.getContextPath()
					+ "/login.jsp");
		}else if(session.getAttribute("loanid").toString()==""){
			response.sendRedirect(request.getContextPath()
					+ "/login.jsp");
		}else{
			loanid=session.getAttribute("loanid").toString();
		}
		if(session.getAttribute("role").toString()==""){
			response.sendRedirect(request.getContextPath()
					+ "/login.jsp");
		}else{
			role=session.getAttribute("role").toString();
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
	<link rel="stylesheet" type="text/css" href="<%=path %>/js/autocomplete/css/ui-lightness/jquery-ui-1.10.4.custom.min.css">
	<script type="text/javascript" src="<%=path %>/js/jquery.min.1.8.1.js" ></script>
	<script type="text/javascript" src="<%=path %>/js/select-ui.min.js" ></script>
	<script type="text/javascript" src="<%=path %>/js/autocomplete/js/jquery-ui-1.10.4.custom.js"></script>
	<script type="text/javascript">
	$(function(){
		$("#lm_loanname").autocomplete({
	            source: function (request, response) {
	                $.ajax({
	                    url: "AutocompleteLoan.action",  //要查询的Action
	                    type: "POST",  //Post提交
	                    dataType: "json", //json格式数据，默认是text
	                    data: { keyword: request.term }, //参数，不知道为什么？请指点
	                    success: function (data) {
                    		response($.map(data.list, function (item) {
	                            return { label: item.text, value: item.value }; //将返回的jsonresult的属性赋值给autocomplete item
	                        }));
	                    	/*
	                        response($.map(data, function (item) {
	                            return { label: item[0].text, value: item[0].value }; //将返回的jsonresult的属性赋值给autocomplete item
	                        }));*/
	                    }
	                });
	            },
	        	focus: function (event, ui) {
	        	    $("#lm_loanname").val(ui.item.label); //选中item的文本
	        	    return false;
	        	},
	        	select: function (event, ui) {
	        	    $("#lm_loanname").val(ui.item.label);  //选中item的文本
	        	    $("#lm_loanid").val(ui.item.value); //选中item的值
	        	    return false;
	        	}
	        });
		});
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
		function addloanmember() {
			var f=document.getElementsByTagName("form")[0];
			var lm_name=document.getElementById("lm_name").value;
			var lm_tradAccount=document.getElementById("lm_tradAccount").value;
			var loanid=document.getElementById("lm_loanid").value;
			if(loanid==""||loanid==null){
				alert("请输入个代");
				return false;
			}
			if(lm_name==""||lm_name==null){
				alert("请输入姓名！");
				return false;
			}else if(lm_tradAccount==""||lm_tradAccount==null){
				alert("请输入交易帐号！");
				return false;
			}else if(!checkTradAccound()){
				alert("保存失败,请输入正确的信息！");
				return false;
			}else{
				f.action="addLoanmember.action";
			}
		}
		function checkPhone() {
			var flag=false;
			var phone=document.getElementById("lm_phoneNumber").value;
			if(phone==""){
				document.getElementById("str").innerHTML="";
				flag=true;
			}else{
				var myreg = /^(((13[0-9]{1})|(15[0-9]{1})|(18[0-9]{1}))+\d{8})$/; 
				if(!myreg.test(phone)) 
				{ 
				    document.getElementById("str").innerHTML="请输入有效的手机号码！";
				    flag= false; 
				}else{
					document.getElementById("str").innerHTML="";
					flag= true; 
				} 
			}
			return flag;
		}
		function checkTradAccound() {
			var flag=false;
			$.ajax({
				url:"getCheckAccount.action",
				type:"post",
				data:{"tradAccount":$("#lm_tradAccount").val()},
				async:false,
				dataType:"json",
				success:function(data){
					if(data.success==true){
						document.getElementById("strs").innerHTML="";
						flag= true;
					}else{
						document.getElementById("strs").innerHTML="该交易账号已存在,请重新交易账号！";
						flag= false;
					}
				}
			});
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
    <li><a href="#">基础数据管理</a></li>
    <li><a href="#">添加终端客户</a></li>
    </ul>
    </div>   
    <div class="formbody">  
    <div class="formtitle"><span>添加终端客户</span></div>  
    <ul class="forminfo">
    <li><label><i style="color: red;">*</i>终端姓名</label><input id="lm_name" name="loanMember.lm_name" type="text" class="dfinput" /><i></i></li>
    <li><label><i style="color: red;">*</i>交易账号</label><input id="lm_tradAccount" name="loanMember.lm_tradAccount" onkeyup="checkTradAccound()" type="text" class="dfinput" /><i id="strs" style="color: red;"></i></li>
    <li><label><i style="color: red;"></i>手机号码</label><input id="lm_phoneNumber" name="loanMember.lm_phoneNumber" onkeyup="checkPhone();" type="text" class="dfinput" /><i id="str" style="color: red;"></i></li>   
	<%if(role=="系统用户"||role=="居间商"){%>
    	    <li><label><i style="color: red;"></i>所属主管</label><input id="lm_loanname" name="loanMember.loanname"  type="text" class="dfinput" /><input type="hidden" id="lm_loanid" name="loanMember.lm_loanid"><i id="str" style="color: red;"></i></li>
    <%} %>
    <%if(role=="个代"){ %>
    	<input type="hidden" id="lm_loanid" name="loanMember.lm_loanid" value="<%=loanid%>">
    <%} %>
    <li><label><i style="color: red;"></i>添加时间</label><input name="loanMember.lm_addTime" value="<%=time %>" type="text" class="dfinput" /> <i></i></li>
    <li><label>&nbsp;</label><input name="" type="submit" class="btn" onclick="addloanmember();" value="确认保存"/>&nbsp;&nbsp;<input name="" type="button" style="background-color:green;" onclick="javascript:history.go(-1);" class="btn" value="返回"/></li>
    </ul>
    <input type="hidden" name="loanMember.lm_status" value="1">
    </div>
    </form>
    <script type="text/javascript">
   		var check=document.getElementsByName("loanMember.lm_loanid");
   		for(var i=0;i<check.length;i++){
   			if(check[i].value==<%=loanid%>){
   				check[i].checked=true;
   			}
   		}
    </script>
  </body>
</html>
