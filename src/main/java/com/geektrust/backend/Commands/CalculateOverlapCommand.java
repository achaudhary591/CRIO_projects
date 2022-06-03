package com.geektrust.backend.Commands;

import java.util.List;

import com.geektrust.backend.Exceptions.CommandNotFoundException;
import com.geektrust.backend.Services.IPortfolioOverlapService;

public class CalculateOverlapCommand implements ICommand{

    private final IPortfolioOverlapService portfolioOverlapService;    
    
    public CalculateOverlapCommand(IPortfolioOverlapService portfolioOverlapService) {
        this.portfolioOverlapService = portfolioOverlapService;
    }

    @Override
    public void execute(List<String> tokens) throws NullPointerException , CommandNotFoundException {
        // TODO Auto-generated method stub
        
        try{
            String fundForCalculation = tokens.get(1);

            portfolioOverlapService.calculatePortfolioOverlap(fundForCalculation);
        }
        catch(NullPointerException e){
            System.out.println("There is no Command");
        }

    }
    
}
