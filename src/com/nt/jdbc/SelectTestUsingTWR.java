package com.nt.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class SelectTestUsingTWR {
	public static void main(String[] args) {
		//register JDBC drivers/w
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		}
		catch(ClassNotFoundException cnf) {
			cnf.printStackTrace();
		}
		try(Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","manager")){
			try(Statement st=con.createStatement()){
				try(ResultSet rs=st.executeQuery("SELECT * FROM STUDENT")){
				      while(rs.next()) {
				    	  System.out.println(rs.getInt(1)+"  "+rs.getString(2)+"  "+rs.getString(3));
				      }//while
			   }//try
			}//try
		}//try
		catch(SQLException se) {
			se.printStackTrace();
		}
	}//main
}//class
