/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ipc2ss.api.rest.magazine.website.resources;

import backend.controllers.AccountCreator;
import backend.JwtUtil;
import backend.exception.AccessException;
import backend.exception.InvalidDataException;
import backend.exception.ServerException;
import backend.model.UserType;
import backend.model.dto.ApiFile;
import backend.model.dto.Credential;
import backend.model.dto.Profile;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.io.InputStream;
import org.glassfish.jersey.media.multipart.FormDataBodyPart;
import org.glassfish.jersey.media.multipart.FormDataContentDisposition;
import org.glassfish.jersey.media.multipart.FormDataParam;

/**
 *
 * @author brigidoalvarado
 */
@Path("new-account")
public class NewAccountResource {

    @POST
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createNewAccount(
            @FormDataParam("userType") String userType,
            @FormDataParam("userName") String userName,
            @FormDataParam("password") String password,
            @FormDataParam("tastes") String tastes,
            @FormDataParam("topicOfInterest") String topicOfInterest,
            @FormDataParam("description") String description,
            @FormDataParam("hobbies") String hobbies,
            @FormDataParam("photo") FormDataBodyPart bodyPart,
            @FormDataParam("photo") InputStream uploadedInputStream,
            @FormDataParam("photo") FormDataContentDisposition contentDispotition) {
        JwtUtil jwtUtil = new JwtUtil();
        Credential credential = new Credential();
        Profile profile = new Profile();
        ApiFile apiFile = new ApiFile();
        try {
            //se inicializa el dto de credencial
            credential.setUserType(UserType.valueOf(userType));
            credential.setUserName(userName);
            credential.setPassword(password);
            //se inicializa el dto del perfil
            profile.setDescription(description);
            profile.setHobbies(hobbies);
            profile.setTastes(tastes);
            profile.setTopicOfInterest(topicOfInterest);
            //se inicializa el archivo
            try {
                apiFile.setInputStream(uploadedInputStream);
                apiFile.setContentType(bodyPart.getMediaType().toString());
            } catch (NullPointerException e) {
                apiFile.setInputStream(null);
                apiFile.setContentType(null);
            }
            //se inicia la logica 
            AccountCreator accountCreator = new AccountCreator(credential, profile, apiFile);
            accountCreator.createNewAccount();
            String token = jwtUtil.generatedToken(userName, userType);
            String jsonResponse = "{\"token\":\"" + token + "\"}";
            return Response.ok(jsonResponse, MediaType.APPLICATION_JSON).build();
        } catch (ServerException e) {
            e.printStackTrace();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        } catch (AccessException e) {
            e.printStackTrace();
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
    }

    @GET
    public Response ping() {
        return Response
                .ok("ping Jakarta EE")
                .build();
    }
}
