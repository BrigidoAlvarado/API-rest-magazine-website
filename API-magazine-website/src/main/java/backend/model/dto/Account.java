/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package backend.model.dto;

import backend.exception.InvalidDataException;
import backend.model.UserType;
import java.io.InputStream;

/**
 *
 * @author brigidoalvarado
 */
public class Account {

    private String password;
    private String userName;
    private String tastes;
    private String topicOfInterest;
    private String hobbies;
    private String contentType;
    private String Description;
    private InputStream photo;
    private UserType userType;

    public String getDescription() {
        return Description;
    }

    public void setDescription(String Description) {
        this.Description = Description;
    }

    public String getPassword() {
        return password;
    }

    public String getUserName() {
        return userName;
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

    public InputStream getPhoto() {
        return photo;
    }

    public UserType getUserType() {
        return userType;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setUserName(String userName) {
        this.userName = userName;
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

    public void setPhoto(InputStream photo) {
        this.photo = photo;
    }

    public void setUserType(UserType userType) throws InvalidDataException {
        try {
            this.userType = userType;
        } catch (IllegalArgumentException e) {
            throw new InvalidDataException("el usuario ingresado es invalido");
        }
    }

    public void validate() throws InvalidDataException {
        if (userName == null) {
            throw new InvalidDataException("el usuario ingresado es nulo");
        } else if (userType != null) {
            throw new InvalidDataException("el tipo de usuario es nulo");
        } else if (password != null) {
            throw new InvalidDataException("el tipo de contraseña es nulo");
        }
    }
}
