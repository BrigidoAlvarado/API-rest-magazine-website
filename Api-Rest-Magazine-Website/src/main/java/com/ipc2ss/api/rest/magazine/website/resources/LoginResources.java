/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ipc2ss.api.rest.magazine.website.resources;

import backend.JwtUtil;
import backend.controllers.LoginController;
import backend.exception.AccessException;
import backend.exception.ServerException;
import backend.model.dto.Credential;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

/**
 *
 * @author brigidoalvarado
 */
@Path("login")
public class LoginResources {
    
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response login(Credential credential) {
        LoginController loginController = new LoginController();
            JwtUtil jwtUtil = new JwtUtil();
        try {
            loginController.login(credential);
            System.out.println("cuenta validada exitosamente");
            String token = jwtUtil.generatedToken(credential.getUserName(), credential.getUserType().name());
            String jsonResponse = "{\"token\":\"" + token + "\"}";
            return Response.ok(jsonResponse, MediaType.APPLICATION_JSON).build();
        } catch (ServerException e) {
          e.printStackTrace();
          return  Response.status(Response.Status.NOT_FOUND).build();
        } catch(AccessException e){
            e.printStackTrace();
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
    }
}
