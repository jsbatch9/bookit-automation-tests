package com.bookit.tests;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.testng.annotations.Test;

public class JDBCTest {
  
	String dbUrl = "jdbc:postgresql://localhost:5432/hr";
	String dbUsername = "postgres";
	String dbPassword = "abc";
		
	@Test
	public void PostGreSQL() throws SQLException {
		Connection connection = DriverManager.getConnection(dbUrl, dbUsername, dbPassword);
		Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);;
		ResultSet resultset = statement.executeQuery("Select * from countries");
		
		// next method -> move pointer to next row
//		resultset.next();
//		System.out.println(resultset.getString(1)+"-"+resultset.getString("country_name")+"-"+resultset.getInt(3));

//		while(resultset.next()) {
//			System.out.println(resultset.getString(1)+"-"+resultset.getString("country_name")+"-"+resultset.getInt(3));
//
//		}
		resultset.next();
		resultset.next();
		resultset.next();
		resultset.next();
		System.out.println(resultset.getRow());
		
		resultset.first();
		
		System.out.println(resultset.getRow());
		
		resultset.close();
		statement.close();
		connection.close();
	}
	
}
