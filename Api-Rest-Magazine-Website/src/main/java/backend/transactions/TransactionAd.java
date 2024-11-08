/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package backend.transactions;

import backend.DBconnection.AdDBConnection;
import backend.DBconnection.DBConnection;
import backend.DBconnection.DBConnectionSingleton;
import backend.DBconnection.GlobalDBConnection;
import backend.DBconnection.ImageAdDBConnection;
import backend.DBconnection.TextAdDBConnection;
import backend.DBconnection.VideoAdDBConnection;
import backend.DBconnection.WalletDBConnection;
import backend.DBconnection.adViewDBConnection;
import backend.exception.ServerException;
import backend.model.dto.Credential;
import backend.model.dto.ImageAd;
import backend.model.dto.TextAd;
import backend.model.dto.VideoAd;
import java.sql.Connection;
import java.sql.SQLException;

/**
 *
 * @author brigidoalvarado
 */
public class TransactionAd extends DBConnection {

    private final WalletDBConnection walletDBConnection = new WalletDBConnection();
    private final GlobalDBConnection globalCostDBConnection = new GlobalDBConnection();

    public void buyAd(TextAd ad, Credential credential, double change, double cost) throws ServerException {
        TextAdDBConnection adDBConnection = new TextAdDBConnection();
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
                throw new ServerException("Error en la transaccion para comprar el anuncio de texto de: " + credential.getUserName());
            } catch (SQLException ex) {
                e.printStackTrace();
                throw new ServerException("Error en rollback de la transaccion para comprar el anuncio de texto de: " + credential.getUserName());
            }
        }
    }

    public void buyAd(VideoAd ad, Credential credential, double change, double cost) throws ServerException {
        VideoAdDBConnection adDBConnection = new VideoAdDBConnection();
        getConnection();
        try {
            connection.setAutoCommit(false);
            walletDBConnection.updateMoney(credential, change, connection);
            adDBConnection.saveVideoAd(ad, credential, connection);
            globalCostDBConnection.newIncome(cost, connection);
            connection.commit();
            connection.setAutoCommit(true);
        } catch (SQLException e) {
            e.printStackTrace();
            try {
                connection.rollback();
                throw new ServerException("Error en la transaccion para comprar el anuncio de texto de: " + credential.getUserName());
            } catch (SQLException ex) {
                e.printStackTrace();
                throw new ServerException("Error en rollback de la transaccion para comprar el anuncio de texto de: " + credential.getUserName());
            }
        }
    }

    public void buyAd(ImageAd ad, Credential credential, double change, double cost) throws ServerException {
        ImageAdDBConnection adDBConnection = new ImageAdDBConnection();
        getConnection();
        try {
            connection.setAutoCommit(false);
            walletDBConnection.updateMoney(credential, change, connection);
            adDBConnection.saveImageAd(ad, credential, connection);
            globalCostDBConnection.newIncome(cost, connection);
            connection.commit();
            connection.setAutoCommit(true);
        } catch (SQLException e) {
            e.printStackTrace();
            try {
                connection.rollback();
                throw new ServerException("Error en la transaccion para comprar el anuncio de texto e imagen de: " + credential.getUserName());
            } catch (SQLException ex) {
                e.printStackTrace();
                throw new ServerException("Error en rollback de la transaccion para comprar el anuncio de texto e imagen de: " + credential.getUserName());
            }
        }
    }

    public void increaseView(int id, String url) throws ServerException {
        AdDBConnection adDBConnection = new AdDBConnection();
        adViewDBConnection viewDBConnection = new adViewDBConnection();
        Connection cnl;
        try(
                 Connection cn = DBConnectionSingleton.getInstance().getConnection();
                ) {
            if (id > 0) {
                cn.setAutoCommit(false);
                adDBConnection.increaseScreenTime(id, cn);
                viewDBConnection.saveView(id, url, cn);
                cn.commit();
                cn.setAutoCommit(true);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            try {
                connection.rollback();
                throw new ServerException("Error en la transaccion para aumentar vista del anuncio: " + id);
            } catch (SQLException ex) {
                e.printStackTrace();
                throw new ServerException("Error en rollback de la transaccion para aumentar vista del anuncio:" + id);
            }
        }
    }
}
