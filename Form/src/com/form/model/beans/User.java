
package com.form.model.beans;

/**
 * User Beans Class
 * 
 * User info  processed between Frontend and Backend use
 * this Beans.
 * Uses Builder Pattern to construct this beans
 * 
 * 
 * @author visweshwaran
 *
 */
public class User {
	
	
	private Long UserID;
	private String UserName;
	private String UserEmail;
	private String UserPassword;
	private String UserPhoneNo;
	private String UserAge;
	private Boolean isNewUser;
	
	public User(Builder builder){
		
		UserID= builder.UserID;
		UserName = builder.UserName;
		UserEmail = builder.UserEmail;
		UserPassword=builder.UserPassword;
		UserPhoneNo= builder.UserPhoneNo;
		UserAge = builder.UserAge;
		isNewUser= builder.isNewUser;
	}
	
	public User() {
	}

	public static class Builder{
		
		private Long UserID;
		private String UserName;
		private String UserEmail;
		private String UserPassword;
		private String UserPhoneNo;
		private String UserAge;
		private Boolean isNewUser=true;
		
		public Builder(){
			
			
		}
		
		public User build(){
			
			return new User(this);
		}
		
		public Builder UserID(Long val){
			
			this.UserID = val;
			return this;
		}
		
		public Builder UserName(String val) {
			UserName=val;
			return this;
		}
		public Builder UserEmail(String val) {
			UserEmail=val;
			return this;
		}
		public Builder UserPassword(String val) {
			UserPassword=val;
			return this;
		}
		public Builder UserPhoneNo(String val) {
			UserPhoneNo=val;
			return this;
		}
		public Builder UserAge(String val) {
			UserAge=val;
			return this;
		}
		public Builder isNewUser(Boolean val) {
			isNewUser=val;
			return this;
		}
		
	}
	
	public Long getUserID() {
		return UserID;
	}
	public void setUserID(Long userID) {
		UserID = userID;
	}
	public String getUserName() {
		return UserName;
	}
	public void setUserName(String userName) {
		UserName = userName;
	}
	public String getUserEmail() {
		return UserEmail;
	}
	public void setUserEmail(String userEmail) {
		UserEmail = userEmail;
	}
	public String getUserPassword() {
		return UserPassword;
	}
	public void setUserPassword(String userPassword) {
		UserPassword = userPassword;
	}
	public String getUserPhoneNo() {
		return UserPhoneNo;
	}
	public void setUserPhoneNo(String userPhoneNo) {
		UserPhoneNo = userPhoneNo;
	}
	public String getUserAge() {
		return UserAge;
	}
	public void setUserAge(String userAge) {
		UserAge = userAge;
	}
	public Boolean getisNewUser() {
		return isNewUser;
	}
	public void setisNewUser(Boolean newUser) {
		isNewUser = newUser;
	}





}
