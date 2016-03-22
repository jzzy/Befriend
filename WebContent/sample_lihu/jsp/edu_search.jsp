<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    <%@page import="java.util.*"%>
    <%@page import="com.befriend.util.*"%>
<%@page import="com.befriend.entity.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title></title>
<link href="<%=request.getContextPath() %>/sample_lihu/css/style.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="<%=request.getContextPath() %>/sample_lihu/js/jquery-2.1.3.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/sample_lihu/js/common.js"></script>
<script type="text/javascript" >
 

//清空   
function  deleteloca()
{	 
	
	if(localStorage.getItem('lcls')!=null&&confirm("是否清空搜索历史？")){
	var div1 = document.getElementById('div1');
	localStorage.clear();
	div1.innerHTML = "";
	}
}
	

//读取
$(document).ready(function(){
	
	var div1 = document.getElementById('div1'); 
	var loc=localStorage.getItem('lcls');  //获取在  localStorage的值 
	//alert(loc);
	if(loc!=null){
		
	
	var array=loc.split(",");
	var htm11='';
	for (var i=0 ; i< array.length ; i++)
	{
	if(i==0){
		htm11='<li><a href="<%=request.getContextPath() %>/getLikeEduWebArea?value='+array[i]+'">'+array[i]+'</a></li>';

	}else{
		htm11+='<li><a href="<%=request.getContextPath() %>/getLikeEduWebArea?value='+array[i]+'">'+array[i]+'</a></li>';

	}
	}


	div1.innerHTML = htm11;
	}
	
});
//提交表单并存储
function  checkvalue()
{	 
	
	var search2 = document.getElementById('search2'); //搜索框
	
	var str = search2.value; 
	if(str.length==0){
		alert("请填写搜索内容！");
		return false;
	}

	var loc=localStorage.getItem('lcls');  //获取在  localStorage的值
	
	if(loc!=null){
		
		 
		var array=loc.split(",");
	
		var b=true;
			for (var i=0 ; i< array.length ; i++)
			{
				
				
				
				if(array[i]==str)
				{
					b=false;
				}
				
			}
		
			if(b){
				//存储在localStorage
				localStorage.setItem('lcls',loc+ ","+str); 
			}
		
	}else{
		localStorage.setItem('lcls',str);
		
	}
	
	
	document.form1.action="<%=request.getContextPath() %>/getLikeEduWebArea";
	document.form1.submit();
	return true;


}
</script>

</head>
<body>
	<div id="wrap">
		<div id="header">
			<div class="topArea clearfix">
			<form name="form1" action="<%=request.getContextPath() %>/getLikeEduWebArea" onsubmit="return false">
				<div class="search_input2">
					<div class="inner">
						<input type="text" name="value" id="search2" />
						<!-- onClick="javascript: checkvalue();"  -->
						<a  class="search_btn2">
						<img  onclick="return checkvalue();" src="<%=request.getContextPath() %>/sample_lihu/images/ico07.gif" alt="确认搜索" />
						</a>
						</span>
					</div>
				</div>
				<div class="prev">
					<a href="<%=request.getContextPath() %>/getEduWebArea"><img src="<%=request.getContextPath() %>/sample_lihu/images/prev.png" alt="后退" /></a>
				</div>
				</form>
			</div>
		</div><!--header-->
		<div id="container">
			<div class="everyone_search">
				<h2>大家都在搜</h2>
				<ul class="clearfix">
				<li><a href="<%=request.getContextPath() %>/getLikeEduWebArea?value=英语培训">英语培训</a></li>
					<li><a href="<%=request.getContextPath() %>/getLikeEduWebArea?value=韦博国际英语">韦博国际英语</a></li>
					<li><a href="<%=request.getContextPath() %>/getLikeEduWebArea?value=绘画">绘画</a></li>
					<li><a href="<%=request.getContextPath() %>/getLikeEduWebArea?value=钢琴">钢琴</a></li>
					<li><a href="<%=request.getContextPath() %>/getLikeEduWebArea?value=芭蕾舞">芭蕾舞</a></li>
					<li><a href="<%=request.getContextPath() %>/getLikeEduWebArea?value=数学">数学</a></li>
				</ul>
			</div>
			<div  class="history_search">
				<h2>搜索历史：</h2>
				<ul>
				<div id="div1" > </div>
				
				<!--
					<li><a href="<%=request.getContextPath() %>/getLikeEduWebArea?value=英语">英语</a></li>
					<li><a href="<%=request.getContextPath() %>/getLikeEduWebArea?value=数学">数学</a></li>
					<li><a href="<%=request.getContextPath() %>/getLikeEduWebArea?value=语文">语文</a></li>
					<li><a href="<%=request.getContextPath() %>/getLikeEduWebArea?value=音乐">音乐</a></li>
					  -->
				</ul>
				<p><a onclick="deleteloca();">清空搜索历史</a></p>
			</div>
		</div><!--container-->
		<div id="footer"></div><!--footer-->
	</div><!--wrap-->

</body>
</html>
