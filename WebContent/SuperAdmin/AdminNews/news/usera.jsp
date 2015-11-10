<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
     <%@page import="java.util.*"%>
     <%@page import="com.befriend.util.*"%>
     <%@page import="com.befriend.entity.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>查看用户</title>

<script type="text/javascript" src="/Befriend/Jquery/jquery-2.1.3.min.js"></script>
<script type="text/javascript" >
function ck()
{
 if(confirm("确定"))
 {
	  
  return true;
  
  
 }
 else{
	   return false;
 }
}
function fk(){
	//alert("开始刷新");
	$.ajax({
		type:"post",
		url:"aStas.action"	
	});
}
$(document).ready(function(){

  $("#b01").click(function(){
	  
  htmlobj=$.ajax({url:"/Befriend/GetUsertime?timeq="+$("#timeq").val()+"&timeh="+$("#timeh").val()+"",async:false});
  $("#myDiv").html(htmlobj.responseText);
  });
});

 
  
  
 </script>
</head>
<body >
<label >开始日期：</label><input id="timeq" type="date" name="timeq" value=<%=OpeFunction.getNumTime(1)+" 00:00:00"%>/>
<label >结束日期：</label><input id="timeh" type="date" name="timeh" value=<%=OpeFunction.getNowTime() %>/>


<button style="width: 120px;height: 35px;" id="b01" type="button">搜索</button>
<input  style="width: 120px;height: 35px;" onclick="fk()" type="button" value="更新">
<div  id="myDiv"></div>
<div style="background: #adc">
<%
int all=Integer.parseInt(request.getAttribute("all").toString());//获取 共有多少用户
int syn=Integer.parseInt(request.getAttribute("syn").toString());//获取 共有多少用户
int bbt=Integer.parseInt(request.getAttribute("bbt").toString());//获取 共有多少用户
%>
<h3>优教通有<%=syn %>个用户</h3>
<h3>掌中校园有<%=bbt %>个用户</h3>
<h3>家长之友有<%=all-syn-bbt %>个用户</h3>
<h3>共有<%=all %>个用户</h3>
<!-- 
<form action="">
<table >
<tr>
<td width="200"><h4>--用户账号</h4></td>
<td width="200"><h4>用户地址</h4></td>

<td width="190"><h4>用户注册时间</h4></td>
<td width="120"><h4>用户登入次数</h4></td>
<td width="200"><h4>用户最后登入时间</h4></td>
<td  width=""><h4>用户手机号</h4></td>
</tr>
</table>
</form>
 -->
<%
List<User> ul=(List)request.getAttribute("ul");
%>
<h3>同步群<a href="synchrGroup" onclick="return ck()"><input type="button" value="确定" ></a></h3>

<h3>同步用户<a href="huanxinzc" onclick="return ck()"><input type="button" value="确定" ></a></h3>
<!-- 
<h3>在线用户<%=ul.size() %>个<a href="updateAllXiaxian" onclick="return ck()"><input type="button" value="一键全部下线" ></a></h3>
 -->
<% 
for(int i=0;i<0;i++){
%>

<form action="SetQ"  method="post" enctype="multipart/form-data">
<table >
<tr>

<td width="200"><%=ul.get(i).getUsername() %></td>
<td width="200"><%=ul.get(i).getAddress() %><%=ul.get(i).getAddcity()%></td>

<td width="240"><%=ul.get(i).getTime() %></td>
<td width="70"><%=ul.get(i).getLoginnum()%></td>
<td width="200"><%=ul.get(i).getFinaltime()%></td>
<td width=""><h4><%=ul.get(i).getPhone()%></h4></td>
</tr>
</table>
</form>
<%
}
%>
<h3>用户信息</h3>

<%
List<User> us=(List)request.getAttribute("GetUserAllus");
for(int i=0;i<0;i++){
%>
<form action="SetQ"  method="post" enctype="multipart/form-data">
<table >
<tr>

<td width="200"><%=us.get(i).getUsername() %></td>
<td width="200"><%=us.get(i).getAddress() %><%=us.get(i).getAddcity()%></td>

<td width="240"><%=us.get(i).getTime() %></td>
<td width="70"><%=us.get(i).getLoginnum()%></td>
<td width="200"><%=us.get(i).getFinaltime()%></td>
<td width=""><h4><%=us.get(i).getPhone()%></h4></td>
</tr>
</table>
</form>

<%
}
%>
<%
    	int currentPage=0;
    	int n=0;
    	currentPage=Integer.parseInt(request.getAttribute("currentPage").toString());//当前页码
    	n=Integer.parseInt(request.getAttribute("competence2").toString());//获取 共有多少页
    	
    	
     %>



		<br>共<%=n%>页</br> <br>第<%=currentPage%>页</br> <a
			href="GetUserAll?currentPage=1">首页</a> <a
			href="GetUserAll?currentPage=<%=currentPage-1%>">上一页</a> <a
			href="GetUserAll?currentPage=<%=currentPage+1%>">下一页</a> <a
			href="GetUserAll?currentPage=<%=n%>">末页</a>
			<a href=<%=request.getContextPath()%><%=session.getAttribute("home")%>>返回首页</a>
			
		
			<br></br>
			
			<%
			for(int s=1;s<=n;s++){
			    
			%>
			<!--  <a href="GetUserAll?currentPage=<%=s%>"><%=s%>&nbsp&nbsp</a> -->
			<%
			if(s%20==0){
				
			    %>
			    <br>
			    <% 
			}
			}
			%>

 <br>

</div>

</body>
</html>