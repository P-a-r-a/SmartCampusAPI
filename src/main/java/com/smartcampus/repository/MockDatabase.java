/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.smartcampus.repository;

import com.smartcampus.model.*;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class MockDatabase {
    private static final MockDatabase instance = new MockDatabase();
    
    private final Map<String, Room> rooms = new ConcurrentHashMap<>();
    private final Map<String, Sensor> sensors = new ConcurrentHashMap<>();
    private final Map<String, List<SensorReading>> readings = new ConcurrentHashMap<>();

    private MockDatabase() {
        // Seed initial data to verify system on startup
        Room defaultRoom = new Room("LIB-301", "Library Quiet Study", 50);
        rooms.put(defaultRoom.getId(), defaultRoom);
    }

    public static MockDatabase getInstance() { 
        return instance; 
    }
    
    public Map<String, Room> getRooms() { return rooms; }
    public Map<String, Sensor> getSensors() { return sensors; }
    public Map<String, List<SensorReading>> getReadings() { return readings; }
}