<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    <%@page import="java.util.*"%>
    <%@page import="com.befriend.util.*"%>
<%@page import="com.befriend.entity.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no"  />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title></title>
<link href="<%=request.getContextPath() %>/sample_lihu/css/style.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" href="<%=request.getContextPath() %>/sample_lihu/css/pullToRefresh.css"/>
<script type="text/javascript" src="<%=request.getContextPath() %>/sample_lihu/js/jquery-1.10.2.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/sample_lihu/js/common.js"></script>

</head>
<body>
	<div id="wrap">
		<div id="header">
			<div class="topArea clearfix">
				<h1><a href="<%=request.getContextPath() %>/webNewsA10"><img src="<%=request.getContextPath() %>/sample_lihu/images/logo.gif" alt="家长之友" /></a></h1>
				<div class="relocation">
					<a href="<%=request.getContextPath() %>/sample_lihu/jsp/relocation.jsp">北京&nbsp;<img src="<%=request.getContextPath() %>/sample_lihu/images/location_more.gif" alt="down" /></a>
				</div>
				<div class="topTool">
					<span><a href="<%=request.getContextPath() %>/sample_lihu/htm/post4.html">发表</a></span>
				</div>
			</div>
			<ul class="topMenu clearfix">
				<li><a href="<%=request.getContextPath() %>/webNewsA10"><img src="<%=request.getContextPath() %>/sample_lihu/images/hottoday_ico.gif" alt="fire" />&nbsp;今日必读</a></li>
				<li class="on"><a href="<%=request.getContextPath() %>/sample_lihu/jsp/forum2.jsp">社区互动</a></li>
				<li><a href="<%=request.getContextPath() %>/webGetapp">贴心服务</a></li>
				<li><a href="<%=request.getContextPath() %>/sample_lihu/jsp/mypage.jsp">我的信息</a></li>
			</ul>
			<div class="forum">
				<h2 class="tit2"><img src="<%=request.getContextPath() %>/sample_lihu/images/forum_tit.gif" alt="全国论坛讨论社区" /></h2>
			</div>
		</div><!--header-->
		<div id="container" class="load">
			<iframe id="mainiframe" src="<%=request.getContextPath() %>/webForumApptype" frameborder="0" scrolling="no" width="100%"></iframe>
		</div><!--container-->
		<div id="footer">
		</div><!--footer-->
	</div><!--wrap-->
</body>
</html>
