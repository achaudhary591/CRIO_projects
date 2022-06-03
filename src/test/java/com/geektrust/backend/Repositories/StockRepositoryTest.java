package com.geektrust.backend.Repositories;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.geektrust.backend.Exceptions.FundNotFoundException;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("Repository Test")
public class StockRepositoryTest {
    
    StockRepository stockRepository = new StockRepository();
    
    @Test
    @DisplayName("Checking if the amount of funds in api are equal to te fund method is accessing")
    public void checkingFundSize(){

        assertEquals(10, stockRepository.getFundAndStockMap().keySet().size());
    }

    @Test
    @DisplayName("Checking if the amount of stocks in a particular fund (eg. SBI_LARGE_&_MIDCAP) api are equal to te stocks method is accessing")
    public void checkingStockSizeInGivenFund(){

        String fundName = "SBI_LARGE_&_MIDCAP";
        assertEquals(53, stockRepository.getStocksFromFund(fundName).size());
    }

    @Test
    @DisplayName("Checking if the given fund contains given stock")
    public void checkingAvailabilityOfStockInGivenFund(){

        String fundName = "SBI_LARGE_&_MIDCAP";
        String stockName = "KIRLOSKAR OIL ENGINES LIMITED";
        assertTrue(stockRepository.getStocksFromFund(fundName).contains(stockName));
    }

    @Test
    @DisplayName("Testing size of new stocklist of a fund after addition of stock in a given fund")
    public void addStocksToFundTest(){
        String fundName = "SBI_LARGE_&_MIDCAP";
        String stockName = "NEW BANK LIMITED";
        stockRepository.addStocksToFund(fundName, stockName);
        assertEquals(54, stockRepository.getStocksFromFund(fundName).size());
    }

    @Test
    @DisplayName("Testing size of new stocklist of a fund after addition of stock already exist in a given fund")
    public void addStocksToDublicateFundTest(){
        String fundName = "SBI_LARGE_&_MIDCAP";
        String stockName = "SHEELA FOAM LIMITED";
        stockRepository.addStocksToFund(fundName, stockName);
        assertEquals(53, stockRepository.getStocksFromFund(fundName).size());
    }

    @Test
    @DisplayName("Testign exception handling when passing an Unknown fund and trying to get list")
    public void unknownFundHandlingOnGetStockToFundTest(){

        assertThrows(FundNotFoundException.class, ()->{stockRepository.getStocksFromFund("UNKNOWN_FUND");});
    }

    @Test
    @DisplayName("Testign exception handling when passing an Unknown stock and trying to get list")
    public void unknownFundHandlingOnAddStockToFundTest(){

        assertThrows(FundNotFoundException.class, ()->{stockRepository.addStocksToFund("UNKNOWN_FUND", "NOICL");});
    }
}
