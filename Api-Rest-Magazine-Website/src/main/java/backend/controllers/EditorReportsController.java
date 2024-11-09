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
import backend.model.dto.Magazine;
import java.util.List;

/**
 *
 * @author brigidoalvarado
 */
public class EditorReportsController {
    
    private final EditorDBConnection editorDBConnection = new EditorDBConnection();

    public List<LockAd> getPaymentReport( Filter filter, String userName) throws ServerException, InvalidDataException{
        filter.datesValidate();
        return editorDBConnection.getLockAdsBougth(filter, userName);
    }
    
    public List<Magazine> getSubscriptionMagazines(Filter filter, String userName) throws ServerException, InvalidDataException{
        filter.datesValidate();
        return editorDBConnection.getSubscriptionMagazines(filter, userName);
    }
    
    public List<Magazine> getCommentReports(Filter filter, String userName) throws ServerException, InvalidDataException {
        filter.datesValidate();
        return editorDBConnection.getCommentsReports(filter, userName);
    }
}
