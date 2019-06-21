package main;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JOptionPane;

public final class MVCGenerator extends javax.swing.JFrame {
    private final Map rbName = new HashMap();
    private final Map rbAttribute = new HashMap();;
    private final Map rbConnector = new HashMap();;
    private final Map rbPrimaryKey = new HashMap();;
    
    public enum Mode{
        MANUAL, AUTO, HYBRID
    }
    
    public MVCGenerator() {
        initComponents();
        
        myPanel.setVisible(false);
        label_SQLConnected.setVisible(false);
        
        rbName.put      (rb_nameManual.getModel()      , Mode.MANUAL);
        rbName.put      (rb_nameAuto.getModel()        , Mode.AUTO  );
        rbAttribute.put (rb_attributeManual.getModel() , Mode.MANUAL);
        rbAttribute.put (rb_attributeAuto.getModel()   , Mode.AUTO  );
        rbConnector.put (rb_jconnectorOld.getModel()   , Mode.MANUAL);
        rbConnector.put (rb_jconnectorNew.getModel()   , Mode.AUTO  );
        rbPrimaryKey.put(rb_PrimaryKeyManual.getModel(), Mode.MANUAL);
        rbPrimaryKey.put(rb_PrimaryKeyAuto.getModel()  , Mode.AUTO  );
        rbPrimaryKey.put(rb_PrimaryKeyHybrid.getModel(), Mode.HYBRID);
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        bg_modelNameMode = new javax.swing.ButtonGroup();
        bg_modelAttributeMode = new javax.swing.ButtonGroup();
        bg_keyDetectionMode = new javax.swing.ButtonGroup();
        bg_jconnectorMode = new javax.swing.ButtonGroup();
        myFileChooser = new javax.swing.JFileChooser();
        label_SQLText = new javax.swing.JLabel();
        label_SQLNotConnected = new javax.swing.JLabel();
        label_SQLConnected = new javax.swing.JLabel();
        label_databaseName = new javax.swing.JLabel();
        tf_databaseName = new javax.swing.JTextField();
        btn_connect = new javax.swing.JButton();
        myPanel = new javax.swing.JPanel();
        label_ModelGenTitle = new javax.swing.JLabel();
        label_ModelName = new javax.swing.JLabel();
        rb_nameAuto = new javax.swing.JRadioButton();
        rb_nameManual = new javax.swing.JRadioButton();
        label_ModelAttribute = new javax.swing.JLabel();
        rb_attributeAuto = new javax.swing.JRadioButton();
        rb_attributeManual = new javax.swing.JRadioButton();
        label_tableDetTitle = new javax.swing.JLabel();
        label_jconnectorTitle = new javax.swing.JLabel();
        rb_jconnectorNew = new javax.swing.JRadioButton();
        rb_jconnectorOld = new javax.swing.JRadioButton();
        label_PrimaryKeyTitle = new javax.swing.JLabel();
        rb_PrimaryKeyAuto = new javax.swing.JRadioButton();
        rb_PrimaryKeyHybrid = new javax.swing.JRadioButton();
        rb_PrimaryKeyManual = new javax.swing.JRadioButton();
        ta_notes = new javax.swing.JTextArea();
        btn_generate = new javax.swing.JButton();
        label_outputDirectory = new javax.swing.JLabel();
        btn_chooseFiles = new javax.swing.JButton();
        tf_outputDirectory = new javax.swing.JTextField();

        myFileChooser.setCurrentDirectory(new java.io.File("C:\\"));
            myFileChooser.setFileSelectionMode(javax.swing.JFileChooser.DIRECTORIES_ONLY);

            setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
            setTitle("MVC Generator - by Ferdy Nicolas");
            setMinimumSize(new java.awt.Dimension(390, 470));
            setPreferredSize(new java.awt.Dimension(390, 470));
            setResizable(false);
            getContentPane().setLayout(null);

            label_SQLText.setText("SQL Connection :");
            getContentPane().add(label_SQLText);
            label_SQLText.setBounds(10, 10, 100, 20);

            label_SQLNotConnected.setForeground(new java.awt.Color(255, 51, 51));
            label_SQLNotConnected.setText("Not Connected");
            getContentPane().add(label_SQLNotConnected);
            label_SQLNotConnected.setBounds(110, 10, 90, 20);

            label_SQLConnected.setForeground(new java.awt.Color(0, 153, 0));
            label_SQLConnected.setText("Connected");
            getContentPane().add(label_SQLConnected);
            label_SQLConnected.setBounds(110, 10, 90, 20);

            label_databaseName.setText("Database Name :");
            getContentPane().add(label_databaseName);
            label_databaseName.setBounds(10, 30, 100, 30);

            tf_databaseName.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    tf_databaseNameActionPerformed(evt);
                }
            });
            getContentPane().add(tf_databaseName);
            tf_databaseName.setBounds(110, 30, 160, 30);

            btn_connect.setText("Connect");
            btn_connect.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    btn_connectActionPerformed(evt);
                }
            });
            getContentPane().add(btn_connect);
            btn_connect.setBounds(280, 30, 100, 30);

            myPanel.setBorder(javax.swing.BorderFactory.createEtchedBorder());
            myPanel.setLayout(null);

            label_ModelGenTitle.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
            label_ModelGenTitle.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            label_ModelGenTitle.setText("Model Configuration");
            myPanel.add(label_ModelGenTitle);
            label_ModelGenTitle.setBounds(10, 10, 350, 20);

            label_ModelName.setText("Model Name Generation Mode :");
            myPanel.add(label_ModelName);
            label_ModelName.setBounds(10, 30, 200, 20);

            bg_modelNameMode.add(rb_nameAuto);
            rb_nameAuto.setText("Automatic");
            myPanel.add(rb_nameAuto);
            rb_nameAuto.setBounds(210, 30, 80, 23);

            bg_modelNameMode.add(rb_nameManual);
            rb_nameManual.setSelected(true);
            rb_nameManual.setText("Manual");
            myPanel.add(rb_nameManual);
            rb_nameManual.setBounds(290, 30, 70, 23);

            label_ModelAttribute.setText("Model Attribute Generation Mode :");
            myPanel.add(label_ModelAttribute);
            label_ModelAttribute.setBounds(10, 50, 200, 20);

            bg_modelAttributeMode.add(rb_attributeAuto);
            rb_attributeAuto.setSelected(true);
            rb_attributeAuto.setText("Automatic");
            myPanel.add(rb_attributeAuto);
            rb_attributeAuto.setBounds(210, 50, 80, 23);

            bg_modelAttributeMode.add(rb_attributeManual);
            rb_attributeManual.setText("Manual");
            myPanel.add(rb_attributeManual);
            rb_attributeManual.setBounds(290, 50, 70, 23);

            label_tableDetTitle.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
            label_tableDetTitle.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            label_tableDetTitle.setText("Controller and DAO Configuration");
            myPanel.add(label_tableDetTitle);
            label_tableDetTitle.setBounds(10, 80, 350, 20);

            label_jconnectorTitle.setText("JConnector Version :");
            myPanel.add(label_jconnectorTitle);
            label_jconnectorTitle.setBounds(10, 100, 140, 20);

            bg_jconnectorMode.add(rb_jconnectorNew);
            rb_jconnectorNew.setSelected(true);
            rb_jconnectorNew.setText("New");
            myPanel.add(rb_jconnectorNew);
            rb_jconnectorNew.setBounds(140, 100, 80, 23);

            bg_jconnectorMode.add(rb_jconnectorOld);
            rb_jconnectorOld.setText("Old");
            myPanel.add(rb_jconnectorOld);
            rb_jconnectorOld.setBounds(220, 100, 70, 23);

            label_PrimaryKeyTitle.setText("Primary Key Detection :");
            myPanel.add(label_PrimaryKeyTitle);
            label_PrimaryKeyTitle.setBounds(10, 120, 140, 20);

            bg_keyDetectionMode.add(rb_PrimaryKeyAuto);
            rb_PrimaryKeyAuto.setText("Automatic");
            myPanel.add(rb_PrimaryKeyAuto);
            rb_PrimaryKeyAuto.setBounds(140, 120, 80, 23);

            bg_keyDetectionMode.add(rb_PrimaryKeyHybrid);
            rb_PrimaryKeyHybrid.setSelected(true);
            rb_PrimaryKeyHybrid.setText("Hybrid");
            myPanel.add(rb_PrimaryKeyHybrid);
            rb_PrimaryKeyHybrid.setBounds(220, 120, 70, 23);

            bg_keyDetectionMode.add(rb_PrimaryKeyManual);
            rb_PrimaryKeyManual.setText("Manual");
            myPanel.add(rb_PrimaryKeyManual);
            rb_PrimaryKeyManual.setBounds(290, 120, 70, 23);

            ta_notes.setEditable(false);
            ta_notes.setBackground(new java.awt.Color(240, 240, 240));
            ta_notes.setColumns(20);
            ta_notes.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
            ta_notes.setRows(5);
            ta_notes.setText("Auto : Will automatically set primary key based if exists;\n         If not, then it will choose the 1st slot as primary\n\nHybrid : Will automatically set primary key based if exists;\n            If not, then it will prompt the user to choose\n\nManual : Let the user choose which coloumn is primary key");
            myPanel.add(ta_notes);
            ta_notes.setBounds(10, 150, 350, 120);

            getContentPane().add(myPanel);
            myPanel.setBounds(10, 70, 370, 280);

            btn_generate.setText("Generate");
            btn_generate.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    btn_generateActionPerformed(evt);
                }
            });
            getContentPane().add(btn_generate);
            btn_generate.setBounds(140, 400, 110, 30);

            label_outputDirectory.setText("Output Directory :");
            getContentPane().add(label_outputDirectory);
            label_outputDirectory.setBounds(10, 360, 110, 30);

            btn_chooseFiles.setText("...");
            btn_chooseFiles.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    btn_chooseFilesActionPerformed(evt);
                }
            });
            getContentPane().add(btn_chooseFiles);
            btn_chooseFiles.setBounds(350, 360, 30, 30);
            getContentPane().add(tf_outputDirectory);
            tf_outputDirectory.setBounds(120, 360, 260, 30);

            pack();
            setLocationRelativeTo(null);
        }// </editor-fold>//GEN-END:initComponents

    private void btn_chooseFilesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_chooseFilesActionPerformed
        int r = myFileChooser.showOpenDialog(null);
        
        if (r == myFileChooser.APPROVE_OPTION) { 
            // set the label to the path of the selected file 
            tf_outputDirectory.setText(myFileChooser.getSelectedFile().getAbsolutePath()); 
        }
    }//GEN-LAST:event_btn_chooseFilesActionPerformed

    private void btn_connectActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_connectActionPerformed
        Koneksi.setNamaDB(tf_databaseName.getText());
        
        if(Koneksi.openConnection() != null){
            JOptionPane.showMessageDialog(null, "Connection Success!\n");
            label_SQLConnected.setVisible(true);
            label_SQLNotConnected.setVisible(false);
            myPanel.setVisible(true);
        } else {
            label_SQLConnected.setVisible(false);
            label_SQLNotConnected.setVisible(true);
            myPanel.setVisible(false);
        }
        
        Koneksi.closeConnection();
    }//GEN-LAST:event_btn_connectActionPerformed

    private void btn_generateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_generateActionPerformed
        if(!label_SQLConnected.isVisible()){
            JOptionPane.showMessageDialog(null, "Database not connected!");
            return;
        }
        
        if(tf_outputDirectory.getText().trim().isEmpty()){
            JOptionPane.showMessageDialog(null, "Output Directory is Empty!");
            return;
        }
        
        Mode namMode = (Mode) rbName.get      (bg_modelNameMode.getSelection()     );
        Mode attMode = (Mode) rbAttribute.get (bg_modelAttributeMode.getSelection());
        Mode conMode = (Mode) rbConnector.get (bg_jconnectorMode.getSelection()    );
        Mode keyMode = (Mode) rbPrimaryKey.get(bg_keyDetectionMode.getSelection()  );
        
        ArrayList<String> tables = dbController.getTableNames();
        
        ArrayList<Model> models = new ArrayList<>();
        String tampil;
        
        for(String t : tables){
            ArrayList<String[]> desc = dbController.getTableDescribe(t);
            
            // Get model Name
            String nama;
            if(namMode == Mode.AUTO){
                nama = t;
            } else {
                tampil = "Table " + t + "\n\nMasukan Nama Model :";
                nama = JOptionPane.showInputDialog(null, tampil);
                
                if(nama == null || nama.trim().isEmpty()){
                    nama = t;
                }
            }
            
            // Get model Attributes
            ArrayList<String> attributes = new ArrayList<>();
            ArrayList<String> types = new ArrayList<>();
            if(attMode == Mode.AUTO){
                for(String[] field : desc){
                    attributes.add(field[0]);
                }
            } else {
                for(String[] field : desc){
                    tampil = "Table " + t + "\nNama Atribut : "+ field[0] +"\n\nMasukan Nama Atrribut :";
                    
                    String n = JOptionPane.showInputDialog(null, tampil);
                    
                    if(n == null || n.trim().isEmpty()){
                        attributes.add(field[0]);
                    } else {
                        attributes.add(n);
                    }
                }
            }
            
            // get mode attribute types
            for(String[] field : desc){
                String s = "";
                for(char c : field[1].toCharArray()){
                    if(c == '('){
                        break;
                    }
                    s += c;
                }
                types.add(s);
            }
            
            // Find primary key
            int key;
            for(key=desc.size()-1; key>=0; key--){
                if(desc.get(key)[3] != null){
                    if(desc.get(key)[3].equals("PRI")){
                        break;
                    }
                }
            }
            
            // If primary key not found
            if(key < 0){
                if(keyMode == Mode.AUTO){
                    key = 0;
                } else {
                    tampil = "Table " + t + "\n";
                    
                    int i=1;
                    for(String[] field : desc){
                        tampil += i + ". " + field[0] + "\n";
                        i++;
                    }
                    tampil += "Pilih Primary Key :";
                    
                    int pilihan;
                    try{
                        String input = JOptionPane.showInputDialog(null, tampil);
                        if(input == null){
                            throw(new NumberFormatException());
                        }
                        pilihan = Integer.parseInt(input);
                    } catch(NumberFormatException e){
                        JOptionPane.showMessageDialog(null, "INPUT ERROR! SELECTING 1st choice as primary...");
                        pilihan = 1;
                    }
                    
                    key = pilihan-1;
                }
            }
            
            models.add(new Model(nama, attributes, types, key));
        }
        
        fileGenerator.createConnector(conMode == Mode.AUTO, tf_outputDirectory.getText());
        fileGenerator.create(models, tables, tf_outputDirectory.getText());
    }//GEN-LAST:event_btn_generateActionPerformed

    private void tf_databaseNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tf_databaseNameActionPerformed
        Koneksi.setNamaDB(tf_databaseName.getText());
        
        if(Koneksi.openConnection() != null){
            JOptionPane.showMessageDialog(null, "Connection Success!\n");
            label_SQLConnected.setVisible(true);
            label_SQLNotConnected.setVisible(false);
            myPanel.setVisible(true);
        } else {
            label_SQLConnected.setVisible(false);
            label_SQLNotConnected.setVisible(true);
            myPanel.setVisible(false);
        }
        
        Koneksi.closeConnection();
    }//GEN-LAST:event_tf_databaseNameActionPerformed
    
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
            java.util.logging.Logger.getLogger(MVCGenerator.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MVCGenerator.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MVCGenerator.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MVCGenerator.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            new MVCGenerator().setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup bg_jconnectorMode;
    private javax.swing.ButtonGroup bg_keyDetectionMode;
    private javax.swing.ButtonGroup bg_modelAttributeMode;
    private javax.swing.ButtonGroup bg_modelNameMode;
    private javax.swing.JButton btn_chooseFiles;
    private javax.swing.JButton btn_connect;
    private javax.swing.JButton btn_generate;
    private javax.swing.JLabel label_ModelAttribute;
    private javax.swing.JLabel label_ModelGenTitle;
    private javax.swing.JLabel label_ModelName;
    private javax.swing.JLabel label_PrimaryKeyTitle;
    private javax.swing.JLabel label_SQLConnected;
    private javax.swing.JLabel label_SQLNotConnected;
    private javax.swing.JLabel label_SQLText;
    private javax.swing.JLabel label_databaseName;
    private javax.swing.JLabel label_jconnectorTitle;
    private javax.swing.JLabel label_outputDirectory;
    private javax.swing.JLabel label_tableDetTitle;
    private javax.swing.JFileChooser myFileChooser;
    private javax.swing.JPanel myPanel;
    private javax.swing.JRadioButton rb_PrimaryKeyAuto;
    private javax.swing.JRadioButton rb_PrimaryKeyHybrid;
    private javax.swing.JRadioButton rb_PrimaryKeyManual;
    private javax.swing.JRadioButton rb_attributeAuto;
    private javax.swing.JRadioButton rb_attributeManual;
    private javax.swing.JRadioButton rb_jconnectorNew;
    private javax.swing.JRadioButton rb_jconnectorOld;
    private javax.swing.JRadioButton rb_nameAuto;
    private javax.swing.JRadioButton rb_nameManual;
    private javax.swing.JTextArea ta_notes;
    private javax.swing.JTextField tf_databaseName;
    private javax.swing.JTextField tf_outputDirectory;
    // End of variables declaration//GEN-END:variables
}
