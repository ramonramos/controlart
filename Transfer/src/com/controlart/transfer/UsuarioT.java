package com.controlart.transfer;

public class UsuarioT extends PessoaT implements Cloneable {

	private static final long serialVersionUID = 1L;

	private int idUsuario;
	private int tipoUsuario;
	private String login;
	private String senha;
	private String novaSenha;
	private String confirmNovaSenha;
	private int ativo;

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

	public int getTipoUsuario() {
		return tipoUsuario;
	}

	public void setTipoUsuario(int tipoUsuario) {
		this.tipoUsuario = tipoUsuario;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getNovaSenha() {
		return novaSenha;
	}

	public void setNovaSenha(String novaSenha) {
		this.novaSenha = novaSenha;
	}

	public String getConfirmNovaSenha() {
		return confirmNovaSenha;
	}

	public void setConfirmNovaSenha(String confirmNovaSenha) {
		this.confirmNovaSenha = confirmNovaSenha;
	}

	public int getAtivo() {
		return ativo;
	}

	public void setAtivo(int ativo) {
		this.ativo = ativo;
	}
}
