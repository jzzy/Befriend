<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    <%@page import="java.util.*"%>
    <%@page import="com.befriend.util.*"%>
<%@page import="com.befriend.entity.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no"  />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title></title>
<link href="sample_lihu/css/style.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" href="sample_lihu/css/pullToRefresh.css"/>
<script type="text/javascript" src="sample_lihu/js/jquery-1.10.2.min.js"></script>
<script type="text/javascript" src="sample_lihu/js/common.js"></script>
<script type="text/javascript">
function  checkvalue()
{	 
document.form1.action="<%=request.getContextPath() %>/getLikeEduWebArea";
document.form1.submit();
//alert('已提交');
return true;

}
</script>
</head>
<body>
<form name="form1" action="getLikeEduWebArea">
	<div id="wrap">
		<div id="header">
			<div class="topArea clearfix">
				<div class="search_input2">
					<div class="inner">
						<input type="text" id="search2" name="value"  value="<%=request.getAttribute("value") %>"/><a  onClick="javascript:return checkvalue();" class="search_btn2"><img src="sample_lihu/images/ico07.gif" alt="确认搜索" /></a></span>
					</div>
				</div>
				<div class="prev">
					<a href="sample_lihu/jsp/edu_search.jsp"><img src="sample_lihu/images/prev.png" alt="后退" /></a>
				</div>
			</div>
			<ul class="navi clearfix" id="jMenu">
				<%
				List<Areas> lar=(List)(request.getAttribute("lar")==null?new ArrayList<Areas>():request.getAttribute("lar")); 			
				List<Cities> lcit=(List)(request.getAttribute("lcit")==null?new ArrayList<Cities>():request.getAttribute("lcit")); 			

				%>
				<li class="district">
					<a  class="fNiv">全部地区&nbsp;<img src="sample_lihu/images/arrow_off.png" width="20" height="10" alt="" /></a>
					<ul>
							<%
					if(lar.size()==0){
					%>
					<li><a href="getLikeEduWebArea?city=all"><%=session.getAttribute("province") %></a></li>
					<%
					}else{
					%>
					<li><a href="getLikeEduWebArea?county=all"><%=session.getAttribute("province") %></a></li>
					<%
					}
					%>
						<%
						for(int i=0;i<lar.size();i++){
							
						
						%>
						<li><a href="getLikeEduWebArea?county=<%=lar.get(i).getArea().substring(0, lar.get(i).getArea().length()-1) %>&province=<%=session.getAttribute("province")==null||session.getAttribute("province").equals("null")?"北京":session.getAttribute("province")%>"><%=lar.get(i).getArea() %></a></li>
					
						<%
			
						}
						%>
						
						
							<%
						for(int i=0;i<lcit.size();i++){
							
						
						%>
						<li><a href="getLikeEduWebArea?city=<%=lcit.get(i).getCity().substring(0, lcit.get(i).getCity().length()-1) %>&province=<%=session.getAttribute("province")==null||session.getAttribute("province").equals("null")?"北京":session.getAttribute("province")%>"><%=lcit.get(i).getCity() %></a></li>
					
						<%
						}
						%>
					</ul>
				</li>
				<li class="edutrain">
					<a  class="fNiv">教育培训&nbsp;<img src="sample_lihu/images/arrow_off.png" width="20" height="10" alt="" /></a>
				
						<ul>
						<li><a href="getLikeEduWebArea?classSecond=all">不限</a></li>
						<li><a href="getLikeEduWebArea?classSecond=外语">外语</a></li>
						<li><a href="getLikeEduWebArea?classSecond=音乐">音乐</a></li>
						<li><a href="getLikeEduWebArea?classSecond=幼儿教育">幼儿教育</a></li>
						<li><a href="getLikeEduWebArea?classSecond=升学辅导">升学辅导</a></li>
						<li><a href="getLikeEduWebArea?classSecond=教育院校">教育院校</a></li>
						<li><a href="getLikeEduWebArea?classSecond=舞蹈">舞蹈</a></li>
						<li><a href="getLikeEduWebArea?classSecond=学校">学校</a></li>
						<li><a href="getLikeEduWebArea?classSecond=驾校">驾校</a></li>
						<li><a href="getLikeEduWebArea?classSecond=兴趣生活">兴趣生活</a></li>
							<li><a href="getLikeEduWebArea?classSecond=职业技术">职业技术</a></li>
						<li><a href="getLikeEduWebArea?classSecond=体育">体育</a></li>
						<li><a href="getLikeEduWebArea?classSecond=美术">美术</a></li>
						
					</ul>
				</li>
				<li class="sort fNiv">
					<a  class="fNiv">智能排序&nbsp;<img src="sample_lihu/images/arrow_off.png" width="20" height="10" alt="" /></a>
					<ul>
						<li><a href="getLikeEduWebArea?sortType=1">离我最近的商户</a></li>
						<li><a href="getLikeEduWebArea?sortType=2">人气最高的商户</a></li>
						<li><a href="getLikeEduWebArea?sortType=5">评价最好的商户</a></li>
						<li><a href="getLikeEduWebArea?sortType=3">人均最低的商户</a></li>
						<li><a href="getLikeEduWebArea?sortType=4">人均最高的商户</a></li>
					</ul>
				</li>
			</ul>
		</div><!--header-->
		<div id="container" class="load">
			<iframe id="mainiframe" src="getLikeEduWeb<%=request.getAttribute("value") %>" frameborder="0" scrolling="no" width="100%"></iframe>
		</div><!--container-->
		<div id="footer"></div><!--footer-->
		<div id="bg"></div>
	</div><!--wrap-->
<script type="text/javascript" src="sample_lihu/js/jMenu.jquery.js"></script>
<script type="text/javascript">
  $(document).ready(function(){
    $("#jMenu").jMenu();
  })
</script>
</form>
</body>
</html>