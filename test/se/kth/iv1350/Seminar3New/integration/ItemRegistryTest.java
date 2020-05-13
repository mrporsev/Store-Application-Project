package se.kth.iv1350.Seminar3New.integration;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ItemRegistryTest {

    private ItemRegistry itemRegistryTest;

    @BeforeEach
    void setUp() {
        itemRegistryTest = new ItemRegistry();
    }

    @AfterEach
    void tearDown() {
        itemRegistryTest = null;
    }

    /*
    @Disabled
    @Test
    void checkIfValidTrue() {
        int barcode = 11;
        assertTrue(true == itemRegistryTest.checkIfValid(barcode), "checkIfValid does not work");
    }
    @Disabled
    @Test
    void checkIfValidFalse() {
        int barcode = 1;
        assertTrue(false == itemRegistryTest.checkIfValid(barcode), "checkIfValid does not work");
    }

     */

    @Test
    void retrieveProduct() {
        String expectedItemName = "Orange";
        int barCodeForItem = 10;
        try {
            assertEquals(expectedItemName,itemRegistryTest.retrieveProduct(barCodeForItem).getItemName());
        } catch(BarcodeNotValidException | DatabaseFailException DFB) {

        }
    }

    @Test
    void updateRegistry() {

    }
}