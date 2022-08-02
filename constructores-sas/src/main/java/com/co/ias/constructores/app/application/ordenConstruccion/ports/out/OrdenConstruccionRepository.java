package com.co.ias.constructores.app.application.ordenConstruccion.ports.out;

import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import com.co.ias.constructores.app.application.ordenConstruccion.model.OrdenConstruccionDBO;

import reactor.core.publisher.Flux;

public interface OrdenConstruccionRepository extends ReactiveMongoRepository<OrdenConstruccionDBO, String>{
	
	@Query(value =" { estado : 'proceso' } ")
	Flux<OrdenConstruccionDBO> ultimoProceso();

}
