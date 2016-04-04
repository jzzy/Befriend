<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    <%@page import="java.util.*"%>
    <%@page import="com.befriend.util.*"%>
<%@page import="com.befriend.entity.*"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title></title>
<link href="sample_lihu/css/style.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" href="sample_lihu/css/pullToRefresh.css"/>
<script type="text/javascript" src="sample_lihu/js/jquery-1.10.2.min.js"></script>
<script type="text/javascript" src="sample_lihu/js/common.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/sample_lihu/js/jquery.lazyload.min.js"></script>

<script type="text/javascript" charset="utf-8">
      $(function() {
    	 
          $("img").lazyload({ 
          placeholder : "<%=request.getContextPath() %>/sample_lihu/images/listBanner.png",
                effect: "fadeIn",
                threshold : 20000
                 
           });  
      });
</script>
</head>
<body>
	 <%

		//获取新闻分类
		int type=(Integer)request.getAttribute("tp");
		

	 System.out.println("type"+type);
	 
		
   String text=null;
   String pth=null;
   String img="";
  
   
   if(type==1){
	   System.out.println("type1"+type);
		 System.out.println("pth1"+pth);
	   pth="/webNewtype?tp=1";
	   img="9.gif";
	   text="";
   }
   if(type==5){
	   img="10.gif";
	   pth="/webNewtype?tp=5";
	   text="如何发现孩子的兴趣爱好及特长？在这里会给你答案，留心观察孩子的才能，帮助众多家长选择孩子的最佳成才之路。";
   }
   if(type==2){
	   img="5.gif";
	   pth="/webNewtype?tp=2";
	   text="教子有方，如何正确的教育孩子？在这里教育专家专门为年轻的家长做了最实用、最科学的教子经验总结。";
   }
   if(type==3){
	   img="2.gif";
	   pth="/webNewtype?tp=3";
	   text="重视家庭教育，关注身心健康，陪伴孩子快乐成长，正确赞赏和激励促使孩子进步，分享育孩子成长相关的宝贵经验。";
   }
   if(type==4){
	   img="3.gif";
	   pth="/webNewtype?tp=4";
	   text="整理众多有关留学咨询的问题，共同分析留学方案的具体问题、就读专业等，分享国际院校海外深造的最佳捷径。";
   }
   if(type==6){
	   img="6.gif";
	   pth="/webNewtype?tp=6";
	   text="汇集数位顶尖名人的教子启示录，分享教育经验，启发父母的正确教育观念。";
   }
   if(type==7){
	   img="4.gif";
	   pth="/webNewtype?tp=7";
	   text="提供日常有助孩子最权威、最实用的保健知识，共同关注家庭健康的生活方式。";
   }
   if(type==8){
	   img="7.gif";
	   pth="/webNewtype?tp=8";
	   text="这里主要有一些好玩的奇闻趣事、人生感悟，更有幽默风趣的故事小品、家庭幽默与无忌童言。";
   }
   if(type==9){
	   img="8.gif";
	   pth="/webHottest";
	   text="这里主要有一些好玩的奇闻趣事、人生感悟，更有幽默风趣的故事小品、家庭幽默与无忌童言。";
   }
   if(type==10){ img="1.gif";
	   pth="/webHotareaf";
	   text="这里主要有一些好玩的奇闻趣事、人生感悟，更有幽默风趣的故事小品、家庭幽默与无忌童言。";
   }
	 System.out.println("type"+type);
	 System.out.println("pth"+pth);
	 
   if(pth==null){
	   pth="logod.png";
	   return;
   }
   
   %>
	<div id="wrap">
		<div id="header">
			<div class="topArea clearfix">
				<h1><a href="webNewsA10"><img src="sample_lihu/images/logo.gif" alt="家长之友" /></a></h1>
				<div class="relocation">
					<a href="sample_lihu/jsp/relocation.jsp"><%=session.getAttribute("province") %>&nbsp;<img src="sample_lihu/images/location_more.gif" alt="down" /></a>
				</div>
				<div class="topTool">
					<span class="mapLink"><a href="sample_lihu/jsp/sitemap.jsp"><img src="sample_lihu/images/toptoolBtn.gif" alt="菜单" /></a></span>
				</div>
			</div>
			<div class="mainList">
				<h2 class="tit2"><img src="sample_lihu/images/<%=img %>" alt="今日必读" /></h2>
			</div>
		</div><!--header-->
	
		
		
	<div id="container">
	 <%
	


		//获取总的页数
		int a=(Integer)request.getAttribute("a");
		//获取第几页
		int currentPage=(Integer)request.getAttribute("currentPage");
		//获取新闻分类
	//	String type=(String)request.getAttribute("type");
		//获取新闻分类代号
		int tp=(Integer)request.getAttribute("tp");
		//获取新闻
		List<News> nl=request.getAttribute("nl")==null?new ArrayList<News>():(List)request.getAttribute("nl");
		String url="";
		if(request.getAttribute("path")==null){
		
		url="/Befriend/webNewtypeToJson?tp="+tp;	
		}else{
			 url="/Befriend/"+request.getAttribute("path")+"?tp="+tp;	
			}
		System.out.println(url);
		


   
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
						<p class="big_img"><a target="_parent" href="webNewsId?id=<%=n.getId()%>"><img src="<%=request.getContextPath() %>/sample_lihu/images/listBanner.png"  data-original="<%="http://182.92.100.235/Befriend/"+n.getImgmax() %>" alt="bigImg" /></a></p>
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
							<p class="big_img"><img  src="<%=request.getContextPath() %>/sample_lihu/images/listBanner.png"  data-original="<%="http://182.92.100.235/Befriend/"+n.getImg() %>" /></p>
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
	<div id="loding"><img src="<%=request.getContextPath() %>/sample_lihu/images/123.gif" alt="loding_ico" /></div><!--loding-->
		
		
	</div><!--wrap-->
	<script type="text/javascript">
	$(window).scroll(function () {
	    if ($(document).scrollTop() + $(window).height() >= $(document).height()) {
	        $("#loding").slideDown(300).delay(300).slideUp(300);
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
							var te='<div class="infoArea"><p class="title"><img src="sample_lihu/images/hot_ico.gif" alt="hot" /><a style="color:#666;font-size: 18px;font-weight:bold;" href=<%=request.getContextPath()%>/webNewsId?id='+val[i].id+' target="_parent">'+val[i].title+'</a></p><p class="info"><span>';
							te+='<img src="sample_lihu/images/comment_ico.gif" alt="留言" />&nbsp;'+val[i].reviews+'</span><span><img src="sample_lihu/images/favor_ico.gif" alt="关注" />&nbsp;'+val[i].collectnum+'</span></p></div><div class="imgArea"><p class="big_img"><a href=<%=request.getContextPath()%>/webNewsId?id='+val[i].id+' target="_parent"><img src="http://182.92.100.235/Befriend/'+val[i].img+'" alt="mediumImg" /></a></p></div>';
							li.innerHTML=te;
							el.appendChild(li, el.childNodes[0]);
						}
						$("#divc").html(parseInt($("#divc").html())+1);
						wrapper.refresh();/****remember to refresh after action completed！！！   ---id.refresh(); --- ****/
						
					
					
						}
		
		
					
					
		})
		
		
		
		

	    }
	});	
</script>

</body>
</html>
