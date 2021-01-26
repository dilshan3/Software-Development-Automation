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
class ElementIdentifierForHal {
    
private String filePath;
    private List<String> originalFile;
    private List<String> imports = new ArrayList<>();
    private List<String> fixedFile = new ArrayList<>();
    private Utility utility;
    private int complexityCount = 0;
    private List recursionLinebyLine = new ArrayList();
    
    //OPERANDS
    
    //data types for methods
    private List<String> dataTypesMethods = Arrays.asList("boolean", "byte", "char", "short", "int", "long", "float", "double", 
            "Boolean", "Character", "Integer", "String", "Double", "void", "signed", "unsiged");
    

    //OPERATORS    
    private List<String> operators = Arrays.asList("+","-","*","/","%","++","--","==","!=",">","<",">=","<=","&&","||","!",
              "|","^","~","<<<",">>>",",","->",".","::","+=","-=","*=","/=","=",">>>=","|=","&=","=&",
              "%=","<<=",">>=","^=","\n");
    //main java keywords
    private List<String> keywords = Arrays.asList("abstract", "assert", "break", "case", "catch", "class", "const",
            "continue", "default", "do", "delete", "enum", "else", "extends", "false", "final", "finally", "for", "goto", "if",
            "implements", "import", "interface", "instanceof", "new","null", "native", "package", "return", "staticfp", "super", "public", "private", "static", "protected",
            "switch", "synchronized", "this", "transient", "true", "try", "throw", "throws", "volatile", "while", "System", "out", "println");

    //Identified methods and variables will be added to this list
    private List<String> variables = new ArrayList();
    private List<String> methods = new ArrayList();
    
    //Identified classes and objects will be added to this list
    private List<String> classesAndObjects = new ArrayList();
    
    private List<String> identifiedKeywords = new ArrayList();
    
    //Tracking
    //private float operandCount = 0;
    private float lineOperandCount = 0;
    private List<Float> lineOperands = new ArrayList<>();
    //private float operatorCount = 0;
    private float lineOperatorCount = 0;
    private List<Float> lineOperators = new ArrayList<>();
    
    private List<String> uniqueLineOperands = new ArrayList<>();
    private List<String> uniqueLineOperator = new ArrayList<>();
    
    
    public ElementIdentifierForHal(String filePath) {
        this.filePath = filePath;
        this.utility = new Utility();
    }
    
