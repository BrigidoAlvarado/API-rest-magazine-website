/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package backend.controllers;

import backend.model.Store;
import backend.transactions.TransactionAd;
import backend.exception.InvalidDataException;
import backend.exception.ServerException;
import backend.model.dto.Credential;
import backend.model.dto.ImageAd;
import backend.model.dto.TextAd;
import backend.model.dto.VideoAd;

/**
 *
 * @author brigidoalvarado
 */
public class BuyAdController {
    
    private final TransactionAd transactionBuyAd = new TransactionAd();
    private final Store adStore = new  Store();
    
    public double buyTextAd(TextAd ad, Credential credential)throws ServerException, InvalidDataException{
        ad.validate();
        double cost = adStore.calculateCost(ad);
        ad.setCost(cost);
        double change = adStore.calculateChange(credential, cost);
        transactionBuyAd.buyAd(ad, credential,change,cost);
        return change;
    }
    
    public double buyAd(VideoAd ad, Credential credential)throws ServerException, InvalidDataException{
        ad.validate();
        double cost = adStore.calculateCost(ad);
        ad.setCost(cost);
        double change = adStore.calculateChange(credential, cost);
        transactionBuyAd.buyAd(ad, credential,change,cost);
        return change;
    }
    
    public double buyAd(ImageAd ad, Credential credential)throws ServerException, InvalidDataException{
        ad.validate();
        double cost = adStore.calculateCost(ad);
        ad.setCost(cost);
        double change = adStore.calculateChange(credential, cost);
        transactionBuyAd.buyAd(ad, credential,change,cost);
        return change;
    }
}
