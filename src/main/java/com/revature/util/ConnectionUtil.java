package com.revature.util;



import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionUtil {
	public static Connection getConnection() throws SQLException, IOException, ClassNotFoundException {
		Class.forName("oracle.jdbc.driver.OracleDriver");
		Properties prop = new Properties();
		File file = new File("test.properties");
		System.out.println(file.getAbsoluteFile());
		InputStream in = ConnectionUtil.class.getClassLoader().getResourceAsStream("\\connection.properties");
		prop.load(in);
		System.out.println(prop);
		
		// need to provide: url to db, username, password
		// read in the contents of a properties file
		return DriverManager.getConnection(prop.getProperty("url"), prop.getProperty("username"),
				prop.getProperty("password"));
	}

//	public static Connection getConnection() throws SQLException, IOException {
//		// read in contents of a properties file - NEVER want to hardcode credentials	
//		Properties prop = new Properties();
//		//InputStream in = new FileInputStream("connection.properties");
//		prop.load(connectionUtil.class.getClassLoader().getResourceAsStream("src\\main\\java\\connection.properties"));
//		// need to provide: url to the database, username, password
//		return DriverManager.getConnection(prop.getProperty("url"), prop.getProperty("username"),
//				prop.getProperty("password"));
//	}
}
