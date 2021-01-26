package com.myprojcet.sdaproject.Component4.CodeQualityDetection;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

//This class is used for identification and fixing code quality issues.
class ElementIdentifier{
    
    private String filePath;
    private List<String> originalFile;
    private List<String> imports = new ArrayList<>();
    private List<String> methods = new ArrayList<>();
    private List<String> fixedFile = new ArrayList<>();
    private Utility utility;
    private int complexityCount = 0;
    private List lineByLineSize = new ArrayList();
    private List recursionLinebyLine = new ArrayList();
    private String filename;
        
    //keywords that have value 2
    private List<String> specialKeywords = Arrays.asList("delete", "new", "throw", "throws");
 
    //data types for methods
    private List<String> dataTypesMethods = Arrays.asList("boolean", "byte", "char", "short", "int", "long", "float", "double", "Boolean", "Character", "Integer", "String", "Double", "void");
    private List<String> modifiers = Arrays.asList("public", "protected", "private", "abstract", "static", "final", "transient", "volatile", "sychronized", "native", "strictfp");
    
    //main java keywords
    private List<String> keywords = Arrays.asList("abstract", "assert","break", "case", "catch", "const",
            "continue", "default", "do", "enum", "extends","false", "final", "finally", "for", "goto", "if",
            "implements", "import", "interface", "instanceof", "null", "native", "package", "staticfp", "super", 
            "switch", "synchronized", "this", "transient", "true", "volatile", "while", "System", "out", "println");

    //List<String> manipulators = Arrays.asList("endl", "\\n", "");

    //Identified methods and variables will be added to this list
    private List<String> variables = new ArrayList();
    
    //Identified classes and objects will be added to this list
    private List<String> classesAndObjects = new ArrayList();
    
    private List<String> identifiedKeywords = new ArrayList();
    
    public ElementIdentifier(String path){
        this.filePath = path;
    }
    
