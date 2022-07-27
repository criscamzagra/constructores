package com.co.ias.constructores.app.application.solicitudConstruccion.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.co.ias.constructores.app.application.solicitudConstruccion.model.ElementoCambiarEstado;
import com.co.ias.constructores.app.application.solicitudConstruccion.model.ValidacionEstado;
import com.co.ias.constructores.app.application.solicitudConstruccion.ports.out.SolicitudConstruccionRepository;

import reactor.core.publisher.Mono;

@Service
public class CambiaEstadoProgresoService {
	public static final Logger log = LoggerFactory.getLogger(CambiaEstadoProgresoService.class);
	@Autowired
	private SolicitudConstruccionRepository solicitudConstruccionRepository;

	public String cambiaEstadoProgreso() {
	
		 solicitudConstruccionRepository.findAll(Sort.by(Sort.Direction.ASC, "estado")).elementAt(0)
				.flatMap(solicitud -> {
					return solicitudConstruccionRepository.ultimoProceso().elementAt(0)
							.map(s -> new ElementoCambiarEstado(ValidacionEstado.SI, solicitud))
							.onErrorReturn(new ElementoCambiarEstado(ValidacionEstado.NO, solicitud));

				}).flatMap(elemento -> {

					if (ValidacionEstado.NO.equals(elemento.getValidacionEstado())) {
						var solicitud = elemento.getSolicitudConstruccionDBO();
						solicitud.setEstado("proceso");

						solicitudConstruccionRepository.save(solicitud).subscribe();

					}
					return Mono.just(elemento);

				}).subscribe();
		 
		 return "Realizado";

	

	}

}
