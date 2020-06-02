package se.kth.iv1350.Seminar3New.startup;

import se.kth.iv1350.Seminar3New.controller.Controller;
import se.kth.iv1350.Seminar3New.integration.AccountingSystem;
import se.kth.iv1350.Seminar3New.integration.ExternalSystemHandler;
import se.kth.iv1350.Seminar3New.integration.ItemRegistry;
import se.kth.iv1350.Seminar3New.integration.SaleLog;
import se.kth.iv1350.Seminar3New.model.Printer;
import se.kth.iv1350.Seminar3New.model.Store;
import se.kth.iv1350.Seminar3New.view.TotalRevenueView;
import se.kth.iv1350.Seminar3New.view.View;

/**
 * Starting the whole application through a main method.
 */
public class Main {
    /**
     * Initializing the whole application by creating the required objects.
     * @param args No command line parameters.
     */
    public static void main(String[] args) {
        AccountingSystem accountingSystem = new AccountingSystem();
        SaleLog saleLog = new SaleLog();
        Store store = new Store("RetailStore", "Kungsgatan 1");
        ItemRegistry itemRegistry = new ItemRegistry();
        Printer printer = new Printer();
        ExternalSystemHandler externalSystemHandler = new ExternalSystemHandler(saleLog,itemRegistry, accountingSystem);
        Controller contr = new Controller(externalSystemHandler, store, printer);
        View view = new View(contr);
        view.fakeSale();
    }
}
