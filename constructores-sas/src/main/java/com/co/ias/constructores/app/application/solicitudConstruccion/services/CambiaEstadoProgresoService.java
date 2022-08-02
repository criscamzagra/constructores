package com.co.ias.constructores.app.application.solicitudConstruccion.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.co.ias.constructores.app.application.ordenConstruccion.ports.out.OrdenConstruccionRepository;
import com.co.ias.constructores.app.application.solicitudConstruccion.model.ElementoCambiarEstado;
import com.co.ias.constructores.app.application.solicitudConstruccion.model.ValidacionEstado;

import reactor.core.publisher.Mono;

@Service
public class CambiaEstadoProgresoService {
	public static final Logger log = LoggerFactory.getLogger(CambiaEstadoProgresoService.class);

	@Autowired
	private OrdenConstruccionRepository ordenConstruccionRepository;

	public String cambiaEstadoProgreso() {

		ordenConstruccionRepository.findAll(Sort.by(Sort.Direction.ASC, "estado")).elementAt(0).flatMap(solicitud -> {
			return ordenConstruccionRepository.ultimoProceso().elementAt(0)
					.map(s -> new ElementoCambiarEstado(ValidacionEstado.SI, solicitud))
					.onErrorReturn(new ElementoCambiarEstado(ValidacionEstado.NO, solicitud));

		}).flatMap(elemento -> {

			if (ValidacionEstado.NO.equals(elemento.getValidacionEstado())) {
				var orden = elemento.getOrdenConstruccionDBO();
				orden.setEstado("proceso");

				ordenConstruccionRepository.save(orden).subscribe();

			}
			return Mono.just(elemento);

		}).subscribe();

		return "Realizado";

	}

}
