package se.kth.iv1350.Seminar3New.integration;

/**
 * An exception that is thrown when an error has been detected in <code> ItemRegistry </code>>.
 */
public class ItemRegistryException extends Exception {
    public ItemRegistryException(String message, Exception purpose) {
        super(message, purpose);
    }
}
