package se.kth.iv1350.Seminar3New.integration;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BarcodeNotValidExceptionTest {

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
    void BNVEShouldNotBeThrownTest() throws DatabaseFailException {
        int validBarCode = 10;
        try {
            itemRegistry.retrieveProduct(validBarCode);
        } catch (BarcodeNotValidException BNVE) {
            fail("The exception was not thrown in the right way.");
        }
    }

    @Test
    void BNVEShouldBeThrownTest() throws DatabaseFailException {
        int invalidBarCode = 15;
        try {
            itemRegistry.retrieveProduct(invalidBarCode);
            fail("The exception should have been thrown but didn't.");
        } catch (BarcodeNotValidException BNVE) {
            assertEquals(BNVE.getBarCode(), invalidBarCode, "Exception message holds wrong barcode.");
        }
    }
}