package se.kth.iv1350.Seminar3New.integration;

/**
 * An exception that is thrown when the database could not be reached.
 */
public class DatabaseFailException extends Exception{
    public DatabaseFailException(String message) {
        super(message);
    }
}
