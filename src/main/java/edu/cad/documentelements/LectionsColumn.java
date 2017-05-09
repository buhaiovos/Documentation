package edu.cad.documentelements;

import edu.cad.entities.CurriculumSubject;
import edu.cad.entities.Subject;
import org.apache.poi.ss.usermodel.Row;

public class LectionsColumn extends HoursColumn{
    
    public LectionsColumn(Row row) {
        super(row, "lections");
    }

    @Override
    public void fill(Row row, CurriculumSubject record) {
        setValue(row, record, Subject::getLections);
    } 
}
