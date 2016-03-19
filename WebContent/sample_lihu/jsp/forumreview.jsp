<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    <%@page import="java.util.*"%>
    <%@page import="com.befriend.util.*"%>
<%@page import="com.befriend.entity.*"%>
<% 
List<ForumTwo> fs=(List)request.getAttribute("ftwos");//论坛评论信息
List<User> us=(List)request.getAttribute("us");//论坛评论的用的用户信息
List<List> fu=(List)request.getAttribute("fu");// 论坛用户之间 回复的 用户信息
List<List> fut=(List)request.getAttribute("fut");// 论坛用户之间 被回复 用户信息
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
<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title></title>
<script type="text/javascript" src="sample_lihu/js/jquery-1.10.2.min.js"></script>
<script type="text/javascript" src="sample_lihu/js/common.js"></script>


<link href="sample_lihu/css/style.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" href="sample_lihu/css/pullToRefresh.css"/>
</head>
<body>

	
	
	<div id="wrap" >
		<div id="header">
			<div class="topArea clearfix">
				<h1>全国论坛讨论社区</h1>
				<div class="prev">
					<a href="sample_lihu/jsp/forum2.jsp"><img src="sample_lihu/images/prev.png" alt="后退" /></a>
				</div>
				<div class="topTool">
					<span><a  onclick="wrapcomm();">回复楼主</a></span>
				</div>
			</div>
		</div><!--header-->
		<div id="container">
			<div class="forumReview">
				<div class="theme">
					<div class="user clearfix">
						<div class="leftArea"><img style="width: 60px;height: 60px;" src="<%=request.getContextPath()+u.getImg() %>" alt="sample" /></div>
						<div class="rightArea">
							<p class="name clearfix"><%=u.getNickname() %><span class="userTag"><img src="sample_lihu/images/user_ico.gif" alt="楼主" />楼主</span></p>
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
							<span><img src="sample_lihu/images/comment_ico.gif" alt="留言" />&nbsp;<%=f.getFrs() %></span>
							<span><img src="sample_lihu/images/favor2_ico.gif" alt="关注" />&nbsp;<%=f.getfHits() %></span>
						</div>
					</div>
					<%
					if(f.getImg()!=null){
					%>
					<div class="imgList clearfix">
					
						<p><a ><img src="<%=request.getContextPath()+f.getImg() %>" alt="sample" /></a></p>
					
					
					
					</div>
					<%
					}
					%>
				</div>
			
				<div class="reviewList">
					<h2 class="tit2"><img src="sample_lihu/images/allreview_tit.gif" alt="全部回复" />
					 <span><a onclick="dz();"><img src="sample_lihu/images/ico02.gif" alt="排列" />顺序排列</a></span></h2>
					 
					 
					 <div id="divd" style="display: none;">
					 
					 
					<div id="wrapper">
						<ul>
						
						<%
						for(int i=fs.size()-1;i>=0;i--){
							
						
						%>
							
							<li class="list">
								<ul>
									<li class="comment">
										<div class="inner clearfix">
											<div class="leftArea"><img style="width: 60px;height: 60px;" src="<%=request.getContextPath()+us.get(i).getImg() %>" alt="sampleuser" /></div>
											<div class="rightArea">
												<p class="name"><%=us.get(i).getNickname() %><span class="userTag"><%=fs.size()-i %>楼</span></p>
												<p class="detail"><%=fs.get(i).getReply() %></p>
												<p class="time"><%=fs.get(i).getTime() %>
												<span class="button">
												<a onclick="wrapcommp(<%=us.get(i).getId() %>,<%=fs.get(i).getId() %>);">
												
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
									List<User> lfu=(List)fu.get(i);
									for(int i1=0;i1<fa.size();i1++){
										
									
								%>	
									
									<li class="reply">
										<div class="inner clearfix">
											<div class="leftArea"><img style="width: 60px;height: 60px;" src="<%=request.getContextPath()+lfu.get(i1).getImg() %>" alt="sampleuser" /></div>
											<div class="rightArea">
												<p class="name"><img src="sample_lihu/images/ico03.png" alt="reply" /><%=us.get(i).getNickname() %></p>
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
					
					
						 <div id="divz" >
					<div id="wrapper">
						<ul>
						
						<%
						for(int i=0;i<fs.size();i++){
							
						
						%>
							
							<li class="list">
								<ul>
									<li class="comment">
										<div class="inner clearfix">
											<div class="leftArea"><img style="width: 60px;height: 60px;" src="<%=request.getContextPath()+us.get(i).getImg() %>" alt="sampleuser" /></div>
											<div class="rightArea">
												<p class="name"><%=us.get(i).getNickname() %><span class="userTag"><%=fs.size()-i %>楼</span></p>
												<p class="detail"><%=fs.get(i).getReply() %></p>
												<p class="time"><%=fs.get(i).getTime() %>
												<span class="button">
												<a onclick="wrapcommp(<%=us.get(i).getId() %>,<%=fs.get(i).getId() %>);">
												
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
									List<User> lfu=(List)fu.get(i);
									for(int i1=0;i1<fa.size();i1++){
										
									
								%>	
									
									<li class="reply">
										<div class="inner clearfix">
											<div class="leftArea"><img style="width: 60px;height: 60px;" src="<%=request.getContextPath()+lfu.get(i1).getImg() %>" alt="sampleuser" /></div>
											<div class="rightArea">
												<p class="name"><img src="sample_lihu/images/ico03.png" alt="reply" /><%=us.get(i).getNickname() %></p>
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
			</div>
		</div><!--container-->
		<div id="footer">
		</div><!--footer-->
	</div><!--wrap-->
	
	
	
<div id="wrapcomm"  style="display: none;">
	<form action="" id="form1">
	<!-- 获取论坛的id -->
			<input type="text" name="forumid" value="<%=f.getId() %>" style="display: none"/>
				<input type="text" id="touserid" name="touserid"  style="display: none"/>
				<input type="text" id="forumtwoid" name="forumtwoid" style="display: none"/>
		<div id="header">
			<div class="topArea clearfix">
				<h1>发表评论</h1>
				<div class="prev">
					<a href="#" onclick="wrap();"><img src="sample_lihu/images/prev.png" alt="后退" /></a>
				</div>
			</div>
		</div><!--header-->
		<div id="container">
			<div class="forumPost">
				<div class="write">
					<p class="posts commentWrite" style="padding-top: 35px;padding-left: -100px;">
					<textarea name="reply" id="postCont" cols="30" rows="10">写点什么吧…</textarea>
					
					</p>
				</div>
				<div class="btn clearfix">
					<span class="rightArea clearfix">
						<a onclick="wrap();">取消</a>
						<a id="al" onclick="submit();">发表</a>
						<a id="ah" onclick="submit2();" style="display: none;">发表</a>
					</span>
				</div>
			</div>
		</div><!--container-->
		<div id="footer"></div><!--footer-->
		</form>
	</div><!--wrap-->
	
<script src="sample_lihu/js/iscroll.js"></script>
<script src="sample_lihu/js/pullToRefresh.js"></script>
<script type="text/javascript" >
var dz=(function(){
	
	
	 //调用新闻
    if($("#divd").is(":visible")){
    	$("#divz").show();
    	$("#divd").hide();
    }else{
    	$("#divd").show();
    	$("#divz").hide();
    }
	
});
var wrap=(function(){
	
	$("#wrap").show();
	$("#wrapcomm").hide();
	
});
var wrapcomm=(function(){
	$("#al").show();
	$("#ah").hide();
	$("#wrap").hide();
	$("#wrapcomm").show();
	var textarea2 = $("#postCont");

	textarea2.focusin(function(){
	  // alert(a);
	  if($(this).text() !== "写点什么吧…"){
		return false;
	  }else{
		$(this).text("");
	  }
	});
	textarea2.focusout(function(){
	  if($(this).text() == ""){
		$(this).text("写点什么吧…");
	  }
	});
	
	
});
var wrapcommp=(function(touserid,forumtwoid){
	//alert(name+name1);
	$("#touserid").val(touserid);
	$("#forumtwoid").val(forumtwoid);
	$("#al").hide();
	$("#ah").show();
	$("#wrap").hide();
	$("#wrapcomm").show();
	var textarea2 = $("#postCont");

	textarea2.focusin(function(){
	  // alert(a);
	  if($(this).text() !== "写点什么吧…"){
		return false;
	  }else{
		$(this).text("");
	  }
	});
	textarea2.focusout(function(){
	  if($(this).text() == ""){
		$(this).text("写点什么吧…");
	  }
	});
	
	
});
var submit=(function(){
	//alert($("#postCont").text());
	
	$("#form1").attr("action","webForumtwosaveapp").submit(); 
	
});
var submit2=(function(){
	
	$("#form1").attr("action","webForumthreesappadd").submit(); 
	
});

</script>
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
		document.getElementById("wrapper").querySelector(".pullDownLabel").innerHTML="<img src='sample_lihu/images/ok.png'/>刷新成功";																					 
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
-->
</body>
</html>
