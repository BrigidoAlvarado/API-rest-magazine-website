/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ipc2ss.api.rest.magazine.website.resources;

import backend.AuthTokenHandler;
import backend.DBconnection.GlobalDBConnection;
import backend.controllers.GlobalCostController;
import backend.enums.Global;
import backend.exception.AccessException;
import backend.exception.InvalidDataException;
import backend.exception.ServerException;
import backend.model.dto.Amount;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.HeaderParam;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

/**
 *
 * @author brigidoalvarado
 */
@Path("global-cost")
public class GlobalCostResource {

    private static final String AUTHORIZATION = "Authorization";

    @GET
    @Path("{kind}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getSolicitudPorCodigo(@PathParam("kind") String kind) {
        GlobalDBConnection globalCostDBConnection = new GlobalDBConnection();
        Amount amount = new Amount();
        try {
            Global globalCost = Global.valueOf(kind);
            amount.setAmount(globalCostDBConnection.getCost(globalCost));
            return Response.ok(amount).build();
        } catch (IllegalArgumentException e) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        } catch (ServerException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }

    @POST
    @Path("{kind}")
    public Response updateCost(
            @HeaderParam(AUTHORIZATION) String authorization,
            @PathParam("kind") Global kind,
            int amount) {
        AuthTokenHandler auth = new AuthTokenHandler();
        GlobalCostController controller = new GlobalCostController();
        try {
            auth.authToken(authorization);
            controller.updateCost(kind, amount);
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
