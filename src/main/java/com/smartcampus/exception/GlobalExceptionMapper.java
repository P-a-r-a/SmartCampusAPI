/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.smartcampus.exception;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;
import java.util.HashMap;
import java.util.Map;

@Provider
public class GlobalExceptionMapper implements ExceptionMapper<Throwable> {
    @Override
    public Response toResponse(Throwable t) {
        int status = 500;
        String message = "A system error occurred.";

        if (t instanceof RoomNotEmptyException) {
            status = 409;
            message = t.getMessage();
        } else if (t instanceof LinkedResourceNotFoundException) {
            status = 422;
            message = t.getMessage();
        } else if (t instanceof SensorUnavailableException) {
            status = 403;
            message = t.getMessage();
        } else if (t instanceof javax.ws.rs.WebApplicationException) {
            status = ((javax.ws.rs.WebApplicationException) t).getResponse().getStatus();
            message = t.getMessage();
        }

        Map<String, String> errorBody = new HashMap<>();
        errorBody.put("status_code", String.valueOf(status));
        errorBody.put("message", message);

        return Response.status(status)
                .entity(errorBody)
                .type(MediaType.APPLICATION_JSON)
                .build();
    }
}
