/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package backend.controllers;

import backend.DBconnection.GlobalDBConnection;
import backend.enums.Global;
import backend.exception.InvalidDataException;
import backend.exception.ServerException;

/**
 *
 * @author brigidoalvarado
 */
public class GlobalCostController {

    private final GlobalDBConnection dBConnection = new GlobalDBConnection();
    
    public void updateCost(Global kind, double amount) throws ServerException, InvalidDataException {
        if (amount > 0) {
            dBConnection.updateGlobalCost(kind, amount);
        } else {
            throw new InvalidDataException("Costo de la acutalizacion de costo globales invalido para: "+kind);
        }
    }
}
