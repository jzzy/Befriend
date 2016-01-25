<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta charset="utf-8">
    <title>定位</title>
    
      <script type="text/javascript" src="/Befriend/SimulationApp/js/jquery.js"></script>
    <script type="text/javascript" src="http://api.map.baidu.com/api?v=1.3"></script>
    <script type="text/javascript" src="http://developer.baidu.com/map/jsdemo/demo/convertor.js"></script>
    <script type="text/javascript" >
    $.getScript('http://int.dpool.sina.com.cn/iplookup/iplookup.php?format=js',function(){  
    	 // alert(remote_ip_info.country);//国家  
    	  //alert(remote_ip_info.province);//省份  
    	  //alert(remote_ip_info.city);//城市  
    	  //alert("您所在的省份为:" + remote_ip_info.province);
    	  if(<%=session.getAttribute("province")==null%>){
    		//  alert("进入主页");
    		  location.href="/Befriend/webNewsA10?province="+remote_ip_info.province;  
    	  }else{
    		//  alert("已经定位过");
    	  }
    	 
             	});  

    </script>
</head>
<body>
</body>
</html>