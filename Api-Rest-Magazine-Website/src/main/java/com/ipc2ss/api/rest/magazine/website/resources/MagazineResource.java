/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ipc2ss.api.rest.magazine.website.resources;

import backend.AuthTokenHandler;
import backend.controllers.MagazineController;
import backend.controllers.MagazinesController;
import backend.exception.AccessException;
import backend.exception.InvalidDataException;
import backend.exception.ServerException;
import backend.model.dto.ApiFile;
import backend.model.dto.Magazine;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.HeaderParam;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.io.InputStream;
import java.time.LocalDate;
import java.util.List;
import org.glassfish.jersey.media.multipart.FormDataBodyPart;
import org.glassfish.jersey.media.multipart.FormDataContentDisposition;
import org.glassfish.jersey.media.multipart.FormDataParam;

/**
 *
 * @author brigidoalvarado
 */
@Path("magazine")
public class MagazineResource {

    private final AuthTokenHandler auth = new AuthTokenHandler();
    
    @POST
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    public Response postMagazine(
            @HeaderParam("Authorization") String authorization,
            @FormDataParam("title") String title,
            @FormDataParam("category") String category,
            @FormDataParam("description") String description,
            @FormDataParam("tags") String tags,
            @FormDataParam("date") String date,
            @FormDataParam("magazine") InputStream uploadedInputStream,
            @FormDataParam("magazine") FormDataBodyPart bodyPart,
            @FormDataParam("magazine") FormDataContentDisposition contentDisposition
    ) {
        AuthTokenHandler ath = new AuthTokenHandler();
        Magazine magazine = new Magazine();
        ApiFile file = new ApiFile();
        try {
            //autorizar consulta
            ath.authToken(authorization);
            //crear objeto magazine
            magazine.setTittle(title);
            magazine.setCategory(category);
            magazine.setDescription(description);
            magazine.setTags(tags);
            magazine.setDate(LocalDate.parse(date));
            magazine.setEditor(ath.getCredential().getUserName());
            //crear objeto ApiFile
            file.setContentType(bodyPart.getMediaType().toString());
            file.setFileName(contentDisposition.getFileName());
            file.setInputStream(uploadedInputStream);
            magazine.setFile(file);
            //Logica
            MagazineController magazineController = new MagazineController();
            magazineController.saveMagazine(magazine);
            return Response.status(Response.Status.CREATED).build();
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
    @Path("new")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getNewPosts(
            @HeaderParam("Authorization") String authorization) {
        AuthTokenHandler ath = new AuthTokenHandler();
        MagazinesController magazinesController = new MagazinesController();
        try {
            ath.authToken(authorization);
            List<Magazine> magazines = magazinesController.getNewPosts();
            return Response.ok(magazines).build();
        } catch (ServerException e) {
            e.printStackTrace();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        } catch (AccessException e) {
            e.printStackTrace();
            return Response.status(Response.Status.UNAUTHORIZED).build();
        }
    }

    @POST
    @Path("cost")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response setCost(
            @HeaderParam("Authorization") String authorization,
            Magazine magazine) {
        MagazineController magazineController = new MagazineController();
        try {
            System.out.println("en set magazine cost");
            auth.authToken(authorization);
            magazineController.setCost(magazine);
            return Response.status(Response.Status.ACCEPTED).build();
        }catch (ServerException e) {
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
}
