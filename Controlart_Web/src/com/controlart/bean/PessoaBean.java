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
import com.controlart.dao.PessoaDao;
import com.controlart.dao.TipoPessoaDao;
import com.controlart.transfer.PessoaT;
import com.controlart.transfer.TipoPessoaT;

@ManagedBean
@ViewScoped
public class PessoaBean extends ControlArtBean implements
		ControlArtBeanInterface {

	private static final long serialVersionUID = 1L;

	private PessoaT pessoa;
	private List<PessoaT> listPessoa;
	private boolean novoRegistro;

	private List<SelectItem> listTipoPessoa;

	private HashMap<Integer, String> hashTipoPessoa;

	public PessoaBean() {
		listPessoa = new ArrayList<PessoaT>(0);

		clearAction();
		consultAction();
		consultTipoPessoa();
	}

	@Override
	public void clearAction() {
		pessoa = new PessoaT();
	}

	@Override
	public void consultAction() {
		try {
			PessoaDao pessoaDao = new PessoaDao();

			listPessoa = pessoaDao.consultAll();
		} catch (SQLException sql) {
			sql.printStackTrace();
			addFacesMessage(getObjectFromBundle("msErroGenerico"), null,
					BeanUtils.SEVERITY_FATAL);
		}
	}

	public void consultTipoPessoa() {
		try {
			TipoPessoaDao tipoPessoaDao = new TipoPessoaDao();

			List<TipoPessoaT> _listTipoPessoa = tipoPessoaDao.consultAll();

			listTipoPessoa = new ArrayList<SelectItem>(0);
			hashTipoPessoa = new HashMap<Integer, String>(0);

			for (TipoPessoaT tipoPessoaT : _listTipoPessoa) {
				if (tipoPessoaT.getSituacao() == 1) {
					listTipoPessoa.add(new SelectItem(tipoPessoaT
							.getIdTipoPessoa(), tipoPessoaT.getNmTipoPessoa()));
				}

				hashTipoPessoa.put(tipoPessoaT.getIdTipoPessoa(),
						tipoPessoaT.getNmTipoPessoa());
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

		pessoa.setSituacao(1);
	}

	@Override
	public void definirEditar() {
		novoRegistro = false;

		try {
			pessoa = (PessoaT) ((PessoaT) getFacesObject("listaPessoa"))
					.clone();
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
			PessoaDao pessoaDao = new PessoaDao();
			pessoaDao.insert(pessoa);

			definirNovo();
			consultAction();

			addFacesMessage(getObjectFromBundle("msRegistroInserido"), null,
					BeanUtils.SEVERITY_INFO);
		} catch (SQLException sql) {
			sql.printStackTrace();
		}
	}

	@Override
	public void updateAction() {
		try {
			PessoaDao pessoaDao = new PessoaDao();
			pessoaDao.update(pessoa);

			if (pessoa.getIdPessoa() == getUsuarioLogado().getIdPessoa()) {
				atualizarDadosUsuarioLogado();
			}

			consultAction();

			addFacesMessage(getObjectFromBundle("msRegistroAtualizado"), null,
					BeanUtils.SEVERITY_INFO);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private void atualizarDadosUsuarioLogado() {
		getUsuarioLogado().setIdTipoPessoa(pessoa.getIdTipoPessoa());
		getUsuarioLogado().setNmPessoa(pessoa.getNmPessoa());
		getUsuarioLogado().setNrFone(pessoa.getNrFone());
		getUsuarioLogado().setDsEmail(pessoa.getDsEmail());
		getUsuarioLogado().setNmRua(pessoa.getNmRua());
		getUsuarioLogado().setNrImovel(pessoa.getNrImovel());
		getUsuarioLogado().setNmBairro(pessoa.getNmBairro());
		getUsuarioLogado().setNmCidade(pessoa.getNmCidade());
		getUsuarioLogado().setSituacao(pessoa.getSituacao());
	}

	@Override
	public void inactivateAction() {
		pessoa = (PessoaT) getFacesObject("listaPessoa");

		try {
			PessoaDao pessoaDao = new PessoaDao();
			pessoaDao.inativate(pessoa);

			consultAction();

			addFacesMessage(getObjectFromBundle("msRegistroRemovido"), null,
					BeanUtils.SEVERITY_INFO);
		} catch (SQLException sqlEx) {
			sqlEx.printStackTrace();
		}
	}

	public String getTipoPessoa(int key) {
		return hashTipoPessoa.get(key);
	}

	public PessoaT getPessoa() {
		return pessoa;
	}

	public void setPessoa(PessoaT pessoa) {
		this.pessoa = pessoa;
	}

	public List<PessoaT> getListPessoa() {
		return listPessoa;
	}

	public void setListPessoa(List<PessoaT> listPessoa) {
		this.listPessoa = listPessoa;
	}

	public boolean isNovoRegistro() {
		return novoRegistro;
	}

	public void setNovoRegistro(boolean novoRegistro) {
		this.novoRegistro = novoRegistro;
	}

	public List<SelectItem> getListTipoPessoa() {
		return listTipoPessoa;
	}

	public void setListTipoPessoa(List<SelectItem> listTipoPessoa) {
		this.listTipoPessoa = listTipoPessoa;
	}
}
