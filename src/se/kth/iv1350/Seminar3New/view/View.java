package se.kth.iv1350.Seminar3New.view;

import se.kth.iv1350.Seminar3New.controller.Controller;
import se.kth.iv1350.Seminar3New.integration.BarcodeNotValidException;
import se.kth.iv1350.Seminar3New.integration.ItemRegistryException;
import se.kth.iv1350.Seminar3New.model.dto.DisplayDTO;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * This is placeholder for the real view. It contains a hardcoded execution with calls to all
 * system operations in the controller.
 */
public class View {
    private Controller contr;
    private String exceptionLog = "\n The following errors have been logged:";

    /**
     * Creates new instance, that uses the specified controller for all calls to other layers.
     *
     * @param contr The controller to use for all calls to other layers.
     */
    public View(Controller contr) {
        this.contr = contr;
        contr.addSaleObserver(new TotalRevenueView());
    }

    /**
     * Fake sale process (simulation).
     */
    public void fakeSale() {
        contr.startSale();
        System.out.println("\n A new sale has been started");

        fakeAddProduct(11, 1);
        fakeAddProduct(10, 1);
        fakeAddProduct(12, 3);
        fakeAddProduct(13, 1);

        displayed(contr.endSale());
        System.out.println("\nWAIT FOR RECEIPT..");
        contr.payment(600);
        System.out.println(exceptionLog);

        exceptionLog = "\nThe following exceptions have been added to the log: ";
        contr.startSale();
        System.out.println("\n\n*******************************\n\n A new sale has been started");

        fakeAddProduct(13, 1);
        fakeAddProduct(15, 1);
        fakeAddProduct(16, 3);
        fakeAddProduct(18, 1);
        fakeAddProduct(10,1);

        displayed(contr.endSale());
        System.out.println("\nWAIT FOR RECEIPT..");
        contr.payment(200);
        System.out.println(exceptionLog);
    }


    private void fakeAddProduct(int barCode, int quantity) {
        try {
            displayed(contr.addItem(barCode, quantity));
        } catch (BarcodeNotValidException BNVE) {
            handleException("The barcode " + "'" + BNVE.getBarCode() + "'" + " is not valid", BNVE);
        } catch (ItemRegistryException IRE) {
            handleException("The item could not be scanned, try again..", IRE);
        }
    }

    private void handleException(String message, Exception cause) {
        System.out.println("VIEW - [" + createDate() + "] - Cause of exception: " + message);
        addToDevLog(cause);
    }

    private void addToDevLog(Exception exception) {
        if (exception.getCause() != null) {
            exceptionLog += "\nLOG - [" + createDate() + "] - " + exception.getCause().getMessage();
        } else {
            exceptionLog += "\nLOG - [" + createDate() + "] - " + exception.getMessage();
        }
    }

    private String createDate() {
        Date date = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("d MMMM @ hh:mm:ss");
        return dateFormat.format(date);
    }


    private void displayed(DisplayDTO displayDTO) {
        if (displayDTO.getItemSold() != null) {
            System.out.println(displayDTO.getItemSold().getQuantity() +
                    " quantity ║ " + displayDTO.getItemSold().getItem().getItemName() +
                    " price: " + displayDTO.getItemSold().getItem().getItemPrice() +
                    "kr/each " + " ║ Total : " + displayDTO.getTotalAmount() + " kr ");
        } else {
            System.out.println("-----------------------------------\n" + "TOTAL: " + displayDTO.getTotalAmount() + "kr");
        }
    }
}
