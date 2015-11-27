<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
    <%@page import="java.util.*"%>
    <%@page import="com.befriend.util.*"%>
<%@page import="com.befriend.entity.News"%>
<!doctype html>

<html>
<head>
<meta charset="utf-8" />
<title>新闻修改</title>
<link rel="stylesheet" href="SuperAdmin/AdminNews/kindeditor/themes/default/default.css" />
<link rel="stylesheet" href="SuperAdmin/AdminNews/kindeditor/plugins/code/prettify.css" />
<script charset="utf-8" src="SuperAdmin/AdminNews/kindeditor/kindeditor.js"></script>
<script charset="utf-8" src="SuperAdmin/AdminNews/kindeditor/lang/zh_CN.js"></script>
<script charset="utf-8" src="SuperAdmin/AdminNews/kindeditor/plugins/code/prettify.js"></script>



<script type="text/javascript">

function cka(){
	 if(confirm("确定？"))
	 	{
		
		  return true;
	 	}
	 		else{
		   return false;
	 		}
}

	function check(picForm)
	{
		  var title = document.getElementById("title").value;
		  var summary = document.getElementById("summary").value;
		  var timet = document.getElementById("time").value;

		   if (title==""||title==null||title.length>=30)
	    	{
	    		alert("请填写30个汉字以内标题！");
	    	
	    		return false;
	  	}
	    if (summary==""||summary==null||summary.length>30)
	    	{
	    		alert("请正确填写概要30字以内！");
	    	
	    		return false;
	  	}
	    if (timet==""||timet==null)
		{
			alert("请输入时间！");
		
			return false;
		} 
	    if(confirm("确定？"))
	 	{
		
		  return true;
	 	}
	 		else{
		   return false;
	 		}
		  
	}
	
		KindEditor.ready(function(K) {
			var editor1 = K.create('textarea[name="content1"]', {
				cssPath : 'SuperAdmin/AdminNews/kindeditor/plugins/code/prettify.css',
				uploadJson : 'SuperAdmin/AdminNews/kindeditor/jsp/upload_json.jsp',
				fileManagerJson : 'SuperAdmin/AdminNews/kindeditor/jsp/file_manager_json.jsp',
				allowFileManager : true,
				afterCreate : function() {
					var self = this;
					K.ctrl(document, 13, function() {
						self.sync();
						document.forms['example'].submit();
					});
					K.ctrl(self.edit.doc, 13, function() {
						self.sync();
						document.forms['example'].submit();
					});
				}
			});
			prettyPrint();
		});
	</script>
</head>
<body style="background: #adc;">

	<div >
	
	<%
	News n=(News)request.getAttribute("n");
	if(n==null){
		%>
		<h2>没有新闻</h2>
		<%
		return;
	}
	%>
	<h2>新闻修改</h2>
		<form action="upAdminNews" method="post" name="picForm"
			enctype="multipart/form-data">
			<table cellpadding="0" cellspacing="0" style="width: 1500px">
				<p></p>
				<tr>
					<input style="display: none;" type="text" name="newsid" value="<%=n.getId() %>"/>
					<td>文章名字： (20个汉字以内)</td>
					<td> <input style="width: 645px;"
					 type="text" id="title" value="<%=n.getTitle() %>" name="title" />
						
						
						</td>
				</tr>
				<p></p>
				<tr>
					<td>文章概要:(30个汉字以内)</td>
					<td><textarea name="summary" id="summary"
							cols="5" rows="3"
							style="width: 645px; height: 100px;"><%=n.getSummary() %></textarea>
						</td>
				</tr>

				<tr>
					<td>文章内容：</td>
					<td colspan="2"><textarea name="content1" id="content1"
							cols="100" rows="8"
							style="width: 150px; height: 500px; visibility: hidden;"><%=n.getContent() %></textarea>

					</td>
				</tr>
				<tr>
					<td>文章被收藏次数：</td>
					<td><input type="text" id="num" name="num"
						value="<%=n.getCollectnum() %>"/></td>

				</tr>
					<tr>
					<td>文章被点击次数：</td>
					<td><input type="text" id="hits" name="hits"
						value="<%=n.getHits() %>"/></td>

				</tr>
				<tr>
					<td>文章发布时间：( 例如 : 2014-12-09 12:12:32 )</td>
					<td><input type="datetime" id="time" name="time"
						value="<%=n.getTime() %>"/></td>

				</tr>
				<tr>
					<td><br /> <input type="submit" name="button"
						onclick="return check(picForm);" value="确定修改" /></td>
				</tr>

			</table>

		</form>
		
		---------<h3><a href="<%=request.getContextPath()%><%=OpeFunction.request().getSession().getAttribute("home") %>" onclick="return cka();">返回主页</a></h3>
	</div>

</body>
</html>
