package com.service;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.Style;
import com.beans.Contact;

//Service endpoint interface  
@WebService  
@SOAPBinding(style = Style.RPC)  
public interface MyService{  
    @WebMethod Contact[] getX(@WebParam(name="param") String Param);  
}
