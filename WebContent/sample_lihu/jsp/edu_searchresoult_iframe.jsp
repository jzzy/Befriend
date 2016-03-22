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
<link href="sample_lihu/css/style.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" href="sample_lihu/css/pullToRefresh.css"/>
<script type="text/javascript" src="sample_lihu/js/jquery-1.10.2.min.js"></script>
<script type="text/javascript" src="sample_lihu/js/common.js"></script>

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
					<a target="_parent" href="getWebCommments?merchantId=<%=lEduServices.get(i).getMerchantId()%>">
							<div class="imgArea"><img src="<%="http://182.92.100.235/"+lEduServices.get(i).getPicture() %>" alt="" /></div>
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
		<div id="divc" style="display: none;">2</div>
<script src="sample_lihu/js/iscroll.js"></script>
<script src="sample_lihu/js/pullToRefresh.js"></script>
<script>
refresher.init({
	id:"wrapper",//<------------------------------------------------------------------------------------┐
	pullDownAction:Refresh,                                                            
	pullUpAction:Load 																			
	});		
$(function () {	// <-- Simulate network congestion, remove setTimeout from production!
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
		var el, li, i,str;
		el =document.querySelector("#wrapper ul");
		$.ajax({
			url:"/Befriend/getLikeEduWebToJson?value=<%=request.getAttribute("value")%>&currentPage="+$("#divc").html(),
			 dataType: "json", 
					async:false,
					success: function (data) {     
						var val = data["edl"];//获取json中的 key
					//	var vus = data["us"];
					//alert(val[0].id);
					
						for (i=0; i<val.length; i++) {
			li = document.createElement('li');
			str='<a target="_parent" href="edu_detail.html" target="_parent"><div class="imgArea">';
			str=str+'<img src="http://182.92.100.235/'+val[i].picture+'" alt="" /></div><div class="infoArea"><h3>'+val[i].name+'</h3>';
			str=str+'<p class="rate clearfix"><span class="star_on"></span><span class="star_on"></span><span class="star_off">';
			str=str+'</span><span class="star_off"></span><span class="star_off"></span></p><div class="clearfix">';
			str=str+'<strong>海淀区</strong><span>职业技术</span><div class="distance">'+val[i].distance+'m</div></div></div></a>';
		//	alert(str);
			li.innerHTML=str;
			el.appendChild(li, el.childNodes[0]);
		}
						wrapper.refresh();/****remember to refresh after action completed！！！   ---id.refresh(); --- ****/
						
						$("#divc").html(parseInt($("#divc").html())+1);
					
					
						},
					
		})	
		
	});	
}
</script>
</body>
</html>
