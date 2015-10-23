<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@page import="java.util.*"%>
    <%@page import="com.befriend.util.*"%>
<%@page import="com.befriend.entity.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>查看</title>
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

请输入:<input type="text"id="tet" value="" name="tet" />

<button id="b01" type="button"  >搜索学区房</button>
<div id="myDiv"></div>
<div style="background: #adc">
<form action="">
<table>
		<tr>
		<td width="320"><h4>学校名字</h4></td>
		<td width="177"><h4>学校地址</h4></td>
		<td  width="163"><h4>上传日期</h4></td>
		<td  width="163"><h4>上传者ID</h4></td>
		<td  width="163"><h4>操作</h4></td>
		<td  width="163"><h4>被搜索次数</h4></td>
	</tr>
		
		
		
		</table> 
		</form>

		<%
		int currentPage=0;
    	int n=0;
    	currentPage=Integer.parseInt(request.getAttribute("currentPage").toString());//当前页码
    	n=Integer.parseInt(request.getAttribute("n").toString());//获取 共有多少页
	
	List<House> l=(List)request.getAttribute("hl");
		if(l==null){
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
		<a onclick="return ck();" href="<%=request.getContextPath()%>/RemovetHouse?id=<%=l.get(i).getId() %>&currentPage=<%=currentPage%>">删除</a>
		</h4></td>
		<td  width="163"><h4><%=l.get(i).getCount()%></h4></td>
	</tr>
		
		
		
		</table> 
		
		<%
		
		
	
	
	 }
	
		%>

	



		<br>共<%=n%>页</br> <br>第<%=currentPage%>页</br> <a
			href="selectHouse?currentPage=1">首页</a> <a
			href="selectHouse?currentPage=<%=currentPage-1%>">上一页</a> <a
			href="selectHouse?currentPage=<%=currentPage+1%>">下一页</a> <a
			href="selectHouse?currentPage=<%=n%>">末页</a>
			
		---------<h3><a href="<%=request.getContextPath()%><%=OpeFunction.request().getSession().getAttribute("home") %>" onclick="return cka();">返回主页</a></h3>

			<br></br>
			<%
			for(int s=1;s<=n;s++){
			    
			%>
			<a href="selectHouse?currentPage=<%=s%>"><%=s%>&nbsp&nbsp</a> 
			<%
			if(s%20==0){
			    %>
			    <br></br>
			    <% 
			}
			}
			%>
	</div>

</body>
</html>