package se.kth.iv1350.Seminar3New.integration;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DatabaseFailExceptionTest {

    private ItemRegistry itemRegistry;

    @BeforeEach
    void setUp() {
        itemRegistry = new ItemRegistry();
    }

    @AfterEach
    void tearDown() {
        itemRegistry = null;
    }

    @Test
    void DFEShouldNotBeThrownTest() throws BarcodeNotValidException {
        int barCodeWithoutSqlError = 10;
        try {
            itemRegistry.retrieveProduct(barCodeWithoutSqlError);
        } catch (DatabaseFailException DFE) {
            fail("The exception was thrown when it should not have been.");
        }
    }

    @Test
    void DFEShouldBeThrownTest() throws BarcodeNotValidException {
        int barCodeWithSqlError = 11;
        String expectedMessage = "SQL error, the database could not be reached.";
        try {
            itemRegistry.retrieveProduct(barCodeWithSqlError);
            fail("The barcode passed without SQL error");
        } catch (DatabaseFailException DFE) {
            assertEquals(expectedMessage, DFE.getMessage(), "Wrong message");
        }
    }
}