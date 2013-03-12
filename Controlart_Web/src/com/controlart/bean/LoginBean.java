package com.controlart.bean;

import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import com.controlart.bean.utils.BeanUtils;
import com.controlart.dao.LoginDao;
import com.controlart.transfer.UsuarioT;

@ManagedBean(name = "loginBean")
@ViewScoped
public class LoginBean extends ControlArtBean {

	private static final long serialVersionUID = 1L;

	private UsuarioT usuario;

	public LoginBean() {
		usuario = new UsuarioT();

		if (getSessionAtribute(BeanUtils.LOGGEDUSER_SESSION_ATTRIBUTE) != null) {
			removeSessionAtribute(BeanUtils.LOGGEDUSER_SESSION_ATTRIBUTE);
			invalidateSession();
		}
	}

	public String login() {
		try {
			usuario.setCdSenha(BeanUtils.encryptPassword(usuario.getCdSenha()));

			LoginDao loginDao = new LoginDao();

			setUsuarioLogado(loginDao.consult(usuario));

			if (getUsuarioLogado() == null) {
				addFacesMessage(getObjectFromBundle("msUsuarioNaoEncontrado"),
						null, BeanUtils.SEVERITY_ERROR);

				return null;
			} else {
				addSessionAtribute(BeanUtils.LOGGEDUSER_SESSION_ATTRIBUTE,
						getUsuarioLogado());

				return "/pages/home.xhtml";
			}
		} catch (NoSuchAlgorithmException nsa) {
			nsa.printStackTrace();
		} catch (SQLException sql) {
			sql.printStackTrace();
		}

		return null;
	}

	public UsuarioT getUsuario() {
		return usuario;
	}

	public void setUsuario(UsuarioT usuario) {
		this.usuario = usuario;
	}
}