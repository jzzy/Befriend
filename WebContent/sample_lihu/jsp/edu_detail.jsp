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
<script type="text/javascript" src="sample_lihu/js/jquery-1.10.2.min.js"></script>
<script type="text/javascript" src="sample_lihu/js/common.js"></script>

<link href="sample_lihu/css/style.css" rel="stylesheet" type="text/css" />
</head>
<body>
	<div id="wrap">
		<div id="header">
			<div class="topArea clearfix">
				<h1>机构详情</h1>
				<div class="prev">
					<a href="javascript:history.back(-1);"><img src="sample_lihu/images/prev.png" alt="后退" /></a>
				</div>
				<div class="topTool">
					<a href="#" class="share"><img src="sample_lihu/images/ico17.gif" alt="分享" /></a>
				</div>
			</div>
		</div><!--header-->
		
		<%
	List<EduComment> educl=(List)request.getAttribute("educl");
	EduServices edus=(EduServices)request.getAttribute("edus");
	%>
		<div id="container">
			<div class="eduDetail">
				<div class="inner">
					<div class="clearfix">
						<div class="imgArea">
							<img src=<%="http://182.92.100.235/"+edus.getPicture() %> alt="" />
						</div>
						<div class="infoArea">
							<p class="name"><%=edus.getName() %></p>
							<p class="rate">
								<%
							for(int g=0;g<edus.getStar();g++){
							%>
								<img src="sample_lihu/images/star_on.png" alt="" />
								
											<%
											if(g==edus.getStar()-1)
							for(int y=0;y<4-g;y++){
							%>
								
								<img src="sample_lihu/images/star_off.png" alt="" />
						<%
							}
							}
						%>
							
							</p>
							<p class="loc"><%=edus.getCity()%> <%=edus.getClassFirst() %></p>
							<p class="point"><span>设施:<%=edus.getEnvScore() %></span><span>环境:<%=edus.getSerScore() %></span><span>服务:<%=edus.getStar() %></span></p>
						</div>
					</div>
					<p class="adress"><a class="clearfix" href="daohang?lng=<%=edus.getLongitude() %>&lat=<%=edus.getLatitude() %>"><%=edus.getAddress() %><img src="sample_lihu/images/ico38.png" alt="" /></a></p>
					<p class="tel"><a class="clearfix" href="tel:<%=edus.getTelephone().split(",")[0] %>"><%=edus.getTelephone().split(",")[0] %><img src="sample_lihu/images/ico38.png" alt="" /></a></p>
				</div>
				<div class="shopInfo">
					<h2><img src="sample_lihu/images/ico35.png" alt="" />商户信息</h2>
					<p class="time">营业时间&nbsp;:&nbsp;尚无营业时间</p>
					<p class="tel">联系电话&nbsp;:&nbsp;<a class="clearfix" href="tel:<%=edus.getTelephone().split(",")[0] %>"><%=edus.getTelephone().split(",")[0] %><a></a></p>
			<p class="shopName">商户名称&nbsp;:&nbsp;<%=edus.getName() %></p>
				</div>
				<div class="review">
					<h2><a href="#"><img src="sample_lihu/images/ico36.png" alt="" />网友点评</a></h2>
					<ul>
						<%
			for(int i=0;i<educl.size();i++){
			%>
						<li>
							<div class="inner clearfix">
								<div class="userImg"><img  src="<%=request.getContextPath()+educl.get(i).getUser().getImg()%>" alt="ico" /></div>
								
								<div class="message">
									<p class="userId"><%=educl.get(i).getUser().getNickname() %>
									<span class="rate">
									<%
							for(int g=0;g<educl.get(i).getScore();g++){
							%>
								<img src="sample_lihu/images/star_on.png" alt="" />
								
											<%
											if(g==educl.get(i).getScore()-1)
							for(int y=0;y<4-g;y++){
							%>
								
								<img src="sample_lihu/images/star_off.png" alt="" />
						<%
							}
							}
						%>
									
									
									
									
									
									
									
									
									
								
									
									</span>
									
									</p>
									<p class="time"><%=educl.get(i).getTime() %>
									</p>
									<p class="words">	<%=educl.get(i).getContent() %>
									</p>
									<ul class="attachImg">
									
									<%
							if(!OpeFunction.isEmpty(educl.get(i).getPictures())){
								
							
							String s = new String(educl.get(i).getPictures());   
					        String a[] = s.split("!#");  
					        for(int y=0;y<a.length;y++){
								// System.out.println("i:"+i+"y:"+y+":"+a[y]);
							
			
							//http://182.92.100.235
							//http://123.56.45.164
							
							if(y<6){
							%>
							<li><img src="<%=a[y] %>" alt="pic" /></li>
							<%
							}
					        }
							}
							%>
							</p>
									<!-- 
									
										<li><img src="sample_lihu/images/sample01.gif" alt="" /></li>
										<li><img src="sample_lihu/images/sample01.gif" alt="" /></li>
										<li><img src="sample_lihu/images/sample01.gif" alt="" /></li>
										 -->
									</li>
								</div>
							</div>
						</li>
						<%
			}
						%>
						
					</ul>
				</div>
				<div class="nearby">
					<h2><img src="sample_lihu/images/ico37.png" alt="" />在附近找</h2>
					<ul class="clearfix">
						<li><a href="GaoDeFujin?type=美食&lng=<%=edus.getLongitude() %>&lat=<%=edus.getLatitude() %>"><img src="sample_lihu/images/ico39.png" alt="美食" />美食</a></li>
						<li><a href="GaoDeFujin?type=娱乐&lng=<%=edus.getLongitude() %>&lat=<%=edus.getLatitude() %>"><img src="sample_lihu/images/ico40.png" alt="娱乐" />娱乐</a></li>
						<li><a href="GaoDeFujin?type=酒店&lng=<%=edus.getLongitude() %>&lat=<%=edus.getLatitude() %>"><img src="sample_lihu/images/ico41.png" alt="酒店" />酒店</a></li>
						<li><a href="GaoDeFujin?type=景点&lng=<%=edus.getLongitude() %>&lat=<%=edus.getLatitude() %>"><img src="sample_lihu/images/ico42.png" alt="景点" />景点</a></li>
						<li><a href="GaoDeFujin?type=购物&lng=<%=edus.getLongitude() %>&lat=<%=edus.getLatitude() %>"><img src="sample_lihu/images/ico43.png" alt="购物" />购物</a></li>
						<li><a href="GaoDeFujin?type=停车&lng=<%=edus.getLongitude() %>&lat=<%=edus.getLatitude() %>"><img src="sample_lihu/images/ico44.png" alt="停车" />停车</a></li>
						
					
		
					</ul>
				</div>
			</div>
		</div><!--container-->
		<div id="footer">
			<div class="newsDetail_ft clearfix">
				<div class="leftArea">
					<a href="<%=request.getContextPath() %>/sample_lihu/edu_commentwrite.html" ><img src="sample_lihu/images/writecom_btn.gif" alt="说说你的看法" /></a>
				</div>
				<div class="rightArea">
					<a href="#" onclick="hre();"><img src="sample_lihu/images/favor_ico_red.gif" alt="红心" /></a>
					<a href="#" class="share"><img src="sample_lihu/images/share_ico.gif" alt="分享" /></a>
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
	
	
<script type="text/javascript">
var hre=(function(){
	// 
	var htmlobj=$.ajax({url:"/Befriend/saveEduAttentionWeb?objectid="+<%=edus.getMerchantId()%>,async:false});
	//alert(htmlobj.responseText=="true");
	if(htmlobj.responseText=="true"){
		alert("收藏成功！");
		
	}else{
		alert("已经收藏过！");
	}	
	
	 
	 
	
	
});
var submit=(function(){
	$("#form1").attr("action","addWebComments").submit(); 
	
});
var wrap=(function(){
	
	$("#wrap").show();
	$("#wrapcomm").hide();
	
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
