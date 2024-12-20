/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ipc2ss.api.rest.magazine.website.resources;

import backend.AuthTokenHandler;
import backend.DBconnection.AdDBConnection;
import backend.controllers.AdController;
import backend.enums.Global;
import backend.exception.AccessException;
import backend.exception.InvalidDataException;
import backend.exception.ServerException;
import backend.model.dto.Ad;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.HeaderParam;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.List;

/**
 *
 * @author brigidoalvarado
 */
@Path("ad")
public class AdResources {

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAdById(
            @PathParam("id") int id) {
        System.out.println("en obtener informacion de un anuncio por id");
        AdController adController = new AdController();
        try {
            Ad ad = adController.getAdById(id);
            return Response.ok(ad).build();
        } catch (ServerException e) {
            e.printStackTrace();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        } catch (InvalidDataException e) {
            e.printStackTrace();
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAds(
            @HeaderParam("Authorization") String authorization) {
        AuthTokenHandler authTokenHandler = new AuthTokenHandler();
        AdController adController = new AdController();
        try {
            authTokenHandler.authToken(authorization);
            List<Ad> ads = adController.getPurchasedAds(authTokenHandler.getCredential());
            return Response.ok(ads).build();
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
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateStatus(
            @HeaderParam("Authorization") String authorization,
            Ad ad) {
        AuthTokenHandler authTokenHandler = new AuthTokenHandler();
        AdController adController = new AdController();
        try {
            authTokenHandler.authToken(authorization);
            adController.updateStatus(authTokenHandler.getCredential(), ad);
            return Response.status(Response.Status.ACCEPTED).build();
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

    @GET
    @Path("random/{type}/{url}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getRandomAd(
            @PathParam("type") String type,
            @PathParam("url") String url) {
        AdController controller = new AdController();
        try {
            Ad ad = controller.getRandomAd(type, url);
            return Response.ok(ad).build();
        } catch (ServerException e) {
            e.printStackTrace();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        } catch (InvalidDataException e) {
            e.printStackTrace();
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
    }

    @GET
    @Path("random/{type}/{url}/{editor}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getRandomAd(
            @PathParam("type") String type,
            @PathParam("url") String url,
            @PathParam("editor") String editor) {
        AdController controller = new AdController();
        try {
            Ad ad = controller.getRandomAd(type, url, editor);
            return Response.ok(ad).build();
        } catch (ServerException e) {
            e.printStackTrace();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }catch (InvalidDataException e) {
            e.printStackTrace();
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
    }
    
    @GET
    @Path("ad-list")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAdList(
    @HeaderParam(Global.AUTHORIZATION) String authorization){
        AuthTokenHandler ath = new AuthTokenHandler();
        AdDBConnection adDBConnection = new AdDBConnection();
        try {
           ath.authToken(authorization);
           List<Ad> adList = adDBConnection.getAdList();
           return Response.ok(adList).build();
        } catch (ServerException e) {
            e.printStackTrace();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        } catch (AccessException e) {
            e.printStackTrace();
            return Response.status(Response.Status.UNAUTHORIZED).build();
        } 
    }
}
