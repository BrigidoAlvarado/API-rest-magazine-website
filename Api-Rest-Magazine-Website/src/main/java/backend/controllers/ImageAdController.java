/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package backend.controllers;

import backend.DBconnection.ImageAdDBConnection;
import backend.exception.InvalidDataException;
import backend.exception.ServerException;
import backend.model.ExpirationAd;
import backend.model.dto.ImageAd;
import backend.model.dto.TextAd;

/**
 *
 * @author brigidoalvarado
 */
public class ImageAdController {
    
    private final ExpirationAd expirationAd = new ExpirationAd();

    public void updateImageAd(ImageAd imageAd) throws ServerException, InvalidDataException {
       expirationAd.validate();
        imageAd.validateUpdate();
            if (imageAd.getFile().getInputStream() == null) {
                TextAdController textAdController = new TextAdController();
                TextAd ad = new TextAd();
                ad.setText(imageAd.getText());
                ad.setId(imageAd.getId());
                textAdController.updateAd(ad);
            } else {
                ImageAdDBConnection dBConnection = new  ImageAdDBConnection();
                dBConnection.updateImageAd(imageAd);
            }
    }
}
