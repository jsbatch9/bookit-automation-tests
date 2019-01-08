package com.bookit.tests;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.testng.annotations.Test;

public class JDBCTest {
  
	String dbUrl = "jdbc:postgresql://localhost:5432/hr";
	String dbUsername = "postgres";
	String dbPassword = "abc";
		
	@Test
	public void PostGreSQL() throws SQLException {
		Connection connection = DriverManager.getConnection(dbUrl, dbUsername, dbPassword);
			
		
		connection.close();
	}
	
}
