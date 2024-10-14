/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package backend;

import backend.DBconnection.UserDBConnection;
import backend.exception.InvalidDataException;
import backend.exception.ServerException;
import backend.model.dto.Account;

/**
 *
 * @author brigidoalvarado
 */
public class AccountCreator {

    private final Account account = new Account();
    private final UserDBConnection userDBConnection = new UserDBConnection();

    public void createNewAccount() throws InvalidDataException, ServerException{
            //account.seters
            account.getCredential().validate();
            userDBConnection.saveNewAccount(account);
    }
}
