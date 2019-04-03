package com.webservice;

import javax.jws.WebService;
import javax.xml.ws.Endpoint;

@WebService
public class HelloWorld {
    public String HelloWord(String name){
        return"Hello: "+name;
    }
    public static void main(String[] args) {
        Endpoint.publish("http://localhost:1080/helloWord",new HelloWorld());

    }
}
