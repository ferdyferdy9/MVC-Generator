package main.ViewPanels;

import java.beans.PropertyChangeSupport;

public abstract class Panel_ViewModel extends javax.swing.JPanel{
    protected String tableName = "";
    abstract ViewModel getViewModel();
    
    public void setSelectedTable(String tableName){
        String oldName = this.tableName;
        
        this.tableName = tableName;
        firePropertyChange("tableName", oldName, tableName);
    }
    
    public String getSelectedTable(){
        return tableName;
    }
}
