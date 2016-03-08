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
<title></title>
<script type="text/javascript" src="sample_lihu/js/jquery-1.10.2.min.js"></script>
<script type="text/javascript" src="sample_lihu/js/common.js"></script>
<script type="text/javascript">
$(document).ready(function(){
	$.ajax({
		type:"post",
		url:"statisticsPVIP.action?id=2"
	});
});
</script>
<link href="sample_lihu/css/style.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" href="sample_lihu/css/pullToRefresh.css"/>
</head>
<body>
	<div id="wrap">
		<div id="header">
			<div class="topArea clearfix">
				<h1><a href="webNewsA10"><img src="sample_lihu/images/logo.gif" alt="家长之友" /></a></h1>
				<div class="relocation">
					<a href="sample_lihu/jsp/relocation.jsp">北京&nbsp;<img src="sample_lihu/images/location_more.gif" alt="down" /></a>
				</div>
				<div class="topTool">
					<span><a href="sample_lihu/post4.html">发表</a></span>
				</div>
			</div>
			<ul class="topMenu clearfix">
				<li><a href="webNewsA10"><img src="sample_lihu/images/hottoday_ico.gif" alt="fire" />&nbsp;今日必读</a></li>
				<li class="on"><a href="webForumApptype">社区互动</a></li>
				<li><a href="webGetapp">贴心服务</a></li>
				<li><a href="sample_lihu/jsp/mypage.jsp">我的信息</a></li>
			</ul>
		</div><!--header-->
		<div id="container">
			<div class="forum">
			<div id="wrapper">
				<h2 class="tit2"><img src="sample_lihu/images/forum_tit.gif" alt="全国论坛讨论社区" /></h2>
				<div id="wrapper">
					<ul>
					
               <%
                    for(int i=0;i<fone.size();i++){
                    	System.out.println(us.get(i));
                    	System.out.println(fone.get(i));
                    	if(us.get(i)==null||fone.get(i)==null){
                    		continue;
                    	}
                    %>
                  
						<li>
							<div class="user clearfix">
				   <%
                   if(us.get(i).getImg()!=null){
                   %>
                   
                   <p class="leftArea"><img src="<%=request.getContextPath()%><%=us.get(i).getImg() %>" style="width: 50px;height: 50px;" alt="sample" /></p>
                   <%
                   }else{
                   %>
                   
                   <p class="leftArea"><img src="SimulationApp/images/logod.png" alt="sample" /></p>
                   <%
                   }
                   %>
								
								<p class="rightArea">
									<span><%=us.get(i).getNickname() %></span>
									<span><%=fone.get(i).getTime() %></span>
								</p>
							</div>
							  <a href="webForumLook?id=<%=fone.get(i).getId()%>" ><div class="title"><%=fone.get(i).getTitle() %></div></a>
							  <a href="webForumLook?id=<%=fone.get(i).getId()%>" ><div class="detail"><%=fone.get(i).getContent() %></div></a>
							<div class="imgList clearfix">
							  
							  <p>
							  <a href="webForumLook?id=<%=fone.get(i).getId()%>" >	
							  <img src="<%=request.getContextPath()+fone.get(i).getImg() %>" alt="sample" />
							    </a>
							  </p>
							
								
							</div>
							<div class="info">
								<span><img src="sample_lihu/images/comment_ico.gif" alt="回复数" />&nbsp;<%=fone.get(i).getFrs() %></span>
								<span><img src="sample_lihu/images/favor2_ico.gif" alt="浏览数" />&nbsp;<%=fone.get(i).getfHits() %></span>
							</div>
						</li>
						
						<% 
                    }
						%>
					</ul>
				</div>
				</div>
			</div>
		</div><!--container-->
		<div id="footer">
		</div><!--footer-->
	</div><!--wrap-->
<script src="sample_lihu/js/iscroll.js"></script>
<script src="sample_lihu/js/pullToRefresh.js"></script>
<!--
<script>

refresher.init({
	id:"wrapper",//<------------------------------------------------------------------------------------
	pullDownAction:Refresh,                                                            
	pullUpAction:Load 																			
	});																																							
function Refresh() {																
	setTimeout(function () {	// <-- Simulate network congestion, remove setTimeout from production!
		var el, li, i;																		
		el =document.querySelector("#wrapper ul");					
		//这里写你的刷新代码				
		document.getElementById("wrapper").querySelector(".pullDownIcon").style.display="none";		
		document.getElementById("wrapper").querySelector(".pullDownLabel").innerHTML="<img src='sample_lihu/css/ok.png'/>刷新成功";																					 
		setTimeout(function () {
			wrapper.refresh();
			document.getElementById("wrapper").querySelector(".pullDownLabel").innerHTML="";								
			},1000);//模拟qq下拉刷新显示成功效果
		/****remember to refresh after  action completed！ ---yourId.refresh(); ----| ****/
	}, 1000);
}
function Load() {
	setTimeout(function () {// <-- Simulate network congestion, remove setTimeout from production!
		var el, li, i;
		el =document.querySelector("#wrapper ul");
		for (i=0; i<10; i++) {
			li = document.createElement('li');
			li.innerHTML='<div class="user clearfix"><p class="leftArea"><img src="sample_lihu/images/testuser_ico.gif" alt="sample" /></p><p class="rightArea"><span>家长之友aaa</span><span>2016-01-15 09:54</span></p></div><div class="title"><a href="forumreview.html">这里是标题</a></div><div class="detail"><a href="forumreview.html">第一题是这样的第一题是这样的第一题是这样的第一题是这样的</a></div><div class="imgList clearfix"><p><a href="forumreview.html"><img src="sample_lihu/images/listBanner.jpg" alt="sample" /></a></p><p><a href="forumreview.html"><img src="sample_lihu/images/listBanner.jpg" alt="sample" /></a></p><p><a href="forumreview.html"><img src="sample_lihu/images/listBanner.jpg" alt="sample" /></a></p></div><div class="info"><span><img src="sample_lihu/images/comment_ico.gif" alt="留言" />&nbsp;520</span><span><img src="sample_lihu/images/favor2_ico.gif" alt="关注" />&nbsp;120</span></div>';
			el.appendChild(li, el.childNodes[0]);
		}
		wrapper.refresh();/****remember to refresh after action completed！！！   ---id.refresh(); --- ****/
	},2000);	
}

</script>
-->
</body>
</html>


