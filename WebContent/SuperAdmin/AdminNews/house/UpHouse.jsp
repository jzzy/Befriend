<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="com.befriend.util.*"%>
<%@page import="com.befriend.entity.*"%>
<!doctype html>

<html>
<head>
<meta charset="utf-8" />
<title>学区房信息修改</title>




<script type="text/javascript">

function cka(){
	 if(confirm("确定放弃当前操作返回主页？"))
	 	{
		
		  return true;
	 	}
	 		else{
		   return false;
	 		}
}


function ck()
{
 if(confirm("确定要删除么？"))
 {
	  
  return true;
  
  
 }
 else{
	   return false;
 }

}
	
	
	function check(picForm)
	{
	   
		  var schoolname = document.getElementById("schoolname").value;
		  var address = document.getElementById("address").value;
		  var time = document.getElementById("time").value;
		  if (schoolname==""||schoolname==null||schoolname.length>40)
	    	{
	    		alert("请填写40个汉字以内的学校名字！");
	    	
	    		return false;
	  	
	    	}
	      if (address==""||address==null||address.length>30)
	    	{
	    		alert("请正确填写地址30字以内！");
	    	
	    		return false;
	  		}
	  	  if (time==""||time==null)
			{
			alert("请输入时间！");
		
			return false;
			} 
	  	 if(confirm("确定要修改么？"))
	  	 {
	  		  
	  	  return true;
	  	  
	  	  
	  	 }
	  	 else{
	  		   return false;
	  	 }
	  
		  
	
return true;
	}
	
		
	</script>
</head>
<body>

	<div style="background: #adc; width: 100%; height: 1000px;">
	<h2>
	学区房信息修改</h2>
	<%
	House h=(House)request.getAttribute("h");
	if(h==null){
		%>
		<b>没有信息</b>
		<% 
		return;
	}
	%>
		<form action="upHouse" method="post" name="picForm"
			enctype="multipart/form-data">
			<table cellpadding="0" cellspacing="0" style="width: 1500px">
				
				<tr>
					<td>学校名字：</td>
					<td><textarea name="schoolname" id="schoolname"
							cols="100" rows="8"
							style="width: 300px; height: 50px;"><%=h.getSchoolname() %></textarea></td>
				</tr>
				<tr>
					<td>学校地址：</td>
					<td><textarea name="address" id="address"
							cols="100" rows="8"
							style="width: 300px; height: 50px;"><%=h.getAddress() %></textarea></td>
				</tr>
				
				<tr>
					<td>范围:</td>
					<td><textarea name="scope" id="scope"
							cols="100" rows="8"
							style="width: 300px; height: 200px;"><%=h.getScope() %></textarea></td>
				</tr>

				<tr>
					<td>楼盘：</td>
					<td colspan="2"><textarea name="property" id="property"
							cols="100" rows="8"
							style="width:300px; height: 200px;"><%=h.getProperty() %></textarea>

					</td>
				</tr>
				<tr>
					<td>关键词：</td>
					<td colspan="2"><textarea name="Keywords" id="Keywords"
							cols="100" rows="8"
							style="width:300px; height: 200px;"><%=h.getKeywords() %></textarea>

					</td>
				</tr>
				
				
				
				

				<tr>
					<td>发布时间：( 例如 : 2014-12-09 12:12:32 )</td>
					<td><input type="datetime" id="time" name="time"
						value="<%=h.getTime() %>"/>
						<input style="display: none" type="text" id="123" name="id"
						value="<%=h.getId() %>"/>
						</td>

				</tr>
				<%
				Object hh=request.getAttribute("hh");
				if(hh!=null){
				%>
				<!-- 
				<tr>
					<td></td>
					<td style="color: red;"><%=hh %>
						</td>

				</tr>
				 -->
				<%
				
				}
				%>
				<tr>
					<td><br /> <input type="submit" name="button"
						onclick="return check(picForm);" value="确定修改" />
						<a onclick="return ck();" href="<%=request.getContextPath()%>/RemovetHouse?id=<%=h.getId() %>">
						<input type="button" name="button"
						 value="删除" /></a></td>
				</tr>

			</table>

		</form>
		
		---------<h3><a href="<%=request.getContextPath()%><%=OpeFunction.request().getSession().getAttribute("home") %>" onclick="return cka();">返回主页</a></h3>
	</div>

</body>
</html>
