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
public class NumberFormatError {
       public static void main(String args[])
   {
      
 	int i =Integer.parseInt("parsing") ;
	System.out.println(i);
	
	int i2 =Integer.parseInt("") ;
	System.out.println(i2);

	long l2 =Long.parseLong("") ;
	System.out.println(l2);

        double i3 =Double.parseDouble("parsing") ;
	System.out.println(i3);

       
   }
}
