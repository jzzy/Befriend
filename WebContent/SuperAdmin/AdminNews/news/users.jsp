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
Stas wechatsta=(Stas)request.getAttribute("wechatsta");
int PV=0;
int UV=0;
int IP=0;
%>
<h2 style="color: blue;">
<b style="color: red;">2015-07-03开始统计</b><br>
<%=timeq%>(不包括<%=timeq%>) 到  <%=timeh %></h2>
<!-- 居中 -->
<CENTER></CENTER>
<form  style="width: 60%;"  action="">
<table style="text-align:center;" border=1 >
<tr>
<td ><h4>来源</h4></td>
<td ><h4>注册人数</h4></td>

<td><h4>在线人数(最高的一天)</h4></td>
<td><h4>登入用户</h4></td>
<td><h4>PV</h4></td>
<td><h4>UV</h4></td>
<td><h4>IP</h4></td>
</tr>


<%
if(bbtsta!=null){
%>

<tr style="color: red;">
<td ><h4>掌中校园</h4></td>
<td><h4><%=bbt %></h4></td>

<td><h4><%=bbtsta.getUsersyned() %></h4></td>
<td ><h4><%=bbtsta.getUserlogined() %></h4></td>
<td ><h4><%=bbtsta.getPv() %></h4></td>
<td ><h4><%=bbtsta.getUserlogined() %></h4></td>
<td><h4><%=bbtsta.getIp() %></h4></td>
</tr>
<%
PV=PV+bbtsta.getPv();
UV=UV+bbtsta.getUserlogined();
IP=IP+bbtsta.getIp();
} %>


<%
if(synsta!=null){
%>
<tr style="color:green;">
<td width="200"><h4>优教通</h4></td>
<td width="200"><h4><%=syn %></h4></td>

<td width="190"><h4><%=synsta.getUsersyned() %></h4></td>
<td width="120"><h4><%=synsta.getUserlogined() %></h4></td>
<td width="120"><h4><%=synsta.getPv() %></h4></td>
<td width="120"><h4><%=synsta.getUserlogined() %></h4></td>
<td ><h4><%=synsta.getIp() %></h4></td>
</tr>

<%
PV=PV+synsta.getPv();
UV=UV+synsta.getUserlogined();
IP=IP+synsta.getIp();
} %>


<tr style="color: blue;">
<td width="200"><h4>家长之友</h4></td>
<td width="200"><h4>
<%=androidsta.getUsersaved()+iossta.getUsersaved()+websta.getUsersaved()+wechatsta.getUsersaved() %>
</h4></td>

<td width="190"><h4><%=iossta.getUsersyned()+androidsta.getUsersyned()+websta.getUsersyned()+wechatsta.getUsersyned() %></h4></td>
<td width="120"><h4><%=androidsta.getUserlogined()+iossta.getUserlogined()+websta.getUserlogined()+wechatsta.getUserlogined() %></h4></td>
<td width="120"><h4><%=iossta.getPv()+websta.getPv()+androidsta.getPv()+wechatsta.getPv() %></h4></td>
<td width="120"><h4><%=androidsta.getUserlogined()+iossta.getUserlogined()+websta.getUserlogined()+wechatsta.getUserlogined() %></h4></td>
<td width="120"><h4><%=iossta.getIp()+websta.getIp()+androidsta.getIp()+wechatsta.getIp() %></h4></td>
</tr>



<%
if(iossta!=null){
%>
<tr  style="color: blue;">
<td width="200"><h4>-苹果</h4></td>
<td width="200"><h4><%=iossta.getUsersaved() %></h4></td>

<td width="190"><h4><%=iossta.getUsersyned() %></h4></td>
<td width="120"><h4><%=iossta.getUserlogined() %></h4></td>
<td width="120"><h4><%=iossta.getPv() %></h4></td>
<td width="120"><h4><%=iossta.getUserlogined() %></h4></td>
<td width="120"><h4><%=iossta.getIp() %></h4></td>
</tr>
<%
PV=PV+iossta.getPv();
UV=UV+iossta.getUserlogined();
IP=IP+iossta.getIp();
} %>
<%
if(androidsta!=null){
%>
<tr style="color: blue;">
<td width="200"><h4>-安卓</h4></td>
<td width="200"><h4><%=androidsta.getUsersaved() %></h4></td>

<td width="190"><h4><%=androidsta.getUsersyned() %></h4></td>
<td width="120"><h4><%=androidsta.getUserlogined() %></h4></td>
<td width="120"><h4><%=androidsta.getPv() %></h4></td>
<td width="120"><h4><%=androidsta.getUserlogined() %></h4></td>
<td width="120"><h4><%=androidsta.getIp() %></h4></td>
</tr>
<%
PV=PV+androidsta.getPv();
UV=UV+androidsta.getUserlogined();
IP=IP+androidsta.getIp();
} %>
<%
if(websta!=null){
%>
<tr style="color: blue;">
<td width="200"><h4>-网页</h4></td>
<td width="200"><h4><%=websta.getUsersaved() %></h4></td>

<td width="190"><h4><%=websta.getUsersyned() %></h4></td>
<td width="120"><h4><%=websta.getUserlogined() %></h4></td>
<td width="120"><h4><%=websta.getPv() %></h4></td>
<td width="120"><h4><%=websta.getUserlogined() %></h4></td>
<td width="120"><h4><%=websta.getIp() %></h4></td>
</tr>

<%

PV=PV+websta.getPv();
UV=UV+websta.getUserlogined();
IP=IP+websta.getIp();
} %>
<%
if(wechatsta!=null){
%>
<tr style="color: blue;">
<td width="200"><h4>-微信</h4></td>
<td width="200"><h4><%=wechatsta.getUsersaved() %></h4></td>

<td width="190"><h4><%=wechatsta.getUsersyned() %></h4></td>
<td width="120"><h4><%=wechatsta.getUserlogined() %></h4></td>
<td width="120"><h4><%=wechatsta.getPv() %></h4></td>
<td width="120"><h4><%=wechatsta.getUserlogined() %></h4></td>
<td width="120"><h4><%=wechatsta.getIp() %></h4></td>
</tr>

<%
PV=PV+wechatsta.getPv();
UV=UV+wechatsta.getUserlogined();
IP=IP+wechatsta.getIp();
} %>
<tr style="color: blue;">
<td width="200"><h4>-ALL</h4></td>
<td width="200"><h4></h4></td>

<td width="190"><h4></h4></td>
<td width="120"><h4></h4></td>
<td width="120"><h4><%=PV %></h4></td>
<td width="120"><h4><%=UV %></h4></td>
<td width="120"><h4><%=IP %></h4></td>
</tr>

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