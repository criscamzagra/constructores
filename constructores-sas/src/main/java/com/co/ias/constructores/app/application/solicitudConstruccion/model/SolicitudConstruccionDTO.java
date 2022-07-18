package com.co.ias.constructores.app.application.solicitudConstruccion.model;


import java.time.LocalDateTime;

public class SolicitudConstruccionDTO {
	
	private String id;
	
	private String tipoConstruccion;
	
	private String coordenadas;
	
	private LocalDateTime fechaInicio;
	
	private LocalDateTime fechaFin;
	
	private String estado;

	public SolicitudConstruccionDTO( String tipoConstruccion, String coordenadas) {

		this.tipoConstruccion = tipoConstruccion;
		this.coordenadas = coordenadas;
	}
	public SolicitudConstruccionDTO( ) {

	
	}
	

	public SolicitudConstruccionDTO(String id, String tipoContruccion, String coordenadas, LocalDateTime fechaInicio,
			LocalDateTime fechaFin, String estado) {
		this.id = id;
		this.tipoConstruccion = tipoContruccion;
		this.coordenadas = coordenadas;
		this.fechaInicio = fechaInicio;
		this.fechaFin = fechaFin;
		this.estado = estado;
	}
	
	public SolicitudConstruccionDTO( String tipoContruccion, String coordenadas, LocalDateTime fechaInicio,
			LocalDateTime fechaFin, String estado) {
		this.tipoConstruccion = tipoContruccion;
		this.coordenadas = coordenadas;
		this.fechaInicio = fechaInicio;
		this.fechaFin = fechaFin;
		this.estado = estado;
	}
	public SolicitudConstruccionDTO(LocalDateTime fechaFin) {
		
		this.fechaFin = fechaFin;

	}
	


	public static SolicitudConstruccionDBO fromDomain(SolicitudConstruccionDTO solicitudConstruccionDTO) {
		return new SolicitudConstruccionDBO(solicitudConstruccionDTO.getTipoContruccion(),solicitudConstruccionDTO.getCoordenadas());
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
