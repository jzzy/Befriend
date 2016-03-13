<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    <%@page import="java.util.*"%>
    <%@page import="com.befriend.util.*"%>
<%@page import="com.befriend.entity.*"%>
<%
//获取论坛
List<ForumOne> fone=(List)request.getAttribute("fones");
//获取论坛最新回复
List<ForumTwo> ftwo=(List)request.getAttribute("ftwos");
//获取论坛创建者
List<User> us=(List)request.getAttribute("us");
//获取共多少页
int cpe=(Integer)request.getAttribute("cpe");
Object area=request.getAttribute("area");
//获取类型
int id=(Integer)request.getAttribute("id");
id=0;
String type="全国论坛";
//获取当前页数
int currentPage=(Integer)request.getAttribute("currentPage");
if(fone==null||us==null){
	%>
	<p>没有信息</p>
	<%
	return;
}
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no"  />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title></title>
<script type="text/javascript" src="<%=request.getContextPath() %>/sample_lihu/js/jquery-1.10.2.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/sample_lihu/js/common.js"></script>
<link href="<%=request.getContextPath() %>/sample_lihu/css/style.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" href="<%=request.getContextPath() %>/sample_lihu/css/pullToRefresh.css"/>
</head>
<body>
	<div id="container">
		<div class="forum">
			<div id="wrapper">
			  <%
                    for(int i=0;i<fone.size();i++){
                    	System.out.println(us.get(i));
                    	System.out.println(fone.get(i));
                    	if(us.get(i)==null||fone.get(i)==null){
                    		continue;
                    	}
                    %>
				<ul>
					<li>
						<div class="user clearfix">
							<p class="leftArea"><img src="<%=request.getContextPath() %>/sample_lihu/images/testuser_ico.gif" alt="sample" /></p>
							<p class="rightArea">
								<span><%=us.get(i).getNickname() %></span>
								<span>2016-01-15 09:54</span>
							</p>
						</div>
						<div class="title"><a href="webForumLook?id=<%=fone.get(i).getId()%>" target="_parent"><%=fone.get(i).getTitle() %></a></div>
						<div class="detail"><a href="webForumLook?id=<%=fone.get(i).getId()%>" target="_parent"><%=fone.get(i).getContent() %></a></div>
						<div class="imgList clearfix">
							<p><a href="webForumLook?id=<%=fone.get(i).getId()%>" target="_parent">
						
							<img src="	<%=request.getContextPath() %>/sample_lihu/images/listBanner.jpg" alt="sample" />
							
							
						
							</a></p>
							<p><a href="webForumLook?id=<%=fone.get(i).getId()%>" target="_parent">
						
							<img src="	<%=request.getContextPath() %>/sample_lihu/images/listBanner.jpg" alt="sample" />
							
							
						
							</a></p>
							<p><a href="webForumLook?id=<%=fone.get(i).getId()%>" target="_parent">
						
							<img src="	<%=request.getContextPath() %>/sample_lihu/images/listBanner.jpg" alt="sample" />
							
							
						
							</a></p>
						</div>
						<div class="info">
							<span><img src="<%=request.getContextPath() %>/sample_lihu/images/comment_ico.gif" alt="留言" />&nbsp;520</span>
							<span><img src="<%=request.getContextPath() %>/sample_lihu/images/favor2_ico.gif" alt="关注" />&nbsp;120</span>
						</div>
					</li>
					
					
					<%
                    }
					%>
				</ul>
			</div>
		</div>
	</div>
	<div id=divc style="display: none;">2</div>
<script src="<%=request.getContextPath() %>/sample_lihu/js/iscroll.js"></script>
<script src="<%=request.getContextPath() %>/sample_lihu/js/pullToRefresh.js"></script>
<script type="text/javascript">
refresher.init({
	id:"wrapper",//<------------------------------------------------------------------------------------
	pullDownAction:Refresh,                                                            
	pullUpAction:Load 																			
	});																																							
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
		
		$.ajax({
			url:"/Befriend/webForumApptypeToJson?currentPage="+$("#divc").html(),
			 dataType: "json", 
					async:false,
					success: function (data) {     
						var val = data["fones"];//获取json中的 key
						var vus = data["us"];
						var el, li, i;
						el =document.querySelector("#wrapper ul");
						var arr=eval(val);
						var us=eval(vus);
						for(var i=0;i<arr.length;i++)
						{
						    //alert(arr[i].title);

								li = document.createElement('li');
								li.innerHTML='<div class="user clearfix"><p class="leftArea"><img src="<%=request.getContextPath() %>/sample_lihu/images/testuser_ico.gif" alt="sample" /></p><p class="rightArea"><span>'+us[i].nickname+'</span><span>2016-01-15 09:54</span></p></div><div class="title"><a href=<%=request.getContextPath() %>/webForumLook?id='+arr[i].id+' target="_parent">'+arr[i].title+'</a></div><div class="detail"><a href=<%=request.getContextPath() %>/webForumLook?id='+arr[i].id+' target="_parent">'+arr[i].content+'</a></div><div class="imgList clearfix"><p><a href="forumreview.html" target="_parent"><img src="<%=request.getContextPath() %>/sample_lihu/images/listBanner.jpg" alt="sample" /></a></p><p><a href="forumreview.html" target="_parent"><img src="<%=request.getContextPath() %>/sample_lihu/images/listBanner.jpg" alt="sample" /></a></p><p><a href="forumreview.html" target="_parent"><img src="<%=request.getContextPath() %>/sample_lihu/images/listBanner.jpg" alt="sample" /></a></p></div><div class="info"><span><img src="<%=request.getContextPath() %>/sample_lihu/images/comment_ico.gif" alt="留言" />&nbsp;520</span><span><img src="<%=request.getContextPath() %>/sample_lihu/images/favor2_ico.gif" alt="关注" />&nbsp;120</span></div>';
								el.appendChild(li, el.childNodes[0]);

						}
						$("#divc").html(parseInt($("#divc").html())+1);
					
					
						},
					
		})
			
		
	
		
		
		wrapper.refresh();/****remember to refresh after action completed！！！   ---id.refresh(); --- ****/
	},1000);	
}
</script>
</body>
</html>