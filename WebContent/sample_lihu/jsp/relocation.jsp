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
<script type="text/javascript" src="<%=request.getContextPath() %>/sample_lihu/js/jquery-1.10.2.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/sample_lihu/js/common.js"></script>
<link href="<%=request.getContextPath() %>/sample_lihu/css/style.css" rel="stylesheet" type="text/css" />
</head>
<body>
	<div id="wrap">
		<div id="header">
			<div class="topArea clearfix">
				<h1><a href="webNewsA10"><img src="<%=request.getContextPath() %>/sample_lihu/images/logo.gif" alt="家长之友" /></a></h1>
				<div class="relocation">
					<a href="<%=request.getContextPath() %>/webNewsA10?province=<%=session.getAttribute("province") %>"><%=session.getAttribute("province") %>&nbsp;<img src="<%=request.getContextPath() %>/sample_lihu/images/location_more.gif" alt="down" /></a>
				</div>
				<div class="topTool">
					<span class="mapLink"><a href="sitemap.jsp"><img src="<%=request.getContextPath() %>/sample_lihu/images/toptoolBtn.gif" alt="菜单" /></a></span>
				</div>
			</div>
		</div><!--header-->
		<div id="container">
			<div class="currentCity clearfix">
				<span class="title">当前定位城市&nbsp;:</span>
				<span class="name"><%=session.getAttribute("province") %><a href="#"><img src="<%=request.getContextPath() %>/sample_lihu/images/relocation_ico.png" alt="重新定位" /></a></span>
			</div>
			<div class="hotCity">
				<h2 class="tit2">热门城市</h2>
				<ul class="clearfix">
				<li><a href="<%=request.getContextPath() %>/webNewsA10?province=北京" >北京</a></li>
					<li><a href="<%=request.getContextPath() %>/webNewsA10?province=上海">上海</a></li>
					<li><a href="<%=request.getContextPath() %>/webNewsA10?province=河南">河南</a></li>
					<li><a href="<%=request.getContextPath() %>/webNewsA10?province=江苏">江苏</a></li>
					<li><a href="<%=request.getContextPath() %>/webNewsA10?province=广东">广东</a></li>
					<li><a href="<%=request.getContextPath() %>/webNewsA10?province=山东">山东</a></li>
					<li><a href="<%=request.getContextPath() %>/webNewsA10?province=浙江">浙江</a></li>
					<li><a href="<%=request.getContextPath() %>/webNewsA10?province=河北">河北</a></li>
				</ul>
			</div>
			<div class="cityList">
				<h2 class="tit2">省市列表(共<span>34</span>个省市)</h2>
				<ul class="clearfix">
					<li><a href="<%=request.getContextPath() %>/webNewsA10?province=北京">北京</a></li>
					<li><a href="<%=request.getContextPath() %>/webNewsA10?province=上海">上海</a></li>
					<li><a href="<%=request.getContextPath() %>/webNewsA10?province=天津">天津</a></li>
					<li><a href="<%=request.getContextPath() %>/webNewsA10?province=重庆">重庆</a></li>
					<li><a href="<%=request.getContextPath() %>/webNewsA10?province=河北">河北</a></li>
					<li><a href="<%=request.getContextPath() %>/webNewsA10?province=山西">山西</a></li>
					<li><a href="<%=request.getContextPath() %>/webNewsA10?province=内蒙古">内蒙古</a></li>
					<li><a href="<%=request.getContextPath() %>/webNewsA10?province=辽宁">辽宁</a></li>
					<li><a href="<%=request.getContextPath() %>/webNewsA10?province=吉林">吉林</a></li>
					<li><a href="<%=request.getContextPath() %>/webNewsA10?province=黑龙江">黑龙江</a></li>
					<li><a href="<%=request.getContextPath() %>/webNewsA10?province=江苏">江苏</a></li>
					<li><a href="<%=request.getContextPath() %>/webNewsA10?province=浙江">浙江</a></li>
					<li><a href="<%=request.getContextPath() %>/webNewsA10?province=安徽">安徽</a></li>
					<li><a href="<%=request.getContextPath() %>/webNewsA10?province=福建">福建</a></li>
					<li><a href="<%=request.getContextPath() %>/webNewsA10?province=江西">江西</a></li>
					<li><a href="<%=request.getContextPath() %>/webNewsA10?province=山东">山东</a></li>
					<li><a href="<%=request.getContextPath() %>/webNewsA10?province=河南">河南</a></li>
					<li><a href="<%=request.getContextPath() %>/webNewsA10?province=湖北">湖北</a></li>
					<li><a href="<%=request.getContextPath() %>/webNewsA10?province=湖南">湖南</a></li>
					<li><a href="<%=request.getContextPath() %>/webNewsA10?province=广东">广东</a></li>
					<li><a href="<%=request.getContextPath() %>/webNewsA10?province=广西">广西</a></li>
					<li><a href="<%=request.getContextPath() %>/webNewsA10?province=海南">海南</a></li>
					<li><a href="<%=request.getContextPath() %>/webNewsA10?province=四川">四川</a></li>
					<li><a href="<%=request.getContextPath() %>/webNewsA10?province=贵州">贵州</a></li>
					<li><a href="<%=request.getContextPath() %>/webNewsA10?province=云南">云南</a></li>
					<li><a href="<%=request.getContextPath() %>/webNewsA10?province=西藏">西藏</a></li>
					<li><a href="<%=request.getContextPath() %>/webNewsA10?province=陕西">陕西</a></li>
					<li><a href="<%=request.getContextPath() %>/webNewsA10?province=甘肃">甘肃</a></li>
					<li><a href="<%=request.getContextPath() %>/webNewsA10?province=宁夏">宁夏</a></li>
					<li><a href="<%=request.getContextPath() %>/webNewsA10?province=青海">青海</a></li>
					<li><a href="<%=request.getContextPath() %>/webNewsA10?province=新疆">新疆</a></li>
					<li><a href="<%=request.getContextPath() %>/webNewsA10?province=香港">香港</a></li>
					<li><a href="<%=request.getContextPath() %>/webNewsA10?province=澳门">澳门</a></li>
					<li><a href="<%=request.getContextPath() %>/webNewsA10?province=台湾">台湾</a></li>
				</ul>
			</div>
		</div><!--container-->
		<div id="footer"></div><!--footer-->
	</div><!--wrap-->
</body>
</html>
