/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ipc2ss.api.rest.magazine.website.resources;

import backend.AuthTokenHandler;
import backend.DBconnection.AdReportsDBConnection;
import backend.controllers.AdminReportsController;
import backend.exception.AccessException;
import backend.exception.InvalidDataException;
import backend.exception.ServerException;
import backend.model.dto.Ad;
import backend.model.dto.Advertiser;
import backend.model.dto.EarningsReport;
import backend.model.dto.Filter;
import backend.model.dto.Magazine;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.HeaderParam;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.List;

/**
 *
 * @author brigidoalvarado
 */
@Path("admin-report")
public class AdminReportsResource {

    private final AuthTokenHandler ath = new AuthTokenHandler();
    private final AdminReportsController controller = new AdminReportsController();
    private static final String AUTHORIZATION = "Authorization";

    @GET
    @Path("earning")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getEarningResport(
            @HeaderParam(AUTHORIZATION) String authorization) {
        try {
            ath.authToken(authorization);
            EarningsReport earningsReport = controller.getEarningReport();
            return Response.ok(earningsReport).build();
        } catch (ServerException e) {
            e.printStackTrace();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        } catch (AccessException e) {
            e.printStackTrace();
            return Response.status(Response.Status.UNAUTHORIZED).build();
        }
    }

    @GET
    @Path("advertiser/{userName}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAdvertiseReport(
            @HeaderParam(AUTHORIZATION) String authorization,
            @PathParam("userName") String userName) {
        try {
            ath.authToken(authorization);
            List<Advertiser> advertiserList = controller.getAdvertiserReport(userName);
            return Response.ok(advertiserList).build();
        } catch (ServerException e) {
            e.printStackTrace();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        } catch (AccessException e) {
            e.printStackTrace();
            return Response.status(Response.Status.UNAUTHORIZED).build();
        }
    }

    @POST
    @Path("popular")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response getPopularMagazines(
            @HeaderParam(AUTHORIZATION) String authorization,
            Filter filter) {
        try {
            ath.authToken(authorization);
            List<Magazine> magazineList = controller.getPopularMagazinesReport(filter);
            return Response.ok(magazineList).build();
        } catch (ServerException e) {
            e.printStackTrace();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        } catch (AccessException e) {
            e.printStackTrace();
            return Response.status(Response.Status.UNAUTHORIZED).build();
        }
    }

    @POST
    @Path("ad")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAdReport(
            @HeaderParam(AUTHORIZATION) String authorization,
            Filter filter) {
        AdReportsDBConnection dBConnection = new AdReportsDBConnection();
        try {
            ath.authToken(authorization);
            List<Ad> adList = dBConnection.getAdList(filter);
            return Response.ok(adList).build();
        } catch (ServerException e) {
            e.printStackTrace();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        } catch (AccessException e) {
            e.printStackTrace();
            return Response.status(Response.Status.UNAUTHORIZED).build();
        }
    }

    @POST
    @Path("effectivity-ad")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response getEffectivityResport(
            @HeaderParam(AUTHORIZATION) String authorization,
            Filter filter) {
        try {
            ath.authToken(authorization);
            List<Advertiser> advetiserList = controller.getEffectivityAdReportList(filter);
            return Response.ok(advetiserList).build();
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
    @Path("comments")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response getCommentMagazineReport(
            @HeaderParam(AUTHORIZATION) String authorization,
            Filter filter) {
        try {
            ath.authToken(authorization);
            List<Magazine> magazineList = controller.getCommentMagazineReport(filter);
            return Response.ok(magazineList).build();
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
