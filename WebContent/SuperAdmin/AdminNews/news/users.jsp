<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
     <%@page import="java.util.*"%>
     <%@page import="com.befriend.util.*"%>
     <%@page import="com.befriend.entity.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>查询统计信息</title>
</head>
<body >



<%
String timeq=(String)request.getAttribute("timeq");
String timeh=(String)request.getAttribute("timeh");
Integer own=(Integer)request.getAttribute("own");
Integer bbt=(Integer)request.getAttribute("bbt");
Integer syn=(Integer)request.getAttribute("syn");
Integer zhzh=(Integer)request.getAttribute("zhzh");
Integer xdd=(Integer)request.getAttribute("xdd");
Integer kux=(Integer)request.getAttribute("kux");

Integer kd=(Integer)request.getAttribute("kd");
Integer yg=(Integer)request.getAttribute("yg");
Integer hc=(Integer)request.getAttribute("hc");

Stas iossta=(Stas)request.getAttribute("iossta");

Stas androidsta=(Stas)request.getAttribute("androidsta");
Stas websta=(Stas)request.getAttribute("websta");
Stas bbtsta=(Stas)request.getAttribute("bbtsta");
Stas synsta=(Stas)request.getAttribute("synsta");
Stas wechatsta=(Stas)request.getAttribute("wechatsta");
Stas zhzhsta=(Stas)request.getAttribute("zhzhsta");
Stas xddsta=(Stas)request.getAttribute("xddsta");
Stas kuxsta=(Stas)request.getAttribute("kuxsta");

Stas hcomsta=(Stas)request.getAttribute("hcomsta");
Stas kdcomsta=(Stas)request.getAttribute("kdcomsta");
Stas ygcomsta=(Stas)request.getAttribute("ygcomsta");
%>
<h2 style="color: blue;">
<b style="color: red;">2015-07-03开始统计</b><br>
<%=timeq%>(不包括<%=timeq%>) 到  <%=timeh %></h2>
<!-- 居中 -->
<CENTER></CENTER>
<form   style="width: 60%;"  action="">
<table style="text-align:center;" border=1 >
<tr>
<td ><h4>来源</h4></td>
<td ><h4>注册人数</h4></td>
<td ><h4>登入次数</h4></td>
<td><h4>同时在线人数(最高的一天)</h4></td>
<!--  <td><h4>登入用户</h4></td>-->
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
<td ><h4><%=bbtsta.getUserlogined() %></h4></td>
<td><h4><%=bbtsta.getUsersyned() %></h4></td>

<td ><h4><%=bbtsta.getPv() %></h4></td>
<td ><h4><%=bbtsta.getUv() %></h4></td>
<td><h4><%=bbtsta.getIp() %></h4></td>
</tr>
<%
} %>
<%
if(xddsta!=null){
%>

<tr  style="color:green;">
<td ><h4>学多多</h4></td>
<td><h4><%=xdd %></h4></td>
<td ><h4><%=xddsta.getUserlogined() %></h4></td>
<td><h4><%=xddsta.getUsersyned() %></h4></td>

<td ><h4><%=xddsta.getPv() %></h4></td>
<td ><h4><%=xddsta.getUv() %></h4></td>
<td><h4><%=xddsta.getIp() %></h4></td>
</tr>
<%
} %>
<%
if(zhzhsta!=null){
%>

<tr style="color: red;">
<td ><h4>翼校通</h4></td>
<td><h4><%=zhzh %></h4></td>
<td ><h4><%=zhzhsta.getUserlogined() %></h4></td>
<td><h4><%=zhzhsta.getUsersyned() %></h4></td>

<td ><h4><%=zhzhsta.getPv() %></h4></td>
<td ><h4><%=zhzhsta.getUv() %></h4></td>
<td><h4><%=zhzhsta.getIp() %></h4></td>
</tr>
<%
} %>

