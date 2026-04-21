/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.westminster.campus.filters;

import javax.ws.rs.container.*;
import javax.ws.rs.ext.Provider;
import java.util.logging.Logger;

@Provider
public class TrafficFilter implements ContainerRequestFilter, ContainerResponseFilter {
    private static final Logger LOG = Logger.getLogger(TrafficFilter.class.getName());

    @Override
    public void filter(ContainerRequestContext req) {
        LOG.info("Request: " + req.getMethod() + " " + req.getUriInfo().getPath());
    }

    @Override
    public void filter(ContainerRequestContext req, ContainerResponseContext res) {
        LOG.info("Response Status: " + res.getStatus());
    }
}