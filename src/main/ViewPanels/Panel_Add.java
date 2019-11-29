package main.ViewPanels;

import java.util.List;
import main.dbController;

public class Panel_Add extends Panel_ViewModel {
    private List<String[]> tableAttribute;
    
    public Panel_Add() {
        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        list_attribute = new javax.swing.JList<>();
        lbl_attribute = new javax.swing.JLabel();

        setBackground(new java.awt.Color(230, 230, 230));
        addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                formPropertyChange(evt);
            }
        });
        setLayout(null);

        list_attribute.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jScrollPane1.setViewportView(list_attribute);

        add(jScrollPane1);
        jScrollPane1.setBounds(10, 40, 130, 130);

        lbl_attribute.setText("Attributes :");
        add(lbl_attribute);
        lbl_attribute.setBounds(10, 10, 130, 20);
    }// </editor-fold>//GEN-END:initComponents

    private void formPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_formPropertyChange
        if(!evt.getPropertyName().equals("tableName")){
            return;
        }
        
        if(tableName.isEmpty()){
            list_attribute.setModel(new javax.swing.DefaultListModel<>());
            return;
        }

        tableAttribute = dbController.getTableDescribe(tableName);

        int size = tableAttribute.size();
        String[] name_list = new String[size];
        for(int i=0; i<size; i++){
            name_list[i] = tableAttribute.get(i)[0];
        }

        list_attribute.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = name_list;
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
    }//GEN-LAST:event_formPropertyChange

    @Override
    public ViewModel getViewModel() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lbl_attribute;
    private javax.swing.JList<String> list_attribute;
    // End of variables declaration//GEN-END:variables
}
