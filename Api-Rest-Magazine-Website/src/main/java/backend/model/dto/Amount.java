/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package backend.model.dto;

import backend.exception.InvalidDataException;

/**
 *
 * @author brigidoalvarado
 */
public class Amount {
    private double amount;
    
    public void setAmount(double amount){
        this.amount = amount;
    }
    
    public double getAmount(){
        return amount;
    }
    
    public void validate () throws InvalidDataException{
        if (amount < 0) {
           throw  new InvalidDataException("La cantida ingresada es invalida");
        }
    }
}
