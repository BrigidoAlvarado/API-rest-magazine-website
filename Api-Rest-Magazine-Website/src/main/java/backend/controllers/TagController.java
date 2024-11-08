/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package backend.controllers;

import backend.DBconnection.TagDBConnection;
import backend.exception.ServerException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;

/**
 *
 * @author brigidoalvarado
 */
public class TagController {
    
    private final TagDBConnection dBConnection = new TagDBConnection();
    
    public void saveNewTag(String[] tags, Connection connection) throws SQLException{
        for (String tag : tags) {
            try {
                dBConnection.save(tag, connection);
            } catch (SQLIntegrityConstraintViolationException e) {
                return;
            }
        }
    }
    
    public void saveTagInMagazine(String [] tags, int magazineId, Connection connection) throws SQLException, ServerException{
        for (String tag : tags) {
            try {
                dBConnection.saveTagInMagazine(tag, magazineId, connection);
            } catch (SQLIntegrityConstraintViolationException e) {
                return;
            }
        }
    }
}
