/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package backend.controllers;

import backend.DBconnection.BusinessDBConnection;
import backend.model.UserType;
import backend.exception.InvalidDataException;
import backend.exception.ServerException;
import backend.model.dto.Amount;
import backend.model.dto.Credential;
/**
 *
 * @author brigidoalvarado
 */
public class PaymentToUpController {
    
    private final BusinessDBConnection businessDBConnection = new BusinessDBConnection();
    
    public Amount toUpMoney(Credential credential, Amount amount) throws InvalidDataException, ServerException{
        if (credential.getUserType() == UserType.anunciante || credential.getUserType() == UserType.editor) {
            amount.validate();
            return businessDBConnection.toUpMoney(credential, amount);
        } else{
            throw new InvalidDataException("El rol des usuario no esta autorizado para realizar recargas");
        }
    }
}