<%
if(synsta!=null){
%>
<tr style="color:green;">
<td width="200"><h4>优教通</h4></td>
<td width="200"><h4><%=syn %></h4></td>
<td width="120"><h4><%=synsta.getUserlogined() %></h4></td>
<td width="190"><h4><%=synsta.getUsersyned() %></h4></td>
<td width="120"><h4><%=synsta.getPv() %></h4></td>
<td width="120"><h4><%=synsta.getUv() %></h4></td>
<td ><h4><%=synsta.getIp() %></h4></td>
</tr>

<%
} %>
<%
if(kuxsta!=null){
%>
<tr style="color:red;">
<td width="200"><h4>酷校</h4></td>
<td width="200"><h4><%=syn %></h4></td>
<td width="120"><h4><%=kuxsta.getUserlogined() %></h4></td>
<td width="190"><h4><%=kuxsta.getUsersyned() %></h4></td>
<td width="120"><h4><%=kuxsta.getPv() %></h4></td>
<td width="120"><h4><%=kuxsta.getUv() %></h4></td>
<td ><h4><%=kuxsta.getIp() %></h4></td>
</tr>

<%
} %>

<%
if(hcomsta!=null){
%>
<tr style="color:green;">
<td width="200"><h4>恒谦教育</h4></td>
<td width="200"><h4><%=hc %></h4></td>
<td width="120"><h4><%=hcomsta.getUserlogined() %></h4></td>
<td width="190"><h4><%=hcomsta.getUsersyned() %></h4></td>
<td width="120"><h4><%=hcomsta.getPv() %></h4></td>
<td width="120"><h4><%=hcomsta.getUv() %></h4></td>
<td ><h4><%=hcomsta.getIp() %></h4></td>
</tr>

<%
} %>
<%
if(kdcomsta!=null){
%>
<tr style="color:red;">
<td width="200"><h4>阔地教育</h4></td>
<td width="200"><h4><%=kd %></h4></td>
<td width="120"><h4><%=kdcomsta.getUserlogined() %></h4></td>
<td width="190"><h4><%=kdcomsta.getUsersyned() %></h4></td>
<td width="120"><h4><%=kdcomsta.getPv() %></h4></td>
<td width="120"><h4><%=kdcomsta.getUv() %></h4></td>
<td ><h4><%=kdcomsta.getIp() %></h4></td>
</tr>

<%
} %>
<%
if(ygcomsta!=null){
%>
<tr style="color:green;">
<td width="200"><h4>亿谷教育</h4></td>
<td width="200"><h4><%=yg %></h4></td>
<td width="120"><h4><%=ygcomsta.getUserlogined() %></h4></td>
<td width="190"><h4><%=ygcomsta.getUsersyned() %></h4></td>
<td width="120"><h4><%=ygcomsta.getPv() %></h4></td>
<td width="120"><h4><%=ygcomsta.getUv() %></h4></td>
<td ><h4><%=ygcomsta.getIp() %></h4></td>
</tr>

<%
} %>

<tr style="color: blue;">
<td width="200"><h4>家长之友</h4></td>
<td width="200"><h4>
<%=androidsta.getUsersaved()+iossta.getUsersaved()+websta.getUsersaved()+wechatsta.getUsersaved() %>
</h4></td>
<td width="120"><h4><%=androidsta.getUserlogined()+iossta.getUserlogined()+websta.getUserlogined()+wechatsta.getUserlogined() %></h4></td>
<td width="190"><h4><%=iossta.getUsersyned()+androidsta.getUsersyned()+websta.getUsersyned()+wechatsta.getUsersyned() %></h4></td>
<td width="120"><h4><%=iossta.getPv()+websta.getPv()+androidsta.getPv()+wechatsta.getPv() %></h4></td>
<td width="120"><h4><%=androidsta.getUv()+iossta.getUv()+websta.getUv()+wechatsta.getUv() %></h4></td>
<td width="120"><h4><%=iossta.getIp()+websta.getIp()+androidsta.getIp()+wechatsta.getIp() %></h4></td>
</tr>
<%
%>
<tr style="color: fuchsia;">
<td width="200"><h4>-ALL</h4></td>
<td width="200"><h4>
<%=

