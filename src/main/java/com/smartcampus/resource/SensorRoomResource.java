/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.smartcampus.resource;

import com.smartcampus.model.Room;
import com.smartcampus.repository.MockDatabase;
import com.smartcampus.exception.RoomNotEmptyException;
import javax.ws.rs.*;
import javax.ws.rs.core.*;
import java.util.*;

@Path("/rooms")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class SensorRoomResource {
    private final MockDatabase db = MockDatabase.getInstance();

    @GET
    public List<Room> getAllRooms() {
        return new ArrayList<>(db.getRooms().values());
    }

    @POST
    public Response createRoom(Room room) {
        db.getRooms().put(room.getId(), room);
        return Response.status(Response.Status.CREATED).entity(room).build();
    }

    @GET
    @Path("/{roomId}")
    public Room getRoom(@PathParam("roomId") String roomId) {
        Room room = db.getRooms().get(roomId);
        if (room == null) throw new NotFoundException("Room not found");
        return room;
    }

    @DELETE
    @Path("/{roomId}")
    public Response deleteRoom(@PathParam("roomId") String roomId) {
        Room room = db.getRooms().get(roomId);
        if (room == null) return Response.status(Response.Status.NOT_FOUND).build();
        
        // Safety constraint: Part 2.2
        if (!room.getSensorIds().isEmpty()) {
            throw new RoomNotEmptyException("Room holds active sensors. Deletion blocked.");
        }
        
        db.getRooms().remove(roomId);
        return Response.noContent().build();
    }
}