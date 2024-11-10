/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package backend.DBconnection;

import backend.exception.ServerException;
import backend.model.dto.Advertiser;
import backend.model.dto.Filter;
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
public class AdvertiserDBConnection extends DBConnection {

    public List<Advertiser> getAdvertiserList(String userName) throws ServerException {
        System.out.println("nombre recibido "+userName);
        List<Advertiser> advertiserList = new ArrayList<>();
        String sql = " select user_name from anunciante where ( ? = 'null' or user_name = ? )";
        try (
                Connection cn = DBConnectionSingleton.getInstance().getConnection(); PreparedStatement ps = cn.prepareStatement(sql);) {

            ps.setString(1, userName);
            ps.setString(2, userName);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Advertiser advertiser = new Advertiser();
                advertiser.setUserName(rs.getString("user_name"));
                advertiserList.add(advertiser);
            }
            System.out.println("retonrnando: " + advertiserList.size());
            return advertiserList;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new ServerException("Error al cargar la lista de aunciantes");
        }
    }

    public List<Advertiser> getEffectivityAdList(Filter filter) throws ServerException {
        AdReportsDBConnection adReportsDBConnection = new AdReportsDBConnection();
        
        System.out.println("fechas: " + filter.getStartDate() + " " + filter.getEndDate());
        List<Advertiser> advertiserList = new ArrayList<>();
        String sql
                = " select distinct user_name from anunciante "
                + " join ad on anunciante_name = user_name "
                + " join ad_view on ad.id = ad_view.ad "
                + " where ( ? is null or ad_view.date >= ? ) "
                + " and     ( ? is null or ad_view.date <= ? )";

        try (Connection cn = DBConnectionSingleton.getInstance().getConnection(); PreparedStatement ps = cn.prepareStatement(sql)) {
            ps.setString(1, filter.getStartDate());
            ps.setString(2, filter.getStartDate());
            ps.setString(3, filter.getEndDate());
            ps.setString(4, filter.getEndDate());
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                Advertiser advertiser = new Advertiser();
                advertiser.setUserName(rs.getString("user_name"));
                advertiser.setAdViewList(adReportsDBConnection.getAdViewList(filter));
                advertiserList.add(advertiser);
            }
                return advertiserList;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new ServerException("Erro al cargar los anuciantes para el reporte de efectividad de anuncios");
        }
    }
}
