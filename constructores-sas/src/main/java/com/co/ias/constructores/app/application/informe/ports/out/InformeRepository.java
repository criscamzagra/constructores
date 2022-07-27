package com.co.ias.constructores.app.application.informe.ports.out;

import java.io.IOException;

import org.springframework.stereotype.Repository;

@Repository
public interface InformeRepository {
	
	 String  generarInforme(String ruta) throws IOException ;


}
