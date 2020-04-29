package se.kth.iv1350.Seminar3New.view;

import se.kth.iv1350.Seminar3New.controller.Controller;
import se.kth.iv1350.Seminar3New.model.dto.DisplayDTO;

/**
 * This is placeholder for the real view. It contains a hardcoded execution with calls to all
 * system operations in the controller.
 */
public class View {
    private Controller contr;

    /**
     * Creates new instance, that uses the specified controller for all calls to other layers.
     *
     * @param contr The controller to use for all calls to other layers.
     */
    public View(Controller contr) {

        this.contr = contr;
    }

    /**
     * Fake sale process (simulation).
     */
    public void fakeSale() {
        contr.startSale();
        System.out.println("\n A new sale has been started");
        display(contr.addItem(10, 1));
        display(contr.addItem(11, 1));
        display(contr.addItem(12, 1));
        contr.payment(600);
    }

    private void display(DisplayDTO displayDTO) {
        if (displayDTO.getErrorMessage() != null) {
            System.out.println(displayDTO.getErrorMessage());
        } else if (displayDTO.getItemSold() != null) {
            System.out.println(displayDTO.getItemSold().getQuantity() +
                    " quantity ║ " + displayDTO.getItemSold().getItem().getItemName() +
                    " price: " + displayDTO.getItemSold().getItem().getItemPrice() +
                    "kr/each " + " ║ Total : " + displayDTO.getTotalAmount() + " kr ");        } else {
            System.out.println("********************" + "\n" + "\nTotal price: " + displayDTO.getTotalAmount());
        }
    }
}
