/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.westminster.campus.resources;

import javax.ws.rs.*;
import javax.ws.rs.core.*;
import java.util.*;

@Path("/")
@Produces(MediaType.APPLICATION_JSON)
public class DiscoveryResource {
    @GET
    public Response getDiscovery() {
        Map<String, Object> discovery = new HashMap<>();
        discovery.put("version", "1.0.0");
        discovery.put("contact", "admin@westminster.ac.uk");
        discovery.put("resources", Map.of(
            "rooms", "/api/v1/rooms",
            "sensors", "/api/v1/sensors"
        ));
        return Response.ok(discovery).build();
    }
}