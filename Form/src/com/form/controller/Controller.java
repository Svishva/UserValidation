package com.form.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.form.dao.implementation.mongodb.UserDAOImpMongoDB;
import com.form.model.beans.User;

/**
 * Servlet implementation class Controller Controls where to redirect when user
 * sends Http requests.
 * 
 * This class extends HttpServlet and all Http method request are caught here
 * and redirected to respected fuctions (like create, update, delete) 
 * 
 * methods: Two
 * 
 * 		doGet
 * 		doPost
 * 
 * @author visweshwaran
 * 
 */
@WebServlet("/Controller")
public class Controller extends HttpServlet {

	private static final long serialVersionUID = 1L;
	public UserDAOImpMongoDB obj = new UserDAOImpMongoDB();


	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
	}

	
	
	
	/**
	 * All form submissions are got here and redirected to respected pages 
	 * on processing user data.
	 * 
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		System.out.println("doPost method executed");

		try {

			/*
			 * 	when User Submit Createuser Form
			 *  this if gets executed
			 *  
			 *  @call createUser(User user) - UserDAOImpMongoDB.java
			 */
			if (request.getParameter("register") != null) {

				System.out.println("register if executed");

				String name = request.getParameter("name");
				String email = request.getParameter("email");
				String phonenumber = request.getParameter("phonenumber");
				User user = new User.Builder().UserEmail(email).UserName(name)
						.isNewUser(true).UserPhoneNo(phonenumber).build();

				if (obj.createUser(user) == true) {
					request.getSession().setAttribute("UserName", name);
					response.sendRedirect("logininfo");
					return;
				} else {
					response.sendRedirect("registerfailed");
					return;
				}

			}

			/*
			 * 	when User Submit changepassword Form
			 *  new password 
			 *  @call changePassword(oldpassword,newpassword) - UserDAOImpMongoDB.java
			 *  this if get executed  
			 */
			if (request.getParameter("updatepassword") != null) {

				System.out.println("updata pwd if executed");

				if (obj.changePassword(request.getParameter("oldpassword"),
						request.getParameter("newpassword2")) == true) {

					response.sendRedirect("passwordchanged");
					return;
				} else {
					response.sendRedirect("passwordnotchanged");
					return;
				}

			}

			/*
			 * 	when User submit login Form
			 *  this if get executed 
			 * 	@call loginUser(email,password) - UserDAOImpMongoDB.java
			 * 
			 *  switch case
			 *  0 => User Loggin details are Wrong 
			 *  1 => User SuccessfullyLogged 
			 *  2 => User logged for First time
			 *  3 => Admin Loggined
			 * 
			 */
			if (request.getParameter("login") != null) {

				System.out.println("login if executed");

				switch (obj.loginUser(request.getParameter("email"),
						request.getParameter("password"))) {

				case 0:

					response.sendRedirect("loginfailed");
					return;
				case 1:
					request.getSession().setAttribute("UserName",
							UserDAOImpMongoDB.loggedUser.getUserName());
					response.sendRedirect("logged");
					return;
				case 2:
					request.getSession().setAttribute("UserName",
							UserDAOImpMongoDB.loggedUser.getUserName());
					response.sendRedirect("changepassword");
					return;
				case 3:
					response.sendRedirect("admin");
					return;
				default:
					response.sendRedirect("home");

				}

			}

		} catch (Exception e) {

			e.printStackTrace();

		}

	}

}
