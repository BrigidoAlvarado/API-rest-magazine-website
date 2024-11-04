/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package backend.DBconnection;

import backend.exception.ServerException;
import backend.model.dto.Advertiser;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author brigidoalvarado
 */
public class AdvertiserDBConnection extends DBConnection{
    
    public List<Advertiser> getAdvertiserList(String userName) throws ServerException {
        List<Advertiser> advertiserList = new ArrayList<>();
        System.out.println(userName);
        String sql = " select user_name from anunciante where ( ? = 'null' or user_name = ? )";
        
        try {
            getConnection();
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, userName);
            ps.setString(2, userName);
            ResultSet rs = ps.executeQuery();
            while( rs.next()) {
                Advertiser advertiser = new Advertiser();
                advertiser.setUserName(rs.getString("user_name"));
                advertiserList.add(advertiser);
            }
            System.out.println("retonrnando: "+advertiserList.size());
            return advertiserList;
        } catch (SQLException e) {
            e.printStackTrace();
            throw  new ServerException("Error al cargar la lista de aunciantes");
        }
    }
}
