/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.myprojcet.sdaproject.Component4.CodeComplexityCalculation;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author Prabuddha Abisheka
 */
public class ControlStructureComplexity {
    
    private String filePath;
    private Utility utility;
    private List<String> originalFile;
    private List<String> methods = new ArrayList<>();
    private List<String> imports = new ArrayList<>();
    private List<String> fixedFile = new ArrayList<>();
    private int complexityCount = 0;
    private List lineByLineSize = new ArrayList();
    private List recursionLinebyLine = new ArrayList();
    private List<String> identifiedKeywords = new ArrayList();
    
    private List<String> controlStructures = Arrays.asList("if", "for", "while", "do ", "switch", "catch");
    
    private List<String> operators = Arrays.asList("?");
    
    public ControlStructureComplexity(String filePath){
        this.filePath = filePath;
        this.utility = new Utility();
    }
    
    //This method is used to format the user selected code file.
    public void formatFiles(){
        try {
            List<String> allLines = Files.readAllLines(Paths.get(this.filePath));
            List<String> allLinesTrimmed = new ArrayList<>();
            
            int commentStart = -1;
            
            for(String line: allLines){
                
                //remove single line comments
                line = line.replaceAll("(?:/\\*(?:[^*]|(?:\\*+[^*/]))*\\*+/)|(?://.*)","");
                
                //remove multiline comments
                if(line.contains("/*")){
                    commentStart = allLines.indexOf(line);
                } else if(line.contains("*/")){
                    commentStart = -1;
                    continue;
                }
                
                if(commentStart != -1){
                    continue;
                }
                
                line = line.replaceAll("\t", "");
                line = line.replaceAll("\n", "");
                
                //skipping empty lines
                if(line.trim().isEmpty()){
                    continue;
                }
                
                if(line.contains("import"))
                    continue;
                
                if(line.contains("package"))
                    continue;
                
                allLinesTrimmed.add(line.trim());
            }
            
            this.fixedFile = allLinesTrimmed;
            
        } catch (Exception e) {
        }
    }
    
