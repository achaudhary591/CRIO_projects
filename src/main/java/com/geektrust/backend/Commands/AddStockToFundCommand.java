package com.geektrust.backend.Commands;

import java.util.List;
import java.util.stream.Collectors;

import com.geektrust.backend.Exceptions.CommandNotFoundException;
import com.geektrust.backend.Services.IPortfolioOverlapService;

public class AddStockToFundCommand implements ICommand{

    private final IPortfolioOverlapService portfolioOverlapService;    
    
    public AddStockToFundCommand(IPortfolioOverlapService portfolioOverlapService) {
        this.portfolioOverlapService = portfolioOverlapService;
    }

    @Override
    public void execute(List<String> tokens) throws CommandNotFoundException , NullPointerException {
        // TODO Auto-generated method stub
       
        try{
            String fundName = tokens.get(1);
            
            String stockName = (tokens.subList(2 , tokens.size())).stream().map(Object::toString).collect(Collectors.joining(" "));      


            portfolioOverlapService.addStocksToFund(fundName, stockName);
        }
        catch(NullPointerException e){
            System.out.println("There is no Command");
        }

    }
    
}
