package com.geektrust.backend.Entities;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("Funds Test")
public class FundsTest {

    @Test
    @DisplayName("Check whether stock exist return true if stock exist in fund")

    public void chekcStockExist(){
        String name = "AXIS_SECURITY";
        Set<String> stocks = new HashSet<String>();
        stocks.add("SBI");
        stocks.add("HYUNDAI");
        stocks.add("MARUTI");
        stocks.add("PORTRONICS");

        Funds fund = new Funds(name , stocks);
        String testStock = "MARUTI";
        assertTrue(fund.getStocks().contains(testStock));
                    
    }

    @Test
    @DisplayName("Check whether stock doesn't exist return false if stock doesn't exist in fund")

    public void chekcStockDoesntExist(){
        String name = "AXIS_SECURITY";
        Set<String> stocks = new HashSet<String>();
        stocks.add("SBI");
        stocks.add("HYUNDAI");
        stocks.add("MARUTI");
        stocks.add("PORTRONICS");

        Funds fund = new Funds(name , stocks);
        String testStock = "LAMBO";
        assertFalse(fund.getStocks().contains(testStock));;
                    
    }
    
}
