/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package backend.DBconnection;

import backend.enums.GlobalCost;
import backend.model.dto.Credential;
import backend.model.dto.TextAd;
import java.sql.Connection;
import java.sql.PreparedStatement;
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
}
