<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@page import="java.util.*"%>
    <%@page import="com.befriend.util.*"%>
<%@page import="com.befriend.entity.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>查看家长来稿</title>
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

<body style="background: #adc">

<div >
<h3><a href="<%=request.getContextPath()%><%=OpeFunction.request().getSession().getAttribute("home") %>" onclick="return cka();">返回主页</a></h3>

<form action="">
<table>
		
		
		
		
		

		<%
		
	
		ParentsLetter p=(ParentsLetter)request.getAttribute("p");
		if(p==null){
			return;
		}
	
		
	%>
		
			<tr>
		<td width="320"><h4>标题:<%=p.getTitle() %>		
		</h4></td>
		</tr>
		
		<%
		if(p.getImg()!=null){
		%>
		<td width="600"><img  alt="<%=request.getContextPath()%><%=p.getImg()%>" src="<%=request.getContextPath()%><%=p.getImg()%>"></td>	
		<%
		}else{
		%>
			<td width="600"><h4>无图</h4></td>	
		<%
		}
		%>
		
		<tr>
		<td width="177"><h4>内容:
		<textarea style="width: 600px;height: 300px">
		<%=p.getContent()%>
		</textarea>
		</h4></td>	
		</tr>
			<tr>
		<td width="177"><h4>反馈时间:<%=p.getTime()%></h4></td>	
		</tr>
			
			<tr>
		<td  width="163"><h4>
		<a onclick="return ck();" href="<%=request.getContextPath()%>/RemoveParentsLetterone?id=<%=p.getId() %>">删除</a>
		</h4></td>
	</tr>
		
		
		
		</table> 
		
	</form>

	</div>

</body>
</html>