package com.controlart.transfer;

public class TipoUsuarioT {
	private int idTipoUsuario;
	private String nmTipoUsuario;
	private String dsTipoUsuario;
	private int situacao;

	public int getIdTipoUsuario() {
		return idTipoUsuario;
	}

	public void setIdTipoUsuario(int idTipoUsuario) {
		this.idTipoUsuario = idTipoUsuario;
	}

	public String getNmTipoUsuario() {
		return nmTipoUsuario;
	}

	public void setNmTipoUsuario(String nmTipoUsuario) {
		this.nmTipoUsuario = nmTipoUsuario;
	}

	public String getDsTipoUsuario() {
		return dsTipoUsuario;
	}

	public void setDsTipoUsuario(String dsTipoUsuario) {
		this.dsTipoUsuario = dsTipoUsuario;
	}

	public int getSituacao() {
		return situacao;
	}

	public void setSituacao(int situacao) {
		this.situacao = situacao;
	}
}
