package com.co.ias.constructores.app.application.informe.services;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.co.ias.constructores.app.application.informe.model.InformeDTO;
import com.co.ias.constructores.app.application.informe.ports.out.InformeRepository;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;

@Repository
public class InformeService implements InformeRepository {

	public static final Logger log = LoggerFactory.getLogger(InformeService.class);


	private final MongoClient client;
	private MongoCollection<InformeDTO> informeCollection;

	public InformeService(MongoClient mongoClient) {
		this.client = mongoClient;
	}

	@PostConstruct
	void init() {
		informeCollection = client.getDatabase("contructores").getCollection("solicitudConstruccion", InformeDTO.class);
	}

	@Override
	public List<InformeDTO> generarInforme(String rutaArchivo) throws IOException {
		ArrayList<InformeDTO> listaInforme = informeCollection.find().into(new ArrayList<>());
		String ruta = rutaArchivo;
		String contenido = "";

		for (InformeDTO item : listaInforme) {
			InformeDTO obj = new InformeDTO();
			obj.setTipoConstruccion(item.getTipoConstruccion());
			obj.setCoordenadas(item.getCoordenadas());
			obj.setFechaInicio(item.getFechaInicio());
			obj.setFechaFin(item.getFechaFin());
			obj.setEstado(item.getEstado());
			contenido += obj;

		}


			File file = new File(ruta);
			// Si el archivo no existe es creado
			if (!file.exists()) {
				file.createNewFile();
			}
			FileWriter fw = new FileWriter(file);
			BufferedWriter bw = new BufferedWriter(fw);
			bw.write(contenido);
			bw.close();

	
		return informeCollection.find().into(new ArrayList<>());
	}

}
