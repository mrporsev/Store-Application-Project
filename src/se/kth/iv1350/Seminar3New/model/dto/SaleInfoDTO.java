package se.kth.iv1350.Seminar3New.model.dto;

import se.kth.iv1350.Seminar3New.model.Sale;
import se.kth.iv1350.Seminar3New.model.Store;

/**
 * The SaleInfoDTO holds information about each finished sale.
 * It is necessary for the external systems that will update their
 * information (SaleLog, AccountingSystem, ItemRegistry).
 */
public class SaleInfoDTO {

    private Sale sale;
    private Store store;

    public SaleInfoDTO(Sale sale, Store store) {
        this.sale = sale;
        this.store = store;
    }

    public Sale getSale() {
        return sale;
    }

    public Store getStore() {
        return store;
    }
}
