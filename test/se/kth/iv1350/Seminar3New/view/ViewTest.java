package se.kth.iv1350.Seminar3New.view;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import se.kth.iv1350.Seminar3New.controller.Controller;
import se.kth.iv1350.Seminar3New.integration.*;
import se.kth.iv1350.Seminar3New.model.Printer;
import se.kth.iv1350.Seminar3New.model.Store;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

class ViewTest {

    private View instanceToTest;
    private ByteArrayOutputStream printoutBuffer;
    private PrintStream originalSysOut;

    @BeforeEach
    public void setUp() {
        AccountingSystem accountingSystem = new AccountingSystem();
        SaleLog saleLog = new SaleLog();
        Store store = new Store("Store", "Address");
        ItemRegistry itemRegistry = new ItemRegistry();
        Printer printer = new Printer();
        ExternalSystemHandler externalSystemHandler = new ExternalSystemHandler(saleLog,itemRegistry,accountingSystem);


        Controller contr = new Controller(externalSystemHandler,store, printer);
        instanceToTest = new View(contr);

        printoutBuffer = new ByteArrayOutputStream();
        PrintStream inMemSysOut = new PrintStream(printoutBuffer);
        originalSysOut = System.out;
        System.setOut(inMemSysOut);
    }

    @AfterEach
    public void tearDown() {
        instanceToTest = null;

        printoutBuffer = null;
        System.setOut(originalSysOut);
    }

    @Test
    public void testRunFakeExecution() {
        instanceToTest.fakeSale();
        String printout = printoutBuffer.toString();
        String expectedOutput = "started";
        assertTrue(printout.contains(expectedOutput), "UI did not start correctly.");
    }

}