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
public class IllegalThreadRunMethodError implements Runnable {

    @Override
    public void run() {
        for (int i = 0; i < 5; i++) {
            System.out.println(i);
        }
    }

    public static void main(String... ar) throws InterruptedException {
        IllegalMonitorState newTh = new IllegalMonitorState();
        Thread th = new Thread(newTh, "Thread2");

        th.start();
        th.run();
        th.run();

    }
}
