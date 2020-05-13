package se.kth.iv1350.Seminar3New.integration;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import se.kth.iv1350.Seminar3New.model.Sale;

import static org.junit.jupiter.api.Assertions.*;

class ItemRegistryExceptionTest {

    private ExternalSystemHandler externalSystemHandler;
    private ItemRegistry itemRegistry;
    private Sale sale;

    @BeforeEach
    void setUp() {
        itemRegistry = new ItemRegistry();
        externalSystemHandler = new ExternalSystemHandler(new SaleLog(), itemRegistry, new AccountingSystem());
        sale = new Sale(externalSystemHandler);
    }

    @AfterEach
    void tearDown() {
        itemRegistry = null;
        externalSystemHandler = null;
        sale = null;
    }

    @Test
    void IREShouldNotBeThrownTest() throws BarcodeNotValidException {
        int barCodeWithoutException = 10;
        try {
            externalSystemHandler.retrieveProduct(barCodeWithoutException);
        } catch (ItemRegistryException IRE) {
            fail("Exception should not have been thrown.");
        }
    }

    @Test
    void IREShouldBeThrownTest() throws BarcodeNotValidException {
        int barCodeWithException = 11;
        String expectedMessage = "Call to item inventory unsuccessful.";
        try {
            externalSystemHandler.retrieveProduct(barCodeWithException);
            fail("The exception was not thrown");
        } catch (ItemRegistryException IRE) {
            assertEquals(IRE.getMessage(), expectedMessage, "Wrong message");
        }
    }

}