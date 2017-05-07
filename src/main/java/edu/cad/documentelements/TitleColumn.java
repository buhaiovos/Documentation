package edu.cad.documentelements;

import edu.cad.entities.Subject;
import org.apache.poi.ss.usermodel.Row;

public class TitleColumn extends AbstractColumn {
    
    public TitleColumn(Row row) {
        super(row, "#section", null);
    }

    @Override
    public void fill(Row row, Subject subject) {
        row.getCell(columnNumber).setCellValue(subject.getSubject().getDenotation());
    }
    
}
