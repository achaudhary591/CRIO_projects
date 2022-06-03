package com.geektrust.backend.Entities;

import java.util.Set;

public class Funds {
    
    private String name;  

    private Set<String> stocks;   

    public Funds() {
    }

    public Funds(Funds fund){
        this(fund.name , fund.stocks);
    }
    
    public Funds(String name, Set<String> stocks) {
        this.name = name;
        this.stocks = stocks;
    }

    public String getName() {
        return name;
    }  

    public Set<String> getStocks() {
        return stocks;
    }    
}