androidsta.getUsersaved()+iossta.getUsersaved()+websta.getUsersaved()+wechatsta.getUsersaved()+syn+bbt+zhzh+xdd+kux+hc+kd+yg%>
</h4></td>
<td width="120"><h4><%=
kuxsta.getUserlogined()+hcomsta.getUserlogined()+kdcomsta.getUserlogined()+ygcomsta.getUserlogined()+
zhzhsta.getUserlogined()+xddsta.getUserlogined()+androidsta.getUserlogined()+iossta.getUserlogined()+websta.getUserlogined()+wechatsta.getUserlogined()+synsta.getUserlogined()+bbtsta.getUserlogined() %></h4></td>
<td width="120"><h4><%=
kuxsta.getUsersyned()+hcomsta.getUsersyned()+kdcomsta.getUsersyned()+ygcomsta.getUsersyned()+
androidsta.getUsersyned()+xddsta.getUsersyned()+zhzhsta.getUsersyned()+iossta.getUsersyned()+websta.getUsersyned()+wechatsta.getUsersyned()+synsta.getUsersyned()+bbtsta.getUsersyned()%></h4></td>
<td width="120"><h4><%=
kuxsta.getPv()+hcomsta.getPv()+kdcomsta.getPv()+ygcomsta.getPv()+
xddsta.getPv()+zhzhsta.getPv()+androidsta.getPv()+iossta.getPv()+websta.getPv()+wechatsta.getPv()+synsta.getPv()+bbtsta.getPv()%></h4></td>
<td width="120"><h4><%=
kuxsta.getUv()+hcomsta.getUv()+kdcomsta.getUv()+ygcomsta.getUv()+
xddsta.getUv()+zhzhsta.getUv()+androidsta.getUv()+iossta.getUv()+websta.getUv()+wechatsta.getUv()+synsta.getUv()+bbtsta.getUv()%></h4></td>
<td width="120"><h4><%=
kuxsta.getIp()+hcomsta.getIp()+kdcomsta.getIp()+ygcomsta.getIp()+
xddsta.getIp()+zhzhsta.getIp()+androidsta.getIp()+iossta.getIp()+websta.getIp()+wechatsta.getIp()+synsta.getIp()+bbtsta.getIp()%></h4></td>
</tr>
<tr style="height: 42px;"></tr>
<%
if(iossta!=null){
%>
<tr  style="color: blue;">
<td width="200"><h4>-苹果</h4></td>
<td width="200"><h4><%=iossta.getUsersaved() %></h4></td>
<td width="120"><h4><%=iossta.getUserlogined() %></h4></td>
<td width="190"><h4><%=iossta.getUsersyned() %></h4></td>
<td width="120"><h4><%=iossta.getPv() %></h4></td>
<td width="120"><h4><%=iossta.getUv() %></h4></td>
<td width="120"><h4><%=iossta.getIp() %></h4></td>
</tr>
<%
} %>
<%
if(androidsta!=null){
%>
<tr style="color: blue;">
<td width="200"><h4>-安卓</h4></td>
<td width="200"><h4><%=androidsta.getUsersaved() %></h4></td>
<td width="120"><h4><%=androidsta.getUserlogined() %></h4></td>
<td width="190"><h4><%=androidsta.getUsersyned() %></h4></td>
<td width="120"><h4><%=androidsta.getPv() %></h4></td>
<td width="120"><h4><%=androidsta.getUv() %></h4></td>
<td width="120"><h4><%=androidsta.getIp() %></h4></td>
</tr>
<%
} 
%>

<%
if(websta!=null){
%>
<tr style="color: blue;">
<td width="200"><h4>-网页</h4></td>
<td width="200"><h4><%=websta.getUsersaved() %></h4></td>
<td width="120"><h4><%=websta.getUserlogined() %></h4></td>
<td width="190"><h4><%=websta.getUsersyned() %></h4></td>
<td width="120"><h4><%=websta.getPv() %></h4></td>
<td width="120"><h4><%=websta.getUv() %></h4></td>
<td width="120"><h4><%=websta.getIp() %></h4></td>
</tr>

<%
} 
%>

<%
if(wechatsta!=null){
%>
<tr style="color: blue;">
<td width="200"><h4>-微信</h4></td>
<td width="200"><h4><%=wechatsta.getUsersaved() %></h4></td>
<td width="120"><h4><%=wechatsta.getUserlogined() %></h4></td>
<td width="190"><h4><%=wechatsta.getUsersyned() %></h4></td>
<td width="120"><h4><%=wechatsta.getPv() %></h4></td>
<td width="120"><h4><%=wechatsta.getUv() %></h4></td>
<td width="120"><h4><%=wechatsta.getIp() %></h4></td>
</tr>

<%
} 
%>



</table>
</form>


<br>

<!-- 居中 -->
<CENTER></CENTER>
<form  style="width: 60%;"  action="">
<table style="text-align:center;" border=1 >
<tr>
<td ><h4>各项点击率</h4></td>
<td ><h4>今日必读</h4></td>

