/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ipc2ss.api.rest.magazine.website.resources;

import backend.AuthTokenHandler;
import backend.controllers.AdminReportsController;
import backend.exception.AccessException;
import backend.exception.ServerException;
import backend.model.dto.EarningsReport;
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
@Path("admin-report")
public class AdminReportsResource {

    private final AuthTokenHandler ath = new AuthTokenHandler();
    private final AdminReportsController controller = new AdminReportsController();
    private static final String AUTHORIZATION = "Authorization";

    @GET
    @Path("earning")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getEarningResport(
            @HeaderParam(AUTHORIZATION) String authorization) {
        try {
            ath.authToken(authorization);
            EarningsReport earningsReport = controller.getEarningReport();
            return Response.ok(earningsReport).build();
        } catch (ServerException e) {
            e.printStackTrace();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        } catch (AccessException e){
            e.printStackTrace();
            return Response.status(Response.Status.UNAUTHORIZED).build();
        } 
    }
}
