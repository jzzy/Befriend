<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
     <%@page import="java.util.*"%>
     <%@page import="com.befriend.util.*"%>
     <%@page import="com.befriend.entity.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>查询用户</title>
</head>
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
   </script>
<body>
<%
List<User> ush=(List)request.getAttribute("usaha");
List<Password> pl=(List)request.getAttribute("pl");
%>
<%
if(ush.size()==0){
%>
<form action="">
<tr>
<td>没有您要的用户!!!</td>
</tr>
</form>
<%} %>
<% 
//获取管理员
Admin admin=(Admin)session.getAttribute("admin");
if(admin==null){
	return;
}
for(int i=0;i<ush.size();i++){
	//等于1 就是 超级管理员
	if(admin.getLevel()==1){
		if(pl.get(i)==null||ush.get(i)==null){
			return;
		}
%>
<form action="">
<table>
<tr>
<td>
</td>
</tr>
<tr>
<td>头像:<img height="70" width="100" src="<%=request.getContextPath()+(ush.get(i).getImg()==null?"/SimulationApp/images/logod.png":ush.get(i).getImg()) %>" />
<a href="userLogout?id=<%=ush.get(i).getId() %>" onclick="return ck()"><input type="button" value="注销手机号" ></a>

</td>
</tr>
<tr>
<td>

用户:<%=ush.get(i).getUsername() %>

</td>
</tr>
<tr>
<td>昵称:<%=ush.get(i).getNickname()==null?"无":ush.get(i).getNickname() %>
</td>
</tr>
<tr>
<td>密码:<%=pl.get(i).getPassword() %>
</td>
</tr>
<tr>
<td>手机:<%=ush.get(i).getPhone()==null?"无":ush.get(i).getPhone() %>
</td>
</tr>
<tr>
<td>
编号:<a href="getUserin?id=<%=ush.get(i).getId() %>" ><%=ush.get(i).getAccnumno() %></a>
</td>
</tr>

<tr>
<td>注册时间:<%=ush.get(i).getTime()%>
</td>
</tr>
<tr>
<td>登入次数:<%=ush.get(i).getLoginnum()%>
</td>
</tr>
<tr>
<td>最后登入时间:<%=ush.get(i).getFinaltime()==null?"无":ush.get(i).getFinaltime()%>
</td>
</tr>
<tr>
<td>最后登入时间:<%=ush.get(i).getFinaltime()==null?"无":ush.get(i).getFinaltime()%>
</td>
</tr>
<%
String come="";

if(ush.get(i).getCome().equals("syn")){
	come="优教通";
}else if(ush.get(i).getCome().equals("own")){
	come="家长之友";
}else if(ush.get(i).getCome().equals("bbt")){
	come="掌中校园";
}else if(ush.get(i).getCome().equals("zdx")){
	come="电信";
}
%>
<tr>
<td>来自:<%=come %>
</td>
</tr>
</table>

</form>

<%
		
	}else{
		if(ush.get(i)==null){
			return;
		}
	%>
	<form action="">
<tr>
<td><a href="getUserin?id=<%=ush.get(i).getId() %>" ><%=ush.get(i).getUsername() %></a></td>
</tr>
</form>
	<% 
	}
}
%>


</body>
</html>