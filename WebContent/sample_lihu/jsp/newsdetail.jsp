<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    <%@page import="java.util.*"%>
    <%@page import="com.befriend.util.*"%>
<%@page import="com.befriend.entity.*"%>
<%
//获取新闻
News n=(News)request.getAttribute("n");
//获取品论新闻的用户信息
List<User> ul=(List)request.getAttribute("ul");
//获取新闻品论
List<Review> rl=(List)request.getAttribute("rl");
if(n==null)
{
	n=new News();
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
</head>
<body>
	<div id="wrap">
		<div id="header">
			<div class="topArea clearfix">
				<h1>新闻详情</h1>
				<div class="prev">
					<a href="javascript:history.back(-1);"><img src="sample_lihu/images/prev.png" alt="后退" /></a>
				</div>
			</div>
		</div><!--header-->
		<div id="container">
			<div class="newsDetail">
				<h2><%=n.getTitle() %></h2>
				<p class="info">
					
					 <%
       if(n.getArea()!=null&&n.getAreas()!=null){
       %>
       
      <span class="name"><%=n.getArea() +" "+n.getAreas()%></span>
		<span class="time"><%=n.getTime() %></span>
		<% 
       }else if(n.getTypes()!=null){
		%>
		<span class="name"><%=n.getType() +" "+n.getTypes()%></span>
		<span class="time"><%=n.getTime() %></span>
		<%
		}else if(n.getType()!=null){
       %>
     <span class="name"><%=n.getType()%></span>
     <span class="time"><%=n.getTime() %></span>
       <%
       }else{
       %>
     	<span class="time"><%=n.getTime() %></span>
       <%
       }
       %>
				</p>
				<p class="bigImg"><img src="<%=request.getContextPath()+n.getImgmax() %>" alt="sample" /></p>
				<p class="detail"><%=n.getContent() %></p>
			</div>
			<div class="hotComment">
				<h2 class="tit2"><img src="sample_lihu/images/hotcomments_tit.gif" alt="热门评论" /></h2>
				<ul>
				    <% 
   	if(rl!=null&&ul!=null){
   	for(int i=0;i<rl.size();i++){
   		if(ul.get(i)!=null&&rl.get(i)!=null){
   %>
					<li>
						<div class="leftArea"><img src="<%=request.getContextPath()+(ul.get(i).getImg()==null?"/SimulationApp/images/logod.png":ul.get(i).getImg() )%>" alt="sampleuser" /></div>
						<div class="rightArea">
							<p class="name"><%=ul.get(i).getUsername() %></p>
							<p class="time"><%=rl.get(i).getTime() %></p>
							<p class="info"><%=rl.get(i).getReview() %></p>
						</div>
					</li>
					<%
   		}
   		}
   	}
				    
					%>
					
				</ul>
			</div>
			<div style="display: none" class="wonderfulComments mainList">
				<h2 class="tit2"><img src="sample_lihu/images/wonderfulcomments_tit.gif" alt="精彩推荐" /></h2>
				<ul>
					<li>
						<div class="infoArea">
							<p class="title"><img src="sample_lihu/images/hot_ico.gif" alt="hot" /><a href="newsdetail.html">一张班主任给家长的试卷，震撼心灵！</a></p>
							<p class="info">
								<span><img src="sample_lihu/images/comment_ico.gif" alt="留言" />&nbsp;520</span>
								<span><img src="sample_lihu/images/favor_ico.gif" alt="关注" />&nbsp;120</span>
							</p>
						</div>
						<div class="imgArea">
							<p class="big_img"><a href="newsdetail.html"><img src="sample_lihu/images/listBanner.jpg" alt="mediumImg" /></a></p>
						</div>
					</li>
					<li>
						<div class="infoArea">
							<p class="title"><img src="sample_lihu/images/hot_ico.gif" alt="hot" /><a href="newsdetail.html">一张班主任给家长的试卷，震撼心灵！</a></p>
							<p class="info">
								<span><img src="sample_lihu/images/comment_ico.gif" alt="留言" />&nbsp;520</span>
								<span><img src="sample_lihu/images/favor_ico.gif" alt="关注" />&nbsp;120</span>
							</p>
						</div>
						<div class="imgArea">
							<p class="big_img"><a href="newsdetail.html"><img src="sample_lihu/images/listBanner.jpg" alt="mediumImg" /></a></p>
						</div>
					</li>
				</ul>
			</div>
		</div><!--container-->
		<div id="footer">
			<div class="newsDetail_ft clearfix">
				<div class="leftArea">
					<a onclick="return ck();" ><img src="sample_lihu/images/writecom_btn.gif" alt="说说你的看法" /></a>
				</div>
				<div class="rightArea">
					<a href="webCsave?newsid=<%=n.getId()%>"><img src="sample_lihu/images/favor_ico_red.gif" alt="红心" /></a>
					<a  class="share"><img src="sample_lihu/images/share_ico.gif" alt="分享" /></a>
				</div>
			</div>
		</div><!--footer-->
		<div id="bg"></div>
		<div id="sharing">
			<div class="inner">
				<ul class="clearfix">
					<li><img src="sample_lihu/images/fx1.png" alt="微信" /><span>微信好友</span></li>
					<li><img src="sample_lihu/images/fx2.png" alt="朋友圈" /><span>朋友圈</span></li>
					<li><img src="sample_lihu/images/fx3.png" alt="QQ好友" /><span>QQ好友</span></li>
					<li><img src="sample_lihu/images/fx4.png" alt="QQ空间" /><span>QQ空间</span></li>
					<li><img src="sample_lihu/images/fx5.png" alt="微信收藏" /><span>微信收藏</span></li>
					<li><img src="sample_lihu/images/fx6.png" alt="QQ收藏" /><span>QQ收藏</span></li>
				</ul>
			</div>
			<p class="shareCan">取消分享</p>
		</div><!--sharing-->
	</div><!--wrap-->
	<form action="webRsave" method="post" id="myform" >
		<input type="text" value="<%=n.getId() %>" name="newsid" style="display: none"/>
	<div id="wrapc" style="display: none;">
		<div id="header">
			<div class="topArea clearfix">
				<h1>发表评论</h1>
				<div class="prev">
					<a onclick="kc();"><img src="sample_lihu/images/prev.png" alt="后退" /></a>
				</div>
			</div>
		</div><!--header-->
		<div id="container">
			<div class="forumPost">
				<div class="write">
					<p class="posts commentWrite"><textarea  style="padding-top: 35px;padding-left: -100px;" name="review" id="review1" cols="30" rows="10"></textarea></p>
				</div>
				<div class="btn clearfix">
					<span class="rightArea clearfix">
						<a onclick="kc();">取消</a>
						<a href="#" id="a1">发表</a>
					</span>
				</div>
			</div>
		</div><!--container-->
		<div id="footer"></div><!--footer-->
	</div><!--wrap-->
	</form>
<script type="text/javascript">
var ck=(function(){
	
	$("#wrapc").show();
	$("#wrap").hide();
	
});
var kc=(function(){
	
	$("#wrapc").hide();
	$("#wrap").show();
	
});

$("#a1").click(function(){
	 var title = document.getElementById("review1").value;
		
	  if (title==""||title==null||title.length<=2){
		alert("请正确评论 2字以上！");
		return false;
		}
	$("#myform").submit(); 
	alert("评论成功");
	return true;
	
});

$(".share").click(function(){
	$("#bg").show();
	$("#sharing").show();
});
$(".shareCan").click(function(){
	$("#bg").hide();
	$("#sharing").hide();
})
$("#bg").click(function(){
	$("#bg").hide();
	$("#sharing").hide();
})

</script>
</body>
</html>


