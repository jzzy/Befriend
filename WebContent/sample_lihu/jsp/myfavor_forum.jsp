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
<script type="text/javascript" src="<%=request.getContextPath() %>/sample_lihu/js/jquery-1.10.2.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/sample_lihu/js/common.js"></script>

<link href="<%=request.getContextPath() %>/sample_lihu/css/style.css" rel="stylesheet" type="text/css" />
</head>
<body>
	<div id="wrap">
		<div id="header">
			<div class="topArea clearfix">
				<h1>我的收藏</h1>
				<div class="prev">
					<a href="#" onclick="javascript:history.back(-1);"><img src="<%=request.getContextPath() %>/sample_lihu/images/prev.png" alt="后退" /></a>
				</div>
				<div class="topTool">
					<span id="favorEdit" >编辑</span>
				</div>
			</div>
			<ul class="topMenu colum3 clearfix">
				<li class="on" id="li1"><a id="af1" href="#">资讯</a></li>
				<li  id="li2"  ><a id="af2" href="#">论坛</a></li>
				<li  id="li3"  ><a id="af3" href="#">教辅机构</a></li>
			</ul>
		</div><!--header-->
		<%
List<EduServices> edusl=(List)request.getAttribute("edusl")==null?new ArrayList<EduServices>():(List)request.getAttribute("edusl"); 
List<News> nl=(List)request.getAttribute("nl")==null?new ArrayList<News>():(List)request.getAttribute("nl"); 
List<Attention> attl=(List)request.getAttribute("attl")==null?new ArrayList<News>():(List)request.getAttribute("attl"); 
%>	
		<div id="container">
			<div class="myFavor">
				
			<!-- 新闻的收藏 -->
			<div id="divmainList" class="mainList">
					<ul>
					<%
				//新闻
				for(int i=0;i<nl.size();i++){
					if(nl.get(i)==null){
						continue;
					}
				%>
				
				
						<li id="<%=nl.get(i).getId() %>">
							<div class="checkArea"   ><label for="a1"><img src="<%=request.getContextPath() %>/sample_lihu/images/ico28.png" alt="swich" /></label>
							<input type="checkbox"  name="check" value="<%=nl.get(i).getId() %>" /></div>
							<div class="inner clearfix">
								<div class="infoArea">
									<p class="title"><a href="newsdetail.html"><%=nl.get(i).getTitle() %></a></p>
									<p class="info">
										<span><img src="<%=request.getContextPath() %>/sample_lihu/images/comment_ico.gif" alt="留言" />&nbsp;<%=nl.get(i).getReviews() %></span>
										<span><img src="<%=request.getContextPath() %>/sample_lihu/images/favor_ico.gif" alt="关注" />&nbsp;<%=nl.get(i).getCollectnum() %></span>
									</p>
								</div>
								<div class="imgArea">
									<p class="big_img"><a href="newsdetail.html"><img src="<%=request.getContextPath() %>/sample_lihu/images/listBanner.jpg" alt="mediumImg" /></a></p>
								</div>
							</div>
						</li>
									<%
				}
							%>	
					</ul>
				</div>
				
				
				<!-- 论坛的收藏 -->
				<div id="divforum" class="forum" style="display: none;">
					<ul>
					
						<li>
							<div class="checkArea">
							<label for="a2">
							<img src="<%=request.getContextPath() %>/sample_lihu/images/ico28.png" alt="swich" />
							</label>
							<input type="checkbox"  name="check" value="1" />
							</div>
							<div class="inner clearfix">
								<div class="user clearfix">
									<p class="leftArea"><img src="<%=request.getContextPath() %>/sample_lihu/images/testuser_ico.gif" alt="sample" /></p>
									<p class="rightArea">
										<span>家长之友aaa</span>
										<span>2016-01-15 09:54</span>
									</p>
								</div>
								<div class="title"><a href="forumreview.html">这里是标题</a></div>
								<div class="detail"><a href="forumreview.html">第一题是这样的第一题是这样的第一题是这样的第一题是这样的</a></div>
								<div class="imgList clearfix">
									<p><a href="forumreview.html"><img src="<%=request.getContextPath() %>/sample_lihu/images/listBanner.jpg" alt="sample" /></a></p>
									<p><a href="forumreview.html"><img src="<%=request.getContextPath() %>/sample_lihu/images/listBanner.jpg" alt="sample" /></a></p>
									<p><a href="forumreview.html"><img src="<%=request.getContextPath() %>/sample_lihu/images/listBanner.jpg" alt="sample" /></a></p>
								</div>
								<div class="info">
									<span><img src="<%=request.getContextPath() %>/sample_lihu/images/comment_ico.gif" alt="留言" />&nbsp;520</span>
									<span><img src="<%=request.getContextPath() %>/sample_lihu/images/favor2_ico.gif" alt="关注" />&nbsp;120</span>
								</div>
							</div>
						</li>		
					</ul>
				</div>
				<!-- 教辅机构的收藏 -->
				<div id="divlistPack" class="listPack" style="display: none;">
					<ul>
					<%
				for(int i=0;i<edusl.size();i++){
				%>
					
					
						<li  id="<%=attl.get(i).getId()%>">
							<div class="checkArea"><label for="a1"><img src="<%=request.getContextPath() %>/sample_lihu/images/ico28.png" alt="swich" /></label>
							<input type="checkbox"  name="check"  value="<%=attl.get(i).getId()%>" /></div>
							<div class="inner clearfix">
								<a href="getWebCommments?merchantId=<%=edusl.get(i).getMerchantId()%>">
									<div class="imgArea"><img src="<%=request.getContextPath() %>/sample_lihu/images/xw_15.png" alt="" /></div>
									<div class="infoArea">
										<h3><%=edusl.get(i).getName() %></h3>
										<p class="rate clearfix"><span class="star_on"></span><span class="star_on"></span><span class="star_off"></span><span class="star_off"></span><span class="star_off"></span></p>
										<div class="clearfix"><strong><%=edusl.get(i).getCounty() %></strong><span><%=edusl.get(i).getClassSecond() %></span><div class="distance">8000m</div></div>
									</div>
								</a>
							</div>
						</li>
								<%
				}
					%>
						
					</ul>
				</div>
			</div>
		</div><!--container-->
		<div id="footer">
			<div class="favorDel clearfix">
				<div class="leftArea">
					<span id="checkAll">全选</span>
				</div>
				<div class="rightArea">
					<span id="delete">删除</span>
				</div>
			</div>
		</div><!--footer-->
	</div><!--wrap-->

