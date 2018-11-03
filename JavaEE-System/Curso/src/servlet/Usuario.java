package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ibm.CORBA.iiop.Request;
import com.sun.xml.internal.bind.v2.model.core.ID;

import beans.BeanCurso;
import dao.DaoUsuario;

/**
 * Servlet implementation class Usuario
 */
@WebServlet("/salvarUsuario")
public class Usuario extends HttpServlet {
	private static final long serialVersionUID = 1L;
	DaoUsuario daoUsuario = new DaoUsuario();

	public Usuario() {
		super();
		// TODO Auto-generated constructor stub
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
		try {
			String acao = request.getParameter("acao");
			String user = request.getParameter("user");
			String id = request.getParameter("id");

			if (acao.equals("delete")) {
				daoUsuario.delete(Integer.parseInt(id));

				RequestDispatcher view = request.getRequestDispatcher("/cadastroUsuario.jsp");
				request.setAttribute("usuarios", daoUsuario.listar());
				view.forward(request, response);
			} 
			else if (acao.equals("editar")) {
				RequestDispatcher view = request.getRequestDispatcher("/cadastroUsuario.jsp");
				BeanCurso usuario = daoUsuario.consultar(Integer.parseInt(id));
				request.setAttribute("user", usuario);
				view.forward(request, response);
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String id = request.getParameter("id");
		String login = request.getParameter("login");
		String senha = request.getParameter("senha");

		BeanCurso usuario = new BeanCurso();
		usuario.setId(!id.isEmpty() ? Long.parseLong(id) : 0);
		usuario.setLogin(login);
		usuario.setSenha(senha);

		try {

			if (id == null || id.isEmpty()) {
				daoUsuario.salvar(usuario);
			} else {
				daoUsuario.atualizar(usuario);
			}

			RequestDispatcher view = request.getRequestDispatcher("/cadastroUsuario.jsp");
			request.setAttribute("usuarios", daoUsuario.listar());
			view.forward(request, response);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
