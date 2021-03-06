package com.controlart.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import com.controlart.bean.PessoaBean;

@FacesConverter("tipoPessoaConverter")
public class TipoPessoaConverter implements Converter {

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

		PessoaBean pessoaBean = (PessoaBean) context.getELContext()
				.getELResolver()
				.getValue(context.getELContext(), null, "pessoaBean");

		if (valor.matches("\\d+")) {
			return pessoaBean.getTipoPessoa((int) value);
		} else {
			return null;
		}
	}
}
