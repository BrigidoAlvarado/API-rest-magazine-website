/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package backend.controllers;

import backend.DBconnection.VideoAdDBConnection;
import backend.exception.InvalidDataException;
import backend.exception.ServerException;
import backend.model.ExpirationAd;
import backend.model.dto.VideoAd;

/**
 *
 * @author brigidoalvarado
 */
public class VideoAdController {
    
    private final ExpirationAd expirationAd = new ExpirationAd();
    private final VideoAdDBConnection dBConnection = new VideoAdDBConnection();
    
    public VideoAd getById(int id) throws InvalidDataException, ServerException{
        expirationAd.validate();
        if (id < 0) {
            throw new InvalidDataException("El id del anuncio de video es invalido");
        } else {
            return dBConnection.getById(id);
        }
    }
    
    public void updateVideoAd( VideoAd ad) throws ServerException, InvalidDataException{
        expirationAd.validate();
        ad.validateUpdate();
        dBConnection.update(ad);
    }
}
