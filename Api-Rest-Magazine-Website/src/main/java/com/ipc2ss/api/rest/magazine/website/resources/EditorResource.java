/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ipc2ss.api.rest.magazine.website.resources;

import backend.AuthTokenHandler;
import backend.controllers.EditorController;
import backend.controllers.MagazineNumberController;
import backend.exception.AccessException;
import backend.exception.InvalidDataException;
import backend.exception.ServerException;
import backend.model.dto.Amount;
import backend.model.dto.ApiFile;
import backend.model.dto.LockAd;
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
import java.util.List;
import org.glassfish.jersey.media.multipart.FormDataBodyPart;
import org.glassfish.jersey.media.multipart.FormDataContentDisposition;
import org.glassfish.jersey.media.multipart.FormDataParam;

/**
 *
 * @author brigidoalvarado
 */
@Path("editor")
public class EditorResource {

    private final AuthTokenHandler auth = new AuthTokenHandler();
    private final EditorController controller = new EditorController();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getPublishedMagazines(
            @HeaderParam("Authorization") String authorization) {
        EditorController editorController = new EditorController();
        try {
            auth.authToken(authorization);
            List<Magazine> list = editorController.getPublishedMagazineList(auth.getCredential());
            return Response.ok(list).build();
        } catch (ServerException e) {
            e.printStackTrace();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        } catch (AccessException e) {
            e.printStackTrace();
            return Response.status(Response.Status.UNAUTHORIZED).build();
        }
    }

    @POST
    @Path("lockAd")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response buyLockAd(
            @HeaderParam("Authorization") String authorization,
            LockAd lockAd) {
        try {
            auth.authToken(authorization);
            Amount amount = controller.buyLockAd(auth.getCredential(), lockAd);
            return Response.ok(amount).build();
        } catch (ServerException e) {
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

    @POST
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    public Response postNewNumber(
            @HeaderParam("Authorization") String authorization,
            @FormDataParam("id") String id,
            @FormDataParam("file") InputStream uploadedInputStream,
            @FormDataParam("file") FormDataBodyPart bodyPart,
            @FormDataParam("file") FormDataContentDisposition contentDisposition) {
        ApiFile file = new ApiFile();
        try {
            file.setContentType(bodyPart.getMediaType().toString());
            file.setFileName(contentDisposition.getFileName());
            file.setInputStream(uploadedInputStream);
            auth.authToken(authorization);
            MagazineNumberController controller = new MagazineNumberController();
            controller.newNumber(Integer.parseInt(id), file);
            return Response.status(Response.Status.CREATED).build();
        } catch (ServerException e) {
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

    @POST
    @Path("status")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateCommentLikeStatus(
            @HeaderParam("Authorization") String authorization,
            Magazine magazine) {
        try {
            System.out.println("en status");
            auth.authToken(authorization);
            controller.lockCommentsAndLikes(magazine);
            return Response.status(Response.Status.ACCEPTED).build();
        } catch (ServerException e) {
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

    @POST
    @Path("suscription-status")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateSubscriptionStatus(
            @HeaderParam("Authorization") String authorization,
            Magazine magazine) {
        try {
            auth.authToken(authorization);
            controller.updateSubscriptionStatus(magazine);
            return Response.status(Response.Status.ACCEPTED).build();
        } catch (ServerException e) {
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
