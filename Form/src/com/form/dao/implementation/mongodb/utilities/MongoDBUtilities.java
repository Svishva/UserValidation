
package com.form.dao.implementation.mongodb.utilities;

import static com.mongodb.client.model.Filters.eq;

import java.math.BigInteger;

import org.bson.Document;

import com.form.dao.implementation.mongodb.dbconnect.FormDatabaseMongoDB;
import com.form.model.beans.User;
import com.form.model.constants.Constants;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.IndexOptions;
import com.mongodb.client.model.Indexes;

/**
 * Some commenly used functions that UserDAOImpMongoDB.java uses are called
 * here. Methods : 5
 * 
 * 		getRandomNumber
 * 		getUniqueUserID 
 * 		createAdmin 
 * 		getAdminEmail 
 * 		getAdminPassword
 * 
 * @author Visweshwaran
 *
 */
public final class MongoDBUtilities implements Constants {
	
	
	
	private final static MongoCollection<Document> collection = FormDatabaseMongoDB.getInstance();

	/*
	 * METHOD :1 Return a Randomly generated String length 10
	 * 
	 * @Implementatin BigInteger Constructor and SecureRandom object
	 * 
	 * @return String length 10
	 */
	public static String getRandomNumber() {

		return new BigInteger(130, random).toString(32).substring(0, 10)
				.replaceAll(" ", "");

	}

	/*
	 * METHOD :2 Return a UserID which does not used in MongoDB
	 * 
	 * @return String
	 */
	public static Long getUniqueUserID() {

		Long i = 1l;

		try {
			while (collection.find(eq("UserID", i)).iterator().hasNext()) {

				i++;
			}
		} catch (Exception e) {

		}

		return i;
	}

	/*
	 * METHOD :3 
	 * 
	 * Create a Admin account with details that are used to send Mail
	 * to other users
	 * 
	 * 
	 */
	public static void createAdmin() {
		
		try {
			IndexOptions indexOptions = new IndexOptions().unique(true);
			collection.createIndex(Indexes.ascending("UserID", "UserEmail"),
					indexOptions);
			User user = new User.Builder().UserID(1l).UserName("admin")
					.UserEmail("svishva30@gmail.com")
					.UserPassword("xxxxxx").isNewUser(false).build();
			collection.insertOne(Document.parse(gson.toJson(user)));

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/*
	 * METHOD :4
	 * 
	 * Return Admin Email which is stored in MongoDB with 
	 * UserName : "admin"
	 * This email is used as from email to verify acount
	 * 
	 * @return String -Admin Email
	 * 
	 */
	public String getAdminEmail() {

		return collection.find(eq("UserName", "admin")).iterator().next()
				.get("UserEmail").toString().toLowerCase();

	}

	/*
	 * METHOD :5
	 * 
	 * Return Admin password which is stored in MongoDB with 
	 * UserName : "admin"
	 * This password is used as Admin email password for sending email
	 * @return String -Admin password
	 */
	public String getAdminPassword() {

		return collection.find(eq("UserName", "admin")).iterator().next()
				.get("UserPassword").toString();

	}

}
