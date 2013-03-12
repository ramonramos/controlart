package com.controlart.bean;

import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import com.controlart.bean.utils.BeanUtils;
import com.controlart.dao.AlterarSenhaDao;
import com.controlart.transfer.UsuarioT;

@ManagedBean(name = "alterarSenhaBean")
@ViewScoped
public class AlterarSenhaBean extends ControlArtBean {

	private static final long 	serialVersionUID = 1L;

	private UsuarioT		 	usuario;

	public AlterarSenhaBean() {
		usuario = new UsuarioT();
	}

	private void clearAction() {
		usuario = new UsuarioT();
	}

	public void updateAction() {
		try {
			String senhaAtual = BeanUtils.encryptPassword(usuario.getCdSenha());

			if (!(senhaAtual.equals(getUsuarioLogado().getCdSenha()))) {
				addFacesMessage(getObjectFromBundle("msSenhaAtualInvalida"),
						null, BeanUtils.SEVERITY_ERROR);

				return;
			}

			if (usuario.getCdNovaSenha()
					.equals(usuario.getCdConfirmNovaSenha())) {
				usuario.setIdPessoa(getUsuarioLogado().getIdPessoa());
				usuario.setCdSenha(BeanUtils.encryptPassword(usuario
						.getCdNovaSenha()));

				AlterarSenhaDao alterarSenhaDao = new AlterarSenhaDao();
				alterarSenhaDao.update(usuario);

				getUsuarioLogado().setCdSenha(usuario.getCdSenha());

				addFacesMessage(getObjectFromBundle("msRegistroAtualizado"),
						null, BeanUtils.SEVERITY_INFO);
			} else {
				addFacesMessage(
						getObjectFromBundle("msNovaSenhaConfirmDiferem"), null,
						BeanUtils.SEVERITY_ERROR);

				return;
			}

			clearAction();
		} catch (NoSuchAlgorithmException nsa) {
			nsa.printStackTrace();
		} catch (SQLException sql) {
			sql.printStackTrace();
		}
	}

	public UsuarioT getUsuario() {
		return usuario;
	}

	public void setUsuario(UsuarioT usuario) {
		this.usuario = usuario;
	}
}