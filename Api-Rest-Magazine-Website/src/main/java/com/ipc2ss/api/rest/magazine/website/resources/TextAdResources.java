/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ipc2ss.api.rest.magazine.website.resources;

import backend.AuthTokenHandler;
import backend.controllers.BuyAdController;
import backend.controllers.TextAdController;
import backend.exception.AccessException;
import backend.exception.InvalidDataException;
import backend.exception.ServerException;
import backend.model.dto.Amount;
import backend.model.dto.TextAd;
import jakarta.ws.rs.Consumes;
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
@Path("text-ad")
public class TextAdResources {

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getById(
            @HeaderParam("Authorization") String authorization,
            @PathParam("id") int id
    ) {
        System.out.println("en obtener anuncio de texto por id");
        AuthTokenHandler authTokenHandler = new AuthTokenHandler();
        TextAdController textAdController = new TextAdController();
        try {
            authTokenHandler.authToken(authorization);
            TextAd ad = textAdController.getTextAdById(authTokenHandler.getCredential(), id);
            return Response.ok(ad).build();
        } catch (AccessException e) {
            e.printStackTrace();
            return Response.status(Response.Status.UNAUTHORIZED).build();
        } catch (ServerException e) {
            e.printStackTrace();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        } catch (InvalidDataException e) {
            e.printStackTrace();
            return Response.status(Response.Status.FORBIDDEN).build();
        }
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response buy(
            @HeaderParam("Authorization") String authorization,
            TextAd textAd) {
        System.out.println("en comprar auncio");
        AuthTokenHandler tokenHandler = new AuthTokenHandler();
        BuyAdController buyAdController = new BuyAdController();
        Amount amount = new Amount();
        try {
            tokenHandler.authToken(authorization);
            amount.setAmount(buyAdController.buyTextAd(textAd, tokenHandler.getCredential()));
            return Response.ok(amount).build();
        } catch (AccessException e) {
            e.printStackTrace();
            return Response.status(Response.Status.UNAUTHORIZED).build();
        } catch (ServerException e) {
            e.printStackTrace();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        } catch (InvalidDataException e) {
            e.printStackTrace();
            return Response.status(Response.Status.FORBIDDEN).build();
        }
    }

    @POST
    @Path("{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response update(
            @HeaderParam("Authorization") String authorization,
            @PathParam("id") int id,
            TextAd ad) {
        System.out.println("en actualizar iformaciond del anuncio");
        AuthTokenHandler authTokenHandler = new AuthTokenHandler();
        TextAdController textAdController = new TextAdController();
        try {
            authTokenHandler.authToken(authorization);
            ad.setId(id);
            textAdController.updateAd(ad);
            return Response.status(Response.Status.ACCEPTED).build();
        } catch (AccessException e) {
            e.printStackTrace();
            return Response.status(Response.Status.UNAUTHORIZED).build();
        } catch (ServerException e) {
            e.printStackTrace();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        } catch (InvalidDataException e) {
            e.printStackTrace();
            return Response.status(Response.Status.FORBIDDEN).build();
        }
    }
}
