package com.co.ias.constructores.app.application.solicitudConstruccion.model;

public class ElementoCambiarEstado {
	
	private ValidacionEstado validacionEstado;
	
	private SolicitudConstruccionDBO solicitudConstruccionDBO;

	public ElementoCambiarEstado(ValidacionEstado validacionEstado, SolicitudConstruccionDBO solicitudConstruccionDBO) {
		this.validacionEstado = validacionEstado;
		this.solicitudConstruccionDBO = solicitudConstruccionDBO;
	}

	public ValidacionEstado getValidacionEstado() {
		return validacionEstado;
	}

	public void setValidacionEstado(ValidacionEstado validacionEstado) {
		this.validacionEstado = validacionEstado;
	}

	public SolicitudConstruccionDBO getSolicitudConstruccionDBO() {
		return solicitudConstruccionDBO;
	}

	public void setSolicitudConstruccionDBO(SolicitudConstruccionDBO solicitudConstruccionDBO) {
		this.solicitudConstruccionDBO = solicitudConstruccionDBO;
	}
	
	
	
	
	

}
