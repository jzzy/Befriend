<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
     <%@page import="java.util.*"%>
     <%@page import="com.befriend.util.*"%>
     <%@page import="com.befriend.entity.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>查询用户</title>
</head>
<body >
<%
List<User> ul=(List)request.getAttribute("GetUsertimeus");
String timeq=(String)request.getAttribute("timeq");
String timeh=(String)request.getAttribute("timeh");
Integer count=(Integer)request.getAttribute("count");
%>
<h4 style="color: blue;"><%=timeq%>^到^<%=timeh %>一共注册<%=count %>个用户</h4>
<form style="background: #adc" action="">
<table >
<tr>
<td width="200"><h4>--用户账号</h4></td>
<td width="200"><h4>用户地址</h4></td>

<td width="190"><h4>用户注册时间</h4></td>
<td width="120"><h4>用户登入次数</h4></td>
<td width="200"><h4>用户最后登入时间</h4></td>
<td  width=""><h4>用户手机号</h4></td>
</tr>
</table>
</form>
<% 
if(ul==null){
	return;
}
for(int i=0;i<ul.size();i++){
%>

<form style="background: #cdc" action="SetQ"  method="post" enctype="multipart/form-data">
<table >
<tr>

<td width="200"><%=ul.get(i).getUsername() %></td>
<td width="200"><%=ul.get(i).getAddress() %><%=ul.get(i).getAddcity()%></td>

<td width="240"><%=ul.get(i).getTime() %></td>
<td width="70"><%=ul.get(i).getLoginnum()%></td>
<td width="200"><%=ul.get(i).getFinaltime()%></td>
<td width=""><h4><%=ul.get(i).getPhone()%></h4></td>
</tr>
</table>
</form>
<%
}
%>
</body>
</html>