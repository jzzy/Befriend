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
<link href="<%=request.getContextPath() %>/sample_lihu/css/style.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" href="<%=request.getContextPath() %>/sample_lihu/css/pullToRefresh.css"/>
<script type="text/javascript" src="<%=request.getContextPath() %>/sample_lihu/js/jquery-1.10.2.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/sample_lihu/js/common.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/sample_lihu/js/jquery.lazyload.js"></script>
<script type="text/javascript" charset="utf-8">
      $(function() {
    	 
          $("img").lazyload({ 
          placeholder : "<%=request.getContextPath() %>/sample_lihu/images/listBanner.png",
                effect: "fadeIn",
                threshold : 20000
                 
           });  
      });
</script>
<script type="text/javascript">
$(window).scroll(function () {
    if ($(document).scrollTop() + $(window).height() >= $(document).height()) {
        $("#loding").slideDown(300).delay(1000).slideUp(300);
        
        $.ajax({
			url:"/Befriend/webForumApptypeToJson?currentPage="+$("#divc").html(),
			 dataType: "json", 
					async:false,
					success: function (data) {     
						var val = data["fones"];//获取json中的 key
						var vus = data["us"];
						var el, li, i,str;
						el =document.querySelector("#wrapper ul");
						var arr=eval(val);
						var us=eval(vus);
						for(var i=0;i<arr.length;i++)
						{
						    //alert(arr[i].title);

								li = document.createElement('li');
								str='<div class="user clearfix"><p class="leftArea">';
								if(us[i].img==null){
									str+='<img src="<%=request.getContextPath() %>/sample_lihu/images/testuser_ico.gif" alt="sample" />';
									
								}else{
									str+='<img src="<%=request.getContextPath() %>'+us[i].img+'" alt="sample" />';
								}
								
								str+='</p><p class="rightArea"><span>'+us[i].nickname+'</span><span>'+arr[i].time+'</span></p></div>';
								str+='<div class="title"><a href=<%=request.getContextPath() %>/webForumLook?id='+arr[i].id+' target="_parent">'+arr[i].title+'</a>';
								str+='</div><div class="detail"><a href=<%=request.getContextPath() %>/webForumLook?id='+arr[i].id+' target="_parent">'+arr[i].content+'</a>';
								str+='</div>';
								if(arr[i].img!=null){
		
									str+='<div class="imgList clearfix"><p><a  target="_parent"><img src="<%=request.getContextPath() %>/sample_lihu/images/listBanner.png" data-original="<%=request.getContextPath() %>'+arr[i].img+'" alt="sample" /></a></p></div>';
								}
								
								str+='<div class="info"><span><img src="<%=request.getContextPath() %>/sample_lihu/images/comment_ico.gif" alt="留言" />';
								str+='&nbsp;'+arr[i].frs+'</span><span><img src="<%=request.getContextPath() %>/sample_lihu/images/favor2_ico.gif" alt="关注" />&nbsp;'+arr[i].fHits+'</span></div>';
								//alert(str);
								li.innerHTML=str;
								el.appendChild(li, el.childNodes[0]);
								
						}
						$("#divc").html(parseInt($("#divc").html())+1);
					
					
						},
					
		})
        
        
    }
});
</script>
</head>
<body>
	<div id="wrap">
		<div id="header">
			<div class="topArea clearfix">
				<h1><a href="<%=request.getContextPath() %>/webNewsA10"><img src="<%=request.getContextPath() %>/sample_lihu/images/logo.gif" alt="家长之友" /></a></h1>
				<div class="relocation">
					<a href="<%=request.getContextPath() %>/sample_lihu/jsp/relocation.jsp"><%=session.getAttribute("province") %>&nbsp;<img src="<%=request.getContextPath() %>/sample_lihu/images/location_more.gif" alt="down" /></a>
				</div>
				<div class="topTool">
					<span><a href="<%=request.getContextPath() %>/sample_lihu/htm/post4.html">发表</a></span>
				</div>
			</div>
			<ul class="topMenu clearfix">
				<li><a href="<%=request.getContextPath() %>/webNewsA10"><img src="<%=request.getContextPath() %>/sample_lihu/images/hottoday_ico.gif" alt="fire" />&nbsp;今日必读</a></li>
				<li class="on"><a href="<%=request.getContextPath() %>/sample_lihu/jsp/forum2.jsp">社区互动</a></li>
				<li><a href="<%=request.getContextPath() %>/webGetapp">贴心服务</a></li>
				<li><a href="<%=request.getContextPath() %>/sample_lihu/jsp/mypage.jsp">我的信息</a></li>
			</ul>
			<div class="forum">
				<h2 class="tit2"><img src="<%=request.getContextPath() %>/sample_lihu/images/forum_tit.gif" alt="全国论坛讨论社区" /></h2>
			</div>
		</div><!--header-->
		
		<div id="container">
		<div class="forum">
			<div id="wrapper">
			 
				<ul>
				 <%
                    for(int i=0;i<fone.size();i++){
                    	System.out.println(us.get(i));
                    	System.out.println(fone.get(i));
                    	if(us.get(i)==null||fone.get(i)==null){
                    		continue;
                    	}
                    %>
					<li>
						<div class="user clearfix">
							<p class="leftArea">
								<%
						if(us.get(i).getImg()!=null){
						%>
							<img src="<%=request.getContextPath()+us.get(i).getImg() %>" alt="sample" />
							<%}else{ %>
								<img src="<%=request.getContextPath() %>/sample_lihu/images/testuser_ico.gif" alt="sample" />
							<%
							}
							%>
							</p>
							<p class="rightArea">
								<span><%=us.get(i).getNickname() %></span>
								<span><%=fone.get(i).getTime()%></span>
							</p>
						</div>
						<div class="title"><a href="webForumLook?id=<%=fone.get(i).getId()%>" target="_parent"><%=fone.get(i).getTitle() %></a></div>
						<div class="detail"><a href="webForumLook?id=<%=fone.get(i).getId()%>" target="_parent"><%=fone.get(i).getContent() %></a></div>
						<%
						if(fone.get(i).getImg()!=null){
						%>
						<div class="imgList clearfix">
						
							<p><a href="webForumLook?id=<%=fone.get(i).getId()%>" target="_parent">
						
							<img  src="<%=request.getContextPath() %>/sample_lihu/images/listBanner.png" data-original="<%=request.getContextPath()+fone.get(i).getImg() %>" alt="sample" />
							
							
						
							</a></p>
						</div>
						<%
						}
						%>
						<div class="info">
							<span><img src="<%=request.getContextPath() %>/sample_lihu/images/comment_ico.gif" alt="留言" />&nbsp;<%=fone.get(i).getFrs()%></span>
							<span><img src="<%=request.getContextPath() %>/sample_lihu/images/favor2_ico.gif" alt="关注" />&nbsp;<%=fone.get(i).getfHits()%></span>
						</div>
						<span></span>
					</li>
					
					
					<%
                    }
					%>
				</ul>
			</div>
		</div>
	</div>
		
		
		<div id=divc style="display: none;">2</div>
			<div id="loding"><img src="<%=request.getContextPath() %>/sample_lihu/images/123.gif" alt="loding_ico" /></div><!--loding-->
	</div><!--wrap-->
	
	
	
</body>
</html>
