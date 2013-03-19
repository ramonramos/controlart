package com.controlart.bean;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.model.SelectItem;

import com.controlart.bean.utils.BeanUtils;
import com.controlart.dao.AcervoDao;
import com.controlart.dao.PecaDao;
import com.controlart.dao.TipoTransacaoDao;
import com.controlart.dao.TransacaoDao;
import com.controlart.transfer.AcervoT;
import com.controlart.transfer.PecaT;
import com.controlart.transfer.TipoTransacaoT;
import com.controlart.transfer.TransacaoT;

@ManagedBean(name = "transacaoBean")
@ViewScoped
public class TransacaoBean extends ControlArtBean {

	private static final long serialVersionUID = 1L;

	private TransacaoT transacao;
	private List<TransacaoT> listTransacao;
	private boolean novoRegistro;

	private List<SelectItem> listPeca;
	private List<SelectItem> listTipoTransacao;
	private List<SelectItem> listAcervo;

	private HashMap<Integer, String> hashPeca;
	private HashMap<Integer, String> hashTipoTransacao;
	private HashMap<Integer, String> hashAcervo;

	public TransacaoBean() {
		listTransacao = new ArrayList<TransacaoT>(0);

		clearAction();
		consultAction();
		consultPeca();
		consultTipoTransacao();
		consultAcervo();
	}

	private void clearAction() {
		transacao = new TransacaoT();
	}

	private void consultAction() {
		try {
			TransacaoDao transacaoDao = new TransacaoDao();
			listTransacao = transacaoDao.consultAll();
		} catch (SQLException sql) {
			sql.printStackTrace();
			addFacesMessage(getObjectFromBundle("msErroGenerico"), null,
					BeanUtils.SEVERITY_FATAL);
		}
	}

	private void consultPeca() {
		try {
			PecaDao pecaDao = new PecaDao();
			List<PecaT> _listPeca = pecaDao.consultAll();

			listPeca = new ArrayList<SelectItem>(0);
			hashPeca = new HashMap<Integer, String>(0);

			for (PecaT pecaT : _listPeca) {
				if (pecaT.getAtivo() == 1) {
					listPeca.add(new SelectItem(pecaT.getId(), pecaT.getNome()));
				}

				hashPeca.put(pecaT.getId(), pecaT.getNome());
			}
		} catch (SQLException sql) {
			sql.printStackTrace();
			addFacesMessage(getObjectFromBundle("msErroGenerico"), null,
					BeanUtils.SEVERITY_FATAL);
		}
	}

	private void consultTipoTransacao() {
		try {
			TipoTransacaoDao tipoTransacaoDao = new TipoTransacaoDao();
			List<TipoTransacaoT> _listTipoTransacao = tipoTransacaoDao
					.consultAll();

			listTipoTransacao = new ArrayList<SelectItem>(0);
			hashTipoTransacao = new HashMap<Integer, String>(0);

			for (TipoTransacaoT tipoTransacaoT : _listTipoTransacao) {
				if (tipoTransacaoT.getAtivo() == 1) {
					listTipoTransacao.add(new SelectItem(
							tipoTransacaoT.getId(), tipoTransacaoT.getNome()));
				}

				hashTipoTransacao.put(tipoTransacaoT.getId(),
						tipoTransacaoT.getNome());
			}
		} catch (SQLException sql) {
			sql.printStackTrace();
			addFacesMessage(getObjectFromBundle("msErroGenerico"), null,
					BeanUtils.SEVERITY_FATAL);
		}
	}

	private void consultAcervo() {
		try {
			AcervoDao acervoDao = new AcervoDao();
			List<AcervoT> _listAcervo = acervoDao.consultAll();

			listAcervo = new ArrayList<SelectItem>(0);
			hashAcervo = new HashMap<Integer, String>(0);

			for (AcervoT acervoT : _listAcervo) {
				if (acervoT.getAtivo() == 1) {
					listAcervo.add(new SelectItem(acervoT.getId(), acervoT
							.getNome()));
				}

				hashAcervo.put(acervoT.getId(), acervoT.getNome());
			}
		} catch (SQLException sql) {
			sql.printStackTrace();
			addFacesMessage(getObjectFromBundle("msErroGenerico"), null,
					BeanUtils.SEVERITY_FATAL);
		}
	}

	public void definirNovo() {
		novoRegistro = true;

		clearAction();
	}

	public void definirEditar() {
		novoRegistro = false;

		try {
			transacao = (TransacaoT) ((TransacaoT) getFacesObject("listaTransacao"))
					.clone();
		} catch (CloneNotSupportedException cns) {
			cns.printStackTrace();
			addFacesMessage(getObjectFromBundle("msErroGenerico"), null,
					BeanUtils.SEVERITY_FATAL);
		}
	}

	public void insertAction() {
		try {
			TransacaoDao transacaoDao = new TransacaoDao();
			transacaoDao.insert(transacao);

			definirNovo();
			consultAction();

			addFacesMessage(getObjectFromBundle("msRegistroInserido"), null,
					BeanUtils.SEVERITY_INFO);
		} catch (SQLException sql) {
			sql.printStackTrace();
		}
	}

	public String getPeca(int key) {
		return hashPeca.get(key);
	}

	public String getTipoTransacao(int key) {
		return hashTipoTransacao.get(key);
	}

	public String getAcervo(int key) {
		return hashAcervo.get(key);
	}

	public TransacaoT getTransacao() {
		return transacao;
	}

	public void setTransacao(TransacaoT transacao) {
		this.transacao = transacao;
	}

	public List<TransacaoT> getListTransacao() {
		return listTransacao;
	}

	public void setListTransacao(List<TransacaoT> listTransacao) {
		this.listTransacao = listTransacao;
	}

	public boolean isNovoRegistro() {
		return novoRegistro;
	}

	public void setNovoRegistro(boolean novoRegistro) {
		this.novoRegistro = novoRegistro;
	}

	public List<SelectItem> getListTipoTransacao() {
		return listTipoTransacao;
	}

	public void setListTipoTransacao(List<SelectItem> listTipoTransacao) {
		this.listTipoTransacao = listTipoTransacao;
	}

	public List<SelectItem> getListAcervo() {
		return listAcervo;
	}

	public void setListAcervo(List<SelectItem> listAcervo) {
		this.listAcervo = listAcervo;
	}

	public HashMap<Integer, String> getHashTipoTransacao() {
		return hashTipoTransacao;
	}

	public void setHashTipoTransacao(HashMap<Integer, String> hashTipoTransacao) {
		this.hashTipoTransacao = hashTipoTransacao;
	}

	public HashMap<Integer, String> getHashAcervo() {
		return hashAcervo;
	}

	public void setHashAcervo(HashMap<Integer, String> hashAcervo) {
		this.hashAcervo = hashAcervo;
	}

	public List<SelectItem> getListPeca() {
		return listPeca;
	}

	public void setListPeca(List<SelectItem> listPeca) {
		this.listPeca = listPeca;
	}

	public HashMap<Integer, String> getHashPeca() {
		return hashPeca;
	}

	public void setHashPeca(HashMap<Integer, String> hashPeca) {
		this.hashPeca = hashPeca;
	}

}
