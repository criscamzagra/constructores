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
public class CambiarEstadoFinalizadoService {
	public static final Logger log = LoggerFactory.getLogger(CambiarEstadoFinalizadoService.class);
	@Autowired
	private OrdenConstruccionRepository ordenConstruccionRepository;

	public String cambiaEstadoFinalizado() {

		ordenConstruccionRepository.findAll(Sort.by(Sort.Direction.DESC, "estado")).elementAt(0).flatMap(solicitud -> {
			return ordenConstruccionRepository.ultimoProceso().elementAt(0)
					.map(s -> new ElementoCambiarEstado(ValidacionEstado.SI, solicitud))
					.onErrorReturn(new ElementoCambiarEstado(ValidacionEstado.NO, solicitud));

		}).flatMap(elemento -> {

			if (ValidacionEstado.SI.equals(elemento.getValidacionEstado())) {
				var orden = elemento.getOrdenConstruccionDBO();
				orden.setId(orden.getId());
				orden.setEstado("finalizado");

				ordenConstruccionRepository.save(orden).subscribe();

			}
			return Mono.just(elemento);

		}).subscribe(s -> log.info(s.toString()));

		return "Realizado";

	}

}
