/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.myprojcet.sdaproject.Component2;

import com.myprojcet.sdaproject.Component2.Relationship;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import net.sourceforge.plantuml.FileFormat;
import net.sourceforge.plantuml.FileFormatOption;
import net.sourceforge.plantuml.SourceStringReader;

/**
 *
 * @author HP
 */
public class Generator {
    
    //relationship types stored as constants for generated diagrams
    private final String CONSTIN =  "inherits";
    private final String CONSTAS =  "associates";
    private final String CONSTCOM =  "composes";
    private final String CONSTAGG =  "aggregates";
    private final String CONSTDIRAS =  "directional associates";
    private final String CONSTIM =  "implements";
    private final String CONSTDEP =  "depends on";
    
    //file paths stored as constants for generated diagrams
    private static final String CONSTPATHCLASS =  ".\\OUTPUT\\resultClassDiagram.png";
    private static final String CONSTPATHFLOW =  ".\\OUTPUT\\resultFlowDiagram.png";
    private static final String CONSTPATHOBJECT =  ".\\OUTPUT\\resultObjectDiagram.png";
    private static final String CONSTPATHER =  ".\\OUTPUT\\resultERDiagram.png";
    
    
    //method to assign different UML relationships based on the similarity level between classes
    public void assignRelationshipType(List<Relationship> reList){
        
        reList.forEach((r) ->{
        
            if(r.getSimLevel() >= 80 && r.getSimLevel() < 100){
            
                r.setRelationship(CONSTIN);
            }
            else if(r.getSimLevel() >= 70 && r.getSimLevel() < 80){
            
                r.setRelationship(CONSTCOM);
            }
            else if(r.getSimLevel() >= 60 && r.getSimLevel() < 70){
            
                r.setRelationship(CONSTAGG);
            }
            else if(r.getSimLevel() >= 25 && r.getSimLevel() < 60){
            
                r.setRelationship(CONSTAS);
            }
            else{
                r.setRelationship("No relationship");
            }
        });
        
    }
    
