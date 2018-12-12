package dao;

import java.awt.Insets;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.text.html.HTMLDocument.HTMLReader.PreAction;

import beans.BeanCurso;
import connection.SingleConnection;
import servlet.LoginServlet;
import servlet.Usuario;

public class DaoUsuario {

	private Connection connection;

	public DaoUsuario() {
		connection = SingleConnection.getConnection();
	}

	public void salvar(BeanCurso usuario) throws Exception {
		try {

			String sql = "INSERT INTO usuario (login, senha, nome, telefone) VALUES (?, ?, ?, ?)";
			PreparedStatement insert = connection.prepareStatement(sql);

			insert.setString(1, usuario.getLogin());
			insert.setString(2, usuario.getSenha());
			insert.setString(3, usuario.getNome());
			insert.setString(4, usuario.getTelefone());
			insert.execute();
			connection.commit();
		} catch (Exception e) {
			e.printStackTrace();
			try {
				connection.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}

		}
	}

	public List<BeanCurso> listar() throws Exception {
		List<BeanCurso> lista = new ArrayList<>();
		String sql = "SELECT * FROM usuario";
		PreparedStatement statement = connection.prepareStatement(sql);
		ResultSet resultSet = statement.executeQuery();

		while (resultSet.next()) {
			BeanCurso beanCurso = new BeanCurso();
			beanCurso.setId(resultSet.getLong("id"));
			beanCurso.setLogin(resultSet.getString("login"));
			beanCurso.setSenha(resultSet.getString("senha"));
			beanCurso.setNome(resultSet.getString("nome"));
			beanCurso.setTelefone(resultSet.getString("telefone"));

			lista.add(beanCurso);
		}

		return lista;
	}

	public void delete(int id) {
		try {
			String sql = "DELETE FROM usuario WHERE id = " + id;
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.execute();
			connection.commit();
		} catch (Exception e) {
			e.printStackTrace();
			try {
				connection.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}

	public BeanCurso consultar(int id) throws Exception {
		String sql = "SELECT * FROM usuario WHERE id = " + id;
		PreparedStatement preparedStatement = connection.prepareStatement(sql);
		ResultSet resultSet = preparedStatement.executeQuery();
		if (resultSet.next()) {
			BeanCurso beanCurso = new BeanCurso();
			beanCurso.setId(resultSet.getLong("id"));
			beanCurso.setLogin(resultSet.getString("login"));
			beanCurso.setSenha(resultSet.getString("senha"));
			beanCurso.setNome(resultSet.getString("nome"));
			beanCurso.setTelefone(resultSet.getString("telefone"));
			return beanCurso;
		}

		return null;
	}
	
	public boolean validarLogin(String login) throws Exception {
		String sql = "SELECT count(1) as qtd FROM usuario WHERE login = '" + login + "';";
		PreparedStatement preparedStatement = connection.prepareStatement(sql);
		ResultSet resultSet = preparedStatement.executeQuery();
		if (resultSet.next()) {
			return resultSet.getInt("qtd") <= 0;
		}

		return false;
	}

	public void atualizar(BeanCurso usuario) {
		try {
			String sql = "UPDATE usuario SET login = ?, senha = ?, nome = ?, telefone = ? WHERE id = ?";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, usuario.getLogin());
			preparedStatement.setString(2, usuario.getSenha());
			preparedStatement.setString(3,  usuario.getNome());
			preparedStatement.setString(4, usuario.getTelefone());
			preparedStatement.setLong(5, usuario.getId());
			preparedStatement.executeUpdate();
			connection.commit();
		} catch (Exception e) {
			e.printStackTrace();
			try {
				connection.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}

	}

}