<td><h4>社区互动</h4></td>
<td><h4>贴心服务</h4></td>
<td><h4>家长来稿</h4></td>
<td><h4>搜学区房</h4></td>
<td><h4>教育部推荐用书</h4></td>
<td><h4>意见反馈</h4></td>
<td><h4>幼儿基础阅读</h4></td>
<td><h4>小学生必读</h4></td>
<td><h4>初中课外阅读</h4></td>
<td><h4>高中课外阅读</h4></td>
</tr>


<%
if(bbtsta!=null){
%>

<tr style="color: red;">
<td ><h4>掌中校园</h4></td>
<td><h4><%=bbtsta.getHome1() %></h4></td>
<td><h4><%=bbtsta.getHome2() %></h4></td>
<td ><h4><%=bbtsta.getHome3()  %></h4></td>
<td ><h4><%=bbtsta.getHome31()  %></h4></td>
<td ><h4><%=bbtsta.getHome32()  %></h4></td>
<td><h4><%=bbtsta.getHome33()  %></h4></td>
<td ><h4><%=bbtsta.getHome34()  %></h4></td>
<td ><h4><%=bbtsta.getHome331()  %></h4></td>
<td><h4><%=bbtsta.getHome332()  %></h4></td>
<td ><h4><%=bbtsta.getHome333()  %></h4></td>
<td ><h4><%=bbtsta.getHome334()  %></h4></td>
</tr>
<%
} 
%>


<%
if(synsta!=null){
%>
<tr style="color:green;">
<td width="200"><h4>优教通</h4></td>
<td><h4><%=synsta.getHome1() %></h4></td>
<td><h4><%=synsta.getHome2() %></h4></td>
<td ><h4><%=synsta.getHome3()  %></h4></td>
<td ><h4><%=synsta.getHome31()  %></h4></td>
<td ><h4><%=synsta.getHome32()  %></h4></td>
<td><h4><%=synsta.getHome33()  %></h4></td>
<td ><h4><%=synsta.getHome34()  %></h4></td>
<td ><h4><%=synsta.getHome331()  %></h4></td>
<td><h4><%=synsta.getHome332()  %></h4></td>
<td ><h4><%=synsta.getHome333()  %></h4></td>
<td ><h4><%=synsta.getHome334()  %></h4></td>
</tr>

<%
} 
%>

<%
if(zhzhsta!=null){
%>
<tr style="color:green;">
<td width="200"><h4>电信 翼校通</h4></td>
<td><h4><%=zhzhsta.getHome1() %></h4></td>
<td><h4><%=zhzhsta.getHome2() %></h4></td>
<td ><h4><%=zhzhsta.getHome3()  %></h4></td>
<td ><h4><%=zhzhsta.getHome31()  %></h4></td>
<td ><h4><%=zhzhsta.getHome32()  %></h4></td>
<td><h4><%=zhzhsta.getHome33()  %></h4></td>
<td ><h4><%=zhzhsta.getHome34()  %></h4></td>
<td ><h4><%=zhzhsta.getHome331()  %></h4></td>
<td><h4><%=zhzhsta.getHome332()  %></h4></td>
<td ><h4><%=zhzhsta.getHome333()  %></h4></td>
<td ><h4><%=zhzhsta.getHome334()  %></h4></td>
</tr>

<%
} 
%>

