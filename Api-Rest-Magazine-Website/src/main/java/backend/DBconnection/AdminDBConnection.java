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
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author brigidoalvarado
 */
public class AdminDBConnection extends DBConnection{
    
    public List<Magazine> getMagazineList() throws ServerException{
        List<Magazine> magazineList = new  ArrayList<>();
        String sql 
                = " select id, tittle, daily_cost from magazine";
        try {
            getConnection();
            Statement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                Magazine magazine = new Magazine();
                magazine.setId(rs.getInt("id"));
                magazine.setTittle(rs.getString("tittle"));
                magazine.setDailyCost(rs.getDouble("daily_cost"));
                magazineList.add(magazine);
            }
            return magazineList;
        } catch (SQLException e) {
            throw new ServerException("Error al cargar el costo diarioi de las revistas publicadas");
        }
    }
    
    public void updateDailyCost(Magazine magazine) throws ServerException {
        String sql = " update magazine set daily_cost = ? where ( id = ? )";
        try {
            getConnection();
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setDouble(1, magazine.getDailyCost());
            ps.setInt(2, magazine.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new ServerException("erro al acrualizar el costo diario de la revista con id: "+magazine.getId());
        }
    }
}
