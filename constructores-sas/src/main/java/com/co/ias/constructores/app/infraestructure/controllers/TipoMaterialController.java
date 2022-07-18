package com.co.ias.constructores.app.infraestructure.controllers;

import java.net.URI;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.support.WebExchangeBindException;

import com.co.ias.constructores.app.application.tipoMaterial.model.TipoMaterialDBO;
import com.co.ias.constructores.app.application.tipoMaterial.ports.in.ActualizarTipoMaterialUseCase;
import com.co.ias.constructores.app.application.tipoMaterial.ports.in.ConsultarTipoMaterialUseCase;
import com.co.ias.constructores.app.application.tipoMaterial.ports.in.CrearTipoMaterialUseCase;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/tipo/material")
public class TipoMaterialController {
	@Autowired
	private CrearTipoMaterialUseCase crearTipoMaterialUseCase;

	@Autowired
	private ActualizarTipoMaterialUseCase actualizarTipoMaterialUseCase;
	
	@Autowired
	private ConsultarTipoMaterialUseCase consultarTipoMaterialUseCase;

	@PostMapping
	public Mono<ResponseEntity<Map<String, Object>>> crearTipoMaterial(
			@RequestBody Mono<TipoMaterialDBO> monoTipoMaterial) {

		Map<String, Object> respuesta = new HashMap<String, Object>();

		return monoTipoMaterial.flatMap(tipo -> {

			return crearTipoMaterialUseCase.execute(tipo).map(m -> {
				respuesta.put("tipoMaterial", m);
				respuesta.put("mensaje", "Tipo de Material creado con éxito");
				respuesta.put("timestamp", new Date());
				return ResponseEntity.created(URI.create("/api/tipo/material".concat(m.getTipoMaterialId())))
						.contentType(MediaType.APPLICATION_JSON).body(respuesta);
			});

		}).onErrorResume(t -> {
			return Mono.just(t).cast(WebExchangeBindException.class).flatMap(e -> Mono.just(e.getFieldErrors()))
					.flatMapMany(Flux::fromIterable)
					.map(fieldError -> "El campo " + fieldError.getField() + " " + fieldError.getDefaultMessage())
					.collectList().flatMap(list -> {
						respuesta.put("errors", list);
						respuesta.put("timestamp", new Date());
						respuesta.put("status", HttpStatus.BAD_REQUEST.value());
						return Mono.just(ResponseEntity.badRequest().body(respuesta));
					});

		});

	}

	@PutMapping("/{id}")
	public Mono<ResponseEntity<TipoMaterialDBO>> actualizarTipoMaterial(@RequestBody TipoMaterialDBO tipoMaterialDBO,
			@PathVariable String id) {
		return actualizarTipoMaterialUseCase.execute(id).flatMap(t -> {
			t.setNombreMaterial(tipoMaterialDBO.getNombreMaterial());
			t.setCodigoMaterial(tipoMaterialDBO.getCodigoMaterial());
			t.setCantidad(tipoMaterialDBO.getCantidad());
			return crearTipoMaterialUseCase.execute(t);
		}).map(p -> ResponseEntity.created(URI.create("/api/tipo/material".concat(p.getTipoMaterialId())))
				.contentType(MediaType.APPLICATION_JSON).body(p))
				.defaultIfEmpty(ResponseEntity.notFound().build());
	}
	
	
	@GetMapping
	public Mono<ResponseEntity<Flux<TipoMaterialDBO>>> lista(){
		return Mono.just(
				ResponseEntity.ok()
				.contentType(MediaType.APPLICATION_JSON)
				.body(consultarTipoMaterialUseCase.findAll())
				);
	}
	
	
	

}
