package edu.cad.documentelements.columns;

import edu.cad.entities.CurriculumSubject;
import edu.cad.entities.Subject;
import org.apache.poi.ss.usermodel.Row;

public class LabsColumn extends HoursColumn{
    
    public LabsColumn(Row row) {
        super(row, "#labs");
    }

    @Override
    public void fill(Row row, CurriculumSubject record) {
        setValue(row, record, Subject::getLabs);
    } 
}
