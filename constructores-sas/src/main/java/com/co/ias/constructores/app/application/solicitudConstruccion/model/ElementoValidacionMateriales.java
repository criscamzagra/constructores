package com.co.ias.constructores.app.application.solicitudConstruccion.model;

import com.co.ias.constructores.app.application.tipoMaterial.model.TipoMaterialDBO;

public class ElementoValidacionMateriales {
	private ValidacionEstado validacionEstado;
	private TipoMaterialDBO tipoMaterialDBO;
	public ElementoValidacionMateriales(ValidacionEstado validacionEstado, TipoMaterialDBO tipoMaterialDBO) {
		this.validacionEstado = validacionEstado;
		this.tipoMaterialDBO = tipoMaterialDBO;
	}
	public ValidacionEstado getValidacionEstado() {
		return validacionEstado;
	}
	public void setValidacionEstado(ValidacionEstado validacionEstado) {
		this.validacionEstado = validacionEstado;
	}
	public TipoMaterialDBO getTipoMaterialDBO() {
		return tipoMaterialDBO;
	}
	public void setTipoMaterialDBO(TipoMaterialDBO tipoMaterialDBO) {
		this.tipoMaterialDBO = tipoMaterialDBO;
	}
	
	
	
	

}
