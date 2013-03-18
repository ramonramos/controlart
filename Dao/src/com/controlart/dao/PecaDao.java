package com.controlart.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.controlart.dao.factory.ConnFactory;
import com.controlart.dao.utils.DaoUtils;
import com.controlart.transfer.PecaT;

public class PecaDao {
	private Connection connection;

	private static final String SQL_CONSULT_ALL = "SELECT tp.* FROM tb_peca tp ORDER BY tp.nm_peca";

	public PecaDao() throws SQLException {
		connection = ConnFactory.getConnection();
	}

	public List<PecaT> consultAll() throws SQLException {
		PreparedStatement pStmt = null;
		ResultSet rs = null;

		try {
			pStmt = connection.prepareStatement(SQL_CONSULT_ALL);

			rs = pStmt.executeQuery();

			return resultsetToObjectT(rs);
		} finally {
			DaoUtils.closePreparedStatement(pStmt);
			DaoUtils.closeConnection(connection);
		}
	}

	private List<PecaT> resultsetToObjectT(ResultSet rs) throws SQLException {
		List<PecaT> listaPecaT = new ArrayList<PecaT>();

		while (rs.next()) {
			PecaT pecaT = new PecaT();

			pecaT.setId(rs.getInt("ID_PECA"));
			pecaT.setClassificacao(rs.getInt("ID_CLASSIFICACAO"));
			pecaT.setNome(rs.getString("NM_PECA"));
			pecaT.setDescricao(rs.getString("DS_PECA"));
			pecaT.setAutor(rs.getString("NM_AUTOR"));
			pecaT.setPeriodo(rs.getString("DS_PERIODO"));
			pecaT.setLargura(rs.getDouble("VL_LARGURA"));
			pecaT.setAltura(rs.getDouble("VL_ALTURA"));
			pecaT.setMaterial(rs.getString("DS_MATERIAL"));
			pecaT.setNumeroRegistro(rs.getInt("NR_REGISTRO"));
			pecaT.setProfundidade(rs.getDouble("VL_PROFUNDIDADE"));
			pecaT.setHistorico(rs.getString("DS_HISTORICA"));
			pecaT.setStatus(rs.getString("DS_STATUS"));
			pecaT.setEstado(rs.getString("DS_ESTADO"));
			pecaT.setPreco(rs.getDouble("VL_PRECO"));
			pecaT.setDisponivelLeilao(rs.getInt("IN_LEILAO"));
			pecaT.setAtivo(rs.getInt("IN_ATIVO"));

			listaPecaT.add(pecaT);
		}

		return listaPecaT;
	}
}