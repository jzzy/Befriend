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
Integer own=(Integer)request.getAttribute("own");
Integer bbt=(Integer)request.getAttribute("bbt");
Integer syn=(Integer)request.getAttribute("syn");


Stas iossta=(Stas)request.getAttribute("iossta");
Stas androidsta=(Stas)request.getAttribute("androidsta");
Stas websta=(Stas)request.getAttribute("websta");
Stas bbtsta=(Stas)request.getAttribute("bbtsta");
Stas synsta=(Stas)request.getAttribute("synsta");
%>
<h2 style="color: blue;">
<b style="color: red;">2015-07-03开始统计</b><br>
<%=timeq%>(不包括<%=timeq%>) 到  <%=timeh %></h2>
<!-- 居中 -->
<CENTER></CENTER>
<form  style="width: 650px;"  action="">
<table style="text-align:center;" border=1 >
<tr>
<td width="200"><h4>来源</h4></td>
<td width="200" ><h4>注册人数</h4></td>

<td width="190"><h4>在线人数(最高的一天)</h4></td>
<td width="120"><h4>登入次数</h4></td>
</tr>


<%
if(bbtsta!=null){
%>

<tr style="color: red;">
<td width="200"><h4>掌中校园</h4></td>
<td width="200"><h4><%=bbt %></h4></td>

<td width="190"><h4><%=bbtsta.getUsersyned() %></h4></td>
<td width="120"><h4><%=bbtsta.getUserlogined() %></h4></td>
</tr>
<%} %>


<%
if(synsta!=null){
%>
<tr style="color:green;">
<td width="200"><h4>优教通</h4></td>
<td width="200"><h4><%=syn %></h4></td>

<td width="190"><h4><%=synsta.getUsersyned() %></h4></td>
<td width="120"><h4><%=synsta.getUserlogined() %></h4></td>
</tr>

<%} %>

<%
if(iossta!=null){
%>
<tr style="color: blue;">
<td width="200"><h4>家长之友</h4></td>
<td width="200"><h4>
<%=androidsta.getUsersaved()+iossta.getUsersaved()+websta.getUsersaved() %>
</h4></td>

<td width="190"><h4><%=iossta.getUsersyned()+androidsta.getUsersyned()+websta.getUsersyned() %></h4></td>
<td width="120"><h4><%=androidsta.getUserlogined()+iossta.getUserlogined()+websta.getUserlogined() %></h4></td>
</tr>

<tr  style="color: blue;">
<td width="200"><h4>家长之友-苹果</h4></td>
<td width="200"><h4><%=iossta.getUsersaved() %></h4></td>

<td width="190"><h4><%=iossta.getUsersyned() %></h4></td>
<td width="120"><h4><%=iossta.getUserlogined() %></h4></td>
</tr>
<%} %>
<%
if(androidsta!=null){
%>
<tr style="color: blue;">
<td width="200"><h4>家长之友-安卓</h4></td>
<td width="200"><h4><%=androidsta.getUsersaved() %></h4></td>

<td width="190"><h4><%=androidsta.getUsersyned() %></h4></td>
<td width="120"><h4><%=androidsta.getUserlogined() %></h4></td>
</tr>
<%} %>
<%
if(websta!=null){
%>
<tr style="color: blue;">
<td width="200"><h4>家长之友-网页</h4></td>
<td width="200"><h4><%=websta.getUsersaved() %></h4></td>

<td width="190"><h4><%=websta.getUsersyned() %></h4></td>
<td width="120"><h4><%=websta.getUserlogined() %></h4></td>
</tr>

<%} %>
</table>
</form>

<% 
if(ul!=null){
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