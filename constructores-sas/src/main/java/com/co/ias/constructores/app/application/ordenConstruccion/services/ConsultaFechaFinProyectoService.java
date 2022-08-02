package com.co.ias.constructores.app.application.ordenConstruccion.services;

import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;

import org.bson.Document;
import org.bson.conversions.Bson;
import org.springframework.stereotype.Service;

import com.co.ias.constructores.app.application.ordenConstruccion.ports.out.ConsultaFechaFinRepository;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

@Service
public class ConsultaFechaFinProyectoService implements ConsultaFechaFinRepository {
	
	private final MongoClient client;
	String fecha;


	public ConsultaFechaFinProyectoService(MongoClient mongoClient) {
		this.client = mongoClient;
	}

	@Override
	public String consultaFechaFin() {
		 MongoDatabase database = client.getDatabase("contructores");
         MongoCollection<Document> collection = database.getCollection("ordenConstruccion");
         
         // Created with Studio 3T, the IDE for MongoDB - https://studio3t.com/
         
         Consumer<Document> processBlock = new Consumer<Document>() {
             @Override
             public void accept(Document document) {
            	  fecha= document.toJson();
             }
         };
         
         List<? extends Bson> pipeline = Arrays.asList(
                 new Document()
                         .append("$group", new Document()
                                 .append("_id", new Document())
                                 .append("MAX(fechaFin)", new Document()
                                         .append("$max", "$fechaFin")
                                 )
                         ), 
                 new Document()
                         .append("$project", new Document()
                                 .append("Fecha Fin", "$MAX(fechaFin)")
                                 .append("_id", 0)
                         )
         );
         
         collection.aggregate(pipeline)
                 .allowDiskUse(true)
                 .forEach(processBlock);
		return "La fecha final del proyecto es : ".concat(fecha);
	}

}
