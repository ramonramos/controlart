package com.controlart.transfer;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class TransacaoT implements Cloneable, Serializable {

	private static final long serialVersionUID = 1L;

	private int id;
	private int peca;
	private int tipo;
	private int acervoOrigem;
	private int acervoDestino;
	private Date dataTransacao;
	private Date dataPrevisao;
	private Date dataDevolucao;
	private BigDecimal preco;
	private String precoString;
	private int online;

	@Override
	public Object clone() throws CloneNotSupportedException {
		return super.clone();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getPeca() {
		return peca;
	}

	public void setPeca(int peca) {
		this.peca = peca;
	}

	public int getTipo() {
		return tipo;
	}

	public void setTipo(int tipo) {
		this.tipo = tipo;
	}

	public int getAcervoOrigem() {
		return acervoOrigem;
	}

	public void setAcervoOrigem(int acervoOrigem) {
		this.acervoOrigem = acervoOrigem;
	}

	public int getAcervoDestino() {
		return acervoDestino;
	}

	public void setAcervoDestino(int acervoDestino) {
		this.acervoDestino = acervoDestino;
	}

	public BigDecimal getPreco() {
		return preco;
	}

	public void setPreco(BigDecimal preco) {
		this.preco = preco;
	}

	public int getOnline() {
		return online;
	}

	public void setOnline(int online) {
		this.online = online;
	}

	public Date getDataTransacao() {
		return dataTransacao;
	}

	public void setDataTransacao(Date dataTransacao) {
		this.dataTransacao = dataTransacao;
	}

	public Date getDataPrevisao() {
		return dataPrevisao;
	}

	public void setDataPrevisao(Date dataPrevisao) {
		this.dataPrevisao = dataPrevisao;
	}

	public Date getDataDevolucao() {
		return dataDevolucao;
	}

	public void setDataDevolucao(Date dataDevolucao) {
		this.dataDevolucao = dataDevolucao;
	}

	public String getPrecoString() {
		return precoString;
	}

	public void setPrecoString(String precoString) {
		this.precoString = precoString;
	}
}
