/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package backend.DBconnection;

import backend.exception.ServerException;
import backend.model.dto.ApiFile;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author brigidoalvarado
 */
public class FileDBConnection extends DBConnection {

    public ApiFile getProfile(String userName, String userType) throws ServerException {
        ApiFile file = new ApiFile();
        String sql
                = "SELECT photo, photo_content_type FROM " + userType + " WHERE ( user_name = ?)";
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
            throw new ServerException("Error al obtener la foto de perfil de: " + userName);
        }
    }
    
    public ApiFile getImage(int id) throws ServerException{
        ApiFile file = new ApiFile();
        String sql
                = " select * from ad where id = ? ";
        try {
            getConnection();
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                file.setContentType(rs.getString("content_type"));
                file.setInputStream(rs.getBinaryStream("image"));
            }
            return file;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new ServerException("Error al obtener la imagen del anuncio de id : " + id);
        }
    }

    public ApiFile getPdf(int id) throws ServerException{
        ApiFile file = new ApiFile();
        String sql = " select * from magazine_number where ( id = ? )";
        try {
            getConnection();
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                file.setContentType(rs.getString("content_type"));
                file.setFileName(rs.getString("file_name"));
                file.setInputStream(rs.getBinaryStream("pdf"));
            }
            return file;
        } catch (SQLException e) {
            throw new ServerException("Error al cargar el archivo pdf con id: "+id);
        }
    }
    
    public List<Integer> getIdFiles(int id) throws ServerException {
        List<Integer> idList = new ArrayList<>();
        String sql
                = " select magazine_number.id "
                + " from magazine_number join magazine "
                + " on magazine.id = magazine_number.magazine "
                + " where ( magazine.id = ? ) ";

        try {
            getConnection();
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int idFile = rs.getInt("id");
                idList.add(idFile);
            }
            return idList;
        } catch (SQLException e) {
            throw new ServerException("Error al cargar el id de los pdf de la revista con id: "+id);
        }
    }
}
