/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package backend.controllers;

import backend.DBconnection.MagazineDBConnection;
import backend.exception.ServerException;
import backend.model.dto.Magazine;
import java.util.List;

/**
 *
 * @author brigidoalvarado
 */
public class MagazinesController {
    
    private final MagazineDBConnection magazineDBConnection  = new MagazineDBConnection();
    
    public List<Magazine> getNewPosts() throws ServerException{
        return magazineDBConnection.getNewPosts();
    }
}
