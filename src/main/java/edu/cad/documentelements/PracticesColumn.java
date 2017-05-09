package edu.cad.documentelements;

import edu.cad.entities.CurriculumSubject;
import edu.cad.entities.Subject;
import org.apache.poi.ss.usermodel.Row;

public class PracticesColumn extends HoursColumn{
    
    public PracticesColumn(Row row) {
        super(row, "practices");
    }

    @Override
    public void fill(Row row, CurriculumSubject record) {
        setValue(row, record, Subject::getPractices);
    } 
}
