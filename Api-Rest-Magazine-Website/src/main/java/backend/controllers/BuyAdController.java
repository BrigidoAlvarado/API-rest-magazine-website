/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package backend.controllers;

import backend.model.Store;
import backend.transactions.TransactionBuyAd;
import backend.exception.InvalidDataException;
import backend.exception.ServerException;
import backend.model.dto.Credential;
import backend.model.dto.TextAd;

/**
 *
 * @author brigidoalvarado
 */
public class BuyAdController {
    
    private final TransactionBuyAd transactionBuyAd = new TransactionBuyAd();
    private final Store adStore = new  Store();
    
    public double buyTextAd(TextAd ad, Credential credential)throws ServerException, InvalidDataException{
        ad.validate();
        double cost = adStore.calculateCost(ad);
        double change = adStore.calculateChange(credential, cost);
        transactionBuyAd.buyAd(ad, credential,change,cost);
        return change;
    }
}
