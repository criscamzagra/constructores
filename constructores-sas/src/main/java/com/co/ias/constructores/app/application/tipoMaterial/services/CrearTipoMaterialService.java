package com.co.ias.constructores.app.application.tipoMaterial.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.co.ias.constructores.app.application.tipoMaterial.model.TipoMaterialDBO;
import com.co.ias.constructores.app.application.tipoMaterial.ports.in.CrearTipoMaterialUseCase;
import com.co.ias.constructores.app.application.tipoMaterial.ports.out.TipoMaterialRepository;

import reactor.core.publisher.Mono;

@Service
public class CrearTipoMaterialService implements CrearTipoMaterialUseCase {

	@Autowired
	private TipoMaterialRepository tipoMaterialRepository;

	@Override
	public Mono<TipoMaterialDBO> execute(TipoMaterialDBO tipoMaterial) {
		
		return tipoMaterialRepository.save(tipoMaterial);
	}

}
