/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package backend.DBconnection;

import backend.exception.ServerException;
import backend.model.dto.Magazine;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author brigidoalvarado
 */
public class EditorDBConnection extends DBConnection {

    public List<Magazine> getPublisedMagazineList(String userName) throws ServerException{
        List<Magazine> list = new ArrayList<>();
        String sql = 
                "SELECT id, tittle FROM magazine WHERE ( editor_user_name = ? )";
        try {
            getConnection();
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, userName);
            ResultSet rs = ps.executeQuery();
            
            while(rs.next()){
                Magazine magazine = new Magazine();
                magazine.setId(rs.getInt("id"));
                magazine.setTittle(rs.getString("tittle"));
                magazine.setEditor(userName);
                list.add(magazine);
            }
            return list;
        } catch (SQLException e) {
            throw new ServerException("Error al cargar las revista publicadas de: "+userName);
        }
    }
}