<script type="text/javascript">
$("#af1").click(function(){
	//alert(1);
	$("#divmainList").show();
	$("#divforum").hide();
	$("#divlistPack").hide();
	
	$(".checkArea").hide();
	$(".favorDel").hide();
	$("#favorEdit").text("编辑");
	
	$("#container .myFavor li").css("padding-left","0px");
	$("#container").css("padding-bottom","0px");
	
	
	
});

$("#af2").click(function(){
	$("#divmainList").hide();
	$("#divforum").show();
	$("#divlistPack").hide();
	
	$(".checkArea").hide();
	$(".favorDel").hide();
	$("#favorEdit").text("编辑");
	$("#container .myFavor li").css("padding-left","0px");
	$("#container").css("padding-bottom","0px");
	
	
});
$("#af3").click(function(){
	$("#divmainList").hide();
	$("#divforum").hide();
	$("#divlistPack").show();
	
	$(".checkArea").hide();
	$(".favorDel").hide();
	$("#favorEdit").text("编辑");
	$("#container .myFavor li").css("padding-left","0px");
	$("#container").css("padding-bottom","0px");
	
	
});
//获取选中的checked的值 进行炒作
$("#delete").click(function(){
	//alert($("input:checkbox:checked").length); 
	
   
    	var text="";  
        $("input[name=check]").each(function() {  
            if ($(this).attr("checked")) {
                text += ","+$(this).val();
            }
    
        });
        alert(text);
        if(confirm("确定删除？")){
		 	 
   		 
   		 $("input[name=check]").each(function() {  
   			 if ($(this).attr("checked")) {
   				// alert($(this).val());
	                $("#"+$(this).val()).css("display","none");
	            }
   	    
   	        });
   		 //调用新闻
         if($("#divmainList").is(":visible")){
        	 $.ajax({
					type:"post",
					url:"RemoveCWeb.action?summary="+text
				});
         }
       //调用论坛
 		if($("#divforum").is(":visible")){
         	
         }
 		//调用教辅机构
 		if($("#divlistPack").is(":visible")){
 			 $.ajax({
    	  			type:"post",
    	  			url:"removeEduAttentionWeb.action?attentionId="+text
    	  		});
         	
         }
   			alert("删除成功！");
   	     	
   	  }else{
   			alert("取消成功！");
  		   }
        
        
      
       
      
    //var chk_value =[];    
    // $('input[name="check"]:checked').each(function(){    
    // chk_value.push($(this).val());    
    // });    
    // alert(chk_value.length==0 ?'你还没有选择任何内容！':chk_value);   
	 	
		
});
$(function(){
$(".checkArea input").attr("checked", false);
	
	$(".checkArea label").click(function(){
		var favorCheck = $(this).siblings("input").attr("checked");
		//alert(favorCheck);
		if( favorCheck != "checked" ){
			//alert(1);
		$(this).children("img").attr("src","<%=request.getContextPath() %>/sample_lihu/images/ico29.png");
		$(this).siblings("input").attr({"checked":true});
		}else {
		$(this).children("img").attr("src","<%=request.getContextPath() %>/sample_lihu/images/ico28.png");
		$(this).siblings("input").attr({"checked":false});
		}
	});
});


$("#favorEdit").click(function(){
	var dis = $(".checkArea").css("display");
	if( dis == "none"){
		$(".checkArea").show();
		$(".favorDel").show();
		$(this).text("完成");
		$("#container .myFavor li").css("padding-left","26px");
		$("#container").css("padding-bottom","48px");
	};
	if( dis == "block"){
		$(".checkArea").hide();
		$(".favorDel").hide();
		$(this).text("编辑");
		$("#container .myFavor li").css("padding-left","0px");
		$("#container").css("padding-bottom","0px");
	};	
});
$("#checkAll").click(function(){
	var favorCheck =$(".mainList input").attr("checked");
	if(favorCheck){
		$(".mainList input").attr({"checked" : false}).siblings("label").find("img").attr("src","<%=request.getContextPath() %>/sample_lihu/images/ico28.png");

	}else{
		$(".mainList input").attr({"checked" : true}).siblings("label").find("img").attr("src","<%=request.getContextPath() %>/sample_lihu/images/ico29.png");
		
	}
});

</script>
</body>
</html>
