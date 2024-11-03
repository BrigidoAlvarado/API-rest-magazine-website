/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package backend.DBconnection;

import backend.exception.ServerException;
import backend.model.dto.ApiFile;
import backend.model.dto.Credential;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author brigidoalvarado
 */
public class FileDBConnection extends DBConnection {
    
    public ApiFile getProfile( String userName, String userType) throws ServerException {
        ApiFile file = new ApiFile();
        String sql = 
                "SELECT photo, photo_content_type FROM "+userType+" WHERE ( user_name = ?)";
        try {
            getConnection();
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, userName);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                file.setContentType(rs.getString("photo_content_type"));
                file.setInputStream(rs.getBinaryStream("photo"));
            }
            return file;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new ServerException("Error al obtener la foto de perfil de: "+userName);
        }
    }
}
