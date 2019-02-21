package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.Usuario;
import dao.DaoUsuario;

@WebServlet("/salvarTelefones")
public class TelefonesServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public TelefonesServlet() {
		super();
	}
	
	DaoUsuario daoUsuario = new DaoUsuario();

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
		String idUser = request.getParameter("user");
		Usuario userSelecionado = daoUsuario.consultar(Integer.parseInt(idUser));
		request.setAttribute("userEscolhido", userSelecionado);
		
		RequestDispatcher view = request.getRequestDispatcher("/telefones.jsp");
//		request.setAttribute("telefones", daoUsuario.listar());
		view.forward(request, response);
		
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}

}
