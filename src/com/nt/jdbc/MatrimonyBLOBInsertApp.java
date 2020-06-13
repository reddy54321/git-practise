package com.nt.jdbc;

//SQL> create table JodiMakers_Matrimony(id number(5) primary key,name varchar2(10),age number(3),gender varchar(6),photo blob);
//SQL> create sequence  Matrimony_Id start with 1 increment by 1;
import java.io.File;
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class MatrimonyBLOBInsertApp {
	private static final String  INSERT_MATRIMONY="INSERT INTO JODIMAKERS_MATRIMONY VALUES(MATRIMONY_ID.NEXTVAL,?,?,?,?)";

	public static void main(String[] args) {
		Scanner sc=null;
		String name=null;
		int age=0;
		String gender=null;
		String photoPath=null;
		File file=null;
		long length=0;
		FileInputStream fis=null;
		Connection con=null;
		PreparedStatement ps=null;
		int result=0;
		try {
			//read inputs
			sc=new Scanner(System.in);
			if(sc!=null) {
				System.out.println("Enter Applicant name::");
				name=sc.nextLine();
				System.out.println("Enter Applicant age::");
				age=sc.nextInt();
				System.out.println("Enter Applicant gender::");
				gender=sc.next();
				System.out.println("Enter Applicant Photo Location::");
				sc.nextLine();
				photoPath=sc.nextLine();
			}//if
			// Locate photo file
			 file=new File(photoPath);
			 //get length of the file
			 length=file.length();
			 //create InputStream holding PhotoLocation
			 fis=new FileInputStream(file);
			 //register JDBC driver s/w
			 Class.forName("oracle.jdbc.driver.OracleDriver");
			 //establish the connection
			 con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","manager");
			//create PreparedStatement object
			 if(con!=null)
				 ps=con.prepareStatement(INSERT_MATRIMONY);
			 //set values to Query params
			 if(ps!=null) {
				 ps.setString(1,name);
				 ps.setInt(2,age);
				 ps.setString(3,gender);
				 //ps.setBinaryStream(4,fis,length);
				 ps.setBlob(4,fis);
			 }
			 //execute the Query
			 if(ps!=null)
				 result=ps.executeUpdate();
			 //process the result
			 if(result==0)
				 System.out.println("Record not inserted");
			 else
				 System.out.println("Record inserted");
		}//try
		catch(SQLException se) {
			se.printStackTrace();
		}
		catch(ClassNotFoundException cnf){
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