    //This method is used to identify elements in the users selected code in order to proceed with issues detection.
    public void analyzeCodeQuality(){
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
            
        } catch (Exception e){
            
        }
    }
    
    //This method is used to get the user's selected file's name.
    public String getFileName(){
        return Paths.get(this.filePath).getFileName().toString();
    }
    
    //This method is used to identify objects in the code.
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
    
    //This method is used to identify methods in the code.
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
    
    //This method is used to detect unused imports in code.
    public List<String> unusedImportDetection(){
        
        List<String> unusedImports = new ArrayList<>();
        
        try{
            List<String> file = Files.readAllLines(Paths.get(this.filePath));
            Utility util = new Utility(file);
            
            List<String> imports = util.extractImports();
            List<String> usedImports = new ArrayList<>();
            int commentStart = -1;
            
            for(String imported: imports){
                
                imported = imported.replaceAll("\\.", " ");
                imported = imported.replaceAll("\\;", " ");
                
                String[] wByW = imported.trim().split(" ");
                int length = wByW.length;
                
                if(wByW[length-1].trim().isEmpty())
                    continue;
                
                if(wByW[length-1].trim().equals("*"))
                    continue;
                
                String currentImport = wByW[length-1].trim();
                
                System.out.println(currentImport);
                
                for(String line : file){
                    
                    //remove all comments
                    line = line.replaceAll("(?:/\\*(?:[^*]|(?:\\*+[^*/]))*\\*+/)|(?://.*)","");
                
                    if(line.contains("/*")){
                        commentStart = file.indexOf(line);
                    } else if(line.contains("*/")){
                        commentStart = -1;
                        continue;
                    }

                    if(commentStart != -1){
                        continue;
                    }

                    if(line.contains("import "))
                        continue;
                
                    if(line.contains("package "))
                        continue;

                    if(line.contains("class "))
                        continue;
                  
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
                    
                    if(line.contains(currentImport)){
                        usedImports.add(currentImport);
                    }
                }
            }
            
            for(String imported: imports){
                
                //List<String> temp = imports;
                int index = imports.indexOf(imported);
                imported = imported.replaceAll("\\.", " ");
                imported = imported.replaceAll("\\;", " ");

                String[] wByW = imported.trim().split(" ");
                int length = wByW.length;

                if(wByW[length-1].trim().isEmpty())
                    continue;

                if(wByW[length-1].trim().equals("*"))
                    continue;

                String currentImport = wByW[length-1].trim();
                
                if(usedImports.contains(currentImport)){
                   continue;
                }
                
                unusedImports.add(imports.get(index));
            }
            
            return unusedImports;
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return unusedImports;
    }
    
    //This method is used to identify redundant interface modifier usage.
    public HashMap<Integer, String> redundantInterfaceModifiers(){
        
        try{
            List<String> file = Files.readAllLines(Paths.get(this.filePath));
            HashMap<Integer, String> indexes = new HashMap<>();
            int bracketCount = 0;
            int commentStart = -1;
            int startingIndex = 0;
            int endingIndex;
            String modifier = "";
            String currentInterface = "";
            boolean interfaceFound = false;
            
            for(String line : file) {
                //remove all comments
                line = line.replaceAll("(?:/\\*(?:[^*]|(?:\\*+[^*/]))*\\*+/)|(?://.*)","");

                if(line.contains("/*")){
                    commentStart = file.indexOf(line);
                } else if(line.contains("*/")){
                    commentStart = -1;
                    continue;
                }

                if(commentStart != -1){
                    continue;
                }
                
                if(line.contains(" interface ")) {
                    
                    startingIndex = file.indexOf(line);
                    
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
                    line = line.replaceAll("\\{", " ");
                    
                    String wByW[] = line.split(" ");
                    modifier = wByW[0];
                    
                    
//                    for(int i = 0; i < wByW.length; i++){
//                        if(wByW[i].e)
//                    }
                    
                    currentInterface = wByW[wByW.length - 1];
                    bracketCount++;
                    interfaceFound = true;
                    continue;
                }
                
                if(interfaceFound){
                
                    if(line.contains("}")){
                        bracketCount--;
                    }

                    if(line.contains("{")){
                        bracketCount++;
                    }

                    if(bracketCount > 0){
                        if(line.contains(modifier)){
                            indexes.put((file.indexOf(line) - startingIndex), currentInterface);
                            
                            System.out.println(file.indexOf(line));
                            System.out.println(startingIndex);
                            System.out.println(indexes);
                        }
                    }
                    
                    if(bracketCount == 0){
                        interfaceFound = false;
                    }
                }
            }
            
            return indexes;
        
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return null;
    }
    
    //This method is used to identify invalid generic usages in code.
    public List<String> invalidGenericUsage(){
        try {
            
            List<String> file = Files.readAllLines(Paths.get(this.filePath));
            int commentStart = -1;
            List<String> issues = new ArrayList<>();
            
            for(String line: file){
                
                //remove all comments
                line = line.replaceAll("(?:/\\*(?:[^*]|(?:\\*+[^*/]))*\\*+/)|(?://.*)","");

                if(line.contains("/*")){
                    commentStart = file.indexOf(line);
                } else if(line.contains("*/")){
                    commentStart = -1;
                    continue;
                }

                if(commentStart != -1){
                    continue;
                }
                
                if(line.contains(" new ")){
                    if(line.contains("<") && line.contains(">")){                        
                        
                        System.out.println(line);
                        
                        String firstGeneric = line.substring(line.indexOf('<') + 1, line.indexOf('>'));
                        String lastGeneric = line.substring(line.lastIndexOf('<') + 1, line.lastIndexOf('>'));
                        
//                        System.out.println(firstGeneric);
//                        System.out.println(lastGeneric);
//                        line = line.replaceFirst(firstGeneric, "1");
//                        line = line.replaceAll(lastGeneric, "");
//                        line = line.replaceFirst("<1>", "<" + firstGeneric + ">");
                        
                        if(firstGeneric.equals(lastGeneric)){
                            issues.add(line);
                        }
                    }
                }
            }
            
            return issues;
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return null;
    }
    
    //This method is used to detect ternary operators.
    public List<String> detectTernaryOperators(){
        try {
            
            List<String> file = Files.readAllLines(Paths.get(this.filePath));
            int commentStart = -1;
            List<String> issues = new ArrayList<>();
            
            for(String line: file){
                
                //remove all comments
                line = line.replaceAll("(?:/\\*(?:[^*]|(?:\\*+[^*/]))*\\*+/)|(?://.*)","");

                if(line.contains("/*")){
                    commentStart = file.indexOf(line);
                } else if(line.contains("*/")){
                    commentStart = -1;
                    continue;
                }

                if(commentStart != -1){
                    continue;
                }
                
                if(line.contains("?") && line.contains(":")){
                    String check = line.trim();
                    int firstIndex = check.indexOf('?');
                    int lastIndex = check.lastIndexOf('?');
                    
                    if(firstIndex == lastIndex) {
                    
                        issues.add(line);
                        
                        //String op = line.trim();
                        //int indexOfop = op.indexOf('?');
                        //int indexOfCond = op.indexOf(':');

                        //op = op.replaceAll("\\", "");
                        //op = op.replaceAll("\\(", " ");
                        //op = op.replaceAll("\\)", " ");
                        //op = op.replaceAll("\\)", "");

//                        String wByW[] = op.split(" ");
//                        String output = wByW[0];
//
//                        int outputLength = output.length();
//                        String ifCondition = op.trim().substring(outputLength, indexOfop);
//                        String trueCondition = line.trim().substring(indexOfop + 1, indexOfCond);
//                        String falseCondition = line.trim().substring(indexOfCond + 1);
//                        falseCondition = falseCondition.replaceAll("\\;", "");
//                        
//                        String lastSyntax = "";
//                        
//                        if( -1 == falseCondition.lastIndexOf(')'))
//                            lastSyntax = ")";
//                        
//                        System.out.println(output);
//                        System.out.println(outputLength);
//                        System.out.println("if(" + ifCondition + ") {");
//                        System.out.println("\t" + output + "(" +trueCondition.trim() + ");");
//                        System.out.println("} else {");
//                        System.out.println("\t" + output + "(" + falseCondition.trim() + lastSyntax + ";");
//                        System.out.println("}");
                        
                    }
                    
                }
            }
            
            return issues;
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return null;
    }
    
    //This method is used to fix the issue due to use of ternary operators.
    public HashMap<String, List<String>> convertTernaryOperator(){
        try {
            
            List<String> file = Files.readAllLines(Paths.get(this.filePath));
            int commentStart = -1;
            HashMap<String, List<String>> fixedMap = new HashMap<>();
            int count = 0;
            
            for(String line: file){
                
                //remove all comments
                line = line.replaceAll("(?:/\\*(?:[^*]|(?:\\*+[^*/]))*\\*+/)|(?://.*)","");

                if(line.contains("/*")){
                    commentStart = file.indexOf(line);
                } else if(line.contains("*/")){
                    commentStart = -1;
                    continue;
                }

                if(commentStart != -1){
                    continue;
                }
                
                if(line.contains("?") && line.contains(":")){
                    String check = line.trim();
                    int firstIndex = check.indexOf('?');
                    int lastIndex = check.lastIndexOf('?');
                    
                    if(firstIndex == lastIndex) {
                    
                        List<String> fixed = new ArrayList<>();
                        count++;
                        
                        String op = line.trim();
                        int indexOfop = op.indexOf('?');
                        int indexOfCond = op.indexOf(':');

                        //op = op.replaceAll("\\", "");
                        op = op.replaceAll("\\(", " ");
                        op = op.replaceAll("\\)", " ");
                        //op = op.replaceAll("\\)", "");

                        String wByW[] = op.split(" ");
                        String output = wByW[0];

                        int outputLength = output.length();
                        String ifCondition = op.trim().substring(outputLength, indexOfop);
                        String trueCondition = line.trim().substring(indexOfop + 1, indexOfCond);
                        String falseCondition = line.trim().substring(indexOfCond + 1);
                        falseCondition = falseCondition.replaceAll("\\;", "");
                        
                        String lastSyntax = "";
                        
                        if( -1 == falseCondition.lastIndexOf(')'))
                            lastSyntax = ")";
                        
                        //System.out.println(output);
                        //System.out.println(outputLength);
                        fixed.add("if(" + ifCondition + ") {");
                        fixed.add("\t" + output + "(" +trueCondition.trim() + ");");
                        fixed.add("} else {");
                        fixed.add("\t" + output + "(" + falseCondition.trim() + lastSyntax + ";");
                        fixed.add("}");
                        
                        fixedMap.put(line, fixed);
                    }
                    
                }
            }
            
            return fixedMap;
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return null;
    }
    
    //This method is used to detect the usage of nested ternary operators.
    public List<String> detectNestedTernaryOperators(){
        try {
            
            List<String> file = Files.readAllLines(Paths.get(this.filePath));
            int commentStart = -1;
            List<String> issues = new ArrayList<>();
            
            for(String line: file){
                
                //remove all comments
                line = line.replaceAll("(?:/\\*(?:[^*]|(?:\\*+[^*/]))*\\*+/)|(?://.*)","");

                if(line.contains("/*")){
                    commentStart = file.indexOf(line);
                } else if(line.contains("*/")){
                    commentStart = -1;
                    continue;
                }

                if(commentStart != -1){
                    continue;
                }
                
                if(line.contains("?") && line.contains(":")){
                    //System.out.println(line);
                    
                    String op = line.trim();
                    int firstIndex = op.indexOf('?');
                    int lastIndex = op.lastIndexOf('?');
                    
                    if(firstIndex != lastIndex) {
                        issues.add(line);
                        System.out.println(line + " asda");
                    }
                }
            }
            
            return issues;
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return null;
    }
    
    //This method is used to identify declared access modifier order.
    public List<String> detectModifierOrder(){
        
        try {
            
            List<String> file = Files.readAllLines(Paths.get(this.filePath));
            int commentStart = -1;
            List<String> issues = new ArrayList<>();
            
            for(String line: file){
                
                //remove all comments
                line = line.replaceAll("(?:/\\*(?:[^*]|(?:\\*+[^*/]))*\\*+/)|(?://.*)","");

                if(line.contains("/*")){
                    commentStart = file.indexOf(line);
                } else if(line.contains("*/")){
                    commentStart = -1;
                    continue;
                }

                if(commentStart != -1){
                    continue;
                }
                
                String op;
                
                op = line.replaceAll("\n", "");
                op = line.replaceAll("\t", "");
                op = line.replaceAll("\\;", " ");
                op = line.replaceAll("\\,", " ");
                op = line.replaceAll("\\[", " ");
                op = line.replaceAll("\\]", " ");
                op = line.replaceAll("\\[]", " ");                
                op = line.replaceAll("\\(", " ");
                op = line.replaceAll("\\)", " ");
                op = line.replaceAll("\\.", " ");
                op = line.replaceAll("\\++", " ");
                op = line.replaceAll("\\--", " ");
                op = line.replaceAll("\\+=", " ");
                op = line.replaceAll("\\-=", " ");
                op = line.replaceAll("\\/=", " ");
                op = line.replaceAll("\\*=", " ");                
                op = line.replaceAll("\\=", "");
                op = line.replaceAll("\\?", " ");
                op = line.replaceAll("\\/", " ");
                op = line.replaceAll("\\%", " ");
                op = line.replaceAll("\\*", " ");
                op = line.replaceAll("\\:", " ");
                op = line.replaceAll("\\&", " ");
                op = line.replaceAll("\\>", " ");
                op = line.replaceAll("\\<", " ");
                op = line.replaceAll("\\|", " ");
                op = line.replaceAll("\\!=", " ");
                op = line.replaceAll("\\~", " ");
                op = line.replaceAll("\\+", " ");
                op = line.replaceAll("\\-", " ");
                op = line.replaceAll("\\!", " ");
                op = line.replaceAll("\\^", " ");
                //line = line.replaceAll("else", "");
                
                System.out.println(line);
                
                if(op.trim().isEmpty()){
                    continue;
                }                
                
                op = line.replaceAll("\\{", " ");
                op = line.replaceAll("\\}", " ");
                
                String[] wByW = op.trim().split(" ");
                
                boolean found = false;
                String mod = "";
                int modLine = -1;
                
                for(int i = 0; i < wByW.length; i++){
                    if(wByW[i].trim().isEmpty())
                        continue;
                    
                    if(this.modifiers.contains(wByW[i].trim())){
                        if(!found) {
                            mod = wByW[i].trim();
                            modLine = i;
                            found = true;
                            continue;
                        }

                        int indexP = modifiers.indexOf(mod);
                        int indexC = modifiers.indexOf(wByW[i].trim());
                        
                        if(indexC < indexP){
                            issues.add(line);
                        } else {
                            mod = wByW[i].trim();
                        }
                    }
                }
            }
            
            return issues;
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return null;
    }
    
    //This method is used to get the index of codeline by using the codeline.
    public int getIndexOf(String find){
        try{
            List<String> file = Files.readAllLines(Paths.get(this.filePath));
            
            for(String line : file){
                
                int index = file.indexOf(line);
                
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
                line = line.replaceAll("\\{", " ");
                
                if(line.contains(find)){
                    return index;
                }
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        return -1;
    }
    
    //This method is used to get the code line depending on the index (codeline)
    public String getLineOfCode(int index){
        try{
            List<String> file = Files.readAllLines(Paths.get(this.filePath));
            
            return file.get(index);
        } catch (Exception e){
            e.printStackTrace();
        }
        
        return null;
    }
    
    //This method is used to get the identified variables.
    public List getVariables(){
        return this.variables;
    }
    
    //This method is used to get the methods list.
    public List getMethodsArray(){
        return this.methods;
    }
    
    //This method is used to get the classes and objects list.
    public List getClassesAndObjectsArray(){
        return this.classesAndObjects;
    }
    
    //This method is used to get the fixed file.
    public List getFixedFile(){
        return this.fixedFile;
    }
    
    //This method is used to get the user's select original file as a list.
    public List getOriginalFile(){
        
        try {
            return Files.readAllLines(Paths.get(this.filePath));
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return null;
    }
}

public class CodeQuality {
//    
//        public static void main(String[] args) {
//        ElementIdentifier e = new ElementIdentifier("C:/Users/Prabuddha Abisheka/Desktop/QualityTest.java");
//        e.unusedImportDetection();
//        e.redundantInterfaceModifiers();
//        e.invalidGenericUsage();
//        e.detectTernaryOperators();
//        e.detectNestedTernaryOperators();
//    }
}