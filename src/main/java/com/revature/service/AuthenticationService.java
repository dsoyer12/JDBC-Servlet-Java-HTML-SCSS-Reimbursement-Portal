package com.revature.service;



import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


import com.revature.beans.Credentials;
import com.revature.beans.User;
import com.revature.util.*;



public class AuthenticationService {
	
	// if credentials are not recognized, return null
	// if they are, return user associated with them
	public User authenticateUser(Credentials creds) throws SQLException, IOException, ClassNotFoundException {
		User u = null;
		

		String username = creds.getUsername();
		String password = creds.getPassword();
				
				  

				try (Connection conn = ConnectionUtil.getConnection()) {
					System.out.println("here1");
					String sql = "SELECT COUNT(USERNAME) AS COUNT, LASTNAME,FIRSTNAME,EMPLOYEEID,PASSWORD,ADMIN,REPORTSTO FROM EMPLOYEE WHERE USERNAME = ? AND PASSWORD = ? GROUP BY USERNAME,LASTNAME,FIRSTNAME,PASSWORD,EMPLOYEEID,ADMIN,REPORTSTO";
					PreparedStatement pstmt = conn.prepareStatement(sql);
					pstmt.setString(1, username);
					pstmt.setString(2, password);
					
					ResultSet rs= pstmt.executeQuery();
					System.out.println("here2");

					while(rs.next()) {
						System.out.println("query successful");
						int checker = rs.getInt("COUNT");
						String lastname = rs.getString("LASTNAME");
						String firstname = rs.getString("FIRSTNAME");
						int manager = rs.getInt("REPORTSTO");
						int id = rs.getInt("EMPLOYEEID");
						String admin = rs.getString("ADMIN");
						if(checker>0) {
							
							u = new User(id,firstname,lastname,password,admin,manager);
							
							
						
							
						}
								
		
	}}catch(SQLException e){ e.printStackTrace(); 
	Class.forName("oracle.jdbc.driver.OracleDriver");
		System.out.println("here");
	}
				return u;}}

