<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    <%@page import="java.util.*"%>
    <%@page import="com.befriend.util.*"%>
<%@page import="com.befriend.entity.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<script type="text/javascript">
function check()
{
   
	  var schoolname = document.getElementById("schoolname").value;
	 
	  if (schoolname==""||schoolname==null||schoolname.length>40)
    	{
    		alert("请填写搜索内容");
    	
    		return false;
  	
    	}
	  

return true;
}
</script>
<script  src="SimulationApp/laigao/js/jquery.js"></script>
<script type="text/javascript">
$(document).ready(function(){
	$.ajax({
		type:"post",
		url:"statisticsPVIP.action"
	});
});
</script>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>搜索结果</title>
<link href="<%=request.getContextPath() %>/SimulationApp/laigao/css/style.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="<%=request.getContextPath() %>/SimulationApp/laigao/js/jquery.js" ></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/SimulationApp/laigao/js/jqu.js" ></script>
</head>

<body>
<%
Object name=request.getAttribute("name");
%>
<div class="cont">
<form action="selectwxHouseall" method="post" enctype="application/x-www-form-urlencoded">

<h1 class="tit tit9">
<span class="fl">
<i class="fl">
<a style="float:left;" href="javascript:history.back(-1);">&lt; </a>
</i> 
<b>
<input type="text" id="schoolname" name="schoolname" value="<%=name %>" />
<img src="<%=request.getContextPath() %>/SimulationApp/laigao/images/gb.jpg" class="colse3"/>
</b>
<input type="submit" value="搜索" onclick="return check();" class="ersearch" />
</span>
</h1>
<div class="rightcont">
   <h5 class="wrighttit">相关结果：</h5>
  
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
   <div class="xinxi1">
   
     <div class="heihei">
      <b class="bluetit" style="font-size: 18px">学校信息：</b>
      <p class="boldtit"><b style="font-size: 17px"><%=l.get(i).getSchoolname() %></b></p>
      <p>学校地址：</b><%=l.get(i).getAddress() %></p>
     </div>
     <!--heihei-->
     <div class="heihei">
      <%
      if(l.get(i).getScope()!=null){
      %>
      <p class="boldtit2"><b  style="font-size: 16px">服务范围：</b></p>
      <p><%=l.get(i).getScope() %></p>
      <%
      }
      %>
       <%
      if(l.get(i).getProperty()!=null){
      %>
      <p class="boldtit2">楼盘信息：</p>
      <p><%=l.get(i).getProperty() %></p>
      <%
      }
      %>
     </div>
     <!--heihei-->
  </div>
<!--xinxi1-->
<%

}
%>
</div>
<!--recont-->

</div>
<!--cont-->
</form>
</body>
</html>

