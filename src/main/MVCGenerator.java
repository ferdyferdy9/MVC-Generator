package main;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class MVCGenerator {
    private static FileWriter fw;
    private static String text;
    private static final String tab = "    ";
    
    public static void create(ArrayList<Model> models, ArrayList<String> tableNames, String dir){
        try {
            (new File(dir+"\\interfaces")).mkdirs();
            (new File(dir+"\\models")).mkdirs();
            (new File(dir+"\\daos")).mkdirs();
            (new File(dir+"\\controllers")).mkdirs();
            
            fw = new FileWriter(dir+"\\models\\OperasiCRUD.java");
            text = "package models;\n\npublic enum OperasiCRUD {\n    INSERT, UPDATE, DELETE, QUERY\n}";
            fw.write(text);
            fw.close();
            
            for(int i=0;i<tableNames.size();i++){
                if(models.get(i).key == -1)
                    continue;
                genInterface(models.get(i), dir);
                genModel(models.get(i), dir);
                genDAO(models.get(i), tableNames.get(i) , dir);
                genController(models.get(i), dir);
            }
            
            JOptionPane.showMessageDialog(null, "Writing Successfull!");
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "ERROR WRITING FILE!\n"+ex);
        }
    }
    
    public static void createConnector(Boolean isNew, String dir){
        try {
            (new File(dir+"\\daos")).mkdirs();
            fw = new FileWriter(dir+"\\daos\\Koneksi.java");
            
            String version;
            
            if(isNew){
                version = "com.mysql.jdbc.Driver";
            } else {
                version = "com.mysql.cj.jdbc.Driver";
            }
            
            text  = "package daos;\n"+
                    "\n"+
                    "import java.sql.*;\n"+
                    "import javax.swing.JOptionPane;\n"+
                    "\n"+
                    "public class Koneksi {\n"+
                    "    private static Connection c;\n"+
                    "    private static String URL = \"jdbc:mysql://localhost:3306/"+ Koneksi.getNameDB() +"\";\n"+
                    "    private static final String DRIVERNAME = \"" + version + "\";\n"+
                    "    private static final String USERNAME = \"root\";\n"+
                    "    public static Connection openConnection() {\n"+
                    "        if (c == null) {\n"+
                    "            try {\n"+
                    "                Class.forName(DRIVERNAME);\n"+
                    "                c = DriverManager.getConnection(URL, USERNAME, null);\n"+
                    "            } catch (SQLException sqle) {\n"+
                    "                JOptionPane.showMessageDialog(null, \"Cannot connect to database!\\n\" + sqle);\n"+
                    "            } catch (ClassNotFoundException ex) {\n"+
                    "                JOptionPane.showMessageDialog(null, \"No Driver found!\\n\" + ex);\n"+
                    "            }\n"+
                    "        }\n"+
                    "        return c;\n"+
                    "    }\n"+
                    "\n"+
                    "    public static Connection closeConnection() {\n"+
                    "        if (c != null) {\n"+
                    "            try {\n"+
                    "                c.close();\n"+
                    "                c = null;\n"+
                    "            } catch (SQLException sqle) {\n"+
                    "                System.out.println(\"Error: \" + sqle);\n"+
                    "            }\n"+
                    "        }\n"+
                    "\n"+
                    "        return c;\n"+
                    "    }\n"+
                    "\n"+
                    "    public static void main(String[] args) {\n"+
                    "        new Koneksi();\n"+
                    "    }\n"+
                    "}\n";
            
            fw.write(text);
            fw.close();
        } catch (IOException ex) {
            Logger.getLogger(MVCGenerator.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private static void genInterface(Model m, String dir) throws IOException{
        fw = new FileWriter(dir+"\\interfaces\\"+ m.name + "Interface.java");

        text = "package interfaces;\n"+
                "\n"+
                "import java.util.List;\n"+
                "import models."+ m.name +";\n"+
                "\n"+
                "public interface "+ m.name +"Interface {\n"+
                "    public boolean insert(" + m.name + " " + m.name.toLowerCase() + ");\n"+
                "    public boolean update(" + m.name + " " + m.name.toLowerCase() + ");\n"+
                "    public boolean delete(" + m.name + " " + m.name.toLowerCase() + ");\n"+
                "\n"+
                "    public List<" + m.name + "> getAll" + m.name + "();\n"+
                "    public " + m.name + " getBy"+ m.attributes.get(m.key).toUpperCase().charAt(0) + m.attributes.get(m.key).substring(1)
                    + "(" + m.types.get(m.key) + " " + m.attributes.get(m.key) + ");\n"+
                "}\n";

        fw.write(text);
        fw.close(); 
    }
    
    private static void genModel(Model m, String dir) throws IOException{
        fw = new FileWriter(dir+"\\models\\"+ m.name + ".java");
        
        text = "package models;\n"+
            "import java.sql.*;\n"+
            "\n"+
            "public class "+ m.name +" {\n";

        
        // private variables
        for(int i=0;i<m.attributes.size();i++){
            text += tab + "private " + m.types.get(i) + " " + m.attributes.get(i) + ";\n";
        }
        text += tab + "\n";
        
        
        // constructor
        text += tab + "public " + m.name + "(";
        for(int i=0;i<m.attributes.size();i++){
            text += m.types.get(i) + " " + m.attributes.get(i);
            if(i<m.attributes.size()-1){
                 text += ", ";
            }
        }
        text += "){\n";
        for(int i=0;i<m.attributes.size();i++){
            text += tab + tab + "this." + m.attributes.get(i) + " = " + m.attributes.get(i) + ";\n";
        }
        text += tab + "}\n";
        
        
        // getter and setters
        for(int i=0;i<m.attributes.size();i++){
            text += tab + "\n";
            text += tab + "public " + m.types.get(i) + " get" + m.attributes.get(i).toUpperCase().charAt(0) + m.attributes.get(i).substring(1) + "(){\n";
            text += tab + tab + "return " + m.attributes.get(i) + ";\n";
            text += tab + "}\n";

            text += tab + "\n";
            text += tab + "public void set" + m.attributes.get(i).toUpperCase().charAt(0) + m.attributes.get(i).substring(1) + "(" 
                        + m.types.get(i) + " " + m.attributes.get(i) + "){\n";
            text += tab + tab + "this." + m.attributes.get(i) + " = " + m.attributes.get(i) + ";\n";
            text += tab + "}\n";
        }
        
        text += "}\n";

        fw.write(text);
        fw.close(); 
    }
    
    private static void genDAO(Model m, String tableName, String dir) throws IOException{
        fw = new FileWriter(dir+"\\daos\\"+ m.name + "DAO.java");

        // Import
        text = "package daos;\n"+
            "import java.sql.*;\n"+
            "import java.util.List;\n"+
            "import java.util.ArrayList;\n"+
            "import java.util.logging.Level;\n"+
            "import java.util.logging.Logger;\n"+
            "import models." + m.name + ";\n"+
            "import interfaces." + m.name + "Interface;\n"+
            "\n"+
            "public class "+ m.name +"DAO implements "+ m.name +"Interface {\n"+
            "    @Override\n"+
            "    public boolean insert("+ m.name +" "+ m.name.toLowerCase() +") {\n"+
            "        String sql = \"INSERT INTO "+ tableName +" VALUES(";
        
        for(int i=0; i<m.attributes.size();i++){
            text+="?";
            if(i < m.attributes.size()-1){
                text+=", ";
            }
        }
        
        text += ")\";\n";
        
        text+="        try {\n"+
            "            PreparedStatement statement = Koneksi.openConnection().prepareStatement(sql);\n";
        
        for(int i=0;i<m.attributes.size();i++){
            text += "            statement.set"+m.types.get(i).toUpperCase().charAt(0) + m.types.get(i).substring(1) +
                    "("+ (i+1) +", "+ m.name.toLowerCase() + ".get" + m.attributes.get(i).toUpperCase().charAt(0) + m.attributes.get(i).substring(1) +"());\n";
        }
            
        text+="            \n"+
            "            int row = statement.executeUpdate();\n"+
            "            statement.close();\n"+
            "            \n"+
            "            if (row > 0) {\n"+
            "                return true;\n"+
            "            }\n"+
            "        } catch (Exception e) {\n"+
            "            Logger.getLogger("+ m.name +".class.getName()).log(Level.SEVERE, null, e);\n"+
            "        }\n"+
            "        return false;\n"+
            "    }\n"+
            "\n"+
            "    @Override\n"+
            "    public boolean update("+ m.name +" "+ m.name.toLowerCase() +") {\n"+
            "        String sql = \"UPDATE "+ tableName +" SET ";
        
        ArrayList<String[]> desc = dbController.getTableDescribe(tableName);
        ArrayList<String> kolom  = new ArrayList<>();
        for(String[] field : desc){
            kolom.add(field[0]);
        }
        
        for(int i=0; i<kolom.size(); i++){
            if(i == m.key) continue;
            
            text+= tableName + "." + kolom.get(i) + " = ?";
            if(i < kolom.size()-1){
                text+= ", ";
            }
        }
                
        text+= " WHERE " + tableName + "." + kolom.get(m.key) + " = ?";
        
        text+="\";\n";
        
        text+="        try {\n"+
            "            PreparedStatement statement = Koneksi.openConnection().prepareStatement(sql);\n";
        
        for(int i=0;i<m.attributes.size();i++){
            if(i < m.key){
                text += "            statement.set"+m.types.get(i).toUpperCase().charAt(0) + m.types.get(i).substring(1) +
                        "("+ (i+1) +", "+ m.name.toLowerCase() + ".get" + m.attributes.get(i).toUpperCase().charAt(0) + m.attributes.get(i).substring(1) +"());\n";
            }
            if(i > m.key){
                text += "            statement.set"+m.types.get(i).toUpperCase().charAt(0) + m.types.get(i).substring(1) +
                        "("+ (i) +", "+ m.name.toLowerCase() + ".get" + m.attributes.get(i).toUpperCase().charAt(0) + m.attributes.get(i).substring(1) +"());\n";
            }            
        }
        text += "            statement.set"+m.types.get(m.key).toUpperCase().charAt(0) + m.types.get(m.key).substring(1) +
                    "("+ (kolom.size()) +", "+ m.name.toLowerCase() + ".get" + m.attributes.get(m.key).toUpperCase().charAt(0) + m.attributes.get(m.key).substring(1) +"());\n";

        text +=    "\n"+
            "            int row = statement.executeUpdate();\n"+
            "            statement.close();\n"+
            "            \n"+
            "            if (row > 0) {\n"+
            "                return true;\n"+
            "            }\n"+
            "        } catch (Exception e) {\n"+
            "            Logger.getLogger("+ m.name +".class.getName()).log(Level.SEVERE, null, e);\n"+
            "        }\n"+
            "        return false;\n"+
            "    }\n"+
            "\n"+
            "    @Override\n"+
            "    public boolean delete("+ m.name +" "+ m.name.toLowerCase() +") {\n"+
            "        String sql = \"DELETE FROM "+ tableName +" WHERE "+ tableName +"."+ kolom.get(m.key) +" = ?\";\n"+
            "        try {\n"+
            "            PreparedStatement statement = Koneksi.openConnection().prepareStatement(sql);\n";
        
        text += "            statement.set"+m.types.get(m.key).toUpperCase().charAt(0) + m.types.get(m.key).substring(1) +
                    "("+ 1 +", "+ m.name.toLowerCase() + ".get" + m.attributes.get(m.key).toUpperCase().charAt(0) + m.attributes.get(m.key).substring(1) +"());\n";
        
        text+="\n"+
            "            int row = statement.executeUpdate();\n"+
            "            statement.close();\n"+
            "            \n"+
            "            if (row > 0) {\n"+
            "                return true;\n"+
            "            }\n"+
            "        } catch (Exception e) {\n"+
            "            Logger.getLogger("+ m.name +".class.getName()).log(Level.SEVERE, null, e);\n"+
            "        }\n"+
            "        return false;\n"+
            "    }\n"+
            "\n"+
            "    @Override\n"+
            "    public List<"+ m.name +"> getAll"+ m.name +"() {\n"+
            "        List<"+ m.name +"> "+ m.name.toLowerCase() +"List = new ArrayList<"+ m.name +">();\n"+
            "        String sql = \"SELECT * FROM "+tableName+"\";\n"+
            "        try {\n"+
            "            PreparedStatement statement = Koneksi.openConnection().prepareStatement(sql);\n"+
            "            ResultSet rs = statement.executeQuery();\n"+
            "\n"+
            "            while (rs.next()) {\n"+
            "                "+ m.name +" "+ m.name.toLowerCase() +" = new "+ m.name +"(";
            
        for(int i=0;i<m.types.size();i++){
            text+= "rs.get" + m.types.get(i).toUpperCase().charAt(0) + m.types.get(i).substring(1) +"("+ (i+1) + ")";
            if(i<m.types.size()-1){
                text+= ", ";
            }
        }
                    
        text+=");\n";
        
        text+="                "+ m.name.toLowerCase() +"List.add("+ m.name.toLowerCase() +");\n"+
            "            }\n"+
            "            statement.close();\n"+
            "        } catch (Exception e) {\n"+
            "            Logger.getLogger("+m.name+".class.getName()).log(Level.SEVERE, null, e);\n"+
            "        }\n"+
            "        return "+ m.name.toLowerCase() +"List;\n"+
            "    }\n"+
            "\n"+
            "    @Override\n"+
            "    public "+ m.name +" getBy"+ m.attributes.get(m.key).toUpperCase().charAt(0) + m.attributes.get(m.key).substring(1)
                    +"("+ m.types.get(m.key) + " " + m.attributes.get(m.key) + ") {\n"+
            "        "+ m.name +" "+ m.name.toLowerCase() +" = null;\n"+
            "        String sql = \"SELECT * FROM "+tableName+" WHERE "+tableName+"."+kolom.get(m.key)+" = ?\";\n"+
            "        try {\n"+
            "            PreparedStatement statement = Koneksi.openConnection().prepareStatement(sql);\n";
        
        text += "            statement.set"+m.types.get(m.key).toUpperCase().charAt(0) + m.types.get(m.key).substring(1) +
                    "("+ 1 +", " + m.attributes.get(m.key) + ");\n";
        
        
        text+="            ResultSet rs = statement.executeQuery();\n"+
            "\n"+
            "            if (rs.next()) {\n"+
            "                "+ m.name.toLowerCase() +" = new "+ m.name +"(";
            
        for(int i=0;i<m.types.size();i++){
            text+= "rs.get" + m.types.get(i).toUpperCase().charAt(0) + m.types.get(i).substring(1) +"("+ (i+1) + ")";
            if(i<m.types.size()-1){
                text+= ", ";
            }
        }
        
        text+=");\n";
            
        text+="            }\n"+
            "        } catch (Exception e) {\n"+
            "            Logger.getLogger("+ m.name +".class.getName()).log(Level.SEVERE, null, e);\n"+
            "        }\n"+
            "        return "+ m.name.toLowerCase() +";\n"+
            "    }\n"+
            "}\n"+
            "\n";
            
        fw.write(text);
        fw.close(); 
    }
    
    private static void genController(Model m, String dir) throws IOException{
        fw = new FileWriter(dir+"\\controllers\\"+ m.name + "Controller.java");
        
        text = "package controllers;\n"+
            "\n"+
            "import java.util.List;\n"+
            "import java.util.Observable;\n"+
            "import daos."+ m.name +"DAO;\n"+
            "import models."+ m.name +";\n"+
            "import interfaces."+ m.name +"Interface;\n"+
            "import models.OperasiCRUD;\n"+
            "\n"+
            "public class "+ m.name +"Controller extends Observable{\n"+
            "    private "+ m.name +"Interface dao = new "+ m.name +"DAO();\n"+
            "    private OperasiCRUD crud;\n"+
            "    \n"+
            "    public void setDAO("+ m.name +"Interface "+ m.name.toLowerCase() +"){\n"+
            "        dao = "+ m.name.toLowerCase() +";\n"+
            "    }\n"+
            "    \n"+
            "    public void setDml("+ m.name +" "+ m.name.toLowerCase() +", OperasiCRUD c){\n"+
            "        boolean hasil = false;\n"+
            "        this.crud = c;\n"+
            "        switch(c){\n"+
            "            case INSERT: hasil = dao.insert("+ m.name.toLowerCase() +"); \n"+
            "                break;\n"+
            "            case UPDATE: hasil = dao.update("+ m.name.toLowerCase() +"); \n"+
            "                break;\n"+
            "            case DELETE: hasil = dao.delete("+ m.name.toLowerCase() +"); \n"+
            "                break;\n"+
            "        }\n"+
            "        setChanged();\n"+
            "        \n"+
            "        if(hasil){\n"+
            "            notifyObservers("+ m.name.toLowerCase() +");\n"+
            "        }else{\n"+
            "            notifyObservers();\n"+
            "        }\n"+
            "    }\n"+
            "    \n"+
            "    public OperasiCRUD getCrudState(){\n"+
            "        return crud;\n"+
            "    }    \n"+
            "    \n"+
            "    public List<"+ m.name +"> getAll"+ m.name +"(){\n"+
            "        return dao.getAll"+ m.name +"();\n"+
            "    }\n"+
            "    \n"+
            "    public "+ m.name +" getBy"+ m.attributes.get(m.key).toUpperCase().charAt(0) + m.attributes.get(m.key).substring(1)
                    +"(" + m.types.get(m.key) + " " + m.attributes.get(m.key) + "){\n"+
            "        return dao.getBy"+ m.attributes.get(m.key).toUpperCase().charAt(0) + m.attributes.get(m.key).substring(1) + "(" + m.attributes.get(m.key) + ");\n"+
            "    }\n"+
            "}\n";
        
        fw.write(text);
        fw.close(); 
    }
}
