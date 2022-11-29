package com.qa.db;

import  java.sql.Connection;		
import  java.sql.Statement;		
import  java.sql.ResultSet;		
import  java.sql.DriverManager;		
import  java.sql.SQLException;

public class DB {
	
	private static String url = "jdbc:mysql://10.147.18.21/node_mysql_crud_db";
	private static String userName = "build4api";
	private static String pass = "build4api";
	
	
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
	public static String query(String q) throws ClassNotFoundException, SQLException {
		String dbUrl = "jdbc:mysql://10.147.18.21/node_mysql_crud_db";

		// Database Username
		String username = "build4api";

		// Database Password
		String password = "build4api";

		// Query to Execute
		String rr = null;

		// Load mysql jdbc driver
		Class.forName("com.mysql.jdbc.Driver");

		// Create Connection to DB
		Connection con = DriverManager.getConnection(dbUrl, username, password);

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

