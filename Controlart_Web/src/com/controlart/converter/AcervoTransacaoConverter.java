package com.controlart.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import com.controlart.bean.TransacaoBean;

@FacesConverter("acervoTransacaoConverter")
public class AcervoTransacaoConverter implements Converter {

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

		TransacaoBean transacaoBean = (TransacaoBean) context.getELContext()
				.getELResolver()
				.getValue(context.getELContext(), null, "transacaoBean");

		if (valor.matches("\\d+")) {
			return transacaoBean.getAcervo((int) value);
		} else {
			return null;
		}
	}
}
