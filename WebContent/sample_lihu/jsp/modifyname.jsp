<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    <%@page import="java.util.*"%>
    <%@page import="com.befriend.util.*"%>
<%@page import="com.befriend.entity.*"%>
<%
User u=(User)session.getAttribute("u");
if(u==null){
	//跳转到登入页
	((HttpServletResponse) OpeFunction.response())
	.sendRedirect("/sample_lihu/htm/login.html");
	return;
}
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no"  />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title></title>
<script type="text/javascript" src="../js/jquery-1.10.2.min.js"></script>
<script type="text/javascript" src="../js/common.js"></script>
<link href="../css/style.css" rel="stylesheet" type="text/css" />
</head>
<body>
<form action="" name="form1" method="post" enctype="multipart/form-data">
	<div id="wrap">
		<div id="header">
			<div class="topArea clearfix">
				<h1>修改昵称</h1>
				<div class="prev">
					<a  href="javascript:history.back(-1);"><img src="../images/prev.png" alt="后退" /></a>
				</div>
			</div>
		</div><!--header-->
		<div id="container">
			<div class="modifyInfo">
				<ul>
					<li class="userName">
						<div class="leftArea">昵称</div>
						<div class="rightArea"><input type="text" name="nickname" value="<%=u.getNickname() %>" /></div>
					</li>
				</ul>
			</div>
		</div><!--container-->
		<div id="footer">
			<a id=sum class="fbButton">保存</a>
		</div><!--footer-->
	</div><!--wrap-->
	</form>
	<script type="text/javascript" >
$("#sum").click(function(){
	document.form1.action="webModification";
	document.form1.submit();
	alert('已提交');
	
});
</script>
</body>
</html>
