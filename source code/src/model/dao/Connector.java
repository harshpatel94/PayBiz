package model.dao;


/**
 * This Java file will help this java application to connect 
 * with MySQL database. This file returns connection object. 
 * So, through out the application where we need to establish
 * database connection we can call this file. 
 */

import java.sql.DriverManager;
import java.sql.SQLException;

import constants.DBconstants;

import java.sql.Connection;

public class Connector {

	/**
	 * Declaring all the static variable which can be use in whole program.
	 * So, in the future if some of the database credentials change 
	 * at only one place we have to modify it.
	 */
	public static Connector instance = new Connector();

	
	//private constructor
	public Connector(){
		try{
			//Step 2: Load MySQL JDBC Driver
			Class.forName(DBconstants.MYSQL_DRIVER);
		}catch(ClassNotFoundException e){
			System.err.println("Error : "+e);
		}
	}
	
	public static Connection createConnection(){
		
		//Connection object
		Connection con = null;
			try{
				//Step 3: Establish Java MySQL connection
				con = DriverManager.getConnection(DBconstants.DATABASE_PATH, DBconstants.USERNAME, DBconstants.PASSWORD);
			}catch(SQLException e){
				System.err.println("Error : "+e);
			} 
		return con;
	}
	
	// for getting connection
	public static Connection getConnection(){
		return instance.createConnection();
	}
}
