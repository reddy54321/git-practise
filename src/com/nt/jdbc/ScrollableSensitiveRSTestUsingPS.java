package com.nt.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ScrollableSensitiveRSTestUsingPS {
    private  static final String  GET_STUDENTS_QUERY="SELECT SNO,SNAME,SADD FROM STUDENT";
	
	public static void main(String[] args) {
		Connection con=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		int count=0;
		try {
		//register JDBC driver s/w
		Class.forName("oracle.jdbc.driver.OracleDriver");
		//establish the connection
		con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","manager");
		//create PreparedStatement object
		if(con!=null)
				  ps=con.prepareStatement(GET_STUDENTS_QUERY, 
						                                          ResultSet.TYPE_SCROLL_INSENSITIVE,
				                                                   ResultSet.CONCUR_READ_ONLY);
				
		  //send and execute SQL Query
		 if(ps!=null)
			 rs=ps.executeQuery();
		 
		 if(rs!=null){
		   System.out.println("Records (TOP-BOTTOM)");
			 while(rs.next()) {
				 if(count==0) {
					 Thread.sleep(40000);  //during this period modify Db table records
				 }
				 count++;
			      System.out.println(rs.getInt(1)+"  "+rs.getString(2)+"  "+rs.getString(3)); 
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
