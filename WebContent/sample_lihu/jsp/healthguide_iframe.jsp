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
<link href="<%=request.getContextPath() %>/sample_lihu/css/style.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" href="<%=request.getContextPath() %>/sample_lihu/css/pullToRefresh.css"/>
<script type="text/javascript" src="<%=request.getContextPath() %>/sample_lihu/js/jquery-1.10.2.min.js"></script>

<script src="<%=request.getContextPath() %>/sample_lihu/js/iscroll.js"></script>
<script src="<%=request.getContextPath() %>/sample_lihu/js/pullToRefresh.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/sample_lihu/js/common.js"></script>

</head>
<body>

	<div id="container">
	 <%
	


		//获取总的页数
		int a=(Integer)request.getAttribute("a");
		//获取第几页
		int currentPage=(Integer)request.getAttribute("currentPage");
		//获取新闻分类
		String type=(String)request.getAttribute("type");
		//获取新闻分类代号
		int tp=(Integer)request.getAttribute("tp");
		//获取新闻
		List<News> nl=(List)request.getAttribute("nl");
		String url="";
		if(request.getAttribute("path")==null){
		
		url="/Befriend/webNewtypeToJson?tp="+tp;	
		}else{
			 url="/Befriend/"+request.getAttribute("path")+"?tp="+tp;	
			}
		

		
   String text=null;
   String pth=null;
  
   
   if(type.equals("升学指南")){
	   pth="9.gif";
	   text="";
   }
   if(type.equals("兴趣特长")){
	   pth="10.gif";
	   text="如何发现孩子的兴趣爱好及特长？在这里会给你答案，留心观察孩子的才能，帮助众多家长选择孩子的最佳成才之路。";
   }
   if(type.equals("教子经验")){
	   pth="5.gif";
	   text="教子有方，如何正确的教育孩子？在这里教育专家专门为年轻的家长做了最实用、最科学的教子经验总结。";
   }
   if(type.equals("成长路上")){
	   pth="2.gif";
	   text="重视家庭教育，关注身心健康，陪伴孩子快乐成长，正确赞赏和激励促使孩子进步，分享育孩子成长相关的宝贵经验。";
   }
   if(type.equals("出国留学")){
	   pth="3.gif";
	   text="整理众多有关留学咨询的问题，共同分析留学方案的具体问题、就读专业等，分享国际院校海外深造的最佳捷径。";
   }
   if(type.equals("名人教子")){
	   pth="6.gif";
	   text="汇集数位顶尖名人的教子启示录，分享教育经验，启发父母的正确教育观念。";
   }
   if(type.equals("健康导航")){
	   pth="4.gif";
	   text="提供日常有助孩子最权威、最实用的保健知识，共同关注家庭健康的生活方式。";
   }
   if(type.equals("轻松驿站")){
	   pth="7.gif";
	   text="这里主要有一些好玩的奇闻趣事、人生感悟，更有幽默风趣的故事小品、家庭幽默与无忌童言。";
   }
   if(type.equals("zr")){
	   pth="8.gif";
	   text="这里主要有一些好玩的奇闻趣事、人生感悟，更有幽默风趣的故事小品、家庭幽默与无忌童言。";
   }
   if(type.equals("bd")){
	   pth="1.gif";
	   text="这里主要有一些好玩的奇闻趣事、人生感悟，更有幽默风趣的故事小品、家庭幽默与无忌童言。";
   }
   if(pth==null){
	   pth="logod.png";
   }
   
   %>
			
		<div class="mainList">
			<div id="wrapper">
					<ul>
					 <%
						for(int i=0;i<nl.size();i++){
						News n=nl.get(i);
						if(i==0){
						%>
						
							<li class="first">
							
						<p class="title"><img src="sample_lihu/images/hot_ico.gif" alt="hot" />
						<a target="_parent" style="color:#666;font-size: 18px;font-weight:bold;" href="webNewsId?id=<%=n.getId()%>" ><%=n.getTitle() %></a></p>
						<p class="big_img"><a target="_parent" href="webNewsId?id=<%=n.getId()%>"><img src="<%="http://182.92.100.235/Befriend/"+n.getImgmax() %>" alt="bigImg" /></a></p>
						<p class="info">
							<span><img src="sample_lihu/images/comment_ico.gif" alt="留言" />&nbsp;<%=n.getReviews() %></span>
								<span><img src="sample_lihu/images/favor_ico.gif" alt="关注" />&nbsp;<%=n.getCollectnum() %></span>
						</p>
						
					</li>
						
						<%
						continue;
						}
						%>
						
						
						
			
					<li>
						<div class="infoArea">
							<p class="title"><img  src="sample_lihu/images/hot_ico.gif" alt="hot" />
							<a target="_parent" style="color:#666;font-size: 18px;font-weight:bold;" href="webNewsId?id=<%=n.getId()%>" ><%=n.getTitle() %></a></p>
							<p class="info">
								<span><img src="sample_lihu/images/comment_ico.gif" alt="留言" />&nbsp;<%=n.getReviews() %></span>
								<span><img src="sample_lihu/images/favor_ico.gif" alt="关注" />&nbsp;<%=n.getCollectnum() %></span>
							</p>
						</div>
						<div class="imgArea">
							<p class="big_img"><img  src="<%="http://182.92.100.235/Befriend/"+n.getImg() %>" /></p>
						</div>
					</li>
					
					<%
							}
					%>
					</ul>
				</div>
		</div>
	</div>
