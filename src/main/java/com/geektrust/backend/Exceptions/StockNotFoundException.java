package com.geektrust.backend.Exceptions;

public class StockNotFoundException extends RuntimeException {
    
    /**
    *
    */   

   public StockNotFoundException()
   {
    super();
   }
   public StockNotFoundException(String msg)
   {
    super(msg);
   }
}