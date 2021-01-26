/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.myprojcet.sdaproject.Component4.CodeComplexityCalculation.Reports.RecursionReport;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
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
public class RecursionReport {
    
    private List<String> codeLines;
    private List<Integer> lineByLineSize;
    private List<Integer> lineByLineRecursion;
    private int totalComplexity;
    private int sizeComplexity;
    
    public RecursionReport(){
        
    }
    
    public RecursionReport(List codeLines, List lineByLineSize, List lineByLineRecursion) {
        this.codeLines = codeLines;
        this.lineByLineSize = lineByLineSize;
        this.lineByLineRecursion = lineByLineRecursion;
    }
    
    public void generateReport(){
        
        try {
            List<Rows> listItems = new ArrayList<>();
        
            System.out.println("here at report");
            
            for(int i = 0; i < this.lineByLineSize.size(); i++){
                this.sizeComplexity += this.lineByLineSize.get(i);
                this.totalComplexity += this.lineByLineSize.get(i) * this.lineByLineRecursion.get(i);
            }

            System.out.println("t:"+this.totalComplexity + " s:"+this.sizeComplexity);
            
            for(int i = 0; i < this.lineByLineRecursion.size(); i++){
                if(this.lineByLineRecursion.get(i) == 1)
                    this.lineByLineRecursion.set(i, 0);
            }

            for(int i = 0; i < this.codeLines.size(); i++) {

                Rows newRow = new Rows();
                newRow.setLine(this.codeLines.get(i));
                newRow.setWeight1(this.lineByLineSize.get(i));
                newRow.setWeight2(this.lineByLineRecursion.get(i));

                listItems.add(newRow);
            }

            
            JRBeanCollectionDataSource itemsJRBean = new JRBeanCollectionDataSource(listItems);

            //Jasper report parameters 
            Map<String, Object> parameters = new HashMap<>();
            parameters.put("CollectionBeanParam", itemsJRBean);
            parameters.put("TotalComplexity", this.totalComplexity);
            parameters.put("SizeComplexity", this.sizeComplexity);

            //read designed jrxml file and creating jasperdesign object
            InputStream input = new FileInputStream(new File(".\\ReportTemplates\\RecursionReport.jrxml"));
            JasperDesign jasperDesign = JRXmlLoader.load(input);

            //compiling jrxml
            JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);

            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, new JREmptyDataSource());

            //Display report in jasperviewer window
            JasperViewer.viewReport(jasperPrint, false);

            //Create pdf
            String out = new SimpleDateFormat("yyyy-MM-dd hh-mm-ss").format(new Date());
            String outputFile = ".\\RecursionReport_"+ out + ".pdf";
            OutputStream outputStream = new FileOutputStream(new File(outputFile));
            JasperExportManager.exportReportToPdfStream(jasperPrint, outputStream);

            System.out.println("Report generated");	
	
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}
