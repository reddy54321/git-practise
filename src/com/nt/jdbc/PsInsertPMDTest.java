package com.nt.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ParameterMetaData;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class PsInsertPMDTest {
   private static final  String INSERT_QUERY="INSERT INTO STUDENT VALUES(?,?,?)";
	public static void main(String[] args) {
		Connection con=null;
		PreparedStatement ps=null;
		ParameterMetaData pmd=null;
		int count=0;
		  try {
			
			  //register JDBC driver s/w
			 Class.forName("oracle.jdbc.driver.OracleDriver");
			 //establish the connection
			 con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","manager");
			 //create STatement object
			 if(con!=null)
				 ps=con.prepareStatement(INSERT_QUERY);
			//get ParmaeterMetaDAta obj
			 if(ps!=null)
				 pmd=ps.getParameterMetaData();
			 //parameter count
			 count=pmd.getParameterCount();
			 if(pmd!=null) {
				 for(int i=1;i<=count;++i) {
					 System.out.println("parameter number::"+i);
					 System.out.println("parameter type::"+pmd.getParameterTypeName(i));
					 System.out.println("pameter mode::"+pmd.getParameterMode(i));
				 }
			 }
		  }//try
		  catch(SQLException se) {
			  se.printStackTrace();
		  }
		  catch(ClassNotFoundException cnf) {
			  cnf.printStackTrace();
		  }
		  catch(Exception e) {
			  e.printStackTrace();
		  }
		  finally {
			  //close jdbc objs
			  try {
				  if(ps!=null)
					  ps.close();
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
