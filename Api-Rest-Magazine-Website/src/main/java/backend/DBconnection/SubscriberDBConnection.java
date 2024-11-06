/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package backend.DBconnection;

import backend.exception.InvalidDataException;
import backend.exception.ServerException;
import backend.model.dto.Magazine;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author brigidoalvarado
 */
public class SubscriberDBConnection extends DBConnection {

    public List<Magazine> getExplorerMagazines(String categoryFilter, String tagFilter, String userName) throws ServerException {

        List<Magazine> magazineList = new ArrayList<>();

        String sqlSelect
                = " select distinct magazine.* from magazine join associated_tag on magazine_id = id "
                + " where ( '' = ? or tag_name = ? ) "
                + " and ( '' = ? or category = ? ) "
                + " and ( daily_cost > 0 ) "
                + " and ( id not in "
                + " ( select magazine_id from subscribed_magazine where subscriber_user_name = ? ))";
        System.out.println(sqlSelect);
        System.out.println(userName);
        try {
            getConnection();
            PreparedStatement ps = connection.prepareStatement(sqlSelect);
            ps.setString(1, tagFilter);
            ps.setString(2, tagFilter);
            ps.setString(3, categoryFilter);
            ps.setString(4, categoryFilter);
            ps.setString(5, userName);
            ResultSet resultSet = ps.executeQuery();
            while (resultSet.next()) {
                Magazine magazine = new Magazine();
                magazine.setId(resultSet.getInt("id"));
                magazine.setSubscriptionStatus(resultSet.getBoolean("subscription_status"));
                magazine.setTittle(resultSet.getString("tittle"));
                magazine.setEditor(resultSet.getString("editor_user_name"));
                magazineList.add(magazine);
                System.out.println("se econtro la revista: " + magazine.getTittle());
            }
            return magazineList;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new ServerException("Error al cargar las revistas a las que se puede suscribir el usuario de la base de datos");
        }
    }

    public void subscribe(Magazine magazine, String userName) throws ServerException {
        String sqlInsert = "insert into subscribed_magazine (subscriber_user_name, magazine_id, date) values (?, ?, ?)";
        try {
            getConnection();
            PreparedStatement ps = connection.prepareStatement(sqlInsert);
            ps.setString(1, userName);
            ps.setInt(2, magazine.getId());
            ps.setString(3, magazine.getDate().toString());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new ServerException("error al guardar la subscripcion en la base de datos");
        }
    }

    public boolean isSuscriptionActive(int id) throws ServerException, InvalidDataException {
        String sql = " select subscription_status from magazine where id = ? ";
        try {
            getConnection();
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getBoolean("subscription_status");
            }
            throw new InvalidDataException("suscripciones desactivadas para la revista con id:" + id);
        } catch (SQLException e) {
            throw new ServerException("Error al obtener el estado de suscripcion de la revista con id:" + id);
        }
    }

    public List<Magazine> getSubscriberMagazines(String userName) throws ServerException {
        List<Magazine> magazineList = new ArrayList<>();
        String sqlSelect
                = "select id, tittle  from magazine join subscribed_magazine on id = magazine_id where subscriber_user_name = ?";
        try {
            getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sqlSelect);
            preparedStatement.setString(1, userName);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Magazine magazine = new Magazine();
                magazine.setId(resultSet.getInt("id"));
                magazine.setTittle(resultSet.getString("tittle"));
                magazineList.add(magazine);
            }
            return magazineList;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new ServerException("error al cargar las revistas suscritas de: " + userName);
        }
    }

    public Magazine getSuscribedMagazineById(int id) throws ServerException {
        FileDBConnection dBConnection = new FileDBConnection();
        Magazine magazine = new Magazine();
        String sql = "select  magazine.* , is_liked  from magazine join subscribed_magazine on id = magazine_id where id = ?";
        try {
            getConnection();
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                magazine.setCommentStatus(rs.getBoolean("comment_status"));
                magazine.setEditor(rs.getString("editor_user_name"));
                magazine.setId(rs.getInt("id"));
                magazine.setSubscriptionStatus(rs.getBoolean("subscription_status"));
                magazine.setTittle(rs.getString("tittle"));
                magazine.setIsLiked(rs.getBoolean("is_liked"));
            }
            magazine.setIdFilesList(dBConnection.getIdFiles(id));
            return magazine;
        } catch (SQLException e) {
            throw new ServerException("error al cargar la informacion de la revista suscrita con id: " + id);
        }
    }

    public boolean isCommentActive(int id) throws ServerException, InvalidDataException {
        String sql = " select comment_status from magazine where id = ? ";
        try {
            getConnection();
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getBoolean("comment_status");
            }
            throw new InvalidDataException("comentrarios desactivadas para la revista con id:" + id);
        } catch (SQLException e) {
            throw new ServerException("Error al obtener el estado de suscripcion de la revista con id:" + id);
        }
    }
    
    public void comment(int id, String comment, String userName) throws ServerException{
        String sql = 
                "INSERT INTO comment ( magazine_id, subscriber_user_name, content ) VALUES ( ? , ? , ?)";
        try {
            getConnection();
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, id);
            ps.setString(2, userName);
            ps.setString(3, comment);
            ps.executeUpdate();
        } catch (Exception e) {
        }
    }

    public void saveLike( int id, String userName, Connection connection) throws SQLException{
        String sql = 
                " UPDATE subscribed_magazine SET is_liked = true WHERE ( subscriber_user_name = ? ) and ( magazine_id = ? );";
        SetConnection(connection);
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setString(1, userName);
        ps.setInt(2, id);
        ps.executeUpdate();
        System.out.println("se is_liked = true");
    }
} 
