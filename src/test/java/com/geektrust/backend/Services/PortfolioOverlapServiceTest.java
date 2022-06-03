package com.geektrust.backend.Services;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.AbstractMap;
import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.Map;
import java.util.Set;

import com.geektrust.backend.Exceptions.FundNotFoundException;
import com.geektrust.backend.Exceptions.StockNotFoundException;
import com.geektrust.backend.Repositories.IStockRepository;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("Portfolio Services stubs test")
public class PortfolioOverlapServiceTest {
    
    IStockRepository mockStockRepository = new MockStockRepositories();
    PortfolioOverlapService mockPortfolioOverlapService = new PortfolioOverlapService(mockStockRepository);

    //reading output from console ref:https://stackoverflow.com/questions/8708342/redirect-console-output-to-string-in-java
    private final ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
    // IMPORTANT: Save the old System.out!
    private final PrintStream printStream = System.out;

    @BeforeEach
    public void setOutput(){
        
        // Tell Java to use your special stream
        System.setOut(new PrintStream(byteArrayOutputStream));
    }

    @AfterEach
    public void flushOutput(){
        // Put things back
        System.out.flush();
        System.setOut(printStream);        
    }

    @Test
    @DisplayName("Testing if currentPortfolioService method setting up new fund or not")
    
    public void CurrentPortfolioStocksTest(){
        
        String[] fundList = {"UTI_NIFTY_INDEX","AXIS_MIDCAP", "PARAG_PARIKH_FLEXI_CAP"};
        mockPortfolioOverlapService.currentPortfolioStocks(fundList);
        assertEquals(3, mockPortfolioOverlapService.getFundName().length);
        assertEquals("UTI_NIFTY_INDEX", mockPortfolioOverlapService.getFundName()[0]);
    }

    @Test
    @DisplayName("Testing calculatePortfolioOverlap percent when funds are in list")
    
    public void calculatePortfolioOverlapWhenFundsAreInListTest(){

        String[] fundList = {"UTI_NIFTY_INDEX","AXIS_MIDCAP", "PARAG_PARIKH_FLEXI_CAP"};
        mockPortfolioOverlapService.currentPortfolioStocks(fundList);
        String fundForCalculation = "MIRAE_ASSET_LARGE_CAP";
        mockPortfolioOverlapService.calculatePortfolioOverlap(fundForCalculation);
        assertEquals("MIRAE_ASSET_LARGE_CAP UTI_NIFTY_INDEX 50.00%\n"+"MIRAE_ASSET_LARGE_CAP AXIS_MIDCAP 28.57%\n", byteArrayOutputStream.toString());
    }

    @Test
    @DisplayName("Testing calculatePortfolioOverlap percent when funds are not in list")
    
    public void calculatePortfolioOverlapWhenFundsAreNotInListTest(){

        String[] fundList = {"UTI_NIFTY_INDEX","AXIS_MIDCAP", "PARAG_PARIKH_FLEXI_CAP"};
        mockPortfolioOverlapService.currentPortfolioStocks(fundList);
        String fundForCalculation = "ROCKSTAR_GAMING";
        mockPortfolioOverlapService.calculatePortfolioOverlap(fundForCalculation);
        assertEquals("FUND_NOT_FOUND\n", byteArrayOutputStream.toString());
    }

    @Test
    @DisplayName("Testing calculatePortfolioOverlap percent when overlap percent is zero")
    
    public void calculatePortfolioOverlapWhenFundsWhenOverlapPercentIsZeroTest(){

        String[] fundList = {"UTI_NIFTY_INDEX","AXIS_MIDCAP", "PARAG_PARIKH_FLEXI_CAP"};
        mockPortfolioOverlapService.currentPortfolioStocks(fundList);
        String fundForCalculation = "MIRAE_ASSET_LARGE_CAP";
        mockPortfolioOverlapService.calculatePortfolioOverlap(fundForCalculation);
        assertEquals("MIRAE_ASSET_LARGE_CAP UTI_NIFTY_INDEX 50.00%\n"+"MIRAE_ASSET_LARGE_CAP AXIS_MIDCAP 28.57%\n", byteArrayOutputStream.toString());
    }

    @Test
    @DisplayName("Testing addition of  Stocks to funds of User")

