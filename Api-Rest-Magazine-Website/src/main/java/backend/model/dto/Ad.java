/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package backend.model.dto;

import backend.enums.AdTime;
import backend.enums.Global;
import backend.exception.InvalidDataException;
import java.time.LocalDate;

/**
 *
 * @author brigidoalvarado
 */

public class Ad {
    
    protected int idAd;
    protected boolean status;
    protected double cost;
    protected Global kindAd;
    protected AdTime adTime;
    protected String advertiser;
    protected LocalDate date;

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    
    
    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
    
    public String getAdvertiser() {
        return advertiser;
    }

    public void setAdvertiser(String advertiser) {
        this.advertiser = advertiser;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
    
    public boolean getStatus(){
        return status;
    };
    
    public void setId(int id){
        this.idAd = id;
    }
    
    public int getId(){
        return idAd;
    }
    
    public void setKindAd(Global kindAd){
        this.kindAd = kindAd;
    }
    
    public Global getKindAd(){
        return kindAd;
    }
    
    public void setAdTime(AdTime adTime){
        this.adTime = adTime;
    }
    
    public AdTime getAdTime(){
        return adTime;
    }
    
    public void validate()throws InvalidDataException{
        if (idAd < 0 ) {
            throw new InvalidDataException("id invalido");
        }
    }
}
