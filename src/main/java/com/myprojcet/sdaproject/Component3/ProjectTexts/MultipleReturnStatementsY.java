/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.myprojcet.sdaproject.Component3.ProjectTexts;

/**
 *
 * @author sasin
 */
public class MultipleReturnStatementsY {

    public static long fibonacci(long number) {
        if ((number == 0) || (number == 1)) { //hvk
            return number;
        } else {
            return fibonacci(number - 1) + fibonacci(number - 2);
        }
    }
//	private double w = 0;

    public static void main(String args[]) {

        for (int count = 0; count <= 10; count++) {
            System.out.println("Fibonacci of " + count + " is " + fibonacci(count));
        }

    }
}
