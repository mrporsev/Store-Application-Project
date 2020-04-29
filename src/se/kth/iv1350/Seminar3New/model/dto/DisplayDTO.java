package se.kth.iv1350.Seminar3New.model.dto;

import se.kth.iv1350.Seminar3New.integration.ItemSold;

/**
 * The DisplayDTO class is the object that is returned to the view containing relevant information.
 * It has different constructors depending on what information that is needed to be shown, because
 * every attribute of this class is not needed to be shown on every occasion.
 */
public class DisplayDTO {

    private ItemSold itemSold;
    private int amount;
    String errorMessage;

    /**
     * Constructor which contains information that will be shown in the display.
     * @param itemSoldDTO Information about the added item.
     * @param runningTotal  The total amount after the last added item.
     */
    public DisplayDTO(ItemSold itemSoldDTO, int runningTotal) {
        this.itemSold = itemSoldDTO;
        this.amount = runningTotal;
        this.errorMessage = null;
    }

    /**
     * What will be shown when an item isn't found.
     * @param errorMessage String that says item not found.
     */
    public DisplayDTO(String errorMessage) {
        this.itemSold = null;
        this.amount = -1;
        this.errorMessage = errorMessage;
    }

    /**
     * Shows only the amount.
     * @param amount Amount of currency that will be displayed.
     */
    public DisplayDTO(int amount) {
        this.itemSold = null;
        this.amount = amount;
        this.errorMessage = null;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public ItemSold getItemSold() {
        return itemSold;
    }

    public int getTotalAmount() {
        return amount;
    }
}
