package edu.cad.documentelements.semestercolumns;

import edu.cad.entities.CurriculumSubject;
import edu.cad.entities.Subject;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

public class SemesterPracticesColumn extends SemesterColumn{
    
    public SemesterPracticesColumn(Sheet sheet, int startColumnIndex, int semester, 
            int weeks) {
        super(sheet, "#sem_practices", startColumnIndex);
        this.semester = semester;
        this.weeks = weeks;
    }
    
    @Override
    public void fill(Row row, CurriculumSubject record) {
        Subject subject = record.getSubject();
        double hours = subject.getSemesterHours(semester, record.getCurriculum(),
                Subject::getPractices);
        hours /= (double) weeks;
        
        if(hours > 0){
            fill(row, hours);
        } else {
            clear(row);
        }  
    }   
}
