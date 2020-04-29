package se.kth.iv1350.Seminar3New.integration;

import se.kth.iv1350.Seminar3New.model.dto.ItemDTO;

/**
 * The item sold class contains information about items that have been bought and how many that have been bought.
 */
public class ItemSold {

    private ItemDTO itemDTO;
    private int quantity = 1;

    public ItemSold(ItemDTO item, int quantity) {
        this.itemDTO = item;
        this.quantity = quantity;
    }

    /**
     * Method used to update the quantity of a certain item that's bought.
     *
     * @param amount amount of units that should be added.
     */
    public void updateQuantity(int amount) {
        this.quantity += amount;
    }

    public int getQuantity() {
        return quantity;
    }

    public ItemDTO getItem() {
        return itemDTO;
    }
}
