package se.kth.iv1350.Seminar3New.controller;

import se.kth.iv1350.Seminar3New.integration.ExternalSystemHandler;
import se.kth.iv1350.Seminar3New.integration.ItemRegistry;
import se.kth.iv1350.Seminar3New.integration.ItemSold;
import se.kth.iv1350.Seminar3New.model.Sale;
import se.kth.iv1350.Seminar3New.model.Store;
import se.kth.iv1350.Seminar3New.model.dto.DisplayDTO;
import se.kth.iv1350.Seminar3New.model.dto.ItemDTO;

public class Controller {

    private Sale sale;
    private Store store;
    private ExternalSystemHandler externalSystemHandler;

    public Controller(ExternalSystemHandler externalSystemHandler, Store store) {
        this.externalSystemHandler = externalSystemHandler;
        this.store = store;
    }

    /**
     * Start a new sale. This method must be called before doing anything else during a sale.
     */
    public void startSale() {
        sale = new Sale();
    }

    /**
     * the process of adding items.
     *
     * @param barCode the searched barcode.
     * @param quantity The quantity of the same type of item that is being scanned.
     * @return display is the return variable which will be viewed in the view. This contains SaleDTO which is information about the
     * sale.
     */
    public DisplayDTO addItem(int barCode, int quantity) {
        if (externalSystemHandler.checkIfValid(barCode)) {
            ItemDTO itemDTO = externalSystemHandler.retrieveProduct(barCode);
            ItemSold itemSold = new ItemSold(itemDTO, quantity);
            int currentPrice = sale.addSale(itemSold);
            return new DisplayDTO(itemSold, currentPrice);
        } else {
            return new DisplayDTO("Error: Item not  found.");
        }
    }

    /**
     * When the sale is ending and the items are no longer scanned.
     *
     * @return Shows the total price on the display.
     */
    public DisplayDTO endSale() {
        return new DisplayDTO(sale.getTotalAmount());
    }

    /**
     * Handles the process of payment.
     * @param amountReceived The amount of currency received from the customer to the cashier.
     * @return DisplayDTO with the amount of change to be given back.
     */
    public DisplayDTO payment(int amountReceived) {
        return new DisplayDTO((sale.calculateChange(amountReceived)));
    }

    /**
     * Accounting system, Item Registry, Sale Log, and Printer are
     * being handled in this method.
     */
    public void handleExternalSystems() {
        externalSystemHandler.handleExternalSystems(sale.createSaleInfoDTO(store));
    }
}
