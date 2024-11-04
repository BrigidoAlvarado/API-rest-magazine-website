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
public class TextAd extends Ad{

    private Global kind;
    private AdTime time;
    private String text;
    private LocalDate date;

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
    
    

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

    @Override
    public void validate() throws InvalidDataException{
        if ( time == null || text == null || this.kind == null || date == null ) {
            throw new InvalidDataException("datos del anuncio de texto invalidos");
        }
    }
    
    public void validateUpdate()throws InvalidDataException{
        if ( text  == null ) {
            throw new InvalidDataException("datos del anuncio de texto invalidos");
        }
    }
    
    public void setKind(Global kind){
        this.kind = kind;
        kindAd = kind;
    }
    
    public Global getKind(){
        return kind;
    }
}
