package com.controlart.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

import com.controlart.bean.PecaBean;

public class AcervoPecaConverter implements Converter {
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

		PecaBean pecaBean = (PecaBean) context.getELContext().getELResolver()
				.getValue(context.getELContext(), null, "pecaBean");

		if (valor.matches("\\d+")) {
			return pecaBean.getAcervo((int) value);
		} else {
			return null;
		}
	}
}
