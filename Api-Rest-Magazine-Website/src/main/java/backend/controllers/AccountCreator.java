/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package backend.controllers;

import backend.DBconnection.UserDBConnection;
import backend.exception.AccessException;
import backend.exception.InvalidDataException;
import backend.exception.ServerException;
import backend.model.dto.Account;
import backend.model.dto.ApiFile;
import backend.model.dto.Credential;
import backend.model.dto.Profile;

/**
 *
 * @author brigidoalvarado
 */
public class AccountCreator {

    private final Account account = new Account();
    private final UserDBConnection userDBConnection = new UserDBConnection();

    public AccountCreator(Credential credential, Profile profile, ApiFile photo) {
        account.setApiFile(photo);
        account.setCredential(credential);
        account.setProfile(profile);
    }
    
    public void createNewAccount() throws AccessException, ServerException{
            account.getCredential().validate();
            userDBConnection.saveNewAccount(account);
    }
}
