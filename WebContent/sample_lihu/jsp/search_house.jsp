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

	<div id="wrap">
		<div id="header">
			<div class="topArea clearfix">
				<h1>搜学区房</h1>
				<div class="prev">
					<a href="javascript:history.back(-1);"><img src="sample_lihu/images/prev.png" alt="后退" /></a>
				</div>
			</div>
		</div><!--header-->
		<div id="container">
			<div class="search_input1">
			<form  id="form1" name="form1" method="post" enctype="application/x-www-form-urlencoded">
				<div class="inner">
					<input type="text" name="schoolname" id="search1"/>
					<a href="#" onClick="javascript:return checkvalue();" class="search_btn1">
					<img src="sample_lihu/images/ico07.gif" alt="确认搜索" />
					</a>
					
				</div>
					</form>
			</div>
			<div class="everyone_search">
				<h2>大家都在搜</h2>
				<ul class="clearfix">
				 	<%
		
	
				List<House> l=(List)request.getAttribute("hl");
					 for(int i=0;i<l.size();i++){
			
			%>
					
					 <li>
					 <a  href="<%=request.getContextPath()%>/selectwxHouseall?schoolname=<%=l.get(i).getSchoolname() %>"><%=l.get(i).getSchoolname() %>
					 </a>
					 </li>
					
					<%
		}
					%>
				</ul>
			</div>
		</div><!--container-->
		<div id="footer"></div><!--footer-->
	</div><!--wrap-->

	<script type="text/javascript">
function  checkvalue()
{	 
	//js提交form表单
document.form1.action="selectwxHouseall";
document.form1.submit();
//alert('已提交');
return true;

}
</script>
</body>
</html>
