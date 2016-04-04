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
<script type="text/javascript" src="<%=request.getContextPath() %>/sample_lihu/js/jquery-1.10.2.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/sample_lihu/js/common.js"></script>
<link href="<%=request.getContextPath() %>/sample_lihu/css/style.css" rel="stylesheet" type="text/css" />
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
					<span class="mapLink"><a href="javascript:history.back(-1);"><img src="<%=request.getContextPath() %>/sample_lihu/images/toptoolBtn.gif" alt="菜单" /></a></span>
				</div>
			</div>
		</div><!--header-->
		<div id="container">
			<div class="columnNav">
				<h2 class="tit2"><img src="<%=request.getContextPath() %>/sample_lihu/images/colnavi_tit.gif" alt="栏目导航" /></h2>
				<ul class="clearfix">
				
					<li><a href="<%=request.getContextPath() %>/webNewtype?tp=1"><img src="<%=request.getContextPath() %>/sample_lihu/images/ft_guidemap1.gif" alt="升学指南" />升学指南</a></li>
				<li><a href="<%=request.getContextPath() %>/webNewtype?tp=2"><img src="<%=request.getContextPath() %>/sample_lihu/images/ft_guidemap2.gif" alt="教子经验" />教子经验</a></li>
				<li><a href="<%=request.getContextPath() %>/webNewtype?tp=3"><img src="<%=request.getContextPath() %>/sample_lihu/images/ft_guidemap3.gif" alt="成长路上" />成长路上</a></li>
				<li><a href="<%=request.getContextPath() %>/webNewtype?tp=4"><img src="<%=request.getContextPath() %>/sample_lihu/images/ft_guidemap4.gif" alt="兴趣特长" />兴趣特长</a></li>
				
				
				
				
				
				<li><a href="<%=request.getContextPath() %>/webNewtype?tp=5"><img src="<%=request.getContextPath() %>/sample_lihu/images/ft_guidemap5.gif" alt="出国留学" />出国留学</a></li>
				
				<li><a href="<%=request.getContextPath() %>/webNewtype?tp=10"><img src="<%=request.getContextPath() %>/sample_lihu/images/ft_guidemap9.gif" alt="本地资讯" />本地资讯</a></li>
				
				<li><a href="<%=request.getContextPath() %>/webNewtype?tp=6"><img src="<%=request.getContextPath() %>/sample_lihu/images/ft_guidemap6.gif" alt="名人教子" />名人教子</a></li>
				<li><a href="<%=request.getContextPath() %>/webNewtype?tp=7"><img src="<%=request.getContextPath() %>/sample_lihu/images/ft_guidemap7.gif" alt="健康导航" />健康导航</a></li>
				<li><a href="<%=request.getContextPath() %>/webNewtype?tp=8"><img src="<%=request.getContextPath() %>/sample_lihu/images/ft_guidemap8.gif" alt="轻松驿站" />轻松驿站</a></li>
				
				</ul>
			</div>
			<div class="servNav">
				<h2 class="tit2"><img src="<%=request.getContextPath() %>/sample_lihu/images/servnavi_tit.gif" alt="服务导航" /></h2>
				<ul class="clearfix">
					<li><a ><img onclick="text();" src="<%=request.getContextPath() %>/sample_lihu/images/servnav1.gif" alt="读书会" /></a></li>
					<li><a href="<%=request.getContextPath() %>/getEduWebArea"><img src="<%=request.getContextPath() %>/sample_lihu/images/servnav2.gif" alt="教辅机构" /></a></li>
					<li><a href="<%=request.getContextPath() %>/sample_lihu/jsp/book_category.jsp"><img src="<%=request.getContextPath() %>/sample_lihu/images/servnav3.gif" alt="教育部推荐用书" /></a></li>
					<li><a href="<%=request.getContextPath() %>/selectwxHouseallzh"><img src="<%=request.getContextPath() %>/sample_lihu/images/servnav4.gif" alt="搜学区房" /></a></li>
					<li><a href="<%=request.getContextPath() %>/sample_lihu/htm/post2.html"><img src="<%=request.getContextPath() %>/sample_lihu/images/servnav5.gif" alt="家长来稿" /></a></li>
					<li><a href="<%=request.getContextPath() %>/sample_lihu/htm/post3.html"><img src="<%=request.getContextPath() %>/sample_lihu/images/servnav6.gif" alt="意见反馈" /></a></li>
				</ul>
			</div>
			<div class="myNav">
				<h2 class="tit2"><img src="<%=request.getContextPath() %>/sample_lihu/images/mynavi_tit.gif" alt="我的导航" /></h2>
				<ul class="clearfix">
					<li><a href="<%=request.getContextPath() %>/webForumApptype"><img src="<%=request.getContextPath() %>/sample_lihu/images/mynavi1.gif" alt="论坛" /></a></li>
					<li><a href="<%=request.getContextPath() %>/sample_lihu/jsp/mypage.jsp"><img src="<%=request.getContextPath() %>/sample_lihu/images/mynavi2.gif" alt="我的" /></a></li>
				</ul>
			</div>
		</div><!--container-->
		<div id="footer"></div><!--footer-->
	</div><!--wrap-->
</body>
</html>
