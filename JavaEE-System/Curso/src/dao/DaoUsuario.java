package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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

			String sql = "INSERT INTO usuario (login, senha) VALUES (?, ?)";
			PreparedStatement insert = connection.prepareStatement(sql);

			insert.setString(1, usuario.getLogin());
			insert.setString(2, usuario.getSenha());
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
			return beanCurso;
		}

		return null;
	}

	public void atualizar(BeanCurso usuario) {
		try {
			String sql = "UPDATE usuario SET login = ?, senha = ? WHERE id = ?";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, usuario.getLogin());
			preparedStatement.setString(2, usuario.getSenha());
			preparedStatement.setLong(3, usuario.getId());
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
