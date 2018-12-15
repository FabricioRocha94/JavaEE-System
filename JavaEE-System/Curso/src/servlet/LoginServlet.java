package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.annotation.WebServlet;

import beans.Usuario;
import dao.DaoLogin;

@WebServlet("/LoginServlet")
public class LoginServlet extends javax.servlet.http.HttpServlet {
	protected void doPost(javax.servlet.http.HttpServletRequest request,
			javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {
		try {
			DaoLogin loginDAO = new DaoLogin();

			String login = request.getParameter("login");
			String senha = request.getParameter("senha");
			Usuario usuario = loginDAO.getUsuario(login);
			String acao = request.getParameter("acao") == null ? "" : request.getParameter("acao");

			if (acao.equals("sair")) {
				request.getSession().invalidate();
				RequestDispatcher view = request.getRequestDispatcher("index.jsp");
				view.forward(request, response);
			} else if (loginDAO.validarLogin(login, senha)) {
				request.getSession().setAttribute("usuario", usuario);
				RequestDispatcher dispatcher = request.getRequestDispatcher("/acessoliberado.jsp");
				dispatcher.forward(request, response);
			} else {
				RequestDispatcher dispatcher = request.getRequestDispatcher("/acessonegado.jsp");
				dispatcher.forward(request, response);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	protected void doGet(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response)
			throws javax.servlet.ServletException, IOException {
		doPost(request, response);
	}
}
