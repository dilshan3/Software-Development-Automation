/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.myprojcet.sdaproject.Component3.ProjectTexts;

import java.io.File;

/**
 *
 * @author Sasini Randunuge
 */
public class InvalidFilePathError {

    public static void main(String[] args) {
        File tempFile = new File("c:/temp/temp.txt");
        System.out.println(tempFile);
    }

}
