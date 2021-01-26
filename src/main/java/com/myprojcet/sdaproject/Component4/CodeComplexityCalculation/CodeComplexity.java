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

//This class is used to calculate the complexity due to code element size and recursion
class ElemenetIdentifier{
    
    private String filePath;
    private List<String> originalFile;
    private List<String> imports = new ArrayList<>();
    private List<String> methods = new ArrayList<>();
    private List<String> fixedFile = new ArrayList<>();
    private Utility utility;
    private int complexityCount = 0;
    private List lineByLineSize = new ArrayList();
    private List recursionLinebyLine = new ArrayList();
    
        
    //keywords that have value 2
    private List<String> specialKeywords = Arrays.asList("delete", "new", "throw", "throws");
 
    //data types for methods
    private List<String> dataTypesMethods = Arrays.asList("boolean", "byte", "char", "short", "int", "long", "float", "double", "Boolean", "Character", "Integer", "String", "Double", "void");
    
    //main java keywords
    private List<String> keywords = Arrays.asList("abstract", "assert","break", "case", "catch", "const",
            "continue", "default", "do", "enum", "extends","false", "final", "finally", "for", "goto", "if",
            "implements", "import", "interface", "instanceof", "null", "native", "package", "staticfp", "super", 
            "switch", "synchronized", "this", "transient", "true", "volatile", "while", "System", "out", "println");

    //Identified methods and variables will be added to this list
    private List<String> variables = new ArrayList();
    
    //Identified classes and objects will be added to this list
    private List<String> classesAndObjects = new ArrayList();
    
    private List<String> identifiedKeywords = new ArrayList();
    
    public ElemenetIdentifier(String filePath) {
        this.filePath = filePath;
        this.utility = new Utility();
    }
    
    /*This method is used to calculate the complexity due to element size.
    *In this method the file will be analysized line by line and sort identified elements.
    *By the identified elements complexity will be calculated.
    *calculation method will change from metric to metric.
    */
    public void calculateSizeKeywords() {
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
                    this.lineByLineSize.add(0);
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
                        //System.out.println(line + "  Line Complexity = " + this.complexityCount);
                        this.lineByLineSize.add(this.complexityCount);
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
                
                //System.out.println(line + "  Line Complexity = " + this.complexityCount);
                this.lineByLineSize.add(this.complexityCount);
                this.complexityCount = 0;
            }
            
        } catch(Exception e) {
            e.printStackTrace();
        }
     
    }
    
    //This method is used to identify objects.
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
    
    //This method is used to identify methods.
    public void getMethods() {
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
                
                if(line.contains("import "))
                    continue;
                
                if(line.contains("package "))
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
                            
                            complexityCount++;
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
                
                for(String dt : dataTypesMethods){ 
                    for(int i = 0; i < wByW.length; i++){
                        if(wByW[i].equals(dt)){
                            if((i+1) < wByW.length && wByW[i+1].contains("(")){
                                String method = wByW[i+1];
                                
                                System.out.println(wByW[i]);
                                
                                for(String object : this.classesAndObjects){
                                    method = method.replaceAll("\\("+object, "()");
                                }
                                
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
            for(int i = 0; i < allLinesTrimmed.size() ; i++)
                System.out.println(allLinesTrimmed.get(i) + " " + lineByLineSize.get(i));
                
            this.fixedFile = allLinesTrimmed;
            
        } catch(Exception e){
            e.printStackTrace();
        }
    }
    
    //This method is used to identify recurvise methods in the code.
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

                    //System.out.println(start);
                    //System.out.println(end);
                    
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

    //This method is used to execute methods in proper order to calculate complexity.
    public void getSize(){
        calculateSizeKeywords();
        getObjects();
        getMethods();
        recursiveComplexity();
        
//        int total = 0;
//        ArrayList<Integer> cr = new ArrayList<>();
//        System.out.println("");
//        System.out.println("---------------------------------------------------------Code-----------------------------------------------------|---Size---|------|");
//        for(int i = 0; i < lineByLineSize.size(); i++){
//            cr.add((Integer) lineByLineSize.get(i) * (Integer) recursionLinebyLine.get(i));
//            //System.out.print(fixedFile.get(i) + "\t\t");
//            //System.out.print((Integer) lineByLineSize.get(i) + "\t|" + ((Integer) lineByLineSize.get(i) * (Integer) recursionLinebyLine.get(i)) + "\n");
//            System.out.format("%-120s%-10d%-16d\n", fixedFile.get(i), (Integer) lineByLineSize.get(i),((Integer) lineByLineSize.get(i) * (Integer) recursionLinebyLine.get(i)));
//            total += (int) lineByLineSize.get(i) * (Integer) recursionLinebyLine.get(i);
//        }
//        System.out.println(total);
    }
    
    //This method is used to get the calculated complexity line by line list.
    public List getLineByLineSize(){
        return this.lineByLineSize;
    }
    
    //This method is used to get the fixed file.
    public List getFixedFile(){
        return this.fixedFile;
    }
    
    //This method is used to get the recursion occurance line by line.
    public List getRecursionLineByLine(){
        return this.recursionLinebyLine;
    }
    
    //This method is used to get the methods.
    public List getMethodsArray(){
        return this.methods;
    }
    
    //This method is used to get the classes and objects list.
    public List getClassesAndObjectsArray(){
        return this.classesAndObjects;
    }
}

//public class CodeComplexity {
//    
//        public static void main(String[] args) {
//        ElemenetIdentifier e = new ElemenetIdentifier("C:/Users/Prabuddha Abisheka/Desktop/Test.java");
//        e.getSize();
//        e.getMethods();
//    }
//}