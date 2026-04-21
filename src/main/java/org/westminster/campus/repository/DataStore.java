/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.westminster.campus.repository;

import org.westminster.campus.models.*;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class DataStore {
    public static Map<String, Room> rooms = new ConcurrentHashMap<>();
    public static Map<String, Sensor> sensors = new ConcurrentHashMap<>();
    public static Map<String, List<SensorReading>> readings = new ConcurrentHashMap<>();

    static {
        // Sample data for testing
        rooms.put("LIB-301", new Room("LIB-301", "Library Quiet Study", 50));
    }
}