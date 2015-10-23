<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    <%@page import="java.util.*"%>
    <%@page import="com.befriend.util.*"%>
<%@page import="com.befriend.entity.*"%>
<%
//取出收藏的新闻
List<News> nl=(List)request.getAttribute("nl");
//取出评论
List<Review> rl=(List)request.getAttribute("rl");

%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<script type="text/javascript" src="SimulationApp/js/style.js"></script>
<script  src="SimulationApp/js/jquery.js"></script>
<script type="text/javascript">
$(document).ready(function(){
	$.ajax({
		type:"post",
		url:"statisticsPVIP.action"
	});
});
</script>
<title>我的评论</title>
<link href="SimulationApp/css/style.css" rel="stylesheet" type="text/css" />
<script  src="SimulationApp/js/TouchSlide.1.1.js"></script>
</head>

<body>
  <h1 class="tit tit5"><a style="float:left;" href="javascript:history.back(-1);"><span>&lt; 我的评论</span></a></h1>
   
      <%
						for(int i=0;i<nl.size();i++){
							if(nl.get(i)!=null&&rl.get(i)!=null)
							{
						%>
   <dl class="list border0">
      <dt>
      <a href="<%=request.getContextPath()%>/webNewsId?id=<%=nl.get(i).getId()%>"><img  src="<%=request.getContextPath()%><%=nl.get(i).getImg()%>"> </a>
      </dt>
     
      <dd>
      <a href="<%=request.getContextPath()%>/webNewsId?id=<%=nl.get(i).getId()%>"><%=nl.get(i).getTitle() %></a>
          <p class="subtit"><%=rl.get(i).getReview() %> 评论时间 :<%=rl.get(i).getTime() %></p>
         
      </dd>
   </dl>
   <%
							}
							}
   %>
   <!--list-->
     
</body>
</html>