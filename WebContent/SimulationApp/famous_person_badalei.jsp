<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
     <%@page import="java.util.*"%>
    <%@page import="com.befriend.util.*"%>
<%@page import="com.befriend.entity.*"%>
<%


//获取总的页数
int a=(Integer)request.getAttribute("a");
//获取第几页
int currentPage=(Integer)request.getAttribute("currentPage");
//获取新闻分类
String type=(String)request.getAttribute("type");
//获取新闻分类代号
int tp=(Integer)request.getAttribute("tp");
//获取新闻
List<News> nl=(List)request.getAttribute("nl");
if(nl==null||currentPage<=0||type==null){
	
	%> 
	<p>没有您要的信息</p>
	<% 
	return;
}

%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title><%=type %></title>
<link href="SimulationApp/css/style.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="SimulationApp/js/jquery-1.2.6.pack.js"></script>
<style type="text/css">
.main{width:100%;height:auto;}
.left{width:80%;height:auto;float:left;}
.right{width:20%;height:auto;float:left;}
</style>


</head>

<body>
<div class="cont">
   <h1 class="tit tit5"><a style="float:left;" href="<%=request.getContextPath()%>/webNewsA10" class="fl"><span>&lt; 今日必读</span></a></h1>
   <div class="sstop">

   <%
   String text=null;
   String pth=null;
   if(type.equals("升学指南")){
	   pth="1sx.png";
	   text="";
   }
   if(type.equals("兴趣特长")){
	   pth="4xq.png";
	   text="如何发现孩子的兴趣爱好及特长？在这里会给你答案，留心观察孩子的才能，帮助众多家长选择孩子的最佳成才之路。";
   }
   if(type.equals("教子经验")){
	   pth="2jz.png";
	   text="教子有方，如何正确的教育孩子？在这里教育专家专门为年轻的家长做了最实用、最科学的教子经验总结。";
   }
   if(type.equals("成长路上")){
	   pth="3cz.png";
	   text="重视家庭教育，关注身心健康，陪伴孩子快乐成长，正确赞赏和激励促使孩子进步，分享育孩子成长相关的宝贵经验。";
   }
   if(type.equals("出国留学")){
	   pth="5lx.png";
	   text="整理众多有关留学咨询的问题，共同分析留学方案的具体问题、就读专业等，分享国际院校海外深造的最佳捷径。";
   }
   if(type.equals("名人教子")){
	   pth="6mr.png";
	   text="汇集数位顶尖名人的教子启示录，分享教育经验，启发父母的正确教育观念。";
   }
   if(type.equals("健康导航")){
	   pth="7jk.png";
	   text="提供日常有助孩子最权威、最实用的保健知识，共同关注家庭健康的生活方式。";
   }
   if(type.equals("轻松驿站")){
	   pth="8qs.png";
	   text="这里主要有一些好玩的奇闻趣事、人生感悟，更有幽默风趣的故事小品、家庭幽默与无忌童言。";
   }
   if(pth==null){
	   pth="logod.png";
   }
   
   %>
       <p class="simg"><img src="SimulationApp/images/<%=pth %>" height="120" /></p><p class="scont"><span><%=text %></span></p>
   </div>
   <ul class="specil_list">
   <%
   for(int i=0;i<nl.size();i++){
   %>
      
 
<style type="text/css">
.main{width:100%;height:auto;}
.left{width:80%;height:auto;float:left;}
.right{width:20%;height:auto;float:left;}
</style>
      
      <li>
      <div class="main">
      	 <div class="left">
      	 <a href="<%=request.getContextPath()%>/webNewsId?id=<%=nl.get(i).getId()%>">
      	 	<span style="color: black; font-size: 16px;"><strong><%=nl.get(i).getTitle() %></strong></span>
      	 	<br />
      	 	<b> <%=nl.get(i).getTime() %></b>
      	 	</a>
      	 </div>
    	<div class="right">
    		<a href="<%=request.getContextPath()%>/webNewsId?id=<%=nl.get(i).getId()%>">
      			<b style="float:right;margin-top: 6px;"><img src="SimulationApp/images/xing.png" /><%=nl.get(i).getHits()%></b>
	       	</a>
    	</div>
      </div>
      
      
      
      
       
     
      </li>
   
    <%
   }
    %> 
   </ul>
   <br/>一共<%=a %>页 当前是第<%=currentPage %>页
</div>


  <!--more4-->

<ul class="page">

<li><a href="<%=request.getContextPath()%>/webNewtype?pageSize=10&currentPage=1&tp=<%=tp%>">首页</a></li>
<li><a href="<%=request.getContextPath()%>/webNewtype?pageSize=10&currentPage=<%=currentPage-1%>&tp=<%=tp%> ">上一页</a></li>
<li><a href="<%=request.getContextPath()%>/webNewtype?pageSize=10&currentPage=<%=currentPage+1%>&tp=<%=tp%> ">下一页</a></li>
<li><a href="<%=request.getContextPath()%>/webNewtype?pageSize=10&currentPage=<%=a %>&tp=<%=tp%>">末页</a></li>
</ul>
<!--  
<ul class="page">
<li>&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp</li>
<li><a href="">共<%=a%>页</a></li>
<li>&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp</li>
<li><a href="">第<%=currentPage%>页</a></li>
<li>&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp</li>
</ul>
-->
<!--cont-->


</body>
</html>
