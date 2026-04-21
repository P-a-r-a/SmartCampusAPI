/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.westminster.campus.resources;

import org.westminster.campus.models.Sensor;
import javax.ws.rs.*;
import javax.ws.rs.core.*;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

@Path("/sensors")
@Produces(MediaType.APPLICATION_JSON)
public class SensorResource {
    public static Map<String, Sensor> sensorStore = new ConcurrentHashMap<>();

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addSensor(Sensor s) {
        if (!RoomResource.roomStore.containsKey(s.getRoomId())) {
            return Response.status(422).entity("Room does not exist").build();
        }
        sensorStore.put(s.getId(), s);
        RoomResource.roomStore.get(s.getRoomId()).getSensorIds().add(s.getId());
        return Response.status(201).entity(s).build();
    }

    @Path("/{sensorId}/readings")
    public SensorReadingResource getReadings(@PathParam("sensorId") String sid) {
        return new SensorReadingResource(sid);
    }
}