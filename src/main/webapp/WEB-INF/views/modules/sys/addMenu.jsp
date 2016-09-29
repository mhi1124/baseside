<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>菜单管理</title>
</head>
<body>
	<form id="resourceForm" name="resourceForm" action="${ctx}/menu/addMenu" class="form-horizontal" method="post">
		<tr>
			<td>
				名称
			</td>
			<td>
				<input type="text" name="menuName" value=""/>
			</td>
		</tr>
		<tr>
			<td>
				路径
			</td>
			<td>
				<input type="text" name="menuUrl" value=""/>
			</td>
		</tr>
		<tr>
			<td><input type="submit" value="提交"/></td>
		</tr>
		<tr></tr>
		<tr></tr>
		
	</form>
	
</body>
</html>