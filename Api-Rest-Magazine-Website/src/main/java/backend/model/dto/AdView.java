/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package backend.model.dto;

import backend.enums.Global;
import java.util.List;

/**
 *
 * @author brigidoalvarado
 */
public class AdView {

    private int id;
    private Global type;
    private List<String> urls;
    private int views;

    public Global getType() {
        return type;
    }

    public void setType(Global type) {
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<String> getUrls() {
        return urls;
    }

    public void setUrls(List<String> urls) {
        this.urls = urls;
    }

    public int getViews() {
        return views;
    }

    public void setViews(int views) {
        this.views = views;
    }

}
