/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Enum.java to edit this template
 */
package backend.enums;

/**
 *
 * @author brigidoalvarado
 */
public enum AdTime {
    one_day(1),
    three_days(3),
    one_week(7),
    two_week(14);
    
    private final int day;
    
    private  AdTime (int day){
        this.day = day;
    }
    
    public int getDay(){
        return day;
    }
}
