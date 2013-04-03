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
import com.controlart.dao.AcervoDao;
import com.controlart.dao.ClassificacaoDao;
import com.controlart.dao.PecaDao;
import com.controlart.transfer.AcervoT;
import com.controlart.transfer.ClassificacaoT;
import com.controlart.transfer.PecaT;

@ManagedBean(name = "pecaBean")
@ViewScoped
public class PecaBean extends ControlArtBean implements ControlArtBeanInterface {

	private static final long serialVersionUID = 1L;

	private PecaT peca;
	private List<PecaT> listPeca;
	private boolean novoRegistro;

	private List<SelectItem> listAcervo;
	private List<SelectItem> listClassificacao;

	private HashMap<Integer, String> hashAcervo;
	private HashMap<Integer, String> hashClassificacao;

	private ImagemBean imagemBean;

	public PecaBean() {
		listPeca = new ArrayList<PecaT>(0);

		clearAction();
		consultAction();
		consultAcervo();
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

		imagemBean = new ImagemBean();
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
			peca.setId(pecaDao.insert(peca));

			imagemBean.setIdPeca(peca.getId());
			imagemBean.insertAction();

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

	public String getAcervo(int key) {
		return hashAcervo.get(key);
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

	public ImagemBean getImagemBean() {
		return imagemBean;
	}

	public void setImagemBean(ImagemBean imagemBean) {
		this.imagemBean = imagemBean;
	}

	public List<SelectItem> getListAcervo() {
		return listAcervo;
	}

	public void setListAcervo(List<SelectItem> listAcervo) {
		this.listAcervo = listAcervo;
	}

	public HashMap<Integer, String> getHashAcervo() {
		return hashAcervo;
	}

	public void setHashAcervo(HashMap<Integer, String> hashAcervo) {
		this.hashAcervo = hashAcervo;
	}
}
