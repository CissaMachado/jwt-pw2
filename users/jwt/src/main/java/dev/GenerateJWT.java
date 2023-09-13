package dev;

import java.util.Arrays;
import java.util.HashSet;

import jakarta.annotation.security.PermitAll;
import jakarta.annotation.security.PermitAll;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.FormParam;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

import org.eclipse.microprofile.jwt.Claims;

import io.smallrye.jwt.build.Jwt;

@Path("/jwt")
public class GenerateJWT {

    @GET
    @PermitAll
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.TEXT_PLAIN)
    public String getJWT() {

        return Jwt.issuer("https://localhost:8443")
                .upn("cissamachado@rpmhub.dev")
                .groups(new HashSet<>(Arrays.asList("User", "Admin")))
                .claim(Claims.full_name, "Cecília dos Santos Machado")
                .claim(Claims.email, "cissa@rpmhub.dev")
                .innerSign()
                .encrypt();
    }

}
