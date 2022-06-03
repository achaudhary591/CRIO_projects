package com.geektrust.backend.Services;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("Portfolio Calculation Test")
public class PortfolioCalculatorOverlapTest {
    
    PortfolioOverlapCalculator poc = new PortfolioOverlapCalculator();

    @Test
    @DisplayName("tesiting overlap percentage returning correct value")

    public void portfolioOverlapCalculateTest(){

        Set<String> stockList1 = Arrays.asList("GTA", "ROCKSTAR", "FINTECH", "BIOTECH","CRUELGAMING").stream().collect(Collectors.toSet());
        Set<String> stockList2 = Arrays.asList("COD", "SEGASTAR", "CITRUSSPORT", "FINTECH", "GTA","SINISTER").stream().collect(Collectors.toSet());

        String overlap = poc.portfolioOverlapCalculate(stockList1, stockList2);
        System.out.println(overlap);
        assertEquals("36.36", overlap);
    }

    @Test
    @DisplayName("testing if one of list of stock is null")
    public void portfolioOverlapCalculateNullTest(){

        Set<String> stockList1 = Arrays.asList("GTA", "ROCKSTAR", "FINTECH", "BIOTECH","CRUELGAMING").stream().collect(Collectors.toSet());
        Set<String> stockList2 = null;//Arrays.asList("COD", "SEGASTAR", "CITRUSSPORT", "FINTECH", "GTA","SINISTER").stream().collect(Collectors.toSet());

        String overlap = poc.portfolioOverlapCalculate(stockList1, stockList2);
        String expectedOut = "One of list is empty, please check and pass proper lists";
        assertEquals(expectedOut, overlap);
    }
}
