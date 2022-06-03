package com.geektrust.backend.Repositories;

import java.util.Set;

import com.geektrust.backend.Exceptions.FundNotFoundException;
import com.geektrust.backend.Exceptions.StockNotFoundException;

public interface IStockRepository {
    
    Set<String> getStocksFromFund(String fundName) throws FundNotFoundException;

    Set<String> addStocksToFund(String fundName , String stockName) throws FundNotFoundException , StockNotFoundException;
}
