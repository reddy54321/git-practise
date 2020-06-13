package com.nt.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class MySQLToOracleApp {
  private  static final String  ORA_INSERT_QUERY="INSERT INTO BANKACCOUNT VALUES(?,?,?,?)";
  private  static final String  MYSQL_SELECT_QUERY="SELECT ACNO,HOLDERNAME,ADDRS,BALANCE FROM BANKACCOUNT";
  
	public static void main(String[] args) {
		Connection oraCon=null,mysqlCon=null;
		PreparedStatement ps=null;
		Statement st=null;
		ResultSet rs=null;
		int acno=0;
		String holder=null,addrs=null;
		float bal=0.0f;
		
		try {
			//register JDBC drivers
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			//establish the connections
			oraCon=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","manager");
			mysqlCon=DriverManager.getConnection("jdbc:mysql:///NTAJ1111DB", "root","root");
			//create Statement objects
			if(oraCon!=null)
				ps=oraCon.prepareStatement(ORA_INSERT_QUERY);
			if(mysqlCon!=null)
				 st=mysqlCon.createStatement();
			//execute Query and get Records from mysql
			if(st!=null) {
				rs=st.executeQuery(MYSQL_SELECT_QUERY);
			}
			//copy MYSQL DB table records to Oracle DB table
			if(rs!=null &ps!=null) {
			while(rs.next()) {
			   //get each record from Mysql DB table
				acno=rs.getInt(1);
				holder=rs.getString(2);
				addrs=rs.getString(3);
				bal=rs.getFloat(4);
				//set each record values to INSERT  query params (oracle)
				ps.setInt(1,acno);
				ps.setString(2,holder);
				ps.setString(3,addrs);
				ps.setFloat(4,bal);
				//execute INSERT Query
				ps.executeUpdate();
			}
			System.out.println("All records are copied");
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
			try {
				if(rs!=null)
					rs.close();
			}
			catch(SQLException se) {
				se.printStackTrace();
			}
			
			try {
				if(ps!=null)
					ps.close();
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
				if(oraCon!=null)
					oraCon.close();
			}
			catch(SQLException se) {
				se.printStackTrace();
			}
			
			try {
				if(mysqlCon!=null)
					mysqlCon.close();
			}
			catch(SQLException se) {
				se.printStackTrace();
			}
		}//finally
	}//main
}//class
