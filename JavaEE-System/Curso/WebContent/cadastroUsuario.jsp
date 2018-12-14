<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Cadastro de usuário</title>
<link rel="stylesheet" type="text/css" media="screen"
	href="resources/css/cadastro.css" />
</head>
<body>
	<center>
		<h1>Cadastro de usuário</h1>
		<h3 style="color: orange;">${msg}</h3>

		<form action="salvarUsuario" method="post" id="formUser"
			onsubmit="return validarCampos()? true : false">
			<ul class="form-style-1">
				<table>
					<li>
					<tr>
						<td>Id:</td>
						<td><input type="text" readonly="readonly" id="id" name="id"
							value="${user.id}" class="field-long"></td>
					</tr>
					</li>
					<li>
					<tr>
						<td>Nome:</td>
						<td><input type="text" id="nome" name="nome"
							value="${user.nome}" class="field-long"></td>
					</tr>
					</li>
					<tr>
						<td>Login:</td>
						<td><input type="text" id="login" name="login"
							value="${user.login}" class="field-long"></td>
					</tr>
					<tr>
						<td>Senha:</td>
						<td><input type="password" id="senha" name="senha"
							value="${user.senha}" class="field-long"></td>
					</tr>
					<tr>
						<td>Telefone:</td>
						<td><input type="text" id="telefone" name="telefone"
							value="${user.telefone}" class="field-long"></td>
					</tr>
				</table>
				<br />
				<input type="submit" value="Salvar" />
				<input type="submit"
					onclick="document.getElementById(formUser).action = 'salvarUsuario?acao=reset'"
					value="Cancelar" />
			</ul>
		</form>
	</center>

	<div class="container">
		<table class="responsive-table">
			<caption>Usuarios Cadastrados</caption>
			<tr>
				<th style="text-align: center;">ID</th>
				<th style="text-align: center;">Login</th>
				<th style="text-align: center;">Nome</th>
				<th style="text-align: center;">Telefone</th>
				<th style="text-align: center;">Exluir</th>
				<th style="text-align: center;">Editar</th>
			</tr>
			<c:forEach items="${usuarios}" var="user">
				<tr>
					<td><c:out value="${user.id}"></c:out></td>
					<td style="width: 150px"><c:out value="${user.login}"></c:out></td>
					<td><c:out value="${user.nome}"></c:out></td>
					<td><c:out value="${user.telefone}"></c:out></td>
					<td><a href="salvarUsuario?acao=delete&id=${user.id}"><img
							src="resources/excluir.png" width="20px" height="20px"
							title="Excluir"></img></a></td>
					<td><a href="salvarUsuario?acao=editar&id=${user.id}"><img
							src="resources/editar.png" width="20px" height="20px"
							title="Editar"></img></a></td>
				</tr>
			</c:forEach>
		</table>
	</div>
	<script type="text/javascript">
		function validarCampos() {
			if (document.getElementById("nome").value == '') {
				alert("Informe o nome!")
				return false;
			} else	if (document.getElementById("login").value == '') {
				alert("Informe o login!")
				return false;
			} else 	if (document.getElementById("senha").value == '') {
				alert("Informe a senha!")
				return false;
			} else 	if (document.getElementById("telefone").value == '') {
				alert("Informe o telefone!")
				return false;
			}
			
			return true;
		}
	</script>
</body>
</html>