package com.co.ias.constructores.app.application.tipoMaterial.model;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.co.ias.constructores.app.application.tipoMaterial.domain.CantidadMaterial;
import com.co.ias.constructores.app.application.tipoMaterial.domain.CodigoMaterial;
import com.co.ias.constructores.app.application.tipoMaterial.domain.NombreMaterial;
import com.co.ias.constructores.app.application.tipoMaterial.domain.TipoMateriaId;
import com.co.ias.constructores.app.application.tipoMaterial.domain.TipoMaterial;

@Document(collection = "tipoMaterial")
public class TipoMaterialDBO {
	@Id
	private String tipoMaterialId;
	@NotEmpty
	private String nombreMaterial;
	@NotEmpty
	private String codigoMaterial;
	@NotNull
	private Integer cantidad;
	


	public TipoMaterialDBO(String nombreMaterial, String codigoMaterial, Integer cantidad) {

		this.nombreMaterial = nombreMaterial;
		this.codigoMaterial = codigoMaterial;
		this.cantidad = cantidad;
	}
	

	public TipoMaterialDBO() {

	
	}


	public TipoMaterial toDomain() {
		return new TipoMaterial(new TipoMateriaId(this.tipoMaterialId), new NombreMaterial(this.nombreMaterial),
				new CodigoMaterial(this.codigoMaterial), new CantidadMaterial(this.cantidad));
	}

	public static TipoMaterialDBO fromDomain(TipoMaterial tipoMaterial) {
		return new TipoMaterialDBO(tipoMaterial.getNombreMaterial().getValue(),
				tipoMaterial.getCodigoMaterial().getValue(), tipoMaterial.getCantidadMaterial().getValue());
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
