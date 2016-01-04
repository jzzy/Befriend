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
<%
List<EduServices> lEduServices=(List)(request.getAttribute("EduServices")==null?new ArrayList<EduServices>():request.getAttribute("EduServices"));
%>
<body>
<div class="cont">
	<div id="header">
		<div class="topArea clearfix">
			<h1>教辅机构</h1>
			<div class="prev">
				<a href="#" onclick="javascript:history.back(-1);"></a>
			</div>
			<div class="location">
				<h2><a href="#">北京市</a></h2>
				<ul>
					<li><a href="#">北京市</a></li>
					<li><a href="#">上海市</a></li>
					<li><a href="#">天津市</a></li>
					<li><a href="#">廊坊市</a></li>
					<li><a href="#">唐山市</a></li>
				</ul>
			</div>
		</div>
	</div>
	<div id="container">
		<ul class="navi clearfix" id="jMenu">
			<li class="district">
				<a href="#" class="fNiv">全部地区&nbsp;<img src="<%=request.getContextPath() %>/SimulationApp/lihu/images/arrow_off.gif" width="20" height="10" alt="" /></a>
				<ul>
					<li><a href="#">东城区</a></li>
					<li><a href="#">西城区</a></li>
					<li><a href="#">朝阳区</li>
					<li><a href="#">丰台区</a></li>
					<li><a href="#">石景山区</a></li>
					<li><a href="#">海淀区</a></li>
					<li><a href="#">门头沟区</a></li>
				</ul>
			</li>
			<li class="edutrain">
				<a href="#" class="fNiv">教育培训&nbsp;<img src="<%=request.getContextPath() %>/SimulationApp/lihu/images/arrow_off.gif" width="20" height="10" alt="" /></a>
				<ul>
					<li><a href="#">外语</a></li>
					<li><a href="#">音乐</a></li>
					<li><a href="#">幼儿教育</a></li>
					<li><a href="#">升学指导</a></li>
					<li><a href="#">教育院校</a></li>
					<li><a href="#">舞蹈</a></li>
					<li><a href="#">学校</a></li>
					<li><a href="#">驾校</a></li>
					<li><a href="#">兴趣生活</a></li>
				</ul>
			</li>
			<li class="sort fNiv">
				<a href="#" class="fNiv">智能排序&nbsp;<img src="<%=request.getContextPath() %>/SimulationApp/lihu/images/arrow_off.gif" width="20" height="10" alt="" /></a>
				<ul>
					<li><a href="#">离我最近的商户</a></li>
					<li><a href="#">人气最高的商户</a></li>
					<li><a href="#">评价醉好的商户</a></li>
					<li><a href="#">人均最低的商户</a></li>
					<li><a href="#">人均最高的商户</a></li>
				</ul>
			</li>
		</ul>
		<div class="inner">
			<div id="bg">bg</div>
			<ul>
			<%
			for(int i=0;i<lEduServices.size();i++){
				float f=lEduServices.get(i).getStar();
				
			%>
				<li class="list">
					<a href="detail.html">
						<div class="imgArea"><img src="<%="http://182.92.100.235/"+lEduServices.get(i).getPicture() %>" alt="" /></div>
						<div class="infoArea">
							<h3><%=lEduServices.get(i).getName() %></h3>
							<p class="rate clearfix">
							<%
							for(int y=0;y<f;y++){
							%>
							<span class="star_r"></span>
							<%
							}	
							%>
							</p>
							<div class="clearfix"><strong><%=lEduServices.get(i).getCity()%></strong><span><%=lEduServices.get(i).getClassFirst() %></span></div>
						</div>
						<div class="distance">8000m</div>
					</a>
				</li>
			<%
			}
			%>
			
			</ul>
		</div>
	</div>
   <!--userlist-->
</div>
<!--cont-->
    <script type="text/javascript">
    //<![CDATA[

    //]]>
    </script>
	
<script type="text/javascript" src="<%=request.getContextPath() %>/SimulationApp/lihu/js/jMenu.jquery.js"></script>
<script type="text/javascript">
  $(document).ready(function(){
    $("#jMenu").jMenu();
  })
</script>
</body>
</html>
