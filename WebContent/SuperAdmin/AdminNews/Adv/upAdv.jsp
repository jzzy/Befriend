<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@page import="java.util.*"%>
<%@page import="com.befriend.entity.*"%>
<%@page import="com.befriend.util.*"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>修改广告图信息</title>

</head>
<script type="text/javascript" src="/Befriend/Jquery/jquery-2.1.3.min.js"></script>
<script type="text/javascript" language="javascript">

$(document).ready(function(){

	  $("#b01").click(function(){
		 
	  htmlobj=$.ajax({url:"/Befriend/Appset",async:false});
	  $("#myDiv").html(htmlobj.responseText);
	  });
	});
  function ck()
  {
   if(confirm("确定要么？"))
   {
	
    return true;
   }
   else{
	   return false;
   }
  
  }
	function check()
	{
		  var valuea = document.getElementById("name").value;
		  var valuep = document.getElementById("type").value;
    if (valuea==""||valuea==null)
    	{
    		alert("app名！");
    	
    		return false;
  	}
    if (valuep==""||valuep==null)
    	{
    		alert("app概要！");
    	
    		return false;
  	}
return true;

	}
 </script>
<body >
<a href="<%=request.getContextPath()%>/getAdv">返回首页</a>
<!--<div id="myDiv"></div>  -->
	<div>
	<%
	Adv adv=(Adv)request.getAttribute("adv");
	
	%>
			<form   action="updateAdv" method="post" enctype="multipart/form-data">
			<table  style="text-align:center;" border=1>
				<tr>
					<td style="width: 100px;">广告名字</td>
						<td style="width: 100px;">广告排序</td>
					<td style="width: 200px;">广告概要</td>
				<td style="width: 100px;">广告连接</td>
					<td style="width: 200px;">原广告图</td>
					<td style="width: 200px;">修改</td>
					<td style="width: 100px;">操作</td>
				
				</tr>
				<%
				if(adv!=null){
				%>
			
				<tr>
					<td ><textarea  style="width: 100px;height: 100px;" id="name" name="name" rows="" cols=""><%=adv.getName() %></textarea></td>
					<td ><textarea  style="width: 100px;height: 100px;" id="sequence" name="sequence" rows="" cols=""><%=adv.getSequence() %></textarea></td>
					<td><textarea  style="width: 200px;height: 100px;" id="" name="summary" rows="" cols=""><%=adv.getSummary()%></textarea></td>
					<td><textarea  style="width: 200px;height: 100px;" name="href" rows="" cols=""><%=adv.getHref()%></textarea></td>
					<td>
					<img alt=""style="width: 200px;height: 100px;" src="<%=request.getContextPath()+adv.getPathimg()%>">
					</td>
					<td><input  style="width: 200px;" type="file" name="imgFile1" /></td>
					<td  style="width: 100px;" >
					<input style="display: none" name="id" value="<%=adv.getId()%>">
					<input type="submit" value="更新" onclick="return ck()"/>
					<input type="reset" value="重置" onclick="return ck()"/>
					 </td>
				</tr>
				<%} %>
			</table>
		</form>

	

	</div>
	
</body>
</html>