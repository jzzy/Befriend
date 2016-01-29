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
<title>教辅机构</title>
<script type="text/javascript" src="<%=request.getContextPath() %>/SimulationApp/lihu/js/jquery.js"></script>
<%

Object province=request.getAttribute("province");



Object county=request.getAttribute("county");



Object city=request.getAttribute("city");


Object classFirst=request.getAttribute("classFirst");


Object classSecond=request.getAttribute("classSecond");


Object address=request.getAttribute("address");

%>
<script type="text/javascript">
$(window).scroll(function () {
    var scrollTop = $(this).scrollTop();
    var scrollHeight = $(document).height();
    var windowHeight = $(this).height();
    if (scrollTop + windowHeight >=scrollHeight) {
	//alert("加载了"+"/Befriend/getEduWebAjax?address=<%=address==null?"":address%>&classSecond=<%=classSecond==null?"":classSecond%>&classFirst=<%=classFirst==null?"":classSecond%>&city=<%=city==null?"":city%>&county=<%=county==null?"":county%>&province=<%=province==null?"":province%>&currentPage="+$("#divc").html());
	 
	htmlobj=$.ajax({url:"/Befriend/getEduWebAjax?address=<%=address==null?"":address%>&classSecond=<%=classSecond==null?"":classSecond%>&classFirst=<%=classFirst==null?"":classSecond%>&city=<%=city==null?"":city%>&county=<%=county==null?"":county%>&province=<%=province==null?"":province%>&currentPage="+$("#divc").html(),async:false});
	$("#divc").html(parseInt($("#divc").html())+1);
     $("#myDiv").html($("#myDiv").html()+htmlobj.responseText);
		 
  //此处是滚动条到底部时候触发的事件，在这里写要加载的数据，或者是拉动滚动条的操作

//var page = Number($("#redgiftNextPage").attr('currentpage')) + 1;
//redgiftList(page);
//$("#redgiftNextPage").attr('currentpage', page + 1);

    }
});
</script>
<link href="<%=request.getContextPath() %>/SimulationApp/lihu/css/style.css" rel="stylesheet" type="text/css" />
</head>
<body>

<div style="display: none;" id="divc">2</div>
	<div class="cont">
		<div id="header">
			<div class="topArea clearfix">
				<div class="prev">
					<a href="javascript:history.back(-1);"></a>
				</div>
				<h1>教辅机构</h1>
				<div class="topTool">
					<a href="<%=request.getContextPath() %>/SimulationApp/lihu/search.html"><img src="<%=request.getContextPath() %>/SimulationApp/lihu/images/search_ico.png" alt="搜索" /></a>
					<a href="<%=request.getContextPath() %>/SimulationApp/lihu/location.html"><img src="<%=request.getContextPath() %>/SimulationApp/lihu/images/location_ico.png" alt="定位" /></a>
				</div>
			</div>
		</div><!--header-->
		
		<div id="container">
			<ul class="navi clearfix" id="jMenu">
				<li class="district">
					<a   class="fNiv">全部地区&nbsp;<img src="<%=request.getContextPath() %>/SimulationApp/lihu/images/arrow_off.gif" width="20" height="10" alt="" /></a>
	
				
					<ul>
	
						<li><a href="getEduWeb?county=东城区">东城区</a></li>
						<li><a href="getEduWeb?county=西城区">西城区</a></li>
						<li><a href="getEduWeb?county=朝阳区">朝阳区</li>
						<li><a href="getEduWeb?county=丰台区">丰台区</a></li>
						<li><a href="getEduWeb?county=石景山区">石景山区</a></li>
						<li><a href="getEduWeb?county=海淀区">海淀区</a></li>
						<li><a href="getEduWeb?county=门头沟区">门头沟区</a></li>
	
 
</form>
						
					</ul>
				</li>
				<li class="edutrain">
					<a class="fNiv">教育培训&nbsp;<img src="<%=request.getContextPath() %>/SimulationApp/lihu/images/arrow_off.gif" width="20" height="10" alt="" /></a>
					<ul>
						<li><a href="getEduWeb?classSecond=外语">外语</a></li>
						<li><a href="getEduWeb?classSecond=音乐">音乐</a></li>
						<li><a href="getEduWeb?classSecond=幼儿教育">幼儿教育</a></li>
						<li><a href="getEduWeb?classSecond=升学指导">升学指导</a></li>
						<li><a href="getEduWeb?classSecond=教育院校">教育院校</a></li>
						<li><a href="getEduWeb?classSecond=舞蹈">舞蹈</a></li>
						<li><a href="getEduWeb?classSecond=学校">学校</a></li>
						<li><a href="getEduWeb?classSecond=驾校">驾校</a></li>
						<li><a href="getEduWeb?classSecond=兴趣生活">兴趣生活</a></li>
					</ul>
				</li>
				<li class="sort fNiv">
					<a  class="fNiv">智能排序&nbsp;<img src="<%=request.getContextPath() %>/SimulationApp/lihu/images/arrow_off.gif" width="20" height="10" alt="" /></a>
					<ul>
						<li><a href="#">离我最近的商户</a></li>
						<li><a href="#">人气最高的商户</a></li>
						<li><a href="#">评价醉好的商户</a></li>
						<li><a href="#">人均最低的商户</a></li>
						<li><a href="#">人均最高的商户</a></li>
					</ul>
				</li>
			</ul>
			<div class="listPack">
				<div id="bg">bg</div>
				<ul>
					<%
			List<EduServices> lEduServices=(List)(request.getAttribute("EduServices")==null?new ArrayList<EduServices>():request.getAttribute("EduServices")); 			
			for(int i=0;i<lEduServices.size();i++){
				float f=lEduServices.get(i).getStar();
				
			%>
				<li class="list">
					<a href="getWebCommments?merchantId=<%=lEduServices.get(i).getMerchantId()%>">
						<div class="imgArea"><img src="<%="http://182.92.100.235/"+lEduServices.get(i).getPicture() %>" alt="" /></div>
						<div class="infoArea">
							<h3><%=lEduServices.get(i).getName() %></h3>
							<p class="rate clearfix">
							<%
							for(int y=0;y<f;y++){
							%>
							<span class="star_r"></span>
							<%
							}	
							%>
							</p>
							<div class="clearfix"><strong><%=lEduServices.get(i).getCity()%></strong><span><%=lEduServices.get(i).getClassFirst() %></span></div>
						</div>
						<!-- 
						<div class="distance">8000m</div>
						 -->
					</a>
				</li>
			<%
			}
			%>
				
				</ul>
			</div>
			<div id="myDiv"></div>
		</div><!--container-->
		
	</div><!--cont-->

<script type="text/javascript" src="<%=request.getContextPath() %>/SimulationApp/lihu/js/jMenu.jquery.js"></script>
<script type="text/javascript">
  $(document).ready(function(){
    $("#jMenu").jMenu();
  })
</script>
</body>
</html>