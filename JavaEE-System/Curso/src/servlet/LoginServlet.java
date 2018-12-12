package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.annotation.WebServlet;

import beans.Usuario;
import dao.DaoLogin;

@WebServlet("/LoginServlet")
public class LoginServlet extends javax.servlet.http.HttpServlet {
    protected void doPost(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        try {
    	Usuario beanCurso = new Usuario();
        DaoLogin loginDAO = new DaoLogin();
        
        String login = request.getParameter("login");
        String senha = request.getParameter("senha");

        if (loginDAO.validarLogin(login, senha)){
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

    protected void doGet(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        doPost(request, response);
    }
}
