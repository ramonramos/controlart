package com.controlart.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.controlart.dao.factory.ConnFactory;
import com.controlart.dao.utils.DaoUtils;
import com.controlart.transfer.UsuarioT;

public class UsuariosDao {
	private Connection connection;

	private static final String SQL_CONSULT_ALL = "SELECT tu.id_usuario, tu.id_tipo_usuario, tu.id_pessoa, tu.ds_login, tu.in_ativo FROM tb_usuario tu ORDER BY tu.ds_login";
	private static final String SQL_INSERT = "INSERT INTO tb_usuario (id_tipo_usuario, id_pessoa, ds_login, ds_senha, in_ativo) VALUES (?, ?, ?, ?, ?)";
	private static final String SQL_UPDATE = "UPDATE tb_usuario SET ds_senha = ?, in_ativo = ? WHERE id_usuario = ?";
	private static final String SQL_INACTIVATE = "UPDATE tb_usuario SET in_ativo = 0 WHERE id_usuario = ?";

	public UsuariosDao() throws SQLException {
		connection = ConnFactory.getConnection();
	}

	public List<UsuarioT> consultAll() throws SQLException {
		PreparedStatement pStmt = null;
		ResultSet rs = null;

		try {
			pStmt = connection.prepareStatement(SQL_CONSULT_ALL);

			rs = pStmt.executeQuery();

			return resultsetToObjectUsuarioT(rs);
		} finally {
			DaoUtils.closeStatementAndResultSet(pStmt, rs);
			DaoUtils.closeConnection(connection);
		}
	}

	private List<UsuarioT> resultsetToObjectUsuarioT(ResultSet rs)
			throws SQLException {
		List<UsuarioT> listaUsuarioT = new ArrayList<UsuarioT>(0);

		while (rs.next()) {
			UsuarioT usuarioT = new UsuarioT();

			usuarioT.setIdUsuario(rs.getInt("ID_USUARIO"));
			usuarioT.setIdTipoUsuario(rs.getInt("ID_TIPO_USUARIO"));
			usuarioT.setIdPessoa(rs.getInt("ID_PESSOA"));
			usuarioT.setDsLogin(rs.getString("DS_LOGIN"));
			usuarioT.setSituacao(rs.getInt("IN_ATIVO"));

			listaUsuarioT.add(usuarioT);
		}

		return listaUsuarioT;
	}

	public void insert(UsuarioT usuarioT) throws SQLException {
		PreparedStatement pStmt = null;

		try {
			pStmt = connection.prepareStatement(SQL_INSERT);
			pStmt.setObject(1, usuarioT.getIdTipoUsuario());
			pStmt.setObject(2, usuarioT.getIdPessoa());
			pStmt.setObject(3, usuarioT.getDsLogin());
			pStmt.setObject(4, usuarioT.getCdSenha());
			pStmt.setObject(5, usuarioT.getSituacao());

			pStmt.execute();
		} finally {
			DaoUtils.closePreparedStatement(pStmt);
			DaoUtils.closeConnection(connection);
		}
	}

	public void update(UsuarioT usuarioT) throws SQLException {
		PreparedStatement pStmt = null;

		try {
			pStmt = connection.prepareStatement(SQL_UPDATE);
			pStmt.setObject(1, usuarioT.getCdSenha());
			pStmt.setObject(2, usuarioT.getSituacao());
			pStmt.setObject(3, usuarioT.getIdUsuario());

			pStmt.executeUpdate();
		} finally {
			DaoUtils.closePreparedStatement(pStmt);
			DaoUtils.closeConnection(connection);
		}
	}

	public void delete(UsuarioT usuarioT) throws SQLException {
		PreparedStatement pStmt = null;

		try {
			pStmt = connection.prepareStatement(SQL_INACTIVATE);
			pStmt.setObject(1, usuarioT.getIdUsuario());

			pStmt.executeUpdate();
		} finally {
			DaoUtils.closePreparedStatement(pStmt);
			DaoUtils.closeConnection(connection);
		}
	}
}
