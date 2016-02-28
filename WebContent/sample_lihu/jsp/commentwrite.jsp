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
</head>
<body>
<%
//获取新闻
News n=(News)request.getAttribute("n");
%>
<form action="webRsave" method="post" id="myform" >
 	<div class="pingnum pingnum3">	
	<!-- 获取论坛的id -->
	<input type="text" value="<%=n.getId() %>" name="newsid" style="display: none"/>
	
	<!-- 获取回复信息 -->
 
	</div>
   

	<div id="wrap">
		<div id="header">
			<div class="topArea clearfix">
				<h1>发表评论</h1>
				<div class="prev">
					<a href="#" onclick="javascript:history.back(-1);"><img src="sample_lihu/images/prev.png" alt="后退" /></a>
				</div>
			</div>
		</div><!--header-->
		<div id="container">
			<div class="forumPost">
				<div class="write">
					<p class="posts commentWrite"><textarea name="review" id="review1" cols="30" rows="10">写点什么吧…</textarea></p>
				</div>
				<div class="btn clearfix">
					<span class="rightArea clearfix">
						<a href="#" onclick="javascript:history.back(-1);">取消</a>
						<a href="#" id="a1">发表</a>
					</span>
				</div>
			</div>
		</div><!--container-->
		<div id="footer"></div><!--footer-->
	</div><!--wrap-->
	 </form>
<script type="text/javascript">
$("#a1").click(function(){
	
	$("#myform").submit(); 
	alert("评论成功");
	
});
$(function(){
	var textarea2 = $("#review1");

	textarea2.focusin(function(){
	  // alert(a);
	  if($(this).text() !== "写点什么吧…"){
		return false;
	  }else{
		$(this).text("");
	  }
	});
	textarea2.focusout(function(){
	  if($(this).text() == ""){
		$(this).text("写点什么吧…");
	  }
	});
});
</script>
</body>
</html>
