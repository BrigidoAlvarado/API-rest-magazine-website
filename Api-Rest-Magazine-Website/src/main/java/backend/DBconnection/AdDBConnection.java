/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package backend.DBconnection;

import backend.enums.Global;
import backend.exception.InvalidDataException;
import backend.exception.ServerException;
import backend.model.dto.Ad;
import backend.model.dto.Credential;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author brigidoalvarado
 */
public class AdDBConnection extends DBConnection {

    public List<Ad> getExpiredAds() throws ServerException, InvalidDataException {
        List<Ad> ads = new ArrayList<>();
        String sql
                = "SELECT id FROM ad WHERE DATEDIFF(CURDATE(), date) > time ; ";
        try(
                Connection cn = DBConnectionSingleton.getInstance().getConnection();
                PreparedStatement st = cn.prepareStatement(sql);
                )  {
            
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                Ad ad = new Ad();
                ad.setId(rs.getInt("id"));
                ads.add(ad);
            }
            return ads;
        } catch (SQLException e) {
            throw new ServerException("Ocurrio un error al obtener el listado de los anuncios vencidos");
        }
    }

    public void setExpireAd(Ad ad) throws ServerException {
        String sql
                = "UPDATE ad SET expire_status = ?  WHERE (id = ?)";
        try (
                Connection cn = DBConnectionSingleton.getInstance().getConnection();
                PreparedStatement st = cn.prepareStatement(sql);
                ) {
            
            st.setBoolean(1, true);
            st.setInt(2, ad.getId());
            st.executeUpdate();
        } catch (SQLException e) {
            throw new ServerException("Error al cambiar el expire_status de: " + ad.getId());
        }
    }

    public List<Ad> getPurchasedAds(Credential credential) throws ServerException, InvalidDataException {
        List<Ad> ads = new ArrayList<>();
        String sql = "select id, kind, state from ad where ( expire_status = false and anunciante_name = ? ) ";
        try (
                Connection cn = DBConnectionSingleton.getInstance().getConnection();
                PreparedStatement st = cn.prepareStatement(sql);
                ) {
            
            st.setString(1, credential.getUserName());
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Ad ad = new Ad();
                ad.setId(rs.getInt("id"));
                ad.setStatus(rs.getBoolean("state"));
                ad.setKindAd(Global.valueOf(rs.getString("kind")));
                ads.add(ad);
            }
            return ads;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new ServerException("Error al obtener el listado de los anuncios comprados no expirados de: " + credential.getUserName());
        }
    }
    
    public Ad getAdById(int id) throws InvalidDataException, ServerException{
        Ad ad = new Ad();
        String sql = "select state from ad where (expire_status = false and id = ?  )";
        try (
                Connection cn = DBConnectionSingleton.getInstance().getConnection();
                PreparedStatement st = cn.prepareStatement(sql);
                ) {
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                ad.setStatus(rs.getBoolean("state"));
                return ad;
            } else {
                throw new InvalidDataException("No se ha encontrado o ha expirado el anuncio con el id: "+id);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw  new ServerException("Error al obtner el anuncio con id: "+id);
        }
    }
    
    public void updateStatus(Credential credential, Ad ad) throws InvalidDataException, ServerException{
        String sql = "update ad set state = ? where ( id = ? and kind = ? and state = true)";
        System.out.println("se actualizara al estado: "+ad.getStatus());
        try(
                Connection cn = DBConnectionSingleton.getInstance().getConnection();
                PreparedStatement st = cn.prepareStatement(sql);
                ) {
            
            st.setBoolean(1, ad.getStatus());
            st.setInt(2, ad.getId());
            st.setString(3, ad.getKindAd().name());
            st.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new ServerException("Error al actualizar el estado del anuncio: "+ad.getId());
        }
    }
    
    public Ad getRandomAd(String type) throws ServerException{
        Ad ad = new Ad();
        String sql = " select * from ad where ( kind = ? ) order by rand() limit 1";
        try (
                Connection cn = DBConnectionSingleton.getInstance().getConnection();
                PreparedStatement ps = cn.prepareStatement(sql);
                ) {
            
            ps.setString(1, type);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                ad.setId(rs.getInt("id"));
                ad.setLink(rs.getString("link"));
                ad.setText(rs.getString("text"));
                ad.putType(Global.valueOf(type));
            }
            System.out.println("se encontro un anuncio de tipo: "+type+" con id:"+ad.getId());
            return ad;
        } catch (SQLException e) {
            throw new ServerException("Error al cargar un anuncio random de: "+type);
        }
    }
    
    public void increaseScreenTime(int id, Connection connection) throws SQLException{
        String sql
                = " UPDATE ad "
                + " SET screen_time = COALESCE(screen_time, 0) + 1 "
                + " WHERE id = ? ";
        SetConnection(connection);
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setInt(1, id);
        ps.executeUpdate();
    }
}
