package com.codurance;

import org.eclipse.jetty.http.HttpStatus;
import spark.Route;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;

import static spark.Spark.get;

public class SimpleRestApplication {
    public static void main(String[] args) {
        get("/hello", respondWith("Hello I'm running on : \n" + getIp()));
        get("/healthcheck", respondWith("OK"));
    }

    private static Route respondWith(Object responseBody){
        return (request, response) -> {
            response.type("text/plain");
            response.status(HttpStatus.OK_200);
            return responseBody;
        };
    }
    
       private static String getIp() throws SocketException {
        Enumeration e = NetworkInterface.getNetworkInterfaces();
        StringBuilder sb = new StringBuilder();
        while(e.hasMoreElements())
        {
            NetworkInterface n = (NetworkInterface) e.nextElement();
            Enumeration ee = n.getInetAddresses();
            while (ee.hasMoreElements())
            {
                InetAddress i = (InetAddress) ee.nextElement();
                sb.append(i.getHostAddress());
            }
        }
        return sb.toString();
    }
}
