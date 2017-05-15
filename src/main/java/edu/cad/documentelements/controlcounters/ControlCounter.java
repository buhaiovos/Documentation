package edu.cad.documentelements.controlcounters;

import edu.cad.daos.HibernateDAO;
import edu.cad.documentelements.AbstractDocumentElement;
import edu.cad.entities.ControlDictionary;
import edu.cad.entities.Curriculum;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;

public class ControlCounter extends AbstractDocumentElement{
    protected Cell cell;
    protected ControlDictionary control;
    
    public ControlCounter(Cell cell, ControlDictionary control) {
        this.cell = cell;
        this.control = control;
    }

    public void fill(Curriculum curriculum) {
        int count;
        StringBuilder value = new StringBuilder();
        
        if(control.getId() == 2){
            ControlDictionary diff = new HibernateDAO<>(ControlDictionary.class).get(9); 
            count = curriculum.countControlsByType(diff);
            
            if(count > 0){
                value.append(count);
                value.append('д');
                value.append('+');
            } 
        }
        
        count = curriculum.countControlsByType(control);
        
        if(count > 0)
            value.append(count);
        
        if(value.length() > 0){
            cell.setCellValue(value.toString());
        }
    }
    
    public void clear(){
        cell.setCellType(CellType.BLANK);
    }
}
