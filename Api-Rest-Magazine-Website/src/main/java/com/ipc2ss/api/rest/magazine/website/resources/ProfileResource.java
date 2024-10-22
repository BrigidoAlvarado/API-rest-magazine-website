/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ipc2ss.api.rest.magazine.website.resources;

import backend.AuthTokenHandler;
import backend.exception.InvalidDataException;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.HeaderParam;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

/**
 *
 * @author brigidoalvarado
 */
@Path("profile")
public class ProfileResource {

    @GET
    //@Produces(MediaType.APPLICATION_JSON)
    public Response getProfile(
            @HeaderParam("Authorization") String authorization) {
        System.out.println("se ha ingresado a profile resource");
        AuthTokenHandler authTokenHandler = new AuthTokenHandler();
        try {
            authTokenHandler.authToken(authorization);
            //EJECUCION DE LA LOGICA
            return Response.ok().build();
        } catch (InvalidDataException e) {
            e.printStackTrace();
            return Response.status(Response.Status.UNAUTHORIZED).build();
        }
    }
}
