package com.nt.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

public class RSMetaDataTest {
	
	public static void main(String[] args) {
		Connection con=null;
		Statement st=null;
		ResultSet rs=null;
		ResultSetMetaData rsmd=null;
		int colCount=0;
		try {
		//register JDBC driver s/w
		Class.forName("oracle.jdbc.driver.OracleDriver");
		//establish the connection
		con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","manager");
		//create STatement object
		if(con!=null)
				  st=con.createStatement();
		  //send and execute SQL Query
		 if(st!=null)
			 rs=st.executeQuery("SELECT SNO,SNAME,SADD FROM STUDENT");
		 if(rs!=null){
			 //get ResultSetMeta object
			 rsmd=rs.getMetaData();
			 colCount=rsmd.getColumnCount();
			 //print col names
			 for(int i=1;i<=colCount;++i)
				 System.out.print(rsmd.getColumnLabel(i)+"   ");
			 System.out.println();
				 
          //print col values			 
			 while(rs.next()) {
			     for(int i=1;i<=colCount;++i)
			    	 System.out.print(rs.getString(i)+"  ");
			     
			     System.out.println();
		    } //while
		 }//if
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
