package com.nt.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

/*SQL> select * from Account;

ACNO       HOLDER                  BALANCE
---------- --------------------          ----------
 101            raja                            9000
 102            ramesh                     8000

SQL> desc Account;
Name                                      Null?    Type
----------------------------------------- -------- ----------------------------
ACNO                                      NOT NULL NUMBER(6)
HOLDER                                             VARCHAR2(20)
BALANCE                                            NUMBER(10,2) */



public class TxMgmTest {

	public static void main(String[] args) {
		Scanner sc=null;
		int srcNo=0,destNo=0;
		float amt=0.0f;
		Connection con=null;
		Statement st=null;
		int result[]=null;
		boolean flag=false;
		
		try {
			//read  inputs
			sc=new Scanner(System.in);
			if(sc!=null) {
				System.out.println("Enter Source Account number::");
				srcNo=sc.nextInt();
				System.out.println("Enter Dest Account number::");
				destNo=sc.nextInt();
				System.out.println("Enter  Amount to transfer::");
				amt=sc.nextFloat();
			}
			//register JDBC driver s/w
			Class.forName("oracle.jdbc.driver.OracleDriver");
			//establish the connection
			con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","manager");
			//create Statement object
			if(con!=null)
				st=con.createStatement();
			//Begin Transaction
			if(st!=null)
				con.setAutoCommit(false);
			//add SQL Queries to the batch
			   //for withdraw opeartion
			    st.addBatch("UPDATE ACCOUNT SET BALANCE=BALANCE-"+amt+" WHERE ACNO="+srcNo);
			    //for deposite operation
			    st.addBatch("UPDATE ACCOUNT SET BALANCE=BALANCE+"+amt+" WHERE ACNO="+destNo);
			 //execute the batch
			    if(st!=null) {
			    	result=st.executeBatch();
			    }
			    //perform Transaction Management
			    for(int i=0;i<result.length;++i) {
			    	if(result[i]==0) {
			    		flag=true;
			    		break;
			    	}
			    }
			    if(flag==true) {
			    	con.rollback();
			    	System.out.println("Transaction rolledback---> Money not transfered");
			    }
			    else {
			    	con.commit();
			    	System.out.println("Transaction succeded---> Money  transfered");
			    }
		}//try
		catch(SQLException se) {
			try {
				con.rollback();
		    	System.out.println("Transaction rolledback---> Money not  transfered");
			}
			catch(SQLException se1) {
				se1.printStackTrace();
			}
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
