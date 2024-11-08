/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package backend.model;

import backend.DBconnection.GlobalDBConnection;
import backend.DBconnection.WalletDBConnection;
import backend.exception.InvalidDataException;
import backend.exception.ServerException;
import backend.model.dto.Ad;
import backend.model.dto.Credential;
import backend.model.dto.LockAd;

/**
 *
 * @author brigidoalvarado
 */
public class Store {
    
    private final GlobalDBConnection globalDBConnection = new GlobalDBConnection();
    private final WalletDBConnection walletDBConnection = new WalletDBConnection();
    
    public double calculateCost(Ad ad)throws ServerException{
        double price = globalDBConnection.getCost(ad.getKindAd());
        return price * ad.getAdTime().getDay();
    }
    
    public double calculateCost(LockAd lockAd)throws ServerException{
        double price = globalDBConnection.getCost(lockAd.getKind());
        return price * lockAd.getDays();
    }
    
    public double calculateChange(Credential credential, double cost) throws ServerException, InvalidDataException{
        double money = walletDBConnection.getMoney(credential);
        money -= cost;
        if (money < 0) {
            throw new InvalidDataException("El usuario no tiene suficiente dinero para realizar la compra");
        }
        return money;
    }
    
}
