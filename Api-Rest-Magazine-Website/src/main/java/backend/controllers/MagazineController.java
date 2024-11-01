/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package backend.controllers;

import backend.DBconnection.MagazineDBConnection;
import backend.exception.InvalidDataException;
import backend.exception.ServerException;
import backend.model.dto.Magazine;
import backend.transactions.MagazineTransaction;

/**
 *
 * @author brigidoalvarado
 */
public class MagazineController {
    
    private final MagazineTransaction magazineTransaction = new MagazineTransaction();
    private final MagazineDBConnection magazineDBConnection = new MagazineDBConnection();
    
    public void saveMagazine(Magazine magazine) throws InvalidDataException, ServerException{
        magazine.validate();
        magazineTransaction.saveMagazine(magazine);
    }
    
    public void setCost(Magazine magazine) throws ServerException, InvalidDataException {
        magazine.validateCost();
        magazineDBConnection.setCost(magazine.getId(), magazine.getDailyCost());
    }
}
