<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
     <%@page import="java.util.*"%>
     <%@page import="com.befriend.util.*"%>
     <%@page import="com.befriend.entity.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>查看点评</title>

<script type="text/javascript" src="/Befriend/Jquery/jquery-2.1.3.min.js"></script>
<script type="text/javascript" >
function ck()
{
 if(confirm("确定?"))
 {
	  
  return true;
  
  
 }
 else{
	   return false;
 }
}
function onk(){
	var str=document.getElementById("inp123").value;
	//alert(str);
	location.href="getLikeCommment?content="+str;
	

	
	
};

 </script>
</head>
<body style="background: #adc" >
<div>
<input style="width: 100px;height: 55px;" type="button" onclick="onk()" value="搜索">
<input id="inp123" style="width: 200px;height: 50px;" type="text" >
<a href="getAllCommments">显示全部</a>
</div>
<div>
<form action="">
<table>
<tr>
<td style="width: 200px;height: 50px;">评论内容</td>
<td style="width: 200px;height: 50px;">评论时间</td>
<td style="width: 100px;height: 50px;">操作</td>
</tr>

<%
int currentPage=Integer.parseInt(request.getAttribute("currentPage").toString());
List<EduComment> el=new ArrayList<EduComment>();
Object obj=request.getAttribute("el");
if(obj!=null){
el=(List)obj;
}
int pageSize=Integer.parseInt(request.getAttribute("pageSize").toString());

for(int i=0;i<el.size();i++){
%>


<tr>
<td><textarea style="width: 200px;height: 50px;" readonly="readonly"><%=el.get(i).getContent() %></textarea>
</td>
<td ><%=el.get(i).getTime()%></td>
<td><a href="deleteCommment?replyId=<%=el.get(i).getId() %>" onclick="return ck()" >删除</a></td>
</tr>


<%
}
%>
</table>
</form>
</div>



		<br>第<%=currentPage%>页</br>
		<a href="getAllCommments?currentPage=1">首页</a> 
		<%
		if(currentPage-1>0){
		%>
		<a href="getAllCommments?currentPage=<%=currentPage-1%>">上一页</a>
		<%
		}
		%>
		<%
		System.out.print(obj);
		if(obj!=null){
		%>
		<a href="getAllCommments?currentPage=<%=currentPage+1%>">下一页</a>
		<%
		}
		%>
		<a href=<%=request.getContextPath()%><%=session.getAttribute("home")%>>返回首页</a>



</body>
</html>