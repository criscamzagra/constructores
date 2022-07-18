package com.co.ias.constructores.app.application.tipoMaterial.domain;

import org.apache.commons.lang3.Validate;

public class CantidadMaterial {
	
    private final Integer value;

    public CantidadMaterial(Integer value) {
        Validate.notNull(value, "Cantidad de Material no puede ser nulo");
        this.value = value;
    }

    public Integer getValue() {
        return value;
    }


}
