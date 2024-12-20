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
public class ImageAd extends Ad {
    
    private int id;
    private Global kind;
    private AdTime time;
    private String text;
    private LocalDate date;
    private ApiFile file;
    private double cost;
        
    public Global getKind() {
        return kind;
    }

    public void setKind(Global kind) {
        this.kind = kind;
        super.kindAd = kind;
    }

    public AdTime getTime() {
        return time;
    }

    public void setTime(AdTime time) {
        this.time = time;
        super.adTime = time;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public ApiFile getFile() {
        return file;
    }

    public void setFile(ApiFile file) {
        this.file = file;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }
    
   public void validateUpdate()throws InvalidDataException{
       if (id < 0 || text == null) {
           throw new InvalidDataException("Los datos para la acuatualizacion son invalidos");
       }
   }
}
