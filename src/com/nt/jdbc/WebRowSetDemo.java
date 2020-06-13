package com.nt.jdbc;

import java.io.FileWriter;
import java.io.Writer;
import java.sql.SQLException;

import javax.sql.rowset.WebRowSet;

import oracle.jdbc.rowset.OracleWebRowSet;

public class WebRowSetDemo {

	public static void main(String[] args) {
		WebRowSet wrowset=null;
		Writer writer=null;
		try {
			wrowset=new OracleWebRowSet();
			wrowset.setUsername("system");
			wrowset.setPassword("manager");
			wrowset.setUrl("jdbc:oracle:thin:@localhost:1521:xe");
			wrowset.setCommand("SELECT * FROM STUDENT");
			wrowset.execute();
			while(wrowset.next()) {
				System.out.println(wrowset.getInt(1)+"  "+wrowset.getString(2)+"  "+wrowset.getString(3));
			}
			writer=new FileWriter("E:/Student.xml");
			wrowset.writeXml(writer);
			writer.flush();
			writer.close();
			System.out.println("Writes to the Xml file...");
				
		}//try
		catch(SQLException se) {
			se.printStackTrace();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
		
	}//main
}//class
