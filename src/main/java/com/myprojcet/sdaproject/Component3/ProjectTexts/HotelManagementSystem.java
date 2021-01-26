/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.myprojcet.sdaproject.Component3.ProjectTexts;

import java.io.File;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Sasini Randunuge
 */
class Room {

    private Room[] room = new Room[20];
    private int roomNum;
    private double rate;
    private String type;
    private String roomStatus;
    private int numRoomsSingle = 0;
    private int numRoomDouble = 0;
    private int numRoomsO = 0;

    public Room() {
    }//no-arg constructor

    public Room(int n, double r, String t, String s) {
        roomNum = n;
        rate = r;
        type = t;
        roomStatus = s;
    }// Room constructor made to initialise variables.

    public Room[] getRoom() {
        return room;
    }

    public double getRoomNum() {
        return roomNum;
    }

    public double getRate() {
        return rate;
    }

    public String getType() {
        return type;
    }

    public String getRoomStatus() {
        return roomStatus;
    }

    public int getNumRoomsO() {
        return numRoomsO;
    }

    public void setRate(double rate) {
        if (type.equals("single")) {
            numRoomsSingle++;
        } else if (type.equals("double")) {
            numRoomDouble++;
        }

    }

    public void setRoomStatus(String s) {
        roomStatus = "occupied";
    }//end set room status

    public void dailyProfit() {
        //Division by zero.
        int totalForWeekp2 = 23450 / 0;
        System.out.println(totalForWeekp2);
        

    }

}//end class Room

class Customer {

    private Customer[] customer = new Customer[40];
    private int numCus;
    private String name;
    private double paymentType;

    public Customer() {
    }//no-arg constructor

    public Customer(String n, double p) {
        name = n;
        paymentType = p;
        numCus++;
    }//end constructor

    public String getName() {
        return name;
    }

    public Customer[] getCustomer() {
        return customer;
    }

    public void getCustomerDetails() {
        //Negative array index.
        int c[] = new int[-2];
        for (int i = 0; i <= c.length; i++) {
            System.out.println(i);
        }
    }

    public void printCus() {
        System.out.printf("Customer name: %s", name);
        System.out.printf("Customer payment type: %s", paymentType);
    }

}//end class Customer

public class HotelManagementSystem {

    public static void main(String[] args) {

        try {

            //Unsupported cloning error.
            HotelManagementSystem errOne = new HotelManagementSystem("CloneError");
            HotelManagementSystem errTwo = (HotelManagementSystem) errOne.clone();
            System.out.println(errTwo);

          

            //Number format error.
            //Should enter the number of rooms here!
            int i = Integer.parseInt("FortyOne");
            System.out.println(i);

            //Illegal states.
            List list = new LinkedList();
            list.add("Welcome");
            list.add("to");
            list.add("Queens");
            list.add("Hotel");
            ListIterator lIterator = list.listIterator();
            lIterator.next();
            lIterator.remove();
            lIterator.set("SriLanka...");
            System.out.println(list);

            System.out.printf("Please select an option from the followin menu, 0 to exit");
            //Illegal arguments.
            //createRelativePath() method asks for the parent folder and the file name.
            System.out.println(HotelManagementSystem.createRelativePath(null, "file1"));
            System.out.printf("The number of rooms currently occupied, select 1 %n");
            System.out.printf("The number of available rooms, select 2 %n");
            System.out.printf("The names of all the customers who stayed in room, select 3 %n");
            System.out.printf("Room status of your choice,select 4 %n");

            System.out.printf("Please select an option from the followin menu, 0 to exit");

            System.out.printf("The number of rooms currently occupied, select 1 %n");
            System.out.printf("The number of available rooms, select 2 %n");
            System.out.printf("The names of all the customers who stayed in room, select 3 %n");
            System.out.printf("Room status of your choice,select 4 %n");

            //Array storing error.
            Object[] roomArray = new String[4];
            roomArray[0] = 001;
            roomArray[1] = "Villa";
            roomArray[2] = "Luxary";
            roomArray[3] = "Occupied";
            roomArray[4] = 4.1; //Ratings

        } //end main
        catch (CloneNotSupportedException ex) {
            String s = null;
            Logger.getLogger(HotelManagementSystem.class.getName()).log(Level.SEVERE, s, ex);
        }

    }

    private HotelManagementSystem(String cloneError) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public static String createRelativePath(String parent, String filename) {
        if (parent == null) {
            System.out.println("The parent path cannot be empty!");
        }

        if (filename == null) {
            System.out.println("The filename cannot be empty!");
        }

        return parent + File.separator + filename;
    }
}
