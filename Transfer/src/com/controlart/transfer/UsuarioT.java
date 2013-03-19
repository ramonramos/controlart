package com.controlart.transfer;

public class UsuarioT extends PessoaT implements Cloneable {

	private static final long serialVersionUID = 1L;

	private int idUsuario;
	private int idTipoUsuario;
	private String dsLogin;
	private String cdSenha;
	private String cdNovaSenha;
	private String cdConfirmNovaSenha;
	private boolean primeiroAcesso;
	private int situacao;

	@Override
	public Object clone() throws CloneNotSupportedException {
		return super.clone();
	}

	public int getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(int idUsuario) {
		this.idUsuario = idUsuario;
	}

	public int getIdTipoUsuario() {
		return idTipoUsuario;
	}

	public void setIdTipoUsuario(int idTipoUsuario) {
		this.idTipoUsuario = idTipoUsuario;
	}

	public String getDsLogin() {
		return dsLogin;
	}

	public void setDsLogin(String dsLogin) {
		this.dsLogin = dsLogin;
	}

	public String getCdSenha() {
		return cdSenha;
	}

	public void setCdSenha(String cdSenha) {
		this.cdSenha = cdSenha;
	}

	public String getCdNovaSenha() {
		return cdNovaSenha;
	}

	public void setCdNovaSenha(String cdNovaSenha) {
		this.cdNovaSenha = cdNovaSenha;
	}

	public String getCdConfirmNovaSenha() {
		return cdConfirmNovaSenha;
	}

	public void setCdConfirmNovaSenha(String cdConfirmNovaSenha) {
		this.cdConfirmNovaSenha = cdConfirmNovaSenha;
	}

	public boolean isPrimeiroAcesso() {
		return primeiroAcesso;
	}

	public void setPrimeiroAcesso(boolean primeiroAcesso) {
		this.primeiroAcesso = primeiroAcesso;
	}

	public int getSituacao() {
		return situacao;
	}

	public void setSituacao(int situacao) {
		this.situacao = situacao;
	}
}
