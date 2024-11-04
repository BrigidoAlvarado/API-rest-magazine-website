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
    private int days;
    private LocalDate date;

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