<%
if(xddsta!=null){
%>
<tr style="color:green;">
<td width="200"><h4>学多多</h4></td>
<td><h4><%=xddsta.getHome1() %></h4></td>
<td><h4><%=xddsta.getHome2() %></h4></td>
<td ><h4><%=xddsta.getHome3()  %></h4></td>
<td ><h4><%=xddsta.getHome31()  %></h4></td>
<td ><h4><%=xddsta.getHome32()  %></h4></td>
<td><h4><%=xddsta.getHome33()  %></h4></td>
<td ><h4><%=xddsta.getHome34()  %></h4></td>
<td ><h4><%=xddsta.getHome331()  %></h4></td>
<td><h4><%=xddsta.getHome332()  %></h4></td>
<td ><h4><%=xddsta.getHome333()  %></h4></td>
<td ><h4><%=xddsta.getHome334()  %></h4></td>
</tr>

<%
} 
%>
<%
if(kuxsta!=null){
%>
<tr style="color:green;">
<td width="200"><h4>酷校</h4></td>
<td><h4><%=kuxsta.getHome1() %></h4></td>
<td><h4><%=kuxsta.getHome2() %></h4></td>
<td ><h4><%=kuxsta.getHome3()  %></h4></td>
<td ><h4><%=kuxsta.getHome31()  %></h4></td>
<td ><h4><%=kuxsta.getHome32()  %></h4></td>
<td><h4><%=kuxsta.getHome33()  %></h4></td>
<td ><h4><%=kuxsta.getHome34()  %></h4></td>
<td ><h4><%=kuxsta.getHome331()  %></h4></td>
<td><h4><%=kuxsta.getHome332()  %></h4></td>
<td ><h4><%=kuxsta.getHome333()  %></h4></td>
<td ><h4><%=kuxsta.getHome334()  %></h4></td>
</tr>

<%
} 
%>
<%
if(hcomsta!=null){
%>
<tr style="color:green;">
<td width="200"><h4>恒谦教育</h4></td>
<td><h4><%=hcomsta.getHome1() %></h4></td>
<td><h4><%=hcomsta.getHome2() %></h4></td>
<td ><h4><%=hcomsta.getHome3()  %></h4></td>
<td ><h4><%=hcomsta.getHome31()  %></h4></td>
<td ><h4><%=hcomsta.getHome32()  %></h4></td>
<td><h4><%=hcomsta.getHome33()  %></h4></td>
<td ><h4><%=hcomsta.getHome34()  %></h4></td>
<td ><h4><%=hcomsta.getHome331()  %></h4></td>
<td><h4><%=hcomsta.getHome332()  %></h4></td>
<td ><h4><%=hcomsta.getHome333()  %></h4></td>
<td ><h4><%=hcomsta.getHome334()  %></h4></td>
</tr>

<%
} 
%>
<%
if(kdcomsta!=null){
%>
<tr style="color:green;">
<td width="200"><h4>阔地教育</h4></td>
<td><h4><%=kdcomsta.getHome1() %></h4></td>
<td><h4><%=kdcomsta.getHome2() %></h4></td>
<td ><h4><%=kdcomsta.getHome3()  %></h4></td>
<td ><h4><%=kdcomsta.getHome31()  %></h4></td>
<td ><h4><%=kdcomsta.getHome32()  %></h4></td>
<td><h4><%=kdcomsta.getHome33()  %></h4></td>
<td ><h4><%=kdcomsta.getHome34()  %></h4></td>
<td ><h4><%=kdcomsta.getHome331()  %></h4></td>
<td><h4><%=kdcomsta.getHome332()  %></h4></td>
<td ><h4><%=kdcomsta.getHome333()  %></h4></td>
<td ><h4><%=kdcomsta.getHome334()  %></h4></td>
</tr>

<%
} 
%>
<%
if(ygcomsta!=null){
%>
<tr style="color:green;">
<td width="200"><h4>亿谷教育</h4></td>
<td><h4><%=ygcomsta.getHome1() %></h4></td>
<td><h4><%=ygcomsta.getHome2() %></h4></td>
<td ><h4><%=ygcomsta.getHome3()  %></h4></td>
<td ><h4><%=ygcomsta.getHome31()  %></h4></td>
<td ><h4><%=ygcomsta.getHome32()  %></h4></td>
<td><h4><%=ygcomsta.getHome33()  %></h4></td>
<td ><h4><%=ygcomsta.getHome34()  %></h4></td>
<td ><h4><%=ygcomsta.getHome331()  %></h4></td>
<td><h4><%=ygcomsta.getHome332()  %></h4></td>
<td ><h4><%=ygcomsta.getHome333()  %></h4></td>
<td ><h4><%=ygcomsta.getHome334()  %></h4></td>
</tr>

<%
} 
%>

