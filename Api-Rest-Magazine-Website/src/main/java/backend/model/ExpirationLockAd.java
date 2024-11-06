/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package backend.model;

import backend.DBconnection.EditorDBConnection;
import backend.exception.ServerException;
import java.util.List;

/**
 *
 * @author brigidoalvarado
 */
public class ExpirationLockAd {

    private final EditorDBConnection dBConnection = new EditorDBConnection();
    
    private void expireLockAds() throws ServerException{
        List<Integer> idList = dBConnection.getIdExpireLockAds();
        for (Integer integer : idList) {
            dBConnection.setExpireLockAd(integer);
        }
    }
    
    public boolean existLokAd(String userName) throws ServerException{
        expireLockAds();
        return dBConnection.hasLockAd(userName);
    }
}
