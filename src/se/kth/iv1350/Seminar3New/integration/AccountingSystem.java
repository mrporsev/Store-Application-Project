package se.kth.iv1350.Seminar3New.integration;

/**
 * Handles the balance of the store.
 */
public class AccountingSystem {

    int currentBalance;

    public AccountingSystem() {
        currentBalance = 100;
    }

    /**
     * Updates the current balance of the store by adding the current balance with the amount received.
     * @param amountReceived The amount of currency received from the customer that the cashier puts in the system.
     */
    public void updateAccounting(int amountReceived) {
        currentBalance += amountReceived;
    }

    public int getCurrentBalance() {
        return currentBalance;
    }
}


