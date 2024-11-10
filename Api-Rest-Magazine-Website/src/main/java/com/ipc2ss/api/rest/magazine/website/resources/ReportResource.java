/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ipc2ss.api.rest.magazine.website.resources;

import backend.model.dto.Ad;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;

/**
 *
 * @author brigidoalvarado
 */
@Path("export")
public class ReportResource {

    @POST
    @Path("admin-ad")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces("application/pdf")
    public Response exportAdminAdReport(
            List<Ad> adList) {
        System.out.println("en export");
        try {
            InputStream jasperStream = getClass().getResourceAsStream("/report/reporteAdminAnuncio.jasper");
            JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(adList);
            
            Map<String, Object> parameters = new HashMap<>();
            parameters.put("dataSource", dataSource);
            
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperStream, parameters);
            
            ByteArrayOutputStream pdfStream = new ByteArrayOutputStream();
            JRPdfExporter exporter = new JRPdfExporter();
            exporter.setExporterInput(new SimpleExporterInput(jasperPrint));
            exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(pdfStream));
            exporter.exportReport();
            
            return Response.ok(pdfStream.toByteArray())
                    .header("Content-Disposition", "inline; filename=report.pdf")
                    .build();
            
        } catch (JRException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }
}