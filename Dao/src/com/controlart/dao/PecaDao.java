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
	private static final String SQL_INSERT = "INSERT INTO tb_peca (id_classificacao, nm_peca, ds_peca, nm_autor, ds_periodo, vl_largura, vl_altura, ds_material, nr_registro, vl_profundidade, ds_historica, ds_status, ds_estado, vl_preco, in_leilao, in_ativo) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
	private static final String SQL_UPDATE = "UPDATE tb_peca SET id_classificacao = ?, nm_peca = ?, ds_peca = ?, nm_autor = ?, ds_periodo = ?, vl_largura = ?, vl_altura = ?, ds_material = ?, nr_registro = ?, vl_profundidade = ?, ds_historica = ?, ds_status = ?, ds_estado = ?, vl_preco = ?, in_leilao = ?, in_ativo = ? WHERE id_peca = ?";
	private static final String SQL_INACTIVATE = "UPDATE tb_peca SET in_ativo = 0 WHERE id_peca = ?";

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
			pecaT.setPreco(rs.getBigDecimal("VL_PRECO"));
			pecaT.setDisponivelLeilao(rs.getInt("IN_LEILAO"));
			pecaT.setAtivo(rs.getInt("IN_ATIVO"));

			listaPecaT.add(pecaT);
		}

		return listaPecaT;
	}

	public void insert(PecaT pecaT) throws SQLException {
		PreparedStatement pStmt = null;

		try {
			pStmt = connection.prepareStatement(SQL_INSERT);
			pStmt.setObject(1, pecaT.getClassificacao());
			pStmt.setObject(2, pecaT.getNome());
			pStmt.setObject(3, pecaT.getDescricao());
			pStmt.setObject(4, pecaT.getAutor());
			pStmt.setObject(5, pecaT.getPeriodo());
			pStmt.setObject(6, pecaT.getLargura());
			pStmt.setObject(7, pecaT.getAltura());
			pStmt.setObject(8, pecaT.getMaterial());
			pStmt.setObject(9, pecaT.getNumeroRegistro());
			pStmt.setObject(10, pecaT.getProfundidade());
			pStmt.setObject(11, pecaT.getHistorico());
			pStmt.setObject(12, pecaT.getStatus());
			pStmt.setObject(13, pecaT.getEstado());
			pStmt.setObject(14, pecaT.getPreco());
			pStmt.setObject(15, pecaT.getDisponivelLeilao());
			pStmt.setObject(16, pecaT.getAtivo());

			pStmt.execute();
		} finally {
			DaoUtils.closePreparedStatement(pStmt);
			DaoUtils.closeConnection(connection);
		}
	}

	public void update(PecaT pecaT) throws SQLException {
		PreparedStatement pStmt = null;

		try {
			pStmt = connection.prepareStatement(SQL_UPDATE);
			pStmt.setObject(1, pecaT.getClassificacao());
			pStmt.setObject(2, pecaT.getNome());
			pStmt.setObject(3, pecaT.getDescricao());
			pStmt.setObject(4, pecaT.getAutor());
			pStmt.setObject(5, pecaT.getPeriodo());
			pStmt.setObject(6, pecaT.getLargura());
			pStmt.setObject(7, pecaT.getAltura());
			pStmt.setObject(8, pecaT.getMaterial());
			pStmt.setObject(9, pecaT.getNumeroRegistro());
			pStmt.setObject(10, pecaT.getProfundidade());
			pStmt.setObject(11, pecaT.getHistorico());
			pStmt.setObject(12, pecaT.getStatus());
			pStmt.setObject(13, pecaT.getEstado());
			pStmt.setObject(14, pecaT.getPreco());
			pStmt.setObject(15, pecaT.getDisponivelLeilao());
			pStmt.setObject(16, pecaT.getAtivo());
			pStmt.setObject(17, pecaT.getId());

			pStmt.executeUpdate();
		} finally {
			DaoUtils.closePreparedStatement(pStmt);
			DaoUtils.closeConnection(connection);
		}
	}

	public void inactivate(PecaT pecaT) throws SQLException {
		PreparedStatement pStmt = null;

		try {
			pStmt = connection.prepareStatement(SQL_INACTIVATE);
			pStmt.setObject(1, pecaT.getId());

			pStmt.executeUpdate();
		} finally {
			DaoUtils.closePreparedStatement(pStmt);
			DaoUtils.closeConnection(connection);
		}
	}
}
