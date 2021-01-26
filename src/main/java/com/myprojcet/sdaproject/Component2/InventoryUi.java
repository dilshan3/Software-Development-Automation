/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.myprojcet.sdaproject.Component2;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author HP
 */
public class InventoryUi extends javax.swing.JFrame {

    /**
     * Creates new form InventoryUi
     */
    private String transportInventory, securityInventory, sizeInventory; 
            
    public InventoryUi() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        transport = new javax.swing.JComboBox<>();
        sizeInv = new javax.swing.JComboBox<>();
        po = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        classDiagInv = new javax.swing.JCheckBox();
        eRDiagInv = new javax.swing.JCheckBox();
        jLabel5 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        submitInv = new javax.swing.JButton();
        security = new javax.swing.JComboBox<>();
        back = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        transport.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        transport.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { " ", "Available", "Not Available" }));
        transport.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                transportActionPerformed(evt);
            }
        });

        sizeInv.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        sizeInv.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { " ", "<100 items", "<1000 items", "<10000 items", " " }));
        sizeInv.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sizeInvActionPerformed(evt);
            }
        });

        po.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        po.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        po.setText("Inventory Control System diagrams");

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel3.setText("Availability of Security");

        classDiagInv.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        classDiagInv.setText("Class Diagram");
        classDiagInv.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                classDiagInvActionPerformed(evt);
            }
        });

        eRDiagInv.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        eRDiagInv.setText("ER diagram");
        eRDiagInv.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                eRDiagInvActionPerformed(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel5.setText("Availability of Goods transport");

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel4.setText("Size of inventory");

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel6.setText("Required diagrams");

        submitInv.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        submitInv.setText("Submit");
        submitInv.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                submitInvActionPerformed(evt);
            }
        });

        security.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        security.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { " ", "Available", "Not Available" }));
        security.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                securityActionPerformed(evt);
            }
        });

        back.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        back.setText("Back");
        back.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                backActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(back, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(26, 26, 26)
                        .addComponent(po, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addGap(128, 128, 128)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(security, javax.swing.GroupLayout.PREFERRED_SIZE, 340, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(sizeInv, javax.swing.GroupLayout.PREFERRED_SIZE, 340, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(0, 0, Short.MAX_VALUE)
                                        .addComponent(jLabel5)
                                        .addGap(187, 187, 187))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(classDiagInv)
                                        .addGap(18, 18, 18)
                                        .addComponent(eRDiagInv))
                                    .addComponent(transport, javax.swing.GroupLayout.PREFERRED_SIZE, 340, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                .addGap(67, 67, 67))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(submitInv, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(377, 377, 377))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(po, javax.swing.GroupLayout.DEFAULT_SIZE, 31, Short.MAX_VALUE)
                    .addComponent(back, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(44, 44, 44)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(sizeInv, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 40, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(security, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 41, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(transport, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(40, 40, 40)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(classDiagInv)
                        .addComponent(eRDiagInv)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 59, Short.MAX_VALUE)
                .addComponent(submitInv, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(48, 48, 48))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void classDiagInvActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_classDiagInvActionPerformed
        
    }//GEN-LAST:event_classDiagInvActionPerformed

    private void eRDiagInvActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_eRDiagInvActionPerformed
        
    }//GEN-LAST:event_eRDiagInvActionPerformed

    private void submitInvActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_submitInvActionPerformed
        
        if(classDiagInv.isSelected()){
            
            try {
                    inventoryClassDiagBuild();
                } catch (IOException ex) {
                    Logger.getLogger(InventoryUi.class.getName()).log(Level.SEVERE, null, ex);
                }
        }
        
        if(eRDiagInv.isSelected()){
        
        }
    }//GEN-LAST:event_submitInvActionPerformed

    private void backActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_backActionPerformed
        // TODO add your handling code here:
        this.setVisible(false);
        this.dispose();
        new DiagramUi().setVisible(true);
    }//GEN-LAST:event_backActionPerformed

    private void sizeInvActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sizeInvActionPerformed
     
        sizeInventory = sizeInv.getItemAt(sizeInv.getSelectedIndex());
    }//GEN-LAST:event_sizeInvActionPerformed

    private void securityActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_securityActionPerformed
       
        securityInventory = security.getItemAt(security.getSelectedIndex());
    }//GEN-LAST:event_securityActionPerformed

    private void transportActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_transportActionPerformed
       
        transportInventory = transport.getItemAt(transport.getSelectedIndex());
    }//GEN-LAST:event_transportActionPerformed

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
            java.util.logging.Logger.getLogger(InventoryUi.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(InventoryUi.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(InventoryUi.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(InventoryUi.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new InventoryUi().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton back;
    private javax.swing.JCheckBox classDiagInv;
    private javax.swing.JCheckBox eRDiagInv;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel po;
    private javax.swing.JComboBox<String> security;
    private javax.swing.JComboBox<String> sizeInv;
    private javax.swing.JButton submitInv;
    private javax.swing.JComboBox<String> transport;
    // End of variables declaration//GEN-END:variables

    public void inventoryClassDiagBuild() throws IOException{
        
        DisplayImage display = new DisplayImage();
        
        if(sizeInventory.equalsIgnoreCase("<100 items")){
        
            if(securityInventory.equalsIgnoreCase("Available") && transportInventory.equalsIgnoreCase("Not Available")){
               
                display.displayImg("C:/UML/inventory/Class/2.jpg");
            }
            else if(securityInventory.equalsIgnoreCase("Not Available") && transportInventory.equalsIgnoreCase("Available")){
                
                display.displayImg("C:/UML/inventory/Class/4.jpg");
            }
            else{
            
                display.displayImg("C:/UML/inventory/Class/3.jpg");
            }
               
        }else if(sizeInventory.equalsIgnoreCase("<1000 items")){
            
            if(securityInventory.equalsIgnoreCase("Available") && transportInventory.equalsIgnoreCase("Available")){
                 
               display.displayImg("C:/UML/inventory/Class/5.jpg");
            }
            else if(securityInventory.equalsIgnoreCase("Available") && transportInventory.equalsIgnoreCase("Not Available")){
            
                display.displayImg("C:/UML/inventory/Class/6.jpg");
            }
            else if(securityInventory.equalsIgnoreCase("Not Available") && transportInventory.equalsIgnoreCase("Available")){
            
                display.displayImg("C:/UML/inventory/Class/7.jpg");
            }
            else{
                display.displayImg("C:/UML/inventory/Class/8.jpg");
            }
        }else{
            
            if(securityInventory.equalsIgnoreCase("Available") && transportInventory.equalsIgnoreCase("Available")){
                 
               display.displayImg("C:/UML/inventory/Class/9.jpg");
            }
            else if(securityInventory.equalsIgnoreCase("Available") && transportInventory.equalsIgnoreCase("Not Available")){
            
               display.displayImg("C:/UML/inventory/Class/10.jpg"); 
            }
            else if(securityInventory.equalsIgnoreCase("Not Available") && transportInventory.equalsIgnoreCase("Available")){
            
               display.displayImg("C:/UML/inventory/Class/11.jpg");
            }
            else{
                
                display.displayImg("C:/UML/inventory/Class/12.jpg");
            }
            
        }
    }
}