    public void uniqueElementIdentifier(){
        try {
            //at here right now 2020
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
                            
                            complexityCount += count;
                            
                            //replacing 
                            line = line.replace(newLine, "");
                            
                            if(line.contains("\""))
                                    start = line.indexOf("\"");
                            
                            complexityCount++;
                        }
                    }
                }
                
                if(line.contains("import "))
                    continue;
                
                if(line.contains("package "))
                    continue;
                
                if(line.contains("class ")){
                    
                    continue;
                }
                    
                
                line = line.trim();
                
                //this is used to count complexity for operetors. (+1)
                for(String operator: this.utility.getOperators()) {

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
                                        
                                        for(String operator2: this.utility.getOperators()) {
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

                        System.out.println(line + " " + count12 + " " + operator);
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
                //line = line.replaceAll("else", "");
                
                System.out.println(line);
                
                if(line.trim().isEmpty()){
                    if(complexityCount > 0){
                        this.complexityCount = 0;
                    }
                    continue;
                }                
                
                line = line.replaceAll("\\{", " ");
                line = line.replaceAll("\\}", " ");
                
                String[] wByW = line.trim().split(" ");
                
                for(int i = 0; i < wByW.length; i++){
                    if(wByW[i].trim().isEmpty())
                        continue;
                    
                    //System.out.println(wByW[i]);
                    
                    if(this.utility.getIgnoreKeywords().contains(wByW[i].trim()))
                        continue;
                    
                    if(this.utility.getAccessorMethods().contains(wByW[i].trim()))
                        continue;
                    
                    if(this.utility.getDataTypesMethods().contains(wByW[i].trim())){
                        complexityCount++;
                        continue;
                    }
                        
                    if(this.utility.getKeywords().contains(wByW[i].trim())){
                        complexityCount++;
                        continue;
                    }
                        
                    if(this.utility.getSpecialKeywords().contains(wByW[i].trim())){
                        complexityCount+=2;
                        continue;
                    }
                    
                    if(wByW[i].matches(".*\\d.*")){
                        complexityCount++;
                        continue;
                    }
                    
                    if(wByW[i].equals("else"))
                        continue;
                    
                    if(!identifiedKeywords.contains(wByW[i].trim()))
                        this.identifiedKeywords.add(wByW[i].trim());
                    complexityCount++;
                }
                
                this.complexityCount = 0;
            }
            
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
    
    public void calculateKeywords() {
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
                
                //Checks for "" in the code, increase operand count by 1 and remove double quotes
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
                            
                            complexityCount += count;
                            
                            //replacing 
                            line = line.replace(newLine, "");
                            
                            if(line.contains("\""))
                                    start = line.indexOf("\"");
                            
                            lineOperandCount += 1.0;
                        }
                    }
                }
                
                if(line.contains("import "))
                    continue;
                
                if(line.contains("package "))
                    continue;
                
                
                //Identifying class declaration and other keywords
                if(line.contains("class ")){
                    
                    //System.out.println(line);
                    
                    if(line.contains("{")) {
                        line = line.replaceAll("\\{", "");
                        lineOperatorCount += 0.5;
                        
                        if(!this.uniqueLineOperator.contains("{"))
                                this.uniqueLineOperator.add("{");
                    }
                    
                    line = line.replaceAll("class ", " ");
                    
                    for(String key : this.keywords){
                        if(line.contains(key)){
                            
                            if(!this.uniqueLineOperator.contains(key))
                                this.uniqueLineOperator.add(key);
                            
                            this.lineOperatorCount += 1.0;
                            line = line.replace(key, "");
                        }
                    }
                    
                    String wByW[] = line.trim().split(" ");
                    
                    for(String values : wByW) {
                        
                        if(!values.trim().isEmpty()) {
                            this.classesAndObjects.add(values.trim());
                            this.lineOperandCount += 1.0;
                            
                            if(!this.uniqueLineOperands.contains(values.trim()))
                                this.uniqueLineOperands.add(values.trim());
                        }
                    }
                                      
                    //System.out.println(operatorCount);
                    //System.out.println(classesAndObjects);
                    this.lineOperands.add(this.lineOperandCount);
                    this.lineOperators.add(this.lineOperatorCount);
                    this.lineOperandCount = 0;
                    this.lineOperatorCount = 0;

                    continue;
                }
                    
                //brackets and paran count
                if(line.contains("{") || line.contains("}") || line.contains("(") || line.contains(")") || line.contains("[") || line.contains("]")){
                    
                    char[] arr = line.toCharArray();
                    for(int i = 0; i < arr.length; i++) {
                        if(arr[i] == '{'){
                            lineOperatorCount += 0.5;
                            
                            if(!this.uniqueLineOperator.contains("{"))
                                this.uniqueLineOperator.add("{");
                        }
                        
                        if(arr[i] == '}'){
                            lineOperatorCount += 0.5;
                            
                            if(!this.uniqueLineOperator.contains("}"))
                                this.uniqueLineOperator.add("}");
                        }
                        
                        if(arr[i] == '('){
                            lineOperatorCount += 0.5;
                            
                            if(!this.uniqueLineOperator.contains("("))
                                this.uniqueLineOperator.add("(");
                        }
                        
                        if(arr[i] == ')'){
                            lineOperatorCount += 0.5;
                            
                            if(!this.uniqueLineOperator.contains(")"))
                                this.uniqueLineOperator.add(")");
                        }
                        
                        if(arr[i] == '['){
                            lineOperatorCount += 0.5;
                            
                            if(!this.uniqueLineOperator.contains("["))
                                this.uniqueLineOperator.add("[");
                        }
                        
                        if(arr[i] == ']'){
                            lineOperatorCount += 0.5;
                            
                            if(!this.uniqueLineOperator.contains("]"))
                                this.uniqueLineOperator.add("]");
                        }
                    }
                }
                
                line = line.trim();
                
                //this is used to count complexity for operetors. (+1)
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
                                        
                                        for(String operator2: this.utility.getOperators()) {
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
                                
                                this.lineOperatorCount += 1.0;
                                
                                if(!this.uniqueLineOperator.contains(operator))
                                 this.uniqueLineOperator.add(operator);
                                
                                count12++;
                                operatorIndex += operator.length();
                            }
                        }

                        System.out.println(line + " " + count12 + " " + operator);
                    }
                }
                
                String extractedLine = line;
                
                extractedLine = extractedLine.replaceAll("\n", "");
                extractedLine = extractedLine.replaceAll("\t", "");
                extractedLine = extractedLine.replaceAll("\\;", " ");
                extractedLine = extractedLine.replaceAll("\\,", " ");
                extractedLine = extractedLine.replaceAll("\\[", " ");
                extractedLine = extractedLine.replaceAll("\\]", " ");
                extractedLine = extractedLine.replaceAll("\\[]", " ");                
                extractedLine = extractedLine.replaceAll("\\(", " ");
                extractedLine = extractedLine.replaceAll("\\)", " ");
                extractedLine = extractedLine.replaceAll("\\.", " ");
                extractedLine = extractedLine.replaceAll("\\++", " ");
                extractedLine = extractedLine.replaceAll("\\--", " ");
                extractedLine = extractedLine.replaceAll("\\+=", " ");
                extractedLine = extractedLine.replaceAll("\\-=", " ");
                extractedLine = extractedLine.replaceAll("\\/=", " ");
                extractedLine = extractedLine.replaceAll("\\*=", " ");                
                extractedLine = extractedLine.replaceAll("\\=", "");
                extractedLine = extractedLine.replaceAll("\\?", " ");
                extractedLine = extractedLine.replaceAll("\\/", " ");
                extractedLine = extractedLine.replaceAll("\\%", " ");
                extractedLine = extractedLine.replaceAll("\\*", " ");
                extractedLine = extractedLine.replaceAll("\\:", " ");
                extractedLine = extractedLine.replaceAll("\\&", " ");
                extractedLine = extractedLine.replaceAll("\\>", " ");
                extractedLine = extractedLine.replaceAll("\\<", " ");
                extractedLine = extractedLine.replaceAll("\\|", " ");
                extractedLine = extractedLine.replaceAll("\\!=", " ");
                extractedLine = extractedLine.replaceAll("\\~", " ");
                extractedLine = extractedLine.replaceAll("\\+", " ");
                extractedLine = extractedLine.replaceAll("\\-", " ");
                extractedLine = extractedLine.replaceAll("\\!", " ");
                extractedLine = extractedLine.replaceAll("\\^", " ");
                //line = line.replaceAll("else", "");
                
                System.out.print(line);
                
                if(extractedLine.trim().isEmpty()){
                    if(complexityCount > 0){

                        this.lineOperands.add(this.lineOperandCount);
                        this.lineOperators.add(this.lineOperatorCount);
                        this.lineOperandCount = 0;
                        this.lineOperatorCount = 0;
                    }
                    continue;
                }                
                
                extractedLine = extractedLine.replaceAll("\\{", " ");
                extractedLine = extractedLine.replaceAll("\\}", " ");
                
                String[] wByW = extractedLine.trim().split(" ");
                
                for(int i = 0; i < wByW.length; i++){
                    if(wByW[i].trim().isEmpty())
                        continue;
                    
                    //System.out.println(wByW[i]);
                    
                    if(this.dataTypesMethods.contains(wByW[i].trim())){
                        this.lineOperandCount += 1.0;
                        
                        if(!this.uniqueLineOperands.contains(wByW[i].trim()))
                            this.uniqueLineOperands.add(wByW[i].trim());
                        
                        continue;
                    }
                        
                    if(this.keywords.contains(wByW[i].trim())){
                        this.lineOperatorCount += 1.0;
                        
                        if(!this.uniqueLineOperator.contains(wByW[i].trim()))
                                this.uniqueLineOperator.add(wByW[i].trim());
                        
                        continue;
                    }
                                            
                    if(wByW[i].matches(".*\\d.*")){
                        this.lineOperandCount += 1.0;
                        
                        if(!this.uniqueLineOperands.contains(wByW[i].trim()))
                                this.uniqueLineOperands.add(wByW[i].trim());
                        
                        continue;
                    }
                    
                    //if(!identifiedKeywords.contains(wByW[i].trim())) {
                        //this.identifiedKeywords.add(wByW[i].trim());
                        //System.out.println(wByW[i] + " here1");
                        
                        char[] arr = line.toCharArray();
                        String newLine = "";
                        
                        for(int k = 0; k < arr.length ; k++){
                            
                            if(arr[k] == '(')
                                newLine += " ";
                            
                            if(arr[k] == ')')
                                newLine += " ";
                            
                            if(arr[k] == '[')
                                newLine += " ";
                                
                            if(arr[k] == ']')
                                newLine += " ";
                            
                            newLine += arr[k];
                            
                            if(arr[k] == '(')
                                newLine += " ";
                            
                            if(arr[k] == ')')
                                newLine += " ";
                            
                            if(arr[k] == '[')
                                newLine += " ";
                                
                            if(arr[k] == ']')
                                newLine += " ";
                        }
                        
                        //line = newLine;
                        
                        String atomicLine[] = newLine.trim().split(" ");
                        
//                        for(String part: atomicLine){
//                            if(part.contains(wByW[i])){
//                                if(atomicLine.)
//                            }
//                        }

                        for(int j = 0; j < atomicLine.length; j++) {
                            if(atomicLine[j].contains(wByW[i].trim())){
                                if(j > 0){
                                    if(this.dataTypesMethods.contains(atomicLine[j - 1].trim())){
                                        if(atomicLine[j].trim().endsWith(";")){
                                            this.variables.add(wByW[i].trim());
                                            break;
                                        } else if ((atomicLine.length > j + 1) && atomicLine[j + 1].contains("(")) {
                                            this.methods.add(wByW[i].trim());
                                            this.lineOperatorCount += 1.0;
                                            
                                            if(!this.uniqueLineOperator.contains(wByW[i].trim()))
                                                this.uniqueLineOperator.add(wByW[i].trim());
                                            
                                            break;
                                        } else if ((atomicLine.length > j + 1) && (atomicLine[j + 1].contains("="))) {
                                            this.variables.add(wByW[i].trim());
                                            this.lineOperandCount += 1.0;
                                            
                                            if(!this.uniqueLineOperands.contains(wByW[i].trim()))
                                                this.uniqueLineOperands.add(wByW[i].trim());
                                            
                                            break;
                                        } else {
                                            System.out.println(wByW[i]);
                                        }                                        
                                    } else {
                                        if(Character.isUpperCase(wByW[i].trim().charAt(0))){
                                            this.classesAndObjects.add(wByW[i].trim());
                                            this.lineOperandCount += 1.0;
                                            
                                            if(!this.uniqueLineOperands.contains(wByW[i].trim()))
                                                this.uniqueLineOperands.add(wByW[i].trim());
                                            
                                            break;
                                        } else if ((atomicLine.length > j + 1) && atomicLine[j + 1].contains("(")){
                                            this.methods.add(wByW[i].trim());
                                            this.lineOperatorCount += 1.0;
                                            
                                            if(!this.uniqueLineOperator.contains(wByW[i].trim()))
                                                this.uniqueLineOperator.add(wByW[i].trim());
                                            
                                            break;
                                        } else {
                                            this.variables.add(wByW[i].trim());
                                            this.lineOperandCount += 1.0;
                                            
                                            if(!this.uniqueLineOperands.contains(wByW[i].trim()))
                                                this.uniqueLineOperands.add(wByW[i].trim());
                                            
                                            break;
                                        }
                                    }
                                } else {
                                    if(Character.isUpperCase(wByW[i].trim().charAt(0))){
                                        this.classesAndObjects.add(wByW[i].trim());
                                        this.lineOperandCount += 1.0;
                                        
                                        if(!this.uniqueLineOperands.contains(wByW[i].trim()))
                                            this.uniqueLineOperands.add(wByW[i].trim());
                                        
                                        break;
                                    } else if (atomicLine[j].trim().contains("(")){
                                        this.methods.add(wByW[i].trim());
                                        this.lineOperatorCount += 1.0;
                                        
                                        if(!this.uniqueLineOperator.contains(wByW[i].trim()))
                                            this.uniqueLineOperator.add(wByW[i].trim());
                                        
                                        break;
                                    } else {
                                        this.variables.add(wByW[i].trim());
                                        this.lineOperandCount += 1.0;
                                        
                                        if(!this.uniqueLineOperands.contains(wByW[i].trim()))
                                            this.uniqueLineOperands.add(wByW[i].trim());
                                        
                                        break;
                                    }
                                }
                            }
                        }
                    //}
                       
                }
                
                System.out.println("  Line operators = " + this.lineOperatorCount + "  Line operands = " + this.lineOperandCount);
