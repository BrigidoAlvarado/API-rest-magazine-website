/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package backend.controllers;

import backend.DBconnection.TextAdDBConnection;
import backend.exception.InvalidDataException;
import backend.exception.ServerException;
import backend.model.ExpirationAd;
import backend.model.dto.Credential;
import backend.model.dto.TextAd;

/**
 *
 * @author brigidoalvarado
 */
public class TextAdController {
    private final TextAdDBConnection textAdDBConnection = new TextAdDBConnection();
    private final ExpirationAd expirationAd = new ExpirationAd();
    
    public TextAd getTextAdById(Credential credential, int id) throws ServerException, InvalidDataException{
        expirationAd.validate();
        if (id > 0) {
           return textAdDBConnection.getById(id);
        } else {
            throw  new InvalidDataException("El id proporcionado <"+id+"> es invalido");
        }
    }
    
    public void updateAd(TextAd ad) throws ServerException, InvalidDataException{
        expirationAd.validate();
        ad.validateUpdate();
        textAdDBConnection.update(ad);
    }
}
