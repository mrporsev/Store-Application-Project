package se.kth.iv1350.Seminar3New.model;

import se.kth.iv1350.Seminar3New.integration.ItemSold;
import se.kth.iv1350.Seminar3New.model.dto.SaleInfoDTO;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Contains details of a finished sale that proofs that a sale has happened.
 */
public class Receipt {

    private String storeName;
    private String storeAddress;
    private LocalTime time = LocalTime.now();
    private List<ItemSold> boughtItems = new ArrayList<ItemSold>();
    private int totalPrice;
    private int amountPaid;
    private int change;
    private int totalVAT;

    public Receipt(SaleInfoDTO saleInfoDTO) {
        this.storeName = saleInfoDTO.getStore().getStoreName();
        this.storeAddress = saleInfoDTO.getStore().getStoreAddress();
        this.time = time;
        this.boughtItems = saleInfoDTO.getSale().getBoughtItems();
        this.totalPrice = saleInfoDTO.getSale().getTotalAmount();
        this.amountPaid = saleInfoDTO.getSale().getAmountReceived();
        this.change = saleInfoDTO.getSale().getChange();
    }

    /**
     * Breaks down each attribute of the Receipt object into a String that will
     * be shown in the view.
     *
     * @return The string version of the object Receipt.
     */
    public String receiptToString() {
        StringBuilder sb = new StringBuilder();
        sb.append("********************");
        sb.append("\n\tReceipt");
        sb.append("\n" + storeName + "\n" + storeAddress + "\n");
        sb.append("Time of purchase: "+ time + "\n");
        sb.append("\nBought items:");

        for (ItemSold item : boughtItems) {
            sb.append("\n" + item.getQuantity() + " " + item.getItem().getItemName() + " @ " +
                    item.getItem().getItemPrice() + " kr/each");
        }

        sb.append("\n\n" + "Total price: " + totalPrice + "\n");
        sb.append("Amount paid: " + amountPaid + "\nChange: " + change);
        sb.append("\n********************");

        return sb.toString();
    }
}
