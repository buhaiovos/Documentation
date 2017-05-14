package edu.cad.documentelements.semestercolumns;

import edu.cad.documentelements.columns.AbstractColumn;
import edu.cad.entities.CurriculumSubject;
import edu.cad.entities.Subject;
import edu.cad.uils.Utils;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

public class SemesterColumn extends AbstractColumn{
    protected int semester;
    protected int weeks;
    
    public SemesterColumn(int columnNumber, int semester, int weeks) {
        super(columnNumber);
        this.semester = semester;
        this.weeks = weeks;
    }
    
    @Override
    public void fill(Row row, CurriculumSubject record) {
        Subject subject = record.getSubject();
        double hours = subject.getSemesterHours(semester, record.getCurriculum(),
                Subject::getTotalHours);
        hours /= (double) weeks;
        
        if(hours > 0){
            fill(row, hours);
        } else {
            clear(row);
        }
    }

    public int getSemester() {
        return semester;
    }

    public int getWeeks() {
        return weeks;
    }
}
