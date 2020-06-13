package com.nt.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Type5DriverTest {
	private static final String  SELECT_STUDENTS_QUERY="SELECT SNO,SNAME,SADD FROM STUDENT";

	public static void main(String[] args) {
		try {
			Class.forName("com.ddtek.jdbc.oracle.OracleDriver");
		}
		catch(ClassNotFoundException cnf) {
			cnf.printStackTrace();
		}
		try(Connection con=DriverManager.getConnection("jdbc:datadirect:oracle://localhost:1521;serviceName=xe","system","manager")){
		   try(Statement st=con.createStatement()){
			   try(ResultSet rs=st.executeQuery(SELECT_STUDENTS_QUERY)){
				   while(rs.next()){
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
