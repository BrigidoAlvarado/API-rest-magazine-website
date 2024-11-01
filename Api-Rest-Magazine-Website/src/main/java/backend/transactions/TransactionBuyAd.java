/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package backend.transactions;

import backend.DBconnection.DBConnection;
import backend.DBconnection.GlobalDBConnection;
import backend.DBconnection.TextAdDBConnection;
import backend.DBconnection.WalletDBConnection;
import backend.exception.ServerException;
import backend.model.dto.Credential;
import backend.model.dto.TextAd;
import java.sql.SQLException;

/**
 *
 * @author brigidoalvarado
 */
public class TransactionBuyAd extends DBConnection{
    
    private final WalletDBConnection walletDBConnection = new WalletDBConnection();
    private final TextAdDBConnection adDBConnection = new TextAdDBConnection();
    private final GlobalDBConnection globalCostDBConnection = new GlobalDBConnection();
    
    public void buyAd(TextAd ad, Credential credential, double change, double cost) throws ServerException{
            getConnection();
        try {
            connection.setAutoCommit(false);
            walletDBConnection.updateMoney(credential, change, connection);
            adDBConnection.saveTextAd(ad, credential, connection);
            globalCostDBConnection.newIncome(cost, connection);
            connection.commit();
            connection.setAutoCommit(true);
        } catch (SQLException e) {
             e.printStackTrace();
            try {
                connection.rollback();
                throw new ServerException("Error en la transaccion para comprar el anuncio de texto de: "+credential.getUserName());
            } catch (SQLException ex) {
                e.printStackTrace();
                throw new ServerException("Error en rollback de la transaccion para comprar el anuncio de texto de: "+credential.getUserName());
            }
        }
    }
}
