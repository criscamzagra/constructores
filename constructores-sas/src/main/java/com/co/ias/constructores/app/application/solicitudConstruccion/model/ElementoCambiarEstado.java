package com.co.ias.constructores.app.application.solicitudConstruccion.model;

import com.co.ias.constructores.app.application.ordenConstruccion.model.OrdenConstruccionDBO;

public class ElementoCambiarEstado {
	
	private ValidacionEstado validacionEstado;
	
	private OrdenConstruccionDBO ordenConstruccionDBO;

	public ElementoCambiarEstado(ValidacionEstado validacionEstado, OrdenConstruccionDBO ordenConstruccionDBO) {
		this.validacionEstado = validacionEstado;
		this.ordenConstruccionDBO = ordenConstruccionDBO;
	}

	public ValidacionEstado getValidacionEstado() {
		return validacionEstado;
	}

	public void setValidacionEstado(ValidacionEstado validacionEstado) {
		this.validacionEstado = validacionEstado;
	}

	public OrdenConstruccionDBO getOrdenConstruccionDBO() {
		return ordenConstruccionDBO;
	}

	public void setOrdenConstruccionDBO(OrdenConstruccionDBO ordenConstruccionDBO) {
		this.ordenConstruccionDBO = ordenConstruccionDBO;
	}
	
	


	
	
	
	

}
