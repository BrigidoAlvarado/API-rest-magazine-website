/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package backend.model;

/**
 *
 * @author brigidoalvarado
 */
public class Tag {
    
    public String[] generateTags(String tagsText){
        String[] tags = tagsText.split("\\s+");
        return tags;
    }
}
