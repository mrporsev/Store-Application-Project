package se.kth.iv1350.Seminar3New.model;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import se.kth.iv1350.Seminar3New.integration.*;
import se.kth.iv1350.Seminar3New.model.dto.ItemDTO;
import se.kth.iv1350.Seminar3New.model.dto.SaleInfoDTO;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

class ReceiptTest {

    private ByteArrayOutputStream outputStream;
    private PrintStream originalSysOut;

    @BeforeEach
    void setUp() {
        originalSysOut = System.out;
        outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));
    }

    @AfterEach
    void tearDown() {
        outputStream = null;
        System.setOut(originalSysOut);
    }

    @Test
    void receiptToString() {
        ExternalSystemHandler externalSystemHandler = new ExternalSystemHandler(new SaleLog(),new ItemRegistry(), new AccountingSystem());
        Sale sale = new Sale(externalSystemHandler);
        ItemSold itemSold = new ItemSold(new ItemDTO(10,"Marabou",6,10), 3);        sale.addSale(itemSold);
        sale.addSale(itemSold);
        sale.calculateChange(100);
        SaleInfoDTO saleInfoDTO = sale.createSaleInfoDTO(new Store("test","store"));
        Receipt receipt = new Receipt(saleInfoDTO);
        Printer printer = new Printer();
        printer.print(receipt.receiptToString());

        String result = outputStream.toString();

        assertTrue(result.contains(itemSold.getItem().getItemName()), "Item not showing.");
        assertTrue(result.contains(Integer.toString(itemSold.getItem().getItemPrice())), "Item not showing.");
        assertTrue(result.contains(Integer.toString(itemSold.getQuantity())), "Item quantity not showing.");
        assertTrue(result.contains("Total price: 30"), "Total price not showing.");
        assertTrue(result.contains("Change: 70"), "Change not showing.");
        assertTrue(result.contains("test"));

    }
}