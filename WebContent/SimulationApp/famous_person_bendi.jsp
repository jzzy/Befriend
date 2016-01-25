<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
     <%@page import="java.util.*"%>
    <%@page import="com.befriend.util.*"%>
<%@page import="com.befriend.entity.*"%>
<%



Object province=session.getAttribute("province");
//获取总的页数
int a=(Integer)request.getAttribute("a");
//获取第几页
int currentPage=(Integer)request.getAttribute("currentPage");
//获取新闻
List<News> nl=(List)request.getAttribute("nl");
if(nl==null||currentPage<=0){
	
	%> 
	<p>没有您要的信息</p>
	<% 
	return;
}

%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>本地资讯</title>
<link href="SimulationApp/css/style.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="SimulationApp/js/jquery-1.2.6.pack.js"></script>
<script type="text/javascript" src="SimulationApp/js/style.js"></script>
<script  src="SimulationApp/js/jquery.js"></script>
<script type="text/javascript">
$(document).ready(function(){
	$.ajax({
		type:"post",
		url:"statisticsPVIP.action"
	});
});
</script>
</head>

<body>
<div class="cont">
    <h1 class="tit tit5">
    <a style="float:left;" href="<%=request.getContextPath()%>/webNewsA10" class="fl"><span>&lt; 今日必读</span></a>
    
    <a style="float:right;" href="<%=request.getContextPath()%>/SimulationApp/lihu/jsp/switchregion.jsp" class="fl"><span>&lt; 切换地区</span></a>
   <center><%=province %></center>
     </h1>

   <div class="sstop">
   <p class="simg"><img src="SimulationApp/images/bdzxlog.png" height="120" /></p><p class="scont"><span>本地资讯是按照地区划分，重点提供本地区的相关教育新闻及政策，让家长方便的了解本地相关的教育资讯。</span></p>
  
   </div>
   <ul class="specil_list">
   <%
   for(int i=0;i<nl.size();i++){
   %>
     <style type="text/css">
.main{width:100%;height:auto;}
.left{width:80%;height:auto;float:left;}
.right{width:20%;height:auto;float:left;}
</style>
      
      <li>
      <div class="main">
      	 <div class="left">
      	 <a href="<%=request.getContextPath()%>/webNewsId?id=<%=nl.get(i).getId()%>">
      	 	<span style="color: black; font-size: 16px;"><strong><%=nl.get(i).getTitle() %></strong></span>
      	 	<br />
      	 	<b> <%=nl.get(i).getTime() %></b>
      	 	</a>
      	 </div>
    	<div class="right">
    		<a href="<%=request.getContextPath()%>/webNewsId?id=<%=nl.get(i).getId()%>">
      			<b style="float:right;margin-top: 6px;"><img src="SimulationApp/images/xing.png" /><%=nl.get(i).getHits()%></b>
	       	</a>
    	</div>
      </div>
      
      
      
      
       
     
      </li>    <%
   }
    %> 
   </ul>
    
   <br/>一共<%=a %>页 当前是第<%=currentPage %>页
</div>


  <!--more4-->

<ul class="page">

<li><a href="<%=request.getContextPath()%>/webHotareaf?pageSize=10&currentPage=1">首页</a></li>
<li><a href="<%=request.getContextPath()%>/webHotareaf?pageSize=10&currentPage=<%=currentPage-1%>">上一页</a></li>
<li><a href="<%=request.getContextPath()%>/webHotareaf?pageSize=10&currentPage=<%=currentPage+1%>">下一页</a></li>
<li><a href="<%=request.getContextPath()%>/webHotareaf?pageSize=10&currentPage=<%=a %>">末页</a></li>
</ul>


</body>
</html>