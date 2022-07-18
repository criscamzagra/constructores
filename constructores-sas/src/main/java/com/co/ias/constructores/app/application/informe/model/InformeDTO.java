package com.co.ias.constructores.app.application.informe.model;

import java.time.LocalDateTime;

import org.bson.types.ObjectId;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;

@JsonInclude(Include.NON_NULL)
public class InformeDTO {
	@JsonSerialize(using = ToStringSerializer.class)
	private ObjectId id;
	private String tipoConstruccion;
	private String coordenadas;
	private LocalDateTime fechaInicio;
	private LocalDateTime fechaFin;
	private String estado;

	public InformeDTO() {

	}

	public InformeDTO(ObjectId id, String tipoConstruccion, String coordenadas, LocalDateTime fechaInicio,
			LocalDateTime fechaFin, String estado) {

		this.id = id;
		this.tipoConstruccion = tipoConstruccion;
		this.coordenadas = coordenadas;
		this.fechaInicio = fechaInicio;
		this.fechaFin = fechaFin;
		this.estado = estado;
	}

	public ObjectId getId() {
		return id;
	}

	public void setId(ObjectId id) {
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

	@Override
	public String toString() {
		return "Informe:  tipoConstruccion= " + tipoConstruccion + ", coordenadas= " + coordenadas + ", fechaInicio= "
				+ fechaInicio + ", fechaFin= " + fechaFin + ", estado= " + estado + " ".concat("\n");
	}

}
