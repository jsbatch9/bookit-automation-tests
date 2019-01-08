package com.bookit.tests;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.testng.annotations.Test;

public class JDBCTest {
  
	String dbUrl = "jdbc:postgresql://localhost:5432/hr";
	String dbUsername = "postgres";
	String dbPassword = "abc";
		
	@Test(enabled=false)
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
//		resultset.next();
//		resultset.next();
//		resultset.next();
//		resultset.next();
//		System.out.println(resultset.getRow());
//		
//		resultset.first();
//		
//		System.out.println(resultset.getRow());
		
		//find out how many record in the resultset?
		resultset.last();
		int rowsCount = resultset.getRow();
		System.out.println("Total number of rows: "+rowsCount);
		
		//how to move first row and loop everthing again after you are at last row ? 
		resultset.beforeFirst(); //0
		while(resultset.next()) {  //1
		System.out.println(resultset.getString(1)+"-"+resultset.getString("country_name")+"-"+resultset.getInt(3));
		}
		
		
		
		resultset.close();
		statement.close();
		connection.close();
	}
	
	
	@Test(enabled=false)
	public void JDBCMetaData() throws SQLException {
		Connection connection = DriverManager.getConnection(dbUrl, dbUsername, dbPassword);
		Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);;
		ResultSet resultset = statement.executeQuery("Select * from countries");
		
		//database metadata(create object)
		DatabaseMetaData dbMetadata =connection.getMetaData();
		
		//which username are we using ?
		System.out.println("User: "+ dbMetadata.getUserName());
		//database product name
		System.out.println("Database Product Name:"+dbMetadata.getDatabaseProductName());
		//database product version
		System.out.println("Database Product Version"+ dbMetadata.getDatabaseProductVersion());
		//-----------------
		
		//resultset metadata create object
		ResultSetMetaData rsMetadata = resultset.getMetaData();
		
		//how many columns we have ?
		System.out.println("Columns count:"+ rsMetadata.getColumnCount());
		
		//get column name
		System.out.println(rsMetadata.getColumnName(1));
		
		//get table name
		System.out.println(rsMetadata.getTableName(1));
		
		//print all column names using loop
		int columnCount = rsMetadata.getColumnCount();
		
		for(int i=1;i<=columnCount;i++) {
			System.out.println(rsMetadata.getColumnName(i));
		}
		
		
		
		
		resultset.close();
		statement.close();
		connection.close();
	}
	
	
	@Test
	public void DBUtil() throws SQLException {
		Connection connection = DriverManager.getConnection(dbUrl, dbUsername, dbPassword);
		Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);;
		ResultSet resultset = statement.executeQuery("SELECT first_name,last_name,salary,job_id FROM employees\n" + 
				"LIMIT 5;");
		
		//database metadata(create object)
		DatabaseMetaData dbMetadata =connection.getMetaData();
				
		//resultset metadata create object
		ResultSetMetaData rsMetadata = resultset.getMetaData();
		
		//our main structure, it will keep whole query result
		List<Map<String,Object>> queryData = new ArrayList<>();
		
		//we will add the first row data to this map
		Map<String,Object> row1 = new HashMap<>();
		
		//point the first row
		resultset.next();
		
		//key is column name, value is value of that column
		row1.put("first_name", "Steven");
		row1.put("last_name", "King");
		row1.put("Salary", 24000);
		row1.put("Job_id", "AD_PRES");
		
		//verify map is keeping all values 
		System.out.println(row1.toString());
		
		//push row1 map to list as a whole row 
		queryData.add(row1);
		
		System.out.println(queryData.get(0).get("first_name"));
		
		
		resultset.close();
		statement.close();
		connection.close();
	}
	
}
