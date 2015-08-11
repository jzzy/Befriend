<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="com.befriend.util.*"%>
<!doctype html>

<html>
<head>
<meta charset="utf-8" />
<title>书籍上传</title>
<link rel="stylesheet" href="../themes/default/default.css" />
<link rel="stylesheet" href="../plugins/code/prettify.css" />
<script charset="utf-8" src="../kindeditor.js"></script>
<script charset="utf-8" src="../lang/zh_CN.js"></script>
<script charset="utf-8" src="../plugins/code/prettify.js"></script>



<script type="text/javascript">

function cka(){
	 if(confirm("确定放弃当前操作返回主页？"))
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
		  var author = document.getElementById("author").value;
		  var review = document.getElementById("review").value;
		  
		 
		   if (title==""||title==null||title.length>20)
	    	{
	    		alert("请填写书籍名字！");
	    	
	    		return false;
	  	}
		   if (author==""||author==null||author.length>30)
	    	{
	    		alert("请填写书籍作者！");
	    	
	    		return false;
	  	}
	    if (summary==""||summary==null)
	    	{
	    		alert("请正书籍简介！");
	    	
	    		return false;
	  	}
	    if (review==""||review==null)
    	{
    		alert("请填写书籍评论!");
    	
    		return false;
  	}
	    if (time==""||time==null)
		{
			alert("请输入时间！");
		
			return false;
		} 
	  
		  
	
return true;
	}
	
	</script>
</head>
<body>

	<div style="background: #adc; width: 1500px; height: 1000px;">
	<h2>书籍上传</h2>
		<form action="upBook" method="post" name="picForm"
			enctype="multipart/form-data">
			<table cellpadding="0" cellspacing="0" style="width: 1500px">
				<p></p>
				<tr>
					<td>书籍名字： (20个汉字以内)</td>
					
					<td colspan="2"><textarea name="title" id="title"
							cols="100" rows="8"
							></textarea>
				</tr>
				<tr>
					
						<td>书籍作者:(30个汉字以内)</td>
					<td colspan="2"><textarea name="author" id="author"
							cols="100" rows="8"
							></textarea>

					</td>
				</tr>

				<tr>
					<td>书籍简介：</td>
					<td colspan="2"><textarea name="summary" id="summary"
							cols="100" rows="8"
							></textarea>

					</td>
				</tr>
				
			<tr>
					<td>书籍评论：</td>
					<td colspan="2"><textarea name="review" id="review"
							cols="100" rows="8"
							></textarea>

					</td>
				</tr>
				<tr>
					<td>出版社：</td>
					<td colspan="2"><textarea name="house" id="house"
							cols="100" rows="8"
							></textarea>

					</td>
				</tr>
				<tr>
					<td>书籍类别</td>
					<td><select name="itype" id="itype" disable=true onclick="ck()">
							<option value="1">幼儿</option>
							<option value="2">小学</option>
							<option value="3">初中</option>
							<option value="4">高中</option>
						
					</select></td>
				</tr>
				
				<tr>
					<td>书籍发布时间：( 例如 : 2014-12-09 12:12:32 )</td>
					<td><input type="datetime" id="time" name="time"
						value="<%=OpeFunction.getNowTime() %>"/></td>

				</tr>
				<tr>
					<td><br /> <input type="submit" name="button"
						onclick="return check(picForm);" value="OK" /></td>
				</tr>

			</table>

		</form>
		<form action="upBookxmls" method="post" enctype="multipart/form-data">
		<table>
		<tr>
		<td>书籍文件 xlsx
		<input type="file" name=xlsxFile >
		</td>
		</tr>
		<tr>
		<td>
		<input type="submit" value="上传">
		</td>
		</tr>
		</table>
		</form>
		
		---------<h3><a href="<%=request.getContextPath()%><%=OpeFunction.request().getSession().getAttribute("home") %>" onclick="return cka();">返回主页</a></h3>
	</div>

</body>
</html>
