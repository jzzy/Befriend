<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
     <%@page import="java.util.*"%>
    <%@page import="com.befriend.util.*"%>
<%@page import="com.befriend.entity.*"%>
<%


List<Book> bookl=(List)request.getAttribute("bookl");
int type=request.getAttribute("type")==null?0:(Integer)request.getAttribute("type");
int currentPage=(Integer)request.getAttribute("currentPage");
int max=request.getAttribute("max")==null?0:(Integer)request.getAttribute("max");
if(bookl.size()==0){
	
	%> 
	<p>没有您要的信息</p>
	<% 
	return;
}
String title="";
switch (type) {
case 1:
	title="幼儿基础阅读书目推荐";
	break;
case 2:
	title="小学基础阅读书目推荐";
	break;
case 3:
	title="初中基础阅读书目推荐";
	break;
case 4:
	title="高中基础阅读书目推荐";
	break;

default:
	break;
}
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>查看</title>
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

   <div class="top">
       <a href="<%=request.getContextPath()%>/webNewsA10" class="fl"><img src="SimulationApp/images/xw_03.png" height="40" /></a>
     <p class="set fr"><a href="<%=request.getContextPath()%>/SimulationApp/about.html"></a><a href="<%=request.getContextPath()%>/SimulationApp/about.html">
     <!--
     <img src="SimulationApp/images/xw_07.png" height="40" />
       -->
     </a></p>
        </div>
   <!--top-->
   
   <!--nav-->
 	<h2 style="font-size: 20px;" class="conth1">教育部推荐用书</h2>
 	<div class="book_back_title">
 	<div style="width:10%; float:left; ">
 	<a style="float:left;" href="javascript:history.back(-1);">
 	<img src="SimulationApp/images/book_back.png" style="margin-left: 10px;margin-top: 5px;" height="20px" alt="" />
 	</a>
 	</div>
 	<div style="width:90%; float:left; text-align: center; ">
 	<h3  style="font-size:5vw;color: #c0c0c0;margin-top: 6px;"><%=title+" 共"+max %>本</h3>
 	</div>
 	</div>
 	
   <ul class="specil_list">
   <%
   for(int i=0;i<bookl.size();i++){
   %>
<a href="<%=request.getContextPath()%>/webLookBookById?id=<%=bookl.get(i).getId()%>">
      <li class="book_item">
      	<h2>
      		
      			<span style="font-size: 18px;"><%=bookl.get(i).getTitle()%>
      			</span>
      		
      	</h2><br />
      	<p style="color: #666;"><%=bookl.get(i).getSummary()%>
      	<br />出版社：<%=bookl.get(i).getHouse()%>
      	</p>
     </li>
     </a>
    <%
   }
    %> 
   </ul>
</div>


  <!--more4-->

<ul class="page">

<li><a href="<%=request.getContextPath()%>/webLookBook?currentPage=1&itype=<%=type%>">首页</a></li>
<li><a href="<%=request.getContextPath()%>/webLookBook?currentPage=<%=currentPage-1%>&itype=<%=type%> ">上一页</a></li>
<li><a href="<%=request.getContextPath()%>/webLookBook?currentPage=<%=currentPage+1%>&itype=<%=type%> ">下一页</a></li>
<li><a href="<%=request.getContextPath()%>/webLookBook?currentPage=<%=max %>&itype=<%=type%>">末页</a></li>
</ul>

<!--cont-->


</body>
</html>
