/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ipc2ss.api.rest.magazine.website.resources;

import backend.AuthTokenHandler;
import backend.DBconnection.FileDBConnection;
import backend.exception.AccessException;
import backend.exception.InvalidDataException;
import backend.exception.ServerException;
import backend.model.dto.ApiFile;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.HeaderParam;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.Response;

/**
 *
 * @author brigidoalvarado
 */
@Path("file")
public class FileResource {

    private final AuthTokenHandler auth = new AuthTokenHandler();

    @GET
    @Path("photo/{userName}/{userType}")
    @Produces({"image/jpeg", "image/png"})
    public Response getPhoto(
            @HeaderParam("Authorization") String authorization,
            @PathParam("userName") String userName,
            @PathParam("userType") String userType) {
        System.out.println("en get photo");
        FileDBConnection dBConnection = new FileDBConnection();
        try {
            auth.authToken(authorization);
            ApiFile file = dBConnection.getProfile(userName, userType);
            return Response.ok(file.getInputStream()).type(file.getContentType()).build();
        } catch (ServerException e) {
            e.printStackTrace();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        } catch (AccessException e) {
            e.printStackTrace();
            return Response.status(Response.Status.UNAUTHORIZED).build();
        }
    }
}
