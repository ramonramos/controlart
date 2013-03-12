package com.controlart.bean;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import com.controlart.bean.interfac.ControlArtBeanInterface;
import com.controlart.bean.utils.BeanUtils;
import com.controlart.dao.TipoPessoaDao;
import com.controlart.transfer.TipoPessoaT;

@ManagedBean(name = "tipoPessoaBean")
@ViewScoped
public class TipoPessoaBean extends ControlArtBean implements
		ControlArtBeanInterface {

	private static final long 	serialVersionUID = 1L;

	private TipoPessoaT 		tipoPessoa;
	private List<TipoPessoaT> 	listTipoPessoa;
	private boolean 			novoRegistro;

	public TipoPessoaBean() {
		listTipoPessoa = new ArrayList<TipoPessoaT>(0);

		clearAction();
		consultAction();
	}

	@Override
	public void clearAction() {
		tipoPessoa = new TipoPessoaT();
	}

	@Override
	public void consultAction() {
		try {
			TipoPessoaDao tipoPessoaDao = new TipoPessoaDao();
			listTipoPessoa = tipoPessoaDao.consultAll();
		} catch (SQLException sql) {
			sql.printStackTrace();
		}
	}

	@Override
	public void definirNovo() {
		novoRegistro = true;

		clearAction();

		tipoPessoa.setSituacao(1);
	}

	@Override
	public void definirEditar() {
		novoRegistro = false;

		try {
			tipoPessoa = (TipoPessoaT) ((TipoPessoaT) getFacesObject("listaTipoPessoa"))
					.clone();
		} catch (CloneNotSupportedException cns) {
			cns.printStackTrace();
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
			TipoPessoaDao tipoPessoaDao = new TipoPessoaDao();
			tipoPessoaDao.insert(tipoPessoa);

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
			TipoPessoaDao tipoPessoaDao = new TipoPessoaDao();
			tipoPessoaDao.update(tipoPessoa);

			consultAction();

			addFacesMessage(getObjectFromBundle("msRegistroAtualizado"), null,
					BeanUtils.SEVERITY_INFO);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void deleteAction() {
		tipoPessoa = (TipoPessoaT) getFacesObject("listaTipoPessoa");

		try {
			TipoPessoaDao tipoPessoaDao = new TipoPessoaDao();
			tipoPessoaDao.delete(getTipoPessoa());

			consultAction();

			addFacesMessage(getObjectFromBundle("msRegistroRemovido"), null,
					BeanUtils.SEVERITY_INFO);
		} catch (SQLException sqlEx) {
			sqlEx.printStackTrace();
		}
	}

	public TipoPessoaT getTipoPessoa() {
		return tipoPessoa;
	}

	public void setTipoPessoa(TipoPessoaT tipoPessoa) {
		this.tipoPessoa = tipoPessoa;
	}

	public List<TipoPessoaT> getListTipoPessoa() {
		return listTipoPessoa;
	}

	public void setListTipoPessoa(List<TipoPessoaT> listTipoPessoa) {
		this.listTipoPessoa = listTipoPessoa;
	}

	public boolean isNovoRegistro() {
		return novoRegistro;
	}

	public void setNovoRegistro(boolean novoRegistro) {
		this.novoRegistro = novoRegistro;
	}
}