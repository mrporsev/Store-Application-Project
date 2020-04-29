package se.kth.iv1350.Seminar3New.integration;

/**
 * Represents a specific item in the store when created.
 */
public class Item {

    private int itemPrice;
    private String itemName;
    private int VAT;
    private int barCode;
    private int amountPresent;

    public Item(String name, int price, int VAT, int barCode, int amountPresent) {
        this.itemPrice = price;
        this.itemName = name;
        this.VAT = VAT;
        this.barCode = barCode;
        this.amountPresent = amountPresent;
    }

    /**
     * This method handles the situation where an item has already been scanned.
     * @param amountSold Amount of sold items of the same sort.
     */
    public void updateAmountPresent(int amountSold) {
        amountPresent -= amountSold;
    }

    public int getBarCode() {
        return barCode;
    }

    public int getItemPrice() {
        return itemPrice;
    }

    public String getItemName() {
        return itemName;
    }

    public int getVAT() {
        return VAT;
    }

    public int getAmountPresent() {
        return amountPresent;
    }
}
