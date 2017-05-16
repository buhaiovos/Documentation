package edu.cad.utils;

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
