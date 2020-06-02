package se.kth.iv1350.Seminar3New.view;

import se.kth.iv1350.Seminar3New.model.SaleObserver;

public class TotalRevenueView implements SaleObserver {

    private int amountReceivedTotally;

    public TotalRevenueView() {
        amountReceivedTotally = 0;
    }

    @Override
    public void paymentCompleted(int amountReceived) {
        amountReceivedTotally += amountReceived;
        System.out.println("TOTALREVENUEVIEW - Total Revenue: " + amountReceivedTotally + " kr");
    }
}