    /*This method is used to calculate the complexity due to Control Structures.
    *In this method the file will be analysized line by line and sort identified elements.
    *By the identified elements complexity will be calculated.
    *calculation method will change from metric to metric.
    */
    public void identifyControlStructures(){
        try {
            this.originalFile = Files.readAllLines(Paths.get(this.filePath));
            
            List<String> allLines = Files.readAllLines(Paths.get(this.filePath));
            int commentStart = -1;
            
            this.utility = new Utility(allLines);
            
            for(String line: allLines){
                
                //remove all comments
                line = line.replaceAll("(?:/\\*(?:[^*]|(?:\\*+[^*/]))*\\*+/)|(?://.*)","");
                
                if(line.contains("/*")){
                    commentStart = allLines.indexOf(line);
                } else if(line.contains("*/")){
                    commentStart = -1;
                    continue;
                }
                
                if(commentStart != -1){
                    continue;
                }
                
                //Checks for "" in the code, increase complexity by 1 and remove double quotes
                if(line.contains("\"")){
                    
                    //single line might have multiple double quotes so it will be checked here
                    while(line.contains("\"")){
                        
                        int start = line.indexOf("\"");
                        int end = start;
                        
                        while(end >= 0) {
                            
                            int index = end;
                            end = line.indexOf("\"", end+1);
                            
                            if(end <= 0){
                                end = index;                                
                                break;
                            }
                            
                            //getting the pattern/string part that should be replaced
                            String newLine = line.substring(start, end+1);
                            
                            Pattern pattern = Pattern.compile("\\\\.");
                            Matcher matcher = pattern.matcher(newLine);
                            
                            int count = 0;
                            while(matcher.find()){
                                count++;
                            }
                            
                            //complexityCount += count;
                            
                            //replacing 
                            line = line.replace(newLine, "");
                            
                            if(line.contains("\""))
                                    start = line.indexOf("\"");
                            
                            //complexityCount++;
                        }
                    }
                }
                
                if(line.contains("import"))
                    continue;
                
                if(line.contains("package"))
                    continue;
                
                if(line.contains("class")){
                    this.lineByLineSize.add(0);
                    continue;
                }
                
                line = line.trim();
                
                for(String operator: this.operators) {

                    if(line.contains(operator)){

                        int operatorIndex = 0;
                        int count12 = 0; //testing variable

                        while(operatorIndex != -1){

                            operatorIndex = line.indexOf(operator, operatorIndex);

                            if(operatorIndex != -1){
                                
                                if(line.length() >= operatorIndex + 1 && operatorIndex - 1 > 0){
                                    char beforeChecker = line.charAt(operatorIndex - 1);
                                    char op = line.charAt(operatorIndex);
                                    char checker = line.charAt(operatorIndex + 1);
                                    
                                    if(operator.equals(checker+"")){
                                        break;
                                    } else {
                                        int status = 0;
                                        
                                        for(String operator2: this.operators) {
                                            if(operator2.equals(checker+"") && operator.equals(op+"")) {
                                                status = -1;
                                            
                                            } else if(operator2.equals(beforeChecker+"")) {
                                                status = -1;
                                            } 
                                        }
                                        
                                        if(status == -1)
                                            break;
                                    }
                                }
                                
                                complexityCount ++;
                                count12++;
                                operatorIndex += operator.length();
                            }
                        }

                        //System.out.println(line + " " + count12 + " " + operator);
                    }
                }
                
                line = line.replaceAll("\n", "");
                line = line.replaceAll("\t", "");
                line = line.replaceAll("\\;", " ");
                line = line.replaceAll("\\,", " ");
                line = line.replaceAll("\\[", " ");
                line = line.replaceAll("\\]", " ");
                line = line.replaceAll("\\[]", " ");                
                line = line.replaceAll("\\(", " ");
                line = line.replaceAll("\\)", " ");
                line = line.replaceAll("\\.", " ");
                line = line.replaceAll("\\++", " ");
                line = line.replaceAll("\\--", " ");
                line = line.replaceAll("\\+=", " ");
                line = line.replaceAll("\\-=", " ");
                line = line.replaceAll("\\/=", " ");
                line = line.replaceAll("\\*=", " ");                
                line = line.replaceAll("\\=", "");
                line = line.replaceAll("\\?", " ");
                line = line.replaceAll("\\/", " ");
                line = line.replaceAll("\\%", " ");
                line = line.replaceAll("\\*", " ");
                line = line.replaceAll("\\:", " ");
                line = line.replaceAll("\\&", " ");
                line = line.replaceAll("\\>", " ");
                line = line.replaceAll("\\<", " ");
                line = line.replaceAll("\\|", " ");
                line = line.replaceAll("\\!=", " ");
                line = line.replaceAll("\\~", " ");
                line = line.replaceAll("\\+", " ");
                line = line.replaceAll("\\-", " ");
                line = line.replaceAll("\\!", " ");
                line = line.replaceAll("\\^", " ");
                
                System.out.println(line);
                
                if(line.trim().isEmpty()){
                    if(complexityCount > 0){
                        //System.out.println(line + "  Line Complexity = " + this.complexityCount);
                        this.lineByLineSize.add(this.complexityCount);
                        this.complexityCount = 0;
                    }
                    continue;
                }
                
                boolean notfound = true;               
                
                for(String conStruct: this.controlStructures){
                    if(!notfound || line.contains(conStruct)){
                        
                        System.out.println(line + " " + conStruct + " " + this.complexityCount);
                        
                        if(line.contains(conStruct))
                            this.complexityCount++;
                        
                        System.out.println("after check " + this.complexityCount);
                        notfound = false;
                        
                        if(line.contains(conStruct)) {
                            this.lineByLineSize.add(this.complexityCount);
                            break;
                        }
                        else
                            this.lineByLineSize.add(0);
                    }
                }
                
                if(line.contains("else") && !line.contains("if")){
                    this.lineByLineSize.add(1);
                } else if(this.complexityCount == 0) {
                    this.lineByLineSize.add(this.complexityCount);
                } else if(notfound){
                    this.lineByLineSize.add(this.complexityCount);
                }
                
                this.complexityCount = 0;
            }
            
            for(int i = 0; i < this.lineByLineSize.size(); i++){
                System.out.println(this.fixedFile.get(i) + " " + this.lineByLineSize.get(i));
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    //This method is used to get the calculated complexity line by line list.
    public List getLineByLineSize(){
        return this.lineByLineSize;
    }
    
    //This method is used to get the fixed file.
    public List getFixedFile(){
        return this.fixedFile;
    }
    
//    public static void main(String args[]){
//            System.out.println("here");
//            ControlStructureComplexity csc = new ControlStructureComplexity("C:/Users/Prabuddha Abisheka/Desktop/Test.java");
//            csc.formatFiles();
//            csc.identifyControlStructures();
//    }
}
