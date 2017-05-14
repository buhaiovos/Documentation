package edu.cad.documentelements.columns;

import edu.cad.entities.CurriculumSubject;
import edu.cad.entities.Subject;
import org.apache.poi.ss.usermodel.Row;

public class LectionsColumn extends HoursColumn{
    
    public LectionsColumn(int columnNumber) {
        super(columnNumber);
    }

    @Override
    public void fill(Row row, CurriculumSubject record) {
        setValue(row, record, Subject::getLections);
    } 
}
