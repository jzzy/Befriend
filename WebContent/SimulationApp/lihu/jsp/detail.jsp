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
<title>教辅机构</title>
<script type="text/javascript" src="<%=request.getContextPath() %>/SimulationApp/lihu/js/jquery.js"></script>
<link href="<%=request.getContextPath() %>/SimulationApp/lihu/css/style.css" rel="stylesheet" type="text/css" />

</head>
<body>
<div class="cont">
	<div id="header">
		<div class="topArea clearfix">
			<div class="prev">
				<a href="javascript:history.back(-1);"></a>
			
			</div>
			<div class="topTool">
			
				<span id="share"><img src="<%=request.getContextPath() %>/SimulationApp/lihu/images/share.png" alt="分享" /></span>
				<span><a href="GaoDeFujin"><img src="<%=request.getContextPath() %>/SimulationApp/lihu/images/dot3.png" alt="附近美食" /></a></span>
		
				
			</div>
		</div>
	</div>
	<%
	List<EduComment> educl=(List)request.getAttribute("educl");
	EduServices edus=(EduServices)request.getAttribute("edus");
	%>
	<div id="container">
		<div class="detail">
			<div class="clearfix">
				<div class="imgArea">
					<img src=<%="http://182.92.100.235/"+edus.getPicture() %> alt="" />
				</div>
				<div class="infoArea">
					<p class="name"><%=edus.getName() %></p>
					<p class="rate">
					<%
							for(int g=0;g<edus.getStar();g++){
							%>
					<img src="<%=request.getContextPath() %>/SimulationApp/lihu/images/detail_star_on.png" alt="" />
					<%
							}
					%>
					
					</p>
					<p class="point"><span>设施:<%=edus.getEnvScore() %></span><span>环境:<%=edus.getSerScore() %></span><span>服务:<%=edus.getStar() %></span></p>
				</div>
			</div>
			<p class="adress"><a class="clearfix" href="daohang?lng=<%=edus.getLongitude() %>&lat=<%=edus.getLatitude() %>"><%=edus.getAddress() %><img src="<%=request.getContextPath() %>/SimulationApp/lihu/images/grayarrow.gif" alt="" /></a></p>
			<p class="tel"><a class="clearfix" href="tel:<%=edus.getTelephone().split(",")[0] %>"><%=edus.getTelephone().split(",")[0] %><img src="<%=request.getContextPath() %>/SimulationApp/lihu/images/grayarrow.gif" alt="" /></a></p>
		</div>
		<div class="review">
			<h2><a href="#">网友点评<img src="<%=request.getContextPath() %>/SimulationApp/lihu/images/grayarrow.gif" alt="ico" /></a></h2>
			<ul>
			<%
			for(int i=0;i<educl.size();i++){
			%>
				<li>
					<div class="inner clearfix">
						<div class="userImg"><img src="<%=request.getContextPath() %><%=educl.get(i).getUser().getImg()==null?"http://182.92.100.235/Befriend/SimulationApp/images/logod.png":educl.get(i).getUser().getImg() %>" alt="ico" /></div>
						<div class="message">
							<p class="userId"><%=educl.get(i).getUser().getNickname() %><span  class="rate">
							<%
							for(int g=0;g<educl.get(i).getScore();g++){
							%>
							<img  src="<%=request.getContextPath() %>/SimulationApp/lihu/images/detail_star_on.png" alt="" />
						<%
							}
						%>
							
							</span>
							</p>
							<p class="words">
							<%=educl.get(i).getContent() %>
							</p>
							<p class="attachImg">
							<%
							if(!OpeFunction.isEmpty(educl.get(i).getPictures())){
								
							
							String s = new String(educl.get(i).getPictures());   
					        String a[] = s.split("!#");  
					        for(int y=0;y<a.length;y++){
								// System.out.println("i:"+i+"y:"+y+":"+a[y]);
							
			
							//http://182.92.100.235
							//http://123.56.45.164
							
							if(y<6){
							%>
							<img src="<%=a[y] %>" alt="pic" />
							<%
							}
					        }
							}
							%>
							</p>
							<p class="time"><%=educl.get(i).getTime() %></p>
						</div>
					</div>
				</li>
				<%} %>
			
			</ul>
		</div>
		<div class="shopInfo">
			<h2><a href="#">商户信息<img src="<%=request.getContextPath() %>/SimulationApp/lihu/images/grayarrow.gif" alt="ico" /></a></h2>
			<p class="time">营业时间&nbsp;:&nbsp;尚无营业时间</p>
			<p class="tel">联系电话&nbsp;:&nbsp;<a class="clearfix" href="tel:<%=edus.getTelephone().split(",")[0] %>"><%=edus.getTelephone().split(",")[0] %><a></a></p>
			<p class="shopName">商户名称&nbsp;:&nbsp;<%=edus.getName() %></p>
		</div>
		<!--cont-->
		
		<div class="nearby">
			<h2>在这家店附近找</h2>
			<ul class="clearfix">
				<li><a href="GaoDeFujin?type=美食&lng=<%=edus.getLongitude() %>&lat=<%=edus.getLatitude() %>"><img src="<%=request.getContextPath() %>/SimulationApp/lihu/images/nearby1.gif" alt="" /></a></li>
				<li><a href="GaoDeFujin?type=娱乐&lng=<%=edus.getLongitude() %>&lat=<%=edus.getLatitude() %>"><img src="<%=request.getContextPath() %>/SimulationApp/lihu/images/nearby2.gif" alt="" /></a></li>
				<li><a href="GaoDeFujin?type=酒店&lng=<%=edus.getLongitude() %>&lat=<%=edus.getLatitude() %>"><img src="<%=request.getContextPath() %>/SimulationApp/lihu/images/nearby3.gif" alt="" /></a></li>
				<li class="clear"><a href="GaoDeFujin?type=景点&lng=<%=edus.getLongitude() %>&lat=<%=edus.getLatitude() %>"><img src="<%=request.getContextPath() %>/SimulationApp/lihu/images/nearby4.gif" alt="" /></a></li>
				<li><a href="GaoDeFujin?type=购物&lng=<%=edus.getLongitude() %>&lat=<%=edus.getLatitude() %>"><img src="<%=request.getContextPath() %>/SimulationApp/lihu/images/nearby5.gif" alt="" /></a></li>
				<li><a href="GaoDeFujin?type=停车&lng=<%=edus.getLongitude() %>&lat=<%=edus.getLatitude() %>"><img src="<%=request.getContextPath() %>/SimulationApp/lihu/images/nearby6.gif" alt="" /></a></li>
			</ul>
		</div>

	</div>
	
	<div id="footer">
		<ul class="clearfix">
			<li><a href="userLookEduASBK"><img src="<%=request.getContextPath() %>/SimulationApp/lihu/images/shoucang.png" alt="我的收藏" /></a></li>
			<li><a href="<%=request.getContextPath() %>/SimulationApp/lihu/comment.html"><img src="<%=request.getContextPath() %>/SimulationApp/lihu/images/dianping.png" alt="我要点评" /></a></li>
		</ul>
	</div>
   <!--userlist-->
   <div id="sharing">
   
   
  
  
  
  
   
		<div class="inner">
			<ul class="clearfix">
			
			
			<script type="text/javascript">
