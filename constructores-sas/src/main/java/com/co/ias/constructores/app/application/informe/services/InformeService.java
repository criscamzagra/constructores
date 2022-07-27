package com.co.ias.constructores.app.application.informe.services;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;

import org.bson.Document;
import org.bson.conversions.Bson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.co.ias.constructores.app.application.informe.ports.out.InformeRepository;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

@Repository
public class InformeService implements InformeRepository {

	public static final Logger log = LoggerFactory.getLogger(InformeService.class);
	String contenido;


	private final MongoClient client;


	public InformeService(MongoClient mongoClient) {
		this.client = mongoClient;
	}


	@Override
	public String generarInforme(String rutaArchivo) throws IOException {
		contenido=" ";
	    MongoDatabase database = client.getDatabase("contructores");
		MongoCollection<Document> collection = database.getCollection("solicitudConstruccion");
		
        Consumer<Document> processBlock = new Consumer<Document>() {
            @Override
            public void accept(Document document) {
            	
            	contenido+= document.toJson().concat("\n");
        		


            	
    			try {
    				File file = new File(rutaArchivo);
        			// Si el archivo no existe es creado
        			if (!file.exists()) {
        				file.createNewFile();
        			}
        			FileWriter fw = new FileWriter(file);
        			BufferedWriter bw = new BufferedWriter(fw);
					bw.write(contenido);
					bw.close();
				} catch (IOException e) {
					log.error(e.getMessage());
				}
    		
            }
        };
        
        List<? extends Bson> pipeline = Arrays.asList(
                new Document()
                        .append("$group", new Document()
                                .append("_id", new Document()
                                        .append("tipoConstruccion", "$tipoConstruccion")
                                        .append("estado", "$estado")
                                )
                                .append("COUNT(*)", new Document()
                                        .append("$sum", 1)
                                )
                        ), 
                new Document()
                        .append("$project", new Document()
                                .append("tipoConstruccion", "$_id.tipoConstruccion")
                                .append("estado", "$_id.estado")
                                .append("Cantidad", "$COUNT(*)")
                                .append("_id", 0)
                        )
        );
        
        collection.aggregate(pipeline)
                .allowDiskUse(true)
                .forEach(processBlock);
	
		return "Realizado";
	}

}
