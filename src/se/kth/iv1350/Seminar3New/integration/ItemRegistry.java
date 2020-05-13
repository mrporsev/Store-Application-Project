package se.kth.iv1350.Seminar3New.integration;

import se.kth.iv1350.Seminar3New.model.dto.ItemDTO;

import java.util.ArrayList;
import java.util.List;

/**
 * The item registry holds information about each item that are provided by the store.
 */
public class ItemRegistry {


    private List<Item> registry = new ArrayList<Item>();

    /**
     * Creates the list of items that are stored in the item registry.
     */
    public ItemRegistry() {
        addItemsToRegistry();
    }

    private void addItemsToRegistry() {
        registry.add(new Item("Orange", 5, 6, 10, 10));
        registry.add(new Item("Apple", 10, 2, 11, 2));
        registry.add(new Item("Chocolate", 3, 2, 12, 3));
    }

    /**
     * Gets the specified item from the item registry by looping through the registry items.
     *
     * @param barCode The scanned barcode.
     * @return Returns the itemDTO of the specified item.
     * @throws BarcodeNotValidException Thrown when a barcode is not find in the item registry.
     * @throws DatabaseFailException    Is implemented as a connection problem between the program and an external database.
     */
    public ItemDTO retrieveProduct(int barCode) throws BarcodeNotValidException, DatabaseFailException {
        if (barCode == 11) {
            throw new DatabaseFailException("SQL error, the database could not be reached.");
        }
        int i = 0;
        while (registry.get(i).getBarCode() != barCode) {
            i++;
            if (i >= registry.size()) {
                throw new BarcodeNotValidException(barCode);
            }
        }
        Item item = registry.get(i);
        return new ItemDTO(item.getItemPrice(), item.getItemName(), item.getVAT(), item.getBarCode());
    }

    /**
     * Updates the registry after a complete sale.
     * The quantity of all items will also be updated (decreased).
     *
     * @param soldItems List of how many products that have been sold.
     */
    public void updateRegistry(List<ItemSold> soldItems) {
        for (ItemSold soldItem : soldItems) {
            for (Item regItem : registry) {
                if (soldItem.getItem().getBarCode() == regItem.getBarCode()) {
                    regItem.updateAmountPresent(soldItem.getQuantity());
                }
            }
        }
    }
}
