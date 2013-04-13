package com.controlart.transfer;

import java.io.Serializable;
import java.math.BigDecimal;

public class PecaT implements Cloneable, Serializable {

	private static final long serialVersionUID = 1L;

	private int id;
	private int acervo;
	private int classificacao;
	private String nome;
	private String descricao;
	private String autor;
	private String periodo;
	private Double largura;
	private Double altura;
	private Double profundidade;
	private String material;
	private String numeroRegistro;
	private String historico;
	private String estado;
	private BigDecimal preco;
	private String precoString;
	private int disponivelLeilao;
	private int ativo;

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

	public int getClassificacao() {
		return classificacao;
	}

	public void setClassificacao(int classificacao) {
		this.classificacao = classificacao;
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

	public String getAutor() {
		return autor;
	}

	public void setAutor(String autor) {
		this.autor = autor;
	}

	public String getPeriodo() {
		return periodo;
	}

	public void setPeriodo(String periodo) {
		this.periodo = periodo;
	}

	public Double getLargura() {
		return largura;
	}

	public void setLargura(Double largura) {
		this.largura = largura;
	}

	public Double getAltura() {
		return altura;
	}

	public void setAltura(Double altura) {
		this.altura = altura;
	}

	public Double getProfundidade() {
		return profundidade;
	}

	public void setProfundidade(Double profundidade) {
		this.profundidade = profundidade;
	}

	public String getMaterial() {
		return material;
	}

	public void setMaterial(String material) {
		this.material = material;
	}

	public BigDecimal getPreco() {
		return preco;
	}

	public void setPreco(BigDecimal preco) {
		this.preco = preco;
	}

	public String getHistorico() {
		return historico;
	}

	public void setHistorico(String historico) {
		this.historico = historico;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public int getDisponivelLeilao() {
		return disponivelLeilao;
	}

	public void setDisponivelLeilao(int disponivelLeilao) {
		this.disponivelLeilao = disponivelLeilao;
	}

	public int getAtivo() {
		return ativo;
	}

	public void setAtivo(int ativo) {
		this.ativo = ativo;
	}

	public int getAcervo() {
		return acervo;
	}

	public void setAcervo(int acervo) {
		this.acervo = acervo;
	}

	public String getNumeroRegistro() {
		return numeroRegistro;
	}

	public void setNumeroRegistro(String numeroRegistro) {
		this.numeroRegistro = numeroRegistro;
	}

	public String getPrecoString() {
		return precoString;
	}

	public void setPrecoString(String precoString) {
		this.precoString = precoString;
	}
}
