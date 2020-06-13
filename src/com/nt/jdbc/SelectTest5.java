package com.nt.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

/* App to get Emp Details based on the given Employee number
 * Author: Team-J
 * Version : 1.0
 */

public class SelectTest5 {

	public static void main(String[] args) {
		Scanner sc=null;
		int eno=0;
		Connection con=null;
		Statement st=null;
		String query=null;
		ResultSet rs=null;
		try {
		//read inputs
			sc=new Scanner(System.in);
			if(sc!=null) {
				System.out.println("Enter employee number::");
			   eno=sc.nextInt();
			}
		 //register JDBC driver
			Class.forName("oracle.jdbc.driver.OracleDriver");
			//establish the connection
			con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","manager");
			//create STatement object
			if(con!=null)
				st=con.createStatement();
			//prepare SQL Query
			          //SELECT EMPNO,ENAME,JOB,SAL FROM EMP WHERE EMPNO=7499
			query="SELECT EMPNO,ENAME,JOB,SAL FROM EMP WHERE EMPNO="+eno;
			//send and execute the SQL Query
			if(st!=null)
				rs=st.executeQuery(query);
			//process the ResultSet
			if(rs!=null) {
				if(rs.next()) 
					System.out.println(rs.getInt(1)+"  "+rs.getString(2)+"  "+rs.getString(3)+"  "+rs.getInt(4));
				else 
					System.out.println("Record not found");
					
				
				
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
			
			try {
				if(sc!=null)
					sc.close();
			}
			catch(Exception e) {
				e.printStackTrace();
			}
			
		}//finally
	}//main
}//class
