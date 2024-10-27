/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package backend.model;

import backend.DBconnection.AdDBConnection;
import backend.exception.ServerException;
import backend.model.dto.Ad;
import java.util.List;

/**
 *
 * @author brigidoalvarado
 */
public class ExpirationAd{
    
    private final AdDBConnection adDBConnection = new AdDBConnection();
    
    public void validate() throws ServerException{
        List<Ad> expiredAds = adDBConnection.getExpiredAds();
        if (expiredAds.isEmpty()) {
            System.out.println("No hay anuncios expirados");
        } else {
            for (Ad expiredAd : expiredAds) {
                adDBConnection.setExpireAd(expiredAd);
            }
        }
    }
    
}
