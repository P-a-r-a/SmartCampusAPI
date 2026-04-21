/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.westminster.campus;

import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;
import java.net.URI;

public class Main {
    public static void main(String[] args) {
        ResourceConfig rc = new ResourceConfig().packages("org.westminster.campus");
        GrizzlyHttpServerFactory.createHttpServer(URI.create("http://localhost:8080/"), rc);
        System.out.println("Server started at http://localhost:8080/api/v1");
    }
}