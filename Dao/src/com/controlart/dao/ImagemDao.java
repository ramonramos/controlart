package com.controlart.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.controlart.dao.factory.ConnFactory;
import com.controlart.dao.utils.DaoUtils;
import com.controlart.transfer.ImagemT;

public class ImagemDao {
	private Connection connection;

	private static final String SQL_CONSULT_BY_PECA = "SELECT tp.* FROM tb_imagem tp WHERE tp.id_peca = ? AND in_ativo = 1";
	private static final String SQL_INSERT = "INSERT INTO tb_imagem (id_peca, nm_imagem, ds_caminho) VALUES (?, ?, ?)";
	private static final String SQL_INACTIVATE = "UPDATE tb_imagem SET in_ativo = 0 WHERE id_peca = ?";

	public ImagemDao() throws SQLException {
		connection = ConnFactory.getConnection();
	}

	public List<ImagemT> consultByPeca(ImagemT imagemT) throws SQLException {
		PreparedStatement pStmt = null;
		ResultSet rs = null;

		try {
			pStmt = connection.prepareStatement(SQL_CONSULT_BY_PECA);
			pStmt.setObject(1, imagemT.getPeca());

			rs = pStmt.executeQuery();

			return resultsetToObject(rs);
		} finally {
			DaoUtils.closeStatementAndResultSet(pStmt, rs);
			DaoUtils.closeConnection(connection);
		}
	}

	private List<ImagemT> resultsetToObject(ResultSet rs) throws SQLException {
		List<ImagemT> listaImagemT = new ArrayList<ImagemT>(0);

		while (rs.next()) {
			ImagemT imagemT = new ImagemT();

			imagemT.setId(rs.getInt("ID_IMAGEM"));
			imagemT.setPeca(rs.getInt("ID_PECA"));
			imagemT.setNome(rs.getString("NM_IMAGEM"));
			imagemT.setAtivo(rs.getInt("IN_ATIVO"));

			listaImagemT.add(imagemT);
		}

		return listaImagemT;
	}

	public void insert(List<ImagemT> listImagemT) throws SQLException {
		PreparedStatement pStmt = null;

		try {
			for (ImagemT imagemT : listImagemT) {
				pStmt = connection.prepareStatement(SQL_INSERT);
				pStmt.setObject(1, imagemT.getPeca());
				pStmt.setObject(2, imagemT.getNome());
				pStmt.setObject(3, imagemT.getNome());

				pStmt.execute();
			}
		} finally {
			DaoUtils.closePreparedStatement(pStmt);
			DaoUtils.closeConnection(connection);
		}
	}

	public void inactivate(ImagemT imagemT) throws SQLException {
		PreparedStatement pStmt = null;

		try {
			pStmt = connection.prepareStatement(SQL_INACTIVATE);
			pStmt.setObject(1, imagemT.getPeca());

			pStmt.executeUpdate();
		} finally {
			DaoUtils.closePreparedStatement(pStmt);
			DaoUtils.closeConnection(connection);
		}
	}
}
