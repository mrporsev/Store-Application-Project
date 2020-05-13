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
    private SaleLog saleLog;

    public ExternalSystemHandler(SaleLog saleLog, ItemRegistry itemReg, AccountingSystem acctSys) {
        this.saleLog = saleLog;
        this.itemReg = itemReg;
        this.acctSys = acctSys;
    }

    /**
     * Handles the external systems by handing out SaleInfoDTO.
     *
     * @param saleInfo The information that is required for each system to function.
     */
    public void handleExternalSystems(SaleInfoDTO saleInfo) {
        acctSys.updateAccounting(saleInfo.getSale().getTotalAmount());
        itemReg.updateRegistry(saleInfo.getSale().getBoughtItems());
        saleLog.addSaleToSaleLog(saleInfo);
    }

    /**
     * Gets the item information in the form of a ItemDTO.
     *
     * @param barCode The scanned barcode.
     * @return The item information.
     * @throws BarcodeNotValidException Is passed if it is thrown in lower layers.
     * @throws ItemRegistryException    Thrown to see if it is thrown in a lower layer.
     */
    public ItemDTO retrieveProduct(int barCode) throws BarcodeNotValidException, ItemRegistryException {
        try {
            return itemReg.retrieveProduct(barCode);
        } catch (DatabaseFailException failException) {
            throw new ItemRegistryException("Call to item inventory unsuccessful.", failException);
        }
    }
}
