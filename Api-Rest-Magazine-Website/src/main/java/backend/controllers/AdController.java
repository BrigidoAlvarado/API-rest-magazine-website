/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package backend.controllers;

import backend.DBconnection.AdDBConnection;
import backend.exception.InvalidDataException;
import backend.exception.ServerException;
import backend.model.ExpirationAd;
import backend.model.dto.Ad;
import backend.model.dto.Credential;
import java.util.List;

/**
 *
 * @author brigidoalvarado
 */

public class AdController {
    private final AdDBConnection adDBConnection = new AdDBConnection();
    private final ExpirationAd expirationAd = new ExpirationAd();
    
    public List<Ad> getPurchasedAds(Credential credential) throws ServerException{
        expirationAd.validate();
        return adDBConnection.getPurchasedAds(credential);
    }
    
    public Ad getAdById(int id) throws InvalidDataException, ServerException {
        return adDBConnection.getAdById(id);
    }
    
    public void updateStatus(Credential credential, Ad ad)throws InvalidDataException, ServerException{
        ad.validate();
        adDBConnection.updateStatus(credential, ad);
    }
}
