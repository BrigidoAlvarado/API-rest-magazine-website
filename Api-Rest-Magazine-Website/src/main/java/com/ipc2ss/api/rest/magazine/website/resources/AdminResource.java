/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ipc2ss.api.rest.magazine.website.resources;

import backend.AuthTokenHandler;
import backend.controllers.AdminController;
import backend.exception.AccessException;
import backend.exception.InvalidDataException;
import backend.exception.ServerException;
import backend.model.dto.Magazine;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.HeaderParam;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.List;

/**
 *
 * @author brigidoalvarado
 */
@Path("admin")
public class AdminResource {

    private final AdminController adminController = new AdminController();
    private final AuthTokenHandler authTokenHandler = new AuthTokenHandler();
    private static final String AUTHORIZATION = "Authorization";

    @GET
    @Path("magazine")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getMagazineList(
            @HeaderParam(AUTHORIZATION) String authorization) {
        try {
            authTokenHandler.authToken(authorization);
            List<Magazine> magazineList = adminController.getMagazineList();
            return Response.ok(magazineList).build();
        } catch (ServerException e) {
            e.printStackTrace();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        } catch (AccessException e) {
            e.printStackTrace();
            return Response.status(Response.Status.UNAUTHORIZED).build();
        }
    }

    @POST
    @Path("cost")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateMagazineDailyCost(
            @HeaderParam(AUTHORIZATION) String authorization,
            Magazine magazine) {
        try {
            authTokenHandler.authToken(authorization);
            adminController.updateDailyCost(magazine);
            return Response.status(Response.Status.ACCEPTED).build();
        } catch (ServerException e) {
            e.printStackTrace();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        } catch (AccessException e) {
            e.printStackTrace();
            return Response.status(Response.Status.UNAUTHORIZED).build();
        } catch (InvalidDataException e) {
            e.printStackTrace();
            return Response.status(Response.Status.FORBIDDEN).build();
        }
    }
}
