package com.co.ias.constructores.app.application.solicitudConstruccion.ports.out;

import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import com.co.ias.constructores.app.application.solicitudConstruccion.model.SolicitudConstruccionDBO;

import reactor.core.publisher.Flux;

public interface SolicitudConstruccionRepository extends ReactiveMongoRepository<SolicitudConstruccionDBO, String>{
	
	@Query(value =" { coordenadas: ?0 } ")
	Flux<SolicitudConstruccionDBO> validaCoordenadas(String coordenadas);
	
	
	
	@Query(value =" { estado : 'proceso' } ")
	Flux<SolicitudConstruccionDBO> ultimoProceso();
	
	

	
	
	
	

}
