/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package backend.DBconnection;

import backend.enums.Global;
import backend.exception.ServerException;
import backend.model.dto.Credential;
import backend.model.dto.VideoAd;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 * @author brigidoalvarado
 */

public class VideoAdDBConnection extends DBConnection {
        
    public void saveVideoAd(VideoAd ad, Credential credential, Connection connection) throws SQLException{
        String sql = "INSERT INTO ad (kind, state, anunciante_name, date, link, text, cost, time ) VALUES ( ?, true, ?, ?, ?, ?, ?, ?)";
        
            SetConnection(connection);
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, Global.videoAd.name());
            ps.setString(2, credential.getUserName());
            ps.setString(3, ad.getDate().toString());
            ps.setString(4, ad.getLink());
            ps.setString(5, ad.getText());
            ps.setDouble(6, ad.getCost());
            ps.setInt(7, ad.getAdTime().getDay());
            ps.executeUpdate();
       
    }
}
