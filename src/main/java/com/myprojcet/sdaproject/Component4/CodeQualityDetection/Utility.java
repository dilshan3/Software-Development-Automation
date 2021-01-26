package com.myprojcet.sdaproject.Component4.CodeQualityDetection;

import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
/**
 *
 * @author Prabuddha Abisheka
 */
public class Utility {
    
    private List<String> file;
    private List<String> changedFile = new ArrayList<>();
    private int commentCount = 0;
    
    private List<String> accessorMethods = Arrays.asList("public", "private", "static", "protected");
    private List<String> ignoreKeywords = Arrays.asList("try", "return");
    private List<String> specialKeywords = Arrays.asList("delete", "new", "throw", "throws");
    private List<String> dataTypesMethods = Arrays.asList("boolean", "byte", "char", "short", "int", "long", "float", "double", "Boolean", "Character", "Integer", "String", "Double", "void");
    private List<String> keywords = Arrays.asList("abstract", "assert","break", "case", "catch", "const",
            "continue", "default", "do", "enum", "extends","false", "final", "finally", "for", "goto", "if",
            "implements", "import", "interface", "instanceof", "null", "native", "package", "staticfp", "super", 
            "switch", "synchronized", "this", "transient", "true", "volatile", "while", "System", "out", "println");
    private List<String> operators = Arrays.asList("+","-","*","/","%","++","--","==","!=",">","<",">=","<=","&&","||","!",
              "|","^","~","<<<",">>>",",","->",".","::","+=","-=","*=","/=","=",">>>=","|=","&=",
              "%=","<<=",">>=","^=","\n");
    
    public Utility (List<String> file){this.file = file;}
    
    //this method is used to remove comments from file line by line
    public List<String> removeComments(){
        
        int commentStart = -1;
        
        for(String line: file){
                
            
            if(file.contains("(?:/\\*(?:[^*]|(?:\\*+[^*/]))*\\*+/)|(?://.*)"))
                this.commentCount++;
            
            //remove all single line comments 
            line = line.replaceAll("(?:/\\*(?:[^*]|(?:\\*+[^*/]))*\\*+/)|(?://.*)","");

            //multiline comments will be excluded by not appending them to the arraylist
            if(line.contains("/*")){
                commentStart = file.indexOf(line);
            } else if(line.contains("*/")){
                commentStart = -1;
                continue;
            }

            if(commentStart != -1){
                continue;
            }
            
            changedFile.add(line);
        }
        
        return this.changedFile;
    }
    
    //This method will be used to extract import lines by file
    public List<String> extractImports(){
        
        List<String> importsList = new ArrayList<>();
        
        for(String line: file){
            
            if(line.contains("import"))
                importsList.add(line);
        }
        
        return importsList;
    }
    
    public int getCommentCount() {
        return this.commentCount;
    }
    
    public List<String> getAccessorMethods(){
        return this.accessorMethods;
    }
    
    public List<String> getIgnoreKeywords(){
        return this.ignoreKeywords;
    }
    
    public List<String> getSpecialKeywords(){
        return this.specialKeywords;
    }
    
    public List<String> getDataTypesMethods(){
        return this.dataTypesMethods;
    }
    
    public List<String> getKeywords(){
        return this.keywords;
    }
    
    public List<String> getOperators(){
        return this.operators;
    }
}
