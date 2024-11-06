/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package backend.DBconnection;

import backend.enums.Global;
import backend.exception.ServerException;
import backend.model.dto.Ad;
import backend.model.dto.Filter;
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
public class AdReportsDBConnection extends DBConnection {
    
    public List<Ad> getAdList(Filter filter) throws ServerException{
        List<Ad> adList = new ArrayList<>();
        String sql = "select * from ad where ( ? is null or ? <= date) and ( ? is  null or date <= ? ) and ( ? is null or kind = ? )";
        try {
            getConnection();
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, filter.getStartDate());
            ps.setString(2, filter.getStartDate());
            ps.setString(3, filter.getEndDate());
            ps.setString(4, filter.getEndDate());
            ps.setString(5, filter.getKind());
            ps.setString(6, filter.getKind());
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                Ad ad = new Ad();
                ad.setKindAd(Global.valueOf(rs.getString("kind")));
                ad.setAdvertiser(rs.getString("anunciante_name"));
                ad.setId(rs.getInt("id"));
                ad.setDate(LocalDate.parse(rs.getString("date")));
                ad.setCost(rs.getDouble("cost"));
                ad.setDays(rs.getInt("time"));
                adList.add(ad);
            }
            return adList;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new ServerException("Error al cargar los anuncios filtrados por fecha y tipo");
        }
    }
 
     public List<Ad> getAdList(String advertiser) throws ServerException{
         System.out.println("en get ad by userName");
        List<Ad> adList = new ArrayList<>();
        String sql = "select * from ad where ( ?  is null or  ? = '' or anunciante_name = ? ) ";
        try {
            getConnection();
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, advertiser);
            ps.setString(2, advertiser);
            ps.setString(3, advertiser);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                Ad ad = new Ad();
                ad.setKindAd(Global.valueOf(rs.getString("kind")));
                ad.setId(rs.getInt("id"));
                ad.setDate(LocalDate.parse(rs.getString("date")));
                ad.setCost(rs.getDouble("cost"));
                ad.setDays(rs.getInt("time"));
                adList.add(ad);
                System.out.println("cargando: "+ad.getId());
            }
            return adList;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new ServerException("Error al cargar los anuncios filtrados por fecha y tipo");
        }
    }
}
