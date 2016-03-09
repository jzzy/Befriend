<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    <%@page import="java.util.*"%>
    <%@page import="com.befriend.util.*"%>
<%@page import="com.befriend.entity.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no"  />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title></title>
<script type="text/javascript" src="<%=request.getContextPath() %>/sample_lihu/js/jquery-1.10.2.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/sample_lihu/js/common.js"></script>
<link href="<%=request.getContextPath() %>/sample_lihu/css/style.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" href="<%=request.getContextPath() %>/sample_lihu/css/pullToRefresh.css"/>
</head>
<body>
	<div id="container">
		<div class="forum">
			<div id="wrapper">
				<ul>
					<li>
						<div class="user clearfix">
							<p class="leftArea"><img src="<%=request.getContextPath() %>/sample_lihu/images/testuser_ico.gif" alt="sample" /></p>
							<p class="rightArea">
								<span>家长之友aaa</span>
								<span>2016-01-15 09:54</span>
							</p>
						</div>
						<div class="title"><a href="forumreview.html" target="_parent">这里是标题</a></div>
						<div class="detail"><a href="forumreview.html" target="_parent">第一题是这样的第一题是这样的第一题是这样的第一题是这样的</a></div>
						<div class="imgList clearfix">
							<p><a href="forumreview.html" target="_parent"><img src="<%=request.getContextPath() %>/sample_lihu/images/listBanner.jpg" alt="sample" /></a></p>
							<p><a href="forumreview.html" target="_parent"><img src="<%=request.getContextPath() %>/sample_lihu/images/listBanner.jpg" alt="sample" /></a></p>
							<p><a href="forumreview.html" target="_parent"><img src="<%=request.getContextPath() %>/sample_lihu/images/listBanner.jpg" alt="sample" /></a></p>
						</div>
						<div class="info">
							<span><img src="<%=request.getContextPath() %>/sample_lihu/images/comment_ico.gif" alt="留言" />&nbsp;520</span>
							<span><img src="<%=request.getContextPath() %>/sample_lihu/images/favor2_ico.gif" alt="关注" />&nbsp;120</span>
						</div>
					</li>
					<li>
						<div class="user clearfix">
							<p class="leftArea"><img src="<%=request.getContextPath() %>/sample_lihu/images/testuser_ico.gif" alt="sample" /></p>
							<p class="rightArea">
								<span>家长之友aaa</span>
								<span>2016-01-15 09:54</span>
							</p>
						</div>
						<div class="title"><a href="forumreview.html" target="_parent">这里是标题</a></div>
						<div class="detail"><a href="forumreview.html" target="_parent">第一题是这样的第一题是这样的第一题是这样的第一题是这样的</a></div>
						<div class="imgList clearfix">
							<p><a href="forumreview.html" target="_parent"><img src="<%=request.getContextPath() %>/sample_lihu/images/listBanner.jpg" alt="sample" /></a></p>
							<p><a href="forumreview.html" target="_parent"><img src="<%=request.getContextPath() %>/sample_lihu/images/listBanner.jpg" alt="sample" /></a></p>
							<p><a href="forumreview.html" target="_parent"><img src="<%=request.getContextPath() %>/sample_lihu/images/listBanner.jpg" alt="sample" /></a></p>
						</div>
						<div class="info">
							<span><img src="<%=request.getContextPath() %>/sample_lihu/images/comment_ico.gif" alt="留言" />&nbsp;520</span>
							<span><img src="<%=request.getContextPath() %>/sample_lihu/images/favor2_ico.gif" alt="关注" />&nbsp;120</span>
						</div>
					</li>
				</ul>
			</div>
		</div>
	</div>
<script src="<%=request.getContextPath() %>/sample_lihu/js/iscroll.js"></script>
<script src="<%=request.getContextPath() %>/sample_lihu/js/pullToRefresh.js"></script>
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
		document.getElementById("wrapper").querySelector(".pullDownLabel").innerHTML="<img src='images/ok.png'/>刷新成功";																					 
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
			li.innerHTML='<div class="user clearfix"><p class="leftArea"><img src="<%=request.getContextPath() %>/sample_lihu/images/testuser_ico.gif" alt="sample" /></p><p class="rightArea"><span>家长之友aaa</span><span>2016-01-15 09:54</span></p></div><div class="title"><a href="forumreview.html" target="_parent">这里是标题</a></div><div class="detail"><a href="forumreview.html" target="_parent">第一题是这样的第一题是这样的第一题是这样的第一题是这样的</a></div><div class="imgList clearfix"><p><a href="forumreview.html" target="_parent"><img src="<%=request.getContextPath() %>/sample_lihu/images/listBanner.jpg" alt="sample" /></a></p><p><a href="forumreview.html" target="_parent"><img src="<%=request.getContextPath() %>/sample_lihu/images/listBanner.jpg" alt="sample" /></a></p><p><a href="forumreview.html" target="_parent"><img src="<%=request.getContextPath() %>/sample_lihu/images/listBanner.jpg" alt="sample" /></a></p></div><div class="info"><span><img src="<%=request.getContextPath() %>/sample_lihu/images/comment_ico.gif" alt="留言" />&nbsp;520</span><span><img src="<%=request.getContextPath() %>/sample_lihu/images/favor2_ico.gif" alt="关注" />&nbsp;120</span></div>';
			el.appendChild(li, el.childNodes[0]);
		}
		wrapper.refresh();/****remember to refresh after action completed！！！   ---id.refresh(); --- ****/
	},2000);	
}
</script>
</body>
</html>
