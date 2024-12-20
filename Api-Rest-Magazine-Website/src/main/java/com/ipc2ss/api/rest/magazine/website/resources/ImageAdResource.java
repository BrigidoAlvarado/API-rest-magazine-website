/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ipc2ss.api.rest.magazine.website.resources;

import backend.AuthTokenHandler;
import backend.controllers.BuyAdController;
import backend.controllers.ImageAdController;
import backend.controllers.TextAdController;
import backend.enums.AdTime;
import backend.enums.Global;
import backend.exception.AccessException;
import backend.exception.InvalidDataException;
import backend.exception.ServerException;
import backend.model.dto.Ad;
import backend.model.dto.Amount;
import backend.model.dto.ApiFile;
import backend.model.dto.ImageAd;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.HeaderParam;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.io.InputStream;
import java.time.LocalDate;
import org.glassfish.jersey.media.multipart.FormDataBodyPart;
import org.glassfish.jersey.media.multipart.FormDataParam;

/**
 *
 * @author brigidoalvarado
 */
@Path("image-ad")
public class ImageAdResource {

    private final BuyAdController controller = new BuyAdController();
    private final AuthTokenHandler ath = new AuthTokenHandler();

    @POST
    @Path("buy")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    @Produces(MediaType.APPLICATION_JSON)
    public Response buyImageAd(
            @HeaderParam(Global.AUTHORIZATION) String authorization,
            @FormDataParam("kind") Global kind,
            @FormDataParam("time") AdTime time,
            @FormDataParam("text") String text,
            @FormDataParam("date") String date,
            @FormDataParam("image") InputStream inputStream,
            @FormDataParam("image") FormDataBodyPart bodyPart) {
        ImageAd ad = new ImageAd();
        ApiFile file = new ApiFile();
        try {
            file.setInputStream(inputStream);
            file.setContentType(bodyPart.getMediaType().toString());
            ad.setFile(file);
            ad.setDate(LocalDate.parse(date));
            ad.setKind(kind);
            ad.setTime(time);
            ad.setText(text);
            ath.authToken(authorization);
            Amount amount = new Amount();
            amount.setAmount(controller.buyAd(ad, ath.getCredential()));
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

    @GET
    @Path("get/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getById(
            @HeaderParam(Global.AUTHORIZATION) String authorization,
            @PathParam("id") int id) {
        TextAdController textAdController = new TextAdController();
        try {
            ath.authToken(authorization);
            Ad ad = textAdController.getTextAdById(ath.getCredential(), id);
            return Response.ok(ad).build();
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
    @Path("update")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    public Response updateImageAd(
            @HeaderParam(Global.AUTHORIZATION) String authorization,
            @FormDataParam("text") String text,
            @FormDataParam("id") int id,
            @FormDataParam("image") InputStream inputStream,
            @FormDataParam("image") FormDataBodyPart bodyPart) {
        ImageAdController imageAdController = new ImageAdController();
        ImageAd ad = new ImageAd();
        ApiFile file = new ApiFile();
        try {
            try {
                file.setInputStream(inputStream);
                file.setContentType(bodyPart.getMediaType().toString());
            } catch (NullPointerException e) {
                file.setInputStream(null);
                file.setContentType(null);
            }
            ad.setId(id);
            ad.setFile(file);
            ad.setText(text);
            ath.authToken(authorization);
            imageAdController.updateImageAd(ad);
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
