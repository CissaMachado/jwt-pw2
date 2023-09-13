package dev;

import java.util.logging.Level;
import java.util.logging.Logger;

import jakarta.annotation.security.RolesAllowed;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

import org.eclipse.microprofile.jwt.Claim;
import org.eclipse.microprofile.jwt.Claims;
import org.eclipse.microprofile.rest.client.inject.RestClient;

@Path("/first")
public class First {

    private static final Logger LOGGER = Logger.getLogger(First.class.getName());

    /* Recupera as informações do token */
    @Inject
    @Claim(standard = Claims.full_name)
    String fullName;

    /* Rest client */
    @Inject
    @RestClient
    Second backend;

    @GET
    @Path("/sum/{a}/{b}")
    @Produces(MediaType.TEXT_PLAIN)
    @RolesAllowed({ "Admin" })
    public int sum(@PathParam("a") int a, @PathParam("b") int b) {
        LOGGER.log(Level.INFO, "First (fullname): {0}", fullName);
        return backend.getSum(a, b);
    }

}
