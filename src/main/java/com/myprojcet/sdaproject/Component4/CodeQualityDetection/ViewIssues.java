/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.myprojcet.sdaproject.Component4.CodeQualityDetection;

import java.awt.Component;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import javax.swing.JTable;
import javax.swing.table.DefaultTableColumnModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;

/**
 *
 * @author Prabuddha Abisheka
 */
public class ViewIssues extends javax.swing.JFrame {

    private ElementIdentifier eI;
    private int count = 0;
    
    public ViewIssues() {
        initComponents();
    }

    public ViewIssues(ElementIdentifier eI){
        initComponents();
        this.eI = eI;
        this.generateResults();
    }
    
    //This method is used to should the detected issues to the users.
    public void generateResults(){
        
        List<String> unusedImports = eI.unusedImportDetection();
        HashMap<Integer, String> redundantModifiers = eI.redundantInterfaceModifiers();
        List<String> invalidGenerics = eI.invalidGenericUsage();
        List<String> nestedTernary = eI.detectNestedTernaryOperators();
        List<String> ternaryUsage = eI.detectTernaryOperators();
        List<String> modifierOrder = eI.detectModifierOrder();
        //System.out.println(eI.getFileName() + " here1");
        
        List<String> output = new ArrayList<>();
        output.add("-----------------------------------------------------------------------------------------------");
        output.add("\n----------------------------Code Issues Detected-----------------------------------");
        output.add("\n-----------------------------------------------------------------------------------------------\n");
        
        if(!unusedImports.isEmpty()){
            output.add("\nUnused imports detected!\n");
            for(String unused : unusedImports){
                output.add("\n"+unused);
            }
            output.add("\n\n-----------------------------------------------------------------------------------------------");
            output.add("\nRecommendation: Remove unused imports to improve overall complilation time and maintainance time.");
            output.add("\n\n-----------------------------------------------------------------------------------------------");
            output.add("\n--------------This issue can be fixed using the application--------------------");
            output.add("\n-----------------------------------------------------------------------------------------------\n");
            count++;
        }
        
        if(!redundantModifiers.isEmpty()){
            output.add("\nRedundant access modifiers usage in interfaces detected!\n");
            for(Integer key : redundantModifiers.keySet()){
                String interfaceName = redundantModifiers.get(key);
                
                int indexOfInterface = eI.getIndexOf(interfaceName);
                String interfaceLine = eI.getLineOfCode(indexOfInterface);              
                
                output.add("\n" + interfaceLine + " : Line " + (indexOfInterface + 1));
                output.add("\n" + eI.getLineOfCode(key + indexOfInterface) + " : Line " + (key + indexOfInterface + 1) + "\n");
            }
            output.add("\n\n-----------------------------------------------------------------------------------------------");
            output.add("\nRecommendation: Remove the redundant access modifier from the interface method.");
            output.add("\nIf the interface is public, methods access modifier will public as well.");
            output.add("\nExample: ");
            output.add("\npublic interface Clockwork {");
            output.add("\n\tint getTimeNow();");
            output.add("\n};");
            output.add("\n\n-----------------------------------------------------------------------------------------------");
            output.add("\n--------------This issue can be fixed using the application--------------------");
            output.add("\n-----------------------------------------------------------------------------------------------\n");
            count++;
        }
        
        if(!invalidGenerics.isEmpty()){
            output.add("\nInvalid Generics Usage In Constructors detected!\n");
            for(String line : invalidGenerics){
                
                List<String> originalFile = eI.getOriginalFile();
                
                int indexOfLine = originalFile.indexOf(line);              
                
                output.add("\n" + line.trim() + " : Line " + (indexOfLine + 1));
            }
            output.add("\n\n-----------------------------------------------------------------------------------------------");
            output.add("\nRecommendation: Remove generics usage in the constructor if the declaration already have generics ");
            output.add("\nExample: List<String> newList = ArrayList<>()");
            output.add("\n\n-----------------------------------------------------------------------------------------------");
            output.add("\n--------------This issue can be fixed using the application--------------------");
            output.add("\n-----------------------------------------------------------------------------------------------\n");
            count++;
        }
        
        if(!ternaryUsage.isEmpty()){
            output.add("\nTernary operators usage detected!\n");
            for(String line : ternaryUsage){
                List<String> originalFile = eI.getOriginalFile();
                
                int indexOfLine = originalFile.indexOf(line);              
                
                output.add("\n" + line.trim() + " : Line " + (indexOfLine + 1));
            }
            output.add("\n\n-----------------------------------------------------------------------------------------------");
            output.add("\nRecommendation: Ternary operators usage is not recommended. "
                    + "\nNesting ternary operator will increase congnitive complexity."
                    + "\nConvert the nested ternary operators to if-else conditions");
            output.add("\n\n-----------------------------------------------------------------------------------------------");
            output.add("\n--------------This issue can be fixed using the application--------------------");
            output.add("\n-----------------------------------------------------------------------------------------------\n");
            count++;
        }
        
        if(!nestedTernary.isEmpty()){
            output.add("\nNested ternary operators detected!\n");
            for(String nested : nestedTernary){
                List<String> originalFile = eI.getOriginalFile();
                
                int indexOfLine = originalFile.indexOf(nested);              
                
                output.add("\n" + nested.trim() + " : Line " + (indexOfLine + 1));
            }
            output.add("\n\n-----------------------------------------------------------------------------------------------");
            output.add("\nRecommendation: Ternary operators should not be nested. "
                    + "\nNesting ternary operator will increase congnitive complexity."
                    + "\nConvert the nested ternary operators to if-else conditions");
            output.add("\n\n-----------------------------------------------------------------------------------------------");          
            output.add("\n-----------------------------------------------------------------------------------------------\n");
            count++;
        }
        
        if(!modifierOrder.isEmpty()){
            output.add("\nInvalid modifier order detected!\n");
            for(String mod : modifierOrder){
                List<String> originalFile = eI.getOriginalFile();
                
                int indexOfLine = originalFile.indexOf(mod);              
                
                output.add("\n" + mod.trim() + " : Line " + (indexOfLine + 1));
            }
            output.add("\n\n-----------------------------------------------------------------------------------------------");
            output.add("\nRecommendation: Modifiers should be declared in the correct order. "
                    + "\nThe Java Language Specification recommends listing modifiers in the following order:"
                    + "\n public"
                    + "\n protected"
                    + "\n private"
                    + "\n abstract"
                    + "\n static"
                    + "\n final"
                    + "\n transient"
                    + "\n volatile"
                    + "\n synchronized"
                    + "\n native"
                    + "\n strictfp"
            );
            output.add("\n\n-----------------------------------------------------------------------------------------------");          
            output.add("\n-----------------------------------------------------------------------------------------------\n");
            count++;
        }
        
        for(String detections : output){
            this.jTextArea1.append(detections);
        }
        
        this.jTextField1.setText(count + "");
        
//        for(int i = 0; i < this.lineByLineSize.size(); i++){
//            this.totalComplexity += this.lineByLineSize.get(i);
//        }
//        
//        DefaultTableModel dataModel = (DefaultTableModel) this.jTable1.getModel();
//        for(int i = 0; i < this.codeLines.size(); i++) {
//            Object o[] = new Object[2];
//            o[0] = this.codeLines.get(i);
//            o[1] = this.lineByLineSize.get(i);
//            dataModel.addRow(o);
//        }
//        this.jTable1.setAutoResizeMode(JTable.AUTO_RESIZE_SUBSEQUENT_COLUMNS);
//        
//        for (int i = 0; i < 1; i++) {
//            DefaultTableColumnModel colModel = (DefaultTableColumnModel) this.jTable1.getColumnModel();
//            TableColumn col = colModel.getColumn(i);
//            int width = 0;
//            int height = 0;
//
//            TableCellRenderer renderer = col.getHeaderRenderer();
//            for (int r = 0; r < this.jTable1.getRowCount(); r++) {
//              renderer = this.jTable1.getCellRenderer(r, i);
//              Component comp = renderer.getTableCellRendererComponent(this.jTable1, this.jTable1.getValueAt(r, i),
//                  false, false, r, i);
//              width = Math.max(width, comp.getPreferredSize().width);
//              height = Math.max(height, comp.getPreferredSize().height);
//            }
//        }
//        
//        this.jTextField1.setText(this.totalComplexity + " ");
//        this.jTextField2.setText(this.commentsCount + " ");
//        this.jTextField3.setText(Math.round(this.commentPercentage * 10000.0)/100.0 + "% ");
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jLabel2 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel1.setText("Detected Code Issues");

        jTextArea1.setEditable(false);
        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jScrollPane2.setViewportView(jTextArea1);

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel2.setText("Number of Detected Issues");

        jTextField1.setEditable(false);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(319, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(331, 331, 331))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(8, 8, 8)
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 199, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 471, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(20, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(ViewIssues.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ViewIssues.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ViewIssues.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ViewIssues.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                ViewIssues rw = new ViewIssues();
                rw.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JTextField jTextField1;
    // End of variables declaration//GEN-END:variables
}
