/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package backend.model.dto;

import backend.enums.Global;
import backend.exception.InvalidDataException;
import java.time.LocalDate;

/**
 *
 * @author brigidoalvarado
 */
public class LockAd {

    private Global kind;
    private String editor;
    private int days;
    private int id;
    private double cost;
    private LocalDate date;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    
    public String getEditor() {
        return editor;
    }

    public void setEditor(String editor) {
        this.editor = editor;
    }

    
    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    
    public Global getKind() {
        return kind;
    }

    public void setKind(Global kind) {
        this.kind = kind;
    }

    public int getDays() {
        return days;
    }

    public void setDays(int days) {
        this.days = days;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
    
    public void validate() throws InvalidDataException{
        if (kind == null || days < 0 || date == null) {
            throw new InvalidDataException("Datos del blqueo de anucio invalido");
        }
    }
}
