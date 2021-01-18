package com.jr12221.frcdatawizard;

import java.awt.Point;
import java.awt.event.KeyEvent;


/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author jack
 */
public class PromptDialog extends javax.swing.JDialog {

    /**
     * Creates new form PromptDialog
     *
     * @param parent
     * @param modal
     * @param message
     */
    public PromptDialog(java.awt.Frame parent, boolean modal, String message) {
        super(parent, modal);
        initComponents();
        areaForText.setText(message);
        answerField.grabFocus();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        answerField = new HintJObject(ttAnswer);
        jScrollPane1 = new javax.swing.JScrollPane();
        areaForText = new javax.swing.JTextArea();
        answerButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        answerField.setColumns(20);
        answerField.setFont(new java.awt.Font("FreeSans", 0, 24)); // NOI18N
        answerField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                answerFieldKeyPressed(evt);
            }
        });

        areaForText.setEditable(false);
        areaForText.setColumns(20);
        areaForText.setFont(new java.awt.Font("FreeSans", 0, 24)); // NOI18N
        areaForText.setLineWrap(true);
        areaForText.setRows(5);
        jScrollPane1.setViewportView(areaForText);

        answerButton.setFont(new java.awt.Font("FreeSans", 0, 24)); // NOI18N
        answerButton.setText("Enter");
        answerButton.setFocusable(false);
        answerButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                answerButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 760, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(answerField, javax.swing.GroupLayout.PREFERRED_SIZE, 560, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(answerButton, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(23, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 500, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 46, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(answerField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(answerButton))
                .addGap(42, 42, 42))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void answerButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_answerButtonActionPerformed
        // TODO add your handling code here:
        previousLoc = this.getLocationOnScreen();
        cleanHints();
        if (answerField.getText().equals("") || answerField.getText() == null) {
            MessageDialog.main("You must input something!");
        } else
            dispose();
    }//GEN-LAST:event_answerButtonActionPerformed

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        // TODO add your handling code here:
        answerField.setText("done");
    }//GEN-LAST:event_formWindowClosing

    private void answerFieldKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_answerFieldKeyPressed
        // TODO add your handling code here:
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            previousLoc = this.getLocationOnScreen();
            cleanHints();
            if (answerField.getText().equals("") || answerField.getText() == null) {
                MessageDialog.main("You must input something!");
            } else {
                dispose();
            }
        }

    }//GEN-LAST:event_answerFieldKeyPressed
    public int cleanHints() {
        if (answerField.getText().equals(ttAnswer)) {
            answerField.setText("");
        }
        return 0;
    }

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
            java.util.logging.Logger.getLogger(PromptDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(PromptDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(PromptDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(PromptDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                PromptDialog dialog = new PromptDialog(new javax.swing.JFrame(), true, "  ");
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton answerButton;
    public javax.swing.JTextField answerField;
    public javax.swing.JTextArea areaForText;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables

    public Point previousLoc = new Point(0, 0);

    private final String ttAnswer = "Enter number selection or other value here";
}
