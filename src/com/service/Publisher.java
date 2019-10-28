package com.service;
import javax.xml.ws.Endpoint;  

//Endpoint publisher 
public class Publisher{  
    public static void main(String[] args) {  
    	Endpoint.publish("http://localhost:8088/ws/Service", new MyServiceImpl());  
    }  
}