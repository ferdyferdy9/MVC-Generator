package main;

import java.sql.*;
import javax.swing.JOptionPane;

public class Koneksi {
    private static Connection c;
    private static String namaDB = "";
    private static String URL;
    private static final String DRIVERNAME = "com.mysql.cj.jdbc.Driver";
    private static final String USERNAME = "root";
    
    public static Connection openConnection() {
        if(namaDB.trim().isEmpty()){
            JOptionPane.showMessageDialog(null, "ERROR: No Database Entered!");
        } else {
            if (c == null) {
                URL = "jdbc:mysql://localhost:3306/" + namaDB;
                
                try {
                    Class.forName("com.mysql.cj.jdbc.Driver");
                    c = DriverManager.getConnection(URL, USERNAME, null);
                } catch (SQLException sqle) {
                    JOptionPane.showMessageDialog(null, "Cannot connect to database!\n" + sqle);
                } catch (ClassNotFoundException ex) {
                    JOptionPane.showMessageDialog(null, "No Driver found!\n" + ex);
                }
            }
        }

        return c;
    }

    public static Connection closeConnection() {
        if (c != null) {
            try {
                c.close();
                c = null;
            } catch (SQLException sqle) {
                System.out.println("Error: " + sqle);
            }
        }

        return c;
    }
    
    public static void setNamaDB(String namaDB){
        Koneksi.namaDB = namaDB;
    }
    
    public static String getNameDB(){
        return Koneksi.namaDB;
    }

    public static void main(String[] args) {
        new Koneksi();
    }
}
