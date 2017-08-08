
package com.form.dao.implementation.mongodb.dbconnect;

import org.bson.Document;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;

/**
 * To connect to Mongo DB this provides 
 * Singleton pattern using Private Constructor
 * 
 *  import this and call getInstance()
 * 
 * @author visweshwaran
 *
 */
public final class FormDatabaseMongoDB {
	
		private FormDatabaseMongoDB(){
			
		}
	
	
	  private  static final MongoClient mc = new MongoClient( "localhost" , 27017 );
	  private  static final MongoCollection<Document> collection = mc.getDatabase("d3").getCollection("Users");
	  
	  /*
	   * Return a reference to 
	   * MongoDB collection "Users"
	   * MongoDB Database "d3" 
	   * 
	   * @return MongoCollection collection
	   */
	  public final static MongoCollection<Document> getInstance(){
		
		System.out.println("Form Database getInstance() executed");

		return collection;
	
	}
}
	


