package com.revature.beans;

import java.io.IOException;

import java.sql.SQLException;
import java.util.InputMismatchException;
import java.util.Scanner;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


public class User {
	
	

	public User(int USER_ID, String NAME,String LastName,String PASSWORD,String isAdmin, int manager) {
		super();
		 this.id = USER_ID;
		 this.firstName = NAME;
		 this.lastName = LastName;
		 this.admin = isAdmin;
		 this.password = PASSWORD;
		 this.setManager(manager);
		
	}
	
	private int manager;

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getAdmin() {
		return admin;
	}
	public void setAdmin(String admin) {
		this.admin = admin;
	}

	public int getManager() {
		return manager;
	}
	public void setManager(int manager) {
		this.manager = manager;
	}

	private int id;
	
	private String firstName;
	private String lastName;
	private String password;
	private String userName;
	private String admin;


	

	

}
