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
public class HospitalUi extends javax.swing.JFrame {

    /**
     * Creates new form HospitalUi
     */
    private String scaleHospital, pharmacyHospital, channellingHospital, labsHospital;
    
    public HospitalUi() {
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

        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        pharmacy = new javax.swing.JComboBox<>();
        channelling = new javax.swing.JComboBox<>();
        scaleHos = new javax.swing.JComboBox<>();
        po = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        classDiagHos = new javax.swing.JCheckBox();
        eRDiagHos = new javax.swing.JCheckBox();
        jLabel4 = new javax.swing.JLabel();
        submitHos = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        labs = new javax.swing.JComboBox<>();
        back = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel5.setText("Availability of Channelling");

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel6.setText("Required diagrams");

        pharmacy.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        pharmacy.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { " ", "Available", "Not Available" }));
        pharmacy.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pharmacyActionPerformed(evt);
            }
        });

        channelling.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        channelling.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { " ", "Available", "Not Available" }));
        channelling.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                channellingActionPerformed(evt);
            }
        });

        scaleHos.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        scaleHos.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { " ", "Small", "Large" }));
        scaleHos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                scaleHosActionPerformed(evt);
            }
        });

        po.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        po.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        po.setText("Hospital Management System diagrams");

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel3.setText("Availability of Pharmacy");

        classDiagHos.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        classDiagHos.setText("Class Diagram");

        eRDiagHos.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        eRDiagHos.setText("ER diagram");

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel4.setText("Scale of hospital");

        submitHos.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        submitHos.setText("Submit");
        submitHos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                submitHosActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel1.setText("Lab Facilities");

        labs.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        labs.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { " ", "Available", "Not Available" }));
        labs.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                labsActionPerformed(evt);
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
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(po, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addGap(128, 128, 128)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 191, Short.MAX_VALUE)
                                .addComponent(pharmacy, javax.swing.GroupLayout.PREFERRED_SIZE, 340, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(scaleHos, javax.swing.GroupLayout.PREFERRED_SIZE, 340, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 173, Short.MAX_VALUE)
                                    .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(channelling, 0, 340, Short.MAX_VALUE)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(classDiagHos)
                                        .addGap(18, 18, 18)
                                        .addComponent(eRDiagHos))
                                    .addComponent(labs, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))))
                .addGap(67, 67, 67))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(submitHos, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(359, 359, 359))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(po, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(back, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(41, 41, 41)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(scaleHos, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 48, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(pharmacy, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(48, 48, 48)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(channelling, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(48, 48, 48)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labs, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(48, 48, 48)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(classDiagHos)
                    .addComponent(eRDiagHos)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(34, 34, 34)
                .addComponent(submitHos, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(20, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void submitHosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_submitHosActionPerformed
        // TODO add your handling code here:
        if(classDiagHos.isSelected()){
        
            try {
                hospitalClassDiagBuild();
            } catch (IOException ex) {
                Logger.getLogger(HospitalUi.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        if(eRDiagHos.isSelected()){
            
            
        }
    }//GEN-LAST:event_submitHosActionPerformed

    private void backActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_backActionPerformed
        // TODO add your handling code here:
        this.setVisible(false);
        this.dispose();
        new DiagramUi().setVisible(true);
    }//GEN-LAST:event_backActionPerformed

    private void scaleHosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_scaleHosActionPerformed
        
        scaleHospital = scaleHos.getItemAt(scaleHos.getSelectedIndex());
    }//GEN-LAST:event_scaleHosActionPerformed

    private void pharmacyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pharmacyActionPerformed
        
        pharmacyHospital = pharmacy.getItemAt(pharmacy.getSelectedIndex());
    }//GEN-LAST:event_pharmacyActionPerformed

    private void channellingActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_channellingActionPerformed
        
        channellingHospital = channelling.getItemAt(channelling.getSelectedIndex());
    }//GEN-LAST:event_channellingActionPerformed

    private void labsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_labsActionPerformed
       
        labsHospital = labs.getItemAt(labs.getSelectedIndex());
    }//GEN-LAST:event_labsActionPerformed

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
            java.util.logging.Logger.getLogger(HospitalUi.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(HospitalUi.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(HospitalUi.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(HospitalUi.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new HospitalUi().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton back;
    private javax.swing.JComboBox<String> channelling;
    private javax.swing.JCheckBox classDiagHos;
    private javax.swing.JCheckBox eRDiagHos;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JComboBox<String> labs;
    private javax.swing.JComboBox<String> pharmacy;
    private javax.swing.JLabel po;
    private javax.swing.JComboBox<String> scaleHos;
    private javax.swing.JButton submitHos;
    // End of variables declaration//GEN-END:variables


    public void hospitalClassDiagBuild() throws IOException{
        
        DisplayImage display = new DisplayImage();
        
        if(scaleHospital.equalsIgnoreCase("small")){
        
           if(pharmacyHospital.equalsIgnoreCase("Available") && labsHospital.equalsIgnoreCase("Available")){
             
               display.displayImg("C:/UML/hospital/Class/4.jpg");
           }
           else if(pharmacyHospital.equalsIgnoreCase("Available") && labsHospital.equalsIgnoreCase("Not Available")){

                display.displayImg("C:/UML/hospital/Class/5.jpg");
           }
           else{
               display.displayImg("C:/UML/hospital/Class/3.jpg");
               
           }
               
        }else{
            
            if(pharmacyHospital.equalsIgnoreCase("Available") && labsHospital.equalsIgnoreCase("Available") &&
                    channellingHospital.equalsIgnoreCase("Available")){
                 
                display.displayImg("C:/UML/hospital/Class/6.jpg");
            }
            else if(pharmacyHospital.equalsIgnoreCase("Available") && labsHospital.equalsIgnoreCase("Available") &&
                    channellingHospital.equalsIgnoreCase("Not Available")){
            
                display.displayImg("C:/UML/hospital/Class/11.jpg");
            }
            else if(pharmacyHospital.equalsIgnoreCase("Available") && labsHospital.equalsIgnoreCase("Not Available") &&
                    channellingHospital.equalsIgnoreCase("Available")){
            
                display.displayImg("C:/UML/hospital/Class/7.jpg");
            }
            else if(pharmacyHospital.equalsIgnoreCase("Not Available") && labsHospital.equalsIgnoreCase("Available") &&
                    channellingHospital.equalsIgnoreCase("Available")){
            
                display.displayImg("C:/UML/hospital/Class/8.jpg");
            }
            else if(pharmacyHospital.equalsIgnoreCase("Not Available") && labsHospital.equalsIgnoreCase("Not Available") &&
                    channellingHospital.equalsIgnoreCase("Available")){
            
                display.displayImg("C:/UML/hospital/Class/9.jpg");
            }
            else if(pharmacyHospital.equalsIgnoreCase("Available") && labsHospital.equalsIgnoreCase("Not Available") &&
                    channellingHospital.equalsIgnoreCase("Not Available")){
            
                display.displayImg("C:/UML/hospital/Class/10.jpg");
            }
            else{
           
                 display.displayImg("C:/UML/hospital/Class/1.png");
            }
        }
    }
}
