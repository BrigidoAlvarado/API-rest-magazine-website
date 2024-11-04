/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package backend.controllers;

import backend.DBconnection.AdminDBConnection;
import backend.exception.InvalidDataException;
import backend.exception.ServerException;
import backend.model.dto.Magazine;
import java.util.List;

/**
 *
 * @author brigidoalvarado
 */
public class AdminController {

    private final AdminDBConnection dBConnection = new AdminDBConnection();
    
    public List<Magazine> getMagazineList() throws ServerException{
        return dBConnection.getMagazineList();
    }
    
    public void updateDailyCost(Magazine magazine) throws ServerException, InvalidDataException{
       magazine.validateCost();
       dBConnection.updateDailyCost(magazine);
    }
}
