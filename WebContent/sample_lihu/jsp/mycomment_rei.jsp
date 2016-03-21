<%@page import="org.apache.jasper.tagplugins.jstl.core.ForTokens"%>
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
				<h1>我的评论</h1>
				<div class="prev">
					<a href="<%=request.getContextPath() %>/sample_lihu/jsp/mypage.jsp"><img src="<%=request.getContextPath() %>/sample_lihu/images/prev.png" alt="后退" /></a>
				</div>
				<div class="topTool">
					<span id="favorEdit" >编辑</span>
				</div>
			</div>
			<ul id="ul1" class="topMenu colum3 clearfix">
				<li class="on" id="li1"><a onclick="ul1()" id="af1" href="#">资讯</a></li>
				<li  id="li2"  ><a onclick="ul2()"   href="#">论坛</a></li>
				<li  id="li3"  ><a  onclick="ul3()" href="#">教辅机构</a></li>
			</ul>
			<ul id="ul2" style="display: none;" class="topMenu colum3 clearfix">
				<li  id="li1"><a onclick="ul1()" id="af1" href="#">资讯</a></li>
				<li class="on" onclick="ul2()" id="li2"  ><a id="af2"   href="#">论坛</a></li>
				<li  id="li3"  ><a onclick="ul3()" id="af3" href="#">教辅机构</a></li>
			</ul>
			<ul id="ul3" style="display: none;" class="topMenu colum3 clearfix">
				<li id="li1"><a onclick="ul1()" id="af1" href="#">资讯</a></li>
				<li  id="li2"  ><a onclick="ul2()" id="af2"   href="#">论坛</a></li>
				<li  class="on" onclick="ul3()"  id="li3"  ><a id="af3" href="#">教辅机构</a></li>
			</ul>
		</div><!--header-->

		<div id="container">
			<div class="myFavor">
	
	
			<!--新闻评论-->
					<div id="divmainList" class="mainList comment">
					<ul>
						<%
		List<News> nl=(List)request.getAttribute("nl")==null?new ArrayList<News>():(List)request.getAttribute("nl");
		List<Review> rl=(List)request.getAttribute("rl")==null?new ArrayList<News>():(List)request.getAttribute("rl"); 
		for(int i=0;i<nl.size();i++){
			if(rl.get(i)==null||nl.get(i)==null){
				continue;
			}
		%>
						<li id="<%=rl.get(i).getId()%>">
							<div class="checkArea"><label for="a1"><img src="<%=request.getContextPath() %>/sample_lihu/images/ico28.png" alt="swich" />
							</label><input type="checkbox" name="check" value="<%=rl.get(i).getId()%>" />
							</div>
							<dl>
								<dt class="inner clearfix">
									<div class="infoArea">
										<p class="title"><a  href="webNewsId?id=<%=rl.get(i).getNewsid()%>"><%=nl.get(i).getTitle() %></a></p>
										<p class="info">
											<span><img src="<%=request.getContextPath() %>/sample_lihu/images/comment_ico.gif" alt="留言" />&nbsp;<%=nl.get(i).getReviews() %></span>
											<span><img src="<%=request.getContextPath() %>/sample_lihu/images/favor_ico.gif" alt="关注" />&nbsp;<%=nl.get(i).getCollectnum() %></span>
										</p>
									</div>
									<%
									if(nl.get(i).getImg()!=null){
									%>
									<div class="imgArea">
										<p class="big_img"><a href="webNewsId?id=<%=rl.get(i).getNewsid()%>"><img src="<%=request.getContextPath()+nl.get(i).getImg() %>" alt="mediumImg" /></a></p>
									</div>
									<%
										}
									%>
								</dt>
								<dd class="reply">
									<div class="inner clearfix">
										<div class="leftArea"><img src="<%=request.getContextPath()+((User)session.getAttribute("u")).getImg() %>" alt="sampleuser" /></div>
										<div class="rightArea">
											<p class="name"><%=((User)session.getAttribute("u")).getNickname() %></p>
											<p class="time"><%=rl.get(i).getTime() %></p>
											<p class="detail"><%=rl.get(i).getReview() %></p>
										</div>
									</div>
								</dd>
								
							</dl>
						</li>
						<%
						
						}
						%>
					
					</ul>
					
				</div>
		
		
					<!-- 论坛  -->
						<div id="divforum" style="display: none;" class="mainList comment">
					<ul>
							<%
		List<ForumOne> fones=(List)request.getAttribute("fones")==null?new ArrayList<ForumOne>():(List)request.getAttribute("fones");
		List<ForumTwo> fow=(List)request.getAttribute("fow")==null?new ArrayList<ForumTwo>():(List)request.getAttribute("fow"); 
		List<User> us=(List)request.getAttribute("us")==null?new ArrayList<User>():(List)request.getAttribute("us"); 
		for(int i=0;i<fones.size();i++){
			if(fones.get(i)==null||fow.get(i)==null||us.get(i)==null){
				continue;
			}
		%>
						<li id="<%=fow.get(i).getId()%>" >
							<div class="checkArea"><label for="a1"><img src="<%=request.getContextPath() %>/sample_lihu/images/ico28.png" alt="swich" /></label>
							<input type="checkbox"  name="check" value="<%=fow.get(i).getId()%>"  /></div>
							<dl>
								<dt class="inner clearfix">
									<div class="infoArea">
										<p class="title"><a href="webForumLook?id=<%=fones.get(i).getId()%>" target="_parent"><%=fones.get(i).getTitle() %></a></p>
										<p class="info">
											<span><img src="<%=request.getContextPath() %>/sample_lihu/images/comment_ico.gif" alt="留言" />
											&nbsp;<%=fones.get(i).getFollectnum()%></span>
											<span><img src="<%=request.getContextPath() %>/sample_lihu/images/favor_ico.gif" alt="关注" />
											&nbsp;<%=fones.get(i).getFrs()%></span>
										</p>
									</div>
									
									<% 
									if(fones.get(i).getImg()!=null){
									%>
									<div class="imgArea">
										<p class="big_img">
										<a href="webForumLook?id=<%=fones.get(i).getId()%>" target="_parent">
										<img src="<%=request.getContextPath() +fones.get(i).getImg()%>" alt="mediumImg" />
										</a>
										</p>
									</div>
									<%
									}
									%>
								</dt>
								<dd class="reply">
									<div class="inner clearfix">
										<div class="leftArea"><img src="<%=request.getContextPath()+((User)session.getAttribute("u")).getImg() %>" alt="sampleuser" /></div>
										<div class="rightArea">
											<p class="name"><%=((User)session.getAttribute("u")).getNickname() %></p>
											<p class="time"><%=fow.get(i).getTime() %></p>
											<p class="detail"><%=fow.get(i).getReply() %></p>
										</div>
									</div>
								</dd>
							
							</dl>
						</li>
						<%
		}
						%>
						</ul>
						</div>	
				
				<!-- 教辅机构 -->
				
				
				<div id="divlistPack"  style="display: none;" class="listPack comment">
					<ul>
					<%
					List<EduComment> educl=request.getAttribute("educl")==null?new ArrayList<EduComment>():(List)request.getAttribute("educl"); 
					List<EduServices> edusl=request.getAttribute("edusl")==null?new ArrayList<EduServices>():(List)request.getAttribute("edusl"); 
					
					
					for(int i=0;i<educl.size();i++){
						if(educl.get(i)==null||edusl.get(i)==null){
							continue;
						}
					%>
					
						<li id="<%=educl.get(i).getId()%>">
							
							<div class="checkArea"><label for="a1"><img src="<%=request.getContextPath() %>/sample_lihu/images/ico28.png" alt="swich" />
							</label><input type="checkbox"  name="check" value="<%=educl.get(i).getId()%>"  />
							</div>
							<dl>
								<dt class="inner clearfix">
									<a href="getWebCommments?merchantId=<%=edusl.get(i).getMerchantId()%>">
										<div class="imgArea"><img src="<%=request.getContextPath() %>/sample_lihu/images/xw_15.png" alt="" /></div>
										<div class="infoArea">
											<h3><%=edusl.get(i).getName() %></h3>
											<p class="rate clearfix"><span class="star_on"></span><span class="star_on"></span><span class="star_off">
											</span><span class="star_off"></span><span class="star_off"></span></p>
											<div class="clearfix"><strong><%=edusl.get(i).getCity() %></strong><span><%=edusl.get(i).getClassFirst()%></span><div class="distance">8000m</div></div>
										</div>
									</a>
								</dt>
								<dd class="reply">
									<div class="inner clearfix">
										<div class="leftArea"><img src="<%=request.getContextPath()+((User)session.getAttribute("u")).getImg() %>" alt="sampleuser" /></div>
										<div class="rightArea">
											<div class="name"><%=((User)session.getAttribute("u")).getNickname() %>
											
											<p class="rate">
											
											<span class="star_on"></span>	
											<span class="star_on"></span>
											<span class="star_off"></span>
											<span class="star_off"></span>
											<span class="star_off"></span>
											
											
											</p></div>
											<p class="time"><%=educl.get(i).getTime() %></p>
											<p class="detail"><%=educl.get(i).getContent()%></p>
										</div>
									</div>
								</dd>
							</dl>
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

var ul1=(function(){
	$("#wrap input").attr({"checked" : false}).siblings("label").find("img").attr("src","<%=request.getContextPath() %>/sample_lihu/images/ico28.png");

	//alert(1);
	$("#divmainList").show();
	$("#divforum").hide();
	$("#divlistPack").hide();
	$("#ul1").show();
	$("#ul2").hide();
	$("#ul3").hide();
	$("#li2").addClass("container");
	//container .pagging ul li
	$(".checkArea").hide();
	$(".favorDel").hide();
	$("#favorEdit").text("编辑");
	
	$("#container .myFavor li").css("padding-left","0px");
	$("#container").css("padding-bottom","0px");
	
	
	
});

var ul2=(function(){
	$("#wrap input").attr({"checked" : false}).siblings("label").find("img").attr("src","<%=request.getContextPath() %>/sample_lihu/images/ico28.png");

	$("#divmainList").hide();
	$("#divforum").show();
	$("#divlistPack").hide();
	$("#ul2").show();
	$("#ul1").hide();
	$("#ul3").hide();
	$(".checkArea").hide();
	$(".favorDel").hide();
	$("#favorEdit").text("编辑");
	$("#container .myFavor li").css("padding-left","0px");
	$("#container").css("padding-bottom","0px");
	
	
});
var ul3=(function(){
	$("#wrap input").attr({"checked" : false}).siblings("label").find("img").attr("src","<%=request.getContextPath() %>/sample_lihu/images/ico28.png");

	$("#divmainList").hide();
	$("#divforum").hide();
	$("#divlistPack").show();
	$("#ul3").show();
	$("#ul2").hide();
	$("#ul1").hide();
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
        //alert(text);
     	if(text.length>1){
        if(confirm("确定删除？")){
		 	 
   		 
   		 $("input[name=check]").each(function() {  
   			 if ($(this).attr("checked")) {
   				// alert($(this).val());
	                $("#"+$(this).val()).css("display","none");
	                $(this).attr({"checked":false})
	            }
   	    
   	        });
   		 //调用新闻
         if($("#divmainList").is(":visible")){
        	 $.ajax({
					type:"post",
					url:"webRemoveReviews.action?content=N&fatherId="+text
				});
         }
       //调用论坛
 		if($("#divforum").is(":visible")){
 			
 			$.ajax({
				type:"post",
				url:"webRemoveReviews.action?content=F&fatherId="+text
			});
         }
 		//调用教辅机构
 		if($("#divlistPack").is(":visible")){
 			 $.ajax({
    	  			type:"post",
    	  			url:"webRemoveReviews.action?content=E&fatherId="+text
    	  		});
         	
         }
 		$("input[name=check]").each(function() {  
 			 if ($(this).attr("checked")) {
 				// alert($(this).val());
	                //$("#"+$(this).val()).css("display","none");
	                $(this).attr({"checked":false});
	            }
 	    
 	        });
   			//alert("删除成功！");
   	     	
   	  }else{
   			alert("取消成功！");
   			
   			$("input[name=check]").each(function() {  
      			 if ($(this).attr("checked")) {
      				// alert($(this).val());
   	                //$("#"+$(this).val()).css("display","none");
   	                $(this).attr({"checked":false});
   	            }
      	    
      	        });
   			
  		 }
        
        
        
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
	var divid="";
	 //调用新闻
    if($("#divmainList").is(":visible")){
    	divid="divmainList";
    }
  //调用论坛
	if($("#divforum").is(":visible")){
		
		divid="divforum";
    }
	//调用教辅机构
	if($("#divlistPack").is(":visible")){
		divid="divlistPack";
    	
    }
	//alert("#"+divid+" input");
	var favorCheck =$("#"+divid+" input").attr("checked");
 
	
	
	if(favorCheck){
		$("#"+divid+" input").attr({"checked" : false}).siblings("label").find("img").attr("src","<%=request.getContextPath() %>/sample_lihu/images/ico28.png");

	}else{
		$("#"+divid+" input").attr({"checked" : true}).siblings("label").find("img").attr("src","<%=request.getContextPath() %>/sample_lihu/images/ico29.png");
		
	}
	
});

</script>
</body>
</html>
