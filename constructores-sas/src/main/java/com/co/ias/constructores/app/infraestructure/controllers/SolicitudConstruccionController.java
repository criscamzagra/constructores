package com.co.ias.constructores.app.infraestructure.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.co.ias.constructores.app.application.solicitudConstruccion.model.SolicitudConstruccionDTO;
import com.co.ias.constructores.app.application.solicitudConstruccion.ports.in.RegistrarSolicitudConstruccionUseCase;
import com.co.ias.constructores.app.application.solicitudConstruccion.services.CambiaEstadoProgresoService;

import reactor.core.Disposable;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/solicitud/contruccion")
public class SolicitudConstruccionController {
	@Autowired
	private RegistrarSolicitudConstruccionUseCase registrarSolicitudConstruccionUseCase;
	 
	@Autowired
	private CambiaEstadoProgresoService cambiaEstadoProgresoService;
	
	@PostMapping
	public Mono<ResponseEntity<Mono<Disposable>>> lista(
			@RequestBody SolicitudConstruccionDTO solicitudConstruccionDTO) {
		return Mono.just(solicitudConstruccionDTO).map(SolicitudConstruccionDTO::fromDomain)
				.map(solicitud -> ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON)
						.body( registrarSolicitudConstruccionUseCase.execute(solicitud) ));

	}
	
	@GetMapping
	public ResponseEntity<?> cambioEstadoProceso() {
		  return ResponseEntity.ok(cambiaEstadoProgresoService.cambiaEstadoProgreso());
	}




}
