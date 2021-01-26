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
public class IllegalThreadStateError implements Runnable{

    public void run() //Entry point of new thread
    {
        for (int i = 0; i < 5; i++) {
            System.out.println(i);
        }
    }

    public static void main(String... ar) throws InterruptedException {
        IllegalThreadStateError newTh = new IllegalThreadStateError();
        Thread th = new Thread(newTh, "Thread2");

//Stating the thread(Thread2).
        th.start();
        th.start();

        th.sleep(1000);
        th.sleep(1000);

    }
}
