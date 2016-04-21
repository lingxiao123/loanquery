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
	<link type="text/css" rel="stylesheet" href="<%=path %>/js/upload/upload.css"></link>
	<link type="text/css" rel="stylesheet" href="<%=path %>/js/sg/css/sg.css"></link>
	<script type="text/javascript" src="<%=path %>/js/jquery-1.11.1.min.js"></script>
	<script type="text/javascript">
		function deleteimport(obj) {
			if(window.confirm("您确认要删除该记录吗？")){
				$.ajax({
					url:"deleteImport.action",
					type:"post",
					data:{"id":obj},
					async:false,
					dataType:"json",
					success:function(data){
						if(data.success==true){
							alert("删除成功！");
							window.location.href="findData.action";
						}
					}
				});
			}
		}
	</script>
	<style type="text/css">
		.t_upload{width:130px;height:36px;display:block;background:#69F;font-size:14px;text-align:center;line-height:36px;color:#fff;cursor: pointer;border-radius:3px;}
		.t_upload:hover{background:#66F;}
	</style>
  </head> 
  <body>
  	<div class="place">
    <span>位置：</span>
    <ul class="placeul">
    <li><a href="#">首页</a></li>
    <li><a href="#">数据导入</a></li>
    <li><a href="#">客户持仓查询</a></li>
    </ul>
    </div>    
    <div class="rightinfo">  
    <div class="tools">  
        	<%-- <a href="linkData.action" target="rightFrame"><span><img src="images/t01.png" /></span>上传</a> --%>
        	<span class="t_upload" id="upload_btn">上传文件</span>       
    </div>   
    <table class="tablelist">
    	<thead>
    	<tr>   
        <th style="width:50px;text-align:center;">编号<i class="sort"><!--<img src="images/px.gif" />  --></i></th>
        <th style="text-align:center;">导入类型</th>
        <th style="text-align:center;">导入时间</th>
        <th style="text-align:center;">状态</th>
        <th style="text-align:center;width:100px;">操作</th>   
        </tr>
        </thead>
        <tbody>
        <c:forEach var="i" items="${importList}">
        	<tr>
		        <td style="text-align:center;">${i.im_imortNumber}</td>
		        <td style="text-align:center;">${i.im_type}</td>
		        <td style="text-align:center;">${i.im_addTime}</td>
		        <c:if test="${i.im_status==0}">
		        	<td style="text-align:center;color:red;">失败</td>
		        </c:if>
		        <c:if test="${i.im_status==1}">
		        	<td style="text-align:center;color:green;">成功</td>
		        </c:if>	
		        <td style="width:100px;text-align:center;"><a style="text-decoration:none;cursor: pointer;" onclick="deleteimport(${i.im_id})" href="#">删除</a></td>		        
	        </tr>
        </c:forEach>                             
        </tbody>
    </table>     
    <div class="pagin">
    	<div class="message" style="width:50%;">共<i class="blue"><%=TotalCount %></i>条记录，当前显示第&nbsp;<i class="blue"><%=Page %>&nbsp;</i>页,共&nbsp;<i class="blue"><%=PageCount %>&nbsp;</i>页</div>
    	 <ul class="paginList">
        	<li class="paginItem"><a href = "findData.action.action?Page=1" style="width:40px;" >首页</a>
               <%if(Page!=1){%> 
               	<a href = "findData.action?Page=<%=Page-1%>" style="" >上一页</a>	
               	
               <% }else{%>
               	<a href = "findData.action?Page=<%=Page-1%>" onclick="return false" style="background-color:#ccc;" >上一页</a>
               <% }%>
               <%
               if(Page!=PageCount){%>
               	<a href = "findData.action?Page=<%=Page+1 %>" style=" ">下一页</a>
               <%}else{%>
               	<a href = "findData.action?Page=<%=Page+1 %>" onclick="return false" style="background-color:#ccc;" style="">下一页</a>
               <%}%>
               <a href = "findData.action?Page=<%=PageCount %>" style=" ">尾页</a></li>
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
    <script type="text/javascript" src="<%=path %>/js/upload/tz_upload.js"></script>
	<script type="text/javascript" src="<%=path %>/js/sg/tz_util.js"></script>
	<script type="text/javascript" src="<%=path %>/js/sg/sg.js"></script>
	<script type="text/javascript">
	$.tmUpload({
		btnId:"upload_btn",
		url:"Upload.action",
		limitSize:"1000 MB",
		fileType:"*.jpg;*.mp4;*.png;*.csv;",
		multiple : true,
		callback:function(serverData,file){
		}
	});
</script>  
    <script type="text/javascript">
	$('.tablelist tbody tr:odd').addClass('odd');
	</script>
  </body>
</html>

