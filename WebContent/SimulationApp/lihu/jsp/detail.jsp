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
<title>教辅机构</title>
<script type="text/javascript" src="<%=request.getContextPath() %>/SimulationApp/lihu/js/jquery.js"></script>
<link href="<%=request.getContextPath() %>/SimulationApp/lihu/css/style.css" rel="stylesheet" type="text/css" />
<link href="<%=request.getContextPath() %>/SimulationApp/lihu/css/common.css" rel="stylesheet" type="text/css" />
</head>
<body>
<div class="cont">
	<div id="header">
		<div class="topArea clearfix">
			<div class="prev">
				<a href="getEduWeb" ></a>
				<!-- 
				onclick="javascript:history.back(-1);"
				 -->
			</div>
			<div class="topTool">
				<a href="#"><img src="<%=request.getContextPath() %>/SimulationApp/lihu/images/share.png" alt="分享" /></a>
				<a href="#"><img src="<%=request.getContextPath() %>/SimulationApp/lihu/images/dot3.png" alt="附近美食" /></a>
			</div>
		</div>
	</div>
	<%
	List<EduComment> educl=(List)request.getAttribute("educl");
	EduServices edus=(EduServices)request.getAttribute("edus");
	%>
	<div id="container">
		<div class="detail">
			<div class="clearfix">
				<div class="imgArea">
					<img src=<%="http://182.92.100.235/"+edus.getPicture() %> alt="" />
				</div>
				<div class="infoArea">
					<p class="name"><%=edus.getName() %></p>
					<p class="rate"><img src="<%=request.getContextPath() %>/SimulationApp/lihu/images/detail_star_on.png" alt="" /><img src="<%=request.getContextPath() %>/SimulationApp/lihu/images/detail_star_on.png" alt="" /><img src="<%=request.getContextPath() %>/SimulationApp/lihu/images/detail_star_on.png" alt="" /><img src="<%=request.getContextPath() %>/SimulationApp/lihu/images/detail_star_on.png" alt="" /><img src="<%=request.getContextPath() %>/SimulationApp/lihu/images/detail_star_off.png" alt="" /></p>
					<p class="point"><span>设施:7.0</span><span>环境:1.0</span><span>服务:0.0</span></p>
				</div>
			</div>
			<p class="adress"><a class="clearfix" href="#"><%=edus.getAddress() %><img src="<%=request.getContextPath() %>/SimulationApp/lihu/images/grayarrow.gif" alt="" /></a></p>
			<p class="tel"><a class="clearfix" href="#"><%=edus.getTelephone() %><img src="<%=request.getContextPath() %>/SimulationApp/lihu/images/grayarrow.gif" alt="" /></a></p>
		</div>
		<div class="review">
			<h2><a href="review.html">网友点评<img src="<%=request.getContextPath() %>/SimulationApp/lihu/images/grayarrow.gif" alt="ico" /></a></h2>
			<ul>
			<%
			for(int i=0;i<educl.size();i++){
			%>
				<li>
					<div class="inner clearfix">
						<div class="userImg"><img src="<%=request.getContextPath() %>/SimulationApp/lihu/images/b10.png" alt="ico" /></div>
						<div class="message">
							<p class="userId"><%=educl.get(i).getUser().getNickname() %><span class="rate">
							<img src="<%=request.getContextPath() %>/SimulationApp/lihu/images/detail_star_on.png" alt="" />
							<img src="<%=request.getContextPath() %>/SimulationApp/lihu/images/detail_star_on.png" alt="" />
							<img src="<%=request.getContextPath() %>/SimulationApp/lihu/images/detail_star_on.png" alt="" />
							<img src="<%=request.getContextPath() %>/SimulationApp/lihu/images/detail_star_on.png" alt="" /><img src="<%=request.getContextPath() %>/SimulationApp/lihu/images/detail_star_off.png" alt="" /></span></p>
							<p class="words">
							<%=educl.get(i).getContent() %>
							</p>
							<p class="attachImg">
							<img src="<%=request.getContextPath() %>/SimulationApp/lihu/images/b10.png" alt="" />
							<img src="<%=request.getContextPath() %>/SimulationApp/lihu/images/b10.png" alt="" />
							<img src="<%=request.getContextPath() %>/SimulationApp/lihu/images/b10.png" alt="" />
							</p>
							<p class="time"><%=educl.get(i).getTime() %></p>
						</div>
					</div>
				</li>
				<%} %>
			
			</ul>
		</div>
		<div class="shopInfo">
			<h2><a href="shopinfo.html">商户信息<img src="<%=request.getContextPath() %>/SimulationApp/lihu/images/grayarrow.gif" alt="ico" /></a></h2>
			<p class="time">营业时间&nbsp;:&nbsp;尚无营业时间</p>
			<p class="tel">联系电话&nbsp;:&nbsp;<%=edus.getTelephone() %></p>
			<p class="shopName">商户名称&nbsp;:&nbsp;<%=edus.getName() %></p>
		</div>
		<div class="nearby">
			<h2>在这家店附近找</h2>
			<ul class="clearfix">
				<li><a href="#"><img src="<%=request.getContextPath() %>/SimulationApp/lihu/images/nearby1.gif" alt="" /></a></li>
				<li><a href="#"><img src="<%=request.getContextPath() %>/SimulationApp/lihu/images/nearby2.gif" alt="" /></a></li>
				<li><a href="#"><img src="<%=request.getContextPath() %>/SimulationApp/lihu/images/nearby3.gif" alt="" /></a></li>
				<li class="clear"><a href="#"><img src="<%=request.getContextPath() %>/SimulationApp/lihu/images/nearby4.gif" alt="" /></a></li>
				<li><a href="#"><img src="<%=request.getContextPath() %>/SimulationApp/lihu/images/nearby5.gif" alt="" /></a></li>
				<li><a href="#"><img src="<%=request.getContextPath() %>/SimulationApp/lihu/images/nearby6.gif" alt="" /></a></li>
			</ul>
		</div>
	</div>
	<div id="footer">
		<ul class="clearfix">
			<li><a href="#"><img src="<%=request.getContextPath() %>/SimulationApp/lihu/images/shoucang.png" alt="我的收藏" /></a></li>
			<li><a href="<%=request.getContextPath() %>/SimulationApp/lihu/comment.html"><img src="<%=request.getContextPath() %>/SimulationApp/lihu/images/dianping.png" alt="我要点评" /></a></li>
		</ul>
	</div>
   <!--userlist-->
</div>
<!--cont-->

</body>
</html>
