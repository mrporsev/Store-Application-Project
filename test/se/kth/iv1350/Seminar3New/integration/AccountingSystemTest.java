package se.kth.iv1350.Seminar3New.integration;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AccountingSystemTest {

    private int amount;
    private AccountingSystem accountingSystem;

    @BeforeEach
    void setUp() {
        amount = 30;
        accountingSystem = new AccountingSystem();
    }

    @AfterEach
    void tearDown() {
        amount = 0;
        accountingSystem = null;
    }

    @Test
    void updateAccounting() {
        int expectedAmount = 130;
        accountingSystem.updateAccounting(amount);
        assertEquals(expectedAmount, accountingSystem.getCurrentBalance(), "updateAccounting not working");
    }

}