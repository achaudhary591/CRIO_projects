package com.geektrust.backend.Commands;

import java.util.Arrays;
import java.util.List;

import com.geektrust.backend.Exceptions.CommandNotFoundException;
import com.geektrust.backend.Services.IPortfolioOverlapService;

public class CurrentPortfolioCommand implements ICommand{

    private final IPortfolioOverlapService portfolioOverlapService;    
    
    public CurrentPortfolioCommand(IPortfolioOverlapService portfolioOverlapService) {
        this.portfolioOverlapService = portfolioOverlapService;
    }



    @Override
    public void execute(List<String> tokens) throws NullPointerException , CommandNotFoundException{
        // TODO Auto-generated method stub
        try{
            String[] temp = new String[tokens.size()];
            tokens.toArray(temp);
            String[] result = Arrays.copyOfRange(temp, 1, temp.length);

            portfolioOverlapService.currentPortfolioStocks(result);       
        }
        catch(NullPointerException e){
            System.out.println("There is no Command");
        }
    
    }
    
}
