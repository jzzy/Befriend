<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
      <%@page import="java.util.*"%>
    <%@page import="com.befriend.util.*"%>
<%@page import="com.befriend.entity.News"%>
<%
//获取前8个新闻
List<News> nl=(List)request.getAttribute("nl")==null?new ArrayList<News>():(List)request.getAttribute("nl");
//省级
Object province=request.getAttribute("province")==null?"":request.getAttribute("province");
//市级
int currentPage=(Integer)request.getAttribute("currentPage")==null?0:(Integer)request.getAttribute("currentPage");
int cp=currentPage;
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<script  src="SimulationApp/js/jquery.js"></script>
<script type="text/javascript">

$(window).scroll(function () {
    var scrollTop = $(this).scrollTop();
    var scrollHeight = $(document).height();
    var windowHeight = $(this).height();
    if (scrollTop + windowHeight >=scrollHeight-300) {
	//alert("加载了");
	 
	htmlobj=$.ajax({url:"/Befriend/weiXniProvince?currentPage="+$("#divc").html(),async:false});
	$("#divc").html(parseInt($("#divc").html())+1);
	$("#myDiv").html($("#myDiv").html()+htmlobj.responseText);
		 
  //此处是滚动条到底部时候触发的事件，在这里写要加载的数据，或者是拉动滚动条的操作

//var page = Number($("#redgiftNextPage").attr('currentpage')) + 1;
//redgiftList(page);
//$("#redgiftNextPage").attr('currentpage', page + 1);

    }
});

$(document).ready(function(){

	  $("#b01").click(function(){
	  htmlobj=$.ajax({url:"/Befriend/weiXniProvince?currentPage="+$("#divc").html(),async:false});
	  $("#divc").html(parseInt($("#divc").html())+1);
	  $("#myDiv").html($("#myDiv").html()+htmlobj.responseText);
	  });
	});
$(document).ready(function(){
	
	//alert("cookie:"+document.cookie);
	$.ajax({
		type:"post",
		url:"statisticsPVIP.action"	
	});
});

</script>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>本地资讯</title>
<link href="<%=request.getContextPath()%>/weixin/css/style.css" rel="stylesheet" type="text/css" />
<script  src="<%=request.getContextPath()%>/weixin/js/TouchSlide.1.1.js"></script>
<script src="<%=request.getContextPath()%>/weixin/js/jweixin-1.0.0.js"></script>    
</head>

<body>
<div id="divc" style="display: none"><%=currentPage+1 %></div>

<div class="cont">

   <div class="top">
   
       <a  href="<%=request.getContextPath()%>/webNewsA10" class="fl"><img src="<%=request.getContextPath()%>/weixin/images/xw_03.png" height="40" /></a>
      <!--   <p class="set fr"><a href="<%=request.getContextPath()%>/webNewsA10" title="返回主页"><img src="<%=request.getContextPath()%>/weixin/images/xw_07s.png" height="40" /></a></p>
  -->
 
    <a style="margin-top: 8px;float: right;font-size:16px;color:white;" href="<%=request.getContextPath()%>/SimulationApp/lihu/jsp/wechatSwitchregion.jsp" class="fl"> 
	
	<span>< 切换地区</span>
    </a>
    <center style="margin-top: 8px;margin-right:0px; font-size:16px;color:white;">
  
    <span><%=province %></span>

    </center>
   </div>

   <!--top-->
   <!--  
<h1 class="tit8"><span class="fl">本地资讯</span>
   <span class="fr"><%=province %><a href="<%=request.getContextPath()%>/weixin/change_city.html" title="切换城市">
   <img src="<%=request.getContextPath()%>/weixin/images/ez-switch-user.png" width="23">
   </a>
   </span>
   </h1>
   -->

  

   <%	   
   if(nl.size()==0){
	   %>
	     
	 
   
   <h1>该地区尚未上传新闻!</h1>
	   <%
	   return;
	   }else{
		   
		   %>
		
		   <%
	   }
   
   %>
   
   
   <%
   if(nl.size()>4){
   %>
 	    

   <div  id="slideBox"  class="slideBox">
			  <div  class="bd">
					<div  class="tempWrap"  style="overflow:hidden; position:relative;">
                        <ul  style="width: 3360px; position: relative; overflow: hidden; padding: 0px; margin: 0px; transition: 200ms; -webkit-transition: 200ms; -webkit-transform: translate(-1680px, 0px) translateZ(0px);">
								<%
								
								for(int i=0;i<4;i++){
									
							%>
							
							<li  style="display: table-cell; vertical-align: top; width:100%;">
								
								<a  class="pic" href="<%=request.getContextPath()%>/webNewsId?id=<%=nl.get(i).getId()%>"><img  src="<%=request.getContextPath()+nl.get(i).getImgmax()%>"></a>															
								
							</li>
                           <%
                           } 
                           %>                         
                       </ul>
                    </div>
				</div>
                <!--bd-->
				<div  class="hd">
					<ul><li  class="">1</li><li  class="">2</li><li  class="on">3</li><li  class="">4</li></ul>
				</div>
   </div>
   
   <%
   }
   %>
   <!--slideBox-->
    <%
    		int i=0;
    		if(nl.size()>4){
    			i=4;
    			}
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
   <div id="myDiv"></div>
   <!--  
   <div id="b01" class="more4">
     <dl >
      <dt><img src="<%=request.getContextPath()%>/weixin/images/xia.png" width="23"></dt>
      <dd> <b   style="color:#666;font-size: 18px;"  >查看更多</b></dd>
 
      
    </dl>
  </div>
  -->
  <!--more4-->
</div>
<!--cont-->

<script  type="text/javascript">
				TouchSlide({ 
					slideCell:"#slideBox",
					titCell:".hd ul", //开启自动分页 autoPage:true ，此时设置 titCell 为导航元素包裹层
					mainCell:".bd ul", 
					effect:"leftLoop", 
					autoPage:true,//自动分页
					autoPlay:true //自动播放
				});
			</script>
</body>
</html>