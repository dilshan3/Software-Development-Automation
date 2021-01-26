/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.myprojcet.sdaproject.Component3.ProjectTexts;

import java.io.File;
import java.util.ArrayList;
import java.util.Map;
import java.util.Scanner;
import javax.swing.JFileChooser;

/**
 *
 * @author sasin
 */
public class MultipleReturnStatementsN {

    private static ArrayList<String> countWordsWithArrayList() {
        String numWords = " ";

        System.out.println("Total number of words: " + numWords);
        System.out.println("number of distincy words: " + result.size());
        ArrayList<String> result = null;
        return result;
    }

    private static Map<String, Integer> countWordsWithMap() {
        String numWords = " ";

        System.out.println("Total number of words: " + numWords);
        System.out.println("number of distincy words: " + result.size());
        Map<String, Integer> result = null;
        return result;
    }

    private static File getFile() {

        JFileChooser chooser = new JFileChooser(".");
        chooser.setDialogTitle("Select File To Count Words:");
        int retval = chooser.showOpenDialog(null);
        File f = null;
        chooser.grabFocus();
        if (retval == JFileChooser.APPROVE_OPTION) {
            f = chooser.getSelectedFile();
        }
        return f;
    }

    public static void main(String[] args) {
        countWordsWithArrayList();
        countWordsWithMap();
        getFile();
    }

}
