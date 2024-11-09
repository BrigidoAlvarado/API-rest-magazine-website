/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package backend.DBconnection;

import backend.exception.ServerException;
import backend.model.dto.Account;
import backend.model.dto.Credential;
import backend.model.dto.Filter;
import backend.model.dto.Profile;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author brigidoalvarado
 */
public class UserDBConnection extends DBConnection {

    public void saveNewAccount(Account account) throws ServerException {
        String sql = "insert into " + account.getCredential().getUserType().name()
                + " (user_name, password, tastes, photo, topic_of_interest, description, hobbies, photo_content_type)"
                + " values ( ? , ?, ?, ?, ?, ?, ?, ?)";
        try (
                Connection cn = DBConnectionSingleton.getInstance().getConnection(); PreparedStatement ps = cn.prepareStatement(sql);) {

            ps.setString(1, account.getCredential().getUserName());
            ps.setString(2, account.getCredential().getPassword());
            ps.setString(3, account.getProfile().getTastes());
            ps.setBlob(4, account.getApiFile().getInputStream());
            ps.setString(5, account.getProfile().getTopicOfInterest());
            ps.setString(6, account.getProfile().getDescription());
            ps.setString(7, account.getProfile().getHobbies());
            ps.setString(8, account.getApiFile().getContentType());
            ps.executeUpdate();
        } catch (SQLIntegrityConstraintViolationException e) {
            e.printStackTrace();
            throw new ServerException("El nombre de usuario: " + account.getCredential().getUserName() + " es invalido");
        } catch (SQLException e) {
            e.printStackTrace();
            throw new ServerException("error al guardar en la base de datos");
        }
    }

    public Credential validateLogin(Credential credential) throws ServerException {

        String query = "select count(1) from " + credential.getUserType().name() + " where user_name = ? and password = ?";
        try (
                Connection cn = DBConnectionSingleton.getInstance().getConnection(); PreparedStatement ps = cn.prepareStatement(query);) {

            ps.setString(1, credential.getUserName());
            ps.setString(2, credential.getPassword());

            ResultSet resultSet = ps.executeQuery();

            if (resultSet.next() && resultSet.getInt(1) > 0) {
                return credential;
            } else {
                throw new ServerException("El nombre de usuario o la contrasña es invalido");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new ServerException("error al cargar desde la base de datos");
        }
    }

    public List<Profile> getSubscriberInfo(int magazineId, Filter filter) throws ServerException {

        List<Profile> profileList = new ArrayList<>();
        String sql
                = " select subscriber.* from subscriber "
                + " join subscribed_magazine on user_name = subscriber_user_name "
                + " join magazine on magazine_id = magazine.id "
                + " where ( magazine.id = ? )"
                + " and ( ? is null or ? <= subscribed_magazine.date ) "
                + " and ( ? is null or ? >= subscribed_magazine.date ) ";
        try (
                Connection cn = DBConnectionSingleton.getInstance().getConnection(); PreparedStatement ps = cn.prepareStatement(sql);) {

            ps.setInt(1, magazineId);
            ps.setString(2, filter.getStartDate());
            ps.setString(3, filter.getStartDate());
            ps.setString(4, filter.getEndDate());
            ps.setString(5, filter.getEndDate());
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Profile profile = new Profile();
                profile.setUserName(rs.getString("user_name"));
                profile.setDescription(rs.getString("description"));
                profile.setHobbies(rs.getString("hobbies"));
                profile.setTastes(rs.getString("tastes"));
                profile.setTopicOfInterest(rs.getString("topic_of_interest"));
                profileList.add(profile);
            }
            return profileList;
        } catch (SQLException e) {
            throw new ServerException("Error al cargar los usuario suscritos a la revista con id: " + magazineId);
        }
    }

    public List<Profile> getLikedSubscriber(int magazineId) throws ServerException {
        List<Profile> profileList = new ArrayList<>();
        String sql
                = " select user_name from subscribed_magazine "
                + " join subscriber on user_name = subscriber_user_name "
                + " where ( is_liked = true) "
                + " and ( magazine_id = ? )";
        try (Connection cn = DBConnectionSingleton.getInstance().getConnection(); PreparedStatement ps = cn.prepareStatement(sql);) {
            ps.setInt(1, magazineId);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                Profile profile = new Profile();
                profile.setUserName(rs.getString("user_name"));
                profileList.add(profile);
            }
            System.out.println("retornando un  lista de :"+profileList.size()+" con la revista de id: "+magazineId);
            return profileList;
        } catch (SQLException e) {
            throw new ServerException("Error al cargar los suscriptores que le han dado me gusta a la revista con id: "+magazineId);
        }
    }
    
    public List<Profile> getSubscriberName(Filter filter, int magazineId) throws  ServerException {
        List<Profile> profileList = new ArrayList<>();
        String sql
                = " select user_name from subscribed_magazine "
                + " join subscriber on user_name = subscriber_user_name "
                + " where  ( magazine_id = ? )"
                + " and ( ? is null or ? <= subscribed_magazine.date ) "
                + " and ( ? is null or ? >= subscribed_magazine.date ) ";
        try (Connection cn = DBConnectionSingleton.getInstance().getConnection(); PreparedStatement ps = cn.prepareStatement(sql);) {
            ps.setInt(1, magazineId);
            ps.setString(2, filter.getStartDate());
            ps.setString(3, filter.getStartDate());
            ps.setString(4, filter.getEndDate());
            ps.setString(5, filter.getEndDate());
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                Profile profile = new Profile();
                profile.setUserName(rs.getString("user_name"));
                profileList.add(profile);
            }
            return profileList;
        } catch (SQLException e) {
            throw new ServerException("Error al cargar los suscriptores que le han dado me gusta a la revista con id: "+magazineId);
        }
    }
}
