package edu.cad.documentelements.semestercolumns;

import edu.cad.entities.CurriculumSubject;
import edu.cad.entities.Subject;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

public class SemesterLectionsColumn extends SemesterColumn{
    
    public SemesterLectionsColumn(Sheet sheet, int startColumnIndex, int semester, 
            int weeks) {
        super(sheet, "#sem_lections", startColumnIndex);
        this.semester = semester;
        this.weeks = weeks;
    }
    
    @Override
    public void fill(Row row, CurriculumSubject record) {
        Subject subject = record.getSubject();
        double hours = subject.getSemesterHours(semester, record.getCurriculum(),
                Subject::getLections);
        hours /= (double) weeks;
        
        if(hours > 0){
            fill(row, hours);
        } else {
            clear(row);
        }    
    }
}
