package com.controlart.transfer;

import java.io.Serializable;

public class ImagemT implements Serializable {

	private static final long serialVersionUID = 1L;

	private int id;
	private int peca;
	private String nome;
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

	public int getAtivo() {
		return ativo;
	}

	public void setAtivo(int ativo) {
		this.ativo = ativo;
	}
}
