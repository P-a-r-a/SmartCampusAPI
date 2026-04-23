/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.smartcampus.resource;

import com.smartcampus.model.*;
import com.smartcampus.repository.MockDatabase;
import com.smartcampus.exception.SensorUnavailableException;
import javax.ws.rs.*;
import javax.ws.rs.core.*;
import java.util.*;

public class SensorReadingResource {
    private final String sensorId;
    private final MockDatabase db = MockDatabase.getInstance();

    public SensorReadingResource(String sensorId) { 
        this.sensorId = sensorId; 
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<SensorReading> getReadingHistory() {
        return db.getReadings().getOrDefault(sensorId, new ArrayList<>());
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response postReading(SensorReading reading) {
        Sensor s = db.getSensors().get(sensorId);
        if (s == null) throw new NotFoundException("Sensor not found");
        
        // State Constraint: Part 5.3
        if ("MAINTENANCE".equalsIgnoreCase(s.getStatus())) {
            throw new SensorUnavailableException("Sensor is disconnected for maintenance.");
        }

        // Side Effect Logic: Part 4.2
        s.setCurrentValue(reading.getValue());
        db.getReadings().computeIfAbsent(sensorId, k -> new ArrayList<>()).add(reading);
        
        return Response.status(Response.Status.CREATED).entity(reading).build();
    }
}
