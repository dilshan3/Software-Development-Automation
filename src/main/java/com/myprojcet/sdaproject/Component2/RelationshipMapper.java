/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.myprojcet.sdaproject.Component2;

import java.util.ArrayList;
import java.util.List;


/**
 *
 * @author HP
 */
public class RelationshipMapper {
    
    public List mapToXl(List<String> inputList){
        List<Relationship> relList =new ArrayList<>();
        Relationship relationship; 
        int j = 3;
        String currentString[]; //to store the current relationship
        String classa, classb;

        
        for(int i=0; i < inputList.size(); i++){
            
            currentString=inputList.get(i).split("\\s"); //the inputList has strings with two classes and the similarity level
            
            //a new relationship is made for each item in the list
            relationship = new Relationship();
            classa= currentString[0].replaceAll("\\s", "").substring(0, 1).toUpperCase() + currentString[0].replaceAll("\\s", "").substring(1);
            classb= currentString[2].replaceAll("\\s", "").substring(0, 1).toUpperCase() + currentString[2].replaceAll("\\s", "").substring(1);
            
            
            relationship.setClassa(classa);
            relationship.setSimLevel(Float.parseFloat(currentString[1].replaceAll("\\s", "")));
            relationship.setClassb(classb);
            
            
            relList.add(relationship);
            
            
        }
        
        return relList;
    } 
    
}
