/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package backend.DBconnection;

import backend.exception.ServerException;
import backend.model.dto.Filter;
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
public class SubscribedMagazineDBConnection extends DBConnection {

    public List<Magazine> getPopularMagazines(Filter filter) throws ServerException {
        UserDBConnection dBConnection = new UserDBConnection();
        List<Magazine> magazineList = new ArrayList<>();
        String sql
                = " select  magazine.id, tittle, editor_user_name, count( subscriber_user_name ) as total "
                + " from magazine join subscribed_magazine on id = magazine_id "
                + " where ( ? is null or subscribed_magazine.date >= ? ) "
                + " and ( ? is null or subscribed_magazine.date <= ? ) "
                + " group by  magazine.id, tittle, editor_user_name "
                + " order by total desc "
                + " limit 5 ";
        try (
                Connection cn = DBConnectionSingleton.getInstance().getConnection(); PreparedStatement ps = cn.prepareStatement(sql);) {

            ps.setString(1, filter.getStartDate());
            ps.setString(2, filter.getStartDate());
            ps.setString(3, filter.getEndDate());
            ps.setString(4, filter.getEndDate());
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Magazine magazine = new Magazine();
                magazine.setEditor(rs.getString("editor_user_name"));
                magazine.setId(rs.getInt("id"));
                magazine.setLikes(rs.getInt("total"));
                magazine.setTittle(rs.getString("tittle"));
                magazine.setSubscriberList(dBConnection.getSubscriberInfo(magazine.getId(), filter));
                magazineList.add(magazine);
            }
            return magazineList;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new ServerException("Error al obtener las 5 revistas mas populares");
        }
    }

}
