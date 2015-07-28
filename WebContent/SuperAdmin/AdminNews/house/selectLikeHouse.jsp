<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@page import="java.util.*"%>
    <%@page import="com.befriend.util.*"%>
<%@page import="com.befriend.entity.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>查看文章</title>
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
  
  

 </script>
<body>


<div style="background: #ada;">
<form action="">
<table>
		<tr>
		<td width="320"><h4>学校名字</h4></td>
		<td width="177"><h4>学校地址</h4></td>
		<td  width="163"><h4>上传日期</h4></td>
		<td  width="163"><h4>上传者ID</h4></td>
		<td  width="163"><h4>操作</h4></td>
	</tr>
		
		
		
		</table> 
		</form>

		<%
		
	
	List<House> l=(List)request.getAttribute("hl");
		
		if(l==null){
			
			%>
			<b style="color: red;">没有您要的数据</b>
			<%
			return;
		}
	if(l.size()==0){
			
			%>
			<b style="color: red;">没有您要的数据</b>
			<%
			return;
		}
	 for(int i=0;i<l.size();i++){
		
	%>
		<form id="<%=i %>" action="" name="picForm">
		<table>
		<tr>
		<td width="320"><h4><a href="<%=request.getContextPath()%>/selectHousenoe?id=<%=l.get(i).getId() %>"><%=l.get(i).getSchoolname() %></a>
		
		</h4></td>
		<td width="177"><h4><%=l.get(i).getAddress()%></h4></td>
		<td  width="163"><h4><%=l.get(i).getTime()%></h4></td>
		<td  width="163"><h4><%=l.get(i).getAdmin()%></h4></td>
		<td  width="163"><h4>
		<a onclick="return ck();" href="<%=request.getContextPath()%>/RemovetHouse?id=<%=l.get(i).getId() %>">删除</a>
		</h4></td>
	</tr>
		
		
		
		</table> 
		
		<%
		
		
	
	
	 }
	
		%>

	



	
	</div>

</body>
</html>