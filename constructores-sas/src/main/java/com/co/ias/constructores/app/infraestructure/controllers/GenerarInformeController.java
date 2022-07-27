package com.co.ias.constructores.app.infraestructure.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.co.ias.constructores.app.application.informe.services.InformeService;
import com.co.ias.constructores.app.shared.errors.AplicationError;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
@RequestMapping("/api/informe")
public class GenerarInformeController {
	@Autowired
	private InformeService informeService;

	@PostMapping
	public ResponseEntity<?> informe(@RequestBody String ruta) {
		try {
			  ObjectMapper mapper = new ObjectMapper();
			  JsonNode node = mapper.readTree(ruta);
			  String  rutaArchivo = node.get("ruta").asText();
			return ResponseEntity.ok(informeService.generarInforme(rutaArchivo));
		} catch (Exception exception) {
			AplicationError aplicationError = new AplicationError("SystemError", exception.getMessage());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(aplicationError);
		}
	}

}
