package com.controlart.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import com.controlart.bean.AcervoBean;

@FacesConverter("pessoaAcervoConverter")
public class PessoaAcervoConverter implements Converter {

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

		AcervoBean acervoBean = (AcervoBean) context.getELContext()
				.getELResolver()
				.getValue(context.getELContext(), null, "acervoBean");

		if (valor.matches("\\d+")) {
			return acervoBean.getPessoa((int) value);
		} else {
			return null;
		}
	}
}
