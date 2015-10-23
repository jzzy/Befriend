<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@page import="java.util.*"%>
    <%@page import="com.befriend.util.*"%>
<%@page import="com.befriend.entity.News"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>查看文章</title>
</head>
<script type="text/javascript" src="/Befriend/Jquery/jquery-2.1.3.min.js"></script>
<script type="text/javascript" language="javascript">

function cka()
{
 if(confirm("确定放弃当前操作返回主页？"))
 {
	
  return true;
  
 }
 else{
	   return false;
 }

}
  function ck()
  {
   if(confirm("确定要删除新闻么？"))
   {
	  
    return true;
    
    
   }
   else{
	   return false;
   }
  
  }
  function k(picForm){
	  
	  if(confirm("确定要修改新闻么？ 该功能尚未推出！请稍后"))
	   {
		  this.picForm.style.display="none";
			
	    return true;
	   }
	   else{
		   return false;
	   }
	
  }
  
  $(document).ready(function(){

	  $("#b01").click(function(){
		  
	  htmlobj=$.ajax({url:"/Befriend/searchNews?title="+$("#txt").val(),async:true});
	  $("#myDiv").html(htmlobj.responseText);
	  });
	});


 </script>
<body>
<div id="myDiv" style="background: #ada;">
<h2><a href="Newsget?tp=1">查看已经发布新闻</a><br><br><a href="Newsget">查看未发布新闻</a><br></h2>

<form  action="searchNews" method="post">
<input id="txt" type="text" name="title"/>
<input type="submit" style="width: 100px;height: 25px;" value="搜索"/>
</form>

<table>
		
		<td width="320"><h4>文章名字</h4></td>
		<td width="177"><h4>文章ID</h4></td>
		<td width="163"><h4>文章标识</h4> </td>
		<td width="177"><h4>上传者ID</h4> </td>
		<td  width="163"><h4>上传日期</h4></td>
		<td  width="163"><h4>是否删除</h4></td>
		<td  width="163"><h4>保存修改</h4></td>
		
		
		
		</table> 

		<%
	
	List<List> l=(List)request.getAttribute("l");
	
	 int i=0;
	String ty="";
	for(;i<l.size();i++){
	    
	    News a= (News) l.get(i);
	    
	  if(a.getType()!=null&&a.getType()!=""){
	      if(a.getType().equals("升学指南")){
		  
		   ty= a.getType()+" "+a.getTypes();
		    }
		else{
		    ty= a.getType();
		}
		    }else{
			
			if(a.getAreas()!=null&&a.getAreas()!=""&&a.getArea()!=null&&a.getArea()!=""){
			    
			ty=a.getArea()+" "+a.getAreas();
			
		    }
			else if(a.getAreas()==null||a.getAreas()=="")
			{
			    
			if(a.getArea()!=null&&a.getArea()!=""){
			ty=a.getArea()+" 缺少市级";
			}else{
			    ty="缺少省级"+" 缺少市级";
			}
			
		    }else if(a.getArea()==null||a.getArea()==""){
			if(a.getAreas()!=null&&a.getAreas()!=""){
				ty="缺少省级 "+a.getAreas();
				}else{
				    ty="缺少省级"+"  缺少市级";
				}
		    }
			
		    }
	
		 
		
	%>
		<form id="<%=i %>" action="" name="picForm">
		<table>
		<tr>
		
		<td width="330"><a  class="pic" href="<%=request.getContextPath()%>/adminNewsId?id=<%=a.getId()%>"><%=a.getTitle()%></a></td>
		<td width="167""><%=a.getId()%></td>
		<td width="180"><%=ty %> </td>
		<td width="130"><%=a.getAdmin()%> </td>
		<td width="210"><%=a.getTime()%></td>
		
		<td width="150"><a href="NewsRM?newsid=<%=a.getId() %>" onclick="return ck()">删除</a></td>
		<td  width="163"><h4 ><a href="adminNewsById?newsid=<%=a.getId() %>"><input type="button" value="修改" ></a></h4></td>
		</tr>
		<hr>
		
		</table> 
		</form>
		<form id="f2<%=i %>" action="" style="display: none;">
		<table>
		<tr>
		
		<td width="330"><input type="text" value="<%=a.getTitle()%>" style="height:25px;width:330px;"></td>
		<td width="162""><%=a.getId()%></td>
		<td width="180"><%=ty %> </td>
		<td width="130"><%=a.getAdmin()%> </td>
		<td width="210"><input type="text" value="<%=a.getTime()%>"style="height:25px;width:145px;"></td>
		<td width="160"></td>
		<td><input type="submit" value="保存"></td>
		</tr>
		<hr>
		
		</table> 
		</form>
		<%
		
		
	}	
	
	
	
		%>

		<%
    	int currentPage=1;
    	int n=0;
    	
    	currentPage=Integer.parseInt((request.getAttribute("currentPage")==null?"0":request.getAttribute("currentPage")).toString());//当前页码
    	n=Integer.parseInt((request.getAttribute("n")==null?"0":request.getAttribute("n")).toString());//获取 共有多少页
    	int tp=Integer.parseInt((request.getAttribute("tp")==null?"0":request.getAttribute("tp")).toString());//
     %>



		<br>共<%=n%>页</br> <br>第<%=currentPage%>页</br> <a
			href="Newsget?currentPage=1&tp=<%=tp%>">首页</a> <a
			href="Newsget?currentPage=<%=currentPage-1%>&tp=<%=tp%>">上一页</a> <a
			href="Newsget?currentPage=<%=currentPage+1%>&tp=<%=tp%>">下一页</a> <a
			href="Newsget?currentPage=<%=n%>&tp=<%=tp%>">末页</a>
			
		---------<h3><a href="<%=request.getContextPath()%><%=OpeFunction.request().getSession().getAttribute("home") %>" onclick="return cka();">返回主页</a></h3>

			<br></br>
			<%
			for(int s=1;s<=n;s++){
			    
			%>
			<a href="Newsget?currentPage=<%=s%>"><%=s%>&nbsp&nbsp</a> 
			<%
			if(s%20==0){
			    %>
			    <br></br>
			    <% 
			}
			}
			%>
	</div>

</body>
</html>