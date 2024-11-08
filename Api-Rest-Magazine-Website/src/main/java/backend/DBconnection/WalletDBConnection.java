/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package backend.DBconnection;

import backend.exception.ServerException;
import backend.model.dto.Amount;
import backend.model.dto.Credential;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author brigidoalvarado
 */
public class WalletDBConnection extends DBConnection  {
    
    public double getMoney(Credential credential) throws ServerException{
        String sqlSelect = 
                "SELECT money FROM "+credential.getUserType().name()+"  WHERE ( user_name  =  ? )";
        
        try (
                Connection cn = DBConnectionSingleton.getInstance().getConnection();
                PreparedStatement ps = cn.prepareStatement(sqlSelect);
                ) {
            
            ps.setString(1, credential.getUserName());
            ResultSet resultSet = ps.executeQuery();
            if (resultSet.next()) {
                return resultSet.getDouble("money");
            } else {
                throw new ServerException("Erro al obtener el dinero de la cuenta");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new ServerException("Error al obtener el dinero de la cuenta de: "+ credential.getUserName());
        }
    }
    
    public void updateMoney(Credential credential, Amount amount) throws ServerException{
        String sqlUpdate = 
                "UPDATE "+credential.getUserType()+"  SET money =  ?  WHERE ( user_name  =  ? )";
        try (
                Connection cn = DBConnectionSingleton.getInstance().getConnection();
                PreparedStatement ps = cn.prepareStatement(sqlUpdate);
                ) {
            //Actualizar la cantidad de dinero
            ps.setDouble(1, amount.getAmount());
            ps.setString(2, credential.getUserName());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw  new ServerException("Erro al actualizar el dinero del usuario: "+ credential.getUserName());
        }
    }
    
    public void updateMoney(Credential credential, double amount, Connection connection) throws SQLException{
        String sqlUpdate = 
                "UPDATE "+credential.getUserType()+"  SET money =  ?  WHERE ( user_name  =  ? )";
            //Actualizar la cantidad de dinero
            PreparedStatement psUpdate = connection.prepareStatement(sqlUpdate);
            psUpdate.setDouble(1, amount);
            psUpdate.setString(2, credential.getUserName());
            psUpdate.executeUpdate();
       
    }
}
