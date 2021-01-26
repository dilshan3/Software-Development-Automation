package com.myprojcet.sdaproject.Component2;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.awt.FlowLayout;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

/**
 *
 * @author HP
 */
public class DisplayImage{
        
        public DisplayImage() {
        }
        
        public void displayImg(String path) throws IOException{
        
            BufferedImage img= ImageIO.read(new File(path));
            ImageIcon icon = new ImageIcon(img);
            JFrame frame= new JFrame();
            frame.setLayout(new FlowLayout());
            frame.setSize(1000, 1200);
            JLabel lbl = new JLabel();
            lbl.setIcon(icon);
            frame.add(lbl);
            frame.setVisible(true);
            frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            frame.setResizable(true);
            
        }
    }