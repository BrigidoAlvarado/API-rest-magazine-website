/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package backend.controllers;

import backend.DBconnection.AdDBConnection;
import backend.exception.InvalidDataException;
import backend.exception.ServerException;
import backend.model.ExpirationAd;
import backend.model.ExpirationLockAd;
import backend.model.dto.Ad;
import backend.model.dto.Credential;
import backend.transactions.TransactionAd;
import java.util.List;

/**
 *
 * @author brigidoalvarado
 */
public class AdController {

    private final AdDBConnection adDBConnection = new AdDBConnection();
    private final ExpirationAd expirationAd = new ExpirationAd();

    public List<Ad> getPurchasedAds(Credential credential) throws ServerException, InvalidDataException {
        expirationAd.validate();
        return adDBConnection.getPurchasedAds(credential);
    }

    public Ad getAdById(int id) throws InvalidDataException, ServerException {
        return adDBConnection.getAdById(id);
    }

    public void updateStatus(Credential credential, Ad ad) throws InvalidDataException, ServerException {
        ad.validate();
        adDBConnection.updateStatus(credential, ad);
    }

    public Ad getRandomAd(String type, String url) throws ServerException, InvalidDataException {
        TransactionAd transactionAd = new TransactionAd();

        expirationAd.validate();
        Ad ad = adDBConnection.getRandomAd(type);
        transactionAd.increaseView(ad.getId(), url);
        return ad;
    }

    public Ad getRandomAd(String type, String url, String editor) throws ServerException, InvalidDataException {
        TransactionAd transactionAd = new TransactionAd();
        ExpirationLockAd expirationLockAd = new ExpirationLockAd();
        Ad ad = new Ad();
        System.out.println("editor: " + editor);
        if (!expirationLockAd.existLokAd(editor)) {
            System.out.println("el editor no tiene bloqueador");
            expirationAd.validate();
            ad = adDBConnection.getRandomAd(type);
            transactionAd.increaseView(ad.getId(), url);
        }
        System.out.println("el editor tiene bloqueador");
        return ad;
    }
}
