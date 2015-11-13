<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
     <%@page import="java.util.*"%>
    <%@page import="com.befriend.util.*"%>
<%@page import="com.befriend.entity.*"%>
<%
//获取论坛
List<ForumOne> fone=(List)request.getAttribute("fones");
//获取论坛最新回复
List<ForumTwo> ftwo=(List)request.getAttribute("ftwos");
//获取论坛创建者
List<User> us=(List)request.getAttribute("us");
//获取共多少页
int cpe=(Integer)request.getAttribute("cpe");
Object area=request.getAttribute("area");
//获取类型
int id=(Integer)request.getAttribute("id");
id=0;
String type="全国论坛";
//获取当前页数
int currentPage=(Integer)request.getAttribute("currentPage");
if(fone==null||us==null){
	%>
	<p>没有信息</p>
	<%
	return;
}
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">

<head>
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>社区互动</title>
<link href="<%=request.getContextPath()%>/SimulationApp/css/style.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="<%=request.getContextPath()%>/SimulationApp/js/jquery.js"></script>
<script type="text/javascript" src="js/style.js"></script>
<script type="text/javascript">
$(document).ready(function(){
	$.ajax({
		type:"post",
		url:"statisticsPVIP.action?id=2"
	});
});
</script>
<script type="text/javascript" src="js/style.js"></script>
</head>

<body>
<div class="topfixed"  style="position: static ;">
   <div class="top">
       <a href="<%=request.getContextPath()%>/webNewsA10" class="fl"><img src="<%=request.getContextPath()%>/SimulationApp/images/xw_03.png" height="40" /></a>
     <p class="set fr"><a href="<%=request.getContextPath()%>/SimulationApp/about.html"></a><a href="<%=request.getContextPath()%>/SimulationApp/about.html"><img src="<%=request.getContextPath()%>/SimulationApp/images/xw_07.png" height="40" /></a></p>
        </div>
   <!--top-->
   <ul class="nav">
     <li><a href="<%=request.getContextPath()%>/webNewsA10">今日必读</a></li>
      <li class="current"><a href="<%=request.getContextPath()%>/webForumApptype">社区互动</a></li>
      <li><a href="<%=request.getContextPath()%>/webGetapp">贴心服务</a></li>
      <li ><a href="<%=request.getContextPath()%>/SimulationApp/personal_information.jsp">我的信息</a></li>
    </ul>
   <!--nav-->
 </div>
<div class="cont" >
<script type="text/javascript">  
	function initcity() {
		var obj = document.getElementById("select");
		var index = obj.selectedIndex; // 选中索引
		var text = obj.options[index].text; // 选中文本
		var value = obj.options[index].value; // 选中值
		window.location.href="<%=request.getContextPath()%>/webForumApptype?model=5&area="+value;
		
	}

	
</script> 


  
 <!--topfixed-->
 <div class="tname tit1">
    <a style="float: left;" href="<%=request.getContextPath()%>/SimulationApp/post_publish.jsp"></a><b style="float: left;"><span><%=type %>讨论社区</span></b>
   <%
   if(id==5){
   %>
   <select id="select" style="float: left;margin-top: 12px;" onchange="initcity();" name="province" >
   <%
   if(area!=null){
   %>
   	<option><%=area %></option>
   	<%
   }else{
	   area="北京";
   }
   	%>
   	<option>北京</option>
   	<option>上海</option>
   	<option>重庆</option>
   	<option>安徽</option>
   	<option>福建</option>
   	
   	<option>甘肃</option>
   	<option>广东</option>
   	<option>广西</option>
   	<option>贵州</option>
   	<option>海南</option>
   	
   	<option>河北</option>
   	<option>黑龙江</option>
   	<option>河南</option>
   	<option>香港</option>
   	<option>湖北</option>
   	
   	<option>湖南</option>
   	<option>江苏</option>
   	<option>江西</option>
   	<option>吉林</option>
   	<option>辽宁</option>
   	
   	<option>澳门</option>
   	<option>内蒙古</option>
   	<option>宁夏</option>
   	<option>青海</option>
   	<option>山东</option>
    <option>山西</option>
   	<option>陕西</option>
   	<option>四川</option>
   	<option>台湾</option>
   	<option>天津</option>
   	
   	<option>新疆</option>
   	<option>西藏</option>
   	<option>云南</option>
   	<option>浙江</option>
   	<option>海外</option>
   	
</select> 
    <%
   }
    %>
 <a style="float: right;" href="<%=request.getContextPath()%>/SimulationApp/post_publish.jsp"><b>发表帖子</b></a>
    
