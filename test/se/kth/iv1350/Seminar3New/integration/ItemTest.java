package se.kth.iv1350.Seminar3New.integration;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ItemTest {

    private Item firstItem;
    private Item secondItem;

    @BeforeEach
    void setUp() {
        firstItem = new Item("lemon",5,2,123,5);
        secondItem = new Item("test",6,3,122,4);
    }

    @AfterEach
    void tearDown() {
        firstItem = null;
        secondItem = null;
    }

    @Test
    void updateAmountPresent() {
        firstItem.updateAmountPresent(1);
        assertEquals(firstItem.getAmountPresent(), secondItem.getAmountPresent(), "Matching.");
    }

}