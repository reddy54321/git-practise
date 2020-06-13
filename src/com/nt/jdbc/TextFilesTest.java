package com.nt.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class TextFilesTest {
   private static final String  COLLEGE_QUERY="SELECT * FROM file1";
	public static void main(String[] args) {
		try {
			Class.forName("com.hxtt.sql.text.TextDriver");
		}
		catch(ClassNotFoundException cnf) {
			cnf.printStackTrace();
		}
		try(Connection con=DriverManager.getConnection("jdbc:text:///F:/Apps/advjava")){
			try(Statement st=con.createStatement()) {
				try(ResultSet rs=st.executeQuery(COLLEGE_QUERY)){
				    while(rs.next()) {
				    	System.out.println(rs.getInt(1)+"  "+rs.getString(2)+" "+rs.getString(3));
				    }
				}//try
			}//try
		}//trty
		catch(SQLException se) {
			se.printStackTrace();
		}
		
	}//main
}//class
