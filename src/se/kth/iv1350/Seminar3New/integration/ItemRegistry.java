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
     * Checks if the item barcode is valid or not.
     * @param barCode The scanned barcode.
     * @return boolean if it is valid or not.
     */
    public boolean checkIfValid(int barCode) {
        for (Item item : registry) {
            if (item.getBarCode() == barCode) {
                return true;
            }
        }
        return false;
    }

    /**
     * Gets the specified item from the item registry by looping through the registry items.
     * @param barCode The scanned barcode.
     * @return  Returns the itemDTO of the specified item.
     */
    public ItemDTO retrieveProduct(int barCode) {
        int i = 0;
        while (registry.get(i).getBarCode() != barCode) {
            i++;
        }
        Item item = registry.get(i);
        return new ItemDTO(item.getItemPrice(), item.getItemName(), item.getVAT(), item.getBarCode());
    }

    /**
     * Updates the registry after a complete sale.
     * The quantity of all items will also be updated (decreased).
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
