package se.kth.iv1350.Seminar3New.model;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import se.kth.iv1350.Seminar3New.integration.*;
import se.kth.iv1350.Seminar3New.model.dto.ItemDTO;

import static org.junit.jupiter.api.Assertions.*;

class SaleTest {

    private Sale saleInstance;

    @BeforeEach
    void setUp() {
        saleInstance = new Sale(new ExternalSystemHandler(new SaleLog(), new ItemRegistry(), new AccountingSystem()));
    }

    @AfterEach
    void tearDown() {
        saleInstance = null;
    }

    @Test
    void calculateChange() {
        int amountPaid = 10;
        int expectedChange = 10;
        saleInstance.calculateChange(amountPaid);
        assertEquals(expectedChange,saleInstance.getChange(),"Chane is not calculated correctly");
    }

    @Test
    void registerDuplicateItem() {
        ItemDTO itemDTOFirst = new ItemDTO(10, "Orange", 6, 111);
        ItemSold firstItem = new ItemSold(itemDTOFirst, 4);
        ItemDTO itemDTOSecond = new ItemDTO(10, "Orange", 6, 111);
        ItemSold secondItem = new ItemSold(itemDTOSecond, 1);

        saleInstance.addSale(firstItem);
        saleInstance.addSale(secondItem);

        assertFalse(saleInstance.getBoughtItems().contains(secondItem), "Item was added.");
        int expectedQuantity = 5;
        assertEquals(expectedQuantity, saleInstance.getBoughtItems().get(0).getQuantity(), "Wrong quantity.");
    }

    @Test
    void registerNonDuplicate() {
        ItemDTO itemDTO = new ItemDTO(10, "Orange", 6, 111);
        ItemSold itemSold = new ItemSold(itemDTO, 4);
        saleInstance.addSale(itemSold);
        assertTrue(saleInstance.getBoughtItems().contains(itemSold), "Item was not added.");
    }
}