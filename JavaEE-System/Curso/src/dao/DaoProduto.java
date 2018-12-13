package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import beans.Produto;
import beans.Usuario;
import connection.SingleConnection;

public class DaoProduto {

	private Connection connection;

	public DaoProduto() {
		connection = SingleConnection.getConnection();
	}

	public void salvar(Produto produto) throws Exception {
		try {

			String sql = "INSERT INTO produto (nome, qtd, valor) VALUES (?, ?, ?)";
			PreparedStatement insert = connection.prepareStatement(sql);

			insert.setString(1, produto.getNome());
			insert.setDouble(2, produto.getQuantidade());
			insert.setDouble(3, produto.getValor());
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

	public List<Produto> listar() throws Exception {
		List<Produto> lista = new ArrayList<>();
		String sql = "SELECT * FROM produto";
		PreparedStatement statement = connection.prepareStatement(sql);
		ResultSet resultSet = statement.executeQuery();

		while (resultSet.next()) {
			Produto beanCurso = new Produto();
			beanCurso.setId(resultSet.getInt("id"));
			beanCurso.setNome(resultSet.getString("nome"));
			beanCurso.setQuantidade(resultSet.getDouble("qtd"));
			beanCurso.setValor(resultSet.getDouble("valor"));

			lista.add(beanCurso);
		}

		return lista;
	}

	public void delete(int id) {
		try {
			String sql = "DELETE FROM produto WHERE id = " + id;
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

	public Produto consultar(int id) throws Exception {
		String sql = "SELECT * FROM produto WHERE id = " + id;
		PreparedStatement preparedStatement = connection.prepareStatement(sql);
		ResultSet resultSet = preparedStatement.executeQuery();
		if (resultSet.next()) {
			Produto beanCurso = new Produto();
			beanCurso.setId(resultSet.getInt("id"));
			beanCurso.setNome(resultSet.getString("nome"));
			beanCurso.setQuantidade(resultSet.getDouble("qtd"));
			beanCurso.setValor(resultSet.getDouble("valor"));
			
			return beanCurso;
		}

		return null;
	}

	public void atualizar(Produto produto) {
		try {
			String sql = "UPDATE produto SET nome = ?, qtd = ?, valor = ? WHERE id = ?";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, produto.getNome());
			preparedStatement.setDouble(2, produto.getQuantidade());
			preparedStatement.setDouble(3, produto.getValor());
			preparedStatement.setInt(4, produto.getId());
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
