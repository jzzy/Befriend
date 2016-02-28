<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    <%@page import="java.util.*"%>
    <%@page import="com.befriend.util.*"%>
<%@page import="com.befriend.entity.*"%>
<%
  
//按照时间排序  11个
@SuppressWarnings("all")
List<News> Hottime=(List)request.getAttribute("Hottime");
//最热3

List<News> Hottest=(List)request.getAttribute("Hottest");
//本地新闻2

List<News> Hotarea=(List)request.getAttribute("Hotarea");
//轻松驿站1

List<News> typeqs=(List)request.getAttribute("typeqs");
//健康导航1

List<News> typejk=(List)request.getAttribute("typejk");
if(Hottime==null||Hottest==null||Hotarea==null||typeqs==null||typejk==null){
	return;
}
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title></title>
<script type="text/javascript"  src="sample_lihu/js/jquery-1.10.2.min.js"></script>
<script type="text/javascript" src="sample_lihu/js/common.js"></script>
<script type="text/javascript">
$(document).ready(function(){
	
	//alert("cookie:"+document.cookie);
	$.ajax({
		type:"post",
		url:"statisticsPVIP.action?id=1"	
	});
});

</script>
<link href="sample_lihu/css/style.css" rel="stylesheet" type="text/css" />
</head>
<body>
	<div id="wrap">
		<div id="header">
			<div class="topArea clearfix">
				<h1><a href="webNewsA10"><img src="sample_lihu/images/logo.gif" alt="家长之友" /></a></h1>
				<div class="relocation">
					<a href="sample_lihu/jsp/relocation.jsp">北京&nbsp;<img src="sample_lihu/images/location_more.gif" alt="down" /></a>
				</div>
				<div class="topTool">
					<span class="mapLink"><a href="sample_lihu/sitemap.html"><img src="sample_lihu/images/toptoolBtn.gif" alt="菜单" /></a></span>
				</div>
			</div>
			<ul class="topMenu clearfix">
				<li class="on"><a href="webNewsA10"><img src="sample_lihu/images/hottoday_ico.gif" alt="fire" />&nbsp;今日必读</a></li>
				<li><a href="webForumApptype">社区互动</a></li>
				<li><a href="webGetapp">贴心服务</a></li>
				<li><a href="sample_lihu/jsp/mypage.jsp">我的信息</a></li>
			</ul>
		</div><!--header-->
		<div id="container">
			<div class="today mainList">
				<h2 class="tit2"><img src="sample_lihu/images/today_tit.gif" alt="今日必读" /></h2>
				<ul>
			 
  <%
						for(int i=0;i<Hottime.size();i++){
						News n=Hottime.get(i);
						
						%>
			<a href="webNewsId?id=<%=n.getId()%>" >
					<li>
						<div class="infoArea">
							<p class="title"><img src="sample_lihu/images/hot_ico.gif" alt="hot" /><%=n.getTitle() %></p>
							<p class="info">
								<span><img src="sample_lihu/images/comment_ico.gif" alt="留言" />&nbsp;<%=n.getReviews() %></span>
								<span><img src="sample_lihu/images/favor_ico.gif" alt="关注" />&nbsp;<%=n.getCollectnum() %></span>
							</p>
						</div>
						<div class="imgArea">
							<p class="big_img"><img src="<%=request.getContextPath()+n.getImgmax() %>" alt="<%=request.getContextPath()+n.getImgmax() %>" /></p>
						</div>
					</li>
					</a>
					<%
							}
					%>
				</ul>
			</div>
			<div class="local mainList">
				<h2 class="tit2"><a href="#"><img src="sample_lihu/images/localinfo_tit.gif" alt="本地资讯" /></a></h2>
				<ul>
				<%
						for(int i=0;i<Hotarea.size();i++){
						News n=Hotarea.get(i);
						
						%>
				<a href="webNewsId?id=<%=n.getId()%>" >
					<li>
						<div class="infoArea">
							<p class="title"><img src="sample_lihu/images/hot_ico.gif" alt="hot" /><%=n.getTitle() %></p>
							<p class="info">
								<span><img src="sample_lihu/images/comment_ico.gif" alt="留言" />&nbsp;<%=n.getReviews() %></span>
								<span><img src="sample_lihu/images/favor_ico.gif" alt="关注" />&nbsp;<%=n.getCollectnum() %></span>
							</p>
						</div>
						<div class="imgArea">
							<p class="big_img"><img src="<%=request.getContextPath()+n.getImgmax() %>" alt="<%=request.getContextPath()+n.getImgmax() %>" /></p>
						</div>
					</li>
					</a>
					<%
							}
					%>
				</ul>
			</div>
			<div class="health mainList">
				<h2 class="tit2"><a href="healthguide.html"><img src="sample_lihu/images/healthguide_tit.gif" alt="健康导航" /></a></h2>
				<ul>
					<%
						for(int i=0;i<typejk.size();i++){
						News n=typejk.get(i);
						
						%>
				<a href="webNewsId?id=<%=n.getId()%>" >
					<li>
						<div class="infoArea">
							<p class="title"><img src="sample_lihu/images/hot_ico.gif" alt="hot" /><%=n.getTitle() %></p>
							<p class="info">
								<span><img src="sample_lihu/images/comment_ico.gif" alt="留言" />&nbsp;<%=n.getReviews() %></span>
								<span><img src="sample_lihu/images/favor_ico.gif" alt="关注" />&nbsp;<%=n.getCollectnum() %></span>
							</p>
						</div>
						<div class="imgArea">
							<p class="big_img"><img src="<%=request.getContextPath()+n.getImgmax() %>" alt="<%=request.getContextPath()+n.getImgmax() %>" /></p>
						</div>
					</li>
					</a>
					<%
							}
					%>
				</ul>
			</div>
			<div class="health2 mainList">
				<h2 class="tit2"><a href="#"><img src="sample_lihu/images/healthguide2_tit.gif" alt="轻松驿站" /></a></h2>
				<ul>
				<%
						for(int i=0;i<typeqs.size();i++){
						News n=typeqs.get(i);
						
						%>
				<a href="webNewsId?id=<%=n.getId()%>" >
					<li>
						<div class="infoArea">
							<p class="title"><img src="sample_lihu/images/hot_ico.gif" alt="hot" /><%=n.getTitle() %></p>
							<p class="info">
								<span><img src="sample_lihu/images/comment_ico.gif" alt="留言" />&nbsp;<%=n.getReviews() %></span>
								<span><img src="sample_lihu/images/favor_ico.gif" alt="关注" />&nbsp;<%=n.getCollectnum() %></span>
							</p>
						</div>
						<div class="imgArea">
							<p class="big_img"><img src="<%=request.getContextPath()+n.getImgmax() %>" alt="<%=request.getContextPath()+n.getImgmax() %>" /></p>
						</div>
					</li>
					</a>
					<%
							}
					%>
				</ul>
			</div>
			<div class="rank mainList">
				<h2 class="tit2"><a href="newsdetail.html"><img src="sample_lihu/images/rank_tit.gif" alt="热门排名" /></a></h2>
				<ul>
					<%
						for(int i=0;i<Hottest.size();i++){
						News n=Hottest.get(i);
						
						%>
				<a href="webNewsId?id=<%=n.getId()%>" >
					<li>
						<div class="infoArea">
							<p class="title"><img src="sample_lihu/images/hot_ico.gif" alt="hot" /><%=n.getTitle() %></p>
							<p class="info">
								<span><img src="sample_lihu/images/comment_ico.gif" alt="留言" />&nbsp;<%=n.getReviews() %></span>
								<span><img src="sample_lihu/images/favor_ico.gif" alt="关注" />&nbsp;<%=n.getCollectnum() %></span>
							</p>
						</div>
						<div class="imgArea">
							<p class="big_img"><img src="<%=request.getContextPath()+n.getImgmax() %>" alt="<%=request.getContextPath()+n.getImgmax() %>" /></p>
						</div>
					</li>
					</a>
					<%
							}
					%>
				</ul>
			</div>
			<ul class="ft_guidemap clearfix">
				<li><a href="webNewtype?tp=1"><img src="sample_lihu/images/ft_guidemap1.gif" alt="升学指南" />升学指南</a></li>
				<li><a href="webNewtype?tp=2"><img src="sample_lihu/images/ft_guidemap2.gif" alt="教子经验" />教子经验</a></li>
				<li><a href="webNewtype?tp=3"><img src="sample_lihu/images/ft_guidemap3.gif" alt="成长路上" />成长路上</a></li>
				<li><a href="webNewtype?tp=4"><img src="sample_lihu/images/ft_guidemap4.gif" alt="兴趣特长" />兴趣特长</a></li>
				<li><a href="webNewtype?tp=5"><img src="sample_lihu/images/ft_guidemap5.gif" alt="出国留学" />出国留学</a></li>
				<li><a href="webNewtype?tp=6"><img src="sample_lihu/images/ft_guidemap6.gif" alt="名人教子" />名人教子</a></li>
				<li><a href="webNewtype?tp=7"><img src="sample_lihu/images/ft_guidemap7.gif" alt="健康导航" />健康导航</a></li>
				<li><a href="webNewtype?tp=8"><img src="sample_lihu/images/ft_guidemap8.gif" alt="轻松驿站" />轻松驿站</a></li>
			</ul>
		</div><!--container-->
		<div id="footer"></div><!--footer-->
	</div><!--wrap-->
</body>
</html>

