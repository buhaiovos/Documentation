package edu.cad.documentelements.hourscolumns;

import edu.cad.documentelements.columns.AbstractColumn;
import edu.cad.entities.CurriculumSubject;
import edu.cad.entities.Subject;
import edu.cad.functionalinterfaces.SubjectProperty;
import org.apache.poi.ss.usermodel.Row;

public abstract class HoursColumn extends AbstractColumn {
    
    public HoursColumn(int columnNumber) {
        super(columnNumber);
    }
    
    protected void setValue(Row row, CurriculumSubject record, SubjectProperty property){
        double value = 0;

        for(Subject child : record.getSubject().getSubSubjects(record.getCurriculum())){
            value += property.getValue(child);
        }

        if (value > 0) {
            fill(row, value);
        } 
    }
}
