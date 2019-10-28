package com.service;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.jws.WebParam;
import javax.jws.WebService;

import com.beans.Contact;

@WebService(endpointInterface = "com.service.MyService")
public class MyServiceImpl implements MyService {
	
	//Function used to invoke the GetX stored procedure in database test, with user "root" and password "pass"
	//Input: a parameter representing the IDX used in the GetX procedure
	//Output: an array of Contact objects used to represent database entries
	@Override
	public Contact[] getX(@WebParam(name="param") String param) {
		try{  
			//connect to the database
			Class.forName("com.mysql.cj.jdbc.Driver");  
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/test","root","pass"); 
			
			//can and should be replaced with a log
			System.out.println("Received parameter: " + param);
			
			int arrLength = 0;
			//create a statement to get the number of entries in the DB first, to create the array
			Statement stmt=con.createStatement();
			ResultSet rs=stmt.executeQuery("call GetX(" + param + ");");  
			while(rs.next()) {
				arrLength++;
			}
			Contact[] finalResult = new Contact[arrLength];
			
			//create the call to GetX a second time to get the actual entries
			stmt = con.createStatement();
			rs = stmt.executeQuery("call GetX(" + param + ");");
			int index = 0;
			while(rs.next()) {
				Contact contact = new Contact(rs.getInt(1), rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6));
				finalResult[index] = contact;
				index++;
			}
			//close DB connection
			con.close();  
			return finalResult;
		}catch(Exception e){ 
			System.out.println(e);
			return null;
		}  
	}

}