    //Method to generate Class diagram
    public void generateClassDiagram(List<Relationship> reList) throws IOException {
    
        assignRelationshipType(reList);
        
        StringBuilder plantUmlSource = new StringBuilder();

        plantUmlSource.append("@startuml\n");
        
        //Mapping relationships one by one using plantUML syntax
        reList.forEach((r) -> {
            if(r.getRelationship().equalsIgnoreCase(CONSTIN)){
                
                plantUmlSource.append(r.getClassb()).append(" <|-- ").append(r.getClassa()).append("\n");
            }
            else if(r.getRelationship().equalsIgnoreCase(CONSTAGG)){
                plantUmlSource.append(r.getClassb()).append(" o-- ").append(r.getClassa()).append("\n");
            }
            else if(r.getRelationship().equalsIgnoreCase(CONSTCOM)){
                plantUmlSource.append(r.getClassb()).append(" *-- ").append(r.getClassa()).append("\n");
            }
            else if(r.getRelationship().equalsIgnoreCase(CONSTIM)){
                plantUmlSource.append(r.getClassb()).append(" <|.. ").append(r.getClassa()).append("\n");
            }
            else if(r.getRelationship().equalsIgnoreCase(CONSTAS)){
                plantUmlSource.append(r.getClassb()).append(" -- ").append(r.getClassa()).append("\n");
            }
            else if(r.getRelationship().equalsIgnoreCase(CONSTDIRAS)){
                plantUmlSource.append(r.getClassb()).append(" <-- ").append(r.getClassa()).append("\n");
            }
            else if(r.getRelationship().equalsIgnoreCase(CONSTDEP)){
                plantUmlSource.append(r.getClassb()).append(" <.. ").append(r.getClassa()).append("\n");
            }
        });
        
        plantUmlSource.append("@enduml");
        
        //Diagram structure converted to a string to generate diagram
        SourceStringReader reader = new SourceStringReader(plantUmlSource.toString());

        //Diagram generated and stored as a PNG
        FileOutputStream output = null;
        try {
            output = new FileOutputStream(new File(CONSTPATHCLASS));
            
        } catch (FileNotFoundException ex) {
            
            JFrame j = new JFrame();
            JOptionPane.showMessageDialog(j,"File Path not found.","Error", JOptionPane.ERROR_MESSAGE);  
            Logger.getLogger(Generator.class.getName()).log(Level.SEVERE, null, ex);
            return;
        }

        reader.outputImage(output, new FileFormatOption(FileFormat.PNG, false));
        
    }
    
    
    //Method to create Object diagram
    public void generateObjectDiagram(List<Relationship> reList) throws IOException {
    
        assignRelationshipType(reList);
        List<String> classes = new ArrayList<>();
        Map<String, String> inheriClasses = new HashMap<>();
        int aggre = 0;
        
        //Identifying objects to be created and filtering out superclasses
        reList.forEach((r) -> {
        
            if((classes.contains(r.getClassa())) && (classes.contains(r.getClassb()))){
                
                if(r.getRelationship().equalsIgnoreCase(CONSTIN) || r.getRelationship().equalsIgnoreCase(CONSTIM)){
                    inheriClasses.put(r.getClassa(), r.getClassb());
                }
                
            }
            else if((!classes.contains(r.getClassa())) || (!classes.contains(r.getClassb()))){
                    
                if((!classes.contains(r.getClassa())) && (!classes.contains(r.getClassb()))){
                        classes.add(r.getClassa());
                        classes.add(r.getClassb());
                }
                else if((!classes.contains(r.getClassa()))){
                        classes.add(r.getClassa());
                }
                else if((!classes.contains(r.getClassb()))){
                        classes.add(r.getClassb());
                }
 
                if(r.getRelationship().equalsIgnoreCase(CONSTIN) || r.getRelationship().equalsIgnoreCase(CONSTIM)){
                    inheriClasses.put(r.getClassa(), r.getClassb());
                }
                
            }
        });

        //Removing super classes from the objects to be drawn
        for(String s: classes){
        
            if(inheriClasses.containsValue(s)){
                classes.set(classes.indexOf(s), null);
            }
        }
        
        StringBuilder plantUmlSource = new StringBuilder();

        plantUmlSource.append("@startuml\n");
        
        classes.forEach((c) -> {
        
            if(c !=  null){
                plantUmlSource.append("object ").append(c.toLowerCase()).append("\n");
            }
            
        }); 
         

        //Mapping relationships to diagram
        for(Relationship r: reList){
            
            if((!r.getRelationship().equalsIgnoreCase(CONSTIN) && !r.getRelationship().equalsIgnoreCase(CONSTIM)) && 
                    ((inheriClasses.containsValue(r.getClassa())) || (inheriClasses.containsValue(r.getClassb())))){
            
                for(Entry<String, String> e: inheriClasses.entrySet()){
                
                    if(Objects.equals(e.getValue(), r.getClassa())){
                        
                        r.setClassa(e.getKey());
                    }
                    else{
                        r.setClassb(e.getKey());
                    }
                }
                
            }            
            else if((r.getRelationship().equalsIgnoreCase(CONSTAGG))){
                
                plantUmlSource.append(r.getClassb().toLowerCase()).append(" -- ").append(r.getClassa().toLowerCase()).append("\n");
                
            }
            else if((r.getRelationship().equalsIgnoreCase(CONSTCOM))){
                plantUmlSource.append(r.getClassb().toLowerCase()).append(" -- ").append(r.getClassa().toLowerCase()).append("\n");

            }
            else if((r.getRelationship().equalsIgnoreCase(CONSTAS))){
                plantUmlSource.append(r.getClassb().toLowerCase()).append(" -- ").append(r.getClassa().toLowerCase()).append("\n");
            }
            else if((r.getRelationship().equalsIgnoreCase(CONSTDIRAS))){
                plantUmlSource.append(r.getClassb().toLowerCase()).append(" -- ").append(r.getClassa().toLowerCase()).append("\n");
            }
            else if((r.getRelationship().equalsIgnoreCase(CONSTDEP))){
                plantUmlSource.append(r.getClassb().toLowerCase()).append(" -- ").append(r.getClassa().toLowerCase()).append("\n");
            }
        }
        
        plantUmlSource.append("@enduml");

        //Diagram structure converted to a string to generate diagram
        SourceStringReader reader = new SourceStringReader(plantUmlSource.toString());

        //Generating diagram and saving as a png file
        FileOutputStream output = null;
        try {
            output = new FileOutputStream(new File(CONSTPATHOBJECT));
            
        } catch (FileNotFoundException ex) {
            JFrame j = new JFrame();
            JOptionPane.showMessageDialog(j,"File Path not found.","Error", JOptionPane.ERROR_MESSAGE);  
            Logger.getLogger(Generator.class.getName()).log(Level.SEVERE, null, ex);
            return;
        }

        reader.outputImage(output, new FileFormatOption(FileFormat.PNG, false));
        
    }
    
    
    //Method to generate ER diagram
    public void generateERDiagram(List<Relationship> reList) throws IOException{
    
        assignRelationshipType(reList);
        StringBuilder plantUmlSource = new StringBuilder();
        
        List<String> classes = new ArrayList<>();
        
        
        //Identifying entities and relationships to be drawn
        reList.forEach((r) -> {
        
            if((!r.getRelationship().equalsIgnoreCase(CONSTIN)) && ((!r.getRelationship().equalsIgnoreCase(CONSTIM)))){
            
                if((!classes.contains(r.getClassa())) || (!classes.contains(r.getClassb())) && r.getSimLevel()>40){
                    
                    if((!classes.contains(r.getClassa())) && (!classes.contains(r.getClassb()))){
                        classes.add(r.getClassa());
                        classes.add(r.getClassb());
                    }
                    else if((!classes.contains(r.getClassa()))){
                        classes.add(r.getClassa());
                    }
                    else if((!classes.contains(r.getClassb()))){
                        classes.add(r.getClassb());
                    }
                }
            }    
              
        });
        
        plantUmlSource.append("@startuml\n");
        
        //Drawing entities
        for(String c: classes){
        
             plantUmlSource.append("entity ").append(c).append(" {} \n");
        }
        
        //Mapping relationships one by one
//        reList.forEach((r) -> {
//            if(r.getRelationship().equalsIgnoreCase(CONSTAGG)){
//                plantUmlSource.append(r.getClassb()).append(" |o--o{ ").append(r.getClassa()).append("\n");
//            }
//            else if(r.getRelationship().equalsIgnoreCase(CONSTCOM)){
//                plantUmlSource.append(r.getClassb()).append(" |o--|{ ").append(r.getClassa()).append("\n");
//            }
//            else if(r.getRelationship().equalsIgnoreCase(CONSTAS)){
//                plantUmlSource.append(r.getClassb()).append(" |o--o| ").append(r.getClassa()).append("\n");
//            }
//            else if(r.getRelationship().equalsIgnoreCase(CONSTDIRAS)){
//                plantUmlSource.append(r.getClassb()).append(" |o--o| ").append(r.getClassa()).append("\n");
//            }
//            else if(r.getRelationship().equalsIgnoreCase(CONSTDEP)){
//                plantUmlSource.append(r.getClassb()).append(" |o--o| ").append(r.getClassa()).append("\n");
//            }
//        });
        
        for(int i = reList.size()-1; i >= 0; i--){
        
            if(reList.get(i).getRelationship().equalsIgnoreCase(CONSTAGG)){
                
                plantUmlSource.append(reList.get(i).getClassb()).append(" |o--o{ ").append(reList.get(i).getClassa()).append("\n");
            }
            else if(reList.get(i).getRelationship().equalsIgnoreCase(CONSTCOM)){
                plantUmlSource.append(reList.get(i).getClassb()).append(" |o--|{ ").append(reList.get(i).getClassa()).append("\n");
            }
            else if(reList.get(i).getRelationship().equalsIgnoreCase(CONSTAS) && reList.get(i).getSimLevel() > 40){
                plantUmlSource.append(reList.get(i).getClassb()).append(" |o--o| ").append(reList.get(i).getClassa()).append("\n");
            }
            else if(reList.get(i).getRelationship().equalsIgnoreCase(CONSTDIRAS)){
                plantUmlSource.append(reList.get(i).getClassb()).append(" |o--o| ").append(reList.get(i).getClassa()).append("\n");
            }
            else if(reList.get(i).getRelationship().equalsIgnoreCase(CONSTDEP)){
                plantUmlSource.append(reList.get(i).getClassb()).append(" |o--o| ").append(reList.get(i).getClassa()).append("\n");
            }
            
        }

        plantUmlSource.append("@enduml");

        //Diagram structure converted to a string to generate diagram
        SourceStringReader reader = new SourceStringReader(plantUmlSource.toString());

        //Generating the diagram and saving it as a png file
        FileOutputStream output = null;
        try {
            output = new FileOutputStream(new File(CONSTPATHER));
            
        } catch (FileNotFoundException ex) {
            JFrame j = new JFrame();
            JOptionPane.showMessageDialog(j,"File Path not found.","Error", JOptionPane.ERROR_MESSAGE);  
            Logger.getLogger(Generator.class.getName()).log(Level.SEVERE, null, ex);
            return;
        }

        reader.outputImage(output, new FileFormatOption(FileFormat.PNG, false));
    }
    
    
    /*Method to generate flow diagram, a diagram that provides a predicted map of classes, that depicts the relationship
    levels between a class of the system that the user selects and the other classes identified from the system's 
    requirements*/
    
