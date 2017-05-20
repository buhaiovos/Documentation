package edu.cad.documentelements.semestercolumns;

import edu.cad.entities.CurriculumSubject;
import edu.cad.entities.Subject;
import org.apache.poi.ss.usermodel.Row;

public class SemesterLabsColumn extends SemesterColumn{
    
    public SemesterLabsColumn(int columnNumber, int semester, int weeks) {
        super(columnNumber, semester, weeks);
    }
    
    @Override
    public void fill(Row row, CurriculumSubject record) {
        Subject subject = record.getSubject();
        double hours = subject.getSemesterHours(semester, record.getCurriculum(),
                Subject::getLabs);
        hours /= (double) weeks;
        
        if(hours > 0){
            fill(row, hours);
        } else {
            clear(row);
        }    
    }
}
