package com.controlart.bean;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import com.controlart.bean.utils.BeanUtils;
import com.controlart.transfer.UsuarioT;

@ManagedBean(name = "templateBean")
@SessionScoped
public class TemplateBean extends ControlArtBean {

	private static final long serialVersionUID = 1L;

	private UsuarioT usuario;

	public TemplateBean() {
		usuario = getUsuarioLogado();
	}

	public void logout() {
		removeSessionAtribute(BeanUtils.LOGGEDUSER_SESSION_ATTRIBUTE);

		invalidateSession();

		FacesContext
				.getCurrentInstance()
				.getApplication()
				.getNavigationHandler()
				.handleNavigation(FacesContext.getCurrentInstance(), null,
						BeanUtils.LOGIN_PAGE);
	}

	public UsuarioT getUsuario() {
		return usuario;
	}

	public void setUsuario(UsuarioT usuario) {
		this.usuario = usuario;
	}
}
