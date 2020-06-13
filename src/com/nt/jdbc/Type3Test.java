package com.nt.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Type3Test {

	public static void main(String[] args) {
		Connection con=null;
		Statement st=null;
		ResultSet rs=null;
		try {
			//register  JDBC driver s/w
			Class.forName("ids.sql.IDSDriver");
			//establish the connection
			con=DriverManager.getConnection("jdbc:ids://localhost:12/conn?dsn=accdsn");
			//create Statement object 
			if(con!=null)
				st=con.createStatement();
			//send and execute SQL query in DB s/w
			if(st!=null)
				rs=st.executeQuery("SELECT SNO,SNAME,SADD FROM STUDENT");
			//process the ResultSet
			if(rs!=null) {
				while(rs.next()) {
					System.out.println(rs.getInt(1)+"  "+rs.getString(2)+"  "+rs.getString(3));
				}//while
			}//if
		}
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
