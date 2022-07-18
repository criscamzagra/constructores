package com.co.ias.constructores.app.application.tipoMaterial.ports.in;

import com.co.ias.constructores.app.application.tipoMaterial.model.TipoMaterialDBO;

import reactor.core.publisher.Flux;

public interface ConsultarTipoMaterialUseCase {
	
	public Flux<TipoMaterialDBO> findAll();

}
