package com.controlart.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import com.controlart.dao.factory.ConnFactory;
import com.controlart.dao.utils.DaoUtils;
import com.controlart.transfer.ImagemT;

public class ImagemDao {
	private Connection connection;

	private static final String SQL_INSERT = "INSERT INTO tb_peca_imagem (id_peca, nm_imagem) VALUES (?, ?)";

	public ImagemDao() throws SQLException {
		connection = ConnFactory.getConnection();
	}

	public void insert(List<ImagemT> listImagemT) throws SQLException {
		PreparedStatement pStmt = null;

		try {
			for (ImagemT imagemT : listImagemT) {
				pStmt = connection.prepareStatement(SQL_INSERT);
				pStmt.setObject(1, imagemT.getPeca());
				pStmt.setObject(2, imagemT.getNome());

				pStmt.execute();
			}
		} finally {
			DaoUtils.closePreparedStatement(pStmt);
			DaoUtils.closeConnection(connection);
		}
	}
}
