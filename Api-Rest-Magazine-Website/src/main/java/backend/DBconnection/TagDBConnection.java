/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package backend.DBconnection;

import backend.exception.InvalidDataException;
import backend.exception.ServerException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author brigidoalvarado
 */
public class TagDBConnection extends DBConnection {

    public void save(String tag, Connection connection) throws SQLException, InvalidDataException {

        System.out.println("guardando el tag " + tag);
        String sqlInsert = "insert into tag (name) values ( ? )";
        try {
            SetConnection(connection);
            PreparedStatement ps = connection.prepareStatement(sqlInsert);
            ps.setString(1, tag);
            ps.executeUpdate();
            System.out.println("guardado");
        } catch (SQLIntegrityConstraintViolationException e) {
            System.out.println("tag repetido retornando");
            throw new InvalidDataException("");
        }

    }

    public void saveTagInMagazine(String tag, int magazineId, Connection connection) throws SQLException, InvalidDataException, ServerException {

        String sqlInsert = "insert into associated_tag (magazine_id, tag_name) values(?, ?)";
        try {
            System.out.println("guardando el tag asociado "+tag);
            SetConnection(connection);
            PreparedStatement ps = connection.prepareStatement(sqlInsert);
            ps.setInt(1, magazineId);
            ps.setString(2, tag);
            ps.executeUpdate();
            System.out.println("guardado");
        } catch (SQLIntegrityConstraintViolationException e) {
            throw new InvalidDataException("");
        }
    }

    public List<String> getTagsList(int magazineId) throws ServerException {

        List<String> tags = new ArrayList<>();
        String sqlSelect = "select tag_name from associated_tag where magazine_id = ? ";
        try (
                Connection cn = DBConnectionSingleton.getInstance().getConnection(); PreparedStatement ps = cn.prepareStatement(sqlSelect); PreparedStatement preparedStatement = cn.prepareStatement(sqlSelect);) {

            preparedStatement.setInt(1, magazineId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                tags.add(resultSet.getString("tag_name"));
            }
            return tags;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new ServerException("Error al cargar las etiquetas de la revista: " + magazineId);
        }
    }
}
