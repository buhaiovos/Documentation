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
        StringBuilder value = new StringBuilder();
        value.append(curriculum.countControlsByType(semester, control));
        
        if(control.getId() == 2){
            ControlDictionary diff = new HibernateDAO<>(ControlDictionary.class).get(9);
            value.append('+');
            value.append(curriculum.countControlsByType(semester, diff));
            value.append('д');
        }
        
        cell.setCellValue(value.toString());
    }
}
