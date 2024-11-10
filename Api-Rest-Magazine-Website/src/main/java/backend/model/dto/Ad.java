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
    protected int days;
    private String text;
    private String link;

    private boolean isText = false;
    private boolean isImage = false;
    private boolean isVideo = false;

    public boolean isIsText() {
        return isText;
    }

    public void setIsText(boolean isText) {
        this.isText = isText;
    }

    public boolean isIsImage() {
        return isImage;
    }

    public void setIsImage(boolean isImage) {
        this.isImage = isImage;
    }

    public boolean isIsVideo() {
        return isVideo;
    }

    public void setIsVideo(boolean isVideo) {
        this.isVideo = isVideo;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public int getDays() {
        return days;
    }

    public void setDays(int days) {
        this.days = days;
    }

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

    public boolean getStatus() {
        return status;
    }

    ;
    
    public void setId(int id) {
        this.idAd = id;
    }

    public int getId() {
        return idAd;
    }

    public void setKindAd(Global kindAd) {
        this.kindAd = kindAd;
    }

    public Global getKindAd() {
        return kindAd;
    }

    public void setAdTime(AdTime adTime) {
        this.adTime = adTime;
    }

    public AdTime getAdTime() {
        return adTime;
    }

    public void validate() throws InvalidDataException {
        if (idAd < 0) {
            throw new InvalidDataException("id invalido");
        }
    }

    public void putType(Global kind) {
        switch (kind) {
            case textAd:
                isText = true;
                break;
            case textImageAd:
                isImage = true;
                break;
            case videoAd:
                isVideo = true;
                break;
            default:
        }
    }
}