package com.co.ias.constructores.app.application.tipoMaterial.model;

import com.co.ias.constructores.app.application.tipoMaterial.domain.CantidadMaterial;
import com.co.ias.constructores.app.application.tipoMaterial.domain.CodigoMaterial;
import com.co.ias.constructores.app.application.tipoMaterial.domain.TipoMateriaId;
import com.co.ias.constructores.app.application.tipoMaterial.domain.TipoMaterial;
import com.co.ias.constructores.app.application.tipoMaterial.domain.NombreMaterial;

public class TipoMaterialDTO {

	private String tipoMaterialId;

	private String nombreMaterial;

	private String codigoMaterial;

	private Integer cantidad;

	public TipoMaterialDTO(String tipoMaterialId, String nombreMaterial, String codigoMaterial, Integer cantidad) {
		this.tipoMaterialId = tipoMaterialId;
		this.nombreMaterial = nombreMaterial;
		this.codigoMaterial = codigoMaterial;
		this.cantidad = cantidad;
	}

	public TipoMaterial toDomain() {
		return new TipoMaterial(new TipoMateriaId(this.tipoMaterialId), new NombreMaterial(this.nombreMaterial),
				new CodigoMaterial(this.codigoMaterial), new CantidadMaterial(this.cantidad));
	}

	public static TipoMaterialDTO fromDomain(TipoMaterial tipoMaterial) {
		return new TipoMaterialDTO(tipoMaterial.getTipoMateriaId().getValue(),
				tipoMaterial.getNombreMaterial().getValue(), tipoMaterial.getCodigoMaterial().getValue(),
				tipoMaterial.getCantidadMaterial().getValue());
	}

	public String getTipoMaterialId() {
		return tipoMaterialId;
	}

	public void setTipoMaterialId(String tipoMaterialId) {
		this.tipoMaterialId = tipoMaterialId;
	}

	public String getNombreMaterial() {
		return nombreMaterial;
	}

	public void setNombreMaterial(String nombreMaterial) {
		this.nombreMaterial = nombreMaterial;
	}

	public String getCodigoMaterial() {
		return codigoMaterial;
	}

	public void setCodigoMaterial(String codigoMaterial) {
		this.codigoMaterial = codigoMaterial;
	}

	public Integer getCantidad() {
		return cantidad;
	}

	public void setCantidad(Integer cantidad) {
		this.cantidad = cantidad;
	}

}
