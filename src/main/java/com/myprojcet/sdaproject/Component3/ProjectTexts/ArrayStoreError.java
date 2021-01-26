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
public class ArrayStoreError {

    public static void main(String[] args) {
        Object[] intArray = new Integer[4];
        intArray[0] = 4;
        intArray[1] = 4.4;
        intArray[2] = 'd';
        intArray[3] = "fd";
        
        Object[] doubleArray = new Double[4];
        doubleArray[0] = 4;
        doubleArray[1] = 4.4;
        doubleArray[2] = "fd";
        doubleArray[3] = 's';

        Object[] stringArray = new String[4];
        stringArray[0] = 4;
        stringArray[1] = 4.4;
        stringArray[2] = "fd";
        stringArray[3] = 's';

        Object[] fltArray = new Float[4];
        fltArray[0] = 4f;
        fltArray[1] = 4.4f;
        fltArray[2] = "fd";
        fltArray[3] = 's';

        Object[] lngArray = new Long[4];
        lngArray[0] = 12332252626L;
        lngArray[1] = 4.4;
        lngArray[2] = "fd";
        lngArray[3] = 's';
    }
}
