/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package backend.controllers;

import backend.DBconnection.ProfileDBConnection;
import backend.exception.ServerException;
import backend.model.dto.ApiFile;
import backend.model.dto.Credential;
import backend.model.dto.Profile;

/**
 *
 * @author brigidoalvarado
 */
public class ProfileController {

    private final ProfileDBConnection profileDBConnection = new ProfileDBConnection();

    //creo que deberia de tener un crud para el perfll aca
    public Profile getProfile(Credential credential) throws ServerException {
        return profileDBConnection.getProfile(credential);
    }

    public void editProfile(Credential credential, Profile profile, ApiFile photo) throws ServerException {
        if (photo.getInputStream() == null) {
            profileDBConnection.editProfile(credential, profile);
        } else {
            profileDBConnection.editProfile(credential, profile, photo);
        }
    }
}
