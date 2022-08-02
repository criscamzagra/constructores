package com.co.ias.constructores.app.infraestructure.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.co.ias.constructores.app.application.ordenConstruccion.services.ConsultaFechaFinProyectoService;

@RestController
@RequestMapping("/api/orden/construccion")
public class OrdenConstruccionController {

	@Autowired
	private ConsultaFechaFinProyectoService consultaFechaFinProyectoService;

	@GetMapping
	public ResponseEntity<?> fechaFin() {
		return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON)
				.body(consultaFechaFinProyectoService.consultaFechaFin());

	}

}
