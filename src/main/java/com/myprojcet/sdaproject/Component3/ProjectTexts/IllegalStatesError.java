/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.myprojcet.sdaproject.Component3.ProjectTexts;

import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

/**
 *
 * @author Sasini Randunuge
 */
public class IllegalStatesError {
       public static void main(String args[]) {
      List list = new LinkedList();
      list.add("Welcome");
      list.add("to");
      list.add("Tutorials");
      list.add("Point");
      ListIterator lIterator = list.listIterator();
      lIterator.next();
      lIterator.remove();  
      lIterator.set("Tutorix"); 
      System.out.println(list);
	
   }
}
