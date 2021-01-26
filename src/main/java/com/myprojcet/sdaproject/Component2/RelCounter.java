/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.myprojcet.sdaproject.Component2;

/**
 *
 * @author HP
 */
public class RelCounter {
    
    private Relationship rel;
    private int count;

    public RelCounter() {
    }

    public RelCounter(Relationship rel, int count) {
        this.rel = rel;
        this.count = count;
    }
    
    public Relationship getRel() {
        return rel;
    }

    public void setRel(Relationship rel) {
        this.rel = rel;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
    
    
    
}
