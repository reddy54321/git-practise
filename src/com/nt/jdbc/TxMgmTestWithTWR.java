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



public class TxMgmTestWithTWR {

	public static void main(String[] args) {
		int srcNo=0,destNo=0;
		float amt=0.0f;
		int result[]=null;
		boolean  flag=false;
		
			//read  inputs
	try(Scanner sc=new Scanner(System.in)){
			if(sc!=null) {
				System.out.println("Enter Source Account number::");
				srcNo=sc.nextInt();
				System.out.println("Enter Dest Account number::");
				destNo=sc.nextInt();
				System.out.println("Enter  Amount to transfer::");
				amt=sc.nextFloat();
			}
	}
	catch(Exception e) {
		e.printStackTrace();
	}
	 try {
			//register JDBC driver s/w
			Class.forName("oracle.jdbc.driver.OracleDriver");
	 }
	 catch(ClassNotFoundException cnf) {
		 cnf.printStackTrace();
	 }
	 
			//establish the connection
		try(Connection 	con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","manager")){
			if(con!=null)
			try(Statement	st=con.createStatement()){
			//Begin Transaction
			   if(st!=null)
				   con.setAutoCommit(false);
			//add SQL Queries to the batch
			   if(st!=null) {
			   //for withdraw opeartion
			    st.addBatch("UPDATE ACCOUNT SET BALANCE=BALANCE-"+amt+" WHERE ACNO="+srcNo);
			    //for deposite operation
			    st.addBatch("UPDATE ACCOUNT SET BALANCE=BALANCE+"+amt+" WHERE ACNO="+destNo);
			   }
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
		}//try
		catch(SQLException se) {
			se.printStackTrace();
		}
		
	}//main
}//class
