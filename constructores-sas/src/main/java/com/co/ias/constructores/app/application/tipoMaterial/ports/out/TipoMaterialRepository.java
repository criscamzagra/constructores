package com.co.ias.constructores.app.application.tipoMaterial.ports.out;

import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import com.co.ias.constructores.app.application.tipoMaterial.model.TipoMaterialDBO;

import reactor.core.publisher.Mono;

public interface TipoMaterialRepository extends ReactiveMongoRepository<TipoMaterialDBO, String>{
	
	@Query(value =" { codigoMaterial: ?0 } ")
	Mono<TipoMaterialDBO> consultaMaterial(String codigoMaterial);

}
