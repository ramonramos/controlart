package com.controlart.transfer;

import java.io.Serializable;

public class TransacaoInfoT implements Serializable {

	private static final long serialVersionUID = 1L;

	private String TipoTransacao;
	private String peca;

	public String getTipoTransacao() {
		return TipoTransacao;
	}

	public void setTipoTransacao(String tipoTransacao) {
		TipoTransacao = tipoTransacao;
	}

	public String getPeca() {
		return peca;
	}

	public void setPeca(String peca) {
		this.peca = peca;
	}
}
