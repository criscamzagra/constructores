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
	private LocalDateTime fechaCreacion;
	
	
	public SolicitudConstruccionDBO( @NotEmpty String tipoConstruccion, @NotEmpty String coordenadas) {
		
		this.tipoConstruccion = tipoConstruccion;
		this.coordenadas = coordenadas;



	}
	public SolicitudConstruccionDBO( @NotEmpty String tipoConstruccion, @NotEmpty String coordenadas,  @NotEmpty LocalDateTime fechaCreacion) {
		
		this.tipoConstruccion = tipoConstruccion;
		this.coordenadas = coordenadas;
		this.fechaCreacion = fechaCreacion;


	}
	public SolicitudConstruccionDBO() {
		
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTipoConstruccion() {
		return tipoConstruccion;
	}

	public void setTipoConstruccion(String tipoConstruccion) {
		this.tipoConstruccion = tipoConstruccion;
	}

	public String getCoordenadas() {
		return coordenadas;
	}

	public void setCoordenadas(String coordenadas) {
		this.coordenadas = coordenadas;
	}

	public LocalDateTime getFechaCreacion() {
		return fechaCreacion;
	}

	public void setFechaCreacion(LocalDateTime fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}
	
	



	
	
	
	


}
