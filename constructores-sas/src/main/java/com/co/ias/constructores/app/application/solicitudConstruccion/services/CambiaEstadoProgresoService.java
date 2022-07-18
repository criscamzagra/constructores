package com.co.ias.constructores.app.application.solicitudConstruccion.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.co.ias.constructores.app.application.solicitudConstruccion.ports.out.SolicitudConstruccionRepository;
@Service
public class CambiaEstadoProgresoService {
	public static final Logger log = LoggerFactory.getLogger(CambiaEstadoProgresoService.class);
	@Autowired
	private SolicitudConstruccionRepository solicitudConstruccionRepository;


	
	
	public String cambiaEstadoProgreso() {
		
		solicitudConstruccionRepository.findAll()
		.filter(solicitud-> solicitud.getEstado().equals("Proceso"))
		.onErrorMap(ex-> new RuntimeException("Ya existe una solicitud en proceso"));
		
				

		
		return "Realizado";

	}
}
