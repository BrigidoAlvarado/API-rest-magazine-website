/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package backend.model.dto;

import backend.exception.AccessException;
import backend.model.UserType;

/**
 *
 * @author brigidoalvarado
 */
public class Credential {
    
    private String userName;
    private UserType userType;
    private String password;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public UserType getUserType() {
        return userType;
    }

    public void setUserType(UserType userType) throws AccessException {
        try {
            this.userType = userType;
        } catch (IllegalArgumentException e) {
            throw new AccessException("el usuario ingresado es invalido");
        }
    }
    
    public void validate() throws AccessException {
        if (userName == null) {
            System.out.println(userName);
            throw new AccessException("el usuario ingresado es nulo");
        } else if (userType == null) {
            System.out.println(userType);
            throw new AccessException("el tipo de usuario es nulo");
        } else if (password == null) {
            System.out.println(password);
            throw new AccessException("el tipo de contraseña es nulo");
        }
    }
}
