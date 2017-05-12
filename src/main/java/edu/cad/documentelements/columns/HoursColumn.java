package edu.cad.documentelements.columns;

import edu.cad.entities.CurriculumSubject;
import edu.cad.entities.Subject;import edu.cad.functionalinterfaces.SubjectProperty;
;
import org.apache.poi.ss.usermodel.Row;

public abstract class HoursColumn extends AbstractColumn {
    
    public HoursColumn(Row row, String token) {
        super(row, token);
        clear(row);
    }
    
    protected void setValue(Row row, CurriculumSubject record, SubjectProperty property){
        Subject subject = record.getSubject();
        double value = 0;

        for(Subject child : record.getCurriculum().getAllSubsubjects(subject)){
            value += property.getValue(child);
        }

        if (value > 0) {
            fill(row, value);
        } 
    }
}
