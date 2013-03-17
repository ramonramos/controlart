package com.controlart.transfer;

import java.io.Serializable;

public class TipoPessoaT implements Serializable, Cloneable {

	private static final long serialVersionUID = 1L;

	private int idTipoPessoa;
	private String nmTipoPessoa;
	private String dsTipoPessoa;
	private int situacao;

	@Override
	public Object clone() throws CloneNotSupportedException {
		return super.clone();
	}

	public int getIdTipoPessoa() {
		return idTipoPessoa;
	}

	public void setIdTipoPessoa(int idTipoPessoa) {
		this.idTipoPessoa = idTipoPessoa;
	}

	public String getNmTipoPessoa() {
		return nmTipoPessoa;
	}

	public void setNmTipoPessoa(String nmTipoPessoa) {
		this.nmTipoPessoa = nmTipoPessoa;
	}

	public String getDsTipoPessoa() {
		return dsTipoPessoa;
	}

	public void setDsTipoPessoa(String dsTipoPessoa) {
		this.dsTipoPessoa = dsTipoPessoa;
	}

	public int getSituacao() {
		return situacao;
	}

	public void setSituacao(int situacao) {
		this.situacao = situacao;
	}
}
