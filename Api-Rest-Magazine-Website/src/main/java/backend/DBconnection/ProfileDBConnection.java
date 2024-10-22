/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package backend.DBconnection;

import backend.model.dto.Credential;
import backend.model.dto.Profile;

/**
 *
 * @author brigidoalvarado
 */
public class ProfileDBConnection extends DBConnection{
    
    public Profile getProfile(Credential credential){
        Profile profile = new Profile();
        return profile;
    }
}
