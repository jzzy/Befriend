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
<link href="<%=request.getContextPath() %>/sample_lihu/css/style.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="<%=request.getContextPath() %>/sample_lihu/js/jquery-1.10.2.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/sample_lihu/js/common.js"></script>

<%
User u=(User)session.getAttribute("u");
if(false){
	
	System.out.println("请重新登入!");
	((HttpServletResponse) OpeFunction.response())
			.sendRedirect("/Befriend/SimulationApp/login.html");
	return;
}
%>
<script type="text/javascript" >
function  text()
{	 
alert('功能尚未开放');


}
</script>
</head>
<body>
	<div id="wrap">
		<div id="header">
			<div class="topArea clearfix">
				<h1><a href="<%=request.getContextPath() %>/webNewsA10"><img src="<%=request.getContextPath() %>/sample_lihu/images/logo.gif" alt="家长之友" /></a></h1>
				<div class="relocation">
					<a href="<%=request.getContextPath() %>/sample_lihu/jsp/relocation.jsp"><%=session.getAttribute("province") %>&nbsp;<img src="<%=request.getContextPath() %>/sample_lihu/images/location_more.gif" alt="down" /></a>
				</div>
				<div class="topTool">
					<span class="mapLink"><a href="<%=request.getContextPath() %>/sample_lihu/jsp/sitemap.jsp"><img src="<%=request.getContextPath() %>/sample_lihu/images/toptoolBtn.gif" alt="菜单" /></a></span>
				</div>
			</div>
			<ul class="topMenu clearfix">
				<li><a href="<%=request.getContextPath() %>/webNewsA10"><img src="<%=request.getContextPath() %>/sample_lihu/images/hottoday_ico.gif" alt="fire" />&nbsp;今日必读</a></li>
				<li><a href="<%=request.getContextPath() %>/webForumApptype">社区互动</a></li>
				<li><a href="<%=request.getContextPath() %>/webGetapp">贴心服务</a></li>
				<li class="on"><a href="<%=request.getContextPath() %>/sample_lihu/jsp/mypage.jsp">我的信息</a></li>
			</ul>
		</div><!--header-->
		<div id="container">
			<div class="myPage">
				<%
				if(u==null){
				%>
				<div class="myInfo logoff">
					<a href="<%=request.getContextPath() %>/sample_lihu/htm/login.html"><img src="<%=request.getContextPath() %>/sample_lihu/images/lock.png" alt="" />马上登陆</a>
					<span class="prev"><img onclick="javascript:history.back(-1);" src="<%=request.getContextPath() %>/sample_lihu/images/ico45.png" alt="上一页" /></span>
				</div>
				<%
				}else{
				%>
				<div class="myInfo logon">
					<p class="userImg"><a href="<%=request.getContextPath() %>/sample_lihu/jsp/myAccount.jsp"><img style="width:100px;height:100px;"  src="<%=request.getContextPath()+u.getImg() %>" alt="sample" /></a></p>
					<p class="name"><%=u.getNickname() %><a href="<%=request.getContextPath() %>/sample_lihu/jsp/myAccount.jsp"><img src="<%=request.getContextPath() %>/sample_lihu/images/ico23.png" alt="修改" /></a></p>
					<p class="info clearfix"><span class="leftArea"><img src="<%=request.getContextPath() %>/sample_lihu/images/ico24.png" alt="" /><%=u.getAddress()+" "+u.getAddcity() %></span><span class="rightArea"><img src="<%=request.getContextPath() %>/sample_lihu/images/ico25.png" alt="年级" /><%=u.getStage() %></span></p>
					<span class="prev"><img onclick="javascript:history.back(-1);" src="<%=request.getContextPath() %>/sample_lihu/images/ico45.png" alt="上一页" /></span>
					<span class="logOut"><a href="websessionrom"><img src="<%=request.getContextPath() %>/sample_lihu/images/ico48.png" alt="退出登录" /></a></span>
					
				</div>
				<%
				}
				%>
				<div class="infoArea">
					<h2>资讯相关</h2>
					<ul class="clearfix">
						<li class="leftArea"><a href="<%=request.getContextPath() %>/userLookEduASBK"><img src="<%=request.getContextPath() %>/sample_lihu/images/ico26.png" alt="我的收藏" /><br/>我的收藏</a></li>
						<li class="rightArea"><a href="<%=request.getContextPath() %>/webEduReviews"><img src="<%=request.getContextPath() %>/sample_lihu/images/ico27.png" alt="我的评论" /><br/>我的评论</a></li>
					</ul>
				</div>
				<div class="shopArea">
					<h2>购物相关</h2>
					<ul class="clearfix">
						<li><img onclick="text();" src="<%=request.getContextPath() %>/sample_lihu/images/myshop1.png" alt="购物车" /><br/>购物车</a></li>
						<li><img onclick="text();" src="<%=request.getContextPath() %>/sample_lihu/images/myshop2.png" alt="待付款" /><br/>待付款</li>
						<li><img onclick="text();" src="<%=request.getContextPath() %>/sample_lihu/images/myshop3.png" alt="已发货" /><br/>已发货</li>
						<li><img onclick="text();" src="<%=request.getContextPath() %>/sample_lihu/images/myshop4.png" alt="交易完成" /><br/>交易完成</li>
						<li><img onclick="text();" src="<%=request.getContextPath() %>/sample_lihu/images/myshop5.png" alt="退货/售后" /><br/>退货/售后</li>
						<li><img onclick="text();" src="<%=request.getContextPath() %>/sample_lihu/images/myshop6.png" alt="收货地址" /><br/>收货地址</li>
					</ul>
				</div>
			</div>
		</div><!--container-->
		<div id="footer"></div><!--footer-->
	</div><!--wrap-->
</body>
</html>