//                this.lineByLineSize.add(this.complexityCount);
//                this.complexityCount = 0;

                this.lineOperands.add(this.lineOperandCount);
                this.lineOperators.add(this.lineOperatorCount);
                this.lineOperandCount = 0;
                this.lineOperatorCount = 0;
            }
            
            System.out.println(this.lineOperands.size());
            System.out.println(this.lineOperators.size());
            System.out.println(this.uniqueLineOperands.size());
            System.out.println(this.uniqueLineOperator.size());
            System.out.println(allLines.size());
            
            for(int i = 0; i < this.uniqueLineOperator.size(); i++)
                System.out.println(this.uniqueLineOperator.get(i));
               
            for(int i = 0; i < this.uniqueLineOperands.size(); i++)
                System.out.println(this.uniqueLineOperands.get(i));
            
        } catch(Exception e) {
            e.printStackTrace();
        }
     
    }
    
    public void getUniqueOperandsOperators(){
        
    }
    
    public void getMethods(){
        try{
            List<String> allLines = Files.readAllLines(Paths.get(this.filePath));
            List<String> methods = new ArrayList<>();
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
            
        } catch(Exception e){
            e.printStackTrace();
        }
    }
    
    public void getObjects() {
        for(String object : identifiedKeywords) {
            char cByC[] = object.toCharArray();
            if(cByC != null) {
                if(Character.isAlphabetic(cByC[0]) && Character.isUpperCase(cByC[0])){
                    this.classesAndObjects.add(object);
                    //System.out.println(object);
                }
            }
        }
    }
      
    public List getFixedFile(){
        return this.fixedFile;
    }
    
    public List getMethodsArray(){
        return this.methods;
    }
    
    public List getOperandsArray(){
        return this.lineOperands;
    }
    
    public List getOperatorsArray(){
        return this.lineOperators;
    }
    
    public List getClassesAndObjectsArray(){
        return this.classesAndObjects;
    }
    
    public double getActualLength(){
        List<Float> operandsList = this.getOperandsArray();
        List<Float> operatorsList = this.getOperatorsArray();
        
        float operandC = 0;
        float operatorC = 0;
        
        if(!operandsList.isEmpty() && !operatorsList.isEmpty()){
            for(int i = 0; i < operatorsList.size(); i++){
            operatorC += operatorsList.get(i);
            }

            for(int i = 0; i < operandsList.size(); i++){
                operandC += operandsList.get(i);
            }
        }
        
        double actualLength = operandC + operatorC;
        
        return actualLength;
    }
    
    public double getEstimatedLength(){
        
        double estimatedLength = 0.0f;
        double x = this.uniqueLineOperator.size() * (Math.log(this.uniqueLineOperator.size())/Math.log(2));
        double y = this.uniqueLineOperands.size() * (Math.log(this.uniqueLineOperands.size())/Math.log(2));
        estimatedLength = x + y;
        
        return estimatedLength;
    }
    
    public double getProgramVocabulary(){
        double n = 0;        
        n = this.uniqueLineOperands.size() + this.uniqueLineOperator.size();
        
        return n;
    }
    
    public double getProgramVolume(){
        double programVolume = 0;
        double N = this.getActualLength();
        double n = this.getProgramVocabulary();
        programVolume = N * (Math.log(n)/Math.log(2));
        
        return programVolume;
    }
    
    public double getPotentialMinimumVolume(){
        double potentialMinimumVolume = 0;        
        potentialMinimumVolume = (2 + this.uniqueLineOperands.size()) * (Math.log(2 + this.uniqueLineOperands.size())/Math.log(2));
        
        return potentialMinimumVolume;
    }
    
    public double getProgramLevel(){
        double programLevel = 0;
        double programVolume = this.getProgramVolume();
        double potentialMinimumVolume = this.getPotentialMinimumVolume();
        
        if(programVolume > 0 && potentialMinimumVolume > 0) {
            programLevel = potentialMinimumVolume/programVolume;
        }
        
        return programLevel;
    }
    
    public double getEffort(){
        double effort = 0;
        double programVolume = this.getProgramVolume();
        double programLevel = this.getProgramLevel();
        
        if(programVolume > 0 && programLevel > 0) {
            effort = programVolume/programLevel;
        }
        
        return effort;
    }
    
    public double getProgrammerTime(){
        double programmerTime = 0;
        double effort = this.getEffort();
        double s = 18;
        
        if(effort > 0) {
            programmerTime = effort/s;
        }
        
        return programmerTime;
    }
}

//public class HalsteadCalculation {
//    
//    public static void main(String args[]){
//        
//        ElementIdentifierForHal hal = new ElementIdentifierForHal("C:/Users/Prabuddha Abisheka/Desktop/Test.java");
//        hal.calculateKeywords();
//        hal.getMethods();
//        System.out.println(hal.getFixedFile().size());
//    }
//}