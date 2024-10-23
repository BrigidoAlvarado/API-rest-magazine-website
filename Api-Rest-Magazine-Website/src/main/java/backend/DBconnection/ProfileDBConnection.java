/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package backend.DBconnection;

import backend.exception.ServerException;
import backend.model.dto.ApiFile;
import backend.model.dto.Credential;
import backend.model.dto.Profile;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author brigidoalvarado
 */
public class ProfileDBConnection extends DBConnection{
    
    public Profile getProfile(Credential credential) throws ServerException{
        Profile profile = null;
        String sqlSelect = "select * from " + credential.getUserType().name() + " where user_name = ? ";
        try {
            getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sqlSelect);
            preparedStatement.setString(1, credential.getUserName());
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                profile = new Profile();
                profile.setUserName(resultSet.getString("user_name"));
                profile.setTastes(resultSet.getString("tastes"));
                profile.setTopicOfInterest(resultSet.getString("topic_of_interest"));
                profile.setDescription(resultSet.getString("description"));
                profile.setHobbies(resultSet.getString("hobbies"));
            }
            if (profile == null) {
                throw new ServerException("El perfil del usuario: "+credential.getUserName()+" no existe en la base de datos");
            } else {
                return profile;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new ServerException("Error al cargar el perfil del usuario: "+credential.getUserName());
        }
    }
    
    public void editProfile(Credential credential, Profile profile, ApiFile photo) throws ServerException{
        String sqlUpdate = 
                "update " +credential.getUserType().name()+ 
                " set tastes = ? , "                   //1
                + " photo = ? , "                       //2
                + " topic_of_interest = ? , "     //3
                + " description = ? , "             //4
                + " hobbies = ? , "                   //5
                + " photo_content_type = ? "  //6
                + " where user_name = ?";     //7
        try {
            getConnection();
            PreparedStatement ps = connection.prepareStatement(sqlUpdate);
            ps.setString(1, profile.getTastes());
            ps.setBlob(  2, photo.getInputStream());
            ps.setString(3, profile.getTopicOfInterest());
            ps.setString(4, profile.getDescription());
            ps.setString(5, profile.getHobbies());
            ps.setString(6, photo.getContentType());
            ps.setString(7, credential.getUserName());
            ps.executeUpdate();
        }  catch (SQLException e) {
            e.printStackTrace();
            throw new ServerException("error al acutalizar el perfil en la base de datos");
        }
    }
    
    public void editProfile(Credential credential, Profile profile) throws ServerException{
        String sqlUpdate =
                " update " + credential.getUserType().name() + " set tastes = ?, topic_of_interest = ? ,  description = ? , hobbies = ?  where user_name = ?";   

        try {
            getConnection();
            PreparedStatement ps = connection.prepareStatement(sqlUpdate);
            ps.setString(1, profile.getTastes());
            ps.setString(2, profile.getTopicOfInterest());
            ps.setString(3, profile.getDescription());
            ps.setString(4, profile.getHobbies());
            ps.setString(5, credential.getUserName());
            ps.executeUpdate();
        }  catch (SQLException e) {
            e.printStackTrace();
            throw new ServerException("error al acutalizar el perfil en la base de datos");
        }
    }
}
