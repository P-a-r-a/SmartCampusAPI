/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.westminster.campus.resources;

import org.westminster.campus.models.Room;
import javax.ws.rs.*;
import javax.ws.rs.core.*;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

@Path("/rooms")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class RoomResource {
    public static Map<String, Room> roomStore = new ConcurrentHashMap<>();

    @GET
    public List<Room> getRooms() { return new ArrayList<>(roomStore.values()); }

    @POST
    public Response createRoom(Room room) {
        roomStore.put(room.getId(), room);
        return Response.status(201).entity(room).build();
    }

    @DELETE
    @Path("/{id}")
    public Response deleteRoom(@PathParam("id") String id) {
        Room r = roomStore.get(id);

        if (r == null) {
            return Response.status(404).build();
        }

        // Logic for Part 2.2: Block deletion if sensors exist
        if (!r.getSensorIds().isEmpty()) {
            throw new org.westminster.campus.exceptions.RoomNotEmptyException(
                "Cannot delete room: " + id + " because it contains active sensors."
            );
        }

        roomStore.remove(id);
        return Response.noContent().build();
    }
}