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
<script type="text/javascript" src="<%=request.getContextPath() %>/sample_lihu/js/jquery-1.10.2.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/sample_lihu/js/common.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/sample_lihu/js/uploadPreview.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/sample_lihu/js/lv2city.js"></script>
<link href="<%=request.getContextPath() %>/sample_lihu/css/style.css" rel="stylesheet" type="text/css" />
<link href="<%=request.getContextPath() %>/sample_lihu/css/common.css" rel="stylesheet"/>
</head>
<body>
<form action="" name="form1" method="post" enctype="multipart/form-data">
	<div class="shadow displaynone"></div>
	<div id="wrap">
		<div id="header">
			<div class="topArea clearfix">
				<h1>我的账户</h1>
				<div class="prev">
					<a href="#" onclick="javascript:history.back(-1);"><img src="<%=request.getContextPath() %>/sample_lihu/images/prev.png" alt="后退" /></a>
				</div>
				<div class="topTool">
					<span><a href="<%=request.getContextPath() %>/sample_lihu/htm/modifypsd.html">修改密码</a></span>
				</div>
			</div>
		</div><!--header-->
		<div id="container">
			<div class="modifyInfo">
				<ul>
					<li class="userIco">
						<div class="leftArea">头像</div>
						<div class="rightArea" id="modify_ico">
						<span><label for="up_img_WU_FILE_0">
						<img id="imgShow_WU_FILE_0" src="<%=request.getContextPath()+u.getImg() %>" alt="sample" />
						</label>
						<input type="file" name="file" id="up_img_WU_FILE_0">
						</span></div>
					</li>
					<li class="userId">
						<div class="leftArea">用户名</div>
						<div class="rightArea"><%=u.getUsername() %></div>
					</li>
					<li class="userName">
						<div class="leftArea">昵称</div>
						<div class="rightArea"><a href="<%=request.getContextPath() %>/sample_lihu/jsp/modifyname.jsp"><%=u.getNickname()%></a></div>
					</li>
					<li class="province">
						<div class="leftArea">省/市</div>
						<div class="rightArea">
						
							<select  id="selProvince" name="province" onchange="provinceChange();"></select> 
						
							
						</div>
					</li>
					<li class="city">
						<div class="leftArea">城市</div>
						<div class="rightArea">
							<select id="selCity"   name="city"></select> 
						</div>
					</li>
					<li>
						<div class="leftArea">所在学校</div>
						<div class="rightArea"><a href="<%=request.getContextPath() %>/sample_lihu/jsp/modifyschool.jsp"><%=u.getSchool() %></a></div>
					</li>
				</ul>
			</div>
		</div><!--container-->
		<div id="footer">
			<a id="sum" class="fbButton">保存</a>
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

