/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.myprojcet.sdaproject.Component3.ProjectTexts;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
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
class Hospital {

    List<Doctor> doctorList = new ArrayList<Doctor>();
    List<Patient> patientList = new ArrayList<Patient>();

    void addDoctor(Doctor o) {
        doctorList.add(o);

    }

    void addPatient(Patient o) {
        patientList.add(o);
    }

    public List<Doctor> showDoctors() {
        return doctorList;
    }

    public List<Patient> showPatients() {
        return patientList;
    }

    public void assignDoctor() {
        for (Patient x : patientList) {
            for (Doctor y : doctorList) {
                if (x.getDisease().equals("eye")) {
                    if (y.getDoctorspeciality().equals("Opthalmologist")) {
                        y.addPatientsToDoctor(x);
                    }
                }
                if (x.getDisease().equals("heart patient")) {
                    if (y.getDoctorspeciality().equals("Surgeon")) {
                        y.addPatientsToDoctor(x);
                    }
                }

                if (x.getDisease().equals("earnose")) {
                    if (y.getDoctorspeciality().equals("ENT")) {
                        y.addPatientsToDoctor(x);
                    }
                }

            }
        }

    }
}

class Doctor {

    private String doctorName;
    private String doctorSpeciality;
    List<Patient> doctorPatientList = new ArrayList<Patient>();

    Doctor(String c, String cc) {
        this.doctorName = c;
        this.doctorSpeciality = cc;

    }

    public String getDoctorName() {
        return doctorName;
    }

    public List<Patient> getDoctorPatientList() {
        return doctorPatientList;
    }

    public void addPatientsToDoctor(Patient o) {
        doctorPatientList.add(o);
    }

    String getDoctorspeciality() {
        return doctorSpeciality;
    }

    public String toString() {
        return (doctorName + "" + doctorSpeciality);
    }

    public void dailyProfit() {
        //Division by zero.
        int totalForWeekp2 = 23450 / 7;
        System.out.println(totalForWeekp2);
    }
}

class Patient {

    private String patientName;
    private int patientAge;
    private String patientGender;
    private String disease;
    private Double height;

    Patient(String patientName, int patientAge, String patientGender, String disease, Double height) {
        this.patientName = patientName;
        this.patientGender = patientGender;
        this.patientAge = patientAge;
        this.disease = disease;
        this.height = height;
    }

    public String getDisease() {
        return disease;
    }

    public String toString() {
        return (patientName + "" + patientAge + "" + patientGender + " " + disease + " " + height);
    }

    public void getUserDetails() {
        //Negative array index.
        int c[] = new int[2];
        for (int i = 0; i <= c.length; i++) {
            System.out.println(i);
        }
    }
}

public class HospitalManagementSystem implements Cloneable{

    public static void main(String args[]) {

        try {
            Hospital h1 = new Hospital();
            int choice = 0;

            //Unsupported cloning error.
            HospitalManagementSystem errOne = new HospitalManagementSystem("CloneError");
            HospitalManagementSystem errTwo = (HospitalManagementSystem) errOne.clone();
            System.out.println(errTwo);

            //Number format error.
            //Need to enter the number of wards
            int i = Integer.parseInt("20");
            System.out.println(i);

            //Illegal states.
            List list = new LinkedList();
            list.add("Welcome");
            list.add("to");
            list.add("Lanka");
            list.add("Genaral");
            ListIterator lIterator = list.listIterator();
            lIterator.next();
//            lIterator.remove();
            lIterator.set("Hospital!");
            System.out.println(list);

            System.out.println("Press 1 to add doctor \n Press 2 to show doctors.\n Press 3 to add patient \n 4 Assign doctor to patients\n 5 Doctor and their patients ");
          
            switch (choice) {
                case 1:
                    break;
                case 2:
                    System.out.println(h1.showDoctors());

                    break;
                case 3:
                    Patient p = new Patient("Steven ", 21, "Male", "eye", 5.1);
                    Patient p1 = new Patient("Michael ", 12, "Male", "heart patient", 4.2);
                    Patient p2 = new Patient("Sara ", 23, "Female", "earnose", 4.3);
                    Patient p3 = new Patient("Amy ", 31, "Female", "earnose", 6.3);
                    Patient p5 = new Patient("Rocky ", 18, "Male", "eye", 5.1);
                    Patient p4 = new Patient("Jessy ", 15, "Male", "heart patient", 4.2);

                    //Illegal arguments.
                    //createRelativePath() method asks for the folder name and file name.
                    System.out.println(HospitalManagementSystem.createRelativePath("folder", "file1"));
                    h1.addPatient(p);
                    h1.addPatient(p1);
                    h1.addPatient(p2);
                    h1.addPatient(p3);
                    h1.addPatient(p4);
                    h1.addPatient(p5);
                    System.out.println(h1.showPatients());

                    //Array storing error.
                    Object[] patientsArray = new String[4];
                    patientsArray[0] = "Fred";
                    patientsArray[1] = "Forty";
                    patientsArray[2] = "Male";
                    patientsArray[3] = "Eye";
//                    patientsArray[4] = 4.1;
                    break;
                case 4:
                    h1.assignDoctor();
                    break;

                case 5:
                    break;
                default:
            }
        } catch (CloneNotSupportedException ex) {
            String s = null;
            Logger.getLogger(HospitalManagementSystem.class.getName()).log(Level.SEVERE, s, ex);
        }
    }

    private HospitalManagementSystem(String cloneError) {
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
