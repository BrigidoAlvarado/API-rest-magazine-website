/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package backend;

import backend.model.ExpirationAd;
import backend.model.dto.Ad;
import java.util.List;

/**
 *
 * @author brigidoalvarado
 */
public class Main {
    public static void main(String[] args) {
        ExpirationAd expirationAd = new ExpirationAd();
        try {
        expirationAd.validate();            
        } catch (Exception e) {
            System.out.println("algo salio mal");
        }
    }
}
