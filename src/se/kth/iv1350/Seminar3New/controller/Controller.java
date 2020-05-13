package se.kth.iv1350.Seminar3New.controller;

import se.kth.iv1350.Seminar3New.integration.*;
import se.kth.iv1350.Seminar3New.model.Printer;
import se.kth.iv1350.Seminar3New.model.Sale;
import se.kth.iv1350.Seminar3New.model.SaleObserver;
import se.kth.iv1350.Seminar3New.model.Store;
import se.kth.iv1350.Seminar3New.model.dto.DisplayDTO;
import se.kth.iv1350.Seminar3New.model.dto.SaleInfoDTO;

public class Controller {

    private Sale sale;
    private Store store;
    private ExternalSystemHandler externalSystemHandler;
    private SaleObserver saleObserver;
    private Printer printer;

    public Controller(ExternalSystemHandler externalSystemHandler, Store store, Printer printer) {
        this.externalSystemHandler = externalSystemHandler;
        this.store = store;
        this.printer = printer;
    }

    public void addSaleObserver(SaleObserver saleObserver) {
        this.saleObserver = saleObserver;
    }

    /**
     * Start a new sale. This method must be called before doing anything else during a sale.
     */
    public void startSale() {
        sale = new Sale(externalSystemHandler);
        sale.addSaleObserver(saleObserver);
    }

    /**
     * the process of adding items.
     *
     * @param barCode  the searched barcode.
     * @param quantity The quantity of the same type of item that is being scanned.
     * @return display is the return variable which will be viewed in the view. This contains SaleDTO which is information about the
     * sale.
     */
    public DisplayDTO addItem(int barCode, int quantity) throws BarcodeNotValidException, ItemRegistryException {
        return sale.scanItem(barCode, quantity);
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
     *
     * @param amountReceived The amount of currency received from the customer to the cashier.
     * @return DisplayDTO with the amount of change to be given back.
     */
    public void payment(int amountReceived) {
        sale.calculateChange(amountReceived);
        SaleInfoDTO saleInfoDTO = sale.printReceipt(printer, store);
        handleExternalSystems(saleInfoDTO);
    }

    /**
     * Accounting system, Item Registry, Sale Log, and Printer are
     * being handled in this method.
     */
    public void handleExternalSystems(SaleInfoDTO saleInfoDTO) {
        externalSystemHandler.handleExternalSystems(saleInfoDTO);
    }
}
