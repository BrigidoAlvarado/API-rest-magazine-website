/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package backend.DBconnection;

import backend.exception.ServerException;
import backend.model.dto.LockAd;
import backend.model.dto.Magazine;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author brigidoalvarado
 */
public class EditorDBConnection extends DBConnection {

    public List<Magazine> getPublisedMagazineList(String userName) throws ServerException{
        List<Magazine> list = new ArrayList<>();
        String sql = 
                "SELECT id, tittle, comment_status, subscription_status FROM magazine WHERE ( editor_user_name = ? )";
        try {
            getConnection();
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, userName);
            ResultSet rs = ps.executeQuery();
            
            while(rs.next()){
                Magazine magazine = new Magazine();
                magazine.setId(rs.getInt("id"));
                magazine.setTittle(rs.getString("tittle"));
                magazine.setEditor(userName);
                magazine.setCommentStatus(rs.getBoolean("comment_status"));
                magazine.setSubscriptionStatus(rs.getBoolean("subscription_status"));
                list.add(magazine);
            }
            return list;
        } catch (SQLException e) {
            throw new ServerException("Error al cargar las revista publicadas de: "+userName);
        }
    }
    
    public void saveLockAd (LockAd lockAd, String userName, Connection connection) throws SQLException {
        String sql = "INSERT INTO lock_ad  (editor, days, date, buy_date ) VALUES ( ? , ?, ?, ? )";
        SetConnection(connection);
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setString(1, userName);
        ps.setInt(2, lockAd.getDays());
        ps.setString(3, lockAd.getDate().toString());
        ps.setString(4, LocalDate.now().toString());
        ps.executeUpdate();
    }
    
    public void updateCommentAndLikesStatus(Magazine magazine) throws ServerException{
        String sql = " UPDATE magazine SET comment_status = ?  WHERE ( id = ? )";
        try {
            getConnection();
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setBoolean(1, magazine.isCommentStatus());
            ps.setInt(2, magazine.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new ServerException("Error al cambiar el estado de comentarios y me gusta de la revista con id: "+magazine.getId());
        }
    }
    
    public void updateSubscriptionStatus(Magazine magazine) throws ServerException{
        String sql = " UPDATE magazine SET subscription_status = ?  WHERE ( id = ? )";
        try {
            getConnection();
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setBoolean(1, magazine.isSubscriptionStatus());
            ps.setInt(2, magazine.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new ServerException("Error al cambiar el estado las suscripciones de la revista con id: "+magazine.getId());
        }
    }
}
