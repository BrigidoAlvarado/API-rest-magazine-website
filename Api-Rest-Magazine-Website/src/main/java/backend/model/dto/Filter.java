/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package backend.model.dto;

import backend.exception.InvalidDataException;
import java.time.LocalDate;

/**
 *
 * @author brigidoalvarado
 */
public class Filter {

    private String startDate;
    private String endDate;
    private String kind;

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getKind() {
        return kind;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }

    public void datesValidate() throws InvalidDataException {
        try {
            if (startDate == null || startDate.equals("")) {
                startDate = null;
            } else {
                LocalDate.parse(startDate);
            }
            if (endDate == null || endDate.equals("")) {
                endDate = null;
            } else {
                LocalDate.parse(endDate);
            }
        } catch (IllegalArgumentException e) {
            throw new InvalidDataException("Las fechas son invalidas: " + startDate + " " + endDate);
        }
    }

}
