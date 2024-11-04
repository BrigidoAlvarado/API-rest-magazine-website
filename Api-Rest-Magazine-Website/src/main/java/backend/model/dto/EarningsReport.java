/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package backend.model.dto;

import java.util.List;

/**
 *
 * @author brigidoalvarado
 */
public class EarningsReport {

    private List<Magazine> magazineList;
    private List<Ad> adList;
    private List<LockAd> lockAdList;
    
    private double totalIncome;
    private double totalExpenses;
    private double totalProfits;

    public List<Magazine> getMagazineList() {
        return magazineList;
    }

    public void setMagazineList(List<Magazine> magazineList) {
        this.magazineList = magazineList;
    }

    public List<Ad> getAdList() {
        return adList;
    }

    public void setAdList(List<Ad> adList) {
        this.adList = adList;
    }

    public List<LockAd> getLockAlList() {
        return lockAdList;
    }

    public void setLockAlList(List<LockAd> lockAlList) {
        this.lockAdList = lockAlList;
    }

    public double getTotalIncome() {
        return totalIncome;
    }

    public void setTotalIncome(double totalIncome) {
        this.totalIncome = totalIncome;
    }

    public double getTotalExpenses() {
        return totalExpenses;
    }

    public void setTotalExpenses(double totalExpenses) {
        this.totalExpenses = totalExpenses;
    }

    public double getTotalProfits() {
        return totalProfits;
    }

    public void setTotalProfits(double totalProfits) {
        this.totalProfits = totalProfits;
    }
}
