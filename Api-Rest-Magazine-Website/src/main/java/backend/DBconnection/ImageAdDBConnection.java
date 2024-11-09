/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package backend.DBconnection;

import backend.enums.Global;
import backend.exception.ServerException;
import backend.model.dto.Credential;
import backend.model.dto.ImageAd;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 * @author brigidoalvarado
 */
public class ImageAdDBConnection extends DBConnection {

    public void saveImageAd(ImageAd ad, Credential credential, Connection connection) throws SQLException {
        String sql = "INSERT INTO ad (kind, state, anunciante_name, date, text, cost, time, image, content_type) VALUES ( ?, true, ?, ?, ?, ?, ?, ?, ? )";

        SetConnection(connection);
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setString(1, Global.textImageAd.name());
        ps.setString(2, credential.getUserName());
        ps.setString(3, ad.getDate().toString());
        ps.setString(4, ad.getText());
        ps.setDouble(5, ad.getCost());
        ps.setInt(6, ad.getAdTime().getDay());
        ps.setBlob(7, ad.getFile().getInputStream());
        ps.setString(8, ad.getFile().getContentType());
        ps.executeUpdate();

    }

    public void updateImageAd(ImageAd ad) throws ServerException {
        String sql
                = " UPDATE ad  SET   content_type = ? , text = ?, image  = ?  WHERE  ( id = ? )";
        try (
                Connection cn = DBConnectionSingleton.getInstance().getConnection(); PreparedStatement ps = cn.prepareStatement(sql);) {
            ps.setString(1, ad.getFile().getContentType());
            ps.setString(2, ad.getText());
            ps.setBlob(3, ad.getFile().getInputStream());
            ps.setInt(4, ad.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new ServerException("Error al actualizar los datos del anuncio con de imagen con texto con id: "+ad.getId()); 
        }
    }
}
