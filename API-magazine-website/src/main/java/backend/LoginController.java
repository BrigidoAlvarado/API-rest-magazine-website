/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package backend;

import backend.DBconnection.UserDBConnection;
import backend.exception.InvalidDataException;
import backend.exception.ServerException;
import backend.model.dto.Credential;

/**
 *
 * @author brigidoalvarado
 */
public class LoginController {
    private UserDBConnection userDBConnection = new UserDBConnection();
    
    public void login(Credential credential)throws InvalidDataException, ServerException{
        credential.validate();
        userDBConnection.validateLogin(credential);
    }
}
