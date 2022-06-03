package com.geektrust.backend.Exceptions;

public class FundNotFoundException extends RuntimeException {
    
    /**
    *
    */   
    String msg = "FUN_NOT_FOUND";
   public FundNotFoundException()
   {
    super();
   }
   public FundNotFoundException(String msg)
   {    
    super(msg);
    
   }
}