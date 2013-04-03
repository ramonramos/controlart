package com.controlart.transfer;

import java.io.Serializable;

public class ImagemT implements Serializable {

	private static final long serialVersionUID = 1L;

	private int id;
	private int peca;
	private String nome;
	private String descricao;
	private String caminho;
	private int ativo;

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

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getCaminho() {
		return caminho;
	}

	public void setCaminho(String caminho) {
		this.caminho = caminho;
	}

	public int getAtivo() {
		return ativo;
	}

	public void setAtivo(int ativo) {
		this.ativo = ativo;
	}
}
