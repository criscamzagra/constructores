package com.co.ias.constructores.app.application.tipoMaterial.domain;

import org.apache.commons.lang3.Validate;

public class TipoMateriaId {
	
	   private final String value;

	public TipoMateriaId(String value) {
		Validate.notNull(value, "Tipo Material  Id no puede ser nulo");
		this.value = value;
	}

	public String getValue() {
		return value;
	}
	
	
	   
	   

}
