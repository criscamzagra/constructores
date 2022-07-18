package com.co.ias.constructores.app.application.solicitudConstruccion.model;

import java.time.LocalDateTime;

import javax.validation.constraints.NotEmpty;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "solicitudConstruccion")
public class SolicitudConstruccionDBO {
	@Id
	private String id;
	@NotEmpty
	private String tipoConstruccion;
	@NotEmpty
	private String coordenadas;
	@NotEmpty
	private LocalDateTime fechaInicio;
	@NotEmpty
	private LocalDateTime fechaFin;
	@NotEmpty
	private String estado;
	public SolicitudConstruccionDBO( @NotEmpty String tipoConstruccion, @NotEmpty String coordenadas) {
		
		this.tipoConstruccion = tipoConstruccion;
		this.coordenadas = coordenadas;
		this.estado="Pendiente";

	}
	
	public SolicitudConstruccionDBO() {
		
	}
	
	


	@Override
	public String toString() {
		return "SolicitudConstruccionDBO [id=" + id + ", tipoConstruccion=" + tipoConstruccion + ", coordenadas="
				+ coordenadas + ", fechaInicio=" + fechaInicio + ", fechaFin=" + fechaFin + ", estado=" + estado + "]";
	}

	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getTipoContruccion() {
		return tipoConstruccion;
	}
	public void setTipoContruccion(String tipoContruccion) {
		this.tipoConstruccion = tipoContruccion;
	}
	public String getCoordenadas() {
		return coordenadas;
	}
	public void setCoordenadas(String coordenadas) {
		this.coordenadas = coordenadas;
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
