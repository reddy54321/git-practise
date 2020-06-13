package com.nt.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class PersonAgeCaluclatorWithMYSQL {
   private static final String AGE_CALCULATOR="SELECT YEAR(CURDATE())-YEAR(DOB) FROM PERSON_TAB WHERE PID=?";
	public static void main(String[] args) {
		Connection con=null;
		Scanner sc=null;
		int no=0;
		PreparedStatement ps=null;
		ResultSet rs=null;
	
		try {
			//read inputs
			sc=new Scanner(System.in);
			if(sc!=null) {
				System.out.println("Enter Person Id::");
				no=sc.nextInt();
			}
			//register JDBC driver
			Class.forName("com.mysql.cj.jdbc.Driver");
			//establish the connection
			con=DriverManager.getConnection("jdbc:mysql:///NTAJ1111DB","root","root");
			//create PreparedStatement object
			if(con!=null) 
				ps=con.prepareStatement(AGE_CALCULATOR);
			//set values to Query params
			if(ps!=null)
				ps.setInt(1,no);
			//execute the Query
			if(ps!=null)
				rs=ps.executeQuery();
			//process the ResultSEt
			if(rs!=null) {
				if(rs.next()) {
					System.out.println("Person Age::"+rs.getFloat(1));
				}
				else {
					System.out.println("PErson not found ");
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
			
			try {
				if(sc!=null)
					sc.close();
			}
			catch(Exception e) {
				e.printStackTrace();
			}
			
		}//finally

	}

}
