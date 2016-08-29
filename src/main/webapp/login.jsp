<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form id="login" action="${ctx}/login/index" method="post">
		<input type="hidden" id="subPwd" name="subPwd" />
		<table align="center">
    		<tr>
    			<td>登录</td>
    		</tr>
    		<tr>
    			<td>用户名：<input type="text" id="loginName" name="loginName" /></td>
    		</tr>
    		<tr>
    			<td>密&nbsp;&nbsp;&nbsp;码：<input type="password" id="loginPwd" name="loginPwd" /></td>
    		</tr>
    		<tr>
    			<td><input id="subt" type="button" value="登录" /></td>
    		</tr>
    	</table>
	</form>
	<a href="${ctx }/menu/toAddMenu.do">添加菜单</a>
</body>
<script src="${ctx}/static/plug-ins/encrypt/jquery-1.7.2.min.js"></script>
<script src="${ctx}/static/plug-ins/encrypt/security.js"></script>

<script type="text/javascript">
$(function(){
	$('#subt').click(function(){
			var name = $('#loginName').val();
			var password =$('#loginPwd').val();
			if(name==null||name==""){
				alert("用户名不得为空！");
				return;
			}
			if(password==null||password==""){
				alert("密码不得为空！");
				return;
			}
			$.ajax({
			type:"post",
			url:"${ctx}/login/loginset",
			success:function(rd){
				if(rd!=null){
					//加密模
					var Modulus = rd.split(';')[0];
					//公钥指数
					var public_exponent = rd.split(';')[1];
					//通过模和公钥参数获取公钥
					var key = new RSAUtils.getKeyPair(public_exponent, "", Modulus);
					//颠倒密码的顺序，要不然后解密后会发现密码顺序是反的
					var reversedPwd = password.split("").reverse().join("");
					//对密码进行加密传输 
					var encrypedPwd = RSAUtils.encryptedString(key,reversedPwd);
					$('#subPwd').val(encrypedPwd);
					//$('#loginPwd').val(encrypedPwd);
					$('#login').submit();
				}
			}
		});
	});
});
</script>
</html>