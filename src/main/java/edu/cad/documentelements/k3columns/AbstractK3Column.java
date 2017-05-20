package edu.cad.documentelements.k3columns;

import edu.cad.documentelements.columns.AbstractColumn;
import edu.cad.entities.CurriculumSubject;
import edu.cad.entities.Subject;
import org.apache.poi.ss.usermodel.Row;

public abstract class AbstractK3Column extends AbstractColumn{

    public  AbstractK3Column(int columnNumber){
        super(columnNumber);
    }
    
    @Override
    public void fill(Row row, CurriculumSubject record) {
        throw new UnsupportedOperationException("Not supported yet."); 
    }
    
    public abstract void fill(Row row, Subject subject);
}
