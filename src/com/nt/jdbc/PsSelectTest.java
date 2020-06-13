package com.nt.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

/* App to get Emp Details based on the given Employee number
 * Author: Team-J
 * Version : 1.0
 */

public class PsSelectTest {
	private static final String  GET_EMPS="SELECT EMPNO,ENAME,JOB,SAL FROM EMP ";
   //private static final String  GET_EMPS="SELECT EMPNO,ENAME,JOB,SAL FROM EMP WHERE JOB='CLERK'";
	public static void main(String[] args) {
		Connection con=null;
		ResultSet rs=null;
		PreparedStatement ps=null;
		boolean flag=false;
		try {
		 //register JDBC driver
			Class.forName("oracle.jdbc.driver.OracleDriver");
			//establish the connection
			con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","manager");
			//create PreparedStatement object
			if(con!=null)
				ps=con.prepareStatement(GET_EMPS);
			//send and execute the SQL Query
			if(ps!=null)
				rs=ps.executeQuery();
			
			//process the ResultSet
			if(rs!=null) {
				while(rs.next()) { 
					flag=true;
					System.out.println(rs.getInt(1)+"  "+rs.getString(2)+"  "+rs.getString(3)+"  "+rs.getInt(4));
				}
				if(flag==false)
					System.out.println("records not found");
				else
					System.out.println("records found and displayed");
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
				if(con!=null)
					con.close();
			}
			catch(SQLException se) {
				se.printStackTrace();
			}
		}//finally
	}//main
}//class
