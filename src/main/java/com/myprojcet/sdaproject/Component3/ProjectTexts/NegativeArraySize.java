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
public class NegativeArraySize {

    public static void main(String... ar) {
        int c[] = new int[-2];
        for (int i = 0; i <= c.length; i++) {
            System.out.println(i);
        }

    }

}
