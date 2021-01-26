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
class ChildTest{
   String childName;
   ChildTest(String n2) {
      childName = n2;
   }
   public void display() {
      System.out.println(childName);
      
      int zero = 0;
      int x = 100/0;

      int y = 100 / zero ;

      int d = 837 /3890;
   }

   public void arrayItems(){
       Object[] intArray = new Integer[4];
        intArray [0] = 4;
	intArray [1] = 4.4;
	intArray [2] = "fd";
   }

   public void lists(){
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
public class Test{
   public static void main(String args[]){
//      ChildTest ct1 = new ChildTest("Jai");
//      ChildTest errTwo = (ChildTest) ct1.clone();

      System.out.println(Test.display(null));

      int i =Integer.parseInt("") ;
      System.out.println(i);

      ThRun newTh= new ThRun();
      Thread th= new Thread(newTh, "Thread1");    

      ThRun newTh2= new ThRun();
      Thread th2= new Thread(newTh2, "Thread2");    

      //Stating the thread(Thread2).
      th.start();
      th.start();

   }

    private static boolean display(Object object) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}

class ThRun implements Runnable		    //implementing the Runnable interface
{
	public void run()  //Entry point of new thread
	{
		for(int i=0;i<5;i++)
		{	
			System.out.println(i);	
		}
	}
}