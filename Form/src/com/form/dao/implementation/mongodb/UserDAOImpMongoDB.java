/**
 * 
 */
package com.form.dao.implementation.mongodb;

import static com.mongodb.client.model.Filters.eq;

import java.io.FileNotFoundException;
import java.io.PrintWriter;

import org.bson.Document;

import com.form.dao.UserDAO;
import com.form.dao.implementation.mongodb.dbconnect.FormDatabaseMongoDB;
import com.form.dao.implementation.mongodb.utilities.MongoDBUtilities;
import com.form.model.beans.User;
import com.form.model.constants.Constants;
import com.form.model.mail.SendMail;
import com.mongodb.BasicDBObject;
import com.mongodb.client.MongoCollection;

/**
 * DAO implementation for UserDAO.java
 * Target Database : MongoDB
 * 
 * No of Methods: 3
 * 			createUser 
 * 			loginUser
 * 			changePassword
 * 
 * @author visweshwaran
 *
 */
public class UserDAOImpMongoDB implements UserDAO, Constants {

	
	private final MongoCollection<Document> collection = FormDatabaseMongoDB.getInstance();
	public static User loggedUser = new User();
	static MongoDBUtilities util = new MongoDBUtilities();
	String ErrorMessage;
	Boolean flag;
	Integer option;

	

	/*	METHOD: 1
	 * 
	 * Registers a new Record with Three values 
	 * (User Name, User Email, User Phone)
	 *  and send password to registered mail
	 *  
	 * @condition User Email and UserPhone 
	 *            must be unique.
	 * @overrides UserDAO method createUser(User user)
	 * @param User
	 * 
	 * @see com.form.dao.UserDAO 
	 * #createUser(com.form.model.beans.User)
	 */
	
	@Override
	public Boolean createUser(User user) {

		flag = false;

		try {

			System.out.println("create user executed");

			if (user.getUserName() != null && user.getUserEmail() != null) {

				if (collection.count() == 0l) {
					MongoDBUtilities.createAdmin();
				}

				user.setUserEmail(user.getUserEmail().toLowerCase());
				if (user.getUserEmail().equals(
						util.getAdminEmail().toLowerCase())) {
					flag = false;
					return flag;
				}
				if (collection.find(eq("UserPassword", user.getUserPhoneNo()))
						.iterator().hasNext()) {

					flag = false;
					return flag;

				}

				if (collection.find(eq("UserEmail", user.getUserEmail()))
						.iterator().hasNext()) {
					flag = false;
					return flag;
				}
				user.setUserID(MongoDBUtilities.getUniqueUserID());
				user.setisNewUser(true);
				String password = MongoDBUtilities.getRandomNumber();
				user.setUserPassword(password);
				System.out
						.println(Document.parse(gson.toJson(user)).toString());
				collection.insertOne(Document.parse(gson.toJson(user)));
				SendMail.toVerify(user.getUserEmail(), user.getUserName(),
						password);
				loggedUser = null;
				flag = true;

			}

		} catch (Exception e) {

			e.printStackTrace();
			try {

				e.printStackTrace(new PrintWriter(ErrorMessage));

			} catch (FileNotFoundException e1) {
				e1.printStackTrace();
			}
			// Send a mail to Admin mailwhat exception has occured
			SendMail.send(util.getAdminEmail(),
					" Exception at UserDAOImpMongoDB.java",
					ErrorMessage.toString());

		}
		return flag;

	}


	/*	METHOD: 2
	 * 
	 * Get email and password and check database if matches
	 * return Integer to Controller
	 * 
	 * 0 => User Loggin details are Wrong 
	 * 1 => User SuccessfullyLogged
	 * 2 => User logged for First time 
	 * 3 => Admin Loggined
	 * 
	 * 	@param email - String
	 *  @param password - String
	 *  @override UserDAO loginUser(String email,String password)
	 *  
	 * @see com.form.dao.UserDAO
	 * #loginUser(java.lang.String, java.lang.String)
	 * 
	 */
	public Integer loginUser(String email, String password) {

		option = 0;

		try {

			System.out.println("login user executed");

			if (collection.count() == 0l) {

				MongoDBUtilities.createAdmin();
			} 
				email = email.toLowerCase();
				BasicDBObject query = new BasicDBObject("UserEmail", email)
						.append("UserPassword", password);
				
				
				if (collection.find(query).iterator().hasNext()) {

					option = 1;

					loggedUser = gson.fromJson(collection.find(eq("UserEmail", email)).first().toJson(), User.class);
							
					flag = (Boolean) collection.find(query).iterator().next()
							.get("isNewUser");
					if (email.equals(util.getAdminEmail().toLowerCase())) {

						System.out.println("login user - admin if executed");

						option = 3;
						return option;
					}

					if (flag != null && flag == false) {
						// goto regular Login page
						System.out.println("login user  if flag !null executed");
								
					} else {

						// goto New User Change Password page
						System.out.println("login user  if else flag !null executed");
						option = 2;
					}
				} else {
					// user details are wrong goto login page
					System.out.println("login user  last part executed");
					loggedUser = null;
				}
			
		} catch (Exception e) {
			e.printStackTrace();

		}
		return option;
	}

	
	/*	METHOD: 3
	 * 
	 * change Randomly generated password with 
	 * user provided password.
	 * 
	 * User email is got from info when user logged in and that record gets
	 * Updated.
	 * @param oldpassword - String 
	 * @param new password - String
	 * @override	UserDAO changePassword(String oldpassword,String newpassoword)
	 *
	 *
	 * @see com.form.dao.UserDAO
	 * #changePassword(java.lang.String, java.lang.String)
	 */
	@Override
	public Boolean changePassword(String oldPassword, String newPassword) {

		flag = false;

		try {

			System.out.println("change pwd executed");

			if (loggedUser == null) {
				flag = false;
				return flag;

			} else if (loggedUser.getUserPassword().equals(oldPassword)) {

				System.out.println("oldPassword=" + oldPassword
						+ "\n newPassword" + newPassword);
				loggedUser.setUserPassword(newPassword);
				loggedUser.setisNewUser(false);
				collection.replaceOne(
						eq("UserEmail", loggedUser.getUserEmail()),
						Document.parse(gson.toJson(loggedUser)));
				System.out.println(Document.parse(gson.toJson(loggedUser))
						.toString());

				flag = true;

			} else {
				System.out.println("changepassword () last else part");

				flag = false;
			}

		} catch (Exception e) {
			e.printStackTrace();

		}
		return flag;
	}

}
