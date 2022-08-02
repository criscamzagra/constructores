package com.co.ias.constructores.app.application.solicitudConstruccion.services;

import java.time.LocalDateTime;

import org.bson.types.ObjectId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Sort;

import com.co.ias.constructores.app.application.ordenConstruccion.model.OrdenConstruccionDBO;
import com.co.ias.constructores.app.application.ordenConstruccion.ports.out.OrdenConstruccionRepository;
import com.co.ias.constructores.app.application.solicitudConstruccion.model.SolicitudConstruccionDBO;
import com.co.ias.constructores.app.application.solicitudConstruccion.ports.in.RegistrarSolicitudConstruccionUseCase;
import com.co.ias.constructores.app.application.solicitudConstruccion.ports.out.SolicitudConstruccionRepository;
import com.co.ias.constructores.app.application.tipoMaterial.ports.out.TipoMaterialRepository;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class RegistrarSolicitudService implements RegistrarSolicitudConstruccionUseCase {

	private static final String PENDIENTE = "pendiente";
	private static final String ADOBE = "Adobe";
	private static final String MADERA = "Madera";
	private static final String ARENA = "Arena";
	private static final String GRAVA = "Grava";
	private static final String CEMENTO = "Cemento";
	private static final int VALOR_CUARENTAICINCO = 45;
	private static final int VALOR_VEINTICINCO = 25;
	private static final int VALOR_CUARENTA = 40;
	private static final int VALOR_CIENTOOCHENTA = 180;
	private static final int VALOR_DOCIENTOS = 200;
	private static final int VALOR_DIEZ = 10;
	private static final int VALOR_OCHENTA = 80;
	private static final int VALOR_SESENTA = 60;
	private static final int VALOR_VEINTE = 20;
	private static final int VALOR_NOVENTA = 90;
	private static final int VALOR_CINCUENTA = 50;
	private static final int VALOR_CIEN = 100;
	public static final Logger log = LoggerFactory.getLogger(RegistrarSolicitudService.class);
	@Autowired
	private SolicitudConstruccionRepository solicitudConstruccionRepository;

	@Autowired
	private TipoMaterialRepository tipoMaterialRepository;

	@Autowired
	private OrdenConstruccionRepository ordenConstruccionRepository;

	LocalDateTime ultimaFecha;
	LocalDateTime fechaInicio;
	LocalDateTime fechaFin;

	enum ExisteMaterial {
		SI, NO
	}

	boolean isCordenada;
	LocalDateTime fechaUltima;

	String idUltimo;

	@Override
	public String execute(SolicitudConstruccionDBO solicitudConstruccionDBO) {
		var idSolicitud = new ObjectId();
		var idOrden = new ObjectId();
		var fechaCreacion = LocalDateTime.now();

		switch (solicitudConstruccionDBO.getTipoConstruccion()) {
		case "Casa":
			calculaFecha(3);
			RegistrarCasa(solicitudConstruccionDBO, idSolicitud, idOrden, fechaCreacion);
			break;
		case "Lago":
			calculaFecha(2);
			RegistrarLago(solicitudConstruccionDBO, idSolicitud, idOrden, fechaCreacion);
			break;
		case "Cancha de futbol":
			calculaFecha(1);
			RegistrarCanchaFutbo(solicitudConstruccionDBO, idSolicitud, idOrden, fechaCreacion);
			break;
		case "Edificio":
			calculaFecha(6);
			RegistrarEdificio(solicitudConstruccionDBO, idSolicitud, idOrden, fechaCreacion);
			break;
		case "Gimnasio":
			calculaFecha(2);
			RegistrarGimnasio(solicitudConstruccionDBO, idSolicitud, idOrden, fechaCreacion);
			break;

		}

		return "Solicitud Registrada";

	}

	private void calculaFecha(int numDiasFin) {
		ordenConstruccionRepository.findAll(Sort.by(Sort.Direction.DESC, "fechaFin")).elementAt(0).map(ultimaOrden -> {
			ultimaFecha = ultimaOrden.getFechaFin();
			fechaInicio = ultimaFecha.plusDays(1);
			fechaFin = fechaInicio.plusDays(numDiasFin);
			return Mono.just(ultimaOrden);

		}).subscribe();
	}

	private void RegistrarCasa(SolicitudConstruccionDBO solicitudConstruccionDBO, ObjectId idSolicitud,
			ObjectId idOrden, LocalDateTime fechaCreacion) {
		tipoMaterialRepository.findAll()
				.map(material -> ((material.getNombreMaterial().equals(CEMENTO) && material.getCantidad() > VALOR_CIEN)
						|| (material.getNombreMaterial().equals(GRAVA) && material.getCantidad() > VALOR_CINCUENTA)
						|| (material.getNombreMaterial().equals(ARENA) && material.getCantidad() > VALOR_NOVENTA)
						|| (material.getNombreMaterial().equals(MADERA) && material.getCantidad() > VALOR_VEINTE)
						|| (material.getNombreMaterial().equals(ADOBE) && material.getCantidad() > VALOR_CIEN)) ?

								ExisteMaterial.SI

								:

								ExisteMaterial.NO

				)
				.reduce((existe1, existe2) -> existe1.equals(ExisteMaterial.SI) && existe2.equals(ExisteMaterial.SI)
						? ExisteMaterial.SI
						: ExisteMaterial.NO)
				.flatMap(solicitud -> solicitudConstruccionRepository
						.validaCoordenadas(solicitudConstruccionDBO.getCoordenadas()).hasElements()
						.map(x -> !x && ExisteMaterial.SI.equals(solicitud)))
				.filter(solicitud -> solicitud).flatMapMany((x) ->

				Flux.concat(tipoMaterialRepository.consultaMaterial(CEMENTO).flatMap(material -> {
					material.setCantidad(material.getCantidad() - VALOR_CIEN);
					return tipoMaterialRepository.save(material);
				}), tipoMaterialRepository.consultaMaterial(GRAVA).flatMap(material -> {
					material.setCantidad(material.getCantidad() - VALOR_CINCUENTA);
					return tipoMaterialRepository.save(material);
				}), tipoMaterialRepository.consultaMaterial(ARENA).flatMap(material -> {
					material.setCantidad(material.getCantidad() - VALOR_NOVENTA);
					return tipoMaterialRepository.save(material);
				}), tipoMaterialRepository.consultaMaterial(MADERA).flatMap(material -> {
					material.setCantidad(material.getCantidad() - VALOR_VEINTE);
					return tipoMaterialRepository.save(material);
				}), tipoMaterialRepository.consultaMaterial(ADOBE).flatMap(material -> {
					material.setCantidad(material.getCantidad() - VALOR_CIEN);
					return tipoMaterialRepository.save(material);
				})

				)

				).flatMap(m -> {

					SolicitudConstruccionDBO solicitudConstruccionObj = new SolicitudConstruccionDBO();
					solicitudConstruccionObj.setId(idSolicitud.toString());
					solicitudConstruccionObj.setFechaCreacion(fechaCreacion);
					solicitudConstruccionObj.setTipoConstruccion(solicitudConstruccionDBO.getTipoConstruccion());
					solicitudConstruccionObj.setCoordenadas(solicitudConstruccionDBO.getCoordenadas());
					return solicitudConstruccionRepository.save(solicitudConstruccionObj);

				}).flatMap(s -> {

					OrdenConstruccionDBO OrdenConstruccionDBO = new OrdenConstruccionDBO();
					OrdenConstruccionDBO.setId(idOrden.toString());
					OrdenConstruccionDBO.setEstado(PENDIENTE);
					OrdenConstruccionDBO.setFechaInicio(fechaInicio);
					OrdenConstruccionDBO.setFechaFin(fechaFin);
					OrdenConstruccionDBO.setTipoConstruccion(s.getTipoConstruccion());
					OrdenConstruccionDBO.setIdSolicitud(idSolicitud.toString());

					return ordenConstruccionRepository.save(OrdenConstruccionDBO);

				}).subscribe();
	}

	private void RegistrarLago(SolicitudConstruccionDBO solicitudConstruccionDBO, ObjectId idSolicitud,
			ObjectId idOrden, LocalDateTime fechaCreacion) {
		tipoMaterialRepository.findAll().map(
				material -> ((material.getNombreMaterial().equals(CEMENTO) && material.getCantidad() > VALOR_CINCUENTA)
						|| (material.getNombreMaterial().equals(GRAVA) && material.getCantidad() > VALOR_SESENTA)
						|| (material.getNombreMaterial().equals(ARENA) && material.getCantidad() > VALOR_OCHENTA)
						|| (material.getNombreMaterial().equals(MADERA) && material.getCantidad() > VALOR_DIEZ)
						|| (material.getNombreMaterial().equals(ADOBE) && material.getCantidad() > VALOR_VEINTE)) ?

								ExisteMaterial.SI

								:

								ExisteMaterial.NO

		).reduce((existe1, existe2) -> existe1.equals(ExisteMaterial.SI) && existe2.equals(ExisteMaterial.SI)
				? ExisteMaterial.SI
				: ExisteMaterial.NO)
				.flatMap(solicitud -> solicitudConstruccionRepository
						.validaCoordenadas(solicitudConstruccionDBO.getCoordenadas()).hasElements()
						.map(x -> !x && ExisteMaterial.SI.equals(solicitud)))
				.filter(solicitud -> solicitud).flatMapMany((x) ->

				Flux.concat(tipoMaterialRepository.consultaMaterial(CEMENTO).flatMap(material -> {
					material.setCantidad(material.getCantidad() - VALOR_CINCUENTA);
					return tipoMaterialRepository.save(material);
				}), tipoMaterialRepository.consultaMaterial(GRAVA).flatMap(material -> {
					material.setCantidad(material.getCantidad() - VALOR_SESENTA);
					return tipoMaterialRepository.save(material);
				}), tipoMaterialRepository.consultaMaterial(ARENA).flatMap(material -> {
					material.setCantidad(material.getCantidad() - VALOR_OCHENTA);
					return tipoMaterialRepository.save(material);
				}), tipoMaterialRepository.consultaMaterial(MADERA).flatMap(material -> {
					material.setCantidad(material.getCantidad() - VALOR_DIEZ);
					return tipoMaterialRepository.save(material);
				}), tipoMaterialRepository.consultaMaterial(ADOBE).flatMap(material -> {
					material.setCantidad(material.getCantidad() - VALOR_VEINTE);
					return tipoMaterialRepository.save(material);
				})

				)

				).flatMap(m -> {

					SolicitudConstruccionDBO solicitudConstruccionObj = new SolicitudConstruccionDBO();
					solicitudConstruccionObj.setId(idSolicitud.toString());
					solicitudConstruccionObj.setFechaCreacion(fechaCreacion);
					solicitudConstruccionObj.setTipoConstruccion(solicitudConstruccionDBO.getTipoConstruccion());
					solicitudConstruccionObj.setCoordenadas(solicitudConstruccionDBO.getCoordenadas());
					return solicitudConstruccionRepository.save(solicitudConstruccionObj);

				}).flatMap(s -> {

					OrdenConstruccionDBO OrdenConstruccionDBO = new OrdenConstruccionDBO();
					OrdenConstruccionDBO.setId(idOrden.toString());
					OrdenConstruccionDBO.setEstado(PENDIENTE);
					OrdenConstruccionDBO.setFechaInicio(fechaInicio);
					OrdenConstruccionDBO.setFechaFin(fechaFin);
					OrdenConstruccionDBO.setTipoConstruccion(s.getTipoConstruccion());
					OrdenConstruccionDBO.setIdSolicitud(idSolicitud.toString());

					return ordenConstruccionRepository.save(OrdenConstruccionDBO);

				}).subscribe();
	}

	private void RegistrarCanchaFutbo(SolicitudConstruccionDBO solicitudConstruccionDBO, ObjectId idSolicitud,
			ObjectId idOrden, LocalDateTime fechaCreacion) {
		tipoMaterialRepository.findAll().map(
				material -> ((material.getNombreMaterial().equals(CEMENTO) && material.getCantidad() > VALOR_VEINTE)
						|| (material.getNombreMaterial().equals(GRAVA) && material.getCantidad() > VALOR_VEINTE)
						|| (material.getNombreMaterial().equals(ARENA) && material.getCantidad() > VALOR_VEINTE)
						|| (material.getNombreMaterial().equals(MADERA) && material.getCantidad() > VALOR_VEINTE)
						|| (material.getNombreMaterial().equals(ADOBE) && material.getCantidad() > VALOR_VEINTE)) ?

								ExisteMaterial.SI

								:

								ExisteMaterial.NO

		).reduce((existe1, existe2) -> existe1.equals(ExisteMaterial.SI) && existe2.equals(ExisteMaterial.SI)
				? ExisteMaterial.SI
				: ExisteMaterial.NO)
				.flatMap(solicitud -> solicitudConstruccionRepository
						.validaCoordenadas(solicitudConstruccionDBO.getCoordenadas()).hasElements()
						.map(x -> !x && ExisteMaterial.SI.equals(solicitud)))
				.filter(solicitud -> solicitud).flatMapMany((x) ->

				Flux.concat(tipoMaterialRepository.consultaMaterial(CEMENTO).flatMap(material -> {
					material.setCantidad(material.getCantidad() - VALOR_VEINTE);
					return tipoMaterialRepository.save(material);
				}), tipoMaterialRepository.consultaMaterial(GRAVA).flatMap(material -> {
					material.setCantidad(material.getCantidad() - VALOR_VEINTE);
					return tipoMaterialRepository.save(material);
				}), tipoMaterialRepository.consultaMaterial(ARENA).flatMap(material -> {
					material.setCantidad(material.getCantidad() - VALOR_VEINTE);
					return tipoMaterialRepository.save(material);
				}), tipoMaterialRepository.consultaMaterial(MADERA).flatMap(material -> {
					material.setCantidad(material.getCantidad() - VALOR_VEINTE);
					return tipoMaterialRepository.save(material);
				}), tipoMaterialRepository.consultaMaterial(ADOBE).flatMap(material -> {
					material.setCantidad(material.getCantidad() - VALOR_VEINTE);
					return tipoMaterialRepository.save(material);
				})

				)

				).flatMap(m -> {

					SolicitudConstruccionDBO solicitudConstruccionObj = new SolicitudConstruccionDBO();
					solicitudConstruccionObj.setId(idSolicitud.toString());
					solicitudConstruccionObj.setFechaCreacion(fechaCreacion);
					solicitudConstruccionObj.setTipoConstruccion(solicitudConstruccionDBO.getTipoConstruccion());
					solicitudConstruccionObj.setCoordenadas(solicitudConstruccionDBO.getCoordenadas());
					return solicitudConstruccionRepository.save(solicitudConstruccionObj);

				}).flatMap(s -> {

					OrdenConstruccionDBO OrdenConstruccionDBO = new OrdenConstruccionDBO();
					OrdenConstruccionDBO.setId(idOrden.toString());
					OrdenConstruccionDBO.setEstado(PENDIENTE);
					OrdenConstruccionDBO.setFechaInicio(fechaInicio);
					OrdenConstruccionDBO.setFechaFin(fechaFin);
					OrdenConstruccionDBO.setTipoConstruccion(s.getTipoConstruccion());
					OrdenConstruccionDBO.setIdSolicitud(idSolicitud.toString());

					return ordenConstruccionRepository.save(OrdenConstruccionDBO);

				}).subscribe();
	}

	private void RegistrarEdificio(SolicitudConstruccionDBO solicitudConstruccionDBO, ObjectId idSolicitud,
			ObjectId idOrden, LocalDateTime fechaCreacion) {
		tipoMaterialRepository.findAll().map(
				material -> ((material.getNombreMaterial().equals(CEMENTO) && material.getCantidad() > VALOR_DOCIENTOS)
						|| (material.getNombreMaterial().equals(GRAVA) && material.getCantidad() > VALOR_CIEN)
						|| (material.getNombreMaterial().equals(ARENA) && material.getCantidad() > VALOR_CIENTOOCHENTA)
						|| (material.getNombreMaterial().equals(MADERA) && material.getCantidad() > VALOR_CUARENTA)
						|| (material.getNombreMaterial().equals(ADOBE) && material.getCantidad() > VALOR_DOCIENTOS)) ?

								ExisteMaterial.SI

								:

								ExisteMaterial.NO

		).reduce((existe1, existe2) -> existe1.equals(ExisteMaterial.SI) && existe2.equals(ExisteMaterial.SI)
				? ExisteMaterial.SI
				: ExisteMaterial.NO)
				.flatMap(solicitud -> solicitudConstruccionRepository
						.validaCoordenadas(solicitudConstruccionDBO.getCoordenadas()).hasElements()
						.map(x -> !x && ExisteMaterial.SI.equals(solicitud)))
				.filter(solicitud -> solicitud).flatMapMany((x) ->

				Flux.concat(tipoMaterialRepository.consultaMaterial(CEMENTO).flatMap(material -> {
					material.setCantidad(material.getCantidad() - VALOR_DOCIENTOS);
					return tipoMaterialRepository.save(material);
				}), tipoMaterialRepository.consultaMaterial(GRAVA).flatMap(material -> {
					material.setCantidad(material.getCantidad() - VALOR_CIEN);
					return tipoMaterialRepository.save(material);
				}), tipoMaterialRepository.consultaMaterial(ARENA).flatMap(material -> {
					material.setCantidad(material.getCantidad() - VALOR_CIENTOOCHENTA);
					return tipoMaterialRepository.save(material);
				}), tipoMaterialRepository.consultaMaterial(MADERA).flatMap(material -> {
					material.setCantidad(material.getCantidad() - VALOR_CUARENTA);
					return tipoMaterialRepository.save(material);
				}), tipoMaterialRepository.consultaMaterial(ADOBE).flatMap(material -> {
					material.setCantidad(material.getCantidad() - VALOR_DOCIENTOS);
					return tipoMaterialRepository.save(material);
				})

				)

				).flatMap(m -> {

					SolicitudConstruccionDBO solicitudConstruccionObj = new SolicitudConstruccionDBO();
					solicitudConstruccionObj.setId(idSolicitud.toString());
					solicitudConstruccionObj.setFechaCreacion(fechaCreacion);
					solicitudConstruccionObj.setTipoConstruccion(solicitudConstruccionDBO.getTipoConstruccion());
					solicitudConstruccionObj.setCoordenadas(solicitudConstruccionDBO.getCoordenadas());
					return solicitudConstruccionRepository.save(solicitudConstruccionObj);

				}).flatMap(s -> {

					OrdenConstruccionDBO OrdenConstruccionDBO = new OrdenConstruccionDBO();
					OrdenConstruccionDBO.setId(idOrden.toString());
					OrdenConstruccionDBO.setEstado(PENDIENTE);
					OrdenConstruccionDBO.setFechaInicio(fechaInicio);
					OrdenConstruccionDBO.setFechaFin(fechaFin);
					OrdenConstruccionDBO.setTipoConstruccion(s.getTipoConstruccion());
					OrdenConstruccionDBO.setIdSolicitud(idSolicitud.toString());

					return ordenConstruccionRepository.save(OrdenConstruccionDBO);

				}).subscribe();
	}

	private void RegistrarGimnasio(SolicitudConstruccionDBO solicitudConstruccionDBO, ObjectId idSolicitud,
			ObjectId idOrden, LocalDateTime fechaCreacion) {
		tipoMaterialRepository.findAll().map(
				material -> ((material.getNombreMaterial().equals(CEMENTO) && material.getCantidad() > VALOR_CINCUENTA)
						|| (material.getNombreMaterial().equals(GRAVA) && material.getCantidad() > VALOR_VEINTICINCO)
						|| (material.getNombreMaterial().equals(ARENA) && material.getCantidad() > VALOR_CUARENTAICINCO)
						|| (material.getNombreMaterial().equals(MADERA) && material.getCantidad() > VALOR_DIEZ)
						|| (material.getNombreMaterial().equals(ADOBE) && material.getCantidad() > VALOR_CINCUENTA)) ?

								ExisteMaterial.SI

								:

								ExisteMaterial.NO

		).reduce((existe1, existe2) -> existe1.equals(ExisteMaterial.SI) && existe2.equals(ExisteMaterial.SI)
				? ExisteMaterial.SI
				: ExisteMaterial.NO)
				.flatMap(solicitud -> solicitudConstruccionRepository
						.validaCoordenadas(solicitudConstruccionDBO.getCoordenadas()).hasElements()
						.map(x -> !x && ExisteMaterial.SI.equals(solicitud)))
				.filter(solicitud -> solicitud).flatMapMany((x) ->

				Flux.concat(tipoMaterialRepository.consultaMaterial(CEMENTO).flatMap(material -> {
					material.setCantidad(material.getCantidad() - VALOR_CINCUENTA);
					return tipoMaterialRepository.save(material);
				}), tipoMaterialRepository.consultaMaterial(GRAVA).flatMap(material -> {
					material.setCantidad(material.getCantidad() - VALOR_VEINTICINCO);
					return tipoMaterialRepository.save(material);
				}), tipoMaterialRepository.consultaMaterial(ARENA).flatMap(material -> {
					material.setCantidad(material.getCantidad() - VALOR_CUARENTAICINCO);
					return tipoMaterialRepository.save(material);
				}), tipoMaterialRepository.consultaMaterial(MADERA).flatMap(material -> {
					material.setCantidad(material.getCantidad() - VALOR_DIEZ);
					return tipoMaterialRepository.save(material);
				}), tipoMaterialRepository.consultaMaterial(ADOBE).flatMap(material -> {
					material.setCantidad(material.getCantidad() - VALOR_CINCUENTA);
					return tipoMaterialRepository.save(material);
				})

				)

				).flatMap(m -> {

					SolicitudConstruccionDBO solicitudConstruccionObj = new SolicitudConstruccionDBO();
					solicitudConstruccionObj.setId(idSolicitud.toString());
					solicitudConstruccionObj.setFechaCreacion(fechaCreacion);
					solicitudConstruccionObj.setTipoConstruccion(solicitudConstruccionDBO.getTipoConstruccion());
					solicitudConstruccionObj.setCoordenadas(solicitudConstruccionDBO.getCoordenadas());
					return solicitudConstruccionRepository.save(solicitudConstruccionObj);

				}).flatMap(s -> {

					OrdenConstruccionDBO OrdenConstruccionDBO = new OrdenConstruccionDBO();
					OrdenConstruccionDBO.setId(idOrden.toString());
					OrdenConstruccionDBO.setEstado(PENDIENTE);
					OrdenConstruccionDBO.setFechaInicio(fechaInicio);
					OrdenConstruccionDBO.setFechaFin(fechaFin);
					OrdenConstruccionDBO.setTipoConstruccion(s.getTipoConstruccion());
					OrdenConstruccionDBO.setIdSolicitud(idSolicitud.toString());

					return ordenConstruccionRepository.save(OrdenConstruccionDBO);

				}).subscribe();
	}

}
