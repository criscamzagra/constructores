package com.co.ias.constructores.app.application.solicitudConstruccion.services;

public class CoordenadaExistenteException extends RuntimeException{


	private static final long serialVersionUID = 1L;



	public CoordenadaExistenteException() {
		super("La coordenada enviada ya se encuentra registrada");
		
	}


	
	
	

}
