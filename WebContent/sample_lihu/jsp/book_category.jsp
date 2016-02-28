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
<script type="text/javascript" src="<%=request.getContextPath()%>/sample_lihu/js/jquery-1.10.2.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/sample_lihu/js/common.js"></script>
<link href="<%=request.getContextPath()%>/sample_lihu/css/style.css" rel="stylesheet" type="text/css" />
</head>
<body>
	<div id="wrap">
		<div id="header">
			<div class="topArea clearfix">
				<h1>教育部推荐用书</h1>
				<div class="prev">
					<a href="#" onclick="javascript:history.back(-1);"><img src="<%=request.getContextPath()%>/sample_lihu/images/prev.png" alt="后退" /></a>
				</div>
			</div>
		</div><!--header-->
		<div id="container">
			<ul class="bookCategory clearfix">
				<li><a href="<%=request.getContextPath()%>/webLookBook?itype=1"><img src="<%=request.getContextPath()%>/sample_lihu/images/edubook01.gif" alt="幼儿" /></a></li>
				<li><a href="<%=request.getContextPath()%>/webLookBook?itype=2"><img src="<%=request.getContextPath()%>/sample_lihu/images/edubook02.gif" alt="小学" /></a></li>
				<li><a href="<%=request.getContextPath()%>/webLookBook?itype=3"><img src="<%=request.getContextPath()%>/sample_lihu/images/edubook03.gif" alt="初中" /></a></li>
				<li><a href="<%=request.getContextPath()%>/webLookBook?itype=4"><img src="<%=request.getContextPath()%>/sample_lihu/images/edubook04.gif" alt="高中" /></a></li>
			</ul>
		</div><!--container-->
		<div id="footer"></div><!--footer-->
	</div><!--wrap-->
</body>
</html>
