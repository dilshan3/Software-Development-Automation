/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.myprojcet.sdaproject.Component4.CodeComplexityCalculation.Reports.HalsteadReport;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author Prabuddha Abisheka
 */
public class HalsteadReport {
    
    private List<String> codeLines;
    private List<Float> operands;
    private List<Float> operators;
    private double estimatedLength;
    private double programVocabulary;
    private double programVolume;
    private double potentialMinimumVolume;
    private double programLevel;
    private double effort;
    private double programmerTime;
    private float actualLength;
    
    public HalsteadReport(){
        
    }
    
    public HalsteadReport(List codeLines, List<Float> operands, List<Float> operators){
        this.codeLines = codeLines;
        this.operands = operands;
        this.operators = operators;
    }
    
    public HalsteadReport(List codeLines, List<Float> operands, List<Float> operators, double estimatedLength,
            double programVocabulary, double programVolume, double potentialMinimumVolume, double programLevel, double effort, double programmerTime){
        this.codeLines = codeLines;
        this.operands = operands;
        this.operators = operators;
        this.estimatedLength = estimatedLength;
        this.programVocabulary = programVocabulary;
        this.programVolume = programVolume;
        this.potentialMinimumVolume = potentialMinimumVolume;
        this.programLevel = programLevel;
        this.effort = effort;
        this.programmerTime = programmerTime;
    }
    
    public void generateReport(){
        
        try {
            List<Rows> listItems = new ArrayList<>();
        
            System.out.println("here at report");
            
            float operandCount = 0;
            float operatorCount = 0;
        
            for(int i = 0; i < this.operators.size(); i++){
                operatorCount += this.operators.get(i);
            }

            for(int i = 0; i < this.operands.size(); i++){
                operandCount += this.operands.get(i);
            }
        
            this.actualLength = operandCount + operatorCount;

            for(int i = 0; i < this.codeLines.size(); i++) {

                Rows newRow = new Rows();
                newRow.setLine(this.codeLines.get(i));
                newRow.setOperands(this.operands.get(i));
                newRow.setOperators(this.operators.get(i));
                
                listItems.add(newRow);
            }
            
            JRBeanCollectionDataSource itemsJRBean = new JRBeanCollectionDataSource(listItems);

            DecimalFormat df = new DecimalFormat("0.00");
            
            //Jasper report parameters 
            Map<String, Object> parameters = new HashMap<>();
            parameters.put("CollectionBeanParam", itemsJRBean);
            parameters.put("ActualLength", this.actualLength);
            parameters.put("EstimatedLength", df.format(this.estimatedLength));
            parameters.put("ProgramVocabulary", df.format(this.programVocabulary));
            parameters.put("ProgramVolume", df.format(this.programVolume));
            parameters.put("PotentialMinimumVolume", df.format(this.potentialMinimumVolume));
            parameters.put("ProgramLevel", df.format(this.programLevel));
            parameters.put("Effort", df.format(this.effort));
            parameters.put("ProgrammerTime", df.format(this.programmerTime));

            //read designed jrxml file and creating jasperdesign object
            InputStream input = new FileInputStream(new File(".\\ReportTemplates\\HalsteadReport.jrxml"));
            JasperDesign jasperDesign = JRXmlLoader.load(input);

            //compiling jrxml
            JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);

            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, new JREmptyDataSource());

            //Display report in jasperviewer window
            JasperViewer.viewReport(jasperPrint, false);

            //Create pdf
            String out = new SimpleDateFormat("yyyy-MM-dd hh-mm-ss").format(new Date());
            String outputFile = ".\\HalsteadReport_"+ out + ".pdf";
            OutputStream outputStream = new FileOutputStream(new File(outputFile));
            JasperExportManager.exportReportToPdfStream(jasperPrint, outputStream);

            System.out.println("report generated");	
	
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}
