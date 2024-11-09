/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ipc2ss.api.rest.magazine.website.resources;

import backend.AuthTokenHandler;
import backend.DBconnection.EditorDBConnection;
import backend.controllers.EditorReportsController;
import backend.enums.Global;
import backend.exception.AccessException;
import backend.exception.InvalidDataException;
import backend.exception.ServerException;
import backend.model.dto.Filter;
import backend.model.dto.LockAd;
import backend.model.dto.Magazine;
import jakarta.ws.rs.Consumes;
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
@Path("editor-reports")
public class EditorReportResource {

    private final AuthTokenHandler auth = new AuthTokenHandler();
    private final EditorReportsController controller = new EditorReportsController();

    @POST
    @Path("payment")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response getPaymentReport(
            @HeaderParam(Global.AUTHORIZATION) String authorization,
            Filter filter) {
        try {
            auth.authToken(authorization);
            List<LockAd> lockAds = controller.getPaymentReport(filter, auth.getCredential().getUserName());
            return Response.ok(lockAds).build();
        } catch (ServerException e) {
            e.printStackTrace();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        } catch (AccessException e) {
            e.printStackTrace();
            return Response.status(Response.Status.UNAUTHORIZED).build();
        } catch (InvalidDataException e) {
            e.printStackTrace();
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
    }

    @POST
    @Path("favorite")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getFavoriteMagazineReport(
            @HeaderParam(Global.AUTHORIZATION) String authorization,
            Filter filter) {
        EditorDBConnection dBConnection = new EditorDBConnection();
        try {
            auth.authToken(authorization);
            List<Magazine> magazines = dBConnection.getFavoriteMagazines(filter, auth.getCredential().getUserName());
            return Response.ok(magazines).build();
        } catch (ServerException e) {
            e.printStackTrace();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        } catch (AccessException e) {
            e.printStackTrace();
            return Response.status(Response.Status.UNAUTHORIZED).build();
        }
    }

    @POST
    @Path("subscription")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response getMagazinesSubscription(
            @HeaderParam(Global.AUTHORIZATION) String authorization,
            Filter filter) {
        try {
            auth.authToken(authorization);
            List<Magazine> magazineList = controller.getSubscriptionMagazines(filter, auth.getCredential().getUserName());
            return Response.ok(magazineList).build();
        } catch (ServerException e) {
            e.printStackTrace();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        } catch (AccessException e) {
            e.printStackTrace();
            return Response.status(Response.Status.UNAUTHORIZED).build();
        } catch (InvalidDataException e) {
            e.printStackTrace();
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
    }
}
