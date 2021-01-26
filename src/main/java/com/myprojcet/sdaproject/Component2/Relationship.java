package com.myprojcet.sdaproject.Component2;

import org.apache.commons.math3.util.Precision;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */




/**
 *
 * @author HP
 */

//This class is used to represent a relationship
public class Relationship {
    
    private String classa, classb, relationship;
    private float simLevel;

    public Relationship() {
    }

    
    public Relationship(String classa, String classb, String relationship, float similarity) {
        this.classa = classa;
        this.classb = classb;
        this.relationship = relationship;
        this.simLevel = Precision.round(similarity, 2);
    }
    
    public Relationship(String classa, String classb, float similarity) {
        this.classa = classa;
        this.classb = classb;
        this.simLevel = Precision.round(similarity, 2);
    }

    Relationship(String classa, String classb) {
        this.classa = classa;
        this.classb = classb;
    }

    public float getSimLevel() {
        return simLevel;
    }

    public void setSimLevel(float simLevel) {
        this.simLevel = Precision.round(simLevel, 2);
    }

    public String getClassa() {
        return classa;
    }

    public void setClassa(String classa) {
        this.classa = classa;
    }

    public String getClassb() {
        return classb;
    }

    public void setClassb(String classb) {
        this.classb = classb;
    }

    public String getRelationship() {
        return relationship;
    }

    public void setRelationship(String relationship) {
        this.relationship = relationship;
    }
    
}
