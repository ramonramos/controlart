package com.controlart.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.controlart.dao.factory.ConnFactory;
import com.controlart.dao.utils.DaoUtils;
import com.controlart.transfer.UsuarioT;

public class AlterarSenhaDao {
	private Connection connection;

	private final static String SQL_UPDATE = "UPDATE tb_usuario SET ds_senha = ? WHERE id_usuario = ?";

	public AlterarSenhaDao() throws SQLException {
		connection = ConnFactory.getConnection();
	}

	public void update(UsuarioT usuarioT) throws SQLException {
		PreparedStatement pStmt = null;

		try {
			pStmt = connection.prepareStatement(SQL_UPDATE);
			pStmt.setObject(1, usuarioT.getCdSenha());
			pStmt.setObject(2, usuarioT.getIdPessoa());

			pStmt.executeUpdate();
		} finally {
			DaoUtils.closePreparedStatement(pStmt);
			DaoUtils.closeConnection(connection);
		}
	}
}
