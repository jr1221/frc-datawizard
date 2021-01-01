/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author jack
 */
public class StartHomeForm extends javax.swing.JFrame {

    /**
     * Creates new form start
     */
    public StartHomeForm() {
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

        jPanel1 = new javax.swing.JPanel();
        GoQC = new javax.swing.JButton();
        choose3 = new HintJObject(ttChoose3);
        choose2 = new HintJObject(ttChoose2);
        choose4 = new HintJObject(ttChoose4);
        choose1 = new HintJObject(ttChoose1);
        yearField = new HintJObject(ttYear);
        eventField = new HintJObject(ttEvent);
        teamField = new HintJObject(ttTeam);
        debugVal = new javax.swing.JCheckBox();
        cliVal = new javax.swing.JCheckBox();
        ftcToggle = new javax.swing.JToggleButton();
        choose0 = new HintJObject(ttChoose0);
        MenuBarTop = new javax.swing.JMenuBar();
        SettingsMenu = new javax.swing.JMenu();
        defYearMenuItem = new javax.swing.JMenuItem();
        apiKeyMenuItem = new javax.swing.JMenuItem();
        clearDef = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("FRC-Datawizard Menu");
        setLocation(new java.awt.Point(500, 700));
        setPreferredSize(new java.awt.Dimension(875, 525));

        jPanel1.setMinimumSize(new java.awt.Dimension(1200, 1500));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        GoQC.setFont(new java.awt.Font("FreeSans", 1, 28)); // NOI18N
        GoQC.setText("Go");
        GoQC.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                GoQCActionPerformed(evt);
            }
        });

        choose3.setColumns(5);
        choose3.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        choose2.setColumns(5);
        choose2.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        choose4.setColumns(5);
        choose4.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        choose1.setColumns(5);
        choose1.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        PreferenceReadWrite pref3 = new PreferenceReadWrite();
        if(pref3.ifYear())
        yearField.setText(String.valueOf(pref3.getYear()));
        yearField.setColumns(7);
        yearField.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        eventField.setColumns(10);
        eventField.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        teamField.setColumns(7);
        teamField.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        debugVal.setFont(new java.awt.Font("FreeSans", 0, 24)); // NOI18N
        debugVal.setText("Debug?");

        cliVal.setFont(new java.awt.Font("FreeSans", 0, 24)); // NOI18N
        cliVal.setText("Data in CLI?");

        ftcToggle.setFont(new java.awt.Font("FreeSans", 0, 24)); // NOI18N
        ftcToggle.setText("Use FTC Data?");

        choose0.setColumns(5);
        choose0.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        SettingsMenu.setText("Options");
        SettingsMenu.setFont(new java.awt.Font("FreeSans", 0, 24)); // NOI18N

        defYearMenuItem.setFont(new java.awt.Font("FreeSans", 0, 24)); // NOI18N
        defYearMenuItem.setText("Set Default Year");
        defYearMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                defYearMenuItemActionPerformed(evt);
            }
        });
        SettingsMenu.add(defYearMenuItem);

        apiKeyMenuItem.setFont(new java.awt.Font("FreeSans", 0, 24)); // NOI18N
        apiKeyMenuItem.setText("Set API Key");
        apiKeyMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                apiKeyMenuItemActionPerformed(evt);
            }
        });
        SettingsMenu.add(apiKeyMenuItem);

        clearDef.setFont(new java.awt.Font("FreeSans", 0, 24)); // NOI18N
        clearDef.setText("Clear Defaults");
        clearDef.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                clearDefActionPerformed(evt);
            }
        });
        SettingsMenu.add(clearDef);

        MenuBarTop.add(SettingsMenu);

        setJMenuBar(MenuBarTop);

        PreferenceReadWrite pref4 = new PreferenceReadWrite();
        if (pref4.getKeyCode()==1) ftcToggle.setSelected(true);
        else if (pref4.getKeyCode()==-1) MessageDialog.main("Please add your API code, you can use the menu option.");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addGroup(layout.createSequentialGroup()
                                    .addGap(25, 25, 25)
                                    .addComponent(GoQC)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(choose2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(layout.createSequentialGroup()
                                    .addGap(341, 341, 341)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(choose1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(choose0, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(341, 341, 341)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(choose4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(choose3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(134, 134, 134)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cliVal)
                            .addComponent(debugVal)
                            .addComponent(ftcToggle)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(508, 508, 508)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(84, 84, 84)
                        .addComponent(yearField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(eventField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(teamField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(41, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(debugVal)
                    .addComponent(choose0, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cliVal)
                    .addComponent(choose1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(27, 27, 27)
                        .addComponent(GoQC)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(ftcToggle)
                        .addGap(81, 81, 81)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(choose2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(choose3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(12, 12, 12)
                        .addComponent(choose4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(17, 17, 17)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(yearField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(eventField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(teamField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(110, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void defYearMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_defYearMenuItemActionPerformed
        // TODO add your handling code here:
        DefaultYearForm.main(null);
    }//GEN-LAST:event_defYearMenuItemActionPerformed

    private void GoQCActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_GoQCActionPerformed
        // TODO add your handling code here:
        String base1;
        if (ftcToggle.isSelected()) {
            base1 = Main.FTC_BASE;
        } else {
            base1 = Main.FRC_BASE;
        }
        int year = cleanHints();
        if (year == -1) {
            return;
        }
        CliSelector cli1 = new CliSelector();
        boolean teamB = true;
        int team = 0;
        if (teamField.getText().equals("")) {
            teamB = false;
        } else {
            team = Integer.parseInt(teamField.getText());
        }
        boolean eventB = true;
        String event = null;
        if (eventField.getText().equals("")) {
            eventB = false;
        } else {
            event = eventField.getText();
        }
        int choose0Int = 0;
        if (choose0.getText().matches("[0-9]+")) {
            choose0Int = Integer.parseInt(choose0.getText());
        }
        else {
                MessageDialog.main("The First Quick Code Must be an Integer!");
                return;
            }
        String urlstr = cli1.urlselect(teamB, eventB, Integer.parseInt(yearField.getText()), event, team, choose0Int, choose1.getText(), choose2.getText(), choose3.getText(), choose4.getText(), !cliVal.isSelected(), base1);
        if (urlstr==null) {
            return;
        }
        Call call1 = new Call();
        int callCode = call1.caller(urlstr, debugVal.isSelected(), base1);
        if (callCode==-1)
            return;
        if (!cliVal.isSelected())
            Results.UI_ReturnData(call1.allKey, call1.allVal, call1.allInfo, call1.index);
        else
            Results.TERM_ReturnData(debugVal.isSelected(), call1.allKey, call1.allVal, call1.index);
    }//GEN-LAST:event_GoQCActionPerformed

    private void apiKeyMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_apiKeyMenuItemActionPerformed
        // TODO add your handling code here:
        ApiEnterForm.main(null);
    }//GEN-LAST:event_apiKeyMenuItemActionPerformed

    private void clearDefActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_clearDefActionPerformed
        // TODO add your handling code here:
        PreferenceReadWrite pref6 = new PreferenceReadWrite();
        pref6.Clear();
        MessageDialog.main("Success!");

    }//GEN-LAST:event_clearDefActionPerformed
    public int cleanHints() {
        int year;
        if (yearField.getText().equals("") || yearField.getText().equals(ttYear)) {
            MessageDialog.main("Year required");
            return -1;
        } else {
            year = Integer.parseInt(yearField.getText());
        }
        if (eventField.getText().equals(ttEvent)) {
            eventField.setText("");
        }
        if (teamField.getText().equals(ttTeam)) {
            teamField.setText("");
        }
        if (choose0.getText().equals(ttChoose0)) {
            choose0.setText("");
        }
        if (choose1.getText().equals(ttChoose1)) {
            choose1.setText("");
        }
        if (choose2.getText().equals(ttChoose2)) {
            choose2.setText("");
        }
        if (choose3.getText().equals(ttChoose3)) {
            choose3.setText("");
        }
        if (choose4.getText().equals(ttChoose4)) {
            choose4.setText("");
        }
        return year;
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
            java.util.logging.Logger.getLogger(StartHomeForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(StartHomeForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(StartHomeForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(StartHomeForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new StartHomeForm().setVisible(true);

            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton GoQC;
    private javax.swing.JMenuBar MenuBarTop;
    private javax.swing.JMenu SettingsMenu;
    private javax.swing.JMenuItem apiKeyMenuItem;
    private javax.swing.JTextField choose0;
    private javax.swing.JTextField choose1;
    private javax.swing.JTextField choose2;
    private javax.swing.JTextField choose3;
    private javax.swing.JTextField choose4;
    private javax.swing.JMenuItem clearDef;
    private javax.swing.JCheckBox cliVal;
    private javax.swing.JCheckBox debugVal;
    private javax.swing.JMenuItem defYearMenuItem;
    private javax.swing.JTextField eventField;
    private javax.swing.JToggleButton ftcToggle;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JTextField teamField;
    private javax.swing.JTextField yearField;
    // End of variables declaration//GEN-END:variables

    private final String ttYear = "year";
    private final String ttTeam = "team #";
    private final String ttEvent = "event code";
    private final String ttChoose0 = "Code #1";
    private final String ttChoose1 = "Code #2";
    private final String ttChoose2 = "Code #3";
    private final String ttChoose3 = "Code #4";
    private final String ttChoose4 = "Code #5";
}