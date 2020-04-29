package se.kth.iv1350.Seminar3New.model.dto;

/**
 * The ItemDTO represents the retrieved product from the item registry.
 */
public class ItemDTO {

    private int itemPrice;
    private String itemName;
    private int itemVAT;
    private int barCode;

    public ItemDTO (int price, String name, int vat, int barCode) {
        this.itemPrice = price;
        this.itemName = name;
        this.itemVAT = vat;
        this.barCode = barCode;
    }

    public int getItemPrice() {
        return itemPrice;
    }

    public String getItemName() {
        return itemName;
    }

    public int getBarCode() {
        return barCode;
    }
}
