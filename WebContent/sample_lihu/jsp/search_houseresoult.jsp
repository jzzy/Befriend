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
</head>
<body>
	<div id="wrap">
		<div id="header">
			<div class="topArea clearfix">
				<div class="search_input2">
					<div class="inner">
					<form  id="form1" name="form1" method="post" enctype="application/x-www-form-urlencoded">
						<input type="text" name="schoolname" value="<%=request.getAttribute("name")==null?"":request.getAttribute("name")%>"  id="search2"/>
						<a href="#"  onClick="javascript:return checkvalue();"  class="search_btn2"><img src="sample_lihu/images/ico07.gif" alt="确认搜索" />
						</a>
						</form>
					</div>
				</div>
				<div class="prev">
					<a href="#" onclick="javascript:history.back(-1);"><img src="sample_lihu/images/prev.png" alt="后退" /></a>
				</div>
			</div>
		</div><!--header-->
		<div id="container">
			<div class="searchResoult">
			<%
		
	
	List<House> l=(List)request.getAttribute("hl");
			
		
		if(l==null){
			
			%>
			<b style="color: red;">未找到相关信息</b>
			<%
			return;
		}
	if(l.size()==0){
			
			%>
			<b style="color: red;">未找到相关信息</b>
			<%
			return;
		}
	 for(int i=0;i<l.size();i++){
		
	%>
				<div class="title">
					<h2><%=l.get(i).getSchoolname() %></h2>
					<p><span><img src="sample_lihu/images/ico9.gif" alt="location_ico" />学校地址：</span><%=l.get(i).getAddress() %></p>
				</div>
				<div class="servArea">
					<h2>服务范围</h2>
					<p><%=l.get(i).getScope() %></p>
				</div>
				<div class="info">
					<h2>楼盘信息</h2>
					<p><%=l.get(i).getProperty() %>
					</p>
				</div>
				<%
				
				}%>
			</div>
		</div><!--container-->
		<div id="footer"></div><!--footer-->
	</div><!--wrap-->
</body>
</html>
