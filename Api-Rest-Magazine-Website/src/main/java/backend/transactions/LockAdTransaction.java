/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package backend.transactions;

import backend.DBconnection.DBConnection;
import backend.DBconnection.EditorDBConnection;
import backend.DBconnection.WalletDBConnection;
import backend.exception.InvalidDataException;
import backend.exception.ServerException;
import backend.model.dto.Amount;
import backend.model.dto.Credential;
import backend.model.dto.LockAd;
import java.sql.SQLException;

/**
 *
 * @author brigidoalvarado
 */
public class LockAdTransaction extends DBConnection {
    
    private final WalletDBConnection walletDBConnection = new WalletDBConnection();
    private final EditorDBConnection editorDBConnection = new EditorDBConnection();
    
    public void buyLockAdTransaction(Credential credential, LockAd lockAd, double change, double cost) throws ServerException, InvalidDataException{
        try {
            getConnection();
            connection.setAutoCommit(false);
            walletDBConnection.updateMoney(credential, change, connection);
            editorDBConnection.saveLockAd(lockAd, credential.getUserName(), connection);
            connection.commit();
            connection.setAutoCommit(true);
        } catch (SQLException e) {
             e.printStackTrace();
            try {
                connection.rollback();
                throw new ServerException("Error en la transaccion para comprar el bloqueo de anuncio de: "+credential.getUserName());
            } catch (SQLException ex) {
                e.printStackTrace();
                throw new ServerException("Error en rollback de la transaccion para comprar el bloqueo de anuncio de texto: "+credential.getUserName());
            }
        }
    }
}
