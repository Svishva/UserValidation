package com.form.model.mail;

/**
 * Methods to send Email using Java Mail API
 * 
 * methods: Two
 * 
 * 			toVerify 
 * 			send
 * 
 * @author visweshwaran
 */


import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import com.form.dao.implementation.mongodb.utilities.MongoDBUtilities;
public class SendMail {
	
	static MongoDBUtilities util = new MongoDBUtilities();
	
	
		/*
		 *  Email Template - used to send Randomly generated password to verify user
		 *  
		 *   @param email - To whom email want to be sent
		 *   @param name  - Nick name of Email owner
		 *   @param password -Randomly generated password
		 *   
		 *   @redirect send(email,subject,message) 
		 * 
		 */
		public static void toVerify(String email,String name,String password) {
			
			String message = "<h2><br><br>Hai "+name+"<br></h2><h3>";
			message += "Here is your password    "+password;
			message += "<br> Please login with your email and this password<br> </h3> ";
			send(email," Verify your Account",message);
			
		}
	
		
		/*
		 * will send  mail using Java Mail API
		 * 
		 * @param to - Email to whom Email want to send
		 * @param subject- Subject of email
		 * @param emailmessage- Content of the email
		 * 
		 */
	 public static void send(String to,String subject,String emailmessage){  
         //Get properties object    
		 final String from = util.getAdminEmail(); 
		 final String password = util.getAdminPassword();
         Properties props = new Properties();    
         props.put("mail.smtp.host", "smtp.gmail.com");    
         props.put("mail.smtp.socketFactory.port", "465");    
         props.put("mail.smtp.socketFactory.class",    
                   "javax.net.ssl.SSLSocketFactory");    
         props.put("mail.smtp.auth", "true");    
         props.put("mail.smtp.port", "465");    
         //get Session   
         Session session = Session.getInstance(props,    
          new javax.mail.Authenticator() {    
          protected PasswordAuthentication getPasswordAuthentication() {    
          return new PasswordAuthentication(from,password);  
          }    
         });    
         //compose message    
         try {    
          MimeMessage message = new MimeMessage(session);    
          message.addRecipient(Message.RecipientType.TO,new InternetAddress(to));    
          message.setSubject(subject);    
          message.setContent(emailmessage,"text/html");
         Transport.send(message);    
          System.out.println("A mail was send to "+to);    
         } catch (MessagingException e) {throw new RuntimeException(e);}    
            
   }  
  
	

}