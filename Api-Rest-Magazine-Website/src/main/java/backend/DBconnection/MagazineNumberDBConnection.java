/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package backend.DBconnection;

import backend.exception.ServerException;
import backend.model.dto.ApiFile;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 * @author brigidoalvarado
 */
public class MagazineNumberDBConnection extends DBConnection{
    
    private final String  sqlInsert = 
                "INSERT INTO magazine_number  ( magazine, pdf, content_type, file_name) VALUES ( ? , ?, ?, ? )";
    
    public void saveFirstNumber(int id, ApiFile file, Connection connection) throws SQLException{
        SetConnection(connection);
        save(id, file, connection);
        System.out.println("numero guardado");
    }
    
    public void saveNumber(int id, ApiFile file) throws ServerException {
        try (
                Connection cn = DBConnectionSingleton.getInstance().getConnection();
                ) {
            save(id, file, cn);
        } catch (SQLException e) {
            throw new ServerException("Error al guardar un nuevo numeor de la revista con id: "+id);
        }
    }
    
    private void save(int id, ApiFile file, Connection conn) throws SQLException{
        PreparedStatement ps = conn.prepareStatement(sqlInsert);
        ps.setInt(1, id);
        ps.setBlob(2, file.getInputStream());
        ps.setString(3, file.getContentType());
        ps.setString(4, file.getFileName());
        ps.executeUpdate();
    }
}
