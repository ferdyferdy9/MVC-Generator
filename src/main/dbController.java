package main;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class dbController {
    private static Connection con;
    private static PreparedStatement stmt;
    private static ResultSet hasil;
    private static String sql;
    
    public static ArrayList<String> getTableNames(){
        ArrayList<String> tables = new ArrayList<>();
        
        try{
            con = Koneksi.openConnection();

            sql = "show tables";
            stmt = con.prepareStatement(sql);
            hasil = stmt.executeQuery();

            while(hasil.next()){
                tables.add(hasil.getString(1));
            }

            stmt.close();
            return tables;
        } catch(SQLException e) {
            System.out.println(e);
        }
        return null;
    }
    
    public static ArrayList<String[]> getTableDescribe(String tableName){
        ArrayList<String[]> tables = new ArrayList<>();
        
        try{
            sql = "describe " + tableName;
            stmt = con.prepareStatement(sql);
            hasil = stmt.executeQuery();

            String[] data;
            while(hasil.next()){
                data = new String[5];
                data[0] = hasil.getString(1);
                data[1] = hasil.getString(2);
                data[2] = hasil.getString(3);
                data[3] = hasil.getString(4);
                data[4] = hasil.getString(5);
                tables.add(data);
            }

            stmt.close();
            return tables;
        } catch(SQLException e) {
            System.out.println(e);
        }
        return null;
    }
}
