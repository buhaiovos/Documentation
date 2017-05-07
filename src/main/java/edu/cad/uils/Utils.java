package edu.cad.uils;

public class Utils {
    
    public static boolean isParseable(String value){
        try{
            Integer.parseInt(value);
        } catch(NumberFormatException ex) {
            return false;
        }
        
        return true;
    }
}
