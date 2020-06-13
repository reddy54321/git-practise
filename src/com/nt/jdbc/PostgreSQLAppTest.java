package com.nt.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class PostgreSQLAppTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Connection con=null;
		Statement st=null;
		ResultSet rs=null;
		boolean flag=false;
	  try {
		  //register JDBC drivers/w
		  //Class.forName("org.postgresql.Driver");
		  //Establish the connection
		  //con=DriverManager.getConnection("jdbc:postgresql:NTAJ1111DB", "postgres", "tiger");
		  con=DriverManager.getConnection("jdbc:postgresql://localhost:5432/NTAJ1111DB", "postgres", "tiger");
		  //create Statement object
		  if(con!=null)
		    st=con.createStatement();
		  //send and execute SQL Query in DB s/w
		  if(st!=null)
			  rs=st.executeQuery("SELECT *  FROM STUDENT");
		  //process the ResultSet
		  if(rs!=null) {
			  while(rs.next()) {
				  flag=true;
				  System.out.println(rs.getInt(1)+"  "+rs.getString(2)+"  "+rs.getString(3));
			  }//while
		  }//if
		  if(!flag)
			  System.out.println("Record not found");
		  else
			  System.out.println("Records found and displayed");
			  
	  }
	/*  catch(ClassNotFoundException cnf) {
		  cnf.printStackTrace();
	  }*/
	  catch(SQLException se) {
		  se.printStackTrace();
	  }
	  finally {
		  //close jdbc objs
		  try {
			  if(rs!=null)
				  rs.close();
		  }
		  catch(SQLException se) {
			  se.printStackTrace();
		  }
		  try {
			  if(st!=null)
				  st.close();
		  }
		  catch(SQLException se) {
			  se.printStackTrace();
		  }
		  try {
			  if(con!=null)
				  con.close();
		  }
		  catch(SQLException se) {
			  se.printStackTrace();
		  }
	  }//finally
	}//main
}//class
