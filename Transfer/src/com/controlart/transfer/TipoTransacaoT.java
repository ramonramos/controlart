package com.controlart.transfer;

import java.io.Serializable;

public class TipoTransacaoT implements Serializable {

	private static final long serialVersionUID = 1L;

	private int id;
	private String nome;
	private char operacao;
	private String descricao;
	private int ativo;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public char getOperacao() {
		return operacao;
	}

	public void setOperacao(char operacao) {
		this.operacao = operacao;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public int getAtivo() {
		return ativo;
	}

	public void setAtivo(int ativo) {
		this.ativo = ativo;
	}
}
