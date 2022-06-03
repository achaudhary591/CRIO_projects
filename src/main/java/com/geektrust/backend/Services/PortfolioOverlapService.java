package com.geektrust.backend.Services;


import com.geektrust.backend.Exceptions.FundNotFoundException;
import com.geektrust.backend.Exceptions.StockNotFoundException;
import com.geektrust.backend.Repositories.IStockRepository;


public class PortfolioOverlapService implements IPortfolioOverlapService{

    private String[] fundNames;
    private IStockRepository stockRepository;
    
    
    private final PortfolioOverlapCalculator portfolioOverlapCalculator = new PortfolioOverlapCalculator();

    public PortfolioOverlapService(){

    }

    public PortfolioOverlapService(IStockRepository stockRepository){
        this.stockRepository = stockRepository;
    }

    @Override
    public void currentPortfolioStocks(String[] fundList) throws FundNotFoundException {
        // TODO: adding given funds to user's portfolio
        if(fundList == null){
            throw new FundNotFoundException("FUND_NOT_FOUND");
        }
        this.fundNames = fundList;        
    }

    @Override
    public void calculatePortfolioOverlap(String fundForCalculation) {
        // TODO: calculating overlap and overlap percent of given funds with the funds user having currently
        try{
            for (String fund : this.fundNames) {
                
                String overlapPercentage = portfolioOverlapCalculator.portfolioOverlapCalculate(
                    stockRepository.getStocksFromFund(fund) , stockRepository.getStocksFromFund(fundForCalculation)
                );

                if(Double.parseDouble(overlapPercentage) > 0)
                    System.out.println(fundForCalculation+" "+fund+" "+overlapPercentage+"%");
                else    
                  continue;
            }
        }
        catch(FundNotFoundException e){
            System.out.println("FUND_NOT_FOUND");
        }      
    }


    @Override
    public void addStocksToFund(String fundName , String stockName) throws FundNotFoundException ,StockNotFoundException {
        // TODO:Adding the fund name to which the new stock will be added and the name of the new stock.
        try{        
            stockRepository.addStocksToFund(fundName, stockName);
        }
        catch(RuntimeException e){
            System.out.println(e.getStackTrace());
        }
    }

    public String[] getFundName(){
        
        for (String string : fundNames) {
            System.out.println(string);
        }
        return fundNames;
    }
    
}
