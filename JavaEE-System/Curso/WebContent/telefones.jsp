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

<!-- Adicionando JQuery -->
<script src="https://code.jquery.com/jquery-3.2.1.min.js"
	integrity="sha256-hwg4gsxgFZhOsEEamdOYGBf13FyQuiTwlAQgxVSNgt4="
	crossorigin="anonymous"></script>

</head>
<body>
	<a href="acessoliberado.jsp">Inicio</a>
	<div style="text-align: center">
		<h1>Cadastro de telefones</h1>
		<h3 style="color: orange;">${msg}</h3>

		<form action="salvarTelefones" method="post" id="formUser"
			onsubmit="return validarCampos()? true : false">
			<ul class="form-style-1">
				<li>
					<table>
						<tr>
							<td>Usuario:</td>
							<td><input type="text" readonly="readonly" id="userId" name="userId"
								class="field-long" value="${userEscolhido.id}"></td>
							<td><input type="text" readonly="readonly" id="userNome" name="userNome"
								class="field-long" value="${userEscolhido.nome}"></td>
						</tr>

					</table>
				</li>

				<br />
				<input type="submit" value="Salvar" />
				<input type="submit"
					onclick="document.getElementById(formUser).action = 'salvarUsuario?acao=reset'"
					value="Cancelar" />
			</ul>
		</form>
	</div>

	<div class="container">
		<table class="responsive-table">
			<caption>Telefones Cadastrados</caption>
			<tr>
				<th style="text-align: center;">ID</th>
				<th style="text-align: center;">Número</th>
				<th style="text-align: center;">Tipo</th>
				<th style="text-align: center;">Excluir</th>
			</tr>
			<c:forEach items="${telefones}" var="fone">
				<tr>
					<td><c:out value="${fone.id}"></c:out></td>
					<td style="width: 150px"><c:out value="${fone.numero}"></c:out></td>
					<td><c:out value="${fone.tipo}"></c:out></td>
					<td><a href="salvarTelefones?acao=delete&id=${fone.id}"><img
							alt="Excluir" src="resources/img/excluir.png" width="20px"
							height="20px" title="Excluir"></img></a></td>
				</tr>
			</c:forEach>
		</table>
	</div>
	<script type="text/javascript">
		function validarCampos() {
			if (document.getElementById("nome").value == '') {
				alert("Informe o nome!")
				return false;
			}

			return true;
		}
	</script>
</body>
</html>