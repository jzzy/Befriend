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
<script type="text/javascript" src="sample_lihu/js/jquery-1.10.2.min.js"></script>
<script type="text/javascript" src="sample_lihu/js/common.js"></script>
<link href="sample_lihu/css/style.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" href="sample_lihu/css/pullToRefresh.css"/>
</head>
<body>
	 <%

		//获取新闻分类
		int type=(Integer)request.getAttribute("tp");
		

	 System.out.println("type"+type);
	 
		
   String text=null;
   String pth=null;
   String img="";
  
   
   if(type==1){
	   System.out.println("type1"+type);
		 System.out.println("pth1"+pth);
	   pth="/webNewtype?tp=1";
	   img="9.gif";
	   text="";
   }
   if(type==5){
	   img="10.gif";
	   pth="/webNewtype?tp=5";
	   text="如何发现孩子的兴趣爱好及特长？在这里会给你答案，留心观察孩子的才能，帮助众多家长选择孩子的最佳成才之路。";
   }
   if(type==2){
	   img="5.gif";
	   pth="/webNewtype?tp=2";
	   text="教子有方，如何正确的教育孩子？在这里教育专家专门为年轻的家长做了最实用、最科学的教子经验总结。";
   }
   if(type==3){
	   img="2.gif";
	   pth="/webNewtype?tp=3";
	   text="重视家庭教育，关注身心健康，陪伴孩子快乐成长，正确赞赏和激励促使孩子进步，分享育孩子成长相关的宝贵经验。";
   }
   if(type==4){
	   img="3.gif";
	   pth="/webNewtype?tp=4";
	   text="整理众多有关留学咨询的问题，共同分析留学方案的具体问题、就读专业等，分享国际院校海外深造的最佳捷径。";
   }
   if(type==6){
	   img="6.gif";
	   pth="/webNewtype?tp=6";
	   text="汇集数位顶尖名人的教子启示录，分享教育经验，启发父母的正确教育观念。";
   }
   if(type==7){
	   img="4.gif";
	   pth="/webNewtype?tp=7";
	   text="提供日常有助孩子最权威、最实用的保健知识，共同关注家庭健康的生活方式。";
   }
   if(type==8){
	   img="7.gif";
	   pth="/webNewtype?tp=8";
	   text="这里主要有一些好玩的奇闻趣事、人生感悟，更有幽默风趣的故事小品、家庭幽默与无忌童言。";
   }
   if(type==9){
	   img="8.gif";
	   pth="/webHottest";
	   text="这里主要有一些好玩的奇闻趣事、人生感悟，更有幽默风趣的故事小品、家庭幽默与无忌童言。";
   }
   if(type==10){ img="1.gif";
	   pth="/webHotareaf";
	   text="这里主要有一些好玩的奇闻趣事、人生感悟，更有幽默风趣的故事小品、家庭幽默与无忌童言。";
   }
	 System.out.println("type"+type);
	 System.out.println("pth"+pth);
	 
   if(pth==null){
	   pth="logod.png";
	   return;
   }
   
   %>
	<div id="wrap">
		<div id="header">
			<div class="topArea clearfix">
				<h1><a href="webNewsA10"><img src="sample_lihu/images/logo.gif" alt="家长之友" /></a></h1>
				<div class="relocation">
					<a href="sample_lihu/jsp/relocation.jsp">北京&nbsp;<img src="sample_lihu/images/location_more.gif" alt="down" /></a>
				</div>
				<div class="topTool">
					<span class="mapLink"><a href="sample_lihu/jsp/sitemap.jsp"><img src="sample_lihu/images/toptoolBtn.gif" alt="菜单" /></a></span>
				</div>
			</div>
			<div class="mainList">
				<h2 class="tit2"><img src="sample_lihu/images/<%=img %>" alt="今日必读" /></h2>
			</div>
		</div><!--header-->
		
		<div id="container" class="load">
			<iframe id="mainiframe" src="<%=request.getContextPath()+pth %>" frameborder="0" scrolling="no" width="100%"></iframe>
		</div><!--container-->
		
		
	</div><!--wrap-->
	


</body>
</html>
