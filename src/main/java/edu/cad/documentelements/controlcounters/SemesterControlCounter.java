package edu.cad.documentelements.controlcounters;

import edu.cad.daos.HibernateDAO;
import edu.cad.entities.ControlDictionary;
import edu.cad.entities.Curriculum;
import org.apache.poi.ss.usermodel.Cell;

public class SemesterControlCounter extends ControlCounter{
    private int semester; 
    
    public SemesterControlCounter(Cell cell, ControlDictionary control, int semester){
        super(cell, control);
        this.semester = semester;
    }

    @Override
    public void fill(Curriculum curriculum) {
        int count;
        StringBuilder value = new StringBuilder();
        
        if(control.getId() == 2){
            ControlDictionary diff = new HibernateDAO<>(ControlDictionary.class).get(9); 
            count = curriculum.countControlsByType(semester, diff);
            
            if(count > 0){
                value.append(count);
                value.append('д');
                value.append('+');
            } 
        }
        
        count = curriculum.countControlsByType(semester, control);
        
        if(count > 0)
            value.append(count);
        
        if(value.length() > 0){
            cell.setCellValue(value.toString());
        }  
    }
    
    
}
