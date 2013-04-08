package com.controlart.bean.interfac;

import java.sql.SQLException;

public interface ControlArtBeanInterface {

	public void clearAction();

	public void consultAction() throws SQLException;

	public void definirNovo();

	public void definirEditar();

	public void saveAction();

	public void insertAction();

	public void updateAction();

	public void inactivateAction();
}
