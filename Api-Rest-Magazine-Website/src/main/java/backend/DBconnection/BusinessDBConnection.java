/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package backend.DBconnection;

import backend.enums.Global;
import backend.exception.ServerException;
import backend.model.dto.Ad;
import backend.model.dto.Amount;
import backend.model.dto.Credential;
import backend.model.dto.LockAd;
import backend.model.dto.Magazine;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author brigidoalvarado
 */
public class BusinessDBConnection extends DBConnection {
    
    public Amount toUpMoney(Credential credential, Amount amount)throws ServerException{
        WalletDBConnection walletConnection = new WalletDBConnection();
        
        double money = walletConnection.getMoney(credential);
        amount.setAmount( money + amount.getAmount());
        walletConnection.updateMoney(credential, amount);
        
        return amount;
    }
    
    public List<Magazine> getExpensesMagazineList() throws ServerException{
        List<Magazine> magazineList = new ArrayList<>();
        String sql = "select daily_cost * (datediff( curdate(), date)) as total, magazine.* from magazine;";
        try {
            getConnection();
            Statement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery(sql);
            while(rs.next()){
                Magazine magazine = new Magazine();
                magazine.setId(rs.getInt("id"));
                magazine.setTittle(rs.getString("tittle"));
                magazine.setEditor(rs.getString("editor_user_name"));
                magazine.setDailyCost(rs.getDouble("total"));
                magazineList.add(magazine);
            }
            return magazineList;
        } catch (SQLException e) {
            throw new ServerException("Error al obtener los egresos de las revistas");
        }
    }
    
    public List<Ad> getIncomeAdList() throws ServerException {
        List<Ad> adList = new ArrayList<>();
        String sql = "select kind, anunciante_name, id , date, cost from ad;";
        try {
            getConnection();
            Statement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                Ad ad = new Ad();
                ad.setKindAd(Global.valueOf(rs.getString("kind")));
                ad.setAdvertiser(rs.getString("anunciante_name"));
                ad.setId(rs.getInt("id"));
                ad.setDate(LocalDate.parse(rs.getString("date")));
                ad.setCost(rs.getDouble("cost"));
                adList.add(ad);
            }
            return adList;
        } catch (SQLException e) {
            throw new ServerException("Error al cargar los ingresos de los anucios");
        }
    }
    
    public List<LockAd> getIncomeLockAdList()throws ServerException{
        List<LockAd> lockAdList = new ArrayList<>();
        String sql = "select * from lock_ad;";
        try {
            getConnection();
            Statement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery(sql);
            while(rs.next()){
                LockAd lockAd = new LockAd();
                lockAd.setCost(rs.getDouble("cost"));
                lockAd.setDate(LocalDate.parse(rs.getString("date")));
                lockAd.setEditor(rs.getString("editor"));
                lockAd.setId(rs.getInt("id"));
                lockAdList.add(lockAd);
            }
            System.out.println("retornando: "+lockAdList.size());
            return lockAdList;
        } catch (SQLException e)  {
            throw new ServerException("Error al obtener los ingresos de los bloqueos de anuncio");
        }
    }
}
