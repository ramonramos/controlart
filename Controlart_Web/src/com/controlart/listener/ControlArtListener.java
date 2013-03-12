package com.controlart.listener;

import javax.faces.context.FacesContext;
import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;

import com.controlart.bean.utils.BeanUtils;
import com.controlart.transfer.PessoaT;

public class ControlArtListener implements PhaseListener {

	private static final long serialVersionUID = 1L;

	@Override
	public void afterPhase(PhaseEvent event) {
		PessoaT loggedUser = (PessoaT) FacesContext.getCurrentInstance()
				.getExternalContext().getSessionMap()
				.get(BeanUtils.LOGGEDUSER_SESSION_ATTRIBUTE);

		if (!getCurrentPage().equalsIgnoreCase(BeanUtils.LOGIN_PAGE)
				&& loggedUser == null) {
			redirectToLoginPage();
		}
	}

	@Override
	public void beforePhase(PhaseEvent event) {
	}

	@Override
	public PhaseId getPhaseId() {
		return PhaseId.RESTORE_VIEW;
	}

	private final void redirectToLoginPage() {
		FacesContext
				.getCurrentInstance()
				.getApplication()
				.getNavigationHandler()
				.handleNavigation(FacesContext.getCurrentInstance(), null,
						BeanUtils.LOGIN_PAGE);
	}

	@SuppressWarnings("unused")
	private final void redirectToHomePage() {
		FacesContext
				.getCurrentInstance()
				.getApplication()
				.getNavigationHandler()
				.handleNavigation(FacesContext.getCurrentInstance(), null,
						BeanUtils.HOME_PAGE);
	}

	private final String getCurrentPage() {
		return FacesContext.getCurrentInstance().getViewRoot().getViewId();
	}
}