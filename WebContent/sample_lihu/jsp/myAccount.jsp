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
<link href="<%=request.getContextPath() %>/sample_lihu/css/style.css" rel="stylesheet" type="text/css" />
</head>
<body>
<form action="webModification" method="post" name="creator" enctype="multipart/form-data">
	<div class="shadow displaynone"></div>
	<div id="wrap">
		<div id="header">
			<div class="topArea clearfix">
				<h1>我的账户</h1>
				<div class="prev">
					<a href="javascript:history.back(-1);">
					<img src="<%=request.getContextPath() %>/sample_lihu/images/prev.png" alt="后退" /></a>
				</div>
				<div class="topTool">
					<span><a href="<%=request.getContextPath() %>/sample_lihu/modifypsd.html">修改密码</a></span>
				</div>
			</div>
		</div><!--header-->
		<div id="container">
			<div class="modifyInfo">
				<ul>
					<li class="userIco">
						<div class="leftArea">头像</div>
						<div class="rightArea" id="modify_ico"><span><label for="up_img_WU_FILE_0">
						<img id="imgShow_WU_FILE_0" style="width: 50px;height: 50px;" src="<%=request.getContextPath()+u.getImg() %>" alt="sample" />
						</label><input type="file" id="up_img_WU_FILE_0"></span></div>
					</li>
					<li class="userId">
						<div class="leftArea">用户名</div>
						<div class="rightArea"><%=u.getUsername() %></div>
					</li>
					<li class="userName">
						<div class="leftArea">昵称</div>
						<div class="rightArea"><a href="<%=request.getContextPath() %>/sample_lihu/modifyname.html"><%=u.getNickname() %></a></div>
					</li>
					<li class="province">
						<div class="leftArea">省/市</div>
						<div class="rightArea">
							<a href="<%=request.getContextPath() %>/sample_lihu/#">北京</a>
							<div class="selectAddr displaynone">
								<div class="inner">
									<h2>省/市</h2>
									<ul>
										<li>北京市1</li>
										<li>北京市2</li>
										<li>北京市3</li>
										<li>北京市4</li>
										<li>北京市5</li>
										<li>北京市6</li>
										<li>北京市7</li>
										<li>北京市8</li>
										<li>北京市9</li>
									</ul>
									<p class="cancel">取消</p>
								</div>
							</div>
						</div>
					</li>
					<li class="city">
						<div class="leftArea">城市</div>
						<div class="rightArea">
							<a href="<%=request.getContextPath() %>/sample_lihu/#">朝阳区</a>
							<div class="selectAddr displaynone">
								<div class="inner">
									<h2>城市</h2>
									<ul>
										<li>朝阳区1</li>
										<li>朝阳区2</li>
										<li>朝阳区3</li>
										<li>朝阳区4</li>
										<li>朝阳区5</li>
										<li>朝阳区6</li>
										<li>朝阳区7</li>
										<li>朝阳区8</li>
										<li>朝阳区9</li>
										<li>朝阳区10</li>
										<li>朝阳区11</li>
										<li>朝阳区12</li>
									</ul>
									<p class="cancel">取消</p>
								</div>
							</div>
						</div>
					</li>
					<li class="gradation">
						<div class="leftArea">孩子阶段</div>
						<div class="rightArea">
							<a href="#"><%=u.getStage() %></a>
							<div class="selectAddr displaynone">
								<div class="inner">
									<h2>孩子阶段</h2>
									<ul>
										<li>未入园</li>
										<li>幼儿园</li>
										<li>一年级</li>
										<li>二年级</li>
										<li>三年级</li>
										<li>四年级</li>
										<li>五年级</li>
										<li>六年级</li>
									</ul>
									<p class="cancel">取消</p>
								</div>
							</div>
						</div>
					</li>
					<li>
						<div class="leftArea">所在学校</div>
						<div class="rightArea"><a href="<%=request.getContextPath() %>/sample_lihu/modifyschool.html"><%=u.getSchool() %></a></div>
					</li>
				</ul>
			</div>
		</div><!--container-->
		<div id="footer">
			<a href="<%=request.getContextPath() %>/sample_lihu/#" class="fbButton">保存</a>
		</div><!--footer-->
	</div><!--wrap-->
<script type="text/javascript">
	$(function(){
		$(".province .rightArea").click(function(){
			$(".shadow").show();
			$(this).find(".selectAddr").show();
		});
		$(".city .rightArea").click(function(){
			$(".shadow").show();
			$(this).find(".selectAddr").show();
		});
		$(".gradation .rightArea").click(function(){
			$(".shadow").show();
			$(this).find(".selectAddr").show();
		});
		$(".selectAddr .cancel").click(function(){
			$(".shadow").hide();
			$(".selectAddr").hide();
			return false;
		});
		$(".selectAddr .inner li").click(function(){
			var val = $(this).text();
			$(".shadow").hide();
			$(".selectAddr").hide();
			$(this).parent().parent().parent().parent().children("a").text(val);
			return false;
		});
	});
</script>
</form>
</body>
</html>
