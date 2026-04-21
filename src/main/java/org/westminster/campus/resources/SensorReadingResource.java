/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.westminster.campus.resources;

import org.westminster.campus.models.*;
import org.westminster.campus.repository.DataStore;
import javax.ws.rs.*;
import javax.ws.rs.core.*;
import java.util.*;

public class SensorReadingResource {
    private String sensorId;

    public SensorReadingResource(String sensorId) {
        this.sensorId = sensorId;
    }

    @GET
    public List<SensorReading> getHistory() {
        return DataStore.readings.getOrDefault(sensorId, new ArrayList<>());
    }

    @POST
    public Response addReading(SensorReading r) {
        Sensor s = DataStore.sensors.get(sensorId);
        if (s == null) return Response.status(404).build();
        if ("MAINTENANCE".equals(s.getStatus())) return Response.status(403).build();

        s.setCurrentValue(r.getValue());
        DataStore.readings.computeIfAbsent(sensorId, k -> new ArrayList<>()).add(r);
        return Response.status(201).entity(r).build();
    }
}