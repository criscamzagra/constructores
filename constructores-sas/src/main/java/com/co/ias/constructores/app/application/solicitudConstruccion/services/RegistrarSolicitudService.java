package com.co.ias.constructores.app.application.solicitudConstruccion.services;

import java.time.LocalDateTime;

import org.bson.types.ObjectId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.co.ias.constructores.app.application.solicitudConstruccion.model.SolicitudConstruccionDBO;
import com.co.ias.constructores.app.application.solicitudConstruccion.ports.in.RegistrarSolicitudConstruccionUseCase;
import com.co.ias.constructores.app.application.solicitudConstruccion.ports.out.SolicitudConstruccionRepository;
import com.co.ias.constructores.app.application.tipoMaterial.model.TipoMaterialDTO;
import com.co.ias.constructores.app.application.tipoMaterial.ports.out.TipoMaterialRepository;

import reactor.core.Disposable;
import reactor.core.publisher.Mono;

@Service
public class RegistrarSolicitudService implements RegistrarSolicitudConstruccionUseCase {

	public static final Logger log = LoggerFactory.getLogger(RegistrarSolicitudService.class);
	@Autowired
	private SolicitudConstruccionRepository solicitudConstruccionRepository;

	@Autowired
	private TipoMaterialRepository tipoMaterialRepository;

	boolean isCordenada;
	LocalDateTime fechaUltima;

	String idUltimo;

	@Override
	public Mono<Disposable> execute(SolicitudConstruccionDBO solicitudConstruccionDBO) {
		var id = new ObjectId();

		return Mono.just(solicitudConstruccionRepository.findAll(Sort.by(Sort.Direction.DESC, "fechaFin")).elementAt(0)

				.flatMap(ultimaSolicitud -> {
					return Mono.just(solicitudConstruccionDBO).map(s -> {
						switch (s.getTipoContruccion()) {

						case "Casa":
							var fecha = ultimaSolicitud.getFechaFin();
							var fechaInicio = fecha.plusDays(1);

							s.setFechaFin(fechaInicio.plusDays(3));
							s.setFechaInicio(fechaInicio);
							s.setId(id.toString());

							break;
						case "Lago":
							fecha = ultimaSolicitud.getFechaFin();
							fechaInicio = fecha.plusDays(1);

							s.setFechaFin(fechaInicio.plusDays(2));
							s.setFechaInicio(fechaInicio);
							s.setId(id.toString());

							break;
						case "Cancha de futbol":
							fecha = ultimaSolicitud.getFechaFin();
							fechaInicio = fecha.plusDays(1);

							s.setFechaFin(fechaInicio.plusDays(1));
							s.setFechaInicio(fechaInicio);
							s.setId(id.toString());

							break;
						case "Edificio":
							fecha = ultimaSolicitud.getFechaFin();
							fechaInicio = fecha.plusDays(1);

							s.setFechaFin(fechaInicio.plusDays(6));
							s.setFechaInicio(fechaInicio);
							s.setId(id.toString());

							break;
						case "Gimnasio":
							fecha = ultimaSolicitud.getFechaFin();
							fechaInicio = fecha.plusDays(1);

							s.setFechaFin(fechaInicio.plusDays(2));
							s.setFechaInicio(fechaInicio);
							s.setId(id.toString());

							break;

						}
						return s;

					});

				}).flatMap(solicitud -> solicitudConstruccionRepository.save(solicitud)).map(s -> {
					switch (s.getTipoContruccion()) {

					case "Casa":
						quitaTipoMaterialCemento("Ce", "Cemento", 100);
						quitaTipoMaterialGrava("Gr", "Grava", 50);
						quitaTipoMaterialArena("Ar", "Arena", 90);
						quitaTipoMaterialMadera("Ma", "Madera", 20);
						quitaTipoMaterialAdobe("Ad", "Adobe", 100);
						break;
					case "Lago":
						quitaTipoMaterialCemento("Ce", "Cemento", 50);
						quitaTipoMaterialGrava("Gr", "Grava", 60);
						quitaTipoMaterialArena("Ar", "Arena", 80);
						quitaTipoMaterialMadera("Ma", "Madera", 10);
						quitaTipoMaterialAdobe("Ad", "Adobe", 20);
						break;
					case "Cancha de futbol":
						quitaTipoMaterialCemento("Ce", "Cemento", 20);
						quitaTipoMaterialGrava("Gr", "Grava", 20);
						quitaTipoMaterialArena("Ar", "Arena", 20);
						quitaTipoMaterialMadera("Ma", "Madera", 20);
						quitaTipoMaterialAdobe("Ad", "Adobe", 20);
						break;
					case "Edificio":
						quitaTipoMaterialCemento("Ce", "Cemento", 200);
						quitaTipoMaterialGrava("Gr", "Grava", 100);
						quitaTipoMaterialArena("Ar", "Arena", 180);
						quitaTipoMaterialMadera("Ma", "Madera", 40);
						quitaTipoMaterialAdobe("Ad", "Adobe", 200);
						break;
					case "Gimnasio":
						quitaTipoMaterialCemento("Ce", "Cemento", 50);
						quitaTipoMaterialGrava("Gr", "Grava", 25);
						quitaTipoMaterialArena("Ar", "Arena", 45);
						quitaTipoMaterialMadera("Ma", "Madera", 10);
						quitaTipoMaterialAdobe("Ad", "Adobe", 50);
						break;

					}
					return s;
				}).subscribe());

	}

