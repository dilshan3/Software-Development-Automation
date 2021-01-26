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
public class UnsupportedCloningError{
    String name;
 
    UnsupportedCloningError (String name) {
        this.name = name;
        System.out.println(name);
    }
 
    public static void main(String[] args) throws CloneNotSupportedException {
            UnsupportedCloningError errOne = new UnsupportedCloningError ("CloneError");
 
            UnsupportedCloningError errTwo = (UnsupportedCloningError) errOne.clone();
            System.out.println(errTwo);
 
    }

}
