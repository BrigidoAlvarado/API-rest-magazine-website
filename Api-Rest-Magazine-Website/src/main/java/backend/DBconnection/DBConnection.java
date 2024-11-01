/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package backend.DBconnection;

import backend.exception.ServerException;
import java.sql.Connection;

/**
 *
 * @author brigidoalvarado
 */
public class DBConnection {
    protected Connection connection;
    
    protected Connection getConnection() throws ServerException{
        connection = DBConnectionSingleton.getInstance().getConnection();
        return connection;
    }
    
    protected void SetConnection(Connection connection){
        this.connection = connection;
    }
}
