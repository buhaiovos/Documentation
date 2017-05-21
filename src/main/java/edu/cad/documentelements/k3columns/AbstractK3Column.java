package edu.cad.documentelements.k3columns;

import edu.cad.documentelements.columns.AbstractColumn;
import edu.cad.entities.CurriculumSubject;
import edu.cad.entities.Subject;
import edu.cad.utils.Utils;
import org.apache.poi.ss.usermodel.Row;

public abstract class AbstractK3Column extends AbstractColumn{

    public  AbstractK3Column(int columnNumber){
        super(columnNumber);
    }
    
    @Override
    public void fill(Row row, CurriculumSubject record) {
        throw new UnsupportedOperationException("Not supported yet."); 
    }
    
    public abstract String getValue(Subject subject);
    
    public void fill(Row row, Subject subject, String value){
        value = getValue(subject);
        
        if(Utils.isParseable(value)){
            fill(row, Integer.parseInt(value));
        }
    }
}
