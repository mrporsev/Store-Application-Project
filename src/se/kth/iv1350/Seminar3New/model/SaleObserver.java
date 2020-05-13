package se.kth.iv1350.Seminar3New.model;

/**
 * Interface that can be implemented by classes that needs to observe when
 * a payment has been completed.
 */
public interface SaleObserver {
    /**
     * This method is called when a payment has been made.
     * @param amountReceived The amount of currency received.
     */
    void paymentCompleted(int amountReceived);
}
