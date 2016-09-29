<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<title>登陆页面</title>
</head>
<body>
<input id="userId" type="hidden" value="<c:if test="${not empty sessionScope.userSessionId}">${sessionScope.userSessionId }</c:if>"/>
	<form id="login" action="${ctx}/login.html" method="post">
		<input type="hidden" id="subPwd" name="subPwd" />
		<table align="center">
    		<tr>
    			<td>登录</td>
    		</tr>
    		<tr>
    			<td>用户名：<input type="text" id="loginName" name="loginName" /></td>
    		</tr>
    		<tr>
    			<td>密&nbsp;&nbsp;&nbsp;码：<input type="password" id="password" name="password" /></td>
    		</tr>
    		<tr>
    			<td><input id="subt" type="button" value="登录" /></td>
    		</tr>
    	</table>
	</form>
</body>
<script src="${ctx}/static/plug-ins/encrypt/jquery-1.7.2.min.js"></script>
<script src="${ctx}/static/plug-ins/encrypt/security.js"></script>
<script type="text/javascript" src="${ctx }/static/plug-ins/layer-v2.3/layer.js"></script>

<script type="text/javascript">
$(function(){
	
	//错误提示信息
	if ("${error}" != "") {
    	layer.alert('${error}', {icon : 5,shift : 6,time : 0});
	}
	
	//页面进行跳转到login.html
	if (window.location.href.indexOf("/login.html") == -1) {
	    if($("#userId").val() == null || $("#userId").val() == "")
	    {
	        top.location.href = "login.html";
	    }else
	    {
	        top.location.href = "index.html";
	    }
	}
	
	
	$('#subt').click(function(){
			var name = $('#loginName').val();
			var password =$('#password').val();
			if(name==null||name==""){
				alert("用户名不得为空！");
				return;
			}
			if(password==null||password==""){
				alert("密码不得为空！");
				return;
			}
			if($("#isAES").val()){
					$.ajax({
					type:"post",
					url:"${ctx}/loginset.html",
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
			}else{
				$('#login').submit();
			}
	});
});
</script>
</html>