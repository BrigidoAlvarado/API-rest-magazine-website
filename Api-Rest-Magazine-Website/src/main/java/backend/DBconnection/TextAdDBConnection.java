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
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author brigidoalvarado
 */
public class TextAdDBConnection extends DBConnection {
   
    public void saveTextAd(TextAd ad, Credential credential, Connection connection) throws SQLException{
        String sql = 
                "INSERT INTO ad ( kind, time, state, anunciante_name, date, text, cost ) VALUES ( ?, ?, ?, ?, ?, ?, ?) ";
  
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, Global.textAd.name());
            preparedStatement.setInt(2, ad.getTime().getDay());
            preparedStatement.setBoolean(3, true);
            preparedStatement.setString(4, credential.getUserName());
            preparedStatement.setString(5, ad.getDate().toString());
            preparedStatement.setString(6, ad.getText());
            preparedStatement.setDouble(7, ad.getCost());
            preparedStatement.executeUpdate();
    }
    
    public TextAd getById( int id) throws ServerException, InvalidDataException{
        String sql = "select * from ad where ( id = ? and expire_status = false )";
        try (
                Connection cn = DBConnectionSingleton.getInstance().getConnection();
                PreparedStatement ps = cn.prepareStatement(sql);
                ) {
          
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                TextAd ad = new TextAd();
                ad.setId(id);
                ad.setText(rs.getString("text"));
                ad.setKind(Global.valueOf(rs.getString("kind")));
                return ad;
            } else {
                 throw new InvalidDataException("El anuncio no con id = "+id+" no existe o ha expirado");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new ServerException("Error al obtener el anuncio de texto con id: "+id);
        }
    }
    
    public void update(TextAd ad) throws ServerException, InvalidDataException{
        String sql = " update  ad set text  = ? where ( id = ?) ";
        try(
                Connection cn = DBConnectionSingleton.getInstance().getConnection();
                PreparedStatement ps = cn.prepareStatement(sql);
                )  {
            
            ps.setString(1, ad.getText());
            ps.setInt(2, ad.getId());
            ps.executeUpdate();
        }catch (SQLException e) {
            e.printStackTrace();
            throw new ServerException("Error al actualizar el anucio de texto con id = "+ad.getId());
        }
    }
}
