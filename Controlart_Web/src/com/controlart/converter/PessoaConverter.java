package com.controlart.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import com.controlart.bean.UsuariosBean;

@FacesConverter("pessoaConverter")
public class PessoaConverter implements Converter {

	@Override
	public Object getAsObject(FacesContext context, UIComponent component,
			String value) {
		if ((value == null) || (!value.matches("\\d+"))) {
			return null;
		} else {
			return Integer.parseInt(value);
		}
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component,
			Object value) {
		String valor = value.toString();

		UsuariosBean usuariosBean = (UsuariosBean) context.getELContext()
				.getELResolver()
				.getValue(context.getELContext(), null, "usuariosBean");

		if (valor.matches("\\d+")) {
			return usuariosBean.getPessoa((int) value);
		} else {
			return null;
		}
	}
}
