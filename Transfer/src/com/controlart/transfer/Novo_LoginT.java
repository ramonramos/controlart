package com.controlart.transfer;

public class Novo_LoginT extends LoginT {

	private static final long serialVersionUID = 1L;

	private int    id_pessoa;
	private String ds_novo_login;
	private String ds_nova_senha;
	private String ds_confirm_nova_senha;

	public String getDs_novo_login() {
		return ds_novo_login;
	}

	public void setDs_novo_login(String ds_novo_login) {
		this.ds_novo_login = ds_novo_login;
	}

	public String getDs_nova_senha() {
		return ds_nova_senha;
	}

	public void setDs_nova_senha(String ds_nova_senha) {
		this.ds_nova_senha = ds_nova_senha;
	}

	public String getDs_confirm_nova_senha() {
		return ds_confirm_nova_senha;
	}

	public void setDs_confirm_nova_senha(String ds_confirm_nova_senha) {
		this.ds_confirm_nova_senha = ds_confirm_nova_senha;
	}

	public int getId_pessoa() {
		return id_pessoa;
	}

	public void setId_pessoa(int id_pessoa) {
		this.id_pessoa = id_pessoa;
	}

}
