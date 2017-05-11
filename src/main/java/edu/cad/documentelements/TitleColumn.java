package edu.cad.documentelements;

import edu.cad.entities.SubjectDictionary;
import org.apache.poi.ss.usermodel.Row;

public class TitleColumn extends AbstractColumn {
    
    public TitleColumn(Row row) {
        super(row, "#section");
    }

    @Override
    public void fill(Row row, edu.cad.entities.CurriculumSubject record) {
        SubjectDictionary subject = record.getSubject().getSubject();
        fill(row, subject.getDenotation());
    }
    
}
