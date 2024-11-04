/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package backend.DBconnection;

import backend.enums.Global;
import backend.exception.ServerException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author brigidoalvarado
 */
public class GlobalDBConnection extends DBConnection {
    
    public void newIncome(double amount, Connection connection) throws SQLException{
        //Obtener la cantidad actuall de dinero de la aplicacion
        String sqlSelect = "select cost from  global_cost where name = ?";
        PreparedStatement st = connection.prepareStatement(sqlSelect);
        st.setString(1, Global.bank.name());
        ResultSet resultSet = st.executeQuery();
        if (resultSet.next()) {
            amount += resultSet.getDouble("cost");
        }
        //Agregar el nuevo ingreso generado
        String sqlUpdate = "UPDATE global_cost  SET  cost  = ?  WHERE ( name = ?)";
        PreparedStatement updateSt = connection.prepareStatement(sqlUpdate);
        updateSt.setDouble(1, amount);
        updateSt.setString(2, Global.bank.name());
        updateSt.executeUpdate();
    }
    
    public double getCost(Global globalCost )throws ServerException{
        String sql = "select cost from  global_cost where name = ?";
        try {
            getConnection();
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, globalCost.name());
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                return rs.getDouble("cost");
            } else {
                throw new ServerException("Error el obtener el costo de :"+globalCost.name());
            }
        } catch (SQLException e) {
            throw new ServerException("Error al obtener el costo de: "+globalCost.name());
        }
    }
    
    public void updateGlobalCost(Global kind, double cost) throws ServerException {
        String sql = " UPDATE global_cost SET cost = ? WHERE ( name = ? )";
        try {
            getConnection();
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setDouble(1, cost);
            ps.setString(2, kind.name());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new ServerException("Error al actualizar el costo global de: "+kind);
        }
    }
}
