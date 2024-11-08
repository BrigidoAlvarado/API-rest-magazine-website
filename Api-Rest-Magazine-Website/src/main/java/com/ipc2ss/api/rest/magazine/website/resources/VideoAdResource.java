/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ipc2ss.api.rest.magazine.website.resources;

import backend.AuthTokenHandler;
import backend.controllers.AdController;
import backend.controllers.BuyAdController;
import backend.controllers.VideoAdController;
import backend.exception.AccessException;
import backend.exception.InvalidDataException;
import backend.exception.ServerException;
import backend.model.dto.Amount;
import backend.model.dto.VideoAd;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.HeaderParam;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

/**
 *
 * @author brigidoalvarado
 */
@Path("video-ad")
public class VideoAdResource {

    

    @POST
    @Path("buy")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response buyVideoAd(
            @HeaderParam("Authorization") String authorization,
            VideoAd videoAd) {
        AuthTokenHandler auth = new AuthTokenHandler();
   BuyAdController controller = new BuyAdController();
        try {
            System.out.println("en comprar video ad");
            auth.authToken(authorization);
            Amount amount = new Amount();
            amount.setAmount(controller.buyAd(videoAd, auth.getCredential()));
            return Response.ok(amount).build();
        }  catch (ServerException e) {
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
