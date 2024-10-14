/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package backend.DBconnection;

import backend.exception.ServerException;
import backend.model.dto.Account;
import backend.model.dto.ApiFile;
import backend.model.dto.Credential;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;

/**
 *
 * @author brigidoalvarado
 */
public class UserDBConnection extends DataBaseConnection {
    
    public void saveNewAccount(Account account, ApiFile photo) throws ServerException{
         String sql = "insert into " + account.getUserType().name()
                + " (user_name, password, tastes, photo, topic_of_interest, description, hobbies, photo_content_type)"
                + " values ( ? , ?, ?, ?, ?, ?, ?, ?, ?)";
        try {
            connection = DBConnection.getInstance().getConnection();
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, account.getUserName());
            ps.setString(2, account.getPassword());
            ps.setString(3, account.getTastes());
            ps.setBlob   (4, photo.getInputStream());
            ps.setString(5, account.getTopicOfInterest());
            ps.setString(6, account.getDescription());
            ps.setString(7, account.getHobbies());
            ps.setString(8, photo.getContentType());
            ps.executeUpdate();
        } catch (SQLIntegrityConstraintViolationException e) {
            e.printStackTrace();
            throw new  ServerException("El nombre de usuario: \"" + account.getUserName() + "\" es invalido");
        } catch (SQLException e) {
            e.printStackTrace();
            throw new ServerException("error al guardar en la base de datos");
        }
    }
    
    public Credential validateLogin(Credential credential)throws ServerException{

        String query = "select count(1) from " + credential.getUserType().name() + " where user_name = ? and password = ?";
        try {
            connection = DBConnection.getInstance().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, credential.getUserName());
            preparedStatement.setString(2, credential.getPassword());

            ResultSet resultSet = preparedStatement.executeQuery();
           
            if (resultSet.next() && resultSet.getInt(1) > 0) {
               return credential;
            }  else {
               throw new ServerException("El nombre de usuario o la contrasña es invalido");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new ServerException("error al cargar desde la base de datos");
        }
    }
    
}