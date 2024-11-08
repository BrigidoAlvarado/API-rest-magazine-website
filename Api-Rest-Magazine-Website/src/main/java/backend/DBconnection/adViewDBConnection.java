/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package backend.DBconnection;

import backend.exception.ServerException;
import backend.model.dto.Filter;
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
public class adViewDBConnection extends DBConnection {

    public void saveView(int id, String url, Connection connection) throws SQLException {
        String sql = "INSERT INTO ad_view (ad, date, url) VALUES ( ?, ?, ? )";
        SetConnection(connection);
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setInt(1, id);
        ps.setString(2, LocalDate.now().toString());
        ps.setString(3, url);
        ps.executeUpdate();
    }

    public int getViews(int id, Filter filter) throws ServerException {
        int views = 0;
        String sql
                = " select count( * ) as total from ad_view where ( ad = ? ) "
                + " and ( ? is null or ad_view.date >= ? ) "
                + " and ( ? is null or ad_view.date <= ? ) ";
        try (Connection cn = DBConnectionSingleton.getInstance().getConnection(); PreparedStatement ps = cn.prepareStatement(sql);) {
            ps.setInt(1, id);
            ps.setString(2, filter.getStartDate());
            ps.setString(3, filter.getStartDate());
            ps.setString(4, filter.getEndDate());
            ps.setString(5, filter.getEndDate());
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                views = rs.getInt("total");
            }
            return views;
        } catch (SQLException e) {
            throw new ServerException("Error al obtener el numero de vistas del anuncio con id: " + id);
        }
    }

    public List<String> getUrlList(int id, Filter filter) throws ServerException {
        List<String> urllist = new ArrayList<>();
        String sql
                = " select distinct url from ad_view Where ( ad = ? ) "
                + " and ( ? is null or ad_view.date >= ? ) "
                + " and ( ? is null or ad_view.date <= ? ) ";
        try (Connection cn = DBConnectionSingleton.getInstance().getConnection(); PreparedStatement ps = cn.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.setString(2, filter.getStartDate());
            ps.setString(3, filter.getStartDate());
            ps.setString(4, filter.getEndDate());
            ps.setString(5, filter.getEndDate());
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String url = rs.getString("url");
                urllist.add(url);
            }
            return urllist;
        } catch (SQLException e) {
            throw new ServerException("Error al obtener las url en las que se mostro la el anuncio con id: " + id);
        }
    }
}
