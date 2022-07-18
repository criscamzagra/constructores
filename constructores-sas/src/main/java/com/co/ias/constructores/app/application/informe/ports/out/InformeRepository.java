package com.co.ias.constructores.app.application.informe.ports.out;

import java.io.IOException;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.co.ias.constructores.app.application.informe.model.InformeDTO;

@Repository
public interface InformeRepository {
	
	 List<InformeDTO> generarInforme(String ruta) throws IOException ;


}
