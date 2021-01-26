package com.myprojcet.sdaproject.Component2;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JLabel;
import javax.swing.JProgressBar;

/**
 *
 * @author HP
 */
public class JThread extends Thread{
    
    JProgressBar progBar;
    JLabel label;
    
    public JThread(JProgressBar pBar, JLabel lbl){
        
        this.progBar = pBar;
        this.label = lbl;
    }
    
    public void run(){
        
        int min = 0;
        int max= 50;
        
        progBar.setMinimum(min);
        progBar.setMaximum(max);
        progBar.setValue(0);
        
        for(int i = min; i <= max; i++){
        
            progBar.setValue(i);
            
            try {
                sleep(100);
            } catch (InterruptedException ex) {
                Logger.getLogger(JThread.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }
        
        label.setText("Diagram Generation Successful. Generated diagrams can be found in OUTPUT folder inside project directory");
        label.setVisible(true);
        
        progBar.setValue(min);
    }
    
}
