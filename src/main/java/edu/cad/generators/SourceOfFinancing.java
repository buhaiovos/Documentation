package edu.cad.generators;

import edu.cad.entities.AcademicGroup;

public enum SourceOfFinancing {
    Budgetary, 
    Contract;
    
    public boolean sourceEquals(AcademicGroup group){
        switch(this){
            case Budgetary  :   if(group.isBudgetary())     return true;
            case Contract   :   if(!group.isBudgetary())    return true;
        }
        
        return false;
    }
    
    public int getStudents(AcademicGroup group){
        switch(this){
            case Budgetary  :   return group.getBudgetaryStudents();
            case Contract   :   return group.getContractStudents();
        }
        
        return 0;
    }
}
