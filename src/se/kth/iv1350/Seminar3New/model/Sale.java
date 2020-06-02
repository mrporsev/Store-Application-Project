package se.kth.iv1350.Seminar3New.model;


import se.kth.iv1350.Seminar3New.integration.BarcodeNotValidException;
import se.kth.iv1350.Seminar3New.integration.ExternalSystemHandler;
import se.kth.iv1350.Seminar3New.integration.ItemRegistryException;
import se.kth.iv1350.Seminar3New.integration.ItemSold;
import se.kth.iv1350.Seminar3New.model.dto.DisplayDTO;
import se.kth.iv1350.Seminar3New.model.dto.ItemDTO;
import se.kth.iv1350.Seminar3New.model.dto.SaleInfoDTO;

import java.util.ArrayList;
import java.util.List;

/**
 * The Sale class handles everything regarding a sale instance.
 */
public class Sale {

    private List<ItemSold> boughtItems = new ArrayList<ItemSold>();
    private int totalAmount;
    private int amountReceived;
    private int change;
    private ExternalSystemHandler externalSystemHandler;
    private SaleObserver saleObserver;

    public Sale(ExternalSystemHandler externalSystemHandler) {
        this.externalSystemHandler = externalSystemHandler;
    }

    public void addSaleObserver(SaleObserver saleObserver) {
        this.saleObserver = saleObserver;
    }

    /**
     * @param barCode  The scanned barcode
     * @param quantity The quantity of the scanned item.
     * @return A DisplayDTO that shows information about the scanned item(s).
     * @throws BarcodeNotValidException Thrown when a barcode is not found in the item registry.
     * @throws ItemRegistryException    Thrown when there is an error while retrieving a product.
     */
    public DisplayDTO scanItem(int barCode, int quantity) throws BarcodeNotValidException, ItemRegistryException {
        ItemDTO itemDTO = externalSystemHandler.retrieveProduct(barCode);
        ItemSold itemSold = new ItemSold(itemDTO, quantity);
        int currentPrice = addSale(itemSold);
        return new DisplayDTO(itemSold, currentPrice);
    }

    /**
     * addSale will check if the item is a duplicate. If it is not it will be added to the boughtItems list, if not
     * it will update the total.
     *
     * @param itemSold The item that is being added to the sale.
     * @return The updated total price after the added item.
     */
    public int addSale(ItemSold itemSold) {
        if (!(checkForDuplicate(itemSold))) {
            boughtItems.add(itemSold);
        }
        updateTotal((itemSold.getItem()).getItemPrice(), itemSold.getQuantity());
        return totalAmount;
    }

    public int getTotalAmount() {
        return totalAmount;
    }

    public List<ItemSold> getBoughtItems() {
        return boughtItems;
    }

    public int getAmountReceived() {
        return this.amountReceived;
    }

    public int getChange() {
        return this.change;
    }

    private boolean checkForDuplicate(ItemSold itemSold) {
        for (ItemSold item : boughtItems) {
            if (itemSold.getItem().getItemName().equals(item.getItem().getItemName())) {
                item.updateQuantity(itemSold.getQuantity());
                return true;
            }
        }
        return false;
    }

    private void updateTotal(int amount, int quantity) {
        totalAmount += (amount * quantity);
    }

    public SaleInfoDTO printReceipt(Printer printer, Store store) {
        SaleInfoDTO saleInfoDTO = createSaleInfoDTO(store);
        Receipt receipt = new Receipt(saleInfoDTO);
        printer.print(receipt.receiptToString());
        return saleInfoDTO;
    }

    /**
     * Calculates the change after recieving amount of currency.
     *
     * @param amountRecieved The amount of currency recieved as payment.
     * @return The change that is going to be given back.
     */
    public void calculateChange(int amountRecieved) {
        this.amountReceived = amountRecieved;
        this.change = amountRecieved - totalAmount;
        signalToListeners(totalAmount);
    }

    private void signalToListeners(int totalPrice) {
        saleObserver.paymentCompleted(totalPrice);
    }

    /**
     * Creates DTO that contains info on completed sale.
     *
     * @param store The store information.
     * @return SaleInfoDTO with complete sale information.
     */
    public SaleInfoDTO createSaleInfoDTO(Store store) {
        return new SaleInfoDTO(this, store);
    }

}