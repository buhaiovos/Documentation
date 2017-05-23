package edu.cad.generators;

public enum TypeOfGroupWork {
    Lection, Practice, Lab;
    
    public int getMinStudents(){
        switch(this){
            case Lection    :   return 20;
            case Practice   :   return 20;
            case Lab        :   return 12;
        }
        
        return -1;
    }
    
    public int getMaxStudents(){
        switch(this){
            case Lection    :   return 35;
            case Practice   :   return 35;
            case Lab        :   return 23;
        }
        
        return -1;
    }
}
