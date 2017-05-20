package edu.cad.documentelements.hourscolumns;

import edu.cad.entities.CurriculumSubject;
import edu.cad.entities.Subject;
import org.apache.poi.ss.usermodel.Row;

public class EctsColumn extends HoursColumn{
    
    public EctsColumn(int columnNumber) {
        super(columnNumber);
    }

    @Override
    public void fill(Row row, CurriculumSubject record) {
        setValue(row, record, Subject::getEcts);
    } 
}
