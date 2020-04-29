package se.kth.iv1350.Seminar3New.integration;

import se.kth.iv1350.Seminar3New.model.Printer;
import se.kth.iv1350.Seminar3New.model.Receipt;
import se.kth.iv1350.Seminar3New.model.dto.ItemDTO;
import se.kth.iv1350.Seminar3New.model.dto.SaleInfoDTO;

/**
 * The External System Handler handles every external system and their operations.
 */
public class ExternalSystemHandler {
    private ItemRegistry itemReg;
    private AccountingSystem acctSys;
    private Printer printer;
    private SaleLog saleLog;

    public ExternalSystemHandler(SaleLog saleLog, ItemRegistry itemReg, AccountingSystem acctSys, Printer printer) {
        this.saleLog = saleLog;
        this.itemReg = itemReg;
        this.acctSys = acctSys;
        this.printer = printer;
    }

    /**
     * Handles the external systems by handing out SaleInfoDTO.
     * @param saleInfo The information that is required for each system to function.
     */
    public void handleExternalSystems(SaleInfoDTO saleInfo) {
        acctSys.updateAccounting(saleInfo.getSale().getTotalAmount());
        itemReg.updateRegistry(saleInfo.getSale().getBoughtItems());
        saleLog.addSaleToSaleLog(saleInfo);
        printReceipt(saleInfo);
    }

    /**
     * Checks if the barcode on the item is valid.
     * @param barCode The scanned barcode.
     * @return true or false that it is valid or not.
     */
    public boolean checkIfValid(int barCode) {
        return itemReg.checkIfValid(barCode);
    }

    /**
     * Gets the item information in the form of a ItemDTO.
     * @param barCode The scanned barcode.
     * @return The item information.
     */
    public ItemDTO retrieveProduct(int barCode) {
        return itemReg.retrieveProduct(barCode);
    }

    /**
     * Handles the printing of the receipt.
     * @param saleInfo  The sale information needed for the receipt.
     */
    public void printReceipt(SaleInfoDTO saleInfo) {
        Receipt receipt = new Receipt(saleInfo);
        String printedReceipt = receipt.receiptToString();
        printer.print(printedReceipt);
    }
}
