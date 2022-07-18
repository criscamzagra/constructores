package com.co.ias.constructores.app.application.tipoMaterial.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.co.ias.constructores.app.application.tipoMaterial.model.TipoMaterialDBO;
import com.co.ias.constructores.app.application.tipoMaterial.ports.in.ConsultarTipoMaterialUseCase;
import com.co.ias.constructores.app.application.tipoMaterial.ports.out.TipoMaterialRepository;

import reactor.core.publisher.Flux;
@Service
public class ConsultarTipoMaterialService  implements ConsultarTipoMaterialUseCase{
	
	@Autowired
	private TipoMaterialRepository tipoMaterialRepository;

	@Override
	public Flux<TipoMaterialDBO> findAll() {
		 return tipoMaterialRepository.findAll();
	}

}
