package com.co.ias.constructores.app.application.tipoMaterial.domain;

public class TipoMaterial {
	
	private  TipoMateriaId tipoMateriaId;
	
	private  NombreMaterial nombreMaterial;
	
	private  CodigoMaterial codigoMaterial;
	
	private  CantidadMaterial cantidadMaterial;

	public TipoMaterial(TipoMateriaId tipoMateriaId, NombreMaterial nombreMaterial, CodigoMaterial codigoMaterial,
			CantidadMaterial cantidadMaterial) {
		this.tipoMateriaId = tipoMateriaId;
		this.nombreMaterial = nombreMaterial;
		this.codigoMaterial = codigoMaterial;
		this.cantidadMaterial = cantidadMaterial;
	}
	
	public TipoMaterial(String nombreMaterial, String codigoMaterial,
			Integer cantidadMaterial) {

		this.nombreMaterial = new NombreMaterial(nombreMaterial);
		this.codigoMaterial = new CodigoMaterial(codigoMaterial);
		this.cantidadMaterial = new CantidadMaterial(cantidadMaterial);
	}

	public TipoMateriaId getTipoMateriaId() {
		return tipoMateriaId;
	}

	public void setTipoMateriaId(TipoMateriaId tipoMateriaId) {
		this.tipoMateriaId = tipoMateriaId;
	}

	public NombreMaterial getNombreMaterial() {
		return nombreMaterial;
	}

	public void setNombreMaterial(NombreMaterial nombreMaterial) {
		this.nombreMaterial = nombreMaterial;
	}

	public CodigoMaterial getCodigoMaterial() {
		return codigoMaterial;
	}

	public void setCodigoMaterial(CodigoMaterial codigoMaterial) {
		this.codigoMaterial = codigoMaterial;
	}

	public CantidadMaterial getCantidadMaterial() {
		return cantidadMaterial;
	}

	public void setCantidadMaterial(CantidadMaterial cantidadMaterial) {
		this.cantidadMaterial = cantidadMaterial;
	}
	
	
	

	
	
	
	

}
