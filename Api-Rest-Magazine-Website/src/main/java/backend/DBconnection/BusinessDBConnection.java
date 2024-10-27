/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package backend.DBconnection;

import backend.exception.ServerException;
import backend.model.dto.Amount;
import backend.model.dto.Credential;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author brigidoalvarado
 */
public class BusinessDBConnection extends DBConnection {
    
    public Amount toUpMoney(Credential credential, Amount amount)throws ServerException{
        WalletDBConnection walletConnection = new WalletDBConnection();
        
        double money = walletConnection.getMoney(credential);
        amount.setAmount( money + amount.getAmount());
        walletConnection.updateMoney(credential, amount);
        
        return amount;
    }
}
