package com.co.ias.constructores.app.application.tipoMaterial.domain;

import org.apache.commons.lang3.Validate;

public class NombreMaterial {
	

	   private final String value;

	public NombreMaterial(String value) {
		Validate.notNull(value, "Nombre no puede ser nulo");
		this.value = value;
	}

	public String getValue() {
		return value;
	}
	
	

}
