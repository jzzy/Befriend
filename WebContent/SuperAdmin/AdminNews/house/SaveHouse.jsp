<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="com.befriend.util.*"%>
<!doctype html>

<html>
<head>
<meta charset="utf-8" />
<title>学区房信息上传</title>




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
   
	  var schoolname = document.getElementById("schoolname").value;
	  var address = document.getElementById("address").value;
	  var time = document.getElementById("time").value;
	  if (schoolname==""||schoolname==null||schoolname.length>40)
    	{
    		alert("请填写40个汉字以内的学校名字！");
    	
    		return false;
  	
    	}
      if (address==""||address==null||address.length>30)
    	{
    		alert("请正确填写地址30字以内！");
    	
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

	<div style="background: #adc; width: 100%; height: 1000px;">
	<h2>添加学区房</h2>
		<form action="Addschooldistrict" method="post" name="picForm"
			enctype="multipart/form-data">
			<table cellpadding="0" cellspacing="0" style="width: 1500px">
				<p></p>
				<tr>
					<td>学校名字：</td>
					<td><textarea name="schoolname" id="schoolname"
							cols="100" rows="8"
							style="width: 300px; height: 50px;"></textarea></td>
				</tr>
				<tr>
					<td>学校地址：</td>
					<td><textarea name="address" id="address"
							cols="100" rows="8"
							style="width: 300px; height: 50px;"></textarea></td>
				</tr>
				
				<tr>
					<td>范围:</td>
					<td><textarea name="scope" id="scope"
							cols="100" rows="8"
							style="width: 300px; height: 200px;"></textarea></td>
				</tr>

				<tr>
					<td>楼盘：</td>
					<td colspan="2"><textarea name="property" id="property"
							cols="100" rows="8"
							style="width:300px; height: 200px;"></textarea>

					</td>
				</tr>
				
				
				
				
				

				<tr>
					<td>发布时间：( 例如 : 2014-12-09 12:12:32 )</td>
					<td><input type="datetime" id="time" name="time"
						value="<%=OpeFunction.getNowTime() %>"/></td>

				</tr>
				<tr>
					<td>选择xlsx格式文件批量上传</td>
					<td><input type="file" id="xlsxFile" name="xlsxFile"
						/></td>

				</tr>
				
				<tr>
					<td><br /> <input type="submit" name="button"
						onclick="return check(picForm);" value="确定发布" /></td>
				</tr>

			</table>

		</form>
		
		---------<h3><a href="<%=request.getContextPath()%><%=OpeFunction.request().getSession().getAttribute("home") %>" onclick="return cka();">返回主页</a></h3>
	</div>

</body>
</html>
