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
<script type="text/javascript" src="<%=request.getContextPath() %>/SimulationApp/lihu/js/jquery.js"></script>
<link href="<%=request.getContextPath() %>/SimulationApp/lihu/css/style.css" rel="stylesheet" type="text/css" />
</head>
<body>
<%
List<EduServices> edusl=(List)request.getAttribute("edusl")==null?new ArrayList<EduServices>():(List)request.getAttribute("edusl"); 
List<News> nl=(List)request.getAttribute("nl")==null?new ArrayList<News>():(List)request.getAttribute("nl"); 
%>
	<div class="cont">
		<div id="header" class="myfavorite">
			<div class="topArea clearfix">
				<div class="prev">
					<a href="#" onclick="javascript:history.back(-1);"></a>
				</div>
				<h1>收藏列表</h1>
				<div class="topTool">
					<span id="favorEdit">编辑</span>
				</div>
			</div>
		</div><!--header-->
		
		<div id="container">
			<div class="myFavor">
				<h2 class="clearfix">
					<div class="myFavor1"><span>资讯</span></div>
					<div class="myFavor2"><span class="on">机构</span></div>
				</h2>
				<ul class="localInfo" id="myFavor1">
				<%
				for(int i=0;i<nl.size();i++){
					if(nl.get(i)==null){
						continue;
					}
				%>
				
					<li>
						<div class="checkArea"><label for="a1"><img src="<%=request.getContextPath() %>/SimulationApp/lihu/images/check_off.png" alt="swich" /></label><input type="checkbox" id="a1" name="check"/></div>
							<a href="webNewsId?id=<%=nl.get(i).getId()%>"><p class="info"><%=nl.get(i).getTitle() %></p>
						<p class="time"><%=nl.get(i).getTime()%></p>
						</a>
						<p class="ratePoint"><img src="<%=request.getContextPath() %>/SimulationApp/lihu/images/xing.png" alt="rateicon" /><%=nl.get(i).getHits() %></p>
					</li>
					
				<%
				}
				%>	
				</ul>
				<ul class="listPack" id="myFavor2">
				<%
				for(int i=0;i<edusl.size();i++){
				%>
					<li class="list">
						<div class="checkArea"><label for="b1"><img src="<%=request.getContextPath() %>/SimulationApp/lihu/images/check_off.png" alt="swich" /></label><input type="checkbox" id="b1" name="check"/></div>
						<a href="detail.html">
							<div class="imgArea"><img src="<%=request.getContextPath() %>/SimulationApp/lihu/images/xw_15.png" alt="" /></div>
							<div class="infoArea">
								<h3><%=edusl.get(i).getName() %></h3>
								<p class="rate clearfix"><span class="star_r"></span><span class="star_r"></span><span class="star_r"></span><span class="star_r"></span><span class="star_r"></span></p>
								<div class="clearfix"><strong><%=edusl.get(i).getCounty() %></strong><span><%=edusl.get(i).getClassSecond() %></span></div>
							</div>
							<div class="distance">8000m</div>
						</a>
					</li>
					<%
				}
					%>
				
				</ul>
			</div>
		</div><!--container-->
		
		<div id="footer" class="myfavorite">
			<div class="favorDel">
				<div class="leftArea">
					<span id="checkAll">全选</span>
				</div>
				<div class="rightArea">
					<span>删除</span>
				</div>
			</div>
		</div><!--footer-->
	</div><!--cont-->

<script type="text/javascript">

$(".checkArea").click(function(){
	var favorCheck = $(this).children("input").attr("checked");
	if( favorCheck == false ){
		$(this).children("label").find("img").attr("src","<%=request.getContextPath() %>/SimulationApp/lihu/images/check_off.png")
	}else if( favorCheck == true ){
		$(this).children("label").find("img").attr("src","<%=request.getContextPath() %>/SimulationApp/lihu/images/check_on.png")
	}
});
$("#favorEdit").click(function(){
	var dis = $(".checkArea").css("display");
	if( dis == "none"){
		$(".checkArea").show();
		$("#footer").show();
		$("#container .myFavor .localInfo li").css("padding-left","40px");
		$("#container .myFavor .listPack .list a").css("padding-left","40px");
		$("#container").css("padding-bottom","38px");
		$(this).text("完成");
	};
	if( dis == "block"){
		$(".checkArea").hide();
		$("#footer").hide();
		$("#container .myFavor .localInfo li").css("padding-left","10px");
		$("#container .myFavor .listPack .list a").css("padding-left","10px")
		$("#container").css("padding-bottom","0");
		$(this).text("编辑");
	};	
});
$(".myFavor1").click(function(){
	$("#myFavor1").css("display","block");
	$("#myFavor2").css("display","none");
	$(this).find("span").addClass("on");
	$(".myFavor2 span").removeClass("on");
	$(".checkArea").hide();
	$("#footer").hide();
	$("#container .myFavor .localInfo li").css("padding-left","10px");
	$("#favorEdit").text("编辑");
	$("#myFavor2 .checkArea input").attr("checked",false)
	$("#myFavor2 .checkArea label img").attr("src","<%=request.getContextPath() %>/SimulationApp/lihu/images/check_off.png");  
});
$(".myFavor2").click(function(){
	$("#myFavor1").css("display","none");
	$("#myFavor2").css("display","block");
	$(this).find("span").addClass("on");
	$(".myFavor1 span").removeClass("on");
	$(".checkArea").hide();
	$("#footer").hide();
	$("#container .myFavor .listPack .list a").css("padding-left","10px");
	$("#favorEdit").text("编辑");
	$("#myFavor1 .checkArea input").attr("checked",false)
	$("#myFavor1 .checkArea label img").attr("src","<%=request.getContextPath() %>/SimulationApp/lihu/images/check_off.png");  
});

$("#checkAll").click(function(){
	if($(".myFavor1 span").hasClass("on")){
		/*$("#myFavor1 .checkArea input").each(function(){        
			if($(this).attr("checked")){                  
				$(this).attr("checked", false).siblings("label").find("img").attr("src","<%=request.getContextPath() %>/SimulationApp/lihu/images/check_off.png");    
			}   else {                  
				$(this).attr("checked", "checked").siblings("label").find("img").attr("src","<%=request.getContextPath() %>/SimulationApp/lihu/images/check_on.png");                      
			}      
			
		})*/
		$("#myFavor1 .checkArea input").attr("checked", true).siblings("label").find("img").attr("src","<%=request.getContextPath() %>/SimulationApp/lihu/images/check_on.png");
	}else if($(".myFavor2 span").hasClass("on")){
		$("#myFavor2 .checkArea input").attr("checked", true).siblings("label").find("img").attr("src","<%=request.getContextPath() %>/SimulationApp/lihu/images/check_on.png");
	}
});

</script>
</body>
</html>

