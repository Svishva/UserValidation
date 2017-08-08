package com.form.model.constants;

	/*
	 * This Interface is for some constants that are 
	 * used Globally
	 * 
	 * @author visweshwaran
	 */
import java.security.SecureRandom;

import org.bson.Document;

import com.form.dao.implementation.mongodb.dbconnect.FormDatabaseMongoDB;
import com.google.gson.Gson;
import com.mongodb.client.MongoCollection;

public interface Constants {

	Gson gson = new Gson();
	SecureRandom random = new SecureRandom();
}
