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
<title>教育部推荐用书</title>
<link href="css/style.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="SimulationApp/js/style.js"></script>
<script  src="js/jquery.js"></script>
<script type="text/javascript">
$(document).ready(function(){
	$.ajax({
		type:"post",
		url:"statisticsPVIP.action?id=33"
	});
});
</script>
</head>

<body>
<div class="cont">
 <div class="topfixed" style="height:40px;">
   <div class="top">
       <a href="<%=request.getContextPath()%>/webNewsA10" class="fl"><img src="images/xw_03.png" height="40" /></a>
     <p class="set fr"><a href="<%=request.getContextPath()%>/SimulationApp/about.html"></a><a href="<%=request.getContextPath()%>/SimulationApp/about.html">
   <!--
     <img src="SimulationApp/images/xw_07.png" height="40" />
       -->
     </a></p>
        </div>
   <!--top-->
   
   <!--nav-->
 </div>
 <!--topfixed-->
 <div class="ltcont margin75" style="margin-top: 40px">
   
  
   <a href="#"><img height="60px" width="100%" src="images/jiayubutuijianyongshu.png" /></a><br>
   <a href="<%=request.getContextPath()%>/webLookBook?itype=1"><img height="60px" width="350px" src="images/jiayubutuijianyongshuyouer.png" /></a><br>
   <a href="<%=request.getContextPath()%>/webLookBook?itype=2"><img height="60px" width="353px" src="images/jiayubutuijianyongshuxiaoxue.png" /></a><br>
    <a href="<%=request.getContextPath()%>/webLookBook?itype=3"><img height="60px" width="353px" src="images/jiayubutuijianyongshuchuzhong.png" /></a><br>
   <a href="<%=request.getContextPath()%>/webLookBook?itype=4"><img height="60px" width="353px" src="images/jiayubutuijianyongshugaozhong.png" /></a>
   
   <!--  
   <div class="xiangm">
   <a href="<%=request.getContextPath()%>/webForumoneTouseid"><img src="images/atz.png" /></a>
   <a><img src="images/ahf.png"/></a>
   <a><img src="images/asc.png" /></a>
   </div>
   -->
 </div>
 <!--ltcont-->
</div>
<!--cont-->

</body>
</html>