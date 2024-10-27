/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ipc2ss.api.rest.magazine.website.resources;

import backend.DBconnection.GlobalDBConnection;
import backend.enums.GlobalCost;
import backend.exception.ServerException;
import backend.model.dto.Amount;
import jakarta.ws.rs.GET;
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

    @GET
    @Path("{kind}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getSolicitudPorCodigo(@PathParam("kind") String kind) {
        GlobalDBConnection globalCostDBConnection = new GlobalDBConnection();
        Amount amount = new Amount();
        try {
            GlobalCost globalCost = GlobalCost.valueOf(kind);
             amount.setAmount(globalCostDBConnection.getCost(globalCost));
             return Response.ok(amount).build();
        } catch (IllegalArgumentException e) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        } catch(ServerException e){
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }
}
