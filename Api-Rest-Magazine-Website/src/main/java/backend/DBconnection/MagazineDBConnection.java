/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package backend.DBconnection;

import backend.exception.ServerException;
import backend.model.dto.Filter;
import backend.model.dto.Magazine;
import java.sql.Connection;
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
public class MagazineDBConnection extends DBConnection {

    public Magazine getById(int id) throws ServerException {
        TagDBConnection tagDBConnection = new TagDBConnection();
        Magazine magazine = new Magazine();
        String sql = " select * from magazine where ( id = ?)";
        try {
            getConnection();
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                magazine.setCategory(rs.getString("category"));
                magazine.setDescription(rs.getString("description"));
                magazine.setEditor(rs.getString("editor_user_name"));
                magazine.setId(rs.getInt("id"));
                magazine.setLikes(rs.getInt("likes"));
                magazine.setTittle(rs.getString("tittle"));
                magazine.setCommentStatus(rs.getBoolean("comment_status"));
                magazine.setTagsList(tagDBConnection.getTagsList(magazine.getId()));
            }
            return magazine;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new ServerException("Error al cargar la revista con id: " + id);
        }
    }

    public void save(Magazine magazine, Connection connection) throws SQLException {

        ResultSet generatedKey;
        PreparedStatement ps;
        String sqlInsert = "insert into magazine (description, category, date, editor_user_name, tittle, subscription_status, comment_status) values ( ?, ?, ?, ?, ?, true, true)";
        SetConnection(connection);
        ps = connection.prepareStatement(sqlInsert, PreparedStatement.RETURN_GENERATED_KEYS);
        ps.setString(1, magazine.getDescription());
        ps.setString(2, magazine.getCategory());
        ps.setString(3, magazine.getDate().toString());
        ps.setString(4, magazine.getEditor());
        ps.setString(5, magazine.getTittle());
        ps.executeUpdate();

        generatedKey = ps.getGeneratedKeys();
        if (generatedKey.next()) {
            magazine.setId(generatedKey.getInt(1));
        }
        // Cerrar recursos
        if (generatedKey != null) {
            generatedKey.close();
        }
        if (ps != null) {
            ps.close();
        }
    }

    public List<Magazine> getNewPosts() throws ServerException {
        List<Magazine> magazines = new ArrayList<>();
        String sqlSelect = "select id, tittle from magazine where daily_cost is null ";
        try {
            getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sqlSelect);
            while (resultSet.next()) {
                Magazine magazine = new Magazine();
                magazine.setId(resultSet.getInt("id"));
                magazine.setTittle(resultSet.getString("tittle"));
                magazines.add(magazine);
            }
            return magazines;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new ServerException("Error al cargar las revistas sin costo diario de la base de datos");
        }
    }

    public void setCost(int id, double cost) throws ServerException {
        System.out.println("se asignara: " + cost + " a " + id);
        String sqlUpdate = "update  magazine set daily_cost = ? where id = ?";
        try {
            getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sqlUpdate);
            preparedStatement.setDouble(1, cost);
            preparedStatement.setInt(2, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new ServerException("error al actualizar el costo diario de  la revista en la base de datos");
        }
    }

    public void increaseLikes(int id) throws SQLException, ServerException {
        int likes = getLikes(id);
        String sql
                = " UPDATE magazine SET likes = ? WHERE ( id = ? );";
        SetConnection(connection);
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setInt(1, likes + 1);
        ps.setInt(2, id);
        ps.executeUpdate();
    }

    public int getLikes(int id) throws ServerException {
        String sql = " select likes from magazine where ( id = ? )";
        try {
            getConnection();
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                int likes = rs.getInt("likes");
                return likes;
            }
            throw new ServerException("error al obtener el numero de likes de la revista con id: " + id);
        } catch (SQLException e) {
            throw new ServerException("error al obtener el numero de likes de la revista con id: " + id);
        }
    }
}
