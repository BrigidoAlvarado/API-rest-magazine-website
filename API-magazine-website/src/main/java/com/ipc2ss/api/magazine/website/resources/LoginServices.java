/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ipc2ss.api.magazine.website.resources;

import backend.LoginController;
import backend.exception.InvalidDataException;
import backend.exception.ServerException;
import backend.model.dto.Credential;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
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
public class LoginServices {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getSolicitudes() {
        // ClaseDBSolicitudes dbSolicitudes = new ClaseDBSolicitudes();
        // List<Solicitud> solicitudes = dbSolicitudes.obtenerSolicitudes();

        return Response.ok().build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createSolicitud(Credential credential) {
        LoginController loginController = new LoginController();
        try {
            loginController.login(credential);
            System.out.println("SE ENCONTRO LA CUENTA");
            return Response.status(Response.Status.CREATED).build();
        } catch (ServerException e) {
          e.printStackTrace();
          return  Response.status(Response.Status.NOT_FOUND).build();
        } catch(InvalidDataException e){
            e.printStackTrace();
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
        
}
    
}
