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
<link rel="stylesheet" href="sample_lihu/css/pullToRefresh.css"/>
</head>
<body>
	<div id="bg">bg</div>
	<div id="wrap">
		<div id="header">
			<div class="topArea clearfix">
				<div class="search_input2">
					<div class="inner">
						<input type="text" id="search2" value="<%=request.getAttribute("value") %>"/><a href="#" class="search_btn2"><img src="sample_lihu/images/ico07.gif" alt="确认搜索" /></a></span>
					</div>
				</div>
				<div class="prev">
					<a href="#" onclick="javascript:history.back(-1);"><img src="sample_lihu/images/prev.png" alt="后退" /></a>
				</div>
			</div>
		</div><!--header-->
		<div id="container">
			<ul class="navi clearfix" id="jMenu">
				<%
				List<Areas> lar=(List)(request.getAttribute("lar")==null?new ArrayList<Areas>():request.getAttribute("lar")); 			
				List<Cities> lcit=(List)(request.getAttribute("lcit")==null?new ArrayList<Cities>():request.getAttribute("lcit")); 			

				%>
				<li class="district">
					<a href="#" class="fNiv">全部地区&nbsp;<img src="sample_lihu/images/arrow_off.png" width="20" height="10" alt="" /></a>
					<ul>
							<%
					if(lar.size()==0){
					%>
					<li><a href="getEduWeb?city=all"><%=session.getAttribute("province") %></a></li>
					<%
					}else{
					%>
					<li><a href="getEduWeb?county=all"><%=session.getAttribute("province") %></a></li>
					<%
					}
					%>
						<%
						for(int i=0;i<lar.size();i++){
							
						
						%>
						<li><a href="getEduWeb?county=<%=lar.get(i).getArea().substring(0, lar.get(i).getArea().length()-1) %>&province=<%=session.getAttribute("province")==null||session.getAttribute("province").equals("null")?"北京":session.getAttribute("province")%>"><%=lar.get(i).getArea() %></a></li>
					
						<%
			
						}
						%>
						
						
							<%
						for(int i=0;i<lcit.size();i++){
							
						
						%>
						<li><a href="getEduWeb?city=<%=lcit.get(i).getCity().substring(0, lcit.get(i).getCity().length()-1) %>&province=<%=session.getAttribute("province")==null||session.getAttribute("province").equals("null")?"北京":session.getAttribute("province")%>"><%=lcit.get(i).getCity() %></a></li>
					
						<%
						}
						%>
					</ul>
				</li>
				<li class="edutrain">
					<a href="#" class="fNiv">教育培训&nbsp;<img src="sample_lihu/images/arrow_off.png" width="20" height="10" alt="" /></a>
					<ul>
							<li><a href="getEduWeb?classSecond=all">不限</a></li>
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
					<a href="#" class="fNiv">智能排序&nbsp;<img src="sample_lihu/images/arrow_off.png" width="20" height="10" alt="" /></a>
					<ul>
						<li><a href="getEduWeb?sortType=1">离我最近的商户</a></li>
						<li><a href="getEduWeb?sortType=2">人气最高的商户</a></li>
						<li><a href="getEduWeb?sortType=5">评价最好的商户</a></li>
						<li><a href="getEduWeb?sortType=3">人均最低的商户</a></li>
						<li><a href="getEduWeb?sortType=4">人均最高的商户</a></li>
					</ul>
				</li>
			</ul>
			<div class="eduServ listPack" id="wrapper">
				<ul>
					<%
			List<EduServices> lEduServices=(List)(request.getAttribute("EduServices")==null?new ArrayList<EduServices>():request.getAttribute("EduServices")); 			
			for(int i=0;i<lEduServices.size();i++){
				float f=lEduServices.get(i).getStar();
				int f1=0;
				
			%>
					<li>
					<a href="getWebCommments?merchantId=<%=lEduServices.get(i).getMerchantId()%>">
							<div class="imgArea"><img src="sample_lihu/images/xw_15.png" alt="" /></div>
							<div class="infoArea">
								<h3><%=lEduServices.get(i).getName() %></h3>
								<p class="rate clearfix">
								
								
								
								
								<%
							for(int y=0;y<f;y++){
								f1++;
							%>
							<span class="star_on"></span>
						
							<%
							}	
							%>
									<%
							for(int y=0;y<5-f1;y++){
							%>
							<span class="star_off"></span>
							
							<%
							}	
							%>	
								
								
								<!-- 
								
								<span class="star_on"></span>
								<span class="star_on"></span>
								<span class="star_off"></span>
								<span class="star_off"></span>
								<span class="star_off"></span>
								 -->
								</p>
								<div class="clearfix"><strong><%=lEduServices.get(i).getCity()%></strong><span><%=lEduServices.get(i).getClassFirst() %></span></div>
						</div>
						
						<div class="distance"><%=lEduServices.get(i).getDistance()%>m</div>
						</a>
					</li>
					<%
			}
					%>
				</ul>
			</div>
		</div><!--container-->
		<div id="footer"></div><!--footer-->
	</div><!--wrap-->
	
<script type="text/javascript" src="sample_lihu/js/jMenu.jquery.js"></script>
<script type="text/javascript">
  $(document).ready(function(){
    $("#jMenu").jMenu();
  })
</script>
<script src="sample_lihu/js/iscroll.js"></script>
<script src="sample_lihu/js/pullToRefresh.js"></script>
<script>
refresher.init({
	id:"wrapper",//<------------------------------------------------------------------------------------┐
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
			li.innerHTML='<a href="#"><div class="imgArea"><img src="sample_lihu/images/xw_15.png" alt="" /></div><div class="infoArea"><h3>尚东酒吧</h3><p class="rate clearfix"><span class="star_on"></span><span class="star_on"></span><span class="star_off"></span><span class="star_off"></span><span class="star_off"></span></p><div class="clearfix"><strong>海淀区</strong><span>职业技术</span><div class="distance">8000m</div></div></div></a>';
			el.appendChild(li, el.childNodes[0]);
		}
		wrapper.refresh();/****remember to refresh after action completed！！！   ---id.refresh(); --- ****/
	},2000);	
}
</script>
</body>
</html>
