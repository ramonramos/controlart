package com.controlart.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.controlart.dao.factory.ConnFactory;
import com.controlart.dao.utils.DaoUtils;
import com.controlart.transfer.UsuarioT;

public class LoginDao {
	private Connection connection;

	private static final String SQL_CONSULT = "SELECT tu.id_usuario, tu.id_tipo_usuario, tu.id_pessoa, tu.ds_login, tu.ds_senha, tu.in_primeiro_acesso, tp.id_tipo_pessoa, tp.nm_pessoa, tp.nr_fone, tp.ds_email, nm_rua, nr_imovel, nm_bairro, nm_cidade FROM tb_usuario tu JOIN tb_pessoa tp on tp.id_pessoa = tu.id_pessoa WHERE tu.ds_login = ? AND tu.ds_senha = ? AND tu.in_ativo = 1";

	public LoginDao() throws SQLException {
		connection = ConnFactory.getConnection();
	}

	public UsuarioT consult(UsuarioT usuarioT) throws SQLException {
		PreparedStatement pStmt = null;
		ResultSet rs = null;

		try {
			pStmt = connection.prepareStatement(SQL_CONSULT);
			pStmt.setObject(1, usuarioT.getDsLogin());
			pStmt.setObject(2, usuarioT.getCdSenha());

			rs = pStmt.executeQuery();

			return resultsetToObjectUsuarioT(rs);
		} finally {
			DaoUtils.closeStatementAndResultSet(pStmt, rs);
			DaoUtils.closeConnection(connection);
		}
	}

	private UsuarioT resultsetToObjectUsuarioT(ResultSet rs)
			throws SQLException {
		UsuarioT usuarioT = null;

		while (rs.next()) {
			usuarioT = new UsuarioT();

			usuarioT.setIdUsuario(rs.getInt("ID_USUARIO"));
			usuarioT.setIdTipoUsuario(rs.getInt("ID_TIPO_USUARIO"));
			usuarioT.setIdPessoa(rs.getInt("ID_PESSOA"));
			usuarioT.setDsLogin(rs.getString("DS_LOGIN"));
			usuarioT.setCdSenha(rs.getString("DS_SENHA"));
			usuarioT.setPrimeiroAcesso(rs.getInt("IN_PRIMEIRO_ACESSO") == 0 ? true
					: false);
			usuarioT.setIdTipoPessoa(rs.getInt("ID_TIPO_PESSOA"));
			usuarioT.setNmPessoa(rs.getString("NM_PESSOA"));
			usuarioT.setNrFone(rs.getString("NR_FONE"));
			usuarioT.setDsEmail(rs.getString("DS_EMAIL"));
			usuarioT.setNmRua(rs.getString("NM_RUA"));
			usuarioT.setNrImovel(rs.getString("NR_IMOVEL"));
			usuarioT.setNmBairro(rs.getString("NM_BAIRRO"));
			usuarioT.setNmCidade(rs.getString("NM_CIDADE"));
		}

		return usuarioT;
	}
}
