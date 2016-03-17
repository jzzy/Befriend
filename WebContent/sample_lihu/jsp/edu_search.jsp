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
<script type="text/javascript" src="<%=request.getContextPath() %>/sample_lihu/jsjquery-1.10.2.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/sample_lihu/jscommon.js"></script>
<script type="text/javascript">
function  checkvalue()
{	 
document.form1.action="<%=request.getContextPath() %>/getLikeEduWebArea";
document.form1.submit();
//alert('已提交');
return true;

}
</script>

</head>
<body>
	<div id="wrap">
		<div id="header">
			<div class="topArea clearfix">
			<form name="form1" action="getLikeEduWebArea">
				<div class="search_input2">
					<div class="inner">
						<input type="text" name="value" id="search2" />
						<a  onClick="javascript:return checkvalue();" class="search_btn2">
						<img src="<%=request.getContextPath() %>/sample_lihu/images/ico07.gif" alt="确认搜索" />
						</a>
						</span>
					</div>
				</div>
				<div class="prev">
					<a href="<%=request.getContextPath() %>/getEduWebArea"><img src="<%=request.getContextPath() %>/sample_lihu/images/prev.png" alt="后退" /></a>
				</div>
				</form>
			</div>
		</div><!--header-->
		<div id="container">
			<div class="everyone_search">
				<h2>大家都在搜</h2>
				<ul class="clearfix">
				<li><a href="<%=request.getContextPath() %>/getLikeEduWebArea?value=英语培训">英语培训</a></li>
					<li><a href="<%=request.getContextPath() %>/getLikeEduWebArea?value=韦博国际英语">韦博国际英语</a></li>
					<li><a href="<%=request.getContextPath() %>/getLikeEduWebArea?value=绘画">绘画</a></li>
					<li><a href="<%=request.getContextPath() %>/getLikeEduWebArea?value=钢琴">钢琴</a></li>
					<li><a href="<%=request.getContextPath() %>/getLikeEduWebArea?value=芭蕾舞">芭蕾舞</a></li>
					<li><a href="<%=request.getContextPath() %>/getLikeEduWebArea?value=数学">数学</a></li>
				</ul>
			</div>
			<div  class="history_search">
				<h2>搜索历史：</h2>
				<ul>
					<li><a href="<%=request.getContextPath() %>/getLikeEduWebArea?value=英语">英语</a></li>
					<li><a href="<%=request.getContextPath() %>/getLikeEduWebArea?value=数学">数学</a></li>
					<li><a href="<%=request.getContextPath() %>/getLikeEduWebArea?value=语文">语文</a></li>
					<li><a href="<%=request.getContextPath() %>/getLikeEduWebArea?value=音乐">音乐</a></li>
				</ul>
				<p><a href="#">清空搜索历史</a></p>
			</div>
		</div><!--container-->
		<div id="footer"></div><!--footer-->
	</div><!--wrap-->
</body>
</html>
