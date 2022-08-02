package com.co.ias.constructores.app.application.ordenConstruccion.model;

import java.time.LocalDateTime;

import javax.validation.constraints.NotEmpty;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "ordenConstruccion")
public class OrdenConstruccionDBO {
	@Id
	private String id;
	@NotEmpty
	private String idSolicitud;
	@NotEmpty
	private String tipoConstruccion;
	@NotEmpty
	private LocalDateTime fechaInicio;
	@NotEmpty
	private LocalDateTime fechaFin;
	@NotEmpty
	private String estado;
	
	public OrdenConstruccionDBO(String id, @NotEmpty String idSolicitud, @NotEmpty String tipoConstruccion,
			@NotEmpty LocalDateTime fechaInicio, @NotEmpty LocalDateTime fechaFin, @NotEmpty String estado) {
		this.id = id;
		this.idSolicitud = idSolicitud;
		this.tipoConstruccion = tipoConstruccion;
		this.fechaInicio = fechaInicio;
		this.fechaFin = fechaFin;
		this.estado = estado;
	}
	
	public OrdenConstruccionDBO(@NotEmpty String idSolicitud, @NotEmpty String tipoConstruccion,
			@NotEmpty LocalDateTime fechaInicio, @NotEmpty LocalDateTime fechaFin, @NotEmpty String estado) {
		this.idSolicitud = idSolicitud;
		this.tipoConstruccion = tipoConstruccion;
		this.fechaInicio = fechaInicio;
		this.fechaFin = fechaFin;
		this.estado = estado;
	}

	public OrdenConstruccionDBO() {
	
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getIdSolicitud() {
		return idSolicitud;
	}

	public void setIdSolicitud(String idSolicitud) {
		this.idSolicitud = idSolicitud;
	}

	public String getTipoConstruccion() {
		return tipoConstruccion;
	}

	public void setTipoConstruccion(String tipoConstruccion) {
		this.tipoConstruccion = tipoConstruccion;
	}

	public LocalDateTime getFechaInicio() {
		return fechaInicio;
	}

	public void setFechaInicio(LocalDateTime fechaInicio) {
		this.fechaInicio = fechaInicio;
	}

	public LocalDateTime getFechaFin() {
		return fechaFin;
	}

	public void setFechaFin(LocalDateTime fechaFin) {
		this.fechaFin = fechaFin;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	

}
