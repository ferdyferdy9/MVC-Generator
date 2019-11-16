package main;

import java.util.ArrayList;

public final class Model {
    String name;
    ArrayList<String> attributes;
    ArrayList<String> types;
    int key;

    public Model(String name, ArrayList<String> attributes, ArrayList<String> types, int key) {
        this.name = name.toUpperCase().charAt(0) + name.substring(1);
        this.attributes = attributes;
        this.types = types;
        this.key = key;
        
        updateTypes();
    }
    
    public void updateTypes(){
        for(int i=0; i<types.size(); i++){
            String baru = types.get(i);
            
            switch(baru.toLowerCase()){
                case "varchar":
                    baru = "String";
                    break;
                    
                case "date":
                    baru = "Date";
                    break;
                    
                case "time":
                    baru = "Time";
                    break;
		case "bigint":
		    baru = "long";
		    break;
            }
            
            types.set(i, baru);
        }
    }

    @Override
    public String toString() {
        return name + " " + attributes.toString() + types.toString() + key;
    }
}
