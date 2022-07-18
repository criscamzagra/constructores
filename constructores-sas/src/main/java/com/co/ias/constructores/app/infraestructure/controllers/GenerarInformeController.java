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

@RestController
@RequestMapping("/api/informe")
public class GenerarInformeController {
	@Autowired
	private InformeService informeService;

	@PostMapping
	public ResponseEntity<?> informe(@RequestBody String ruta) {

		try {
			return ResponseEntity.ok(informeService.generarInforme(ruta));
		} catch (Exception exception) {
			AplicationError aplicationError = new AplicationError("SystemError", exception.getMessage());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(aplicationError);
		}
	}

}
