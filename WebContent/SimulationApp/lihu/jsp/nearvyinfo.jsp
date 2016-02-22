<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    <%@page import="java.util.*"%>
    <%@page import="com.befriend.util.*"%>
<%@page import="com.befriend.entity.*"%>
<%@page import="org.json.*"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title></title>
<script type="text/javascript" src="<%=request.getContextPath() %>/SimulationApp/lihu/js/jquery.js"></script>
<link href="<%=request.getContextPath() %>/SimulationApp/lihu/css/style.css" rel="stylesheet" type="text/css" />

<style type="text/css">

</style>

</head>
<body>
<%
JSONArray jsonArray=(JSONArray)request.getAttribute("jsonArray");

Object	type=request.getAttribute("type");
%>

	<div class="cont">
		<div id="header">
			<div class="topArea clearfix">
				<div class="prev">
				<a href="javascript:history.back(-1);"></a>
				</div>
				<h1>附近的<span><%=type %></span></h1>
			</div>
		</div><!--header-->
		
		<div id="container">
			<ul class="nearbyInfo">
				<%
				for (int i = 0; i < jsonArray.length(); i++) {
					JSONObject jo = jsonArray.getJSONObject(i);
				%>
				<li>
					<dl class="topArea">
						<dt><%=jo.getString("name") %></dt>
						<dd class="tel">联系方式&nbsp;:&nbsp;<%=jo.getString("tel").length()<=3?"无":jo.getString("tel").split(";")[0]  %></dd>
						<dd class="type">服务类型&nbsp;:&nbsp;<%=type %>服务</dd>
					</dl>
					<ul class="bottomArea clearfix">
						<li>
							<a href="daohang?lng=<%=jo.getString("location").split(",")[0] %>&lat=<%=jo.getString("location").split(",")[1] %>"><img src="<%=request.getContextPath() %>/SimulationApp/lihu/images/bolt.png" alt="" />导航过去</a>
						</li>
						<li>
							<a href="tel:<%=jo.getString("tel").length()<=3?"无":jo.getString("tel").split(";")[0] %>">
							<img src="<%=request.getContextPath() %>/SimulationApp/lihu/images/tel.png" alt="<%=jo.getString("tel").length()<=3?"无":jo.getString("tel").split(";")[0]  %>" />
							联系商家</a>
						</li>
					</ul>
				</li>
				<%
				}
				%>
			</ul>
		</div><!--container-->
	</div><!--cont-->

</body>
</html>
