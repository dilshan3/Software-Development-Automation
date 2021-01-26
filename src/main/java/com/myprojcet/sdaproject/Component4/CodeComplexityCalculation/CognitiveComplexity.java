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
public class CognitiveComplexity {
    
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
    private List<Integer> methodPositions = new ArrayList<>();
    private List<String> controlStructures = Arrays.asList("if", "for", "while", "do ", "switch", "catch");
    private List<String> dataTypesMethods = Arrays.asList("boolean", "byte", "char", "short", "int", "long", "float", "double", "Boolean", "Character", "Integer", "String", "Double", "void");
    private List<String> operators = Arrays.asList("&&","||", "?");
    
    public CognitiveComplexity(String filePath){
        this.filePath = filePath;
        this.utility = new Utility();
    }
    
    //This method is used to format the users selected code file.
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
    
    //This method is used to identify methods.
    public void getMethods(){
        try{
            List<String> allLines = Files.readAllLines(Paths.get(this.filePath));
            List<String> methods = new ArrayList<>();
            List<String> allLinesTrimmed = new ArrayList<>();
            
            for(int i = 0; i < lineByLineSize.size(); i++){
                recursionLinebyLine.add(1);
            }
            
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
                            
                            //replacing 
                            line = line.replace(newLine, "");
                            
                            if(line.contains("\""))
                                    start = line.indexOf("\"");
                        }
                    }
                }
                
                line = line.replaceAll("\\}", " ");
                line = line.replaceAll("\\{", " ");
                //line = line.replaceAll("\\.", " ");
                line = line.replaceAll("\\++", " ");
                line = line.replaceAll("\\--", " ");
                line = line.replaceAll("\\+=", " ");
                line = line.replaceAll("\\-=", " ");
                line = line.replaceAll("\\/=", " ");
                line = line.replaceAll("\\*=", " ");
                line = line.replaceAll("\\;", " ");
                line = line.replaceAll("\\[", " ");
                line = line.replaceAll("\\]", " ");
                line = line.replaceAll("\\[]", " ");                

                line = line.replaceAll("return", " ");
                line = line.trim();
                
                for(String keyword: this.utility.getAccessorMethods()) {
                    line = line.replaceAll(keyword, "");
                }
                
                String wByW[] = line.split(" ");
                
                for(String dt : this.utility.getDataTypesMethods()){ 
                    for(int i = 0; i < wByW.length; i++){
                        if(wByW[i].equals(dt)){
                            if((i+1) < wByW.length && wByW[i+1].contains("(")){
                                String method = wByW[i+1];
                                
//                                for(String object : this.classesAndObjects){
//                                    method = method.replaceAll("\\("+object, "()");
//                                }
                                
                                for(String object : this.dataTypesMethods){    
                                    method = method.replaceAll("\\(" + object, "()");
                                }
                                
                                method = method.replaceAll("\\(.*?\\).*?", "");
                                
                                System.out.println(method);
                                this.methods.add(method);
                            }
                        }
                    }
                }
            }
            
        } catch(Exception e){
            e.printStackTrace();
        }
    }
    
    //This method is used to identify recursive methods.
    public void recursiveComplexity(){
        try {
            for(String method: methods){
                
                int start = -1;
                int end = -1;
                
                for(String line : fixedFile){
                    if(line.contains("class"))
                        continue;
                    
                    if(line.contains(" "+method)){
                      start = fixedFile.indexOf(line);
                      break;
                    }
                }
                 
                if(start != -1){
                    int bracketCount = 0;
                    
                    Pattern p1 = Pattern.compile("\\{");
                    Pattern p2 = Pattern.compile("\\}");
                    
                    for(int i = start; i < fixedFile.size(); i++){
                        Matcher m1 = p1.matcher(fixedFile.get(i));
                        Matcher m2 = p2.matcher(fixedFile.get(i));
                        
                        while(m1.find()){
                            bracketCount++;
                        }
                        
                        while(m2.find()){
                            bracketCount--;
                        }
                        
                        if(bracketCount == 0){
                            end = i;
                            break;
                        }
                    }
                    
                    this.methodPositions.add(start);
                    this.methodPositions.add(end);
                    
                    for(int j = start+1; j <= end; j++){
                        if(fixedFile.get(j).contains(method)){

                            for(int l = start; l <= end; l++){
                                recursionLinebyLine.remove(l);
                                recursionLinebyLine.add(l, 2);
                            }
                            break;
                        }
                    }
                }
            }
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
    
    /*This method is used to calculate the Cognitive Complexity.
    *In this method the file will be analysized line by line and sort identified elements.
    *By the identified elements complexity will be calculated.
    *calculation method will change from metric to metric.
    */
    public void calculateComplexity(){
        try {
            this.originalFile = Files.readAllLines(Paths.get(this.filePath));
            
            List<String> allLines = Files.readAllLines(Paths.get(this.filePath));
            int commentStart = -1;
            
            //variables for control structure couting and nesting calculation
            int structureCount = 0;
            ArrayList structureBrackets = new ArrayList<>();
            boolean notfound = true;
            int brackets = 0;
            
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
                
                if(line.contains("import "))
                    continue;
                
                if(line.contains("package "))
                    continue;
                
                if(line.contains("class ")){
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
                                
                                complexityCount++;
                                if(operator.equals("?")){
                                    complexityCount += structureCount;
                                }
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
                
                //System.out.println(line);
                
                if(line.trim().isEmpty()){
                    if(complexityCount > 0){
                        //System.out.println(line + "  Line Complexity = " + this.complexityCount);
                        this.lineByLineSize.add(this.complexityCount);
                        this.complexityCount = 0;
                    }
                    continue;
                }
                
                Pattern p1 = Pattern.compile("\\{");
                Pattern p2 = Pattern.compile("\\}");
                Matcher m1 = p1.matcher(line);
                Matcher m2 = p2.matcher(line);
                
                if(m2.find() && brackets > 0){            
                    if(structureBrackets.contains(brackets)){
                        int index = structureBrackets.indexOf(brackets);
                        structureBrackets.remove(index);
                        brackets--;
                        structureCount--;
                    } else {
                     
                        brackets--;
                    }
                    
                    if(brackets == 0){
                        structureBrackets.clear();
                    }
                }
                
                if(m1.find()){
                    brackets++;            
                }
                
                for(String conStruct: this.controlStructures){
                    if(!notfound || line.contains(conStruct)){
                        
                        System.out.println(line + " " + conStruct + " " + this.complexityCount);
                        
                        if(line.contains(conStruct) && !line.contains("else")) {
                            this.complexityCount++;
                            
                            this.complexityCount += structureCount;
                            this.lineByLineSize.add(this.complexityCount);
                            
                            structureCount++;
                            notfound = false;
                            structureBrackets.add(brackets);
                        } else {
                            notfound = true;
                        }
                        
                        System.out.println("after check " + this.complexityCount);
                        
                        
                        if(line.contains(conStruct)) {
                            break;
                        }
                    }
                }
                
                if(line.contains("else") && line.contains("if")){
                    this.lineByLineSize.add(1 + this.complexityCount);
                } else if(line.contains("else") && !line.contains("if")){
                    this.lineByLineSize.add(1);
                }else if(notfound){
                    this.lineByLineSize.add(this.complexityCount);
                }

                this.complexityCount = 0;
            }
            
            for(int i = 0; i < fixedFile.size(); i++)
                System.out.println(fixedFile.get(i) + " " + this.lineByLineSize.get(i));
            
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
    
    //This method is used to get the method positions.
    public List getMethodPositions(){
        return this.methodPositions;
    }
    
    //This method is used to get the recursion line by line list.
    public List getRecursionLineByLine(){
        return this.recursionLinebyLine;
    }
    
//    public static void main(String args[]){
//            System.out.println("here");
//            CognitiveComplexity cc = new CognitiveComplexity("C:/Users/Prabuddha Abisheka/Desktop/Test.java");
//            cc.formatFiles();
//            cc.calculateComplexity();
//            cc.getMethods();
//            cc.recursiveComplexity();
//    }
}
