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
<script type="text/javascript" src="<%=request.getContextPath() %>/sample_lihu/js/common.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/sample_lihu/js/jMenu.jquery.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/sample_lihu/js/jquery.lazyload.min.js"></script>
</head>
<body>
	<div id="bg">bg</div>
	<div id="wrap">
		<div id="header">
			<div class="topArea clearfix">
				<h1>教辅机构</h1>
				<div class="prev">
					<a href="webGetapp"><img src="<%=request.getContextPath() %>/sample_lihu/images/prev.png" alt="后退" /></a>
				</div>
				<div class="topTool">
					<a href="<%=request.getContextPath() %>/sample_lihu/jsp/edu_search.jsp"><img src="sample_lihu/images/ico31.png" alt="搜索" /></a>
					<a href="<%=request.getContextPath() %>/sample_lihu/jsp/mypage.jsp"><img src="sample_lihu/images/ico32.png" alt="我的页面" /></a>
				</div>
			</div>
			<ul class="navi clearfix" id="jMenu">
			<%
				List<Areas> lar=(List)(request.getAttribute("lar")==null?new ArrayList<Areas>():request.getAttribute("lar")); 			
				List<Cities> lcit=(List)(request.getAttribute("lcit")==null?new ArrayList<Cities>():request.getAttribute("lcit")); 			

				%>
			
				<li class="district">
					<a  class="fNiv">全部地区&nbsp;<img src="sample_lihu/images/arrow_off.png" width="20" height="10" alt="" /></a>
					<ul>
									<%
					if(lar.size()==0){
					%>
					<li><a href="getEduWebArea?city=all"><%=session.getAttribute("province") %></a></li>
					<%
					}else{
					%>
					<li><a href="getEduWebArea?county=all"><%=session.getAttribute("province") %></a></li>
					<%
					}
					%>
						<%
						for(int i=0;i<lar.size();i++){
							
						
						%>
						<li><a href="getEduWebArea?county=<%=lar.get(i).getArea().substring(0, lar.get(i).getArea().length()-1) %>&province=<%=session.getAttribute("province")==null||session.getAttribute("province").equals("null")?"北京":session.getAttribute("province")%>"><%=lar.get(i).getArea() %></a></li>
					
						<%
			
						}
						%>
						
						
							<%
						for(int i=0;i<lcit.size();i++){
							
						
						%>
						<li><a href="getEduWebArea?city=<%=lcit.get(i).getCity().substring(0, lcit.get(i).getCity().length()-1) %>&province=<%=session.getAttribute("province")==null||session.getAttribute("province").equals("null")?"北京":session.getAttribute("province")%>"><%=lcit.get(i).getCity() %></a></li>
					
						<%
						}
						%>
					</ul>
				</li>
				<li class="edutrain">
					<a class="fNiv">教育培训&nbsp;<img src="sample_lihu/images/arrow_off.png" width="20" height="10" alt="" /></a>
					<ul>
						<li><a href="getEduWebArea?classSecond=all">不限</a></li>
						<li><a href="getEduWebArea?classSecond=外语">外语</a></li>
						<li><a href="getEduWebArea?classSecond=音乐">音乐</a></li>
						<li><a href="getEduWebArea?classSecond=幼儿教育">幼儿教育</a></li>
						<li><a href="getEduWebArea?classSecond=升学辅导">升学辅导</a></li>
						<li><a href="getEduWebArea?classSecond=教育院校">教育院校</a></li>
						<li><a href="getEduWebArea?classSecond=舞蹈">舞蹈</a></li>
						<li><a href="getEduWebArea?classSecond=学校">学校</a></li>
						<li><a href="getEduWebArea?classSecond=驾校">驾校</a></li>
						<li><a href="getEduWebArea?classSecond=兴趣生活">兴趣生活</a></li>
							<li><a href="getEduWebArea?classSecond=职业技术">职业技术</a></li>
						<li><a href="getEduWebArea?classSecond=体育">体育</a></li>
						<li><a href="getEduWebArea?classSecond=美术">美术</a></li>
						
					</ul>
				</li>
				<li class="sort fNiv">
					<a  class="fNiv">智能排序&nbsp;<img src="sample_lihu/images/arrow_off.png" width="20" height="10" alt="" /></a>
					<ul>
					<li><a href="getEduWebArea?sortType=1">离我最近的商户</a></li>
						<li><a href="getEduWebArea?sortType=2">人气最高的商户</a></li>
						<li><a href="getEduWebArea?sortType=5">评价最好的商户</a></li>
						<li><a href="getEduWebArea?sortType=3">人均最低的商户</a></li>
						<li><a href="getEduWebArea?sortType=4">人均最高的商户</a></li>
					</ul>
				</li>
			</ul>
		</div><!--header-->
		<!--<div id="container" class="load" style="padding:70px 0 0;">
			<iframe id="mainiframe" src="<%=request.getContextPath()+"/getEduWeb" %>" frameborder="0" scrolling="no" width="100%"></iframe>
		</div>container-->
		
		
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
					<a href="<%=request.getContextPath() %>/getWebCommments?merchantId=<%=lEduServices.get(i).getMerchantId()%>" target="_parent" >
							<div class="imgArea"><img src="<%=request.getContextPath() %>/sample_lihu/images/listBanner.png" data-original="<%="http://182.92.100.235/"+lEduServices.get(i).getPicture() %>" alt="" /></div>
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
		<div id="loding"><img src="<%=request.getContextPath() %>/sample_lihu/images/123.gif" alt="loding_ico" /></div><!--loding-->
		
		<div id="footer"></div><!--footer-->
	</div><!--wrap-->
	

