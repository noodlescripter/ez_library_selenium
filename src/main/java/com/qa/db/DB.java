package com.qa.db;

import  java.sql.Connection;		
import  java.sql.Statement;		
import  java.sql.ResultSet;		
import  java.sql.DriverManager;		
import  java.sql.SQLException;

public class DB {
	
	private  String db_url = "jdbc:mysql://localhost:3306/qa_db";
	private  String username = "root";
	private  String pwd = "admin_123";
	
	
	/*
	 * private static Connection establishConn() {
	 * 
	 * Connection conn = null;
	 * 
	 * try { Class.forName("com.mysql.cj.jdbc.Driver"); conn =
	 * DriverManager.getConnection(url, userName, pass);
	 * 
	 * } catch (Exception e) { System.out.println("Connection to DB failed"); }
	 * 
	 * return conn; }
	 */
	
	
	//@use this function to execute query
	public String query(String q) throws ClassNotFoundException, SQLException {
	
		// Query to Execute
		String rr = null;

		// Load mysql jdbc driver
		Class.forName("com.mysql.jdbc.Driver");

		// Create Connection to DB
		Connection con = DriverManager.getConnection(db_url, username, pwd);

		// Create Statement Object
		Statement stmt = con.createStatement();

		// Execute the SQL Query. Store results in ResultSet
		ResultSet set = stmt.executeQuery(q);
		while(set.next()) {
			rr = set.getString(1);
		}
		return rr;
	}
}