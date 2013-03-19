package com.controlart.bean;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.model.SelectItem;

import com.controlart.bean.interfac.ControlArtBeanInterface;
import com.controlart.bean.utils.BeanUtils;
import com.controlart.dao.ClassificacaoDao;
import com.controlart.dao.PecaDao;
import com.controlart.transfer.ClassificacaoT;
import com.controlart.transfer.PecaT;

@ManagedBean(name = "pecaBean")
@ViewScoped
public class PecaBean extends ControlArtBean implements ControlArtBeanInterface {

	private static final long serialVersionUID = 1L;

	private PecaT peca;
	private List<PecaT> listPeca;
	private boolean novoRegistro;

	private List<SelectItem> listClassificacao;

	private HashMap<Integer, String> hashClassificacao;

	public PecaBean() {
		listPeca = new ArrayList<PecaT>(0);

		clearAction();
		consultAction();
		consultClassificacao();
	}

	@Override
	public void clearAction() {
		peca = new PecaT();
	}

	@Override
	public void consultAction() {
		try {
			PecaDao pecaDao = new PecaDao();
			listPeca = pecaDao.consultAll();
		} catch (SQLException sql) {
			sql.printStackTrace();
			addFacesMessage(getObjectFromBundle("msErroGenerico"), null,
					BeanUtils.SEVERITY_FATAL);
		}
	}

	private void consultClassificacao() {
		try {
			ClassificacaoDao classificacaoDao = new ClassificacaoDao();

			List<ClassificacaoT> _listClassificacao = classificacaoDao
					.consultAll();

			listClassificacao = new ArrayList<SelectItem>(0);
			hashClassificacao = new HashMap<Integer, String>(0);

			for (ClassificacaoT classificacaoT : _listClassificacao) {
				if (classificacaoT.getAtivo() == 1) {
					listClassificacao.add(new SelectItem(
							classificacaoT.getId(), classificacaoT.getNome()));
				}

				hashClassificacao.put(classificacaoT.getId(),
						classificacaoT.getNome());
			}
		} catch (SQLException sql) {
			sql.printStackTrace();
			addFacesMessage(getObjectFromBundle("msErroGenerico"), null,
					BeanUtils.SEVERITY_FATAL);
		}
	}

	@Override
	public void definirNovo() {
		novoRegistro = true;

		clearAction();

		peca.setAtivo(1);
	}

	@Override
	public void definirEditar() {
		novoRegistro = false;

		try {
			peca = (PecaT) ((PecaT) getFacesObject("listaPeca")).clone();
		} catch (CloneNotSupportedException cns) {
			cns.printStackTrace();
			addFacesMessage(getObjectFromBundle("msErroGenerico"), null,
					BeanUtils.SEVERITY_FATAL);
		}
	}

	@Override
	public void saveAction() {
		if (novoRegistro) {
			insertAction();
		} else {
			updateAction();
		}
	}

	@Override
	public void insertAction() {
		try {
			PecaDao pecaDao = new PecaDao();
			pecaDao.insert(peca);

			definirNovo();
			consultAction();

			addFacesMessage(getObjectFromBundle("msRegistroInserido"), null,
					BeanUtils.SEVERITY_INFO);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void updateAction() {
		try {
			PecaDao pecaDao = new PecaDao();
			pecaDao.update(peca);

			consultAction();

			addFacesMessage(getObjectFromBundle("msRegistroAtualizado"), null,
					BeanUtils.SEVERITY_INFO);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void inactivateAction() {
		peca = (PecaT) getFacesObject("listaPeca");

		try {
			PecaDao pecaDao = new PecaDao();
			pecaDao.inactivate(peca);

			consultAction();

			addFacesMessage(getObjectFromBundle("msRegistroRemovido"), null,
					BeanUtils.SEVERITY_INFO);
		} catch (SQLException sqlEx) {
			sqlEx.printStackTrace();
		}
	}

	public String getClassificacao(int key) {
		return hashClassificacao.get(key);
	}

	public PecaT getPeca() {
		return peca;
	}

	public void setPeca(PecaT peca) {
		this.peca = peca;
	}

	public List<PecaT> getListPeca() {
		return listPeca;
	}

	public void setListPeca(List<PecaT> listPeca) {
		this.listPeca = listPeca;
	}

	public boolean isNovoRegistro() {
		return novoRegistro;
	}

	public void setNovoRegistro(boolean novoRegistro) {
		this.novoRegistro = novoRegistro;
	}

	public List<SelectItem> getListClassificacao() {
		return listClassificacao;
	}

	public void setListClassificacao(List<SelectItem> listClassificacao) {
		this.listClassificacao = listClassificacao;
	}

	public HashMap<Integer, String> getHashClassificacao() {
		return hashClassificacao;
	}

	public void setHashClassificacao(HashMap<Integer, String> hashClassificacao) {
		this.hashClassificacao = hashClassificacao;
	}
}
