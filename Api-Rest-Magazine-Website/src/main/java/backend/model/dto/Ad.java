/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package backend.model.dto;

import backend.enums.AdTime;
import backend.enums.GlobalCost;
import backend.exception.InvalidDataException;

/**
 *
 * @author brigidoalvarado
 */

public class Ad {
    
    private int id;
    protected boolean status;
    protected GlobalCost kindAd;
    protected AdTime adTime;

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
        this.id = id;
    }
    
    public int getId(){
        return id;
    }
    
    public void setKindAd(GlobalCost kindAd){
        this.kindAd = kindAd;
    }
    
    public GlobalCost getKindAd(){
        return kindAd;
    }
    
    public void setAdTime(AdTime adTime){
        this.adTime = adTime;
    }
    
    public AdTime getAdTime(){
        return adTime;
    }
    
    public void validate()throws InvalidDataException{
        if (id < 0 ) {
            throw new InvalidDataException("id invalido");
        }
    }
}
