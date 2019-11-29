package main;

import java.util.List;
import main.ViewPanels.*;

public class ViewGenForm extends javax.swing.JFrame {
    
    public ViewGenForm() {
        initComponents();
        
        List<String> name_list = dbController.getTableNames();
        list_table.setListData(name_list.toArray(new String[name_list.size()]));
        
        setEnabledAll(tp_main, false);
    }
    
    private void setEnabledAll(java.awt.Container container, Boolean enabled){
        container.setEnabled(enabled);
        
        for(java.awt.Component c : container.getComponents()){
            Class<?> s = c.getClass().getSuperclass();
            while(s != null){
                if(s.equals(java.awt.Component.class)){
                    setEnabledAll((java.awt.Container) c, enabled);
                    break;
                }
                s = s.getSuperclass();
            }
            
            c.setEnabled(enabled);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        myFileChooser = new javax.swing.JFileChooser();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        list_generate = new javax.swing.JList<>();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        list_table = new javax.swing.JList<>();
        btn_up = new javax.swing.JButton();
        btn_down = new javax.swing.JButton();
        tp_main = new javax.swing.JTabbedPane();
        btn_generate = new javax.swing.JButton();
        btn_chooseFiles = new javax.swing.JButton();
        tf_outputDirectory = new javax.swing.JTextField();
        label_outputDirectory = new javax.swing.JLabel();

        myFileChooser.setCurrentDirectory(new java.io.File("C:\\"));
            myFileChooser.setFileSelectionMode(javax.swing.JFileChooser.DIRECTORIES_ONLY);

            setLocationByPlatform(true);
            setMaximumSize(new java.awt.Dimension(630, 450));
            setMinimumSize(new java.awt.Dimension(614, 436));
            setPreferredSize(new java.awt.Dimension(614, 436));
            setResizable(false);
            getContentPane().setLayout(null);

            jLabel2.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
            jLabel2.setText("Select Table :");
            jLabel2.setToolTipText("");
            getContentPane().add(jLabel2);
            jLabel2.setBounds(10, 10, 130, 22);

            list_generate.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
            list_generate.addMouseListener(new java.awt.event.MouseAdapter() {
                public void mousePressed(java.awt.event.MouseEvent evt) {
                    list_generateMousePressed(evt);
                }
            });
            jScrollPane1.setViewportView(list_generate);

            getContentPane().add(jScrollPane1);
            jScrollPane1.setBounds(10, 250, 130, 140);

            jLabel1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
            jLabel1.setText("To Generate :");
            jLabel1.setToolTipText("");
            getContentPane().add(jLabel1);
            jLabel1.setBounds(10, 220, 130, 22);

            list_table.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
            list_table.addMouseListener(new java.awt.event.MouseAdapter() {
                public void mousePressed(java.awt.event.MouseEvent evt) {
                    list_tableMousePressed(evt);
                }
            });
            jScrollPane2.setViewportView(list_table);

            getContentPane().add(jScrollPane2);
            jScrollPane2.setBounds(10, 40, 130, 140);

            btn_up.setText("/\\");
                btn_up.setMargin(new java.awt.Insets(2, -2, 2, -2));
                btn_up.addActionListener(new java.awt.event.ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent evt) {
                        btn_upActionPerformed(evt);
                    }
                });
                getContentPane().add(btn_up);
                btn_up.setBounds(30, 190, 40, 21);

