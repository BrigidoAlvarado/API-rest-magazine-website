/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package backend.controllers;

import backend.DBconnection.TagDBConnection;
import backend.exception.InvalidDataException;
import backend.exception.ServerException;
import java.sql.Connection;
import java.sql.SQLException;

/**
 *
 * @author brigidoalvarado
 */
public class TagController {

    private final TagDBConnection dBConnection = new TagDBConnection();

    public void saveNewTag(String[] tags, Connection connection) throws SQLException {
        System.out.println("intentando guardar los nuevos tags");
        int cont = 0;
        for (String tag : tags) {
            try {
                cont++;
                dBConnection.save(tag, connection);
                System.out.println("iteracion se guardo el nuevo tag save no." + cont);
            } catch (InvalidDataException e) {
                System.out.println("tag repetido retornando!!");
            }
        }
        System.out.println("salio del for");
    }

    public void saveTagInMagazine(String[] tags, int magazineId, Connection connection) throws SQLException, ServerException {
        System.out.println("guardando los tags en la revista");
        System.out.println("tags "+tags.length);
        int cont = 0;
        for (String tag : tags) {
            System.out.println("iniciando for id:" + magazineId);
            try {
                cont++;
                dBConnection.saveTagInMagazine(tag, magazineId, connection);
                System.out.println("se guardo el tag en revista iteracion save in no." + cont);
            } catch (InvalidDataException e) {
                System.out.println("tag repetido en revista ");
            }
        }
    }
}
