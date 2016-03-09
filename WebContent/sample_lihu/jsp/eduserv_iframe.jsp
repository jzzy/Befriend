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
<script type="text/javascript" src="sample_lihu/js/jquery-1.10.2.min.js"></script>
<script type="text/javascript" src="sample_lihu/js/common.js"></script>
<link href="sample_lihu/css/style.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" href="sample_lihu/css/pullToRefresh.css"/>
</head>
<body>
	<div id="container">
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
	</div>
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
		document.getElementById("wrapper").querySelector(".pullDownLabel").innerHTML="<img src='images/ok.png' />刷新成功";																					 
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
			li.innerHTML='<a href="edu_detail.html" target="_parent"><div class="imgArea"><img src="sample_lihu/images/xw_15.png" alt="" /></div><div class="infoArea"><h3>尚东酒吧</h3><p class="rate clearfix"><span class="star_on"></span><span class="star_on"></span><span class="star_off"></span><span class="star_off"></span><span class="star_off"></span></p><div class="clearfix"><strong>海淀区</strong><span>职业技术</span><div class="distance">8000m</div></div></div></a>';
			el.appendChild(li, el.childNodes[0]);
		}
		wrapper.refresh();/****remember to refresh after action completed！！！   ---id.refresh(); --- ****/
	},2000);	
}
</script>
</body>
</html>
