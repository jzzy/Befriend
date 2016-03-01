<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    <%@page import="java.util.*"%>
    <%@page import="com.befriend.util.*"%>
<%@page import="com.befriend.entity.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title></title>
<script type="text/javascript" src="sample_lihu/js/jquery-1.10.2.min.js"></script>
<script type="text/javascript" src="sample_lihu/js/common.js"></script>
<link href="sample_lihu/css/style.css" rel="stylesheet" type="text/css" />
</head>
<body>
	<div id="wrap">
		<div id="header">
			<div class="topArea clearfix">
				<h1>教育部推荐用书</h1>
				<div class="prev">
					<a href="#" onclick="javascrsipt:history.back(-1);"><img src="sample_lihu/images/prev.png" alt="后退" /></a>
				</div>
			</div>
		</div><!--header-->
		<div id="container">
			<ul class="bookDetail clearfix">
			<%
			Book b=(Book)request.getAttribute("book");
			if(b!=null){
			%>
				<li>
					<h2><%=b.getTitle() %></h2>
					<p><img src="sample_lihu/images/ico04.gif" alt="作者" /><%=b.getAuthor() %></p>
					<p><img src="sample_lihu/images/ico05.gif" alt="出版社" /><%=b.getHouse() %></p>
				</li>
				<li>
					<h3>简介</h3>
					<p class="info"><%=b.getSummary()%></p>
				</li>
				<li>
					<h3>书评</h3>
					<p class="info"><%=b.getReview() %></p>
				</li>
				<%
			}
				%>
			</ul>
		</div><!--container-->
		<div id="footer"></div><!--footer-->
	</div><!--wrap-->
</body>
</html>
