/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package backend.DBconnection;

import backend.enums.GlobalCost;
import backend.exception.InvalidDataException;
import backend.exception.ServerException;
import backend.model.dto.Credential;
import backend.model.dto.TextAd;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

/**
 *
 * @author brigidoalvarado
 */
public class TextAdDBConnection extends DBConnection {
   
    public void saveTextAd(TextAd ad, Credential credential, Connection connection) throws SQLException{
        String sql = 
                "INSERT INTO ad ( kind, time, state, anunciante_name, date, text) VALUES ( ?, ?, ?, ?, ?, ?)";
  
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, GlobalCost.textAd.name());
            preparedStatement.setInt(2, ad.getTime().getDay());
            preparedStatement.setBoolean(3, true);
            preparedStatement.setString(4, credential.getUserName());
            preparedStatement.setString(5, LocalDate.now().toString());
            preparedStatement.setString(6, ad.getText());
            preparedStatement.executeUpdate();
    }
    
    public TextAd getById( int id) throws ServerException, InvalidDataException{
        String sql = "select * from ad where ( id = ? and state = true and expire_status = false )";
        try {
            getConnection();
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                TextAd ad = new TextAd();
                ad.setId(id);
                ad.setKind(GlobalCost.valueOf("kind"));
                ad.setText("text");
                return ad;
            } else {
                 throw new InvalidDataException("El anuncio no con id = "+id+" no existe, ha expirado o esta desactivado");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new ServerException("Error al obtener el anuncio de texto con id: "+id);
        }
    }
    
    public void update(TextAd ad) throws ServerException, InvalidDataException{
        String sql = " update table ad set text  = ? where ( id = ?) ";
        try {
            getConnection();
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, ad.getText());
            st.setInt(2, ad.getId());
            st.executeUpdate();
        }catch (SQLException e) {
            e.printStackTrace();
            throw new ServerException("Error al actualizar el anucio de texto con id = "+ad.getId());
        }
    }
}
