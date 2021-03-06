package com.bakerbeach.market.morphia;

import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;
import org.springframework.beans.factory.config.AbstractFactoryBean;

import com.mongodb.MongoClient;

public class DatastoreFactory2 extends AbstractFactoryBean<Datastore> {
	protected Morphia morphia;
	protected MongoClient mongoClient;
	protected String dbName;
	protected String packages = "";

	@Override
	public Class<?> getObjectType() {
		return Datastore.class;
	}

	@Override
	protected Datastore createInstance() throws Exception {
		for (String packageName : packages.split(",")) {
			morphia.mapPackage(packageName);
		}

		if (!morphia.getMapper().getConverters().isRegistered(BigDecimalConverter.class)) {
			morphia.getMapper().getConverters().addConverter(BigDecimalConverter.class);
		}
		if (!morphia.getMapper().getConverters().isRegistered(CurrencyConverter.class)) {
			morphia.getMapper().getConverters().addConverter(CurrencyConverter.class);
		}

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
