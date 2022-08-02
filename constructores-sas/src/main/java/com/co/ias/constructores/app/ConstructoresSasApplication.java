package com.co.ias.constructores.app;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;

import com.co.ias.constructores.app.application.ordenConstruccion.model.OrdenConstruccionDBO;
import com.co.ias.constructores.app.application.ordenConstruccion.ports.out.OrdenConstruccionRepository;
import com.co.ias.constructores.app.application.solicitudConstruccion.model.SolicitudConstruccionDBO;
import com.co.ias.constructores.app.application.solicitudConstruccion.ports.out.SolicitudConstruccionRepository;
import com.co.ias.constructores.app.application.solicitudConstruccion.services.RegistrarSolicitudService;
import com.co.ias.constructores.app.application.tipoMaterial.model.TipoMaterialDBO;
import com.co.ias.constructores.app.application.tipoMaterial.services.CrearTipoMaterialService;

import reactor.core.publisher.Flux;

@SpringBootApplication
public class ConstructoresSasApplication implements CommandLineRunner {
	@Autowired
	private CrearTipoMaterialService crearTipoMaterialService;
	
	@Autowired
	private RegistrarSolicitudService registrarSolicitudService;
	
	@Autowired
	private SolicitudConstruccionRepository solicitudConstruccionRepository;
	
	@Autowired
	private OrdenConstruccionRepository ordenConstruccionRepository;
	

	@Autowired
	private ReactiveMongoTemplate mongoTemplate;

	public static void main(String[] args) {
		SpringApplication.run(ConstructoresSasApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		var fechaCreacion = LocalDateTime.now();
		var fechaIniciov = fechaCreacion.plusDays(1);
		var frchaFinv = fechaIniciov.plusDays(3);
	
		mongoTemplate.dropCollection("solicitudConstruccion").subscribe();
		mongoTemplate.dropCollection("ordenConstruccion").subscribe();
		mongoTemplate.dropCollection("tipoMaterial").subscribe();

		Flux.just(new TipoMaterialDBO("Cemento", "Ce", 5000),
				new TipoMaterialDBO("Grava", "Gr", 5000),
				new TipoMaterialDBO("Arena", "Ar", 5000),
				new TipoMaterialDBO("Madera", "Ma", 5000),
				new TipoMaterialDBO("Adobe", "Ad", 5000)).flatMap(tipo -> {
					

					return crearTipoMaterialService.execute(tipo);
				}).subscribe();
		
		
		
		
		Flux.just(new SolicitudConstruccionDBO("Casa","1452,4568",LocalDateTime.now()))
		.flatMap(solicitud -> {
			
			return solicitudConstruccionRepository.save(solicitud);
		
		}).flatMap(s->{
			OrdenConstruccionDBO ordenConstruccionDBO=new OrdenConstruccionDBO(s.getId(),"Casa",fechaIniciov,frchaFinv,"pendiente");
			return ordenConstruccionRepository.save(ordenConstruccionDBO);
			
		})
		
		.subscribe();
		
	
		
		
		
	
	
		

	}

	
}
