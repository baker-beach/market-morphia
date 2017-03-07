package com.bakerbeach.market.morphia;

import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;

import com.mongodb.MongoClient;

public class DatastoreFactory {
	private Morphia morphia;
	private MongoClient mongoClient;
	private String dbName;
	private String packages = "";
	
	public Datastore newInstance() {
		for (String packageName : packages.split(",")) {
			morphia.mapPackage(packageName);
		}
		morphia.getMapper().getConverters().addConverter(BigDecimalConverter.class);
		return morphia.createDatastore(mongoClient, dbName);
	}
	
	public void setMorphia(Morphia morphia) {
		this.morphia = morphia;
	}
	
	public void setMongoClient(MongoClient mongoClient) {
		this.mongoClient = mongoClient;
	}
	
	public void setDbName(String dbName) {
		this.dbName = dbName;
	}
	
	public void setPackages(String packages) {
		this.packages = packages;
	}
	
}
