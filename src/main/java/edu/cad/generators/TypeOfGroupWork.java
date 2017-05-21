package edu.cad.generators;

public enum TypeOfGroupWork {
    Lection, Practice, Lab;
    
    public int getMaxStudents(){
        switch(this){
            case Lection    :   return 35;
            case Practice   :   return 35;
            case Lab        :   return 26;
        }
        
        return -1;
    }
}
