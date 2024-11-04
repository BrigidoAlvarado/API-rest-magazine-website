/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package backend.controllers;

import backend.DBconnection.BusinessDBConnection;
import backend.DBconnection.GlobalDBConnection;
import backend.enums.Global;
import backend.exception.ServerException;
import backend.model.dto.EarningsReport;

/**
 *
 * @author brigidoalvarado
 */
public class AdminReportsController {

    private final BusinessDBConnection businessDBConnection = new BusinessDBConnection();
    private final GlobalDBConnection globalDBConnection = new GlobalDBConnection();

    public EarningsReport getEarningReport() throws ServerException {
        EarningsReport report = new EarningsReport();
        report.setAdList(businessDBConnection.getIncomeAdList());
        report.setLockAdList(businessDBConnection.getIncomeLockAdList());
        report.setMagazineList(businessDBConnection.getExpensesMagazineList());
        report.setTotalIncome(globalDBConnection.getCost(Global.bank));
        report.calculateTotalExpenses();
        report.calculateTotalProfit();
        return report;
    }
}
