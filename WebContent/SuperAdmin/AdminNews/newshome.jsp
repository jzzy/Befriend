<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>

<%@page import="com.befriend.entity.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>新闻管理后台</title>
</head>
<body>
<%
//获取管理员
Admin admin=(Admin)session.getAttribute("admin");
if(admin==null){
	//重新登入
	return;
}
%>
	当前那用户是:<%=admin.getAdmin()%><br>
	<br>
	<a href="<%=request.getContextPath()%>/GetUserAll">查看用户信息</a><br>
	<!--  <a href="<%=request.getContextPath()%>/ViewStatistics">查询统计信息</a><br>-->
	<a href="<%=request.getContextPath()%>/Newsget">管理新闻</a><br>
	<a href="<%=request.getContextPath()%>/SuperAdmin/AdminNews/kindeditor/jsp/upBook.jsp">书籍上传</a><br>
	<a href="<%=request.getContextPath()%>/SuperAdmin/AdminNews/kindeditor/jsp/AU.jsp">八大类新闻上传</a><br>
	<a href="<%=request.getContextPath()%>/SuperAdmin/AdminNews/kindeditor/jsp/AUB.jsp">本地新闻上传</a><br>
	<a href="<%=request.getContextPath()%>/SuperAdmin/AdminNews/house/SaveHouse.jsp">学区房上传</a><br>
<a href="<%=request.getContextPath()%>/SuperAdmin/AdminNews/house/selectHouse">查看学区房</a><br>
<a href="<%=request.getContextPath()%>/selectjzfk">查看家长反馈</a><br>
<a href="<%=request.getContextPath()%>/SuperAdmin/AdminNews/ParentsLetter/SavePL.jsp">添加家长来稿</a><br>

<a href="<%=request.getContextPath()%>/selectParentsLetter">查看家长来稿</a><br>

<a href="<%=request.getContextPath()%>/getAdv">管理广告图</a><br>


</body>
</html>