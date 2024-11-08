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
public class EffectivityAdReport {

    private String advertiser;
    private List<AdView> adViewList;

    public String getAdvertiser() {
        return advertiser;
    }

    public void setAdvertiser(String advertiser) {
        this.advertiser = advertiser;
    }

    public List<AdView> getAdViewList() {
        return adViewList;
    }

    public void setAdViewList(List<AdView> adViewList) {
        this.adViewList = adViewList;
    }
    
    
}
