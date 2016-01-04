<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title></title>
<script type="text/javascript" src="<%=request.getContextPath() %>/SimulationApp/lihu/js/jquery.js"></script>
<link href="<%=request.getContextPath() %>/SimulationApp/lihu/css/style.css" rel="stylesheet" type="text/css" />
 <script type="text/javascript" >
    $.getScript('http://int.dpool.sina.com.cn/iplookup/iplookup.php?format=js',function(){  
    	document.getElementById('spanarea').innerHTML = remote_ip_info.province;
    	//  alert("地址为:"+remote_ip_info.country+"-"+remote_ip_info.province+"-"+remote_ip_info.city);//国家省份城市
    	 
         
    	});  
  
    function get(){
    	    	    	
    	location.href="/Befriend/webHotareaf?province="+$("#spanarea").html();	 
    	         
    	    	
     
     
    }

    </script>
</head>
<body>
	<div class="cont">
		<div id="header">
			<div class="topArea clearfix">
				<div class="prev">
					<a href="<%=request.getContextPath() %>/webHotareaf?province=北京" onclick="javascript:history.back(-1);"></a>
				</div>
				<h1>本地资讯</h1>
			</div>
		</div><!--header-->
		
		<div id="container">
			<div class="currentCity clearfix">
				<span class="title">当前定位城市&nbsp;:</span>
				<a href="#" onclick="get()" ><span id="spanarea" class="name"></span></a>
			</div>
			<div class="hotCity">
				<h2>热门城市</h2>
				<ul class="clearfix">
					<li><a href="<%=request.getContextPath() %>/webHotareaf?province=北京" >北京</a></li>
					<li><a href="<%=request.getContextPath() %>/webHotareaf?province=上海">上海</a></li>
					<li><a href="<%=request.getContextPath() %>/webHotareaf?province=河南">河南</a></li>
					<li><a href="<%=request.getContextPath() %>/webHotareaf?province=江苏">江苏</a></li>
					<li><a href="<%=request.getContextPath() %>/webHotareaf?province=广东">广东</a></li>
					<li><a href="<%=request.getContextPath() %>/webHotareaf?province=山东">山东</a></li>
					<li><a href="<%=request.getContextPath() %>/webHotareaf?province=浙江">浙江</a></li>
					<li><a href="<%=request.getContextPath() %>/webHotareaf?province=河北">河北</a></li>
				</ul>
			</div>
			<div class="cityList">
				<h2>省市列表(共<span>34</span>个省市)</h2>
				<ul class="clearfix">
					<li><a href="<%=request.getContextPath() %>/webHotareaf?province=北京">北京</a></li>
					<li><a href="<%=request.getContextPath() %>/webHotareaf?province=上海">上海</a></li>
					<li><a href="<%=request.getContextPath() %>/webHotareaf?province=天津">天津</a></li>
					<li><a href="<%=request.getContextPath() %>/webHotareaf?province=重庆">重庆</a></li>
					<li><a href="<%=request.getContextPath() %>/webHotareaf?province=河北">河北</a></li>
					<li><a href="<%=request.getContextPath() %>/webHotareaf?province=山西">山西</a></li>
					<li><a href="<%=request.getContextPath() %>/webHotareaf?province=内蒙古">内蒙古</a></li>
					<li><a href="<%=request.getContextPath() %>/webHotareaf?province=辽宁">辽宁</a></li>
					<li><a href="<%=request.getContextPath() %>/webHotareaf?province=吉林">吉林</a></li>
					<li><a href="<%=request.getContextPath() %>/webHotareaf?province=黑龙江">黑龙江</a></li>
					<li><a href="<%=request.getContextPath() %>/webHotareaf?province=江苏">江苏</a></li>
					<li><a href="<%=request.getContextPath() %>/webHotareaf?province=浙江">浙江</a></li>
					<li><a href="<%=request.getContextPath() %>/webHotareaf?province=安徽">安徽</a></li>
					<li><a href="<%=request.getContextPath() %>/webHotareaf?province=福建">福建</a></li>
					<li><a href="<%=request.getContextPath() %>/webHotareaf?province=江西">江西</a></li>
					<li><a href="<%=request.getContextPath() %>/webHotareaf?province=山东">山东</a></li>
					<li><a href="<%=request.getContextPath() %>/webHotareaf?province=河南">河南</a></li>
					<li><a href="<%=request.getContextPath() %>/webHotareaf?province=湖北">湖北</a></li>
					<li><a href="<%=request.getContextPath() %>/webHotareaf?province=湖南">湖南</a></li>
					<li><a href="<%=request.getContextPath() %>/webHotareaf?province=广东">广东</a></li>
					<li><a href="<%=request.getContextPath() %>/webHotareaf?province=广西">广西</a></li>
					<li><a href="<%=request.getContextPath() %>/webHotareaf?province=海南">海南</a></li>
					<li><a href="<%=request.getContextPath() %>/webHotareaf?province=四川">四川</a></li>
					<li><a href="<%=request.getContextPath() %>/webHotareaf?province=贵州">贵州</a></li>
					<li><a href="<%=request.getContextPath() %>/webHotareaf?province=云南">云南</a></li>
					<li><a href="<%=request.getContextPath() %>/webHotareaf?province=西藏">西藏</a></li>
					<li><a href="<%=request.getContextPath() %>/webHotareaf?province=陕西">陕西</a></li>
					<li><a href="<%=request.getContextPath() %>/webHotareaf?province=甘肃">甘肃</a></li>
					<li><a href="<%=request.getContextPath() %>/webHotareaf?province=宁夏">宁夏</a></li>
					<li><a href="<%=request.getContextPath() %>/webHotareaf?province=青海">青海</a></li>
					<li><a href="<%=request.getContextPath() %>/webHotareaf?province=新疆">新疆</a></li>
					<li><a href="<%=request.getContextPath() %>/webHotareaf?province=香港">香港</a></li>
					<li><a href="<%=request.getContextPath() %>/webHotareaf?province=澳门">澳门</a></li>
					<li><a href="<%=request.getContextPath() %>/webHotareaf?province=台湾">台湾</a></li>
				</ul>
			</div>
		</div><!--container-->
	</div><!--cont-->

</body>
</html>
