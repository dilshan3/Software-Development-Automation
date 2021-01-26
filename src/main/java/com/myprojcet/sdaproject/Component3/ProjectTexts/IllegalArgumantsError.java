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
public class IllegalArgumantsError {
        public static String createRelativePath(String parent, String filename) {
        if(parent == null)
            System.out.println("The parent path cannot be empty!");
         
        if(filename == null)
            System.out.println("The filename cannot be empty!");
         
        return parent + File.separator + filename;
    }
     
    public static void main(String[] args) {
	
        // The following command will be successfully executed.
        System.out.println(IllegalArgumantsError.createRelativePath("dir1", "file1"));
        System.out.println();
         
        // The following command throws an IllegalArgumentError.
        System.out.println(IllegalArgumantsError.createRelativePath(null, "file1"));
    }
}
