<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@page import="java.util.*"%>
<%@page import="com.befriend.entity.*"%>
<%@page import="com.befriend.util.*"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>管理广告图</title>

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

<!--<div id="myDiv"></div>  -->
	<div>
	
	<h3>
	<a href=<%=request.getContextPath()%><%=session.getAttribute("home")%>>返回首页</a>
	<a href="getAdv?Online=0" >查看未上线</a>
	<a href="getAdv?Online=1">查看已上线</a></h3>
			<form   action="saveAdv" method="post" enctype="multipart/form-data">
			<table  style="text-align:center;" border=1>
				<tr>
					<td style="width: 100px;">广告名字</td>
					<td style="width: 100px;">广告排序</td>
					<td style="width: 200px;">广告概要</td>
				<td style="width: 100px;">广告连接</td>
					<td style="width: 200px;">广告图</td>
					<td style="width: 100px;">操作</td>
				
				</tr>
				
			
				<tr>
					<td ><textarea  style="width: 100px;height: 100px;" id="name" name="name" rows="" cols=""></textarea></td>
					<td><textarea  style="width: 100px;height: 100px;" id="sequence" name="sequence" rows="" cols=""></textarea></td>
					<td><textarea  style="width: 200px;height: 100px;" id="summary" name="summary" rows="" cols=""></textarea></td>
					<td><textarea  style="width: 200px;height: 100px;" id="href" name="href" rows="" cols=""></textarea></td>
				
					<td><input  style="width: 200px;" type="file" name="imgFile1" /></td>
					<td  style="width: 100px;" >
					<input type="submit" value="上传" onclick="return ck()"/>
					<input type="reset" value="重置" onclick="return ck()"/>
					 </td>
				</tr>
			</table>
		</form>
	<br>

		<form  action="*" method="post" enctype="multipart/form-data">
		<table style="text-align:center;" border=1 >
				<tr>
					<td style="width: 100px;">广告名字</td>
						<td style="width: 100px;">广告排序</td>
					<td style="width: 200px;">广告概要</td>
					<td style="width: 100px;">广告连接</td>
					<td style="width: 200px;">广告图</td>
					<td style="width: 100px;">操作</td>
					<td style="width: 200px;">日期</td>
					<td style="width: 200px;">展示时间</td>
					 
				</tr>
			
		
				<%
		List<Adv> al=(List)request.getAttribute("advl");
		for(int i=0;i<al.size();i++){
		%>
		
				<tr>
				
					<td ><textarea  readonly="readonly" style="width: 100px;height: 100px;" rows="" cols=""><%=al.get(i).getName() %></textarea></td>
				<td ><input style="width: 100px;height: 100px;" type="button" value="<%=al.get(i).getSequence() %>"></td>
				
				
					<td ><textarea  readonly="readonly" style="width: 200px;height: 100px;" rows="" cols=""><%=al.get(i).getSummary() %></textarea>
					<td ><textarea  readonly="readonly" style="width: 200px;height: 100px;" rows="" cols=""><%=al.get(i).getHref() %></textarea>
					</td>
					<td style="width: 200px;"><img style="width: 200px;height: 100px;" alt="" src="<%=request.getContextPath()%><%=al.get(i).getPathimg() %>"></td>
					<td >
					<% 
					if(al.get(i).getOnline()==1){
					%>
					<a href="onlineAdv?id=<%=al.get(i).getId() %>&Online=0" onclick="return ck()">下线</a>
					<% 
					}else{
					%>
					<a href="onlineAdv?id=<%=al.get(i).getId() %>&Online=1" onclick="return ck()">上线</a>
					<%
					} 
					%>
					<a href="byidLookAdv?id=<%=al.get(i).getId() %>" onclick="return ck()">修改</a>
					
					</td>
					<td >
					上传时间<br><%=al.get(i).getTime() %><br>
					下架时间<br>
					<%if(al.get(i).getFinaltime()!=null){ %>
					<%=al.get(i).getFinaltime()%>
					<%
					}
					%>
					</td>
					<td>
					<%=al.get(i).getCalculatingTime()%>小时
					</td>
				</tr>
				
			
			
			<% 
		}
			%>
			</table>
		</form>
		

	</div>
	
</body>
</html>