                btn_down.setText("\\/");
                btn_down.setMargin(new java.awt.Insets(2, -2, 2, -2));
                btn_down.addActionListener(new java.awt.event.ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent evt) {
                        btn_downActionPerformed(evt);
                    }
                });
                getContentPane().add(btn_down);
                btn_down.setBounds(80, 190, 40, 21);

                tp_main.setTabLayoutPolicy(javax.swing.JTabbedPane.SCROLL_TAB_LAYOUT);
                tp_main.setEnabled(false);
                tp_main.setPreferredSize(new java.awt.Dimension(440, 330));
                getContentPane().add(tp_main);
                tp_main.setBounds(150, 10, 440, 330);
                tp_main.addTab("Add", panel_add);
                tp_main.addTab("List", panel_list);
                tp_main.addTab("Update", panel_update);
                tp_main.addTab("Delete", panel_delete);

                btn_generate.setText("Generate");
                getContentPane().add(btn_generate);
                btn_generate.setBounds(480, 350, 110, 30);

                btn_chooseFiles.setText("...");
                btn_chooseFiles.addActionListener(new java.awt.event.ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent evt) {
                        btn_chooseFilesActionPerformed(evt);
                    }
                });
                getContentPane().add(btn_chooseFiles);
                btn_chooseFiles.setBounds(440, 350, 30, 30);
                getContentPane().add(tf_outputDirectory);
                tf_outputDirectory.setBounds(250, 350, 220, 30);

                label_outputDirectory.setText("Output Directory :");
                getContentPane().add(label_outputDirectory);
                label_outputDirectory.setBounds(150, 350, 100, 30);

                pack();
            }// </editor-fold>//GEN-END:initComponents

    private void list_tableMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_list_tableMousePressed
        String tableName = "";
        
        if(list_table.getSelectedIndex() == -1){
            return;
        }
        
        if(!tp_main.isEnabled())
            setEnabledAll(tp_main, true);
        
        list_generate.clearSelection();
        
        tableName = list_table.getSelectedValue();
        
        panel_add.setSelectedTable(tableName);
        panel_list.setSelectedTable(tableName);
        panel_update.setSelectedTable(tableName);
        panel_delete.setSelectedTable(tableName);
    }//GEN-LAST:event_list_tableMousePressed

    private void btn_downActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_downActionPerformed
        if(list_table.getSelectedIndex() == -1){
            return;
        }
        
        List<String> table_list = new java.util.ArrayList<>();
        List<String> generate_list = new java.util.ArrayList<>();
        
        int table_size = list_table.getModel().getSize();
        for(int i=0; i<table_size; i++){
            table_list.add(list_table.getModel().getElementAt(i));
        }
        
        int generate_size = list_generate.getModel().getSize();
        for(int i=0; i<generate_size; i++){
            generate_list.add(list_generate.getModel().getElementAt(i));
        }
        
        table_list.remove(list_table.getSelectedValue());
        generate_list.add(list_table.getSelectedValue());
        
        java.util.Collections.sort(table_list);
        java.util.Collections.sort(generate_list);
        
        list_table.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = table_list.toArray(new String[table_list.size()]);
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        
        list_generate.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = generate_list.toArray(new String[generate_list.size()]);
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        
        setEnabledAll(tp_main, false);
        panel_add.setSelectedTable("");
        panel_list.setSelectedTable("");
        panel_update.setSelectedTable("");
        panel_delete.setSelectedTable("");
    }//GEN-LAST:event_btn_downActionPerformed

    private void btn_upActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_upActionPerformed
        if(list_generate.getSelectedIndex() == -1){
            return;
        }
        
        List<String> table_list = new java.util.ArrayList<>();
        List<String> generate_list = new java.util.ArrayList<>();
        
        int table_size = list_table.getModel().getSize();
        for(int i=0; i<table_size; i++){
            table_list.add(list_table.getModel().getElementAt(i));
        }
        
        int generate_size = list_generate.getModel().getSize();
        for(int i=0; i<generate_size; i++){
            generate_list.add(list_generate.getModel().getElementAt(i));
        }
        
        table_list.add(list_generate.getSelectedValue());
        generate_list.remove(list_generate.getSelectedValue());
        
        java.util.Collections.sort(table_list);
        java.util.Collections.sort(generate_list);
        
        list_table.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = table_list.toArray(new String[table_list.size()]);
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        
        list_generate.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = generate_list.toArray(new String[generate_list.size()]);
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        
        setEnabledAll(tp_main, false);
        panel_add.setSelectedTable("");
        panel_list.setSelectedTable("");
        panel_update.setSelectedTable("");
        panel_delete.setSelectedTable("");
    }//GEN-LAST:event_btn_upActionPerformed

    private void list_generateMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_list_generateMousePressed
        String tableName = "";
        
        if(list_generate.getSelectedIndex() == -1){
            return;
        }
        
        if(!tp_main.isEnabled())
            setEnabledAll(tp_main, true);
        
        list_table.clearSelection();
        
        tableName = list_generate.getSelectedValue();
        
        panel_add.setSelectedTable(tableName);
        panel_list.setSelectedTable(tableName);
        panel_update.setSelectedTable(tableName);
        panel_delete.setSelectedTable(tableName);
    }//GEN-LAST:event_list_generateMousePressed

    private void btn_chooseFilesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_chooseFilesActionPerformed
        int r = myFileChooser.showOpenDialog(null);

        if (r == myFileChooser.APPROVE_OPTION) {
            // set the label to the path of the selected file
            tf_outputDirectory.setText(myFileChooser.getSelectedFile().getAbsolutePath());
        }
    }//GEN-LAST:event_btn_chooseFilesActionPerformed
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_chooseFiles;
    private javax.swing.JButton btn_down;
    private javax.swing.JButton btn_generate;
    private javax.swing.JButton btn_up;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel label_outputDirectory;
    private javax.swing.JList<String> list_generate;
    private javax.swing.JList<String> list_table;
    private javax.swing.JFileChooser myFileChooser;
    private javax.swing.JTextField tf_outputDirectory;
    private javax.swing.JTabbedPane tp_main;
    // End of variables declaration//GEN-END:variables
    private Panel_ViewModel panel_add = new Panel_Add();
    private Panel_ViewModel panel_list = new Panel_List();
    private Panel_ViewModel panel_update = new Panel_Update();
    private Panel_ViewModel panel_delete = new Panel_Delete();
}
