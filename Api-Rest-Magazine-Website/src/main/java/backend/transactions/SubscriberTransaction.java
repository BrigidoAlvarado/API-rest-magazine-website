/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package backend.transactions;

import backend.DBconnection.DBConnection;
import backend.DBconnection.MagazineDBConnection;
import backend.DBconnection.SubscriberDBConnection;
import backend.exception.InvalidDataException;
import backend.exception.ServerException;
import java.sql.SQLException;

/**
 *
 * @author brigidoalvarado
 */
public class SubscriberTransaction extends DBConnection{
    
    private final SubscriberDBConnection subscriberDBConnection = new SubscriberDBConnection();
    private final MagazineDBConnection magazineDBConnection = new MagazineDBConnection();
    
    public void likeMagazine(int id, String userName ) throws ServerException, InvalidDataException {
        try {
            getConnection();
            connection.setAutoCommit(false);
            subscriberDBConnection.saveLike(id, userName, connection);
            magazineDBConnection.increaseLikes(id, connection);
            connection.commit();
            connection.setAutoCommit(true);
        }  catch (SQLException e) {
            e.printStackTrace();
            try {
                connection.rollback();
                throw new ServerException("Error en la transaccion para dar me gusta a la revista conid : "+id);
            } catch (SQLException ex) {
                e.printStackTrace();
                throw new ServerException("Error en rollback de la transaccion para dar me gusta a la revista con id : "+id);
            }
        }
    }
}
