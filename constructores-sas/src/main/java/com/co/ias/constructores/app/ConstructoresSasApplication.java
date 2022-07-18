package com.co.ias.constructores.app;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;

import com.co.ias.constructores.app.application.solicitudConstruccion.model.SolicitudConstruccionDBO;
import com.co.ias.constructores.app.application.solicitudConstruccion.ports.out.SolicitudConstruccionRepository;
import com.co.ias.constructores.app.application.tipoMaterial.model.TipoMaterialDBO;
import com.co.ias.constructores.app.application.tipoMaterial.services.CrearTipoMaterialService;

import reactor.core.publisher.Flux;

@SpringBootApplication
public class ConstructoresSasApplication implements CommandLineRunner {
	@Autowired
	private CrearTipoMaterialService crearTipoMaterialService;
	
	@Autowired
	private SolicitudConstruccionRepository solicitudConstruccionRepository;

	@Autowired
	private ReactiveMongoTemplate mongoTemplate;

	public static void main(String[] args) {
		SpringApplication.run(ConstructoresSasApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		AgregarTiposMaterial();
		mongoTemplate.dropCollection("solicitudConstruccion").subscribe();
		SolicitudConstruccionDBO solicitudConstruccionDBO = new SolicitudConstruccionDBO();
		solicitudConstruccionDBO.setCoordenadas("15,74");
		solicitudConstruccionDBO.setTipoContruccion("Casa");
		solicitudConstruccionDBO.setFechaInicio(LocalDateTime.now());
		solicitudConstruccionDBO.setFechaFin(LocalDateTime.now().plusDays(3));
		solicitudConstruccionDBO.setEstado("Pendiente");
		
		Flux.just(solicitudConstruccionDBO
				).flatMap(s->{
					return solicitudConstruccionRepository.save(s);
				}).subscribe();
		

	}

	private void AgregarTiposMaterial() {
		mongoTemplate.dropCollection("tipoMaterial").subscribe();

		Flux.just(new TipoMaterialDBO("Cemento", "Ce", 5000),
				new TipoMaterialDBO("Grava", "Gr", 5000),
				new TipoMaterialDBO("Arena", "Ar", 5000),
				new TipoMaterialDBO("Madera", "Ma", 5000),
				new TipoMaterialDBO("Adobe", "Ad", 5000)).flatMap(tipo -> {

					return crearTipoMaterialService.execute(tipo);
				}).subscribe();
	}

}
