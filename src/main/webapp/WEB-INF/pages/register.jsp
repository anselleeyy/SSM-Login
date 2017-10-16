<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="mvc"%>
<!DOCTYPE>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">

<title>管理系统-注册页</title>
<link rel="stylesheet" href="css/register.css">
</head>
<body>

	<div class="body"></div>
	<div class="grad"></div>
	<div class="header">
		<div>注册</div>
	</div>
	<br>
	<mvc:form class="login" action="verify" name="register"
		modelAttribute="user">
		<mvc:input type="text" path="username" placeholder="用户名"
			name="username" />
		<br>
		<mvc:errors path="username" style="color: RED" />
		<mvc:input type="email" path="email" placeholder="邮箱地址" name="email" />
		<br>
		<mvc:errors path="email" style="color: RED" />
		<mvc:input type="password" path="password" placeholder="密码"
			name="password" />
		<br>
		<mvc:errors path="password" style="color: RED" />
		<input type="password" placeholder="再次输入密码" name="password2" />
		<br>
		<input type="button" value="注册" onclick="Regis()">
	</mvc:form>

	<script type="text/javascript">
		function Regis() {
			var pwd1 = document.forms[0][2].value;
			var pwd2 = document.forms[0][3].value;
			if (pwd1 !== pwd2) {
				alert('两次密码不符，请重新输入');
				document.forms[0][3].focus();
			} else {
				register.action = "verify";
				register.submit();
			}

		}
	</script>

</body>
</html>
