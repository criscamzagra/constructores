package com.co.ias.constructores.app.application.solicitudConstruccion.services;

public class CoordenadaExistente extends RuntimeException{


	private static final long serialVersionUID = 1L;



	public CoordenadaExistente() {
		super("La coordenada enviada ya se encuentra registrada");
		
	}


	
	
	

}
