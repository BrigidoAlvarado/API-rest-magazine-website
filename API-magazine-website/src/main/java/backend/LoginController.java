/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package backend;

import backend.DBconnection.UserDBConnection;
import backend.exception.InvalidDataException;
import backend.model.dto.Credential;

/**
 *
 * @author brigidoalvarado
 */
public class LoginController {
    private Credential credential = new Credential();
    private UserDBConnection userDBConnection = new UserDBConnection();
    
    public void validateLogin()throws InvalidDataException{
        credential.validate();
        
    }
}
