package se.kth.iv1350.Seminar3New.integration;

/**
 * An exception that is thrown when a barcode could not be found in the item registry.
 */
public class BarcodeNotValidException extends Exception {
    private int barCode;

    public BarcodeNotValidException(int barCode) {
        super("A product with the barcode "+ "'" + barCode + "'" + " could not be found.");
        this.barCode = barCode;
    }

    public int getBarCode() {
        return barCode;
    }
}
