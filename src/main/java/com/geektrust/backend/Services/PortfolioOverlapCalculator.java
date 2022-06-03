package com.geektrust.backend.Services;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class PortfolioOverlapCalculator {
    
    public String portfolioOverlapCalculate(Set<String> list1 , Set<String> list2){
        
        
        if(list1 == null || list2 == null){
            return "One of list is empty, please check and pass proper lists";
        }
        
        Integer totalStocksCountInBothList = list1.size() + list2.size();
        
        //concatenating both list into one
        List<String> comboList = new ArrayList<String>();
        comboList.addAll(list1);
        comboList.addAll(list2);        
        
        //using hashset we can remove duplicate entries
        HashSet<String> intersectionOfBothStockList = new HashSet<String>(comboList);
        
        //subtracting combination of both list to the intersection of lists to get common list
        Integer commonStocksCountInBothList = totalStocksCountInBothList - intersectionOfBothStockList.size();        
        
        //2*(No of common stocks in A & B)/ (No of stocks in A + No of stocks in B) * 100
        Double overlap = (200 *(double)commonStocksCountInBothList / totalStocksCountInBothList);
        
        //defining decimal Format to get onl 2  digits after decimal
        DecimalFormat decimalFormat = new DecimalFormat("#.##");
        decimalFormat.setMinimumFractionDigits(2);        
        
        return decimalFormat.format(overlap);
    }
}
