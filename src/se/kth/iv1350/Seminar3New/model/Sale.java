package se.kth.iv1350.Seminar3New.model;


import se.kth.iv1350.Seminar3New.integration.ItemSold;
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

    /**
     * Calculates the change after recieving amount of currency.
     *
     * @param amountRecieved The amount of currency recieved as payment.
     * @return The change that is going to be given back.
     */
    public int calculateChange(int amountRecieved) {
        this.amountReceived = amountRecieved;
        this.change = amountRecieved - totalAmount;
        return this.change;
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