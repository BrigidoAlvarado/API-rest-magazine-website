/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package backend;

import backend.DBconnection.ProfileDBConnection;
import backend.model.dto.Credential;
import backend.model.dto.Profile;

/**
 *
 * @author brigidoalvarado
 */
public class ProfileController {
    
    private final ProfileDBConnection profileDBConnection = new ProfileDBConnection();
    
    //creo que deberia de tener un crud para el perfll aca
    public Profile getProfile(Credential credential){
        return profileDBConnection.getProfile(credential);
    }
}
