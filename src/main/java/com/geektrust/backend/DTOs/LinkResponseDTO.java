package com.geektrust.backend.DTOs;

import java.util.ArrayList;

import com.geektrust.backend.Entities.Funds;

public class LinkResponseDTO {
    
    private ArrayList<Funds> funds;    

    public ArrayList<Funds> getFunds() {
        return funds;
    }    
}
