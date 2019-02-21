package dao;

import java.awt.Insets;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.text.html.HTMLDocument.HTMLReader.PreAction;

import beans.Usuario;
import connection.SingleConnection;
import servlet.LoginServlet;
import servlet.UsuarioServlet;

public class DaoUsuario {

	private Connection connection;

	public DaoUsuario() {
		connection = SingleConnection.getConnection();
	}

	public void salvar(Usuario usuario) throws Exception {
		try {

			String sql = "INSERT INTO usuario (login, senha, nome, telefone, cep, rua, bairro, cidade, estado)"
					+ " VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
			PreparedStatement insert = connection.prepareStatement(sql);

			insert.setString(1, usuario.getLogin());
			insert.setString(2, usuario.getSenha());
			insert.setString(3, usuario.getNome());
			insert.setString(4, usuario.getTelefone());
			insert.setString(5, usuario.getCep());
			insert.setString(6, usuario.getRua());
			insert.setString(7, usuario.getBairro());
			insert.setString(8, usuario.getCidade());
			insert.setString(9, usuario.getEstado());
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

	public List<Usuario> listar() throws Exception {
		List<Usuario> lista = new ArrayList<>();
		String sql = "SELECT * FROM usuario";
		PreparedStatement statement = connection.prepareStatement(sql);
		ResultSet resultSet = statement.executeQuery();

		while (resultSet.next()) {
			Usuario beanCurso = new Usuario();
			beanCurso.setId(resultSet.getLong("id"));
			beanCurso.setLogin(resultSet.getString("login"));
			beanCurso.setSenha(resultSet.getString("senha"));
			beanCurso.setNome(resultSet.getString("nome"));
			beanCurso.setTelefone(resultSet.getString("telefone"));
			beanCurso.setCep(resultSet.getString("cep"));
			beanCurso.setRua(resultSet.getString("rua"));
			beanCurso.setBairro(resultSet.getString("bairro"));
			beanCurso.setCidade(resultSet.getString("cidade"));
			beanCurso.setEstado(resultSet.getString("estado"));

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

	public Usuario consultar(int id) throws Exception {
		String sql = "SELECT * FROM usuario WHERE id = " + id;
		PreparedStatement preparedStatement = connection.prepareStatement(sql);
		ResultSet resultSet = preparedStatement.executeQuery();
		if (resultSet.next()) {
			Usuario beanCurso = new Usuario();
			beanCurso.setId(resultSet.getLong("id"));
			beanCurso.setLogin(resultSet.getString("login"));
			beanCurso.setSenha(resultSet.getString("senha"));
			beanCurso.setNome(resultSet.getString("nome"));
			beanCurso.setTelefone(resultSet.getString("telefone"));
			beanCurso.setCep(resultSet.getString("cep"));
			beanCurso.setRua(resultSet.getString("rua"));
			beanCurso.setBairro(resultSet.getString("bairro"));
			beanCurso.setCidade(resultSet.getString("cidade"));
			beanCurso.setEstado(resultSet.getString("estado"));
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
	
	public boolean validarSenha(String senha) throws Exception {
		String sql = "SELECT count(1) as qtd FROM usuario WHERE senha = '" + senha + "';";
		PreparedStatement preparedStatement = connection.prepareStatement(sql);
		ResultSet resultSet = preparedStatement.executeQuery();
		if (resultSet.next()) {
			return resultSet.getInt("qtd") <= 0;
		}

		return false;
	}

	public void atualizar(Usuario usuario) {
		try {
			String sql = "UPDATE usuario SET login = ?, senha = ?, nome = ?, telefone = ?, cep = ?, rua = ?, bairro = ?, cidade = ?, estado = ? WHERE id = ?";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, usuario.getLogin());
			preparedStatement.setString(2, usuario.getSenha());
			preparedStatement.setString(3,  usuario.getNome());
			preparedStatement.setString(4, usuario.getTelefone());
			preparedStatement.setString(5, usuario.getCep());
			preparedStatement.setString(6, usuario.getRua());
			preparedStatement.setString(7, usuario.getBairro());
			preparedStatement.setString(8, usuario.getCidade());
			preparedStatement.setString(9, usuario.getEstado());
			
			preparedStatement.setLong(10, usuario.getId());
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
