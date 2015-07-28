<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@page import="java.util.*"%>
    <%@page import="com.befriend.util.*"%>
<%@page import="com.befriend.entity.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>查看家长反馈</title>
</head>
<script type="text/javascript" src="/Befriend/Jquery/jquery-2.1.3.min.js"></script>
<script type="text/javascript" language="javascript">

function cka()
{
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
  $(document).ready(function(){

	  $("#b01").click(function(){
		  
	  htmlobj=$.ajax({url:"/Befriend/selectHouseall?schoolname="+$("#tet").val(),async:false});
	  $("#myDiv").html(htmlobj.responseText);
	  });
	});
  

 </script>
<body>
<div style="background: #ada;">
<body>


<div style="background: #adc">
<h3><a href="<%=request.getContextPath()%><%=OpeFunction.request().getSession().getAttribute("home") %>" onclick="return cka();">返回主页</a></h3>

<form action="">
<table>
		
		
		
		
		

		<%
		
	
	Feedback f=(Feedback)request.getAttribute("f");
		if(f==null){
			return;
		}
	
		
	%>
		
			<tr>
		<td width="320"><h4>用户名:<%=f.getUsername() %>		
		</h4></td>
		</tr>
		<tr>
		<td width="177"><h4>反馈内容:
		<textarea style="width: 600px;height: 300px">
		<%=f.getInformation()%>
		</textarea>
		</h4></td>	
		</tr>
			<tr>
		<td width="177"><h4>反馈时间:<%=f.getTime()%></h4></td>	
		</tr>
			
			<tr>
		<td  width="163"><h4>
		<a onclick="return ck();" href="<%=request.getContextPath()%>/Removejzfkid?id=<%=f.getId() %>">删除</a>
		</h4></td>
	</tr>
		
		
		
		</table> 
		
	</form>

	</div>

</body>
</html>