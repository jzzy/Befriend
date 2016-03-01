<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    <%@page import="java.util.*"%>
    <%@page import="com.befriend.util.*"%>
<%@page import="com.befriend.entity.*"%>
<% 
List<ForumTwo> fs=(List)request.getAttribute("ftwos");//论坛评论信息
List<User> us=(List)request.getAttribute("us");//论坛评论的用的用户信息
List<User> fu=(List)request.getAttribute("fu");// 论坛用户之间 回复的 用户信息
List<User> fut=(List)request.getAttribute("fut");// 论坛用户之间 被回复 用户信息
List fl=(List)request.getAttribute("fl");//论坛用户之间的回复信息
ForumOne f=(ForumOne)request.getAttribute("fone");//论坛信息
User u=(User)request.getAttribute("u");//论坛楼主信息
int ri=1;
if(f==null){
	f=new ForumOne();
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
<link href="sample_lihu/css/style.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" href="sample_lihu/css/pullToRefresh.css"/>
</head>
<body>
	<div id="wrap">
		<div id="header">
			<div class="topArea clearfix">
				<h1>全国论坛讨论社区</h1>
				<div class="prev">
					<a href="#" onclick="javascript:history.back(-1);"><img src="sample_lihu/images/prev.png" alt="后退" /></a>
				</div>
				<div class="topTool">
					<span><a href="commentwrite.html">回复楼主</a></span>
				</div>
			</div>
		</div><!--header-->
		<div id="container">
			<div class="forumReview">
				<div class="theme">
					<div class="user clearfix">
						<div class="leftArea"><img src="sample_lihu/images/testuser_ico.gif" alt="sample" /></div>
						<div class="rightArea">
							<p class="name clearfix"><%=u.getUsername() %><span class="userTag"><img src="sample_lihu/images/user_ico.gif" alt="楼主" />楼主</span></p>
							<p class="time"><%=f.getTime() %></p>
						</div>
					</div>
					<div class="title"><%=f.getTitle() %></div>
					<div class="detail"><%=f.getContent() %></div>
					<div class="info clearfix">
						<div class="leftArea">
							<span><%=f.getArea()==null?"":f.getArea() %></span>
						</div>
						<div class="rightArea">
							<span><img src="sample_lihu/images/comment_ico.gif" alt="留言" />&nbsp;<%=f.getfHits() %></span>
							<span><img src="sample_lihu/images/favor2_ico.gif" alt="关注" />&nbsp;<%=f.getFollectnum()%></span>
						</div>
					</div>
					<div class="imgList clearfix">
					
						<p><a href="#"><img src="sample_lihu/images/listBanner.jpg" alt="sample" /></a></p>
						<p><a href="#"><img src="sample_lihu/images/listBanner.jpg" alt="sample" /></a></p>
						<p><a href="#"><img src="sample_lihu/images/listBanner.jpg" alt="sample" /></a></p>
					</div>
				</div>
				<div class="reviewList">
					<h2 class="tit2"><img src="sample_lihu/images/allreview_tit.gif" alt="全部回复" /> <span><a href="commentwrite.html"><img src="sample_lihu/images/ico02.gif" alt="排列" />正序排列</a></span></h2>
					<div id="wrapper">
						<ul>
						
						<%
						for(int i=0;i<fs.size();i++){
							
						
						%>
						
							<li class="list">
								<ul>
									<li class="comment">
										<div class="inner clearfix">
											<div class="leftArea"><img src="sample_lihu/images/testuser_ico.gif" alt="sampleuser" /></div>
											<div class="rightArea">
												<p class="name"><%=us.get(i).getUsername() %><span class="userTag"><%=i+1 %>楼</span></p>
												<p class="detail"><%=fs.get(i).getReply() %></p>
												<p class="time"><%=fs.get(i).getTime() %>
												<span class="button">
												<a href="commentwrite.html">
												
												<img src="sample_lihu/images/ico01.png" alt="留言" />
												
												</a>
												</span>
												</p>
											</div>
										</div>
									</li>
									
									
								<%
								if(fl.get(i)!=null){
									//获取回复信息
									List<ForumThree> fa=(List)fl.get(i);
									for(int i1=0;i1<fa.size();i1++){
										
									
								%>	
									
									<li class="reply">
										<div class="inner clearfix">
											<div class="leftArea"><img src="sample_lihu/images/testuser_ico.gif" alt="sampleuser" /></div>
											<div class="rightArea">
												<p class="name"><img src="sample_lihu/images/ico03.png" alt="reply" /><%=us.get(i).getUsername() %></p>
												<p class="detail"><%=fa.get(i1).getReply() %></p>
												<p class="time"><%=fa.get(i1).getTime() %></p>
											</div>
										</div>
									</li>
									<%
									}
								}
									%>
								</ul>
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
		document.getElementById("wrapper").querySelector(".pullDownLabel").innerHTML="<img src='css/ok.png'/>刷新成功";																					 
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
			li.setAttribute("class", "list"); 
			li.innerHTML='<ul><li class="comment"><div class="inner clearfix"><div class="leftArea"><img src="sample_lihu/images/testuser_ico.gif" alt="sampleuser" /></div><div class="rightArea"><p class="name">家长之友aaa <span class="userTag">1楼</span></p><p class="detail">第一题是这样的：“你家有藏书吗？有多少册”第一题是这样的：“你家有藏书吗？有多少册”第一题是这样的：“你家有藏书吗？有多少册”第一题是这样的：“你家有藏书吗？有多少册”</p><p class="time">2016-1-15 09:54 <span class="button"><a href="commentwrite.html"><img src="sample_lihu/images/ico01.png" alt="留言" /></a></span></p></div></div></li></ul>';
			el.appendChild(li, el.childNodes[0]);
		}
		wrapper.refresh();/****remember to refresh after action completed！！！   ---id.refresh(); --- ****/
	},2000);	
}
</script>
</body>
</html>
