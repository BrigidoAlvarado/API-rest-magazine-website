/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package backend.transactions;

import backend.DBconnection.DBConnection;
import backend.DBconnection.MagazineDBConnection;
import backend.DBconnection.MagazineNumberDBConnection;
import backend.controllers.TagController;
import backend.exception.ServerException;
import backend.model.dto.Magazine;
import java.sql.SQLException;

/**
 *
 * @author brigidoalvarado
 */
public class MagazineTransaction extends DBConnection{
    
    private final MagazineDBConnection magazineDBConnection = new MagazineDBConnection();
    private final TagController tagController = new TagController();
    private final MagazineNumberDBConnection magazineNumberDBConnection = new MagazineNumberDBConnection();
    
    public void saveMagazine(Magazine magazine)throws ServerException{
        try {
            getConnection();
            connection.setAutoCommit(false);
            magazineDBConnection.save(magazine, connection);
            tagController.saveNewTag(magazine.getTags(), connection);
            tagController.saveTagInMagazine(magazine.getTags(), magazine.getId(), connection);
            magazineNumberDBConnection.saveFirstNumber(magazine.getId(), magazine.getFile(), connection);
            connection.commit();
            connection.setAutoCommit(true);
        } catch (SQLException e) {
            e.printStackTrace();
            try {
                connection.rollback();
                throw new ServerException("Error en la transaccion para guardar la nueva revista del editor: "+magazine.getEditor());
            } catch (SQLException ex) {
                e.printStackTrace();
                throw new ServerException("Error en rollback de la transaccion guardar la nueva revista publicada de: "+magazine.getEditor());
            }
        }
    }
}
