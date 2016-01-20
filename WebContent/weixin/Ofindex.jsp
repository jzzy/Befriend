<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
      <%@page import="java.util.*"%>
    <%@page import="com.befriend.util.*"%>
<%@page import="com.befriend.entity.News"%>
<%
//获取前8个新闻
List<News> nl=(List)request.getAttribute("nl")==null?new ArrayList<News>():(List)request.getAttribute("nl");
//省级
Object province=request.getAttribute("province")==null?null:request.getAttribute("province");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<script  src="SimulationApp/js/jquery.js"></script>

<head>
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>本地资讯</title>
<link href="<%=request.getContextPath()%>/weixin/css/style.css" rel="stylesheet" type="text/css" />
<script  src="<%=request.getContextPath()%>/weixin/js/TouchSlide.1.1.js"></script>
<script src="<%=request.getContextPath()%>/weixin/js/jweixin-1.0.0.js"></script>    
</head>

<body>
<div class="cont">
 
   
  
   <!--slideBox-->
    <%
    if(nl.size()==0){
    %>
  
 
    <%
    	return;
    }
    		int i=0;
    		
				for(;i<nl.size();i++){
			%>
						<a href="<%=request.getContextPath()%>/webNewsId?id=<%=nl.get(i).getId()%>">
				<dl class="list">
    				<dt >
    	<img src="<%=request.getContextPath()+nl.get(i).getImg() %>"/>
    				
    				</dt>
     			    <dd>
     			    <p>  <b style="color:#666;font-size: 18px;"  ><%=nl.get(i).getTitle() %></b></p>
       				
        		     <p  class="subtit" style="color: #666;"><%=nl.get(i).getSummary() %></p>
     		       </dd>
  				 </dl>	
  				 </a>
							
           <%
			}
            %>  
   <!--list-->  
  </div>
  <!--more4-->
</div>
<!--cont-->
</body>
</html>