<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
     <%@page import="java.util.*"%>
    <%@page import="com.befriend.util.*"%>
<%@page import="com.befriend.entity.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">

<head>
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>教育部推荐用书</title>
<link href="SimulationApp/css/style.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="SimulationApp/js/style.js"></script>
<script type="text/javascript" src="SimulationApp/js/jquery.js"></script>
<script type="text/javascript">
$(document).ready(function(){
	$.ajax({
		type:"post",
		url:"statisticsPVIP.action"
	});
});
</script>
</head>
<%
Book book=(Book)request.getAttribute("book");
if(book==null){
	return;
}
String title="";
switch (book.getType()) {
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
<body>
<div class="cont"><!--top-->
 <div class="top">
       <a href="<%=request.getContextPath()%>/webNewsA10" class="fl"><img src="SimulationApp/images/xw_03.png" height="40" /></a>
     <p class="set fr"><a href="<%=request.getContextPath()%>/SimulationApp/about.html"></a><a href="<%=request.getContextPath()%>/SimulationApp/about.html">
        <!--
     <img src="SimulationApp/images/xw_07.png" height="40" />
       -->
     </a></p>
        </div>
        <div class="book_detail_title">
        <div style="width:10%; float:left; ">
        <a style="float:left;" href="javascript:history.back(-1);">
 	<img src="SimulationApp/images/book_back.png" style="margin-left: 10px;margin-top: 5px;" height="20px" alt="" />
 	</a>
 	</div>
 	<div style="width:80%; float:left; text-align: center; ">
 	<h2 style="color: #000000;margin-top: 6px;font-size: 5vw;">《<%=title %>》</h2>
 	</div>
 	</div>
   <!--top-->
 <div class="book_detail">
 
 <div class="book">
  	<p style="padding:0px 10px;color: #000000;"><span style="font-size: 18px;font-weight: bold;">书名:<%=book.getTitle() %></span ><span style="font-size: 16px"><br />作者:<%=book.getAuthor() %><br /><%=book.getHouse()%></span></p>
   </div>
   
   <br />
   <br />
   
   <div class="book">
  	<p style="padding:0px 10px;color: #000000;"><span style="font-size: 20px;font-weight: bold;">简介:</span><span style="font-size: 16px"><%=book.getSummary() %></p>
   </div>
   
   <br />
   <br />
   
   <div class="book">
  	<p style="padding:0px 10px;color: #000000;"><span style="font-size: 20px;font-weight: bold;">书评:</span><span style="font-size: 16px"><%=book.getReview() %></p>
   </div>
   </div>
   </div>
   
  

</div>
<!--cont-->

</body>

</html>
  
 
