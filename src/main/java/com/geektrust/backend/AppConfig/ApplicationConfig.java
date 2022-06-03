package com.geektrust.backend.AppConfig;

import com.geektrust.backend.Commands.AddStockToFundCommand;
import com.geektrust.backend.Commands.CalculateOverlapCommand;
import com.geektrust.backend.Commands.CurrentPortfolioCommand;
import com.geektrust.backend.Commands.CommandInvoker;
import com.geektrust.backend.Repositories.IStockRepository;
import com.geektrust.backend.Repositories.StockRepository;
import com.geektrust.backend.Services.IPortfolioOverlapService;
import com.geektrust.backend.Services.PortfolioOverlapService;

public class ApplicationConfig {

    
    private final IStockRepository stockRepository = new StockRepository();

    private final IPortfolioOverlapService portfolioOverlapService = new PortfolioOverlapService(stockRepository);

    private final AddStockToFundCommand addStockToFundCommand = new AddStockToFundCommand(portfolioOverlapService);
    private final CalculateOverlapCommand calculateOverlapCommand = new CalculateOverlapCommand(portfolioOverlapService);
    private final CurrentPortfolioCommand currentPortfolioCommand = new CurrentPortfolioCommand(portfolioOverlapService);

    private final CommandInvoker commandInvoker = new CommandInvoker();

    public CommandInvoker getCommandInvoker(){
        
        commandInvoker.register("CURRENT_PORTFOLIO", currentPortfolioCommand);
        commandInvoker.register("CALCULATE_OVERLAP", calculateOverlapCommand);
        commandInvoker.register("ADD_STOCK", addStockToFundCommand);

        return commandInvoker;
    }
    
}
