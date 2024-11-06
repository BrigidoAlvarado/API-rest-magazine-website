/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package backend.DBconnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;

/**
 *
 * @author brigidoalvarado
 */
public class adViewDBConnection extends DBConnection {
   
    public void saveView(int id, String url, Connection connection) throws SQLException{
        String sql = "INSERT INTO ad_view (ad, date, url) VALUES ( ?, ?, ? )";
        SetConnection(connection);
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setInt(1, id);
        ps.setString(2, LocalDate.now().toString());
        ps.setString(3, url);
        ps.executeUpdate();
    }
}
