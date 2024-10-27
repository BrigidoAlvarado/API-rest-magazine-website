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
public class TextAd extends Ad{

    private GlobalCost kind;
    private AdTime time;
    private String text;

    public AdTime getTime() {
        return time;
    }

    public void setTime(AdTime time) {
        this.time = time;
        adTime = time;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void validate() throws InvalidDataException{
        if ( time == null || text == null ) {
            throw new InvalidDataException("datos del anuncio de texto invalidos");
        }
    }
    
    public void setKind(GlobalCost kind){
        this.kind = kind;
        kindAd = kind;
    }
    
    public GlobalCost getKind(){
        return kind;
    }
}