    public void generateFlowDiagram(List<Relationship> reList, String selectedClass) throws IOException{
        
        //Map to store the class relationships
        Map<Float, String> flowRelationships = new HashMap<>();
        
        //List to store classes that has high similarity with selected class
        List<String> level1Classes = new ArrayList<>();
        
        //List to store classes that has low similarity with selected class
        List<String> loRelClasses = new ArrayList<>();
        
        //Variable used to include double quotation mark in a string
        char ch ='"';
        
        //Mapping the class name and the similarity level of each relationship with selectedClass to a map
        reList.forEach((r)->{
        
            if(r.getClassa().equals(selectedClass) || r.getClassb().equals(selectedClass)){
            
                if(r.getClassa().equals(selectedClass)){
                
                    flowRelationships.put(r.getSimLevel(), r.getClassb());
                }
                else{
                
                    flowRelationships.put(r.getSimLevel(), r.getClassa());
                }
            }
        });
        
        //Listing keys to sort relationship levels in ascending order
        List<Float> similaritykeys = new ArrayList<>();
        similaritykeys.addAll(flowRelationships.keySet());
        
        //Sorting the keys
        Collections.sort(similaritykeys);
        
        StringBuilder plantUmlSource = new StringBuilder();
        
        //Creating the flow diagram
        plantUmlSource.append("@startuml\n");
        plantUmlSource.append("digraph flow{\n");
        
        //Mapping the flow diagram's first level that includes classes that can directly access the user selected class 
        for(Float key: similaritykeys){
        
            if(key >= 40){
           
                plantUmlSource.append(flowRelationships.get(key)).append("[shape=record, label="+ch+"{").append(flowRelationships.get(key)).
                        append(" | ").
                        append(key).append(" ").append("}"+ch+"]\n");            
                plantUmlSource.append(selectedClass).append(" -> ").append(flowRelationships.get(key)).append("\n");
                
                level1Classes.add(flowRelationships.get(key));
            }
            else{
            
               //Adding classes with less similarity values to loRelClasses for second level of flow diagram
               loRelClasses.add(flowRelationships.get(key));
            }
        }
        
        //Calling below method to map the 2nd level of the flow diagram
        create2ndLevelFlowDiagram(level1Classes, loRelClasses, reList, plantUmlSource);
        
        plantUmlSource.append("}\n");
        plantUmlSource.append("@enduml");
                
        //Diagram structure converted to a string to generate diagram
        SourceStringReader reader = new SourceStringReader(plantUmlSource.toString());

        //Generating diagram and saving it as a png file
        FileOutputStream output = null;
        try {
            output = new FileOutputStream(new File(CONSTPATHFLOW));
            
        } catch (FileNotFoundException ex) {
            JFrame j = new JFrame();
            JOptionPane.showMessageDialog(j,"File Path not found.","Error", JOptionPane.ERROR_MESSAGE);  
            Logger.getLogger(Generator.class.getName()).log(Level.SEVERE, null, ex);
            return;
        }

        reader.outputImage(output, new FileFormatOption(FileFormat.PNG, false));
    }
    
    
    /*The method below creates the 2nd level of the flow diagram by comparing the similarity values of the classes
    that can not directly access the user selected class and the classes that can directly (1st level of flow diagram) 
    access the user selected class*/
    
