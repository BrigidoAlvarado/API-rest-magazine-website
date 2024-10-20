/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ipc2ss.api.rest.magazine.website.resources;

import backend.exception.InvalidDataException;
import backend.exception.ServerException;
import backend.model.UserType;
import backend.model.dto.Account;
import backend.model.dto.ApiFile;
import backend.model.dto.Credential;
import backend.model.dto.Profile;
import jakarta.ws.rs.Consumes;
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
    @Path("new-account")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    @Produces(MediaType.APPLICATION_JSON)
    public Response uploadFileV1(
            @FormDataParam("userType") String userType,
            @FormDataParam("userType") String userName,
            @FormDataParam("password") String password,
            @FormDataParam("tastes") String tastes,
            @FormDataParam("topicOfInterest") String topicOfInterest,
            @FormDataParam("description") String description,
            @FormDataParam("hobbies") String hobbies,
            @FormDataParam("fileObject") FormDataBodyPart bodyPart) {

        Account account = new Account();
        Credential credential = new Credential();
        Profile profile = new Profile();
        ApiFile apiFile = new ApiFile();
        account.setCredential(credential);
        account.setProfile(profile);
        try {
            //se inicializa el dto de credencial
            credential.setUserType(UserType.valueOf(userType));
            credential.setUserName(userName);
            credential.setPassword(password);
            credential.validate();
            //se inicializa el dto del perfil
            profile.setDescription(description);
            profile.setHobbies(hobbies);
            profile.setTastes(tastes);
            profile.setTopicOfInterest(topicOfInterest);
            //se inicializa el archivo
            apiFile.setInputStream(bodyPart.getContent());
            apiFile.setContentType(bodyPart.getMediaType().toString());
        } catch (InvalidDataException e) {
            e.printStackTrace();
        } /*catch(ServerException e){
            
        }*/
    }
}
