/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.myprojcet.sdaproject.Component3.ProjectTexts;

/**
 *
 * @author Sasini Randunuge
 */
class StockTrading{
   public String getDescription(){
   return "StockTrading";
  }
}

public class ClassNotFound {
   public static void main(String args[]) throws ClassNotFoundException{
      Class.forName("StockTrading");
      System.out.println("StockTrading class successfully loaded");
   }
}
