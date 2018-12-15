package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import beans.Usuario;
import connection.SingleConnection;

public class DaoLogin {
	private Connection connection;
	
	public DaoLogin() {
		connection = SingleConnection.getConnection();
	}
	
	public boolean validarLogin(String login, String senha) throws Exception {
		String sql = "SELECT * FROM usuario WHERE login = '" + login + "' AND senha = '" + senha + "'";
		PreparedStatement statement = connection.prepareStatement(sql);
		ResultSet resultSet = statement.executeQuery();
		if (resultSet.next()) {
			return true;
		} else {
			return false;
		}
	}
	
	public Usuario getUsuario(String login) throws Exception {
		String sql = "SELECT * FROM usuario WHERE login = '" + login + "';";
		PreparedStatement statement = connection.prepareStatement(sql);
		ResultSet resultSet = statement.executeQuery();
		Usuario user = new Usuario();
		if (resultSet.next()) {
			user.setId(resultSet.getLong("id"));
			user.setNome(resultSet.getString("nome"));
			user.setLogin(resultSet.getString("login"));
			user.setSenha(resultSet.getString("senha"));
			user.setTelefone(resultSet.getString("telefone"));
			return user;
		} else {
			return null;
		}
	}

}
