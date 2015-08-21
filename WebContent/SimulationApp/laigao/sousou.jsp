<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@page import="java.util.*"%>
    <%@page import="com.befriend.util.*"%>
<%@page import="com.befriend.entity.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>搜索</title>
<link href="<%=request.getContextPath() %>/SimulationApp/laigao/css/style.css" rel="stylesheet" type="text/css" />
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


	
	
	
function check()
{
   
	  var schoolname = document.getElementById("schoolname").value;
	 
	  if (schoolname==""||schoolname==null||schoolname.length>40)
    	{
    		alert("请填写搜索内容");
    	
    		return false;
  	
    	}
      
  
	  

return true;
}
	
		
	</script>
</head>

<body>
<form action="selectwxHouseall" method="post" enctype="application/x-www-form-urlencoded">
<div class="cont">
   <h1 class="tit tit5"><a href="Special_Columns.html" class="fl"><a style="float:left;" href="javascript:history.back(-1);">&lt; 搜寻学区房</a></a><b></b></h1>
   <div class="relycont2">
      <div class="sss"><input type="text" id="schoolname" name="schoolname" class="scon" /><input onclick="return check();" type="submit" value="" class="sbut" /></div>
      <ul style="color: black; font-size: 16px;" class="slist">
         <li>输入要查找的学校获得对应学区房信息</li>
         <li>输入学区房小区名称获得周围学校信息</li>
      </ul>
   </div>
   <!--relycont2-->
   <div class="dasou">
      <p class="stit" style="color: #666; font-size: 18px;">大家都在搜：</p>
      <ul>
      	<%
		
	
	List<House> l=(List)request.getAttribute("hl");
		
		if(l==null){
			
			%>
			<b style="color: red;">未找到相关信息</b>
			<%
			return;
		}
	if(l.size()==0){
			
			%>
			<b style="color: red;">未找到相关信息</b>
			<%
			return;
		}
	 for(int i=0;i<l.size();i++){
		
	%>
         <li "><a style="color: black; font-size: 14px;" href="<%=request.getContextPath()%>/selectwxHouseall?schoolname=<%=l.get(i).getSchoolname() %>"><%=l.get(i).getSchoolname() %></a></li>
         <%
	 }
         %>
         
      </ul>
   </div>
   <!--dasou-->
</div>
<!--cont-->
</form>
</body>
</html>
