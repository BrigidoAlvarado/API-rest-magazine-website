/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package backend.model.dto;

/**
 *
 * @author brigidoalvarado
 */
public class Profile {
    
    private String tastes;
    private String topicOfInterest;
    private String hobbies;
    private String contentType;
    private String Description;

    public String getDescription() {
        return Description;
    }

    public void setDescription(String Description) {
        this.Description = Description;
    }

    public String getTastes() {
        return tastes;
    }

    public String getTopicOfInterest() {
        return topicOfInterest;
    }

    public String getHobbies() {
        return hobbies;
    }

    public String getContentType() {
        return contentType;
    }
    
  
    public void setTastes(String tastes) {
        this.tastes = tastes;
    }

    public void setTopicOfInterest(String topicOfInterest) {
        this.topicOfInterest = topicOfInterest;
    }

    public void setHobbies(String hobbies) {
        this.hobbies = hobbies;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }
}
