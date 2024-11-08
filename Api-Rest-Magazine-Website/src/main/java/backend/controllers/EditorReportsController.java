/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package backend.controllers;

import backend.DBconnection.EditorDBConnection;
import backend.exception.InvalidDataException;
import backend.exception.ServerException;
import backend.model.dto.Filter;
import backend.model.dto.LockAd;
import java.util.List;

/**
 *
 * @author brigidoalvarado
 */
public class EditorReportsController {

    public List<LockAd> getPaymentReport( Filter filter, String userName) throws ServerException, InvalidDataException{
        EditorDBConnection editorDBConnection = new EditorDBConnection();
        filter.datesValidate();
        return editorDBConnection.getLockAdsBougth(filter, userName);
    }
}