<tr style="color: blue;">
<td width="200"><h4>家长之友</h4></td>
<td><h4><%=websta.getHome1()+wechatsta.getHome1()+androidsta.getHome1()+iossta.getHome1()%></h4></td>
<td><h4><%=websta.getHome2()+wechatsta.getHome2()+androidsta.getHome2()+iossta.getHome2()%></h4></td>
<td><h4><%=websta.getHome3()+wechatsta.getHome3()+androidsta.getHome3()+iossta.getHome3()%></h4></td>
<td><h4><%=websta.getHome31()+wechatsta.getHome31()+androidsta.getHome31()+iossta.getHome31()%></h4></td>
<td><h4><%=websta.getHome32()+wechatsta.getHome32()+androidsta.getHome32()+iossta.getHome32()%></h4></td>
<td><h4><%=websta.getHome33()+wechatsta.getHome33()+androidsta.getHome33()+iossta.getHome33()%></h4></td>
<td><h4><%=websta.getHome34()+wechatsta.getHome34()+androidsta.getHome34()+iossta.getHome34()%></h4></td>
<td><h4><%=websta.getHome331()+wechatsta.getHome331()+androidsta.getHome331()+iossta.getHome331()%></h4></td>
<td><h4><%=websta.getHome332()+wechatsta.getHome332()+androidsta.getHome332()+iossta.getHome332()%></h4></td>
<td><h4><%=websta.getHome333()+wechatsta.getHome333()+androidsta.getHome333()+iossta.getHome333()%></h4></td>
<td><h4><%=websta.getHome334()+wechatsta.getHome334()+androidsta.getHome334()+iossta.getHome334()%></h4></td>
</tr>
<tr style="color: fuchsia;">
<td width="200"><h4>-ALL</h4></td>
<td><h4><%= ygcomsta.getHome1()+xddsta.getHome1()+hcomsta.getHome1()+kuxsta.getHome1()+kdcomsta.getHome1()+
websta.getHome1()+wechatsta.getHome1()+androidsta.getHome1()+iossta.getHome1()+synsta.getHome1()+bbtsta.getHome1()%></h4></td>
<td><h4><%=ygcomsta.getHome2()+
xddsta.getHome2()+hcomsta.getHome2()+kuxsta.getHome2()+kdcomsta.getHome2()+
websta.getHome2()+wechatsta.getHome2()+androidsta.getHome2()+iossta.getHome2()+synsta.getHome2()+bbtsta.getHome2()%></h4></td>
<td><h4><%=ygcomsta.getHome3()+
xddsta.getHome3()+hcomsta.getHome3()+kuxsta.getHome3()+kdcomsta.getHome3()+
websta.getHome3()+wechatsta.getHome3()+androidsta.getHome3()+iossta.getHome3()+synsta.getHome3()+bbtsta.getHome3()%></h4></td>
<td><h4><%=ygcomsta.getHome31()+
xddsta.getHome31()+hcomsta.getHome31()+kuxsta.getHome31()+kdcomsta.getHome31()+
websta.getHome31()+wechatsta.getHome31()+androidsta.getHome31()+iossta.getHome31()+synsta.getHome31()+bbtsta.getHome31()%></h4></td>
<td><h4><%=ygcomsta.getHome32()+
xddsta.getHome32()+hcomsta.getHome32()+kuxsta.getHome32()+kdcomsta.getHome32()+
websta.getHome32()+wechatsta.getHome32()+androidsta.getHome32()+iossta.getHome32()+synsta.getHome32()+bbtsta.getHome32()%></h4></td>
<td><h4><%=ygcomsta.getHome33()+
xddsta.getHome33()+hcomsta.getHome33()+kuxsta.getHome33()+kdcomsta.getHome33()+
websta.getHome33()+wechatsta.getHome33()+androidsta.getHome33()+iossta.getHome33()+synsta.getHome33()+bbtsta.getHome33()%></h4></td>
<td><h4><%=ygcomsta.getHome34()+
xddsta.getHome34()+hcomsta.getHome34()+kuxsta.getHome34()+kdcomsta.getHome34()+
websta.getHome34()+wechatsta.getHome34()+androidsta.getHome34()+iossta.getHome34()+synsta.getHome34()+bbtsta.getHome34()%></h4></td>
<td><h4><%=ygcomsta.getHome331()+
xddsta.getHome331()+hcomsta.getHome331()+kuxsta.getHome331()+kdcomsta.getHome331()+
websta.getHome331()+wechatsta.getHome331()+androidsta.getHome331()+iossta.getHome331()+synsta.getHome331()+bbtsta.getHome331()%></h4></td>
<td><h4><%=ygcomsta.getHome332()+
xddsta.getHome332()+hcomsta.getHome332()+kuxsta.getHome332()+kdcomsta.getHome332()+
websta.getHome332()+wechatsta.getHome332()+androidsta.getHome332()+iossta.getHome332()+synsta.getHome332()+bbtsta.getHome332()%></h4></td>
<td><h4><%=ygcomsta.getHome333()+
xddsta.getHome333()+hcomsta.getHome333()+kuxsta.getHome333()+kdcomsta.getHome333()+
websta.getHome333()+wechatsta.getHome333()+androidsta.getHome333()+iossta.getHome333()+synsta.getHome333()+bbtsta.getHome333()%></h4></td>
<td><h4><%=ygcomsta.getHome334()+
xddsta.getHome334()+hcomsta.getHome334()+kuxsta.getHome334()+kdcomsta.getHome334()+
websta.getHome334()+wechatsta.getHome334()+androidsta.getHome334()+iossta.getHome334()+synsta.getHome334()+bbtsta.getHome334()%></h4></td>
</tr>
<tr style="height: 42px;"></tr>
<%
if(iossta!=null){
%>
<tr  style="color: blue;">
<td width="200"><h4>-苹果</h4></td>
<td><h4><%=iossta.getHome1() %></h4></td>
<td><h4><%=iossta.getHome2() %></h4></td>
<td ><h4><%=iossta.getHome3()  %></h4></td>
<td ><h4><%=iossta.getHome31()  %></h4></td>
<td ><h4><%=iossta.getHome32()  %></h4></td>
<td><h4><%=iossta.getHome33()  %></h4></td>
<td ><h4><%=iossta.getHome34()  %></h4></td>
<td ><h4><%=iossta.getHome331()  %></h4></td>
<td><h4><%=iossta.getHome332()  %></h4></td>
<td ><h4><%=iossta.getHome333()  %></h4></td>
<td ><h4><%=iossta.getHome334()  %></h4></td>
</tr>
<%
} 
%>
<%
if(androidsta!=null){
%>
<tr style="color: blue;">
<td width="200"><h4>-安卓</h4></td>
<td><h4><%=androidsta.getHome1() %></h4></td>
<td><h4><%=androidsta.getHome2() %></h4></td>
<td ><h4><%=androidsta.getHome3()  %></h4></td>
<td ><h4><%=androidsta.getHome31()  %></h4></td>
<td ><h4><%=androidsta.getHome32()  %></h4></td>
<td><h4><%=androidsta.getHome33()  %></h4></td>
<td ><h4><%=androidsta.getHome34()  %></h4></td>
<td ><h4><%=androidsta.getHome331()  %></h4></td>
<td><h4><%=androidsta.getHome332()  %></h4></td>
<td ><h4><%=androidsta.getHome333()  %></h4></td>
<td ><h4><%=androidsta.getHome334()  %></h4></td>
</tr>
<%
} %>
<%
if(websta!=null){
%>
<tr style="color: blue;">
<td width="200"><h4>-网页</h4></td>
<td><h4><%=websta.getHome1() %></h4></td>6
<td><h4><%=websta.getHome2() %></h4></td>
<td ><h4><%=websta.getHome3()  %></h4></td>
<td ><h4><%=websta.getHome31()  %></h4></td>
<td ><h4><%=websta.getHome32()  %></h4></td>
<td><h4><%=websta.getHome33()  %></h4></td>
<td ><h4><%=websta.getHome34()  %></h4></td>
<td ><h4><%=websta.getHome331()  %></h4></td>
<td><h4><%=websta.getHome332()  %></h4></td>
<td ><h4><%=websta.getHome333()  %></h4></td>
<td ><h4><%=websta.getHome334()  %></h4></td>
</tr>

<%
} %>
<%
if(wechatsta!=null){
%>
<tr style="color: blue;">
<td width="200"><h4>-微信</h4></td>
<td><h4><%=wechatsta.getHome1() %></h4></td>
<td><h4><%=wechatsta.getHome2() %></h4></td>
<td ><h4><%=wechatsta.getHome3()  %></h4></td>
<td ><h4><%=wechatsta.getHome31()  %></h4></td>
<td ><h4><%=wechatsta.getHome32()  %></h4></td>
<td><h4><%=wechatsta.getHome33()  %></h4></td>
<td ><h4><%=wechatsta.getHome34()  %></h4></td>
<td ><h4><%=wechatsta.getHome331()  %></h4></td>
<td><h4><%=wechatsta.getHome332()  %></h4></td>
<td ><h4><%=wechatsta.getHome333()  %></h4></td>
<td ><h4><%=wechatsta.getHome334()  %></h4></td>
</tr>

<%
} %>


</table>
</form>

</body>
</html>