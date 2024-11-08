/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ipc2ss.api.rest.magazine.website.resources;

import backend.AuthTokenHandler;
import backend.DBconnection.SubscriberDBConnection;
import backend.controllers.SubscriberController;
import backend.exception.AccessException;
import backend.exception.InvalidDataException;
import backend.exception.ServerException;
import backend.model.dto.Magazine;
import backend.transactions.SubscriberTransaction;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.HeaderParam;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.time.LocalDate;
import java.util.List;

/**
 *
 * @author brigidoalvarado
 */
@Path("subscriber")
public class SubscriberResources {

    private final AuthTokenHandler auth = new AuthTokenHandler();
    private final SubscriberController controller = new SubscriberController();

    @GET
    @Path("explorer/{categoryFilter}/{tagFilter}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getMagazines(
            @HeaderParam("Authorization") String authorization,
            @PathParam("categoryFilter") String categoryFilter,
            @PathParam("tagFilter") String tagFilter) {
        try {
            auth.authToken(authorization);
            List<Magazine> magazines = controller.getMagazinesExplorer(tagFilter, categoryFilter, auth.getCredential().getUserName());
            return Response.ok(magazines).build();
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

    @GET
    @Path("magazine")
    public Response getSubscribedMagazineList(
            @HeaderParam("Authorization") String authorization) {
        try {
            auth.authToken(authorization);
            List<Magazine> list = controller.getSubscribedMagazines(auth.getCredential().getUserName());
            return Response.ok(list).build();
        } catch (ServerException e) {
            e.printStackTrace();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        } catch (AccessException e) {
            e.printStackTrace();
            return Response.status(Response.Status.UNAUTHORIZED).build();
        }
    }

    @GET
    @Path("magazine/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getSubscribedMagazine(
            @HeaderParam("Authorization") String authorization,
            @PathParam("id") int id) {
        try {
            auth.authToken(authorization);
            Magazine magazine = controller.getSubscribedMagazine(id, auth.getCredential().getUserName());
            return Response.ok(magazine).build();
        } catch (ServerException e) {
            e.printStackTrace();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        } catch (AccessException e) {
            e.printStackTrace();
            return Response.status(Response.Status.UNAUTHORIZED).build();
        }
    }

    @POST
    @Path("subscribe")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response suscribe(
            @HeaderParam("Authorization") String authorization,
            Magazine magazine) {
        try {
            auth.authToken(authorization);
            controller.subscribe(magazine, auth.getCredential().getUserName());
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

    @POST
    @Path("comment/{id}/{date}")
    public Response commentMagazine(
            @HeaderParam("Authorization") String authorization,
            @PathParam("id") int id,
            @PathParam("date") String date,
            String comment) {
        try {
            System.out.println("en comment intentando :(");
            auth.authToken(authorization);
            controller.commentMagazine(id, comment, auth.getCredential().getUserName(), LocalDate.parse(date));
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

    @POST
    @Path("like")
    public Response doLike(
            @HeaderParam("Authorization") String authorization,
            int id) {
        SubscriberTransaction subscriberTransaction = new SubscriberTransaction();
        try {
            auth.authToken(authorization);
            subscriberTransaction.likeMagazine(id, auth.getCredential().getUserName());
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
