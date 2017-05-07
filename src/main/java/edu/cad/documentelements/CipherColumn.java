package edu.cad.documentelements;

import edu.cad.entities.Curriculum;
import edu.cad.entities.CurriculumSubject;
import edu.cad.entities.Subject;
import org.apache.poi.ss.usermodel.Row;

public class CipherColumn extends AbstractColumn {
    
    public CipherColumn(Row row, Curriculum curriculum) {
        super(row, "#cipher", curriculum);
    }

    @Override
    public void fill(Row row, Subject subject) {
        for(CurriculumSubject element : curriculum.getCurriculumSubjects()){
            if(element.getSubject().equals(subject)){
                row.getCell(columnNumber).setCellValue(element.getCipher());
                break;
            }
        } 
    }   
}
