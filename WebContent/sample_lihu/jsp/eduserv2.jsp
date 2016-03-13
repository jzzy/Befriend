<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    <%@page import="java.util.*"%>
    <%@page import="com.befriend.util.*"%>
<%@page import="com.befriend.entity.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no"  />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title></title>
<script type="text/javascript" src="<%=request.getContextPath() %>/sample_lihu/js/jquery-1.10.2.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/sample_lihu/js/common.js"></script>
<link href="<%=request.getContextPath() %>/sample_lihu/css/style.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" href="<%=request.getContextPath() %>/sample_lihu/css/pullToRefresh.css"/>
</head>
<body>
	<div id="bg">bg</div>
	<div id="wrap">
		<div id="header">
			<div class="topArea clearfix">
				<h1>教辅机构</h1>
				<div class="prev">
					<a href="javascript:history.back(-1);"><img src="<%=request.getContextPath() %>/sample_lihu/images/prev.png" alt="后退" /></a>
				</div>
				<div class="topTool">
					<a href="<%=request.getContextPath() %>/sample_lihu/jsp/edu_search.jsp"><img src="sample_lihu/images/ico31.png" alt="搜索" /></a>
					<a href="<%=request.getContextPath() %>/sample_lihu/jsp/mypage.jsp"><img src="sample_lihu/images/ico32.png" alt="我的页面" /></a>
				</div>
			</div>
			<ul class="navi clearfix" id="jMenu">
			<%
				List<Areas> lar=(List)(request.getAttribute("lar")==null?new ArrayList<Areas>():request.getAttribute("lar")); 			
				List<Cities> lcit=(List)(request.getAttribute("lcit")==null?new ArrayList<Cities>():request.getAttribute("lcit")); 			

				%>
			
				<li class="district">
					<a  class="fNiv">全部地区&nbsp;<img src="sample_lihu/images/arrow_off.png" width="20" height="10" alt="" /></a>
					<ul>
									<%
					if(lar.size()==0){
					%>
					<li><a href="getEduWebArea?city=all"><%=session.getAttribute("province") %></a></li>
					<%
					}else{
					%>
					<li><a href="getEduWebArea?county=all"><%=session.getAttribute("province") %></a></li>
					<%
					}
					%>
						<%
						for(int i=0;i<lar.size();i++){
							
						
						%>
						<li><a href="getEduWebArea?county=<%=lar.get(i).getArea().substring(0, lar.get(i).getArea().length()-1) %>&province=<%=session.getAttribute("province")==null||session.getAttribute("province").equals("null")?"北京":session.getAttribute("province")%>"><%=lar.get(i).getArea() %></a></li>
					
						<%
			
						}
						%>
						
						
							<%
						for(int i=0;i<lcit.size();i++){
							
						
						%>
						<li><a href="getEduWebArea?city=<%=lcit.get(i).getCity().substring(0, lcit.get(i).getCity().length()-1) %>&province=<%=session.getAttribute("province")==null||session.getAttribute("province").equals("null")?"北京":session.getAttribute("province")%>"><%=lcit.get(i).getCity() %></a></li>
					
						<%
						}
						%>
					</ul>
				</li>
				<li class="edutrain">
					<a class="fNiv">教育培训&nbsp;<img src="sample_lihu/images/arrow_off.png" width="20" height="10" alt="" /></a>
					<ul>
						<li><a href="getEduWebArea?classSecond=all">不限</a></li>
						<li><a href="getEduWebArea?classSecond=外语">外语</a></li>
						<li><a href="getEduWebArea?classSecond=音乐">音乐</a></li>
						<li><a href="getEduWebArea?classSecond=幼儿教育">幼儿教育</a></li>
						<li><a href="getEduWebArea?classSecond=升学指导">升学指导</a></li>
						<li><a href="getEduWebArea?classSecond=教育院校">教育院校</a></li>
						<li><a href="getEduWebArea?classSecond=舞蹈">舞蹈</a></li>
						<li><a href="getEduWebArea?classSecond=学校">学校</a></li>
						<li><a href="getEduWebArea?classSecond=驾校">驾校</a></li>
						<li><a href="getEduWebArea?classSecond=兴趣生活">兴趣生活</a></li>
					</ul>
				</li>
				<li class="sort fNiv">
					<a  class="fNiv">智能排序&nbsp;<img src="sample_lihu/images/arrow_off.png" width="20" height="10" alt="" /></a>
					<ul>
					<li><a href="getEduWebArea?sortType=1">离我最近的商户</a></li>
						<li><a href="getEduWebArea?sortType=2">人气最高的商户</a></li>
						<li><a href="getEduWebArea?sortType=5">评价最好的商户</a></li>
						<li><a href="getEduWebArea?sortType=3">人均最低的商户</a></li>
						<li><a href="getEduWebArea?sortType=4">人均最高的商户</a></li>
					</ul>
				</li>
			</ul>
		</div><!--header-->
		<div id="container" class="load" style="padding:70px 0 0;">
			<iframe id="mainiframe" src="<%=request.getContextPath()+"/getEduWeb" %>" frameborder="0" scrolling="no" width="100%"></iframe>
		</div><!--container-->
		<div id="footer"></div><!--footer-->
	</div><!--wrap-->
	
<script type="text/javascript" src="<%=request.getContextPath() %>/sample_lihu/js/jMenu.jquery.js"></script>
<script type="text/javascript">
  $(document).ready(function(){
    $("#jMenu").jMenu();
  })
</script>
</body>
</html>