	private void quitaTipoMaterialAdobe(String codigoTipoMaterial, String nombreTipoMaterial, int cantidadMaterial) {
		tipoMaterialRepository.consultaMaterial(codigoTipoMaterial)
				.map(m -> new TipoMaterialDTO(m.getTipoMaterialId(), m.getNombreMaterial(), m.getCodigoMaterial(),
						m.getCantidad()))
				.filter(tm -> tm.getNombreMaterial().equals(nombreTipoMaterial)).doOnNext(material -> {
					if (material.getCantidad() < cantidadMaterial) {
						throw new RuntimeException(
								"No hay suficiente".concat(nombreTipoMaterial).concat("para la contruccion"));
					} else {
						tipoMaterialRepository.findById(material.getTipoMaterialId()).flatMap(tmr -> {
							tmr.setCantidad(tmr.getCantidad() - cantidadMaterial);
							return tipoMaterialRepository.save(tmr);
						}).subscribe();
					}
				}).subscribe();
	}

	private void quitaTipoMaterialMadera(String codigoTipoMaterial, String nombreTipoMaterial, int cantidadMaterial) {
		tipoMaterialRepository.consultaMaterial(codigoTipoMaterial)
				.map(m -> new TipoMaterialDTO(m.getTipoMaterialId(), m.getNombreMaterial(), m.getCodigoMaterial(),
						m.getCantidad()))
				.filter(tm -> tm.getNombreMaterial().equals(nombreTipoMaterial)).doOnNext(material -> {
					if (material.getCantidad() < cantidadMaterial) {
						throw new RuntimeException(
								"No hay suficiente".concat(nombreTipoMaterial).concat("para la contruccion"));
					} else {
						tipoMaterialRepository.findById(material.getTipoMaterialId()).flatMap(tmr -> {
							tmr.setCantidad(tmr.getCantidad() - cantidadMaterial);
							return tipoMaterialRepository.save(tmr);
						}).subscribe();
					}
				}).subscribe();
	}

	private void quitaTipoMaterialArena(String codigoTipoMaterial, String nombreTipoMaterial, int cantidadMaterial) {
		tipoMaterialRepository.consultaMaterial(codigoTipoMaterial)
				.map(m -> new TipoMaterialDTO(m.getTipoMaterialId(), m.getNombreMaterial(), m.getCodigoMaterial(),
						m.getCantidad()))
				.filter(tm -> tm.getNombreMaterial().equals(nombreTipoMaterial)).doOnNext(material -> {
					if (material.getCantidad() < cantidadMaterial) {
						throw new RuntimeException(
								"No hay suficiente".concat(nombreTipoMaterial).concat("la contruccion"));
					} else {
						tipoMaterialRepository.findById(material.getTipoMaterialId()).flatMap(tmr -> {
							tmr.setCantidad(tmr.getCantidad() - cantidadMaterial);
							return tipoMaterialRepository.save(tmr);
						}).subscribe();
					}
				}).subscribe();
	}

	private void quitaTipoMaterialGrava(String codigoTipoMaterial, String nombreTipoMaterial, int cantidadMaterial) {
		tipoMaterialRepository.consultaMaterial(codigoTipoMaterial)
				.map(m -> new TipoMaterialDTO(m.getTipoMaterialId(), m.getNombreMaterial(), m.getCodigoMaterial(),
						m.getCantidad()))
				.filter(tm -> tm.getNombreMaterial().equals(nombreTipoMaterial)).doOnNext(material -> {
					if (material.getCantidad() < cantidadMaterial) {
						throw new RuntimeException(
								"No hay suficiente".concat(nombreTipoMaterial).concat("para la contruccion"));
					} else {
						tipoMaterialRepository.findById(material.getTipoMaterialId()).flatMap(tmr -> {
							tmr.setCantidad(tmr.getCantidad() - cantidadMaterial);
							return tipoMaterialRepository.save(tmr);
						}).subscribe();
					}
				}).subscribe();
	}

	private void quitaTipoMaterialCemento(String codigoTipoMaterial, String nombreTipoMaterial, int cantidadMaterial) {
		tipoMaterialRepository.consultaMaterial(codigoTipoMaterial)
				.map(m -> new TipoMaterialDTO(m.getTipoMaterialId(), m.getNombreMaterial(), m.getCodigoMaterial(),
						m.getCantidad()))
				.filter(tm -> tm.getNombreMaterial().equals(nombreTipoMaterial)).doOnNext(material -> {
					if (material.getCantidad() < cantidadMaterial) {
						throw new RuntimeException(
								"No hay suficiente".concat(nombreTipoMaterial).concat("para la contruccion"));
					} else {
						tipoMaterialRepository.findById(material.getTipoMaterialId()).flatMap(tmr -> {
							tmr.setCantidad(tmr.getCantidad() - cantidadMaterial);
							return tipoMaterialRepository.save(tmr);
						}).subscribe();
					}
				}).subscribe();
	}



}