<div id=divc style="display: none;">2</div>

<script type="text/javascript">

window.onload=function(){
	refresher.init({
		id:"wrapper",//<------------------------------------------------------------------------------------┐
		pullDownAction:Refresh,                                                            
		pullUpAction:Load 																			
		},3000);	
	var el, li, i;																		
	el =document.querySelector("#wrapper ul");					
	//这里写你的刷新代码				
	document.getElementById("wrapper").querySelector(".pullDownIcon").style.display="none";		
	document.getElementById("wrapper").querySelector(".pullDownLabel").innerHTML="<img src='sample_lihu/images/ok.png'/>刷新成功";																					 
	setTimeout(function () {
		wrapper.refresh();
		//document.getElementById("wrapper").querySelector(".pullDownLabel").innerHTML="";								
		},10000);//模拟qq下拉刷新显示成功效果
	/****remember to refresh after  action completed！ ---yourId.refresh(); ----| ****/

}


function Refresh() {																
	setTimeout(function () {	// <-- Simulate network congestion, remove setTimeout from production!
		var el, li, i;																		
		el =document.querySelector("#wrapper ul");					
		//这里写你的刷新代码				
		document.getElementById("wrapper").querySelector(".pullDownIcon").style.display="none";		
		document.getElementById("wrapper").querySelector(".pullDownLabel").innerHTML="<img src='sample_lihu/images/ok.png'/>刷新成功";																					 
		setTimeout(function () {
			wrapper.refresh();
			//document.getElementById("wrapper").querySelector(".pullDownLabel").innerHTML="";								
			},1000);//模拟qq下拉刷新显示成功效果
		/****remember to refresh after  action completed！ ---yourId.refresh(); ----| ****/
	}, 1000);
}
function Load() {
	setTimeout(function () {// <-- Simulate network congestion, remove setTimeout from production!
	//	alert(1);
		$.ajax({
					dataType: "json",
				
					url:"<%=url%>"+"&currentPage="+$("#divc").html(),		
								
					async:false,
					success: function (data) {     
						var val = data["nl"];//获取json中的 key
					
						var el, li, i;
						el =document.querySelector("#wrapper ul");
						for (i=0; i<val.length; i++) {
							li = document.createElement('li');
							li.innerHTML='<div class="infoArea"><p class="title"><img src="sample_lihu/images/hot_ico.gif" alt="hot" /><a style="color:#666;font-size: 18px;font-weight:bold;" href=<%=request.getContextPath()%>/webNewsId?id='+val[i].id+' target="_parent">'+val[i].title+'</a></p><p class="info"><span><img src="sample_lihu/images/comment_ico.gif" alt="留言" />&nbsp;520</span><span><img src="sample_lihu/images/favor_ico.gif" alt="关注" />&nbsp;120</span></p></div><div class="imgArea"><p class="big_img"><a href=<%=request.getContextPath()%>/webNewsId?id='+val[i].id+' target="_parent"><img src="http://182.92.100.235/Befriend/'+val[i].img+'" alt="mediumImg" /></a></p></div>';
							el.appendChild(li, el.childNodes[0]);
						}
						wrapper.refresh();/****remember to refresh after action completed！！！   ---id.refresh(); --- ****/
						
					
					
						}
		
		
					
					
		})
		
		
		
		

	});	
}
</script>
</body>
</html>
