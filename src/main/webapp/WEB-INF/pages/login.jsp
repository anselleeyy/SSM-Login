<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">

<title>管理系统-登录页</title>
<link rel="stylesheet" href="css/login.css">
</head>
<body>
	<div class="body"></div>
	<div class="grad"></div>
	<div class="header">
		<div>登录</div>
	</div>
	<br>
	<form class="login" action="login" name="login" onkeydown="keyAction(event)">
		<input type="text" placeholder="用户名" name="username" value="${username}"><br>
		<input type="password" placeholder="密码" name="password"><br>

		<input type="button" value="登录" onclick="Login()">
		<input type="button" value="注册" onclick="Register()">	
	</form>
	
	<script>
		function Login()
		{
			login.action = "login";
			login.submit();
		}
		
		function keyAction(event) {
			if (event.keyCode === 13) {
				Login();
			}
		}
		
		function Register()
		{
			login.action = "register";
			login.submit();
			
		}
	</script>
	
</body>
</html>
