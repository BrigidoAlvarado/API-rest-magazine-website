/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package backend.controllers;

import backend.DBconnection.EditorDBConnection;
import backend.exception.InvalidDataException;
import backend.exception.ServerException;
import backend.model.Store;
import backend.model.dto.Amount;
import backend.model.dto.Credential;
import backend.model.dto.LockAd;
import backend.model.dto.Magazine;
import backend.transactions.LockAdTransaction;
import java.util.List;

/**
 *
 * @author brigidoalvarado
 */
public class EditorController {

    private final EditorDBConnection editorDBConnection = new EditorDBConnection();

    public List<Magazine> getPublishedMagazineList(Credential credential) throws ServerException {
        return editorDBConnection.getPublisedMagazineList(credential.getUserName());
    }

    public Amount buyLockAd(Credential credential, LockAd lockAd) throws ServerException, InvalidDataException {
        Amount amount = new Amount();
        LockAdTransaction transaction = new LockAdTransaction();
        Store store = new Store();
        lockAd.validate();
        double cost = store.calculateCost(lockAd);
        lockAd.setCost(cost);
        double change = store.calculateChange(credential, cost);
        transaction.buyLockAdTransaction(credential, lockAd, change, cost);
        amount.setAmount(change);
        return amount;
    }

    public void lockCommentsAndLikes(Magazine magazine) throws ServerException, InvalidDataException {
        if (magazine.getId() > 0) {
            editorDBConnection.updateCommentAndLikesStatus(magazine);
        } else {
            throw new InvalidDataException("Datos de la revista invalidos ");
        }
    }
    
    public void updateCommentsAndLikesStatus(Magazine magazine)throws ServerException, InvalidDataException {
        if (magazine.getId() > 0) {
            editorDBConnection.updateCommentAndLikesStatus(magazine);
        } else {
            throw new InvalidDataException("Datos de la revista invalidos ");
        }
    }
    
    public void updateSubscriptionStatus(Magazine magazine)throws ServerException, InvalidDataException {
        if (magazine.getId() > 0) {
            editorDBConnection.updateSubscriptionStatus(magazine);
        } else {
            throw new InvalidDataException("Datos de la revista invalidos ");
        }
    }
}
