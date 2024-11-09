/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package backend.DBconnection;

import backend.enums.Global;
import backend.exception.InvalidDataException;
import backend.exception.ServerException;
import backend.model.dto.Credential;
import backend.model.dto.TextAd;
import backend.model.dto.VideoAd;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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
    
    public VideoAd getById( int id) throws ServerException, InvalidDataException{
        String sql = "select * from ad where ( id = ? and expire_status = false )";
        try (
                Connection cn = DBConnectionSingleton.getInstance().getConnection();
                PreparedStatement ps = cn.prepareStatement(sql);
                ) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                VideoAd ad = new VideoAd();
                ad.setId(id);
                ad.setText(rs.getString("text"));
                ad.setKind(Global.valueOf(rs.getString("kind")));
                ad.setLink(rs.getString("link"));
                return ad;
            } else {
                 throw new InvalidDataException("El anuncio no con id = "+id+" no existe o ha expirado");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new ServerException("Error al obtener el anuncio de texto con id: "+id);
        }
    }
    
    public void update(VideoAd ad) throws ServerException, InvalidDataException{
        String sql = " update  ad set text  = ?, link = ?  where ( id = ?) ";
        try(
                Connection cn = DBConnectionSingleton.getInstance().getConnection();
                PreparedStatement ps = cn.prepareStatement(sql);
                )  {
            ps.setString(1, ad.getText());
            ps.setString(2, ad.getLink());
            ps.setInt(3, ad.getId());
            ps.executeUpdate();
        }catch (SQLException e) {
            e.printStackTrace();
            throw new ServerException("Error al actualizar el anucio de texto con id = "+ad.getId());
        }
    }
}
