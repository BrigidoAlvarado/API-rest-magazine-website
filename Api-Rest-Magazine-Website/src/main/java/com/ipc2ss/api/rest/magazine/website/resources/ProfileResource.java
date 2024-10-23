/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ipc2ss.api.rest.magazine.website.resources;

import backend.AccountCreator;
import backend.AuthTokenHandler;
import backend.JwtUtil;
import backend.ProfileController;
import backend.exception.InvalidDataException;
import backend.exception.ServerException;
import backend.model.UserType;
import backend.model.dto.ApiFile;
import backend.model.dto.Credential;
import backend.model.dto.Profile;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.HeaderParam;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.io.InputStream;
import org.glassfish.jersey.media.multipart.FormDataBodyPart;
import org.glassfish.jersey.media.multipart.FormDataParam;

/**
 *
 * @author brigidoalvarado
 */
@Path("profile")
public class ProfileResource {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getProfile(
            @HeaderParam("Authorization") String authorization) {
        AuthTokenHandler authTokenHandler = new AuthTokenHandler();
        ProfileController profileController = new ProfileController();
        try {
            authTokenHandler.authToken(authorization);
            Credential credential = authTokenHandler.getCredential();
            Profile profile = profileController.getProfile(credential);
            return Response.ok(profile).build();
        } catch (InvalidDataException e) {
            System.out.println("enviando no autoriazado");
            e.printStackTrace();
            return Response.status(Response.Status.UNAUTHORIZED).build();
        } catch (ServerException e) {
            System.out.println("enviando internal server error");
            e.printStackTrace();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }
    
    @POST
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    public Response createNewAccount(
            @HeaderParam("Authorization") String authorization,
            @FormDataParam("userName") String userName,
            @FormDataParam("tastes") String tastes,
            @FormDataParam("topicOfInterest") String topicOfInterest,
            @FormDataParam("description") String description,
            @FormDataParam("hobbies") String hobbies,
            @FormDataParam("photo") FormDataBodyPart bodyPart,
            @FormDataParam("photo") InputStream uploadedInputStream) {
        System.out.println("se editara el perfil de: "+userName);
        AuthTokenHandler authTokenHandler = new AuthTokenHandler();
        ProfileController profileController = new ProfileController();
        Profile profile = new Profile();
        ApiFile apiFile = new ApiFile();
        try {
            //Authorizaciones
            authTokenHandler.authToken(authorization);
            if (!userName.equalsIgnoreCase(authTokenHandler.getCredential().getUserName())) {
                throw new InvalidDataException("se esta intentando editar un perfil ajeno");
            }
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
            profileController.editProfile(authTokenHandler.getCredential(), profile, apiFile);
            return Response.status(Response.Status.ACCEPTED).build();
        } catch (ServerException e) {
            e.printStackTrace();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        } catch (InvalidDataException e) {
            e.printStackTrace();
            return Response.status(Response.Status.UNAUTHORIZED).build();
        }
    }
}
