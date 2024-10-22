/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package backend.model.dto;

import java.io.InputStream;

/**
 *
 * @author brigidoalvarado
 */
public class ApiFile {
    
    private InputStream binaryFile;
    private String fileName;
    private String contentType;

    public InputStream getInputStream() {
        return binaryFile;
    }

    public void setInputStream(InputStream inputStream) {
        this.binaryFile = inputStream;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
        System.out.println("el content type es: "+contentType);
    }
}
