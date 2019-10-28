package com.service; 
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.namespace.QName;  
import javax.xml.ws.Service;

import com.beans.Contact;
import com.service.MyService;  

@WebServlet("/webPage/proxy")
public class Proxy extends HttpServlet{  

	
    /**
	 * 
	 */
	private static final long serialVersionUID = -8212186842177425113L;
	
	//example of doGet
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html");//setting the content type  
		PrintWriter pw=resp.getWriter();//get the stream to write the data  
		   
		pw.println("<html><body>");  
		pw.println("Hello! You have accessed a proxy, I do not do anything! Here, proof: <br><button>Do nothing</button>");  
		pw.println("</body></html>");  
		  
		pw.close();
	}
	
	//Function that accepts incoming post requests to this servlet
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//Obtain the input parameter from the post request
		String param = request.getParameter("input");
		
		//"log" the received parameter
		System.out.println("Parameter received: " + param);
		
		//Data validation for the post request parameter
		//First check if it is null or empty
		if(param == null || param == "") {
			param = "null"; 
		//If not null then check if it is a number or not.
		}else if(!isNumber(param)) {
			response.getWriter().write("Error: Parameter is not a number!");
			return;
		}

		
		//Log the next step
		System.out.println("Parameter sent to server: " + param);
		//If the parameter is valid then the call to the SOAP service is performed
		try {
			Contact[] contacts = callX(param);
			String res = "";
			//The result will be returned as a string;
			for(int index = 0; index < contacts.length; index++) {
				res += contacts[index].getIDX() + " " 
						+ contacts[index].getTimeStamp() + " " 
						+ contacts[index].getFirstName() + " "
						+ contacts[index].getLastName() + " "
						+ contacts[index].geteMail() + " " 
						+ contacts[index].getPhoneNumber() + "\n";
			}
			//Send the result back to caller
			response.getWriter().write(res);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	//Function that checks if a given String object can be converted to a number
	//input: a String object
	//output: a boolean value, true if the String is convertible and false if not.
	private boolean isNumber(String input) {
	    try {
	        Integer.parseInt(input);
	    } catch (NumberFormatException | NullPointerException nfe) {
	        return false;
	    }
	    return true;
	}
	
	//Function that forwards a parameter to the SOAP web service
	//Input: the parameter to be forwarded
	//Output: an array of Contact objects representing the server answer
	private Contact[] callX(String param) throws Exception {
    	
    	URL url = new URL("http://localhost:8088/ws/Service?wsdl");  
   
        //1st argument service URI
    	//2nd argument is service name + Service appended 
        QName qname = new QName("http://service.com/", "MyServiceImplService");  
        Service service = Service.create(url, qname);  
        MyService myService = service.getPort(MyService.class);
        Contact[] resContacts = myService.getX(param);

        return resContacts;  
     }  
 } 
