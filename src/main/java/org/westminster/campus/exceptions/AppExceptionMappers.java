/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.westminster.campus.exceptions;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

public class AppExceptionMappers {

    @Provider
    public static class RoomNotEmptyMapper implements ExceptionMapper<IllegalStateException> {
        @Override
        public Response toResponse(IllegalStateException e) {
            return Response.status(409).entity(new ErrorMessage(e.getMessage(), 409)).type(MediaType.APPLICATION_JSON).build();
        }
    }

    @Provider
    public static class GlobalMapper implements ExceptionMapper<Throwable> {
        @Override
        public Response toResponse(Throwable t) {
            return Response.status(500).entity(new ErrorMessage("Internal Server Error", 500)).type(MediaType.APPLICATION_JSON).build();
        }
    }
}