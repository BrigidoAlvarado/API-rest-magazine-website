/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package backend.model.dto;

import backend.enums.AdTime;
import backend.enums.Global;
import java.time.LocalDate;

/**
 *
 * @author brigidoalvarado
 */
public class VideoAd extends Ad{

    private Global kind;
    private AdTime time;
    private String link;
    private LocalDate date;
    private String text;
    private double cost;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    
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

    public void setTime(AdTime days) {
        this.time = days;
        super.adTime = days;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }
    
    
}