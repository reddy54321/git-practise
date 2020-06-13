package com.nt.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

/*SQL> truncate table  ticket_reservation;
Table truncated.
SQL> create sequence ticket_seq start with 1 increment by 1;
Sequence created.
SQL> create sequence seat_seq start with 1 increment by 1;
Sequence created.
SQL> desc ticket_reservation;
 Name                                      Null?    Type
 ----------------------------------------- -------- ----------------------------
 TICKETID                                  NOT NULL NUMBER(5)
 PSNGRNAME                                          VARCHAR2(20)
 GENDER                                             VARCHAR2(7)
 SEATNO                                             NUMBER(5)
 BOARDINGPLACE                                      VARCHAR2(20)
 DESTPLACE                                          VARCHAR2(20)   */

public class PsBatch_GroupTicketReservation {
   private static final String  TICKET_RESERVATION="INSERT INTO  TICKET_RESERVATION VALUES(TICKET_SEQ.NEXTVAL,?,?,SEAT_SEQ.NEXTVAL,?,?)";
	public static void main(String[] args) {
		Scanner sc=null;
		String srcPlace=null,destPlace=null;
		Connection con=null;
		PreparedStatement ps=null;
		int groupSize=0;
		String  psngrName=null,psngrGender=null;
		int result[]=null;
		try {
			//read inputs
			sc=new Scanner(System.in);
			if(sc!=null) {
				System.out.println("Enter Source place::");
				srcPlace=sc.next();
				System.out.println("Enter Dest place::");
				destPlace=sc.next();
				System.out.println("Enter group size::");
				groupSize=sc.nextInt();
			}
			//register Driver 
			Class.forName("oracle.jdbc.driver.OracleDriver");
			//establish the connection
			con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","manager");
			//create PreparedStatement object
			if(con!=null)
				ps=con.prepareStatement(TICKET_RESERVATION);
			//read group Passengers details
			for(int i=1;i<=groupSize;++i) {
				System.out.println("Enter "+i+" passgener name::");
				psngrName=sc.next();
				System.out.println("Enter "+i+"  passenger Gender::");
				psngrGender=sc.next();
				//add muliple sets of query param values to the batch
				ps.setString(1, psngrName);
				ps.setString(2,psngrGender);
				ps.setString(3,srcPlace);
				ps.setString(4,destPlace);
				ps.addBatch();
			}//for
			//execute the Query
			if(ps!=null) {
				result=ps.executeBatch();
			}
			//process the result
			if(result!=null)
				 System.out.println("Group reservation is done");
			else
				 System.out.println("Group reservation is not done");
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
	}//main
}//class