    public void create2ndLevelFlowDiagram(List<String> level1Classes, List<String> lowRelClasses, 
            List<Relationship> relList, StringBuilder plantUmlSource){
     
        //Variable used to include double quotation mark in a string
        char ch ='"';
        
        /*Variable used to store the highest similarity value of relationship between a 1st level class and a class that
        can not access the user selected class directly*/
        double highestSim;
        
        /*Variable used to store the highest relationship between a 1st level class and a class that can not 
        access the user selected class directly*/
        Relationship relWithHighestSim;
        
        //Highest similarity for each class that is directly not accessible by the user selected class is checked
        for(int i = 0; i < lowRelClasses.size(); i++){
        
            highestSim = 0;
            relWithHighestSim = new Relationship();
            
            for(Relationship r: relList){
        
                if((r.getClassa().equals(lowRelClasses.get(i)) && r.getSimLevel() >= 40) ||
                (r.getClassb().equals(lowRelClasses.get(i)) && r.getSimLevel() >= 40)){
            
                    if(r.getClassa().equals(lowRelClasses.get(i)) && (level1Classes.contains(r.getClassb())) && r.getSimLevel() > highestSim){

                        highestSim =r.getSimLevel();
                        relWithHighestSim.setClassa(r.getClassb());
                        relWithHighestSim.setClassb(r.getClassa());
                        relWithHighestSim.setSimLevel(r.getSimLevel());
                        
                    }
                    else if(r.getClassb().equals(lowRelClasses.get(i)) && (level1Classes.contains(r.getClassa())) && r.getSimLevel() > highestSim){
                        
                        highestSim =r.getSimLevel();
                        relWithHighestSim.setClassa(r.getClassb());
                        relWithHighestSim.setClassb(r.getClassa());
                        relWithHighestSim.setSimLevel(r.getSimLevel());
                        
                    }
                    
                }
                
            }
            
            if(!lowRelClasses.contains(relWithHighestSim.getClassb())&& relWithHighestSim.getClassb() != null){
            
                plantUmlSource.append(relWithHighestSim.getClassa()).append("[shape=record, label="+ch+"{").append(relWithHighestSim.getClassa()).
                        append(" | ").
                        append(relWithHighestSim.getSimLevel()).append(" ").append("}"+ch+"]\n");            
                plantUmlSource.append(relWithHighestSim.getClassb()).append(" -> ").append(relWithHighestSim.getClassa()).append("\n");
                
            }
        }
    }
    
}
