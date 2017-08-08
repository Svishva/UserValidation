
	/*
	 *  DAO for User Beans
	 *  Contains methods that are processed on user  
	 *  
	 *  methods : Three
	 *  	createUser
	 * 		loginUser
	 * 		changePassword
	 * 		 
	 * 
	 *  @author visweshwarn
	 */

package com.form.dao;
import com.form.model.beans.User;

public interface UserDAO {

	/*	METHOD: 1
	 * 
	 * Registers a new Record with Three values 
	 * (User Name, User Email, User Phone)
	 *  and send password to registered mail
	 * 
	 * @param User 
	 * @condition User Email and UserPhone 
	 *            must be unique.
	 */
	Boolean createUser(User user);
	
	
	
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
	 * 
	 */
	Integer loginUser(String email , String password);
	
	
	/*	METHOD: 3
	 * 
	 * change Randomly generated password with 
	 * user provided password.
	 * 
	 * User email is got from info when user logged in and that record gets
	 * Updated.
	 * @param oldpassword - String 
	 * @param new password - String
	 * 
	 */
	Boolean changePassword(String oldPassword, String newPassword);
	
}
