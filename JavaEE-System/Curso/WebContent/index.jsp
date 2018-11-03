<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" media="screen"
	href="resources/css/style.css" />
</head>
<body>
	<div class="login-page">
		<div class="form">
			<form action="LoginServlet" class="login-form" method="post">
				Login: <input type="text" id="login" name="login"> <br>
				Senha: <input type="password" id="senha" name="senha"> <br>
				<button type="submit">Logar</button>
			</form>
		</div>
	</div>
</body>
</html>