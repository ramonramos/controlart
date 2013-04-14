package com.controlart.transfer;

import java.io.Serializable;

public class PessoaT implements Serializable, Cloneable {

	private static final long serialVersionUID = 1L;

	private int idPessoa;
	private int tipoPessoa;
	private boolean possuiAcervo;
	private String nomeAcervo;
	private String nome;
	private String fone;
	private String email;
	private String logradouro;
	private String numero;
	private String bairro;
	private String cidade;
	private int ativo;

	@Override
	public Object clone() throws CloneNotSupportedException {
		return super.clone();
	}

	public int getIdPessoa() {
		return idPessoa;
	}

	public void setIdPessoa(int idPessoa) {
		this.idPessoa = idPessoa;
	}

	public int getTipoPessoa() {
		return tipoPessoa;
	}

	public void setTipoPessoa(int tipoPessoa) {
		this.tipoPessoa = tipoPessoa;
	}

	public boolean isPossuiAcervo() {
		return possuiAcervo;
	}

	public void setPossuiAcervo(boolean possuiAcervo) {
		this.possuiAcervo = possuiAcervo;
	}

	public String getNomeAcervo() {
		return nomeAcervo;
	}

	public void setNomeAcervo(String nomeAcervo) {
		this.nomeAcervo = nomeAcervo;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getFone() {
		return fone;
	}

	public void setFone(String fone) {
		this.fone = fone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getLogradouro() {
		return logradouro;
	}

	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public int getAtivo() {
		return ativo;
	}

	public void setAtivo(int ativo) {
		this.ativo = ativo;
	}
}