</div>
 
               <%
                    for(int i=0;i<fone.size();i++){
                    %>
                    
   <ul class="userlist">
      <li>
                <div class="taoluncont">        
            <div class="taolun">
         
                   <div class="tall">
                   <p class="tneitong">
                   <a href="<%=request.getContextPath()%>/webForumLook?id=<%=fone.get(i).getId() %>">
                     <%
                   if(us.get(i).getImg()!=null){
                   %>
                   <img src="<%=request.getContextPath()%><%=us.get(i).getImg() %>" />
                   <%
                   }else{
                   %>
                    <img src="SimulationApp/images/logod.png" />
                   <%
                   }
                   %>
       
                   </a>
                   </p>
                   <div class="fl"><a href="<%=request.getContextPath()%>/webForumLook?id=<%=fone.get(i).getId() %>"><span style="font-size: 16px;"><b><%=us.get(i).getNickname() %></b><br /><span style="font-size: 12px"><%=fone.get(i).getTime() %></span></a></abbr></div></div>
                   <div class="huifu3">
                      <p class="other"><a href="<%=request.getContextPath()%>/webForumLook?id=<%=fone.get(i).getId() %>"><b><%=fone.get(i).getTitle() %></b></a></p>
                      <p class="huifunierong"><a href="<%=request.getContextPath()%>/webForumLook?id=<%=fone.get(i).getId() %>"><%=fone.get(i).getContent() %></a></p>
                      <p class="date"><span class="fl">来源：
                      <%
                      String are="";
                      if(fone.get(i).getArea()!=null){
                    	  are+=fone.get(i).getArea();
                      }
                      if(fone.get(i).getAreas()!=null){
                    	  are+=" "+fone.get(i).getAreas();
                      }
                      %>
                      <%=are %>
                      </span><span class="fr"><img src="SimulationApp/images/pinglun.png" /><%=fone.get(i).getFrs() %><img src="SimulationApp/images/huif.png" /><%=fone.get(i).getfHits() %></span></p>
                  
                  <!--  <br />
                  <hr />
                  -->
                   </div>
                  
                  
               </div>
               <!--taolun -->   
                
          </div>
              
              
      </li>
      
      
   </ul>
    <%
                    }
                   %>
   <!-- 返回主页 -->
   
 	<!-- 返回上一页 -->
   
  
   
   <!--userlist-->
   
   <p align="center">一共<%=cpe %>页 当前是第<%=currentPage %>页</p>
   <ul class="page page2">
<li><a href="<%=request.getContextPath()%>/webForumApptype">主页</a></li>
<li><a href="<%=request.getContextPath()%>/webForumApptype?model=<%=id%>&pageSize=10&currentPage=1&area=<%=area %>">首页</a></li>
<li><a href="<%=request.getContextPath()%>/webForumApptype?model=<%=id%>&pageSize=10&currentPage=<%=currentPage-1%>&area=<%=area %>">上一页</a></li>
<li><a href="<%=request.getContextPath()%>/webForumApptype?model=<%=id%>&pageSize=10&currentPage=<%=currentPage+1%>&area=<%=area %>">下一页</a></li>
<li><a href="<%=request.getContextPath()%>/webForumApptype?model=<%=id%>&pageSize=10&currentPage=<%=cpe %>&area=<%=area %>">末页</a></li>
<li><a href="javascript:history.back(-1);">返回</a></li>
</ul>
   <div class="my"><a href="<%=request.getContextPath()%>/webForumoneTouseid">我的帖子</a></div>
</div>
<!--cont-->

</body>
</html>