(function(){
var p = {
url:location.href, /*获取URL，可加上来自分享到QQ标识，方便统计*/
desc:'', /*分享理由(风格应模拟用户对话),支持多分享语随机展现（使用|分隔）*/
title:'', /*分享标题(可选)*/
summary:'', /*分享摘要(可选)*/
pics:'', /*分享图片(可选)*/
flash: '', /*视频地址(可选)*/
site:'', /*分享来源(可选) 如：QQ分享*/
style:'201',
width:32,
height:32
};
var s = [];
for(var i in p){
s.push(i + '=' + encodeURIComponent(p[i]||''));
}
document.write(['<a  href="http://connect.qq.com/widget/shareqq/index.html?',s.join('&'),'" target="_blank"><li><img src="<%=request.getContextPath() %>/SimulationApp/lihu/images/fx3.png" alt="QQ好友" /><span>QQ好友</span></li></a>'].join(''));
})();
</script>
<script src="http://connect.qq.com/widget/loader/loader.js" widget="shareqq" charset="utf-8"></script>



 <script type="text/javascript">
(function(){
var p = {
url:location.href,
showcount:'1',/*是否显示分享总数,显示：'1'，不显示：'0' */
desc:'',/*默认分享理由(可选)*/
summary:'',/*分享摘要(可选)*/
title:'',/*分享标题(可选)*/
site:'',/*分享来源 如：腾讯网(可选)*/
pics:'', /*分享图片的路径(可选)*/
style:'201',
width:113,
height:39
};
var s = [];
for(var i in p){
s.push(i + '=' + encodeURIComponent(p[i]||''));
}
document.write(['<a version="1.0"  href="http://sns.qzone.qq.com/cgi-bin/qzshare/cgi_qzshare_onekey?',s.join('&'),'" target="_blank"><li><img src="<%=request.getContextPath() %>/SimulationApp/lihu/images/fx4.png" alt="QQ空间" /><span>QQ空间</span></li></a>'].join(''));
})();
</script>
<script src="http://qzonestyle.gtimg.cn/qzone/app/qzlike/qzopensl.js#jsdate=20111201" charset="utf-8"></script>	
	
			<!-- 
				<li><img src="<%=request.getContextPath() %>/SimulationApp/lihu/images/fx1.png" alt="微信" /><span>微信好友</span></li>
				<li><img src="<%=request.getContextPath() %>/SimulationApp/lihu/images/fx2.png" alt="朋友圈" /><span>朋友圈</span></li>
				
				
				<li><img src="<%=request.getContextPath() %>/SimulationApp/lihu/images/fx5.png" alt="微信收藏" /><span>微信收藏</span></li>
				<li><img src="<%=request.getContextPath() %>/SimulationApp/lihu/images/fx6.png" alt="QQ收藏" /><span>QQ收藏</span></li>
				 -->
			</ul>
			
			
				<!-- JiaThis Button BEGIN -->
				<!--  
				<div id="ckepop">
<span class="jiathis_txt">分享到：</span>
<a class="jiathis_button_weixin">微信</a> 
<a href="http://www.jiathis.com/share"  class="jiathis jiathis_txt jiathis_separator jtico jtico_jiathis" target="_blank">更多</a>
<a class="jiathis_counter_style"></a> </div> 
<script type="text/javascript" src="http://v3.jiathis.com/code/jia.js?uid=1" charset="utf-8"></script>
</div> 
-->
<!-- JiaThis Button END -->

		</div>
		<p class="shareCan">取消分享</p>
	</div><!--sharing-->
	
	<div id="bg">bg</div>
</div>


</body>
<script type="text/javascript">

$("#share").click(function(){
	$("#bg").show();
	$("#sharing").show();
});
$(".shareCan").click(function(){
	$("#bg").hide();
	$("#sharing").hide();
})
$("#bg").click(function(){
	$("#bg").hide();
	$("#sharing").hide();
})

</script>
</html>

