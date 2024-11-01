/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package backend.controllers;

import backend.DBconnection.EditorDBConnection;
import backend.exception.ServerException;
import backend.model.dto.Credential;
import backend.model.dto.Magazine;
import java.util.List;

/**
 *
 * @author brigidoalvarado
 */
public class EditorController {

    private final EditorDBConnection editorDBConnection = new EditorDBConnection();
    
    public List<Magazine> getPublishedMagazineList(Credential credential) throws ServerException{
        return editorDBConnection.getPublisedMagazineList(credential.getUserName());
    }
}
