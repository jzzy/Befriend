<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    <%@page import="java.util.*"%>
    <%@page import="com.befriend.util.*"%>
<%@page import="com.befriend.entity.*"%>
<%
List<Book> bookl=(List)request.getAttribute("bookl");
int type=request.getAttribute("type")==null?0:(Integer)request.getAttribute("type");
String title="";
switch (type) {
case 1:
	title="幼儿数目推荐";
	break;
case 2:
	title="小学数目推荐";
	break;
case 3:
	title="初中数目推荐";
	break;
case 4:
	title="高中数目推荐";
	break;

default:
	title="幼儿数目推荐";
	break;
}
switch(type){
case 1:
	type=331;
	break;
case 2:
	type=332;
	break;
case 3:
	type=333;
	break;
case 4:
	type=334;
	break;
default:
	type=331;
	break;
}
int currentPage=(Integer)request.getAttribute("currentPage");
int max=request.getAttribute("max")==null?0:(Integer)request.getAttribute("max");
if(bookl.size()==0){
	
}

%>
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
	<div id="wrap">
		<div id="header">
			<div class="topArea clearfix">
				<h1><%=title %></h1>
				<div class="prev">
					<a href="#" onclick="javascript:history.back(-1);"><img src="sample_lihu/images/prev.png" alt="后退" /></a>
				</div>
			</div>
		</div><!--header-->
		<div id="container">
			<div id="wrapper">
				<ul class="bookList clearfix">
								 <%
   								 for(int i=0;i<bookl.size();i++){
   							     %>
					<li>
						<a href="webLookBookById?id=<%=bookl.get(i).getId() %>">
							<h2><%=bookl.get(i).getTitle() %></h2>
							<p><img src="sample_lihu/images/ico04.gif" alt="作者" /><%=bookl.get(i).getAuthor() %></p>
							<p><img src="sample_lihu/images/ico05.gif" alt="出版社" /><%=bookl.get(i).getSummary() %></p>
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
			li.innerHTML='<a href="book_detail.html"><h2>中国童谣</h2><p><img src="images/ico04.gif" alt="作者" />李光迪、金波（文）、田原、胡永凯（图）</p><p><img src="images/ico05.gif" alt="出版社" />连环画出版社</p></a>';
			el.appendChild(li, el.childNodes[0]);
		}
		wrapper.refresh();/****remember to refresh after action completed！！！   ---id.refresh(); --- ****/
	},2000);	
}
</script>
</body>
</html>
