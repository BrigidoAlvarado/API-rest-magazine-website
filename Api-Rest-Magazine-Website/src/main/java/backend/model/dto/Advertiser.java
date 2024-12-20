/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package backend.model.dto;

import java.util.List;

/**
 *
 * @author brigidoalvarado
 */
public class Advertiser {

    private List<Ad> adList;
    private String userName;
    private List<AdView> adViewList;

    public List<AdView> getAdViewList() {
        return adViewList;
    }

    public void setAdViewList(List<AdView> adViewList) {
        this.adViewList = adViewList;
    }
    

    public List<Ad> getAdList() {
        return adList;
    }

    public void setAdList(List<Ad> adList) {
        this.adList = adList;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

}
