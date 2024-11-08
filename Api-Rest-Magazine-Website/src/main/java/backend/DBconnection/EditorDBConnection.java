/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package backend.DBconnection;

import backend.exception.ServerException;
import backend.model.dto.Filter;
import backend.model.dto.LockAd;
import backend.model.dto.Magazine;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author brigidoalvarado
 */
public class EditorDBConnection extends DBConnection {

    public List<Magazine> getPublisedMagazineList(String userName) throws ServerException {
        List<Magazine> list = new ArrayList<>();
        String sql
                = "SELECT id, tittle, comment_status, subscription_status FROM magazine WHERE ( editor_user_name = ? )";
        try (
                Connection cn = DBConnectionSingleton.getInstance().getConnection(); PreparedStatement ps = cn.prepareStatement(sql);) {

            ps.setString(1, userName);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
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
            throw new ServerException("Error al cargar las revista publicadas de: " + userName);
        }
    }

    public void saveLockAd(LockAd lockAd, String userName, Connection connection) throws SQLException {
        String sql = "INSERT INTO lock_ad  (editor, days, date, cost ) VALUES ( ? , ?, ?, ? )";
        SetConnection(connection);
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setString(1, userName);
        ps.setInt(2, lockAd.getDays());
        ps.setString(3, lockAd.getDate().toString());
        ps.setDouble(4, lockAd.getCost());
        ps.executeUpdate();
    }

    public void updateCommentAndLikesStatus(Magazine magazine) throws ServerException {
        String sql = " UPDATE magazine SET comment_status = ?  WHERE ( id = ? )";
        try (
                Connection cn = DBConnectionSingleton.getInstance().getConnection(); PreparedStatement ps = cn.prepareStatement(sql);) {

            ps.setBoolean(1, magazine.isCommentStatus());
            ps.setInt(2, magazine.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new ServerException("Error al cambiar el estado de comentarios y me gusta de la revista con id: " + magazine.getId());
        }
    }

    public void updateSubscriptionStatus(Magazine magazine) throws ServerException {
        String sql = " UPDATE magazine SET subscription_status = ?  WHERE ( id = ? )";
        try (
                Connection cn = DBConnectionSingleton.getInstance().getConnection(); PreparedStatement ps = cn.prepareStatement(sql);) {

            ps.setBoolean(1, magazine.isSubscriptionStatus());
            ps.setInt(2, magazine.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new ServerException("Error al cambiar el estado las suscripciones de la revista con id: " + magazine.getId());
        }
    }

    public List<Integer> getIdExpireLockAds() throws ServerException {
        List<Integer> idList = new ArrayList<>();
        String sql
                = " select id from lock_ad where ( datediff( curdate(), date ) > days );";
        try (
                Connection cn = DBConnectionSingleton.getInstance().getConnection(); Statement st = cn.prepareStatement(sql);) {

            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                idList.add(rs.getInt("id"));
            }
            return idList;
        } catch (SQLException e) {
            throw new ServerException("Error al cargar el id de todos los lock_ad expirados");
        }
    }

    public void setExpireLockAd(int id) throws ServerException {
        String sql
                = "update lock_ad set expire_status = false where id = ?";
        try (
                Connection cn = DBConnectionSingleton.getInstance().getConnection(); PreparedStatement ps = cn.prepareStatement(sql);) {

            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new ServerException("Error al actualizar el estado de los lock_ad expirados");
        }
    }

    public boolean hasLockAd(String userName) throws ServerException {
        String sql = "select expire_status from lock_ad where ( editor = ? ) and ( expire_status = false )";
        try (
                Connection cn = DBConnectionSingleton.getInstance().getConnection(); PreparedStatement ps = cn.prepareStatement(sql);) {

            ps.setString(1, userName);
            ResultSet rs = ps.executeQuery();
            return rs.next();
        } catch (SQLException e) {
            throw new ServerException("Error al validar si existen bloqueos de anuncio para el editor: " + userName);
        }
    }

    public List<LockAd> getLockAdsBougth(Filter filter, String userName) throws ServerException {
        List<LockAd> lockAdList = new ArrayList<>();
        String sql
                = " select * from lock_ad "
                + " where (editor = ? ) "
                + " and ( ? is null or date >= ? ) "
                + " and ( ? is null or date <= ? ) ";
        try (Connection cn = DBConnectionSingleton.getInstance().getConnection(); PreparedStatement ps = cn.prepareStatement(sql);) {
            ps.setString(1, userName);
            ps.setString(2, filter.getStartDate());
            ps.setString(3, filter.getStartDate());
            ps.setString(4, filter.getEndDate());
            ps.setString(5, filter.getEndDate());
            ResultSet rs = ps.executeQuery();
            while( rs.next() ){
                LockAd lockAd = new LockAd();
                lockAd.setDate(LocalDate.parse(rs.getString("date")));
                lockAd.setDays(rs.getInt("days"));
                lockAd.setCost(rs.getDouble("cost"));
                lockAdList.add(lockAd);
            }
            return lockAdList;
        } catch (SQLException e) {
            throw new ServerException("Error al obtener los bloqueos de anuncios comprados por el editor: "+userName);
        }
    }
}
