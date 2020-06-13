package com.nt.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class UpdateTest {
	public static void main(String[] args) {
		Scanner sc=null;
		int no=0;
		String newName=null,newAddrs=null;
		Connection con=null;
		Statement st=null;
		String query=null;
		int count=0;
		try {
			//read inputs
			sc=new Scanner(System.in);
			if(sc!=null) {
				System.out.println("Enter student number to modify details::");
				no=sc.nextInt();  //101
				sc.nextLine();
				System.out.println("Enter new name for student::");
				newName=sc.nextLine(); //rajesh khanna 
				System.out.println("Enter new  address for student::");
				newAddrs=sc.nextLine();  // navi  mumbai
			}
			//convert input values as required for the SQL Query
			newName="'"+newName+"'"; //gives 'rajesh khanna'
			newAddrs="'"+newAddrs+"'"; //gives navi mumbai
			//register JDBC driver s/w
			Class.forName("oracle.jdbc.driver.OracleDriver");
			//establish the connection
			con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","manager");
			//create STatement object
			if(con!=null)
				st=con.createStatement();
			//prepare SQL Query
			    //UPDATE STUDENT SET SNAME='rajesh khanna',SADD='mumbai' WHERE SNO=189
			query="UPDATE STUDENT SET SNAME="+newName+",SADD="+newAddrs+" WHERE SNO="+no;
			System.out.println(query);
			//send and execute Query
			if(st!=null)
				count=st.executeUpdate(query);
			//process the Result
			if(count==0)
				System.out.println("Record not found for updation");
			else
				System.out.println("Record found and updated");
		}//try
		catch(SQLException se) {
		    if(se.getErrorCode()==12899)
		    	System.out.println("Value is too large column");
		    else if(se.getErrorCode()==933)
		    	System.out.println("Invalid SQL Command/Query");
		    else
		    	System.out.println("Unknown DB problem");
		}
		catch(ClassNotFoundException cnf) {
			cnf.printStackTrace();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		finally {
		//close jdbc objs
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
