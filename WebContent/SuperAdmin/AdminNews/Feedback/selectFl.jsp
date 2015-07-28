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



<div style="background: #adc">
<h3><a href="<%=request.getContextPath()%><%=OpeFunction.request().getSession().getAttribute("home") %>" onclick="return cka();">返回主页</a></h3>

<form action="">
<table>
		<tr>
		<td width="320"><h4>用户名</h4></td>
		<td width="177"><h4>反馈时间</h4></td>
		<td  width="163"><h4>操作</h4></td>
	</tr>
		
		
		
		</table> 
		</form>

		<%
		
	
	List<Feedback> l=(List)request.getAttribute("fl");
		if(l==null){
			return;
		}
	 for(int i=0;i<l.size();i++){
		
	%>
		<form id="<%=i %>" action="" name="picForm">
		<table>
		<tr>
		<td width="320"><h4><a href="<%=request.getContextPath()%>/selectjzfkid?id=<%=l.get(i).getId() %>"><%=l.get(i).getUsername() %></a>		
		</h4></td>
		<td width="177"><h4><%=l.get(i).getTime()%></h4></td>	
		<td  width="163"><h4>
		<a onclick="return ck();" href="<%=request.getContextPath()%>/Removejzfkid?id=<%=l.get(i).getId() %>">删除</a>
		</h4></td>
	</tr>
		
		
		
		</table> 
		
		<%
		
		
	
	
	 }
	
		%>

	



	
	</div>

</body>
</html>