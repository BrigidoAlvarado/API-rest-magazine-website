/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ipc2ss.api.rest.magazine.website.resources;

import backend.model.dto.Ad;
import jakarta.servlet.ServletOutputStream;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.StreamingOutput;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.util.JRLoader;

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
        System.out.println("En export tiene un tamaño de " + adList.size());
        
        try {
            InputStream jasperStream = getClass().getClassLoader().getResourceAsStream("reports/Blank_A4.jasper");
            if (jasperStream == null) {
                System.out.println("El archivo no se encontró en el classpath.");
                return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Archivo no encontrado").build();
            } else {
                System.out.println("El archivo se cargó correctamente.");
            }

            JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(adList);

            // Crear los parámetros para el reporte (si necesitas alguno)
            Map<String, Object> parameters = new HashMap<>();
            parameters.put("dataSource", dataSource);
            // Puedes agregar más parámetros si es necesario

            JasperReport report = (JasperReport) JRLoader.loadObject(jasperStream);

            JasperPrint jasperPrint = JasperFillManager.fillReport(report, parameters, dataSource);

            StreamingOutput  stream = new StreamingOutput() {
                @Override
                public void write(OutputStream outputStream) throws java.io.IOException {
                    try {
                        JasperExportManager.exportReportToPdfStream(jasperPrint, outputStream);
                    } catch (JRException ex) {
                        Logger.getLogger(ReportResource.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            };

            return Response.ok(stream)
                    .header("Content-Disposition", "inline; filename=report.pdf")
                    .build();
            
        } catch (JRException e) {
            e.printStackTrace();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }
        
}