    public void addStocksToFundTest(){

        String[] fundList = {"UTI_NIFTY_INDEX","AXIS_MIDCAP", "PARAG_PARIKH_FLEXI_CAP"};
        mockPortfolioOverlapService.currentPortfolioStocks(fundList);

        String fundName = "UTI_NIFTY_INDEX";
        String stockName = "NOICL";

        mockPortfolioOverlapService.addStocksToFund(fundName, stockName);

        String fundForCalculation = "SBI_LARGE_&_MIDCAP";
        mockPortfolioOverlapService.calculatePortfolioOverlap(fundForCalculation);

        assertEquals("SBI_LARGE_&_MIDCAP UTI_NIFTY_INDEX 25.00%\n"
        +"SBI_LARGE_&_MIDCAP AXIS_MIDCAP 33.33%\n", byteArrayOutputStream.toString());
    }

    @Test
    @DisplayName("Testing addition of  Stocks to funds of User when stock name have space in between eg HDFC BANK LIMITED")

    public void addStocksToFundWhenStockHaveSpacesInBetweenTest(){

        String[] fundList = {"UTI_NIFTY_INDEX","AXIS_MIDCAP", "PARAG_PARIKH_FLEXI_CAP"};
        mockPortfolioOverlapService.currentPortfolioStocks(fundList);

        String fundName = "UTI_NIFTY_INDEX";
        String stockName = "HDFC BANK LIMITED";

        mockPortfolioOverlapService.addStocksToFund(fundName, stockName);

        String fundForCalculation = "SBI_LARGE_&_MIDCAP";
        mockPortfolioOverlapService.calculatePortfolioOverlap(fundForCalculation);

        assertEquals("SBI_LARGE_&_MIDCAP UTI_NIFTY_INDEX 25.00%\n"
        +"SBI_LARGE_&_MIDCAP AXIS_MIDCAP 33.33%\n", byteArrayOutputStream.toString());
    }

}
class MockStockRepositories implements IStockRepository{

    Map<String , Set<String>> fundsAndStockMap = Map.ofEntries(
        new AbstractMap.SimpleEntry("UTI_NIFTY_INDEX", Arrays.asList("INFOSYS LIMITED","EPL LIMITED", "ICICI BANK LIMITED", "ICICI LOMBARD GENERAL INSURANCE COMPANY LIMITED",
        "BHARTI AIRTEL LIMITED").stream().collect(Collectors.toSet())),
        new AbstractMap.SimpleEntry("AXIS_MIDCAP", Arrays.asList("JK CEMENT LIMITED",
        "INFOSYS LIMITED", "SANOFI INDIA LIMITED", "BHARTI AIRTEL LIMITED").stream().collect(Collectors.toSet())),
        new AbstractMap.SimpleEntry("PARAG_PARIKH_FLEXI_CAP", Arrays.asList("FACEBOOK INC",
        "MICROSOFT CORPORATION").stream().collect(Collectors.toSet())),
        new AbstractMap.SimpleEntry("MIRAE_ASSET_LARGE_CAP", Arrays.asList("EPL LIMITED",
                "INFOSYS LIMITED","OIL & NATURAL GAS CORPORATION LIMITED").stream().collect(Collectors.toSet())),
        new AbstractMap.SimpleEntry("SBI_LARGE_&_MIDCAP", Arrays.asList("INFOSYS LIMITED",
        "TTK PRESTIGE LIMITED").stream().collect(Collectors.toSet()))
);

    @Override
    public Set<String> getStocksFromFund(String fundName) throws FundNotFoundException {
        // TODO Auto-generated method stub
        Set<String> stockListOfFund = this.fundsAndStockMap.get(fundName);
        if(stockListOfFund == null){
            throw new FundNotFoundException("STOCKS_NOT_FOUND");
        }
        return stockListOfFund;
    }

    @Override
    public Set<String> addStocksToFund(String fundName, String stockName)
            throws FundNotFoundException, StockNotFoundException {
        // TODO Auto-generated method stub
        Set<String> updatedStockList = getStocksFromFund(fundName);
        if(updatedStockList == null){
            throw new StockNotFoundException("STOCKS_NOT_FOUND");
        }
        updatedStockList.add(stockName);
        return updatedStockList;
    }    
}
