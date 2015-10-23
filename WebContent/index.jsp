<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<script type="text/javascript" src="weixin/js/jquery.js"></script>
  <script type="text/javascript" >
    $.getScript('http://int.dpool.sina.com.cn/iplookup/iplookup.php?format=js',function(){  
    	document.getElementById('div').innerHTML = "根据IP 地址为:"+remote_ip_info.country+"-"+remote_ip_info.province+"-"+remote_ip_info.city;
    	//  alert("地址为:"+remote_ip_info.country+"-"+remote_ip_info.province+"-"+remote_ip_info.city);//国家省份城市
    	 
         
    	});  
   

    </script>
<body>Hello <%=request.getContextPath()%>!

<div style="background:#abc">
<hr></hr>
<a href="SuperAdmin/SuperAdmin.jsp">系统管理员登入</a>
<a href="SuperAdmin/UserAdminForum.jsp">用户论坛管理员登入</a>
<a href="SuperAdmin/UserAdminInformation.jsp">查看用户注册信息</a>
<hr></hr>
</div>

<div style="background:#adc">
	<a href="<%=request.getContextPath()%>/Newsget">管理新闻</a><br>
	<hr></hr>
	<a href="<%=request.getContextPath()%>/Appset">App管理</a><br>
	
	<a href="<%=request.getContextPath()%>/SuperAdmin/SuperAdmin/App/JZTDUP.jsp">家长天地App更新</a><br>	
	<hr></hr>
	<a href="<%=request.getContextPath()%>/SuperAdmin/AdminForum/Forumsave.jsp">添加论坛</a><br>	
	<a href="<%=request.getContextPath()%>/getUser">新增各地论坛管理员</a><br>
	<a href="<%=request.getContextPath()%>/getUseradmin">查询论坛管理员</a><br>
	<hr></hr>
	<a href="<%=request.getContextPath()%>/weiXniBDN">微信新闻首页</a><br>
	<a href="<%=request.getContextPath()%>/SimulationApp/login.html">WEB用户登入</a><br>
	<a href="<%=request.getContextPath()%>/webNewsA10">WEB主页</a><br>
	
	<hr></hr>
	<a href="<%=request.getContextPath()%>/weixin/Wonderful.html">精彩互动</a><br>	
	<a href="<%=request.getContextPath()%>/weixin/Local_info.html">本地资讯</a><br>
	<hr></hr>
	<a href="<%=request.getContextPath()%>/SimulationApp/laigao/laigao.html">家长投稿</a><br>
	
	<a href="<%=request.getContextPath()%>/SimulationApp/laigao/erro.html">没有搜索到</a><br>
	<a href="<%=request.getContextPath()%>/selectwxHouseallzh">搜索</a><br>
	<a href="<%=request.getContextPath()%>/source">查看源码</a><br>
		<a href="<%=request.getContextPath()%>/SimulationApp/feedback.html">意见反馈</a><br>
	
	<a href="<%=request.getContextPath()%>/SimulationApp/exchangebook.jsp">教育部推荐用书</a><br>
	
	
</div>
<div id="div" style="color: blue;" ></div>
</body>

</html>
