package com.controlart.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import com.controlart.bundle.ControlArtBundle;

@FacesConverter("statusConverter")
public class StatusConverter implements Converter {

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

		if (valor.matches("\\d+")) {
			switch (Integer.parseInt(valor)) {
			case 0: {
				return getObjectFromBundle("labelInativo");
			}
			case 1: {
				return getObjectFromBundle("labelAtivo");
			}
			default: {
				return null;
			}
			}
		} else {
			return null;
		}
	}

	private final String getObjectFromBundle(String key) {
		ControlArtBundle text = new ControlArtBundle();

		return text.getString(key);
	}
}
