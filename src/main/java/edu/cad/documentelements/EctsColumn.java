package edu.cad.documentelements;

import edu.cad.entities.CurriculumSubject;
import edu.cad.entities.Subject;
import org.apache.poi.ss.usermodel.Row;

public class EctsColumn extends HoursColumn{
    
    public EctsColumn(Row row) {
        super(row, "#ects");
    }

    @Override
    public void fill(Row row, CurriculumSubject record) {
        setValue(row, record, Subject::getEcts);
    } 
}
