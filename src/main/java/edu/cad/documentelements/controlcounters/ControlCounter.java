package edu.cad.documentelements.controlcounters;

import edu.cad.daos.HibernateDAO;
import edu.cad.documentelements.AbstractDocumentElement;
import edu.cad.entities.ControlDictionary;
import edu.cad.entities.Curriculum;
import org.apache.poi.ss.usermodel.Cell;

public class ControlCounter extends AbstractDocumentElement{
    protected Cell cell;
    protected ControlDictionary control;
    
    public ControlCounter(Cell cell, ControlDictionary control) {
        this.cell = cell;
        this.control = control;
    }

    public void fill(Curriculum curriculum) {
        StringBuilder value = new StringBuilder();
        value.append(curriculum.countControlsByType(control));
        
        if(control.getId() == 2){
            ControlDictionary diff = new HibernateDAO<>(ControlDictionary.class).get(9);
            value.append('+');
            value.append(curriculum.countControlsByType(diff));
            value.append('д');
        }
        
        cell.setCellValue(value.toString());
    }
}
