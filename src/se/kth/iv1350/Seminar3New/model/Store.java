package se.kth.iv1350.Seminar3New.model;

/**
 * Contains the information about the store.
 */
public class Store {

    private String storeName;
    private String storeAddress;

    public Store(String storeName, String storeAddress) {
        this.storeName = storeName;
        this.storeAddress = storeAddress;
    }

    public String getStoreName() {
        return storeName;
    }

    public String getStoreAddress() {
        return storeAddress;
    }
}
