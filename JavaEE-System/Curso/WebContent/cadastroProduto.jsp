<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Cadastro de produto</title>
<link rel="stylesheet" type="text/css" media="screen"
	href="resources/css/cadastro.css" />
</head>
<body>
	<center>
		<h1>Cadastro de produto</h1>
		<h3 style="color: orange;">${msg}</h3>

		<form action="salvarProduto" method="post" id="formProduto"
			onsubmit="return validarCampo()? true : false">
			<ul class="form-style-1">
				<table>
					<li>
					<tr>
						<td>Id:</td>
						<td><input type="text" readonly="readonly" id="id" name="id"
							value="${produto.id}" class="field-long"></td>
					</tr>
					</li>
					<li>
					<tr>
						<td>Nome:</td>
						<td><input type="text" id="nome" name="nome"
							value="${produto.nome}" class="field-long"></td>
					</tr>
					</li>
					<tr>
						<td>Quantidade:</td>
						<td><input type="text" id="quantidade" name="quantidade"
							value="${produto.quantidade}" class="field-long"></td>
					</tr>
					<tr>
						<td>Valor(R$):</td>
						<td><input type="text" id="valor" name="valor"
							value="${produto.valor}" class="field-long"></td>
					</tr>
				</table>
				<br />
				<input type="submit" value="Salvar" />
				<input type="submit"
					onclick="document.getElementById(formProduto).action = 'salvarProduto?acao=reset'"
					value="Cancelar" />
			</ul>
		</form>
	</center>

	<div class="container">
		<table class="responsive-table">
			<caption>Produtos Cadastrados</caption>
			<tr>
				<th style="text-align: center;">ID</th>
				<th style="text-align: center;">Nome</th>
				<th style="text-align: center;">Quantidade</th>
				<th style="text-align: center;">Valor</th>
				<th style="text-align: center;">Exluir</th>
				<th style="text-align: center;">Editar</th>
			</tr>
			<c:forEach items="${produtos}" var="produto">
				<tr>
					<td><c:out value="${produto.id}"></c:out></td>
					<td style="width: 150px"><c:out value="${produto.nome}"></c:out></td>
					<td><c:out value="${produto.quantidade}"></c:out></td>
					<td><c:out value="${produto.valor}"></c:out></td>
					<td><a href="salvarProduto?acao=delete&id=${produto.id}"><img
							src="resources/excluir.png" width="20px" height="20px"
							title="Excluir"></img></a></td>
					<td><a href="salvarProduto?acao=editar&id=${produto.id}"><img
							src="resources/editar.png" width="20px" height="20px"
							title="Editar"></img></a></td>
				</tr>
			</c:forEach>
		</table>
	</div>
	<script type="text/javascript">
		function validarCampo() {
			if (document.getElementById("nome").value == "") {
				alert("Informe um nome!");
				return false;
			} else if (document.getElementById("quantidade").value == "") {
				alert("Informe a quantidade!");
				return false;
			} else if (document.getElementById("valor").value == "") {
				alert("Informe o valor!");
				return false;
			}
			return true;
		}
	</script>
</body>
</html>