<script type="text/javascript">
  $(document).ready(function(){
    $("#jMenu").jMenu();
  })
</script>
<script>

$(window).scroll(function () {
    if ($(document).scrollTop() + $(window).height() >= $(document).height()) {
        $("#loding").slideDown(300).delay(300).slideUp(300);
		var el, li, i,ht,y,g;
		el =document.querySelector("#wrapper ul");
		//alert($("#divc").html());
		$.ajax({
			
			url:"/Befriend/getEduWebToJson?currentPage="+$("#divc").html(),
			 dataType: "json", 
					async:false,
					success: function (data) {     
						var val = data["edl"];//获取json中的 key
					//	var vus = data["us"];
					//alert(val[0].id);
					
						for (i=0; i<val.length; i++) {
							
							li = document.createElement('li');
							
							
							ht='<a href="<%=request.getContextPath()+"/" %>getWebCommments?merchantId='+val[i].merchantId+'" target="_parent"><div class="imgArea">';
							ht+='<img src="<%=request.getContextPath() %>/sample_lihu/images/listBanner.png" data-original="http://182.92.100.235/'+val[i].picture+'" alt="" /></div><div class="infoArea"><h3>'+val[i].name+'</h3>';
							ht+='<p class="rate clearfix">';
							g=0;
							for(y=0;y<val[i].star;y++){
								g++;
								ht+='<span class="star_on"></span>';
							}
							for(y=0;y<5-g;y++){
								ht+='<span class="star_off"></span>';
							}

							ht+='</p>';
							ht+='<div class="clearfix"><strong>'+val[i].city+'</strong><span>'+val[i].classFirst+'</span><div class="distance">'+val[i].distance+'m</div></div></div></a>';
							li.innerHTML=ht;
							el.appendChild(li, el.childNodes[0]);
						}
						 $("img").lazyload({ 
					          placeholder : "<%=request.getContextPath() %>/sample_lihu/images/listBanner.png",
					                effect: "fadeIn",
					                threshold : 200
					                 
					           });
						$("#divc").html(parseInt($("#divc").html())+1);
						
						//wrapper.refresh();/****remember to refresh after action completed！！！   ---id.refresh(); --- ****/
					
					
					
					
						},
					
		})
		
		
		

    }
});	

$(function() {
	 
    $("img").lazyload({ 
    placeholder : "<%=request.getContextPath() %>/sample_lihu/images/listBanner.png",
          effect: "fadeIn",
          threshold : 200
           
     });  
});
</script>
</body>
</html>
