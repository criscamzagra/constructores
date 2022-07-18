package com.co.ias.constructores.app.application.tipoMaterial.domain;

import org.apache.commons.lang3.Validate;

public class CodigoMaterial {
	   private final String value;

	public CodigoMaterial(String value) {
		Validate.notNull(value, "Codigo no puede ser nulo");
		this.value = value;
	}

	public String getValue() {
		return value;
	}
	

}
