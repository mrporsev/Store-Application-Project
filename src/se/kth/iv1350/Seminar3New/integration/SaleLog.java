package se.kth.iv1350.Seminar3New.integration;

import se.kth.iv1350.Seminar3New.model.dto.SaleInfoDTO;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

/**
 * The sale log contains information about each finished sale.
 */
public class SaleLog {

    private List<SaleInfoDTO> completedSales = new ArrayList<SaleInfoDTO>();

    /**
     * Adds sale to completed sales.
     * @param saleInfo The information about the completed sale.
     */
    public void addSaleToSaleLog(SaleInfoDTO saleInfo) {
        completedSales.add(saleInfo);
    }
}
