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

</head>

<body>
<div class="cont">
   <h1 class="tit tit6"><a href="<%=request.getContextPath()%>/webNewsA10" class="fl">&lt; 今日必读</a></h1>
   <div class="sstop">
   
   <%
   String text=null;
   String pth=null;
   if(type.equals("小升初")){
	   pth="2xsc.png";
	   text="整理各城区重点初中招生简章，涵盖学校划片对口信息、升学成绩分析、家长评论、学校详解等内容。";
   }
   if(type.equals("幼升小")){
	   pth="3yx.png";
	   text="涵盖当年小学入学政策、招考资讯，并收录各城区重点小学最新招生简章，汇总家长评论，择校经验，多篇章呈现立体化升学专题。";
   }
   if(type.equals("中考")){
	   pth="4zk.png";
	   text="从招生报名条件、学校基本信息、中考升学成绩、备考资料、考试必知等多方面，汇总分析择校经验，为升学之路提供参考。";
   }
   if(type.equals("高考")){
	   pth="4gk.png";
	   text="包含最具实用价值的高考生升学指南，根据数年二十余万成功案例的经验，上千精英专家专业分析结果，择取相应精选资讯。";
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
      
      
      
      
       
     
      </li>    <%
   }
    %> 
   </ul>
   <br/>一共<%=a %>页 当前是第<%=currentPage %>页
</div>


  <!--more4-->

<ul class="page">

<li><a href="<%=request.getContextPath()%>/webTypes?pageSize=10&currentPage=1&tp=<%=tp%>">首页</a></li>
<li><a href="<%=request.getContextPath()%>/webTypes?pageSize=10&currentPage=<%=currentPage-1%>&tp=<%=tp%>">上一页</a></li>
<li><a href="<%=request.getContextPath()%>/webTypes?pageSize=10&currentPage=<%=currentPage+1%>&tp=<%=tp%>">下一页</a></li>
<li><a href="<%=request.getContextPath()%>/webTypes?pageSize=10&currentPage=<%=a %>&tp=<%=tp%>">末页</a></li>
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
