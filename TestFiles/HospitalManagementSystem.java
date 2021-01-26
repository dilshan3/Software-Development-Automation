/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.myprojcet.sdaproject.Component4.TestFiles;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

class Hospital {

    List<Doctor> dList = new ArrayList<Doctor>();
    List<Patient> pList = new ArrayList<Patient>();

    void addDoctor(Doctor o) {
        dList.add(o);

    }

    void addPatient(Patient o) {
        pList.add(o);
    }

    public List<Doctor> showDoctors() {
        return dList;
    }

    public List<Patient> showPatients() {
        return pList;
    }

    public void assignDoctor() {
        for (Patient x : pList) {
            for (Doctor y : dList) {
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
        int days = 0;
        int totalForWeekp1 = 23450 / days;
        System.out.println(totalForWeekp1);
        int totalForWeekp2 = 23450 / 0;
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
        int c[] = new int[-2];
        for (int i = 0; i <= c.length; i++) {
            System.out.println(i);
        }
    }
}

public interface FibonacciMethod {
	public String fibonacci(long number);
	private int wowowowo();
	public int results();
}

public class HospitalManagementSystem {

    public static void main(String args[]) {
	try {
		
		List<String> stringList = new ArrayList<String>();
		
	    if (a && (y == b)) {
			if (y == x) {
				while (true) {
					if (x++ < 20) {
						break;
					}
				}
			} else if (y == t && !d) {
				System.out.println(x = a ? y : x);
			} else {
				System.out.println(x = a ? y : x = a ? y : x);
			}
	    }

        } catch (CloneNotSupportedException ex) {
            String s = null;
            Logger.getLogger(HospitalManagementSystem.class.getName()).log(Level.SEVERE, s, ex);
        }
    }

    private HospitalManagementSystem(String cloneError) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools Templates.
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
