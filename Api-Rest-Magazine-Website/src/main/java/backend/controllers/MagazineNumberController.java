/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package backend.controllers;

import backend.DBconnection.MagazineNumberDBConnection;
import backend.exception.InvalidDataException;
import backend.exception.ServerException;
import backend.model.dto.ApiFile;

/**
 *
 * @author brigidoalvarado
 */
public class MagazineNumberController {
    
    private final MagazineNumberDBConnection dBConnection = new MagazineNumberDBConnection();
    
    public void newNumber(int id, ApiFile file) throws ServerException, InvalidDataException {
        file.validate();
        if (id < 0) {
            throw  new InvalidDataException("El id enviado es invalido");
        } else {
            dBConnection.saveNumber(id, file);
        }
    }
}
