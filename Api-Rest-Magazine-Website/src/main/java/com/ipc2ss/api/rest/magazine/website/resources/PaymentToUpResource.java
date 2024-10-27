/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ipc2ss.api.rest.magazine.website.resources;

import backend.exception.InvalidDataException;
import backend.exception.ServerException;
import backend.AuthTokenHandler;
import backend.controllers.PaymentToUpController;
import backend.exception.AccessException;
import backend.model.dto.Amount;
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
@Path("payment-to-up")
public class PaymentToUpResource {
    
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response login(
    @HeaderParam("Authorization") String authorization,
            Amount amount) {
        AuthTokenHandler tokenHandler = new AuthTokenHandler();
        try {
            tokenHandler.authToken(authorization);
            PaymentToUpController paymentToUpController = new PaymentToUpController();
            paymentToUpController.toUpMoney(tokenHandler.getCredential(), amount);
            
            return Response.ok(amount).build();
        } catch (ServerException e) {
          e.printStackTrace();
          return  Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        } catch(AccessException e){
            e.printStackTrace();
            return Response.status(Response.Status.UNAUTHORIZED).build();
        } catch (InvalidDataException e){
            e.printStackTrace();
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
    }
}
