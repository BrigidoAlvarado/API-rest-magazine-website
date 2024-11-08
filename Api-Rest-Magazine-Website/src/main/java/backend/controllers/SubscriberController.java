/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package backend.controllers;

import backend.DBconnection.SubscriberDBConnection;
import backend.exception.InvalidDataException;
import backend.exception.ServerException;
import backend.model.dto.Magazine;
import backend.transactions.SubscriberTransaction;
import java.time.LocalDate;
import java.util.List;

/**
 *
 * @author brigidoalvarado
 */
public class SubscriberController {

    private final SubscriberDBConnection dBConnection = new SubscriberDBConnection();

    public List<Magazine> getMagazinesExplorer(String tag, String category, String userName) throws ServerException, InvalidDataException {

        if (category == null || tag == null) {
            throw new InvalidDataException("Los filtros ingresados son invalidos");
        }
        if (category.equals("void")) {
            category = "";
        }
        if (tag.equals("void")) {
            tag = "";
        }
        return dBConnection.getExplorerMagazines(category, tag, userName);
    }

    public void subscribe(Magazine magazine, String userName) throws ServerException, InvalidDataException {
        magazine.validateSubscription();
        if (dBConnection.isSuscriptionActive(magazine.getId())) {
            dBConnection.subscribe(magazine, userName);
        }
    }

    public List<Magazine> getSubscribedMagazines(String userName) throws ServerException{
        return dBConnection.getSubscriberMagazines(userName);
    }
    
    public Magazine getSubscribedMagazine(int id) throws ServerException{
        return dBConnection.getSuscribedMagazineById(id);
    }
    
    public void commentMagazine(int id, String comment, String userName, LocalDate date) throws ServerException, InvalidDataException{
        if (dBConnection.isCommentActive(id) && comment != null && id > 0) {
            dBConnection.comment(id, comment, userName, date);
        } else {
            throw new InvalidDataException("Datos para realizar el comentario invalidos");
        }
    }
    
    public void likeMagazine(int id, String userName) throws  ServerException, InvalidDataException{
        SubscriberTransaction transaction = new SubscriberTransaction();
        if (dBConnection.isCommentActive(id) || id > 0) {
            transaction.likeMagazine(id, userName);
        }else {
            throw new InvalidDataException("El id de la revista a la que se la ha dado like es invalido");
        }
    }
}
