
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="beans.Usuario" %>

	<%
	
	Usuario user = (Usuario) request.getSession().getAttribute("usuario");
	if(user == null) {
		RequestDispatcher view = request.getRequestDispatcher("acessonegado.jsp");
		view.forward(request, response);
	}
	
	%>
	
<html>
<head>
    <title>Title</title>
</head>
<body>
    Acesso liberado <a href="LoginServlet?acao=sair">Sair</a>
    <br>
    <a href="salvarUsuario?acao=listarTodos"><img src="resources/user-icon.png" width="100px" height="100px" title="Cadastrar Usuario"/></a>
    <a href="salvarProduto?acao=listarTodos"><img src="resources/product-icon.png" width="150px" height="100px" title="Cadastrar Produto"/></a>
</body>
</html>
