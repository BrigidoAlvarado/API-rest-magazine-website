/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package backend.model.dto;

import backend.model.UserType;

/**
 *
 * @author brigidoalvarado
 */
public class Profile {
    
    private String userName;
    private UserType userType;
    private String tastes;
    private String topicOfInterest;
    private String hobbies;
    private String Description;

    public UserType getUserType() {
        return userType;
    }

    public void setUserType(UserType userType) {
        this.userType = userType;
    }

    
    
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

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

    public void setTastes(String tastes) {
        this.tastes = tastes;
    }

    public void setTopicOfInterest(String topicOfInterest) {
        this.topicOfInterest = topicOfInterest;
    }

    public void setHobbies(String hobbies) {
        this.hobbies = hobbies;
    }
}
